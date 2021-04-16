package UI;

import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import DAO.RuntimeQuizDao;
import DAO.SuperDao;

public class QuizHideView extends SuperView {
	RuntimeQuizDao runtimeQuizDao;
	
	public QuizHideView(JFrame frame, SuperDao dao) {
		super(frame, dao);
//		runtimeQuizDao = (RuntimeQuizDao)dao.getDao();
	}
	public QuizHideView setRuntimeQuizDao(RuntimeQuizDao runtimeQuizDao) {
		this.runtimeQuizDao = runtimeQuizDao;
		return this;
	}
	@Override
	protected void placeFields() {
		placeFactory.placeNext(keyLabel);
		placeFactory.placeBelow(valueLabel);
	}
	@Override
	protected void placeButtons() {
		placeFactory.placeBelow(getButton);
	}
	@Override
	protected void placeSubFields() {
	}
	@Override
	protected void placeSubButtons() {
	}
//	 runtimeQuizDao의 호출시점은 Constructor가 아닌가? => override된 method라 super에서 열람함.
	@Override
	protected void setLabelButtonConfig() {
		keyLabel.setText("Q:");
		runtimeQuizDao = (RuntimeQuizDao)dao.getDao();
		valueLabel.setText(runtimeQuizDao.getQuiz());
		titleBar.setText("Quiz");
		getButton.setText("ANSWER");
	}
	@Override
	protected void setKeyValueFieldConfig() {
	}
	@Override
	protected void setBtnCmd() {
		getBtnCmd = "ANSWER";
	}
	@Override
	protected void doPosting(HashMap<String, String> crawlInfo) {
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
