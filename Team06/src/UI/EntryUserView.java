package UI;

import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import DAO.SuperDao;
import DAO.UserDao;
import View.QuizMenuFrame;

public class EntryUserView extends SuperView {
	UserDao userDao;
	JLabel checkLabel;
	JTextField checkField;
	JButton checkButton;
	String checkBtnCmd;
	
	QuizMenuFrame qf;
	
	public EntryUserView(JFrame frame, SuperDao dao) {
		super(frame, dao);
		userDao = (UserDao)dao.getDao();
	}
	@Override
	protected void placeSubFields() {
		checkLabel = new JLabel();
		checkField = new JTextField();
		checkButton = new JButton();
		placeFactory.placeNext(checkLabel);
		placeFactory.placeBelow(checkField);
		placeFactory.placeNext(checkButton);
		checkButton.addActionListener(this);
	}
	@Override
	protected void placeSubButtons() {
	}
	@Override
	protected void setLabelButtonConfig() {
		keyLabel.setText("ID:");
		valueLabel.setText("PW:");
		checkLabel.setText("PW:");
		titleBar.setText("Entry User");
		getButton.setText("CANCEL");
		postButton.setText("CONFIRM");
		checkButton.setText("ID CHECK");
	}
	@Override
	protected void setKeyValueFieldConfig() {
		viewInfo.add(checkField);
		checkField.setName("check");
	}
	@Override
	protected void setBtnCmd() {
		getBtnCmd = "CANCEL";
		postBtnCmd = "CONFIRM";
		checkBtnCmd = "ID CHECK";
	}
	@Override
	protected void doPosting(HashMap<String, String> crawlInfo) {
		if(crawlInfo.get("value").equals(checkField.getText())) {
			userDao.entryUser(crawlInfo.get("key"), crawlInfo.get("value"));
			JOptionPane.showMessageDialog(this, "회원등록에 성공하였습니다.   환영합니다! " + crawlInfo.get("key") + "님!!");
			frame.remove(this);
			frame.getContentPane().add(new LoginView(frame, userDao));
			frame.revalidate();
			frame.repaint();
		} else {
			JOptionPane.showMessageDialog(this, "비밀번호를 다시 확인해주세요");
			return;
		}
	}
	@Override
	protected boolean isPostDone() {
		return checkID();
	}
	@Override
	protected void updateResponseBar() {
		
	}
	@Override
	protected void doSubService(String btnCmd) {
		if(btnCmd.equals(checkBtnCmd)) {
			doCheckPost();
		}
	}
	@Override
	protected void doPostGet() {
		viewInfo.get(0).setText("");
		validate();
		repaint();
	}
	private void doCheckPost() {
		if(isPostDone()) {
		//	updateResponseBar();
			JOptionPane.showMessageDialog(this, "이미 등록된 ID입니다");
			doPostGet();
			return;
		} 
		JOptionPane.showMessageDialog(this, "사용할 수 있는 ID입니다");
		//updateResponseBar();
		
	}
	private boolean checkID() {
		String ID = crawlViewInfo().get("key");
		return userDao.isUserExist(ID);
	}
}
