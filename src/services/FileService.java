package services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;

import com.sun.tools.javac.util.StringUtils;

/**
 * Class used to manipulates files
 * 
 * @author jtantoine
 *
 */
public class FileService {
	//TODO : Faire une condition
	public boolean isParamAFile(String pathParam) {
		boolean result = false;
		Path parsedPath = Path.of(pathParam, null);
		result = Files.isRegularFile(parsedPath, LinkOption.NOFOLLOW_LINKS);
		return result;
	}
	
	public boolean isParamADirectory(String pathParam) {
		boolean result = false;
		Path parsedPath = Path.of(pathParam, null);
		result = Files.isDirectory(parsedPath, LinkOption.NOFOLLOW_LINKS);
		return result;
	}
	
	public long getSizeOfParam(String pathParam) throws IOException {
		long result = 0L;
		Path parsedPath = Path.of(pathParam, null);
		if(isParamADirectory(pathParam) || isParamAFile(pathParam)) {
			result = Files.size(parsedPath);
		}
		return result;
	}
	
	//TODO: Créer méthode pour pouvoir récupérer nom
	
	public File getFile(String pathParam) {
		File file = null;
		if(!StringUtils.isBlank(pathParam)) {
			Path parsedPath = Path.of(pathParam, null);
//			Files.g
		}
		return file;
		
	}

}
