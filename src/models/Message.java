package models;

import java.io.File;
import java.util.Date;

public class Message {
	private File file;
	private String sender;
	private String subject;
	private Date date;
	private String message;

	/**
	 * Creates a new message.
	 * 
	 * @param folder
	 *            the folder that contains the message
	 * @param sender
	 *            author of the message
	 * @param subject
	 *            the subject of the message
	 * @param date
	 *            the date when the message was send
	 * @param message
	 *            the text of the message
	 */
	public Message(File file, String sender, String subject, Date date,
			String message) {
		setFile(file);
		setSender(sender);
		setSubject(subject);
		setDate(date);
		setMessage(message);
	}

	/**
	 * @param file
	 *            the file to set
	 */
	public void setFile(File file) {
		this.file = file;
	}

	/**
	 * @return the file
	 */
	public File getFile() {
		return file;
	}

	/**
	 * @param sender
	 *            the sender to set
	 */
	public void setSender(String sender) {
		this.sender = sender;
	}

	/**
	 * @return the sender
	 */
	public String getSender() {
		return sender;
	}

	/**
	 * @param subject
	 *            the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	
	public String toString()
	{
		return "Sender: "+sender+"\n"+"Subject: "+subject+"\n"+"Message: "+message+"\n";
	}
}
