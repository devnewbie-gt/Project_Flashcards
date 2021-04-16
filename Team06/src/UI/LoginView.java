package UI;

import java.util.HashMap;

import javax.swing.JFrame;

import DAO.SuperDao;
import DAO.UserDao;
import View.QuizMenuFrame;

public class LoginView extends SuperView {
	UserDao userDao;
	
	public LoginView(JFrame frame, SuperDao dao) {
		super(frame, dao);
		userDao = (UserDao)dao.getDao();
	}
	@Override
	protected void placeSubFields() {
	}
	@Override
	protected void placeSubButtons() {
	}
	@Override
	protected void setLabelButtonConfig() {
		keyLabel.setText("ID:");
		valueLabel.setText("PW:");
		titleBar.setText("LOGIN");
		getButton.setText("Join");
		postButton.setText("Login");
	}
	@Override
	protected void setKeyValueFieldConfig() {
	}
	@Override
	protected void setBtnCmd() {
	}
	@Override
	protected void doPosting(HashMap<String, String> crawlInfo) {
		System.out.println(this.nextPanel);

		if(userDao.login(crawlInfo.get("key"), crawlInfo.get("value"))) {
			QuizMenuFrame quizMenuFrame = new QuizMenuFrame(frame, crawlInfo.get("key"));
		}
	}
	@Override
	protected boolean isPostDone() {
		return false;
	}
	@Override
	protected void updateResponseBar() {
	}
	@Override
	protected void doPostGet() {
	}
	@Override
	protected void doSubService(String btnCmd) {
	}
}
