package fr.kazanmw.ui.service;

import java.awt.Component;

import javax.swing.JOptionPane;

/**
 * @author Sceok
 *
 */
public class PopUpDisplayingService {

	private PopUpDisplayingService() {
	}

	public static void displayErrorPopUp(Component parentParam, String messageParam, String titleParam) {
		JOptionPane.showMessageDialog(parentParam, messageParam, titleParam, JOptionPane.ERROR_MESSAGE);
	}

	public static void displayInformationPopUp(Component parentParam, String messageParam, String titleParam) {
		JOptionPane.showMessageDialog(parentParam, messageParam, titleParam, JOptionPane.INFORMATION_MESSAGE);
	}
}
