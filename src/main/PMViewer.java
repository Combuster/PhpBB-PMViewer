/**
 * Application entry.
 */
package main;

import java.util.HashSet;

import models.Folder;
import models.Language;

/**
 * @author Robert Heim
 * 
 */
public class PMViewer {

	private static String applicationName = "PMViewer";
	private static String version = "0.0.1a";
	/** Singleton design pattern */
	private static PMViewer instance;
	/** folder that contains the data.xml files (or in subfolders) */
	private String folder;
	private Language language;
	private Gui gui;
	/** folders found in the "folder".*/
	private HashSet<Folder> folders;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PMViewer PMV = PMViewer.getInstance();
		PMV.setFolder("pns");
		
		System.out.println();
		System.out.print("*** starting " + applicationName + " "
				+ PMV.getVersion() + " ...  ***");
		System.out.println();

		PMV.setLanguage(new Language("languages", "en"));
		PMV.getLanguage().addLanguageFile("main");

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
}
