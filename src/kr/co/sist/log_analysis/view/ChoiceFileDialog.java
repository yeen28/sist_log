package kr.co.sist.log_analysis.view;

import java.awt.FileDialog;

@SuppressWarnings("serial")
public class ChoiceFileDialog extends FileDialog {
	
	String directoryFile;

	public ChoiceFileDialog(LogAnalysisView parent) {
		super(parent, "���� ����", FileDialog.LOAD);

		setVisible(true);
		directoryFile = getDirectory() + getFile();
		
		parent.getDirectoryLabel().setText(directoryFile);
	}
	
}