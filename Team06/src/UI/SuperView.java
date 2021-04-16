package UI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import DAO.SuperDao;

public abstract class SuperView extends JPanel implements ActionListener {
	protected JTextField keyField;
	protected JTextField valueField;
	protected JLabel keyLabel;
	protected JLabel valueLabel;
	protected JLabel titleBar;
	protected JLabel responseBar;
	protected JButton postButton;
	protected JButton getButton;
	protected String postBtnCmd;
	protected String getBtnCmd;
	protected JFrame frame;
	protected SuperView nextPanel;
	protected ArrayList<JTextField>viewInfo;
	protected SuperDao dao;
	
	ComponentPlaceFactory placeFactory;
	int cursurX, cursurY;
		
	public SuperView(JFrame frame, SuperDao dao) {
		this.frame = frame;
		this.setLayout(null);
		this.placeFactory = new ComponentPlaceFactory(this);
		this.dao = dao;
		initComponents();
	}
	protected void setNextPanel(SuperView nextPanel) {
		this.nextPanel = nextPanel;
	}
	protected void initComponents() {
		titleBar = new JLabel();
		keyLabel = new JLabel();
		valueLabel = new JLabel();
		keyField = new JTextField();
		valueField = new JTextField();
		postButton = new JButton();
		getButton = new JButton();
		responseBar = new JLabel();
		
		frame.getContentPane().add(BorderLayout.SOUTH, responseBar);
		placeComponents();
		prepareViewInfo();
		prepareButton();
	}
	protected void placeComponents() {
		placeFields();
		placeButtons();
	}
	protected void placeFields() {
		placeFactory.placeBelow(titleBar);
		placeFactory.placeNext(keyLabel);
		placeFactory.placeBelow(keyField);
		placeFactory.placeNext(valueLabel);
		placeFactory.placeBelow(valueField);
		placeSubFields();
	}
	protected abstract void placeSubFields();
	protected void placeButtons() {
		placeFactory.placeNext(postButton);
		placeFactory.placeBelow(getButton);
		placeSubButtons();
	}
	protected abstract void placeSubButtons();
	protected void prepareViewInfo() {
		viewInfo = new ArrayList<>();

		viewInfo.add(keyField);
		viewInfo.add(valueField);
		keyField.setName("key");
		valueField.setName("value");
		
		setLabelButtonConfig();
		setKeyValueFieldConfig();
	}
	protected abstract void setLabelButtonConfig();
	protected abstract void setKeyValueFieldConfig();
	protected void prepareButton() {
		postBtnCmd = "POST";
		getBtnCmd = "GET";
		setBtnCmd();
		postButton.setActionCommand(postBtnCmd);
		getButton.setActionCommand(getBtnCmd);
		getButton.addActionListener(this);
		postButton.addActionListener(this);
	}
	protected abstract void setBtnCmd();
	protected void doPost() {
		doPosting(crawlViewInfo());
		if(!isPostDone()) {
			updateResponseBar();
			return;
		}
		updateResponseBar();
		doPostGet();
	}
	protected void doPostGet() {
		doGet(nextPanel);
	}
	protected abstract void doPosting(HashMap<String, String> crawlInfo);
	protected abstract boolean isPostDone();
	protected HashMap<String, String> crawlViewInfo() {
		HashMap<String, String> result = new HashMap<>();
		for(JTextField info : viewInfo) {
			result.put(info.getName(), info.getText());
		}
		return result;
	}
	protected abstract void updateResponseBar();
	protected void doGet(SuperView nextPanel) {
		frame.remove(this);
		System.out.println(this);
		System.out.println(nextPanel);
		if(nextPanel == null && isNextMenuFrameExist()) {
			frame.setVisible(false);
			nextMenuFrameOn();
		} else {
			System.out.println("test");
			frame.getContentPane().add(BorderLayout.CENTER, nextPanel);
		}
		frame.validate();
		frame.repaint();
	}
	protected boolean isNextMenuFrameExist() {
		return false;
	}
	protected void nextMenuFrameOn() {
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(!(e.getSource() instanceof JButton))	return;
		JButton button = (JButton)e.getSource();
		String btnCmd = getButtonCommand(button);
		if(btnCmd.equals(postBtnCmd)) {
			doPost();
		} else if(btnCmd.equals(getBtnCmd)) {
			doGet(nextPanel);
		}
		doSubService(btnCmd);
	}
	protected abstract void doSubService(String btnCmd);
	protected String getButtonCommand(JButton button) {
		return (String)button.getActionCommand();
	}
}
