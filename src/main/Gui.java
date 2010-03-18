package main;

import javax.swing.UIManager;

import views.MainWindowView;

/**
 * 
 * @author Robert Heim
 */
public class Gui {

	private MainWindowView mainWindowView;

	/**
	 * Initializes, builds and starts the GUI.
	 */
	public Gui(String frameName) {
		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {

		}

		// create main Window
		setMainWindowView(new MainWindowView(frameName, PMViewer.getInstance()
				.getAllMessages()));
		runGui();
	}

	/**
	 * runs the GUI and sets the frame visible.
	 */
	public void runGui() {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				getMainWindowView().setVisible(true);
			}
		});
	}

	/**
	 * @param mainWindowView
	 *            the mainWindowView to set
	 */
	public void setMainWindowView(MainWindowView mainWindowView) {
		this.mainWindowView = mainWindowView;
	}

	/**
	 * @return the mainWindowView
	 */
	public MainWindowView getMainWindowView() {
		return mainWindowView;
	}

}