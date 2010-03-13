/**
 * Application entry.
 */
package main;

import models.Language;

/**
 * @author Robert Heim
 * 
 */
public class PNViewer {

	private static String applicationName = "PNViewer";
	private static String version = "0.0.1a";
	/** contains the data.xml files */
	private String folder;
	private Language language;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PNViewer PNV = new PNViewer("pns");

		System.out.println();
		System.out.print("*** starting PNViewer " + PNV.getVersion() + " ...  ***");
		System.out.println();

		PNV.setLanguage(new Language("languages", "en"));
		Global.lang = PNV.getLanguage();
		PNV.getLanguage().addLanguageFile("main");

		// Set up the GUI
		Gui gui = new Gui(applicationName + " " + version);
		gui.getFrame().setJMenuBar(gui.createMenuBar());

		gui.output.append("*** PNViewer " + PNV.getVersion() + " ***\n");

	}

	public PNViewer(String folder) {
		setFolder(folder);
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
