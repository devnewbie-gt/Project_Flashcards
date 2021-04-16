package UI;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import DAO.QuizDao;
import DAO.SuperDao;
import View.QuizMenu;

public class InsertQuizView extends SuperView {
	JFrame nextMenuFrame;
	QuizDao quizDao;
	JLabel tagLabel;
	JTextField tagField;
	
	public InsertQuizView(JFrame frame, SuperDao dao) {
		super(frame, dao);
//		quizDao = (QuizDao)dao.getDao();
	}
	/*Smelly 3 Methods Below for getMenuFrame*/
	protected void setNextMenuFrame(JFrame nextMenuFrame) {
		this.nextMenuFrame = nextMenuFrame;
	}
	@Override
	protected boolean isNextMenuFrameExist() {
		return (nextMenuFrame != null )? true : false;
	}
	@Override
	protected void nextMenuFrameOn() {
		nextMenuFrame.setVisible(true);
	}
	public InsertQuizView setQuizDao(QuizDao quizDao) {
		this.quizDao = quizDao;
		return this;
	}
	@Override
	protected void placeSubFields() {
		tagLabel = new JLabel();
		tagField = new JTextField();
		placeFactory.placeNext(tagLabel);
		placeFactory.placeBelow(tagField);
	}
	@Override
	protected void placeSubButtons() {
	}
	@Override
	protected void setLabelButtonConfig() {
		keyLabel.setText("Q:");
		valueLabel.setText("A:");
		tagLabel.setText("TAG:");
		titleBar.setText("Insert Quiz");
		getButton.setText("CANCEL");
		postButton.setText("CONFIRM");
	}
	@Override
	protected void setKeyValueFieldConfig() {
		viewInfo.add(tagField);
		tagField.setName("tag");
	}
	@Override
	protected void setBtnCmd() {
		getBtnCmd = "CANCEL";
		postBtnCmd = "CONFIRM";
	}
	@Override
	protected void doPosting(HashMap<String, String> crawlInfo) {
		quizDao.entryQuiz(quizDao.getUserID(),
									crawlInfo.get("key"),
									crawlInfo.get("value"),
									crawlInfo.get("tag")
									);
//		quizDao.displayList();
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
		for(JTextField info: viewInfo) {
			info.setText("");
		}
		validate();
		repaint();
	}
	@Override
	protected void doSubService(String btnCmd) {
	}
}
