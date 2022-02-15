package maestro;

import java.io.File;
import java.nio.file.LinkOption;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import maestro.utils.enums.MessagingKeysEnum;
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
	private static String resultsDirectoryForOutputFile = StringUtils.EMPTY;

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
		IOConsoleService.displayMessageInConsole(MessagesService.getString(MessagingKeysEnum.MAINMENU_2.getKey()));
		final String trc = fetchDataFromConsoleUntilCorrectDirectoryPath(MessagesService.getString(MessagingKeysEnum.MAINMENU_2.getKey()));
	}

	private static void demanderCheminAAnalyser() {
		IOConsoleService.displayMessageInConsole(MessagesService.getString(MessagingKeysEnum.MAINMENU_1.getKey()));
		spider.setTargetNode(fetchDataFromConsoleUntilCorrectDirectoryPath(MessagesService.getString(MessagingKeysEnum.MAINMENU_1.getKey())));
	}

	private static void saluer() {
		IOConsoleService.displayMessageInConsole(MessagesService.fetchHeader());
	}

	private static void demanderCheminDeRestitutionFichierResultatAnalyse() {
		IOConsoleService.displayMessageInConsole(MessagesService.getString(MessagingKeysEnum.MAINMENU_3.getKey()));
		resultsDirectoryForOutputFile = fetchDataFromConsoleUntilCorrectDirectoryPath(MessagesService.getString(MessagingKeysEnum.MAINMENU_3.getKey()));
	}

	private static String fetchDataFromConsoleUntilCorrectDirectoryPath(final String additionalErrorMessage) {
		String result = null;
		while (result == null || !isFetchedStringAValidDirectoryPath(result)) {
			result = IOConsoleService.fetchDataFromConsole();
			if (!isFetchedStringAValidDirectoryPath(result)) {
				IOConsoleService.displayMessageInConsole(MessagesService.getString("Ordonnanceur.error.0")+"\r\n" + additionalErrorMessage);
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
