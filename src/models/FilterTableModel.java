package models;

import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import main.Global;
import main.PMViewer;

/**
 * @author Robert Heim
 * 
 */
@SuppressWarnings("serial")
public class FilterTableModel extends AbstractTableModel {

	private String[] columnNames = {
			PMViewer.getInstance().getLanguage()
					.getTranslation("BASIC", "DATE"),
			PMViewer.getInstance().getLanguage().getTranslation("BASIC",
					"SUBJECT"),
			PMViewer.getInstance().getLanguage().getTranslation("BASIC",
					"SENDER"),
			PMViewer.getInstance().getLanguage().getTranslation("BASIC",
					"PATH") };

	private Vector<Message> messages;

	public FilterTableModel() {
		messages = new Vector<Message>();
	}

	@Override
	public String getColumnName(int col)
	{
		return columnNames[col];
	}
	
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return messages.size();
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		Message m = messages.get(arg0);
		if (m == null)
			return null;

		switch (arg1) {
		case 0:
			SimpleDateFormat formatter = new SimpleDateFormat(Global.PMVIEWER_DATE_FORMAT);
			return formatter.format(m.getDate().getTime());	
		case 1:
			return m.getSubject();
		case 2:
			return m.getSender();
		case 3:
			return m.getFile().getPath();
		default:
			return null;
		}
	}

	/**
	 * Gets all current messages in the table.
	 * @return all current messages in the table 
	 */
	public Vector<Message> getMessages() {
		return messages;
	}

	/**
	 * Gets the message-object from a row.
	 * @param row the row of the message
	 * @return the message-object
	 */
	public Message getMessageAt(int row) {
		return messages.get(row);
	}

	public void update(Vector<Message> messages) {
		this.messages = messages;
	}
	
	 /*
     * JTable uses this method to determine the default renderer/
     * editor for each cell.  If we didn't implement this method,
     * then the last column would contain text ("true"/"false"),
     * rather than a check box.
     */
    @SuppressWarnings("unchecked")
	public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

}
