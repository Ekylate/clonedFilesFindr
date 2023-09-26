package fr.kazanmw.ui;

import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.Instant;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.apache.commons.lang3.StringUtils;

import fr.kazanmw.maestro.Ordonnanceur;

public class GlobalFrame extends JFrame{
	private static final String TYPE_YOUR_FOLDER_HERE = "Type your folder here...";
	static int txtChooseTheFolderInteractionCounter = 0;
	/**
	 *
	 */
	private static final long serialVersionUID = -3957819353328780072L;
	private final JTextField txtChooseTheFolder;

	public GlobalFrame() {
		this.setTitle("CloneFinder");
		this.setBounds(400, 400, 450, 250);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);

		this.txtChooseTheFolder = new JTextField();
		this.txtChooseTheFolder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("mouse clicked on text field : " + Date.from(Instant.now()));
				GlobalFrame.this.clearTextFieldOnInteraction();
			}
		});
		this.txtChooseTheFolder.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				System.out.println("focus gained on text field : " + Date.from(Instant.now()));
				GlobalFrame.this.clearTextFieldOnInteraction();
			}
			@Override
			public void focusLost(FocusEvent e) {
				System.out.println("focus lost on text field : " + Date.from(Instant.now()));
				GlobalFrame.this.putBackPlaceholder();
			}
		});
		this.txtChooseTheFolder.setEnabled(true);
		this.txtChooseTheFolder.setEditable(true);
		this.txtChooseTheFolder.setText(TYPE_YOUR_FOLDER_HERE);
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
	 * Main method of that program
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				final GlobalFrame globalFrame = new GlobalFrame();
				globalFrame.setVisible(true);
			}
		});
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

	private void clearTextFieldOnInteraction() {
		if(txtChooseTheFolderInteractionCounter > 0 && StringUtils.equals(TYPE_YOUR_FOLDER_HERE, this.txtChooseTheFolder.getText())) {
			this.txtChooseTheFolder.setText(StringUtils.EMPTY);
		}
		txtChooseTheFolderInteractionCounter++;
	}

	private void putBackPlaceholder() {
		if(StringUtils.isBlank(this.txtChooseTheFolder.getText())) {
			this.txtChooseTheFolder.setText(TYPE_YOUR_FOLDER_HERE);
		}
	}
}
