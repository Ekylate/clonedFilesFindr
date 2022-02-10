package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class MessagesService {
	private static final String MESSAGES_KEY = "messages";

	private static final String HEADER_FILENAME = "header_cmd.properties";

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

	public static String fetchHeader() {
		final InputStream resourceAsStream = MessagesService.class.getClassLoader().getResourceAsStream(HEADER_FILENAME);
		final StringBuilder resultBuildr = new StringBuilder();
		try (InputStreamReader streamReader = new InputStreamReader(resourceAsStream, StandardCharsets.UTF_8); BufferedReader reader = new BufferedReader(streamReader)) {
			String line;
			while ((line = reader.readLine()) != null) {
				resultBuildr.append(line);
				resultBuildr.append("\n");
			}
		} catch (final IOException e) {
			e.printStackTrace();
		}
		return resultBuildr.toString();

	}
}
