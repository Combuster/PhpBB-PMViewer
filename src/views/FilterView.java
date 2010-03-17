package views;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import main.Global;
import main.PMViewer;
import models.FilterTableModel;
import models.Language;
import models.Message;

/**
 * @author Robert Heim
 * 
 */
public class FilterView {
	private JPanel panel;
	private JTable table;
	private JLabel labelPath;
	private JLabel labelSubject;
	private JLabel labelSender;
	private JLabel labelDateFrom;
	private JLabel labelDateTo;
	private JTextField path;
	private JTextField subject;
	private JTextField sender;
	private JTextField dateFrom;
	private JTextField dateTo;
	private JButton btnFilter;
	private JButton btnClear;
	private MessagesView messagesView;
	private Vector<Message> messages;

	public FilterView(Vector<Message> messages) {
		Language lang = PMViewer.getInstance().getLanguage();

		table = new JTable(new FilterTableModel());
		table.getSelectionModel().addListSelectionListener(
				PMViewer.getInstance().getFilterController());

		labelPath = new JLabel(lang.getTranslation("BASIC", "PATH"));
		labelSubject = new JLabel(lang.getTranslation("BASIC", "SUBJECT"));
		labelSender = new JLabel(lang.getTranslation("BASIC", "SENDER"));
		labelDateFrom = new JLabel(lang.getTranslation("BASIC", "DATE_FROM")
				+ " (" + Global.PMVIEWER_FILTER_INPUT_DATE_FORMAT + ")");
		labelDateTo = new JLabel(lang.getTranslation("BASIC", "DATE_TO") + " ("
				+ Global.PMVIEWER_FILTER_INPUT_DATE_FORMAT + ")");
		path = new JTextField();
		subject = new JTextField();
		sender = new JTextField();
		dateFrom = new JTextField();
		dateTo = new JTextField();
		btnFilter = new JButton(lang.getTranslation("BASIC", "FILTER"));
		btnFilter.setActionCommand("filter");
		btnFilter.addActionListener(PMViewer.getInstance()
				.getFilterController());
		btnClear = new JButton(lang.getTranslation("BASIC", "CLEAR"));
		btnClear.setActionCommand("clear");
		btnClear
				.addActionListener(PMViewer.getInstance().getFilterController());

		setMessages(messages);

		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER; // end row
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

		JPanel filterPanel = new JPanel(new GridBagLayout());

		filterPanel.add(labelPath);
		filterPanel.add(path, gridBagConstraints);
		filterPanel.add(labelSubject);
		filterPanel.add(subject, gridBagConstraints);
		filterPanel.add(labelSender);
		filterPanel.add(sender, gridBagConstraints);
		filterPanel.add(labelDateFrom);
		filterPanel.add(dateFrom, gridBagConstraints);
		filterPanel.add(labelDateTo);
		filterPanel.add(dateTo, gridBagConstraints);
		filterPanel.add(btnFilter);
		filterPanel.add(btnClear, gridBagConstraints);

		JScrollPane tablePanel = new JScrollPane(table);
		table.setFillsViewportHeight(true);

		panel = new JPanel(new GridLayout(2, 0));
		panel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(lang.getTranslation("BASIC",
						"FILTER_VIEW")), BorderFactory.createEmptyBorder(5, 5,
						5, 5)));
		panel.add(tablePanel);
		panel.add(filterPanel);

	}

	public void setMessages(Vector<Message> messages) {
		this.messages = messages;
		updateTable();
	}

	private void updateTable() {
		((FilterTableModel) table.getModel()).update(messages);
		table.updateUI();
	}

	/**
	 * @return the panel
	 */
	public JPanel getPanel() {
		return panel;
	}

	/**
	 * @param messagesView
	 *            the messagesView to set
	 */
	public void setMessagesView(MessagesView messagesView) {
		this.messagesView = messagesView;
	}

	/**
	 * @return the messagesView
	 */
	public MessagesView getMessagesView() {
		return messagesView;
	}

	public void setPathText(String s) {
		path.setText(s);
	}

	public String getPathText() {
		return path.getText();
	}

	public void setSubjectText(String s) {
		subject.setText(s);
	}

	public String getSubjectText() {
		return subject.getText();
	}

	public void setSenderText(String s) {
		sender.setText(s);
	}

	public String getSenderText() {
		return sender.getText();
	}

	public void setDateFromText(String s) {
		dateFrom.setText(s);
	}

	public String getDateFromText() {
		return dateFrom.getText();
	}

	public void setDateToText(String s) {
		dateTo.setText(s);
	}

	public String getDateToText() {
		return dateTo.getText();
	}

	public JTable getTable() {
		return table;
	}
}