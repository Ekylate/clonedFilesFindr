package BusinessObjects;

import java.util.List;
import java.util.Map;

/**
 * Class used to represent results of search within the process
 * @author jtantoine
 *
 */
public class SeachResultBO {
	private Map<String, List<String>> resultsByName;
	private Map<String, List<String>> resultsBySize;

	/**
	 * @return the resultsByName
	 */
	public Map<String, List<String>> getResultsByName() {
		return resultsByName;
	}
	/**
	 * @param resultsByName the resultsByName to set
	 */
	public void setResultsByName(Map<String, List<String>> resultsByName) {
		this.resultsByName = resultsByName;
	}
	/**
	 * @return the resultsBySize
	 */
	public Map<String, List<String>> getResultsBySize() {
		return resultsBySize;
	}
	/**
	 * @param resultsBySize the resultsBySize to set
	 */
	public void setResultsBySize(Map<String, List<String>> resultsBySize) {
		this.resultsBySize = resultsBySize;
	}

}
