package services;

import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;

/**
 * Class used to manipulates files
 * 
 * @author jtantoine
 *
 */
public class FileService {
	//TODO : Faire une condition
	//TODO : Choisir librairie pour manipulation fichiers
	public boolean isParamAFile(String pathParam) {
		boolean result = false;
		Path parsedPath = Path.of(pathParam, null);
		result = Files.isRegularFile(parsedPath, LinkOption.NOFOLLOW_LINKS);
		return result;
	}
	
	//TODO: Créer méthode pour vérifier si répertoire ou non
	
	//TODO: Créer méthode pour pouvoir récupérer taille
	
	//TODO: Créer méthode pour pouvoir récupérer nom

}
