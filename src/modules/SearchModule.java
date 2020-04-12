package modules;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import BusinessObjects.SeachResultBO;
import services.FileService;

/**
 * Class used to search through directories
 * @author jtantoine
 *
 */
public class SearchModule {
	private String rootNode = StringUtils.EMPTY;
	private SeachResultBO resultBo = new SeachResultBO();
	private FileService fileService = new FileService();
	private List<String> notParsedYet = new ArrayList<String>();
	private Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	//TODO : remanier méthode pour que récursivité ou boucle
	public void explore() {
		String targetNode = StringUtils.EMPTY;
		
		if(StringUtils.isNotBlank(rootNode)) {
			if(fileService.isParamADirectory(rootNode)) {
				// TODO : ajouter une récursivité
				notParsedYet.add(fileService.listFilesInDirectory(rootNode));
			} else {
				LOGGER.debug("Element rencontré non répertoire : {}", rootNode);
			}
		} else {
			LOGGER.warn("Noeud de départ vide, programme de recherche non lancé");
		}
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
	public SeachResultBO getResultBo() {
		return resultBo;
	}

	/**
	 * @param resultBo the resultBo to set
	 */
	public void setResultBo(SeachResultBO resultBo) {
		this.resultBo = resultBo;
	}
}
