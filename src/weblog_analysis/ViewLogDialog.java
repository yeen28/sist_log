package weblog_analysis;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class ViewLogDialog extends JDialog {

	public ViewLogDialog(LogAnalysisView parent) throws RangeException, Exception {
		super(parent, "�α� �м�", true);
		Font font = new Font("���� ���", Font.BOLD, 13);

		JTextArea data = new JTextArea();
		JScrollPane jsp = new JScrollPane(data);
		data.setFont(font);
		add("Center", jsp);
		data.setBackground(new Color(0xFFFFFF));

		// ������ �����ͼ� ���⿡ ���⸸ �ϸ��
		LogProcess logProcess = new LogProcess(parent);

		data.setText("1. �ִ� ��� Ű: " + logProcess.getMostUsedKeyName() + ", Ƚ��: "
				+ logProcess.getMostUsedKeyCount() + "\n2. �������� ���� Ƚ��: " + logProcess.printBrowserStats()
				+ "\n3. ���񽺸� ���������� ������ Ƚ��(200): " + logProcess.getCount200() + ", ���� Ƚ��(404): "
				+ logProcess.getCount404() + "\n4. ��û�� ���� ���� �ð�: " + logProcess.getHourZone() + "��"
				+ "\n5. ���������� ��û�� �߻��� Ƚ��(403): " + logProcess.getCount403() + ", ����(%): "
				+ logProcess.getCount403Rate() + "\n6. ��û�� ���� ������ �߻��� Ƚ��(500): " + logProcess.getCount500()
				+ ", ����(%): " + logProcess.getCount500Rate() + "\n7. �Էµ� ���ι����� �ִ� ���Ű�� �̸��� Ƚ��: "
				+ logProcess.getMostUsedKeyNameRange() + ", " + logProcess.getMostUsedKeyCountRange());

		// â ����
		setBounds(parent.getX() + 50, parent.getY() + 50, 480, 240);
		setVisible(true);
	}
}
