package fr.kazanmw.ui;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class GlobalFrame extends JFrame{
	/**
	 *
	 */
	private static final long serialVersionUID = -3957819353328780072L;
	private final JTextField txtChooseTheFolder;
	public GlobalFrame() {
		this.setTitle("CloneFinder");
		this.setResizable(false);
		this.getContentPane().setLayout(null);

		this.txtChooseTheFolder = new JTextField();
		this.txtChooseTheFolder.setEnabled(true);
		this.txtChooseTheFolder.setEditable(true);
		this.txtChooseTheFolder.setText("Type your folder here...");
		this.txtChooseTheFolder.setBounds(68, 42, 173, 26);
		this.getContentPane().add(this.txtChooseTheFolder);
		this.txtChooseTheFolder.setColumns(10);

		final JButton btnNewButton = new JButton("choose folder");
		btnNewButton.setToolTipText("click here to select a folder through Windows explorer");
		btnNewButton.setBounds(251, 44, 105, 23);
		this.getContentPane().add(btnNewButton);

		final JLabel lblStatusText = new JLabel("Last run status :");
		lblStatusText.setFont(new Font("Source Sans Pro Light", Font.PLAIN, 17));
		lblStatusText.setBounds(98, 94, 116, 26);
		this.getContentPane().add(lblStatusText);

		final JLabel lblValue = new JLabel("value");
		lblValue.setFont(new Font("Source Sans Pro Light", Font.PLAIN, 17));
		lblValue.setBounds(224, 94, 116, 26);
		this.getContentPane().add(lblValue);
	}
}
