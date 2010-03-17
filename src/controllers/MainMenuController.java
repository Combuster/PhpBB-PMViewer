/**
 * Controller for the menu actions.
 */

package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import main.Gui;
import main.PMViewer;
import models.Message;
import views.FilterView;
import views.MessagesView;

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
		if ("open".equals(e.getActionCommand())) {

			JFileChooser fc = new JFileChooser();
			fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			fc.setCurrentDirectory(new File("."));
			fc.setAcceptAllFileFilterUsed(false);
			fc.setFileFilter(new FileFilter() {
				@Override
				public String getDescription() {
					return PMViewer.getInstance().getLanguage().getTranslation(
							"BASIC", "DIRECTORIES_ONLY");
				}

				@Override
				public boolean accept(File f) {
					return f.isDirectory();
				}
			});

			if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				System.out.println("ok");
				PMViewer PMV = PMViewer.getInstance();
				Gui gui = PMV.getGui();

				// remove FilterView and MessagesView from gui
				gui.getFrame().getContentPane().removeAll();

				// update views
				gui.setFilterView(new FilterView(new Vector<Message>()));
				gui.setMessagesView(new MessagesView());
				gui.getFilterView().setMessagesView(gui.getMessagesView());
				gui.getMessagesView().update(new Vector<Message>());

				// set new FilterView and MessagesView to gui
				gui.getFrame().getContentPane().add(
						gui.getFilterView().getPanel());
				gui.getFrame().getContentPane().add(
						gui.getMessagesView().getPanel());

				// set and read new folder
				PMV.setFolder(fc.getSelectedFile().toString());

				gui.getFilterView().setMessages(PMV.getAllMessages());
				gui.getMessagesView().update(PMV.getAllMessages());

				gui.getFrame().validate();
			}
		}
		if ("info".equals(e.getActionCommand())) {
			JOptionPane.showMessageDialog(PMViewer.getInstance().getGui()
					.getFrame(), PMViewer.getInstance().getInfo());
			return;
		}
	}
}
