package weblog_analysis;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class ViewLogDialog extends JDialog {

	public ViewLogDialog(LogAnalysisView parent) throws RangeException, Exception {
		super(parent, "로그 분석", true);
		Font font = new Font("맑은 고딕", Font.BOLD, 13);

		JTextArea data = new JTextArea();
		JScrollPane jsp = new JScrollPane(data);
		data.setFont(font);
		add("Center", jsp);
		data.setBackground(new Color(0xFFFFFF));

		// 데이터 가져와서 여기에 띄우기만 하면됨
		LogProcess logProcess = new LogProcess(parent);

		data.setText("1. 최다 사용 키: " + logProcess.getMostUsedKeyName() + ", 횟수: "
				+ logProcess.getMostUsedKeyCount() + "\n2. 브라우저별 접속 횟수: " + logProcess.printBrowserStats()
				+ "\n3. 서비스를 성공적으로 수행한 횟수(200): " + logProcess.getCount200() + ", 실패 횟수(404): "
				+ logProcess.getCount404() + "\n4. 요청이 가장 많은 시간: " + logProcess.getHourZone() + "시"
				+ "\n5. 비정상적인 요청이 발생한 횟수(403): " + logProcess.getCount403() + ", 비율(%): "
				+ logProcess.getCount403Rate() + "\n6. 요청에 대한 에러가 발생한 횟수(500): " + logProcess.getCount500()
				+ ", 비율(%): " + logProcess.getCount500Rate() + "\n7. 입력된 라인범위중 최다 사용키의 이름과 횟수: "
				+ logProcess.getMostUsedKeyNameRange() + ", " + logProcess.getMostUsedKeyCountRange());

		// 창 설정
		setBounds(parent.getX() + 50, parent.getY() + 50, 480, 240);
		setVisible(true);
	}
}
