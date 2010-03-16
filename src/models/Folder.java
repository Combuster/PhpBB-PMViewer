package models;

import java.util.HashSet;

/**
 * @author Robert Heim
 * 
 */
public class Folder {
	private String path;
	private HashSet<Message> messages;

	public Folder(String path) {
		setPath(path);
		messages = new HashSet<Message>();
	}

	/**
	 * @param path
	 *            the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @return the messages
	 */
	public HashSet<Message> getMessages() {
		return messages;
	}

	/**
	 * @param message
	 *            the message to add
	 */
	public void addMessage(Message message) {
		this.messages.add(message);
	}

	/**
	 * @param message
	 *            the message to remove
	 */
	public void removeMessage(Message message) {
		this.messages.remove(message);
	}

	/**
	 * @param message
	 *            the message to check
	 * @return true if the object contains the message, false otherwise
	 */
	public boolean hasMessage(Message message) {
		return this.messages.contains(message);
	}
	
	public String toString()
	{
		return getPath();
	}
}
