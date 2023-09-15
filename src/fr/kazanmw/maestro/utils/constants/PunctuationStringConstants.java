package fr.kazanmw.maestro.utils.constants;

/**
 * Class used to store punctuation characters as String constants
 * @author jtantoine
 *
 */
public class PunctuationStringConstants {

	private PunctuationStringConstants() {}

	public static final String WHITESPACE = " ";

	public static final String COMMA = ",";

	public static final String DOT = ".";

	public static final String SEMICOLON = ";";

	public static final String FIVE_WHITESPACES = new StringBuilder().append(WHITESPACE).append(WHITESPACE)
			.append(WHITESPACE).append(WHITESPACE).append(WHITESPACE).toString();
}
