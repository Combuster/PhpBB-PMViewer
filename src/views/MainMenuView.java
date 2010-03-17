/**
 * This file implements the main menu and the delegation of its actions to a
 * controller.
 */

package views;

import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import main.PMViewer;
import models.Language;
import controllers.MainMenuController;

/**
 * This file implements the main menu and the delegation of its actions to a
 * controller.
 * 
 * @author Robert Heim
 */
public class MainMenuView {

	private MainMenuController controller;

	public MainMenuView() {
		controller = PMViewer.getInstance().getMainMenuController();
	}

	
	/**
	 * Creates the main menuBar.
	 * 
	 * @return the menuBar
	 */
	public JMenuBar createMenuBar() {
		Language lang = PMViewer.getInstance().getLanguage();
		JMenuBar menuBar;
		JMenu menu;
		
		// Create the menu bar.
		menuBar = new JMenuBar();

		// Build main menu.
		menu = new JMenu(lang.getTranslation("BASIC", "PMVIEWER"));
		menu.setMnemonic(KeyEvent.VK_P);
		menuBar.add(menu);

		JMenuItem open = new JMenuItem(lang.getTranslation("BASIC", "OPEN_FOLDER"),
				KeyEvent.VK_X);
		open.addActionListener(controller);
		open.setActionCommand("open");
		menu.add(open);
		
		JMenuItem exit = new JMenuItem(lang.getTranslation("BASIC", "EXIT"),
				KeyEvent.VK_X);
		exit.addActionListener(controller);
		exit.setActionCommand("exit");
		menu.add(exit);

		// Build Info menu in the menu bar.
		menu = new JMenu("?");
		menu.setMnemonic(KeyEvent.VK_A);
		menuBar.add(menu);

		JMenuItem info = new JMenuItem(lang.getTranslation("BASIC", "INFO"), KeyEvent.VK_I);
		info.addActionListener(controller);
		info.setActionCommand("info");
		menu.add(info);

		return menuBar;
	}

}