package UI;

import javax.swing.JFrame;

import DAO.QuizDao;
import DAO.RuntimeQuizDao;
import DAO.UserDao;
import View.QuizMenuFrame;

public class SimpleFrame extends JFrame{
	InsertQuizView insertQuizView;
	QuizHideView quizHideView;
	QuizRevealView quizRevealView;
	LoginView loginView;
	EntryUserView entryUserView;
	
	QuizDao quizDao;
	RuntimeQuizDao runtimeQuizDao;
	UserDao userDao;
	
	public SimpleFrame() {
		quizDao = new QuizDao("Dummy");
		runtimeQuizDao = new RuntimeQuizDao();
		userDao = new UserDao();
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(100, 100, 400, 400);
		
		loginView = new LoginView(this, userDao);
		entryUserView = new EntryUserView(this, userDao);
		insertQuizView = new InsertQuizView(this, quizDao);
		quizHideView = new QuizHideView(this, runtimeQuizDao);
		quizRevealView = new QuizRevealView(this, runtimeQuizDao);
		
		loginView.setNextPanel(entryUserView);
		entryUserView.setNextPanel(loginView);
		insertQuizView.setNextPanel(null); //ModQuizMenu;
		quizHideView.setNextPanel(quizRevealView);
		quizRevealView.setNextPanel(null);
		
		this.getContentPane().add(loginView);
//		this.getContentPane().add(entryUserView);
//		this.getContentPane().add(insertQuizView);
//		this.getContentPane().add(quizHideView);
//		this.getContentPane().add(quizRevealView);

		this.setVisible(true);
	}
	public void setQuizMenuFrame(QuizMenuFrame quizMenuFrame) {
		insertQuizView.setNextMenuFrame(quizMenuFrame);
		quizRevealView.setNextMenuFrame(quizMenuFrame);
	}
	public LoginView getLoginView() {
		return loginView;
	}
	public InsertQuizView getInsertQuizView() {
		return insertQuizView;
	}
	public QuizHideView getQuizHideView() {
		return quizHideView;
	}
}
