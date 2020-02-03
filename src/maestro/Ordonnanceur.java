package maestro;

import modules.SearchModule;
import services.IOConsoleService;
import services.MessagesService;

/**
 * Class used to launch processes
 * 
 * @author jtantoine
 *
 */
public class Ordonnanceur {
	
	private static final SearchModule spider = new SearchModule();

	/**
	 * Main method of that program
	 * @param args
	 */
	public static void main(String[] args) {
		saluer();
		demanderCheminAAnalyser();
		demanderOptionsAnalyse();
		analyserChemin();
		restituerAnalyse();
	}

	private static void restituerAnalyse() {
		// TODO Get results and format them for rendering
		
	}

	private static void analyserChemin() {
		// TODO Launch analysis of the targeted folder
		
	}

	private static void demanderOptionsAnalyse() {
		//TODO : Look for other ways to fetch messages
		String msgParam = MessagesService.getString("Ordonnanceur.mainmenu.0"); //$NON-NLS-1$
		IOConsoleService.displayMessageInConsole(msgParam);
	}

	private static void demanderCheminAAnalyser() {
		//TODO : Look for other ways to fetch messages
		String msgParam = MessagesService.getString("Ordonnanceur.mainmenu.1"); //$NON-NLS-1$
		IOConsoleService.displayMessageInConsole(msgParam);
		spider.setTargetNode(IOConsoleService.fetchDataFromConsole());
	}

	private static void saluer() {
		//TODO : Look for other ways to fetch messages
		String greetings = MessagesService.getString("Ordonnanceur.mainmenu.2"); //$NON-NLS-1$
		IOConsoleService.displayMessageInConsole(greetings);
	}

}
