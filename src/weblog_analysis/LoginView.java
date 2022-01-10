package weblog_analysis;

import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class LoginView extends JFrame {

	public static final int windowWidth = 720;
	public static final int windowHeight = 480;

	private JTextField idField;
	private JPasswordField passwordField;
	private JButton loginButton;

	public LoginView() {
		super("로그 분석 - 로그인");

		// 기준값 설정
		int width = 400;
		int height = 50;
		int x = (windowWidth / 2) - (width / 2);
		int y = 150;
		
		Font font = new Font("맑은 고딕", Font.BOLD, 15);		
		
		// 쌍용로고 이미지
		ImageIcon logo = new ImageIcon("src/images/logo.jpg");
		Image logoIcon = logo.getImage();
		Image logoIconResize = logoIcon.getScaledInstance(250, 50, Image.SCALE_SMOOTH);
		ImageIcon iconResize = new ImageIcon(logoIconResize);
		// 이미지 넣을 라벨
		JLabel img = new JLabel(iconResize);
		img.setBounds(x, y - 100, width, height);
		add(img);

		// 아이디 필드
		idField = new JTextField();
		idField.setBounds(x, y, width, height);
		idField.setFont(font);
		add(idField);

		// 패스워드 필드
		passwordField = new JPasswordField();
		passwordField.setBounds(x, y + 100, width, height);
		passwordField.setFont(font);
		add(passwordField);

		// 아이디 라벨
		JLabel idLabel = new JLabel("ID");
		idLabel.setBounds(x, y - 35, 100, 50);
		idLabel.setFont(font);
		add(idLabel);

		// 패스워드 라벨
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(x, y + 100 - 35, 100, 50);
		passwordLabel.setFont(font);
		add(passwordLabel);

		// 로그인 버튼
		loginButton = new JButton("Login");
		loginButton.setBounds(x, y + 200, width, height);
		loginButton.setFont(font);
		add(loginButton);

		// 윈도우 설정
		setLayout(null);
		setResizable(false);
		setBounds(800, 200, windowWidth, windowHeight);
		setVisible(true);

		// 이벤트 처리
		LoginEvent loginEvent = new LoginEvent(this);
		addWindowListener(loginEvent);
		loginButton.addActionListener(loginEvent);
		passwordField.addKeyListener(loginEvent);
	}

	public JTextField getIdField() {
		return idField;
	}

	public JPasswordField getPasswordField() {
		return passwordField;
	}

	public JButton getLoginButton() {
		return loginButton;
	}
}
