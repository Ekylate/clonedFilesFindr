package fr.kazanmw.business_objects;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class used to represent results of search within the process
 * @author jtantoine
 *
 */
public class SearchResultBO {
	private Map<String, List<String>> resultsByName;
	private Map<String, List<String>> resultsBySize;

	/**
	 * @return the resultsByName
	 */
	public Map<String, List<String>> getResultsByName() {
		if(this.resultsByName == null) {
			this.resultsByName = new HashMap<>();
		}
		return this.resultsByName;
	}
	/**
	 * @param resultsByName the resultsByName to set
	 */
	public void setResultsByName(final Map<String, List<String>> resultsByName) {
		this.resultsByName = resultsByName;
	}
	/**
	 * @return the resultsBySize
	 */
	public Map<String, List<String>> getResultsBySize() {
		return this.resultsBySize;
	}
	/**
	 * @param resultsBySize the resultsBySize to set
	 */
	public void setResultsBySize(final Map<String, List<String>> resultsBySize) {
		this.resultsBySize = resultsBySize;
	}

}
