package models;

/**
 * Implements the Language functionality of the GameTracker.
 */

import java.util.HashMap;

/**
 * Implements the Language functionality of the GameTracker.
 * 
 * @author Robert Heim
 */
public class Language {
	/** the language that we fall back to, if a word is not found. */
	private Language fallBackLang;
	/** defines if this language is the fallBackLangugage */
	private boolean isFallBackLang = false;
	/** the standard language id */
	public static String fallBackLanguage = "en";
	/** the id of the language ('en', 'de', etc..) */
	private String id;
	/** all files that are registered to the Language */
	private HashMap<String, LanguageFile> files;
	/** the folder where all language files lay in */
	private String folder;
	/** the string to use for unknown words */
	public static final String UNKNOWN_WORD = "(?)";

	/**
	 * Initializes the language
	 * 
	 * @param folder
	 *            the languages folder
	 * @param id
	 *            the id of the language
	 */
	public Language(String folder, String id) {
		this.id = id;
		this.folder = folder + "/" + id;
		files = new HashMap<String, LanguageFile>();
		if (id != "en")
			this.fallBackLang = new Language(folder, "en", true);
		else {
			this.isFallBackLang = true;
		}
	}

	/**
	 * Initializes the fallBackLanguage
	 * 
	 * @param folder
	 *            the languages folder
	 * @param id
	 *            the id of the language
	 * @param isFallBack
	 *            defines, that this is the fallback language object and thats
	 *            why there is no need for another fallback.
	 */
	private Language(String folder, String id, Boolean isFallBack) {
		this.id = id;
		this.folder = folder + "/" + id;
		this.isFallBackLang = isFallBack;
		files = new HashMap<String, LanguageFile>();
	}

	/**
	 * adds a new file to the language to get translations from
	 * 
	 * @param file
	 *            the file without ".xml" realtive to the languages-folder
	 *            example: "modes/basic" for the file:
	 *            ./languages/en/modes/basic.xml
	 */
	public void addLanguageFile(String file) {
		LanguageFile f = new LanguageFile(folder + "/" + file + ".xml");
		files.put(file, f);
		if (!isFallBackLang && fallBackLang != null) {
			fallBackLang.addLanguageFile(file);
		}
	}

	/**
	 * Gets the translation for a key.
	 * 
	 * @param file
	 *            the file to read the translation from
	 * @param group
	 *            the group id where the attr_id is looked up in.
	 * @param attr_id
	 *            the id of the translation within the group
	 * @return null if there is no entry for group#attr_id
	 */
	public String getTranslation(String file, String group, String attr_id) {
		LanguageFile f = files.get(file);
		if (f == null)
			return UNKNOWN_WORD;

		String t = f.getTranslation(group, attr_id);

		// fall back if the translation can not be found
		if (t == UNKNOWN_WORD && !isFallBackLang && fallBackLang != null)
			t = fallBackLang.getTranslation(file, group, attr_id);

		return t;
	}

	/**
	 * Gets the translation for a key from the main file
	 * 
	 * @param group
	 *            the group id where the attr_id is looked up in.
	 * @param attr_id
	 *            the id of the translation within the group
	 * @return null if there is no entry for group#attr_id
	 */
	public String getTranslation(String group, String attr_id) {
		return getTranslation("main", group, attr_id);
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
}