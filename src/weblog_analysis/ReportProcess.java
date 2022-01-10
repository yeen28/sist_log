package weblog_analysis;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JOptionPane;

public class ReportProcess {

	public ReportProcess(LogAnalysisView parent) throws RangeException, Exception {
		LogProcess logProcess = new LogProcess(parent);

		SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar time = Calendar.getInstance();

		String directory = "c:/dev/report";
		String reportName = "/report_" + format1.format(time.getTime()) + ".dat";

		new File(directory).mkdirs();
		BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(directory + reportName), "utf-8"));
		
		String log = "파일경로: " + logProcess.getLogFilePath() + "\n리포트 작성일: " + format2.format(time.getTime())
		+ "\n\n1. 최다 사용키의 이름과 횟수: " + logProcess.getMostUsedKeyName() + ", " + logProcess.getMostUsedKeyCount()
		+ "\n2. 브라우저별 접속 횟수(비율): " + logProcess.printBrowserStats()
		+ "\n3. 서비스를 성공적으로 수행한 횟수(200): " + logProcess.getCount200() + ", 실패횟수(404): " + logProcess.getCount404()
		+ "\n4. 요청이 가장 많은 시간: " + logProcess.getHourZone() + "시"
		+ "\n5. 비 정상적인 요청 발생 횟수(403), 비율: " + logProcess.getCount403() + "(" + logProcess.getCount403Rate() + "%)"
		+ "\n6. 요청에 대한 에러 발생 횟수(500), 비율: " + logProcess.getCount500() + "(" + logProcess.getCount500Rate() + "%)"
		+ "\n7. 입력된 라인범위중 최다 사용키의 이름과 횟수: " + logProcess.getMostUsedKeyNameRange() + ", " + logProcess.getMostUsedKeyCountRange();
		
		bufferedWriter.write(log);
		
		bufferedWriter.flush();
		bufferedWriter.close();
		
		JOptionPane.showMessageDialog(parent, directory + reportName + "에 리포트가 저장되었습니다.", "리포트 작성", JOptionPane.PLAIN_MESSAGE);
	}

}
