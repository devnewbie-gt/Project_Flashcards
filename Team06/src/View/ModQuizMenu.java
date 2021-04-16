package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import DAO.QuizDao;
import DTO.modifyQuiz;
import UI.InsertQuizView;
import UI.SimpleFrame;

public class ModQuizMenu extends SuperQuizView implements ActionListener {
	
	private QuizMenuFrame qf;
	private QuizMenu qm;
	private QuizDao qd;
	private String id;
	private SimpleFrame sf;
	private InsertQuizView insertQuizView;
	
	JList<modifyQuiz> list;
	DefaultListModel<modifyQuiz> qModel;
	
	public ModQuizMenu(QuizMenuFrame qf, JFrame sf, QuizMenu qm, QuizDao qd, String id) {
		this.qf = qf;
		this.qm = qm;
		this.qd = qd;
		this.id = id;
		this.sf = (SimpleFrame)sf;
		
		setLayout(null);
		JLabel title = new JLabel("수정 메뉴");
		title.setBounds(250, 20, 300, 50);
		add(title);
		post1 = new JButton("퀴즈 추가");
		post1.setBounds(450, 100, 130, 30);
		post1.addActionListener(this);
		add(post1);
		
		post2 = new JButton("새로고침");
		post2.setBounds(450, 170, 130, 30);
		post2.addActionListener(this);
		add(post2);
		
		JButton deleteQuiz = new JButton("퀴즈 삭제");
		deleteQuiz.setBounds(450, 240, 130, 30);
		deleteQuiz.addActionListener(this);
		add(deleteQuiz);
		
		JButton commit = new JButton("적용");
		commit.setBounds(450, 310, 130, 30);
		commit.addActionListener(this);
		add(commit);
		
		get = new JButton("이전으로");
		get.setBounds(450, 380, 130, 30);
		get.addActionListener(this);
		add(get);
		
		
		qModel = new DefaultListModel<modifyQuiz>();
		for (int i = 0; i < qd.modQuizList.size(); i++) {
			qModel.add(i, qd.modQuizList.get(i));
		}
		
		list = new JList<modifyQuiz>(qModel);
		list.setBounds(50, 70, 300, 350);
		add(list);
		
		setBounds(0, 0, 640, 480);
		qf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public ModQuizMenu setInsertQuizView(InsertQuizView insertQuizView) {
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
		if(str.equals("퀴즈 삭제")){
			if(qd.modQuizList.size() == 0) {
				JOptionPane.showMessageDialog(this, "현재 등록된 퀴즈가 존재하지 않습니다.");
				return;
			}
			if(list.getSelectedValue() == null) {
				return;
			}
			qd.deleteQuiz(id, list.getSelectedValue().getQuizTag());
			
			qModel = new DefaultListModel<modifyQuiz>();
			for (int i = 0; i < qd.modQuizList.size(); i++) {
				qModel.add(i, qd.modQuizList.get(i));
			}
			list.setModel(qModel);
			JOptionPane.showMessageDialog(this, "선택한 퀴즈의 작업 상태가 '삭제'로 변경되었습니다.");
		}
		if(str.equals("적용")) {
			String chk = qd.commitQuiz();
			if(chk != null) {
				JOptionPane.showMessageDialog(this, chk);
			}
			doGet();
		}
		if(e.getSource() == get) {
			doGet();
		}
	}

	@Override
	public void doPost1() {
		sf.getContentPane().removeAll();
		sf.getContentPane().add(insertQuizView.setQuizDao(qd));
		sf.setVisible(true);
		sf.revalidate();
		sf.repaint();
		
		qf.setVisible(false);
	}
	
	@Override
	public void doPost2() {
		qModel.removeAllElements();
		qModel = new DefaultListModel<modifyQuiz>();
		for (int i = 0; i < qd.modQuizList.size(); i++) {
			qModel.add(i, qd.modQuizList.get(i));
		}
		list.setModel(qModel);
	}
	
	@Override
	public void doGet() {
		qd.modQuizList.clear();
		qd.getMemberQuiz();
		
		
		qf.getContentPane().removeAll();
//		System.out.println(qf);
		qf.getContentPane().add(qm);
		qf.revalidate();
		qf.repaint();
	}

}
