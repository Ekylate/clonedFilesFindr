package fr.kazanmw.services;

import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;


/**
 * Classe servant à gérer les entrées et sorties via la console
 * @author jtantoine
 *
 */
public class IOConsoleService {

	private static Scanner scannerVar;

	/**
	 * Méthode permettant d'afficher du texte en console
	 *
	 * @param msgParam
	 */
	// TODO : Use a logger just in case there is a problem with the content
	public static void displayMessageInConsole(final String msgParam) {
		if (StringUtils.isNotBlank(msgParam)) {
			System.out.println(msgParam);
		}
	}

	/**
	 * Méthode permettant de récupérer les entrants en provenance de la console
	 * @return ce que l'utilisateur a récupéré
	 */
	public static String fetchDataFromConsole() {
		initScannerIfNecessary();
		final String incomingValue = scannerVar.next();
//		System.out.println("Valeur recuperee par le scanner = " + incomingValue);
		return incomingValue;
	}

	private static void initScannerIfNecessary() {
		if(scannerVar == null ) {
			scannerVar = new Scanner(System.in);
		}
	}

	public static void closeScanner() {
		if(scannerVar != null) {
			scannerVar.close();
		}
	}
}
