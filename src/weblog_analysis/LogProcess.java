package weblog_analysis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class LogProcess {

	private String logFilePath;

	private File logFile;
	private FileReader fileReader;
	private BufferedReader bufferedReader;

	private Map<String, Integer> mostUsedKey = new HashMap<String, Integer>(); // 1번문항 저장소
	private String mostUsedKeyName;
	private int mostUsedKeyCount;

	private Map<String, Integer> browserCount = new HashMap<String, Integer>(); // 2번문항 저장소

	private int[] hourArr = new int[24];
	private int hourZone;
	private int hourCount;

	private int startLine;
	private int endLine;

	private Map<String, Integer> mostUsedKeyRange = new HashMap<String, Integer>(); // 7번문항 저장소
	private String mostUsedKeyNameRange;
	private int mostUsedKeyCountRange;

	private int total = 0;
	private int count200 = 0;
	private int count403 = 0;
	private int count404 = 0;
	private int count500 = 0;

	public LogProcess(LogAnalysisView view) throws RangeException, FileNotFoundException, NullPointerException, Exception {
		logFilePath = view.getDirectoryLabel().getText();
		logFile = new File(logFilePath);
		fileReader = new FileReader(logFile);
		bufferedReader = new BufferedReader(fileReader);

		readLog(); // 1~6번
		checkRange(view.getStartLine().getText(), view.getEndLine().getText());
		no7(); // 7번

		bufferedReader.close();

	}

	public void readLog() throws IOException {
		String line = "";

		// 한줄씩 읽기를 반복하여 문서 전체 읽기
		while ((line = bufferedReader.readLine()) != null) {
			total++;

			// 에러 분류 및 3, 5, 6 번 요청종류 카운트
			switch (line.substring(line.indexOf('[') + 1, line.indexOf(']'))) {
			case "200": { // 정상처리
				count200++;
				no1(line);
				break;
			}
			case "403": { // 비정상 요청: 키가 없음
				count403++;
				break;
			}
			case "404": { // 요청 실패
				count404++;
				no1(line);
				break;
			}
			case "500": { // 요청에대한 에러
				count500++;
				no1(line);
				break;
			}
			}

			// 2번: 브라우저별 접속횟수 비율
			int index = line.indexOf("[", 7);
			String browser = line.substring(index + 1, line.indexOf("]", index));

			if (browserCount.containsKey(browser)) {
				browserCount.put(browser, browserCount.get(browser) + 1);
			} else {
				browserCount.put(browser, 1);
			}

			// 4번. 요청시간대 통계
			index = line.lastIndexOf("[") + 12;
			String hour = line.substring(index, index + 2);
			hourArr[Integer.parseInt(hour)]++;

		}

		// 1번 결과 정리
		Entry<String, Integer> maxEntry = null;

		Set<Entry<String, Integer>> entrySet = mostUsedKey.entrySet();
		for (Entry<String, Integer> entry : entrySet) {
			if (maxEntry == null || (entry.getValue() > maxEntry.getValue())) {
				maxEntry = entry;
			}
		}

		mostUsedKeyName = maxEntry.getKey();
		mostUsedKeyCount = maxEntry.getValue();

		// 4번 결과 정리
		hourZone = 0;
		hourCount = hourArr[0];

		for (int i = 0; i < hourArr.length; i++) {
			if (hourCount < hourArr[i]) {
				hourZone = i;
				hourCount = hourArr[i];
			}
		}
	}

	public void no1(String line) {
		int idx1 = line.indexOf("key=") + 4;
		int idx2 = line.indexOf("&query");

		String keyName = line.substring(idx1, idx2);

		if (mostUsedKey.containsKey(keyName)) {
			mostUsedKey.put(keyName, mostUsedKey.get(keyName) + 1);
		} else {
			mostUsedKey.put(keyName, 1);
		}
	}

	public void checkRange(String startLine, String endLine) throws RangeException {
		// 받아온 범위를 문자열에서 정수로 변경
		try {
			this.startLine = Integer.parseInt(startLine);
		} catch (Exception e) {
			this.startLine = 0;
		}

		try {
			this.endLine = Integer.parseInt(endLine);
		} catch (Exception e) {
			this.endLine = Integer.MAX_VALUE;
		}

		// 끝 범위가 시작범위보다 큰 경우 = 예외처리
		if (this.endLine - this.startLine < 0) {
			throw new RangeException();
		}

		// 시작 범위가 라인 범위를 벗어난 경우 = 예외처리
		if (this.startLine > total) {
			throw new RangeException();
		}

		// 끝 범위가 라인범위를 벗어난 경우 = 라인 끝까지
		if (this.endLine > total) {
			this.endLine = total;
		}
	}

	public void no7() throws NullPointerException, FileNotFoundException, IOException {
		FileReader fileReader = new FileReader(logFile);
		BufferedReader bufferedReader = new BufferedReader(fileReader);

		String line = "";

		for (int i = 0; i < startLine - 1; i++) { // 버퍼리더 시작위치까지 옮기기
			bufferedReader.readLine();
		}

		for (int i = 0; i < endLine - startLine + 1; i++) {
			line = bufferedReader.readLine();

			if (line.substring(line.indexOf('[') + 1, line.indexOf(']')).equals("403")) { // 에러 403 인경우
				// 403의 경우 키가 없으므로 아무것도 안함
			} else {
				int idx1 = line.indexOf("key=") + 4;
				int idx2 = line.indexOf("&query");

				String keyName = line.substring(idx1, idx2);
				
				if (mostUsedKeyRange.containsKey(keyName)) {
					mostUsedKeyRange.put(keyName, mostUsedKeyRange.get(keyName) + 1);
				} else {
					mostUsedKeyRange.put(keyName, 1);
				}
			}
		}
		
		Entry<String, Integer> maxEntryRange = null;
		Set<Entry<String, Integer>> entrySetRange = mostUsedKeyRange.entrySet();
		
		for (Entry<String, Integer> entryRange : entrySetRange) {
			if (maxEntryRange == null || (entryRange.getValue() > maxEntryRange.getValue())) {
				maxEntryRange = entryRange;
			}
		}

		mostUsedKeyNameRange = maxEntryRange.getKey();
		mostUsedKeyCountRange = maxEntryRange.getValue();
		
		bufferedReader.close();
	}

	public String getLogFilePath() {
		return logFilePath;
	}

	// 1번 게터
	public String getMostUsedKeyName() {
		return mostUsedKeyName;
	}

	public int getMostUsedKeyCount() {
		return mostUsedKeyCount;
	}

	// 2번 정리 및 문자열로 리턴
	public String printBrowserStats() {
		Iterator<String> browserIter = browserCount.keySet().iterator();

		String result = "";

		while (browserIter.hasNext()) {
			String browserName = browserIter.next();
			int count = browserCount.get(browserName);
			double rate = ((double) count / (double) total) * 100;
			rate = Math.round(rate * 100) / 100.0;

			result += "\n\t" + browserName + ": " + count + " (" + rate + "%)";
		}

		return result;
	}

	// 3번 게터
	public int getCount200() {
		return count200;
	}

	public int getCount404() {
		return count404;
	}

	// 4번 게터
	public int getHourZone() {
		return hourZone;
	}

	// 5번 게터
	public int getCount403() {
		return count403;
	}

	public double getCount403Rate() {
		double rate = ((double) count403 / (double) total) * 100;

		return Math.round(rate * 100) / 100.0;
	}

	// 6번 게터
	public int getCount500() {
		return count500;
	}

	public double getCount500Rate() {
		double rate = ((double) count500 / (double) total) * 100;

		return Math.round(rate * 100) / 100.0;
	}

	public String getMostUsedKeyNameRange() {
		return mostUsedKeyNameRange;
	}

	public int getMostUsedKeyCountRange() {
		return mostUsedKeyCountRange;
	}
	
}