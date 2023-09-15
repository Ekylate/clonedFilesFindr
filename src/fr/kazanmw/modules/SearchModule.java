package fr.kazanmw.modules;

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

import fr.kazanmw.business_objects.SearchResultBO;
import fr.kazanmw.maestro.utils.constants.PunctuationStringConstants;
import fr.kazanmw.services.FileService;

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
	private static final Logger LOGGER = LoggerFactory.getLogger(SearchModule.class);

	public void explore() {
		if(StringUtils.isNotBlank(this.rootNode)) {
			if(FileService.isParamADirectory(this.rootNode)) {
				this.notParsedYet.addAll(FileService.listFilesInDirectoryRecursively(this.rootNode));
			} else {
				LOGGER.debug("Element rencontré non répertoire : {}", this.rootNode);
			}
		} else {
			LOGGER.warn("Noeud de départ vide, programme de recherche non lancé");
		}
	}


	public Map<String, List<String>> searchForMatchesInFetchedData() {
		final Map<String, List<String>> result = new HashMap<>();
		if(!this.notParsedYet.isEmpty()) {
			for(final Path p : this.notParsedYet) {
				this.addIfFile(p);
			}
		}
		this.resultBo.getResultsByName().entrySet().stream()
				.filter(entry -> entry.getValue().size() > FLOOR_VALUE_TO_NOTICE_DOUBLONS)
				.forEach(entry -> result.put(entry.getKey(), entry.getValue()));
		return result;
	}

	public String getResult(final Map<String, List<String>> paramMap) {
		final StringBuilder result = new StringBuilder();
		if (!paramMap.isEmpty()) {
			paramMap.entrySet().stream()
					.forEach(entry -> result.append(entry.getKey()).append(System.lineSeparator())
							.append(fr.kazanmw.maestro.utils.StringUtils.concatenateStringListToSingleMultilinedString(entry.getValue(),
									PunctuationStringConstants.FIVE_WHITESPACES)));
		}
		return result.toString();
	}

	/**
	 * @return the targetNode
	 */
	public String getTargetNode() {
		return this.rootNode;
	}

	/**
	 * @param targetNode the targetNode to set
	 */
	public void setTargetNode(final String targetNode) {
		this.rootNode = targetNode;
	}

	/**
	 * @return the resultBo
	 */
	public SearchResultBO getResultBo() {
		return this.resultBo;
	}

	/**
	 * @param resultBo the resultBo to set
	 */
	public void setResultBo(final SearchResultBO resultBo) {
		this.resultBo = resultBo;
	}

	/**
	 * Initialize in the given map objet a key and its values with an empty list
	 */
	private void initializeKeyIfNotExisting(final Map<String, List<String>> map, final String key) {
		if(!map.containsKey(key)) {
			map.put(key, new ArrayList<String>());
		}
	}

	private void addIfFile(final Path fullPathParam) {
		if(FileUtils.isRegularFile(new File(fullPathParam.toString()), LinkOption.NOFOLLOW_LINKS)) {
			final Path keyPath = fullPathParam.getFileName();
			this.initializeKeyIfNotExisting(this.resultBo.getResultsByName(), keyPath.toString());
			this.resultBo.getResultsByName().get(keyPath.toString()).add(fullPathParam.toString());
		}
	}
}
