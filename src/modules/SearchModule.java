package modules;

import java.io.File;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.FileUtils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import business_objects.SearchResultBO;
import maestro.utils.constants.PunctuationStringConstants;
import services.FileService;

/**
 * Class used to search through directories
 * @author jtantoine
 *
 */
public class SearchModule {
	private static final int FLOOR_VALUE_TO_NOTICE_DOUBLONS = 1;
	private String rootNode = StringUtils.EMPTY;
	private SearchResultBO resultBo = new SearchResultBO();
	private final List<Path> notParsedYet = new ArrayList<>();
	private Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	//TODO : remanier méthode pour que récursivité ou boucle
	public void explore() {
		if(StringUtils.isNotBlank(rootNode)) {
			if(FileService.isParamADirectory(rootNode)) {
//				notParsedYet.add(FileService.listFilesInDirectory(rootNode));
				notParsedYet.addAll(FileService.listFilesInDirectoryRecursively(rootNode));
			} else {
				LOGGER.debug("Element rencontré non répertoire : {}", rootNode);
			}
		} else {
			LOGGER.warn("Noeud de départ vide, programme de recherche non lancé");
		}
	}
	
	public Map<String, List<String>> searchForMatchesInFetchedData() {
		Map<String, List<String>> result = new HashMap<>();
		if(!notParsedYet.isEmpty()) {
			for(Path p : notParsedYet) {
				addIfFile(p);
			}
		}
		resultBo.getResultsByName().entrySet().stream()
				.filter(entry -> entry.getValue().size() > FLOOR_VALUE_TO_NOTICE_DOUBLONS)
				.forEach(entry -> result.put(entry.getKey(), entry.getValue()));
		return result;
	}

	public String getResult(Map<String, List<String>> paramMap) {
		StringBuilder result = new StringBuilder();
		if (!paramMap.isEmpty()) {
			paramMap.entrySet().stream()
					.forEach(entry -> result.append(entry.getKey()).append(System.lineSeparator())
							.append(PunctuationStringConstants.BLANKSPACE)
							.append(maestro.utils.StringUtils.concatenateStringListToSingleMultilinedString(entry.getValue(),
									PunctuationStringConstants.FIVE_WHITESPACES)));
		}
		return result.toString();
	}

	/**
	 * @return the targetNode
	 */
	public String getTargetNode() {
		return rootNode;
	}

	/**
	 * @param targetNode the targetNode to set
	 */
	public void setTargetNode(String targetNode) {
		this.rootNode = targetNode;
	}

	/**
	 * @return the resultBo
	 */
	public SearchResultBO getResultBo() {
		return resultBo;
	}

	/**
	 * @param resultBo the resultBo to set
	 */
	public void setResultBo(SearchResultBO resultBo) {
		this.resultBo = resultBo;
	}
	
	/**
	 * Initialize in the given map objet a key and its values with an empty list
	 */
	private void initializeKeyIfNotExisting(Map<String, List<String>> map, String key) {
		if(!map.containsKey(key)) {
			map.put(key, new ArrayList<String>());
		}
	}
	
	private void addIfFile(Path fullPathParam) {
		if(FileUtils.isRegularFile(new File(fullPathParam.toString()), LinkOption.NOFOLLOW_LINKS)) {
			Path keyPath = fullPathParam.getFileName();
			initializeKeyIfNotExisting(resultBo.getResultsByName(), keyPath.toString());
			resultBo.getResultsByName().get(keyPath.toString()).add(fullPathParam.toString());
		}
	}
}
