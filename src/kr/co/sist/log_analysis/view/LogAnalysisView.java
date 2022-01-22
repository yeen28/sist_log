package kr.co.sist.log_analysis.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import kr.co.sist.log_analysis.evt.LogAnalysisEvent;

@SuppressWarnings("serial")
public class LogAnalysisView extends JFrame {

	private JButton fileChoiceButton;
	private JButton viewButton;
	private JTextField startLine;
	private JTextField endLine;
	private JButton reportButton;
	private JLabel directoryLabel;

	private Boolean reportWrite = true;

	public LogAnalysisView(String id) {
		super("�α׺м� - �м�â");

		if (id.equals("root")) { // root�� ��� ����false
			reportWrite = false;
		}

		LogAnalysisEvent logAnalysisEvent = new LogAnalysisEvent(this);

		// ������Ʈ ���� ����
		int windowWidth = 720;
		int outputButtonWidth = 120;
		
		// ��Ʈ ����
		Font font = new Font("���� ���", Font.BOLD, 12);

		// ���� ǥ�� ��
		JLabel idLabel = new JLabel("���� ����: " + id);
		idLabel.setBounds(100, 10, 100, 40);
		add(idLabel);

		// ���� ���� ��ư
		fileChoiceButton = new JButton("���� ����");
		fileChoiceButton.setBounds(100, 80, 100, 30);
		fileChoiceButton.setFont(font);
		add(fileChoiceButton);
		fileChoiceButton.addActionListener(logAnalysisEvent);

		// ���ϰ�ι� ���ϸ� ��
		directoryLabel = new JLabel();
		directoryLabel.setBounds(210, 80, windowWidth - 290, 30);
		directoryLabel.setOpaque(true);
		directoryLabel.setBackground(new Color(0xFFFFFF));
		directoryLabel.setFont(font);
		add(directoryLabel);

		// ���� �ȳ� ��
		JLabel lineInput = new JLabel();
		lineInput.setText("<html>�˻��� ���� ����: <br>���� ~ ��</html>");
		lineInput.setBounds(100, 200, 150, 30);
		lineInput.setFont(font);
		add(lineInput);

		// ���� �Է� (���� ����)
		startLine = new JTextField();
		startLine.setBounds(220, 200, 100, 30);
		startLine.setFont(font);
		add(startLine);

		// ���� �Է� (�� ����)
		endLine = new JTextField();
		endLine.setBounds(340, 200, 100, 30);
		endLine.setFont(font);
		add(endLine);
		
		// ���� �Է� �߰� ����ǥ�ö�
		JLabel lineRange = new JLabel();
		lineRange.setText("~");
		lineRange.setBounds(325, 200, 100, 30);
		lineRange.setFont(font);
		add(lineRange);

		// �� ��ư
		viewButton = new JButton("View");
		viewButton.setBounds((windowWidth / 2) - (outputButtonWidth + 80), 300, outputButtonWidth, 50);
		viewButton.setFont(font);
		add(viewButton);
		viewButton.addActionListener(logAnalysisEvent);

		// ����Ʈ ��ư
		reportButton = new JButton("Report����");
		reportButton.setBounds((windowWidth / 2) + 80, 300, outputButtonWidth, 50);
		reportButton.setFont(font);
		add(reportButton);
		reportButton.addActionListener(logAnalysisEvent);

		// ������ ����
		setLayout(null);
		setResizable(false);
		setBounds(800, 200, windowWidth, 480);
		setVisible(true);
		addWindowListener(logAnalysisEvent);
	}

	public Boolean getReportWrite() {
		return reportWrite;
	}

	public JButton getFileChoiceButton() {
		return fileChoiceButton;
	}

	public JLabel getDirectoryLabel() {
		return directoryLabel;
	}

	public JTextField getStartLine() {
		return startLine;
	}

	public JTextField getEndLine() {
		return endLine;
	}

	public JButton getViewButton() {
		return viewButton;
	}

	public JButton getReportButton() {
		return reportButton;
	}

}