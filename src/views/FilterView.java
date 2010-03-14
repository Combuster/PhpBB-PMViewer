package views;

import java.util.HashSet;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import models.Message;

public class FilterView {
	private JPanel panel;
	private JTable table;
	private JLabel labelFolder;
	private JLabel labelSubject;
	private JLabel labelFrom;
	private JLabel labelDate;
	private JTextField folder;
	private JTextField from;
	private JTextField date;
	private JButton btnFilter;
	private JButton btnClear;
	private MessagesView messagesView;
	private HashSet<Message> messages;
}
