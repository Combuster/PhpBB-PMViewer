package main;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * 
 * @author Robert Heim
 */
public class Gui {

	// / the menu for the display
	private JScrollPane scrollPane;
	public JTextArea output;
	private JFrame outputFrame;
	public Boolean isReady = false;
	private JFrame frame;
	private String frameName;

	/**
	 * Initializes, builds and starts the GUI.
	 */
	public Gui(String frameName) {
		this.frameName = frameName;
		createGUI();
		runGUI();
	}

	public void runGUI() {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				frame.setVisible(true);
			}
		});
	}

	/**
	 * creates and shows the GUI
	 */
	private void createGUI() {
		// Debugging output
		outputFrame = new JFrame("Output");
		output = new JTextArea(10, 30);
		output.setEditable(false);
		scrollPane = new JScrollPane(output);
		outputFrame.getContentPane().add(scrollPane, BorderLayout.CENTER);

		outputFrame.setSize(400, 300);
		outputFrame.setVisible(true);

		// Create and set up the window.
		frame = new JFrame(frameName);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setSize(400, 300);
		frame.setLocationRelativeTo(null); // center frame
		isReady = true;
	}

	/**
	 * @return the frame
	 */
	public JFrame getFrame() {
		return frame;
	}

	/**
	 * Creates the main menuBar.
	 * 
	 * @return the menuBar
	 */
	public JMenuBar createMenuBar() {
		JMenuBar menuBar;
		JMenu menu;

		// Create the menu bar.
		menuBar = new JMenuBar();

		// Build main menu.
		menu = new JMenu(Global.lang.getTranslation("BASIC", "PNVIEWER"));
		menu.setMnemonic(KeyEvent.VK_P);
		menuBar.add(menu);

		JMenuItem open = new JMenuItem(Global.lang.getTranslation("BASIC",
				"OPEN"), KeyEvent.VK_O);
		menu.add(open);

		return menuBar;
	}
}