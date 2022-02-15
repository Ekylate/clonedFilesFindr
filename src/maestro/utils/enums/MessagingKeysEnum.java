package maestro.utils.enums;

public enum MessagingKeysEnum {
	//Main menu messages
	MAINMENU_0("Ordonnanceur.mainmenu.0"),
	MAINMENU_1("Ordonnanceur.mainmenu.1"),
	MAINMENU_2("Ordonnanceur.mainmenu.2"),
	MAINMENU_3("Ordonnanceur.mainmenu.3"),

	//Error messages
	ERROR_0("Ordonnanceur.error.0"),

	//Information messages
	INFORMATION_0("Ordonnanceur.info.0"),
	;

	private MessagingKeysEnum(String keyParam) {
		this.key = keyParam;
	}

	private final String key;

	/**
	 * @return the key
	 */
	public String getKey() {
		return this.key;
	}
}
