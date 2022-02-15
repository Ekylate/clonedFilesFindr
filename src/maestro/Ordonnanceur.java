package maestro;

import java.io.File;
import java.nio.file.LinkOption;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
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
		demanderCheminDeRestitutionFichierResultatAnalyse();
//		demanderOptionsAnalyse();
		analyserChemin();
		restituerAnalyse();
	}

	private static void restituerAnalyse() {
		// TODO create result file
		System.out.println(resultMatches);
		FileUtils.getUserDirectory();
//		FileService.writeFile(System., resultMatches);

	}

	private static void analyserChemin() {
		spider.explore();
		final Map<String, List<String>> searchForMatchesInFetchedData = spider.searchForMatchesInFetchedData();
		resultMatches = spider.getResult(searchForMatchesInFetchedData);
	}

	private static void demanderOptionsAnalyse() {
		// TODO: process fetched options
		final String msgParam = MessagesService.getString("Ordonnanceur.mainmenu.2"); //$NON-NLS-1$
		IOConsoleService.displayMessageInConsole(msgParam);
		final String trc = fetchDataFromConsoleUntilCorrectDirectoryPath();
	}

	private static void demanderCheminAAnalyser() {
		IOConsoleService.displayMessageInConsole(MessagesService.getString("Ordonnanceur.mainmenu.1"));
		spider.setTargetNode(fetchDataFromConsoleUntilCorrectDirectoryPath());
	}

	private static void saluer() {
		IOConsoleService.displayMessageInConsole(MessagesService.fetchHeader());
	}

	private static void demanderCheminDeRestitutionFichierResultatAnalyse() {
		IOConsoleService.displayMessageInConsole(MessagesService.getString("Ordonnanceur.mainmenu.3"));
		final String fetchedData = fetchDataFromConsoleUntilCorrectDirectoryPath();
	}

	private static String fetchDataFromConsoleUntilCorrectDirectoryPath() {
		String result = null;
		while (result == null || !isFetchedStringAValidDirectoryPath(result)) {
			result = IOConsoleService.fetchDataFromConsole();
			if (!isFetchedStringAValidDirectoryPath(result)) {
				IOConsoleService.displayMessageInConsole(MessagesService.getString("Ordonnanceur.error.0")+"\r\n" + MessagesService.getString("Ordonnanceur.mainmenu.3"));
			}
		}
		return result;
	}

	private static boolean isFetchedStringAValidDirectoryPath(String resultParam) {
		return isFetchedStringAValidPath(resultParam) && FileUtils.isDirectory(new File(resultParam), LinkOption.NOFOLLOW_LINKS);
	}

	private static boolean isFetchedStringAValidPath(String resultParam) {
		return StringUtils.isNotBlank(resultParam) && new File(resultParam).exists();
	}
}
