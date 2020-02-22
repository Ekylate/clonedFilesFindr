package services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.lang3.StringUtils;

/**
 * Class used to manipulates files
 * 
 * @author jtantoine
 *
 */
public class FileService {
	private String[] emptyStringArray = new String[0];
	
	//TODO : Faire une condition
	public boolean isParamAFile(String pathParam) {
		boolean result = false;
		Path parsedPath = Paths.get(pathParam, emptyStringArray);
		result = Files.isRegularFile(parsedPath, LinkOption.NOFOLLOW_LINKS);
		return result;
	}
	
	public boolean isParamADirectory(String pathParam) {
		boolean result = false;
		Path parsedPath = Paths.get(pathParam, emptyStringArray);
		result = Files.isDirectory(parsedPath, LinkOption.NOFOLLOW_LINKS);
		return result;
	}
	
	public long getSizeOfParam(String pathParam) throws IOException {
		long result = 0L;
		Path parsedPath = Paths.get(pathParam, emptyStringArray);
		if(isParamADirectory(pathParam) || isParamAFile(pathParam)) {
			result = Files.size(parsedPath);
		}
		return result;
	}
	
	public String getFilename(String pathParam) {
		String result = StringUtils.EMPTY;
		if(StringUtils.isNotEmpty(pathParam)) {
			Path parsedPath = Paths.get(pathParam, emptyStringArray);
			result = parsedPath.getFileName().toString();
		}
		return result;
		
	}
	
	public File getFile(String pathParam) {
		File file = null;
		if(!StringUtils.isBlank(pathParam)) {
			Path parsedPath = Paths.get(pathParam, emptyStringArray);
			file = parsedPath.toFile();
		}
		return file;
		
	}
	
	//TODO : change return type to contain all results
	public void listFilesInDirectory(String pathParam) throws IOException {
		Files.list(new File(pathParam).toPath()).forEach(path -> System.out.println(path));
	}

}
