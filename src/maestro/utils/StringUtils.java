package maestro.utils;

/**
 * Handmade class used because of the lack of the conventionnal class of the same name
 * @author jtantoine
 *
 */
public class StringUtils {
	public static final String EMPTY = "";
	
	public static boolean isEmpty(String paramToCheck) {
		return (paramToCheck != null && paramToCheck.isEmpty());
	}

	public static boolean isNotEmpty(String paramToCheck) {
		return (paramToCheck != null && !paramToCheck.isEmpty());
	}
	
	public static boolean isBlank(String paramToCheck) {
		return (paramToCheck == null || isEmpty(paramToCheck) || paramToCheck.isBlank());
	}

	public static boolean isNotBlank(String paramToCheck) {
		return !isBlank(paramToCheck);
	}
}
