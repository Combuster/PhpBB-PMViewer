package views;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.HashSet;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import main.PMViewer;
import models.Language;
import models.Message;

/**
 * @author Robert Heim
 * 
 */
public class MessagesView {
	private JPanel panel;
	private JPanel msgViewsPanel;
	private HashSet<MessageView> messageViews;

	public MessagesView() {
		messageViews = new HashSet<MessageView>();
		Language lang = PMViewer.getInstance().getLanguage();
		
		//panel = new JPanel();
		panel = new JPanel(new GridLayout(1, 0));
		panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory
				.createTitledBorder(lang.getTranslation("BASIC", "MESSAGES_VIEW")),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		
		msgViewsPanel = new JPanel(new GridBagLayout());
		//JScrollPane msgViewsScrollPane = new JScrollPane(msgViewsPanel);
		JScrollPane msgViewsScrollPane = new JScrollPane(msgViewsPanel);

		panel.add(msgViewsScrollPane);
	}

	/**
	 * @return the panel
	 */
	public JPanel getPanel() {
		return panel;
	}

	/**
	 * @param messageViews the messageViews to set
	 */
	public void update(Vector<Message> messages) {
		removeAllMessageViews();
		
		for (Message m : messages)
		{
			MessageView mv = new MessageView(m);
			addMessageView(mv);
		}
	}

	/**
	 * adds a messageView
	 * @param messageView the messageView to add
	 */
	public void addMessageView(MessageView messageView)
	{
		GridBagConstraints c1 = new GridBagConstraints();
		c1.gridwidth = GridBagConstraints.REMAINDER; // end row
		c1.weightx = 0.5;
		c1.fill = GridBagConstraints.HORIZONTAL;
		c1.anchor = GridBagConstraints.FIRST_LINE_START;
		
		messageViews.add(messageView);
		msgViewsPanel.add(messageView.getPanel(), c1);
	}
	
	/**
	 * removes all messageViews
	 */
	public void removeAllMessageViews()
	{
		messageViews.clear();
		msgViewsPanel.removeAll();
		msgViewsPanel.updateUI();
	}
	
	/**
	 * @return the messageViews
	 */
	public HashSet<MessageView> getMessageViews() {
		return messageViews;
	}
}
