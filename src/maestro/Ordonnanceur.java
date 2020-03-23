package maestro;

import java.util.List;
import java.util.Map;

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
		spider.explore();
		Map<String, List<String>> searchForMatchesInFetchedData = spider.searchForMatchesInFetchedData();
		System.out.println(spider.getResult(searchForMatchesInFetchedData));
	}

	private static void demanderOptionsAnalyse() {
		//TODO : Look for other ways to fetch messages
		IOConsoleService.displayMessageInConsole(MessagesService.getString("Ordonnanceur.mainmenu.0"));
	}

	private static void demanderCheminAAnalyser() {
		//TODO : Look for other ways to fetch messages
		IOConsoleService.displayMessageInConsole(MessagesService.getString("Ordonnanceur.mainmenu.1"));
		spider.setTargetNode(IOConsoleService.fetchDataFromConsole());
	}

	private static void saluer() {
		//TODO : Look for other ways to fetch messages
		IOConsoleService.displayMessageInConsole(MessagesService.getString("Ordonnanceur.mainmenu.2"));
	}

}
