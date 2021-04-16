package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import DAO.QuizDao;
import DAO.RuntimeQuizDao;
import DTO.runtimeQuiz;
import UI.InsertQuizView;
import UI.LoginView;
import UI.QuizHideView;
import UI.SimpleFrame;

public class QuizMenu extends SuperQuizView implements ActionListener {
	
	private QuizMenuFrame qf;
	private RuntimeQuizDao rq;
	private JList<runtimeQuiz> list;
	private LoginView loginView;
	private QuizHideView quizHideView;
	private InsertQuizView insertQuizView;
	QuizDao qd;
	private String id;
	private JFrame sf;
	
	DefaultListModel<runtimeQuiz> rqModel;
	
	public QuizMenu(QuizMenuFrame qf, JFrame sf, String id) {
		this.qf = qf;
		this.id = id;
		this.sf = sf;
		
		rq = new RuntimeQuizDao();
		qd = new QuizDao(id, rq);
		
		setLayout(null);
		// 제목을 표시하는 JLabel입니다..
		JLabel title = new JLabel("암기 플래시카드");
		title.setBounds(250, 20, 300, 50);
		add(title);
		
		// Quiz풀이 창으로 넘어가는 버튼입니다..
		get = new JButton("퀴즈 풀기");
		get.setBounds(450, 100, 130, 30);
		get.addActionListener(this);
		add(get);
		
		// runtimeQuizList를 채우는 버튼입니다..
		post1 = new JButton();
		post1.setText("퀴즈 불러오기");
		post1.setBounds(450, 170, 130, 30);
		post1.addActionListener(this);
		add(post1);
		
		// Quiz수정 패널로 넘어가는 버튼입니다..
		JButton toModQuiz = new JButton("퀴즈 추가/수정");
		toModQuiz.setBounds(450, 240, 130, 30);
		toModQuiz.addActionListener(this);
		add(toModQuiz);
		
		// 로그아웃 버튼입니다..
		post2 = new JButton("로그아웃");
		post2.setBounds(450, 310, 130, 30);
		post2.addActionListener(this);
		add(post2);
		
		// 퀴즈 리스트를 표시하는 표입니다..
		rqModel = new DefaultListModel<runtimeQuiz>();
		for (int i = 0; i < rq.runtimeQuizList.size(); i++) {
			rqModel.add(i, rq.runtimeQuizList.get(i));
		}
		
		list = new JList<runtimeQuiz>(rqModel);
		list.setBounds(50, 70, 300, 350);
		add(list);
//		System.out.println(list);
		
		
		setBounds(0, 0, 640, 480);
		qf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public QuizMenu setLoginView(LoginView loginView) {
		this.loginView = loginView;
		return this;
	}
	public QuizMenu setQuizHideView(QuizHideView quizHideView) {
		this.quizHideView = quizHideView;
		return this;
	}
	public QuizMenu setInsertQuizView(InsertQuizView insertQuizView) {
		this.insertQuizView = insertQuizView;
		return this;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton)e.getSource();
		String str = btn.getLabel();
		if(e.getSource() == post1) {
			doPost1();
		}
		if(e.getSource() == post2) {
			doPost2();
		}
		if(e.getSource() == get) {
			doGet();
		}
		if(str.equals("퀴즈 추가/수정")) {
			qf.getContentPane().removeAll();
			qf.getContentPane().add(new ModQuizMenu(qf, sf, this, qd, id).setInsertQuizView(insertQuizView));
			qf.revalidate();
			qf.repaint();
		}
	}
	@Override
	public void doPost1() {
		if(rqModel.size() != rq.runtimeQuizList.size()) {
			rqModel.removeAllElements();
			for (int i = 0; i < rq.runtimeQuizList.size(); i++) {
				rqModel.add(i, rq.runtimeQuizList.get(i));
			}
			list.setModel(rqModel);
		}else {
		
			if(!rq.isEmpty()) {
				JOptionPane.showMessageDialog(this, "이미 문제가 존재합니다!");
				return;
			}
			if(rq.isEmpty() && qd.modQuizList.size() == 0) {
				JOptionPane.showMessageDialog(this, "현재 " + id + "님의 퀴즈가 존재하지 않습니다.");
				return;
			}
			qd.setRuntimeQuiz();
			DefaultListModel<runtimeQuiz> rqModel = new DefaultListModel<runtimeQuiz>();
			for (int i = 0; i < rq.runtimeQuizList.size(); i++) {
				rqModel.add(i, rq.runtimeQuizList.get(i));
			}
			list.setModel(rqModel);
		}
		
		JOptionPane.showMessageDialog(this, "퀴즈를 다시 불러왔습니다.");
	}
	@Override
	public void doPost2() {
		sf.add(loginView);
		sf.setVisible(true);
		qf.dispose();
	}
	@Override
	public void doGet() {
		sf = (SimpleFrame)sf;
		sf.getContentPane().removeAll();
		sf.getContentPane().add(quizHideView.setRuntimeQuizDao(rq));
		sf.revalidate();
		sf.repaint();
		sf.setVisible(true);
		qf.setVisible(false);
	}
}
