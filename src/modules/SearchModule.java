package modules;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

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
	
	//TODO : remanier méthode pour que récursivité ou boucle
	public void explore() {
		String targetNode = StringUtils.EMPTY;
		
		if(StringUtils.isNotBlank(rootNode)) {
			if(fileService.isParamADirectory(rootNode)) {
				// TODO : ajouter une récursivité
				notParsedYet.add(fileService.listFilesInDirectory(rootNode));
			}
			//TODO : use LOGGER here in case targetNode is not a directory
		} 
		//TODO : use LOGGER here in case targetNode is empty
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
