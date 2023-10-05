package fr.kazanmw.maestro.utils;

import java.util.List;

/**
 * Handmade class used because of the lack of the conventionnal class of the same name
 * @author jtantoine
 *
 */
public class StringUtils {
	private StringUtils() { }

	/**
	 * Concatenate the elements of the list in a multilined String. And the offset
	 * will be in the beginning of each line oh the final multilined String
	 *
	 * @param listParam list of the elements to concatenate
	 * @param offset    offset to put in the beginning of each line
	 * @return
	 */
	public static String concatenateStringListToSingleMultilinedString(final List<String> listParam, final String offset) {
		String result = org.apache.commons.lang3.StringUtils.EMPTY;
		if (listParam != null && !listParam.isEmpty()) {
			final StringBuilder strBuildr = new StringBuilder();
			for (final String elmt : listParam) {
				strBuildr.append(offset).append(elmt).append(System.lineSeparator());
			}
			result = strBuildr.toString();
		}
		return result;
	}
}
