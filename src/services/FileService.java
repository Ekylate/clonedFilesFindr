package services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import maestro.utils.StringUtils;
import maestro.utils.constants.PunctuationStringConstants;

/**
 * Class used to manipulates files
 * 
 * @author jtantoine
 *
 */
public class FileService {
	private static final String[] emptyStringArray = new String[0];
	
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

	public static String listFilesInDirectory(String pathParam, String prefix) {
		StringBuilder result = new StringBuilder();
		Stream<Path> tmp = null;
		try {
			tmp = Files.list(new File(pathParam).toPath());
			// TODO : use logger here
			tmp.forEach(path -> {
//				System.out.println(path);
				result.append(pathParam).append(System.lineSeparator());
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
		return result.toString();
	}

}
