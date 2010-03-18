/**
 * 
 */
package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileFilter;

import views.MainWindowView;

import main.Global;
import main.Gui;
import main.PMViewer;
import models.FilterTableModel;
import models.Message;

/**
 * @author Robert Heim
 * 
 */
public class MainWindowController implements ActionListener,
		ListSelectionListener {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// Menu
		if ("exit".equals(e.getActionCommand())) {
			System.exit(0);
			return;
		}
		if ("open".equals(e.getActionCommand())) {

			JFileChooser fc = new JFileChooser();
			fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			fc.setCurrentDirectory(new File("."));
			fc.setAcceptAllFileFilterUsed(false);
			fc.setFileFilter(new FileFilter() {
				@Override
				public String getDescription() {
					return PMViewer.getInstance().getLanguage().getTranslation(
							"BASIC", "DIRECTORIES_ONLY");
				}

				@Override
				public boolean accept(File f) {
					return f.isDirectory();
				}
			});

			if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {

				PMViewer PMV = PMViewer.getInstance();
				Gui gui = PMV.getGui();

				// clear views
				gui.getMainWindowView().clearMessages();

				// set and read new folder
				PMV.setFolder(fc.getSelectedFile().toString());

				gui.getMainWindowView().setMessages(PMV.getAllMessages());
			}
			return;
		}
		if ("about".equals(e.getActionCommand())) {
			JOptionPane.showMessageDialog(PMViewer.getInstance().getGui()
					.getMainWindowView(), PMViewer.getInstance().getInfo());
			return;
		}

		// Buttons

		MainWindowView mw = PMViewer.getInstance().getGui().getMainWindowView();
		if (e.getActionCommand() == "clear") {
			mw.setPathText("");
			mw.setSubjectText("");
			mw.setSenderText("");
			mw.setDateFromText("");
			mw.setDateToText("");
			mw.setMessages(PMViewer.getInstance().getAllMessages());
		} else if (e.getActionCommand() == "filter") {

			// parse dates..
			SimpleDateFormat sdf = new SimpleDateFormat(
					Global.PMVIEWER_FILTER_INPUT_DATE_FORMAT);
			Date df = null;
			try {
				df = sdf.parse(mw.getDateFromText());
			} catch (ParseException e1) {
				; // ignore the date
				// e.printStackTrace();
			}
			Date dt = null;
			try {
				dt = sdf.parse(mw.getDateToText());
			} catch (ParseException e1) {
				; // ignore the date
				// e.printStackTrace();
			}

			// filter messages to match the users input
			Vector<Message> msgs = filter(mw.getPathText(),
					mw.getSubjectText(), mw.getSenderText(), df, dt);
			// set/display matching messages
			mw.setMessages(msgs);
		}
		mw.getTable().clearSelection();
	}

	@Override
	public void valueChanged(ListSelectionEvent event) {
		if (event.getValueIsAdjusting()) {
			return;
		}

		// get selected messages from the tableOverview
		JTable t = PMViewer.getInstance().getGui().getMainWindowView()
				.getTable();
		FilterTableModel tm = (FilterTableModel) t.getModel();

		Vector<Message> msgs = null;

		msgs = new Vector<Message>();

		for (int c : t.getSelectedRows()) {
			msgs.add(tm.getMessageAt(c));
		}

		PMViewer.getInstance().getGui().getMainWindowView().updateMessagesView(
				msgs);
	}

	/**
	 * filters the messages and returns the matching ones.
	 * 
	 * @param path
	 *            path of the file that should contain the messages
	 * @param subject
	 *            subject that the message should match
	 * @param sender
	 *            sender who send the message
	 * @param dateFrom
	 *            date after that the message was send
	 * @param dateTo
	 *            before that date the message was send
	 * @return vector of matching messages
	 */
	public Vector<Message> filter(String path, String subject, String sender,
			Date dateFrom, Date dateTo) {

		Vector<Message> messages = new Vector<Message>();

		for (Message m : PMViewer.getInstance().getAllMessages()) {
			if (m.getFile().getPath().toLowerCase()
					.contains(path.toLowerCase())
					&& m.getSubject().toLowerCase().contains(
							subject.toLowerCase())
					&& m.getSender().toLowerCase().contains(
							sender.toLowerCase())
					&& (dateFrom == null || dateFrom.before(m.getDate()) || dateFrom
							.equals(m.getDate()))
					&& (dateTo == null || dateTo.after(m.getDate()) || dateTo
							.equals(m.getDate()))) {
				messages.add(m);
			}
		}

		return messages;
	}
}
