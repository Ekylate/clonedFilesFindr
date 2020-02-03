package modules;

import org.apache.commons.lang3.StringUtils;

import BusinessObjects.SeachResultBO;

/**
 * Class used to search through directories
 * @author jtantoine
 *
 */
public class SearchModule {
	private String targetNode = StringUtils.EMPTY;
	private SeachResultBO resultBo = new SeachResultBO();

	/**
	 * @return the targetNode
	 */
	public String getTargetNode() {
		return targetNode;
	}

	/**
	 * @param targetNode the targetNode to set
	 */
	public void setTargetNode(String targetNode) {
		this.targetNode = targetNode;
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
