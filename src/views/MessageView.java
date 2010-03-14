package views;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import models.Message;

public class MessageView {
	private JPanel panel;
	private JLabel labelFrom;
	private JLabel labelDate;
	private JLabel labelMessage;
	private JTextField textFrom;
	private JTextField textDate;
	private JTextArea textMessage;
	private Message message;
}
