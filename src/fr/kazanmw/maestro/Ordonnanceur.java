package fr.kazanmw.maestro;

import java.io.File;
import java.io.IOException;
import java.nio.file.LinkOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import fr.kazanmw.maestro.utils.enums.MessagingKeysEnum;
import fr.kazanmw.modules.SearchModule;
import fr.kazanmw.services.FileService;
import fr.kazanmw.services.IOConsoleService;
import fr.kazanmw.services.MessagesService;

/**
 * Class used to launch processes
 *
 * @author jtantoine
 *
 */
public class Ordonnanceur {

	private static final SearchModule spider = new SearchModule();
	private static final Object RESULT_FILE_EXTENSION = ".txt";
	private static String resultMatches = StringUtils.EMPTY;
	private static String resultsDirectoryForOutputFile = StringUtils.EMPTY;
	private static DateTimeFormatter filenameFormatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");

	/**
	 * Main method of that program
	 * @param args
	 */
	public static void main(String[] args) {
		lancerTraitementPourInviteDeCommandes();
	}

	public static void lancerTraitementPourIHM(String pathParam) {
		if(verifierConditionsDeLancement(pathParam)) {
			spider.setTargetNode(pathParam);
			deduireCheminRestitutionResultat(pathParam);
//			demanderOptionsAnalyse();
			analyserChemin();
			creerFichierResultatDeRecherche();
			restituerAnalyse();
		}
	}

	private static boolean verifierConditionsDeLancement(String pathParam) {
		return StringUtils.isNotEmpty(pathParam) && FileService.isParamADirectory(pathParam);
	}

	// TODO : JTA/JAE : stop using static vars, use return and parameters instead
	private static void deduireCheminRestitutionResultat(String pathParam) {
		resultsDirectoryForOutputFile = pathParam;
	}

	//TODO : JTA : récupérer chemin du fichier créé pour restitution utilisateur
	private static void creerFichierResultatDeRecherche() {
		printResultsInFile(resultsDirectoryForOutputFile, resultMatches);
	}

	private static void lancerTraitementPourInviteDeCommandes() {
		saluer();
		demanderCheminAAnalyser();
		demanderCheminDeRestitutionFichierResultatAnalyse();
		fermerScanner();
//		demanderOptionsAnalyse();
		analyserChemin();
		restituerAnalyse();
	}

	private static void restituerAnalyse() {
		IOConsoleService.displayMessageInConsole(resultMatches);
		final String pathfileOfResultsFile = printResultsInFile(resultsDirectoryForOutputFile, resultMatches);
		if(!StringUtils.isEmpty(pathfileOfResultsFile)) {
			IOConsoleService.displayMessageInConsole(MessagesService.getString(MessagingKeysEnum.INFORMATION_0.getKey()) + StringUtils.SPACE + pathfileOfResultsFile);
		}
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
		boolean isFetchedStringAValidDirectoryPathBoolean = false;
		while (result == null || !isFetchedStringAValidDirectoryPathBoolean) {
			result = IOConsoleService.fetchDataFromConsole();
			isFetchedStringAValidDirectoryPathBoolean = isFetchedStringAValidDirectoryPath(result);
			if (!isFetchedStringAValidDirectoryPathBoolean) {
				IOConsoleService.displayMessageInConsole(MessagesService.getString(MessagingKeysEnum.ERROR_0.getKey())+"\r\n" + additionalErrorMessage);
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

	private static String printResultsInFile(final String directoryParam, final String fileContentParam) {
		String result;
		final StringBuilder filePathBuildr = new StringBuilder();
		filePathBuildr.append(StringUtils.isNotEmpty(directoryParam) ? directoryParam : FileUtils.getUserDirectory());
		if(!StringUtils.endsWith(directoryParam, File.separator)) {
			filePathBuildr.append(File.separator);
		}
		filePathBuildr.append(LocalDateTime.now().format(filenameFormatter));
		filePathBuildr.append(RESULT_FILE_EXTENSION);
		try {
			FileService.writeFile(filePathBuildr.toString(), fileContentParam);
			result = filePathBuildr.toString();
		} catch (final IOException e) {
			result = StringUtils.EMPTY;
		}

		return result;
	}

	private static void fermerScanner() {
		IOConsoleService.closeScanner();
	}
}
