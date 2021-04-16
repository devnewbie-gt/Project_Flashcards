package View;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTable;

public abstract class SuperQuizView extends JPanel {
	
	// 퀴즈메뉴 및 수정 메뉴에서 퀴즈를 보여줄 테이블
//	protected JList<E> quizQueue;
	
	// 각 메뉴에서 post 역할을 하는 버튼
	protected JButton post1;
	protected JButton post2;
	
	// 각 메뉴에서 get 역할을 하는 버튼
	protected JButton get;
	
	public abstract void doPost1();
	public abstract void doPost2();
	public abstract void doGet();
	
}
