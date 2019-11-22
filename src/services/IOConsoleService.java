package services;

import java.util.Scanner;

/**
 * Classe servant à gérer les entrées et sorties via la console
 * @author jtantoine
 *
 */
public class IOConsoleService {
	
	/**
	 * Méthode permettant d'afficher du texte en console
	 * @param msgParam
	 */
	//TODO : Set a check on non blankness of variable
	public static void displayMessageInConsole(String msgParam) {
		System.out.println(msgParam);
	}
	
	/**
	 * Méthode permettant de récupérer les entrants en provenance de la console
	 * @return
	 */
	//TODO : Put the expected type as a parameter
	public static String fetchDataFromConsole() {
		Scanner scanner = new Scanner(System.in);
		String incomingValue = scanner.next();
	}

}
