/**
 * This class represents a LanguageFile.
 */

package models;

import java.util.HashMap;
import java.util.Vector;

import main.Global;
import utilities.xml.XMLDocument;
import utilities.xml.XMLNode;

/**
 * This class represents a LanguageFile. It reads and holds language information
 * and translations from a specified language file. All translations are coded
 * in English uppercase language-keys like "GROUP#ATTR_ID". Example:
 * "BASIC#HELLO" maps to "Hello".
 * 
 * @author Robert Heim
 */
public class LanguageFile {
	/** file/document to read the translations from */
	private XMLDocument doc;
	/** this HashMap holds all translations */
	private HashMap<String, String> data;

	/**
	 * Initializes the object and reads the translations from the file
	 * 
	 * @param fileName
	 *            the file to read the translations from
	 */
	public LanguageFile(String fileName) {
		doc = new XMLDocument(fileName);
		data = new HashMap<String, String>();
		readFile();
	}

	/**
	 * reads the translations from the file and stores them in the data HasMap
	 */
	private void readFile() {

		XMLNode lang = doc.getRootElement();

		// read groups
		Vector<XMLNode> groups = lang.getChildren("group");
		for (XMLNode g : groups) {
			// groups
			String group = g.getAttributeValue("id");

			// entries
			Vector<XMLNode> entries = g.getChildren("entry");
			for (XMLNode e : entries) {
				String entry = e.getAttributeValue("id");
				String translation = e.getText();
				data.put(group + "#" + entry, translation);
			}
		}
	}

	/**
	 * Gets the translation for a key.
	 * 
	 * @param group
	 *            the group id where the attr_id is looked up in.
	 * @param attr_id
	 *            the id of the translation within the group
	 * @return null if there is no entry for group#attr_id
	 */
	public String getTranslation(String group, String attr_id) {
		String key = group.concat("#").concat(attr_id);
		String translation = data.get(key);
		if (translation == null) {
			translation = Language.UNKNOWN_WORD;
		}
		return translation;
	}
}