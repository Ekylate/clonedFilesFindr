package services;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class MessagesService {
	private static final String MESSAGES_KEY = "messages";

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGES_KEY);

	private MessagesService() {
	}

	public static String getString(final String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (final MissingResourceException e) {
			return "//!\\ Missing key in resources file : " + key;
		}
	}
}
