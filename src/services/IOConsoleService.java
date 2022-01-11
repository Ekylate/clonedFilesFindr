package services;

import java.util.Scanner;

import maestro.utils.StringUtils;


/**
 * Classe servant à gérer les entrées et sorties via la console
 * @author jtantoine
 *
 */
public class IOConsoleService {

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
	//TODO : create utils method
	public static String fetchDataFromConsole() {
		final Scanner scanner = new Scanner(System.in);
		final String incomingValue = scanner.next();
		scanner.close();
		return incomingValue;
	}

}
