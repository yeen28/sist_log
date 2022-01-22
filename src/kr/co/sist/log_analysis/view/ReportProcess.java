package kr.co.sist.log_analysis.view;

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
		
		String log = "���ϰ��: " + logProcess.getLogFilePath() + "\n����Ʈ �ۼ���: " + format2.format(time.getTime())
		+ "\n\n1. �ִ� ���Ű�� �̸��� Ƚ��: " + logProcess.getMostUsedKeyName() + ", " + logProcess.getMostUsedKeyCount()
		+ "\n2. �������� ���� Ƚ��(����): " + logProcess.printBrowserStats()
		+ "\n3. ���񽺸� ���������� ������ Ƚ��(200): " + logProcess.getCount200() + ", ����Ƚ��(404): " + logProcess.getCount404()
		+ "\n4. ��û�� ���� ���� �ð�: " + logProcess.getHourZone() + "��"
		+ "\n5. �� �������� ��û �߻� Ƚ��(403), ����: " + logProcess.getCount403() + "(" + logProcess.getCount403Rate() + "%)"
		+ "\n6. ��û�� ���� ���� �߻� Ƚ��(500), ����: " + logProcess.getCount500() + "(" + logProcess.getCount500Rate() + "%)"
		+ "\n7. �Էµ� ���ι����� �ִ� ���Ű�� �̸��� Ƚ��: " + logProcess.getMostUsedKeyNameRange() + ", " + logProcess.getMostUsedKeyCountRange();
		
		bufferedWriter.write(log);
		
		bufferedWriter.flush();
		bufferedWriter.close();
		
		JOptionPane.showMessageDialog(parent, directory + reportName + "�� ����Ʈ�� ����Ǿ����ϴ�.", "����Ʈ �ۼ�", JOptionPane.PLAIN_MESSAGE);
	}

}
