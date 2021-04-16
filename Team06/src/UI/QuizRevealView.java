package UI;

import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import DAO.RuntimeQuizDao;
import DAO.SuperDao;

public class QuizRevealView extends SuperView {
	JFrame nextMenuFrame;
	RuntimeQuizDao runtimeQuizDao;
	JLabel quizLabel;
	JLabel answerLabel;
	JButton wrongButton;
	String wrongBtnCmd;

	public QuizRevealView(JFrame frame, SuperDao superDao) {
		super(frame, superDao);
		runtimeQuizDao = (RuntimeQuizDao)superDao.getDao();
	}
	/*Smelly 3 Methods Below for getMenuFrame*/
	protected void setNextMenuFrame(JFrame nextMenuFrame) {
		this.nextMenuFrame = nextMenuFrame;
	}
	@Override
	protected boolean isNextMenuFrameExist() {
		System.out.println("dip");
		nextMenuFrame.setVisible(false);
		return (nextMenuFrame != null )? true : false;
	}
	@Override
	protected void nextMenuFrameOn() {
		nextMenuFrame.setVisible(true);
	}
	
	@Override
	protected void placeFields() {
		quizLabel = new JLabel();
		answerLabel = new JLabel();

		placeFactory.placeNext(keyLabel);
		placeFactory.placeBelow(quizLabel);
		placeFactory.placeNext(valueLabel);
		placeFactory.placeBelow(answerLabel);
		placeSubFields();
	}
	@Override
	protected void placeSubFields() {
	}
	@Override
	protected void placeSubButtons() {
		runtimeQuizDao = (RuntimeQuizDao)dao.getDao();
		getNextLabel();
		
		wrongButton = new JButton();
		placeFactory.placeBelow(wrongButton);
		wrongButton.addActionListener(this);
	}
	private void getNextLabel() {
		quizLabel.setText(runtimeQuizDao.getQuiz());
		answerLabel.setText(runtimeQuizDao.getAnswer());
	}
	@Override
	protected void setLabelButtonConfig() {
		titleBar.setText("Quiz");
		keyLabel.setText("Q:");
		valueLabel.setText("A:");
		getButton.setText("EXIT");
		postButton.setText("CORRECT");
		wrongButton.setText("WRONG");
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
		runtimeQuizDao.Correct();
	}
	@Override
	protected boolean isPostDone() {
		return true;
	}
	@Override
	protected void updateResponseBar() {
	}
	@Override
	protected void doPostGet() {
		getNextLabel();
		validate();
		repaint();
	}
	@Override
	protected void doSubService(String btnCmd) {
		if(btnCmd.equals(wrongBtnCmd)) {
			doWrongPost();
		}
	}
	private void doWrongPost() {
		runtimeQuizDao.inCorrect();
		doPostGet();
	}
}
