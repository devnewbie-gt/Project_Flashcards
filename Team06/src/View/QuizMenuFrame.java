package View;

import javax.swing.JFrame;

import UI.SimpleFrame;

public class QuizMenuFrame extends JFrame {
	JFrame sf;
	QuizMenu qm;
	public QuizMenuFrame(JFrame sf, String id) { 
		this.sf = sf;
		SimpleFrame simpleFrame = (SimpleFrame)sf;
		simpleFrame.setQuizMenuFrame(this);
		
		setTitle("환영합니다. " + id + "님");
		qm = new QuizMenu(this, sf, id)
				.setLoginView(simpleFrame.getLoginView())
				.setInsertQuizView(simpleFrame.getInsertQuizView())
				.setQuizHideView(simpleFrame.getQuizHideView());
		add(qm);
		setBounds(100, 100, 640, 480);
		setVisible(true);
	}
}
