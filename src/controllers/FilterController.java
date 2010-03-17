/**
 * 
 */
package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import views.FilterView;

import main.Global;
import main.PMViewer;
import models.FilterTableModel;
import models.Message;

/**
 * @author Robert Heim
 * 
 */
public class FilterController implements ActionListener, ListSelectionListener {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		FilterView fv = PMViewer.getInstance().getGui().getFilterView();
		if (event.getActionCommand() == "clear") {
			fv.setPathText("");
			fv.setSubjectText("");
			fv.setSenderText("");
			fv.setDateFromText("");
			fv.setDateToText("");
			fv.setMessages(PMViewer.getInstance().getAllMessages());
		} else if (event.getActionCommand() == "filter") {

			// parse dates..
			SimpleDateFormat sdf = new SimpleDateFormat(
					Global.PMVIEWER_FILTER_INPUT_DATE_FORMAT);
			Date df = null;
			Date dt = null;
			try {
				df = sdf.parse(fv.getDateFromText());
				dt = sdf.parse(fv.getDateToText());
			} catch (ParseException e) {
				; // ignore the dates
				// e.printStackTrace();
			}

			// filter messages to match the users input
			Vector<Message> msgs = filter(fv.getPathText(),
					fv.getSubjectText(), fv.getSenderText(), df, dt);
			// set/display matching messages
			fv.setMessages(msgs);
		}
		fv.getTable().clearSelection();
	}

	@Override
	public void valueChanged(ListSelectionEvent event) {
		if (event.getValueIsAdjusting()) {
			return;
		}

		// get selected messages from the FilterView table
		JTable t = PMViewer.getInstance().getGui().getFilterView().getTable();
		FilterTableModel tm = (FilterTableModel) t.getModel();

		Vector<Message> msgs = null;

		if (t.getSelectedRowCount() <= 0) {
			msgs = tm.getMessages();
		} else {
			msgs = new Vector<Message>();

			for (int c : t.getSelectedRows()) {
				msgs.add(tm.getMessageAt(c));
			}
		}

		PMViewer.getInstance().getGui().getMessagesView().update(msgs);
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
			if (m.getFile().getPath().toLowerCase().contains(path.toLowerCase())
					&& m.getSubject().toLowerCase().contains(subject.toLowerCase())
					&& m.getSender().toLowerCase().contains(sender.toLowerCase())
					&& (dateFrom == null || dateFrom.before(m.getDate()) || dateFrom.equals(m.getDate())
					&& (dateTo == null || dateTo.after(m.getDate()) || dateTo.equals(m.getDate())))) {
				messages.add(m);
			}
		}

		return messages;
	}
}
