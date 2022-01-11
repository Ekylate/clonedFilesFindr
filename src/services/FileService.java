package services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import maestro.utils.StringUtils;
import maestro.utils.constants.PunctuationStringConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class used to manipulates files
 * 
 * @author jtantoine
 *
 */
public class FileService {
	private Logger LOGGER = LoggerFactory.getLogger(FileService.class);
	private static final String[] emptyStringArray = new String[0];
	private static Set<String> alreadyPrintedElements = new HashSet<String>();

	// TODO : Faire une condition
	public static boolean isParamAFile(String pathParam) {
		boolean result = false;
		Path parsedPath = Paths.get(pathParam, emptyStringArray);
		result = Files.isRegularFile(parsedPath, LinkOption.NOFOLLOW_LINKS);
		return result;
	}

	public static boolean isParamADirectory(String pathParam) {
		boolean result = false;
		Path parsedPath = Paths.get(pathParam, emptyStringArray);
		result = Files.isDirectory(parsedPath, LinkOption.NOFOLLOW_LINKS);
		return result;
	}

	public static long getSizeOfParam(String pathParam) throws IOException {
		long result = 0L;
		Path parsedPath = Paths.get(pathParam, emptyStringArray);
		if (isParamADirectory(pathParam) || isParamAFile(pathParam)) {
			result = Files.size(parsedPath);
		}
		return result;
	}

	public static String getFilename(String pathParam) {
		String result = StringUtils.EMPTY;
		if (StringUtils.isNotEmpty(pathParam)) {
			Path parsedPath = Paths.get(pathParam, emptyStringArray);
			result = parsedPath.getFileName().toString();
		}
		return result;

	}

	public static File getFile(String pathParam) {
		File file = null;
		if (!StringUtils.isBlank(pathParam)) {
			Path parsedPath = Paths.get(pathParam, emptyStringArray);
			file = parsedPath.toFile();
		}
		return file;
	}
	
	public static void writeFile(String pathToWriteFileTo, String fileContent) {
	}

	public static Set<String> listFilesInDirectory(String pathParam) {
		Set<String> result = new HashSet<>();
		Stream<Path> tmp = null;
		try {
			tmp = Files.list(new File(pathParam).toPath());
			// TODO : use logger here
			tmp.forEach(path -> {
//				System.out.println(path);
				result.add(path.toString());
			});
		} catch (IOException ioe) {
			// TODO : use logger here
			System.out.println("Problème avec le parcours du dossier : " + pathParam);
			System.out.println("An IOException occured and here is why : " + ioe.getCause().getMessage());
		} finally {
			if (tmp != null) {
				tmp.close();
			}
		}
		return result;
	}
	
	public static List<Path> listFilesInDirectoryRecursively(String pathParam) {
		List<Path> result = new ArrayList<>();
		Stream<Path> tmp = null;
		try {
			File tmpFile = new File(pathParam);
			if(!alreadyPrintedElements.contains(pathParam)) {
				result.add(tmpFile.toPath());
				alreadyPrintedElements.add(pathParam);
			}
			if(isParamADirectory(pathParam)) {
				tmp = Files.list(new File(pathParam).toPath());
				// TODO : use logger here
				tmp.forEach(path -> {
					if(!alreadyPrintedElements.contains(path.toString())) {
						result.add(path);
						alreadyPrintedElements.add(path.toString());
					}
					if(isParamADirectory(path.toString())) {
						result.addAll(listFilesInDirectoryRecursively(path.toString()));
					}
				});
			} else{
				//TODO : Use logger to log it's not a directory
			}
		} catch (IOException ioe) {
			// TODO : use logger here
			System.out.println("Problème avec le parcours du dossier : " + pathParam);
			System.out.println("An IOException occured and here is why : " + ioe.getCause().getMessage());
		} finally {
			if (tmp != null) {
				tmp.close();
			}
		}
		return result;
	}

	public static String listFilesInDirectoryRecursivelyWithRendering(String pathParam, String prefixParam) {
		StringBuilder result = new StringBuilder();
		Stream<Path> tmp = null;
		String prefix = StringUtils.isNotEmpty(prefixParam) ? prefixParam : StringUtils.EMPTY;
		if(!alreadyPrintedElements.contains(pathParam)) {
//			System.out.println(prefix + pathParam);
			result.append(prefix).append(pathParam).append(System.lineSeparator());
			alreadyPrintedElements.add(pathParam);
		}
		try {
			if(isParamADirectory(pathParam)) {
				tmp = Files.list(new File(pathParam).toPath());
				// TODO : use logger here
				tmp.forEach(path -> {
					if(!alreadyPrintedElements.contains(path.toString())) {
						result.append(prefix).append(path).append(System.lineSeparator());
						alreadyPrintedElements.add(path.toString());
					}
					if(isParamADirectory(path.toString())) {
						result.append(listFilesInDirectoryRecursivelyWithRendering(path.toString(), PunctuationStringConstants.WHITESPACE+prefix));
					}
				});
			} else {
				//TODO : Use logger to log it's not a directory
			}
		} catch (IOException ioe) {
			// TODO : use logger here
			System.out.println("Problème avec le parcours du dossier : " + pathParam);
			System.out.println("An IOException occured and here is why : " + ioe.getCause().getMessage());
		} finally {
			if (tmp != null) {
				tmp.close();
			}
		}
		return result.toString();
	}
}
