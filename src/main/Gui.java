package main;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import views.FilterView;
import views.MainMenuView;
import views.MessagesView;

/**
 * 
 * @author Robert Heim
 */
public class Gui {

	// / the menu for the display
	private JScrollPane scrollPane;
	public JTextArea output;
	private JFrame outputFrame;
	private JFrame frame;
	private String frameName;
	private MessagesView messagesView;
	private FilterView filterView;
	private MainMenuView mainMenuView;

	/**
	 * Initializes, builds and starts the GUI.
	 */
	public Gui(String frameName) {
		this.frameName = frameName;
		setFilterView(new FilterView(PMViewer.getInstance().getAllMessages()));
		setMessagesView(new MessagesView());
		setMainMenuView(new MainMenuView());
		filterView.setMessagesView(messagesView);
		messagesView.update(PMViewer.getInstance().getAllMessages());

		createGui();
		runGui();
	}

	/**
	 * runs the GUI and sets the frame visible.
	 */
	public void runGui() {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				frame.setVisible(true);
			}
		});
	}

	/**
	 * creates and shows the GUI
	 */
	private void createGui() {
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

		frame.setSize(800, 700);
		frame.setLocationRelativeTo(null); // center frame
		frame.setContentPane(new JPanel(new GridLayout(2, 0)));

		frame.getContentPane().add(filterView.getPanel());
		frame.getContentPane().add(messagesView.getPanel());

	}

	/**
	 * @return the frame
	 */
	public JFrame getFrame() {
		return frame;
	}

	/**
	 * @param messagesView
	 *            the messagesView to set
	 */
	public void setMessagesView(MessagesView messagesView) {
		this.messagesView = messagesView;
	}

	/**
	 * @return the messagesView
	 */
	public MessagesView getMessagesView() {
		return messagesView;
	}

	/**
	 * @param filterView
	 *            the filterView to set
	 */
	public void setFilterView(FilterView filterView) {
		this.filterView = filterView;
	}

	/**
	 * @return the filterView
	 */
	public FilterView getFilterView() {
		return filterView;
	}

	/**
	 * @param mainMenuView
	 *            the mainMenuView to set
	 */
	public void setMainMenuView(MainMenuView mainMenuView) {
		this.mainMenuView = mainMenuView;
	}

	/**
	 * @return the mainMenuView
	 */
	public MainMenuView getMainMenuView() {
		return mainMenuView;
	}
}