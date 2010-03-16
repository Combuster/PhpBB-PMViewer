/**
 * Application entry.
 */
package main;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Vector;

import controllers.FilterController;

import models.Folder;
import models.Language;
import models.Message;
import utilities.xml.XMLDocument;
import utilities.xml.XMLNode;

/**
 * @author Robert Heim
 * 
 */
public class PMViewer {

	private static String applicationName = "PMViewer";
	private static String version = "0.0.8b";
	/** Singleton design pattern */
	private static PMViewer instance;
	/** folder that contains the data.xml files (or in subfolders) */
	private String folder;
	private Language language;
	private Gui gui;
	/** folders found in the "folder". */
	private HashSet<Folder> folders;
	/** Controller for the filter */
	private FilterController filterController;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PMViewer PMV = PMViewer.getInstance();

		System.out.println();
		System.out.print("*** starting " + applicationName + " "
				+ PMV.getVersion() + " ...  ***");
		System.out.println();

		PMV.setLanguage(new Language("languages", "en"));
		PMV.getLanguage().addLanguageFile("main");

		PMV.setFolder("data/pns");
		// get folders and messages
		PMV.readFolders();

		PMV.setFilterController(new FilterController());
		
		// Set up the GUI
		PMV.gui = new Gui(applicationName + " " + version);
		PMV.gui.getFrame().setJMenuBar(PMV.gui.createMenuBar());

		PMV.gui.output.append("*** " + applicationName + " " + PMV.getVersion()
				+ " ***\n");

	}

	/**
	 * Singleton design-pattern uses a private constructor called exactly one
	 * time in the getInstance()
	 */
	private PMViewer() {
		this.folders = new HashSet<Folder>();
	}

	/**
	 * Singleton design-pattern: get the one-and-only instance of the PMViewer
	 * object. If there is none than create it.
	 */
	public static PMViewer getInstance() {
		if (instance == null) {
			instance = new PMViewer();
		}
		return instance;
	}

	public String getVersion() {
		return version;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}

	public String getFolder() {
		return folder;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public HashSet<Folder> getFolders() {
		return folders;
	}

	public void addFolder(Folder folder) {
		this.folders.add(folder);
	}

	public void removeFolder(Folder folder) {
		this.folders.remove(folder);
	}

	public boolean hasFolder(Folder folder) {
		return folders.contains(folder);
	}

	/**
	 * @param gui
	 *            the guit to set
	 */
	public void setGui(Gui gui) {
		this.gui = gui;
	}

	/**
	 * @return the gui
	 */
	public Gui getGui() {
		return gui;
	}

	/**
	 * @param filterController the filterController to set
	 */
	public void setFilterController(FilterController filterController) {
		this.filterController = filterController;
	}

	/**
	 * @return the filterController
	 */
	public FilterController getFilterController() {
		return filterController;
	}

	/**
	 * gets all messages.
	 * 
	 * @return vector containing all messages
	 */
	public Vector<Message> getAllMessages() {
		Vector<Message> messages = new Vector<Message>();
		for (Folder f : folders) {
			messages.addAll(f.getMessages());
		}
		return messages;
	}

	/**
	 * reads the specified folder and adds all subfolders and messages to the
	 * objects
	 */
	private void readFolders() {
		if (folder == null || folder == "")
			return;
		folders = new HashSet<Folder>();
		readFolder(folder);
	}

	/**
	 * Recursively reads the folder specified in path, adds all subfolders to
	 * the PMViewer, reads all mesages from the files found in any of the
	 * folders and adds the generated message-objects to the corresponding
	 * folder.
	 * 
	 * @param path
	 *            the path of the folder to read
	 */
	private void readFolder(String path) {

		// add folder to the PMViewer
		Folder currentFolder = new Folder(path);
		addFolder(currentFolder);

		// loop over the files and subfolders
		File dir = new File(path);
		File[] files = dir.listFiles();
		if (files != null) {
			for (File f : files) {
				// is f a file and does the extension fit?
				if (f.isFile() && f.getName().endsWith(".xml")) {
					// get messages from the file and add them to the Folder
					readFile(f, currentFolder);
				} else
				// subfolder?
				if (f.isDirectory()
						&& !(f.getName().equals(".") || f.getName()
								.equals(".."))) {
					readFolder(path + "/" + f.getName());
				}
			}
		}
	}

	/**
	 * Reads the file and adds all messages to the folder.
	 * 
	 * @param file
	 *            the file to read
	 * @param folder
	 *            the folder to add the messages to
	 */
	public void readFile(File file, Folder folder) {
		// TODO create MessagesFile between Folders and Messages
		XMLDocument doc = new XMLDocument(file);
		XMLNode phpBB = doc.getRootElement();
		// read messages
		Vector<XMLNode> pms = phpBB.getChildren("privmsg");
		for (XMLNode p : pms) {
			String sender = p.getChildText("sender");
			String subject = p.getChildText("subject");
			// TODO read Date of the message from file
			SimpleDateFormat sdf = new SimpleDateFormat(Global.PHPBB_DATE_FORMAT);
			Date date = null;
			try {
				date = sdf.parse(p.getChildText("date"));
			} catch (ParseException e) {
				date = new Date(0);
			}

			String message = p.getChildText("message");

			Message m = new Message(file, sender, subject, date, message);
			folder.addMessage(m);
		}
	}
}
