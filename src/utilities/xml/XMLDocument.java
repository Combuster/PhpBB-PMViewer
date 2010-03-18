/**
 * This file implements a wrapper-class for xml-operations.
 */

package utilities.xml;

import java.io.File;
import java.io.FileReader;

import nanoxml.XMLElement;

/**
 * This class is a wrapper class, it may use other libs.
 * 
 * @author Robert Heim
 */
public class XMLDocument {
	private XMLNode root;

	/**
	 * initializes a new XMLDocument
	 * 
	 * @param file
	 *            the file to parse
	 */
	public XMLDocument(File file) {
		buildDoc(file);
	}

	/**
	 * initializes a new XMLDocument
	 * 
	 * @param file
	 *            the file to parse
	 */
	public XMLDocument(String file) {
		buildDoc(new File(file));
	}

	/**
	 * builds the document/root
	 * 
	 * @param file
	 *            the file to parse
	 */
	private void buildDoc(File file) {
		XMLElement root_innerType = new XMLElement();
		try {
			FileReader reader = new FileReader(file);
			root_innerType.parseFromReader(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
		root = new XMLNode(root_innerType);
	}

	/**
	 * gets the rootElement of the tree
	 * 
	 * @return the rootElement of the tree
	 */
	public XMLNode getRootElement() {
		return root;
	}
}
