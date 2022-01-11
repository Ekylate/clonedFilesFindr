package maestro;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

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
	private static String resultMatches = StringUtils.EMPTY;

	/**
	 * Main method of that program
	 * @param args
	 */
	public static void main(String[] args) {
		saluer();
		demanderCheminAAnalyser();
//		demanderOptionsAnalyse();
		analyserChemin();
		restituerAnalyse();
	}

	private static void restituerAnalyse() {
		// TODO create result file
		System.out.println(resultMatches);
		System.out.println();
		
	}

	private static void analyserChemin() {
		spider.explore();
		Map<String, List<String>> searchForMatchesInFetchedData = spider.searchForMatchesInFetchedData();
		resultMatches = spider.getResult(searchForMatchesInFetchedData);
	}

	private static void demanderOptionsAnalyse() {
		// TODO: process fetched options 
		String msgParam = MessagesService.getString("Ordonnanceur.mainmenu.2"); //$NON-NLS-1$
		IOConsoleService.displayMessageInConsole(msgParam);
		String trc = IOConsoleService.fetchDataFromConsole();
	}

	private static void demanderCheminAAnalyser() {
		IOConsoleService.displayMessageInConsole(MessagesService.getString("Ordonnanceur.mainmenu.1"));
		spider.setTargetNode(IOConsoleService.fetchDataFromConsole());
	}

	private static void saluer() {
		IOConsoleService.displayMessageInConsole(MessagesService.getString("Ordonnanceur.mainmenu.0"));
	}

}
