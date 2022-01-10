package weblog_analysis;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;

import javax.swing.JOptionPane;

public class LogAnalysisEvent extends WindowAdapter implements ActionListener {

	LogAnalysisView logAnalysisView;
	private boolean reportWrite;

	public LogAnalysisEvent(LogAnalysisView logAnalysisView) {
		this.logAnalysisView = logAnalysisView;
		this.reportWrite = logAnalysisView.getReportWrite();
	}

	@Override
	public void windowClosing(WindowEvent e) {
		super.windowClosing(e);
		logAnalysisView.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == logAnalysisView.getFileChoiceButton()) {
			choiceFile();
		}

		if (e.getSource() == logAnalysisView.getViewButton()) {
			viewLogInfo();
		}

		if (e.getSource() == logAnalysisView.getReportButton()) {
			reportLogInfo();
		}
	}

	public void choiceFile() {
		new ChoiceFileDialog(logAnalysisView);
	}

	public void viewLogInfo() {
		try {
			new ViewLogDialog(logAnalysisView);
		} catch (FileNotFoundException fe) {
			fe.printStackTrace();
			JOptionPane.showMessageDialog(logAnalysisView, "������ ���� ������ �ּ���.", "���� ����", JOptionPane.ERROR_MESSAGE);
		} catch (RangeException re) {
			re.printStackTrace();
			JOptionPane.showMessageDialog(logAnalysisView, "������ �߸��Ǿ����ϴ�.", "���� ����", JOptionPane.ERROR_MESSAGE);
		} catch (NullPointerException ne) {
			ne.printStackTrace();
			JOptionPane.showMessageDialog(logAnalysisView, "������ ������ �ּ���.", "���� ����", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(logAnalysisView, "�߸��� �����Դϴ�.", "���� ����", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void reportLogInfo() {
		if (reportWrite) {
			try {
				new ReportProcess(logAnalysisView);
			} catch (FileNotFoundException fe) {
				fe.printStackTrace();
				JOptionPane.showMessageDialog(logAnalysisView, "������ ���� ������ �ּ���.", "���� ����", JOptionPane.ERROR_MESSAGE);
			} catch (RangeException re) {
				re.printStackTrace();
				JOptionPane.showMessageDialog(logAnalysisView, "������ �߸��Ǿ����ϴ�.", "���� ����", JOptionPane.ERROR_MESSAGE);
			} catch (NullPointerException ne) {
				ne.printStackTrace();
				JOptionPane.showMessageDialog(logAnalysisView, "������ ������ �ּ���.", "���� ����", JOptionPane.ERROR_MESSAGE);
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(logAnalysisView, "�߸��� �����Դϴ�.", "���� ����", JOptionPane.ERROR_MESSAGE);
			}

		} else {
			JOptionPane.showMessageDialog(logAnalysisView, "����Ʈ �ۼ� ������ �����ϴ�.", "����Ʈ ����", JOptionPane.ERROR_MESSAGE);
		}
	}

}
