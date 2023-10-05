package fr.kazanmw.maestro;

import java.awt.Component;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.nio.file.LinkOption;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.swing.JFileChooser;

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
	private static final String RESULT_FILE_EXTENSION = ".txt";
	private static String resultMatches = StringUtils.EMPTY;
	private static String resultsDirectoryForOutputFile = StringUtils.EMPTY;
	private static String resultsOutputFile = StringUtils.EMPTY;
	private static DateTimeFormatter filenameFormatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");

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

	public static void ouvrirDossierResultat() {
		System.out.println(Date.from(Instant.now())+ " : ouvrirDossierResultat : resultsOutputFile : " + resultsOutputFile);
		System.out.println(Date.from(Instant.now())+ " : ouvrirDossierResultat : resultsDirectoryForOutputFile : " + resultsDirectoryForOutputFile);
		try {
			if(StringUtils.isNotBlank(resultsOutputFile)) {
					Desktop.getDesktop().open(new File(resultsOutputFile));
			} else if(StringUtils.isNotBlank(resultsDirectoryForOutputFile)) {
				Desktop.getDesktop().open(new File(resultsDirectoryForOutputFile));
			} else {
				//TODO : JTA/JAE : put error HERE
			}
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String choisirDossierRecherche(String dossierSaisiParam, Component globalFrameParam) {
		final JFileChooser fileChooser = new JFileChooser();
		if(StringUtils.isNotBlank(dossierSaisiParam)) {
			fileChooser.setCurrentDirectory(new File(dossierSaisiParam));
		}
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		final int resultat = fileChooser.showOpenDialog(globalFrameParam);
		if(resultat == JFileChooser.APPROVE_OPTION) {
			final File selectedFile = fileChooser.getSelectedFile();
			if(selectedFile.isDirectory()){
				return selectedFile.getAbsolutePath();
			}
			//TODO  : JTA/JAE : Put feedback message here : choose a directory and not a file
		}
		return StringUtils.EMPTY;
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
		final String printedFilePath = printResultsInFile(resultsDirectoryForOutputFile, resultMatches);
		if(StringUtils.isNotBlank(printedFilePath)) {
			resultsOutputFile = printedFilePath;
		}
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
		fetchDataFromConsoleUntilCorrectDirectoryPath(MessagesService.getString(MessagingKeysEnum.MAINMENU_2.getKey()));
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
