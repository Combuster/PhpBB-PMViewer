/**
 * Controller for the menu actions.
 */

package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import main.PMViewer;

/**
 * Controller for the menu actions.
 * 
 * @author Robert Heim
 */
public class MainMenuController implements ActionListener {

	/**
	 * creates a new Controller instance for the mainMenu.
	 */
	public MainMenuController() {
	}

	/**
	 * Delegates actions to the specific controller-functions.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if ("exit".equals(e.getActionCommand())) {
			System.exit(0);
			return;
		}
		if ("info".equals(e.getActionCommand())) {
			JOptionPane.showMessageDialog(PMViewer.getInstance().getGui()
					.getFrame(), PMViewer.getInstance().getInfo());
			return;
		}
	}
}
