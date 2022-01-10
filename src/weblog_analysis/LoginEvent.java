package weblog_analysis;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LoginEvent extends WindowAdapter implements ActionListener, KeyListener {

	private LoginView loginView;

	public LoginEvent(LoginView loginView) {
		this.loginView = loginView;
	}

	@Override
	public void windowClosing(WindowEvent e) {
		super.windowClosing(e);

		loginView.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == loginView.getLoginButton()) {
			loginProcess(loginView);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_ENTER:
			loginProcess(loginView);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	public void loginProcess(LoginView loginView) {
		new LoginProcess(loginView);
	}
}
