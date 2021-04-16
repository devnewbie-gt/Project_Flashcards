package UI;

import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;

import DAO.QuizDao;
import DAO.RuntimeQuizDao;
import DAO.SuperDao;
import DAO.UserDao;
import View.QuizMenu;

public class QuizView extends SuperView {
	RuntimeQuizDao runtimeQuizDao;
	JButton wrongButton;
	String wrongBtnCmd;
	QuizDao quizDao;

	public QuizView(JFrame frame, SuperDao superDao) {
		super(frame, superDao);
		
		runtimeQuizDao = (RuntimeQuizDao)superDao.getDao();
	}
	
	@Override
	protected void placeSubFields() {
	}
	@Override
	protected void placeSubButtons() {
//		JButton revealButton = new JButton();
/*		placeFactory.placeBelow(wrongButton);
		wrongButton.addActionListener(this);*/
	}
	@Override
	protected void setLabelButtonConfig() {
		keyLabel.setText("Q:");
		valueLabel.setText("A:");
		titleBar.setText("Quiz");
		getButton.setText("EXIT");
		postButton.setText("CORRECT");
//		wrongButton.setText("WRONG");
	}
	@Override
	protected void setKeyValueFieldConfig() {
	}
	@Override
	protected void setBtnCmd() {
		getBtnCmd = "EXIT";
		postBtnCmd = "CORRECT";
		wrongBtnCmd = "WRONG";
	}
	@Override
	protected void doPosting(HashMap<String, String> crawlInfo) {
		runtimeQuizDao.DisplayAnswer();/*.entryQuiz(quizDao.getUserID(),
				crawlInfo.get("key"),
				crawlInfo.get("value"),
				crawlInfo.get("tag")
				);*/
	}
	@Override
	protected boolean isPostDone() {
		return false;
	}
	@Override
	protected void updateResponseBar() {
	}
	@Override
	protected void doSubService(String btnCmd) {
	}
}
