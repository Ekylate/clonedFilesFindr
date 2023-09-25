package fr.kazanmw.ui;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import fr.kazanmw.maestro.Ordonnanceur;

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

		final JButton btnPickingUpFolder = new JButton("choose folder");
		btnPickingUpFolder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GlobalFrame.this.ouvrirExplorateur();
			}
		});
		btnPickingUpFolder.setToolTipText("click here to select a folder through Windows explorer");
		btnPickingUpFolder.setBounds(251, 44, 105, 23);
		this.getContentPane().add(btnPickingUpFolder);

		final JLabel lblStatusText = new JLabel("Last run status :");
		lblStatusText.setFont(new Font("Source Sans Pro Light", Font.PLAIN, 17));
		lblStatusText.setBounds(98, 94, 116, 26);
		this.getContentPane().add(lblStatusText);

		final JLabel lblValue = new JLabel("value");
		lblValue.setFont(new Font("Source Sans Pro Light", Font.PLAIN, 17));
		lblValue.setBounds(224, 94, 116, 26);
		this.getContentPane().add(lblValue);

		final JButton processLaunchingButton = new JButton("Search for clones");
		processLaunchingButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Ordonnanceur.lancerTraitementPourIHM(GlobalFrame.this.getTxtChooseTheFolder().getText());
			}
		});
		processLaunchingButton.setBounds(80, 142, 116, 23);
		this.getContentPane().add(processLaunchingButton);

		final JButton resultsFolderOpeningButton = new JButton("Open results folder");
		resultsFolderOpeningButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GlobalFrame.this.ouvrirDossierDeResultats();
			}
		});
		resultsFolderOpeningButton.setBounds(206, 142, 134, 23);
		this.getContentPane().add(resultsFolderOpeningButton);
	}

	/**
	 * @return the txtChooseTheFolder
	 */
	public JTextField getTxtChooseTheFolder() {
		return this.txtChooseTheFolder;
	}

	//TODO: JTA : compléter méthode
	protected void ouvrirDossierDeResultats() {
	}

	//TODO: JTA : compléter méthode
	protected void ouvrirExplorateur() {
	}
}
