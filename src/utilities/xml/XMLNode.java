/**
 * This class represents an element in the XMLDocument.
 */

package utilities.xml;

import java.util.Enumeration;
import java.util.Vector;

import nanoxml.XMLElement;

/**
 * This class represents a node in the XMLDocument.
 * 
 * @author Robert Heim
 */
public class XMLNode {
	private XMLElement e;

	/**
	 * creates a new XMLElement
	 * 
	 * @param e
	 *            Element of the internal element-type
	 */
	public XMLNode(XMLElement e) {
		this.e = e;
	}

	/**
	 * gets the text of an child
	 * 
	 * @param name
	 *            the name of the child
	 * @return the text of the child
	 */
	public String getChildText(String name) {
		Vector<?> children = e.getChildren();
		int size = children.size();
		for (int i = 0; i < size; i++) {
			XMLElement tmp = (XMLElement) children.get(i);
			if (tmp.getName().equals(name))
				return tmp.getContent();
		}
		return null;
	}

	/**
	 * gets all children of the element with tag-name==name
	 * 
	 * @param name
	 *            the name of the children
	 * @return the children
	 */
	public Vector<XMLNode> getChildren(String name) {
		Vector<XMLNode> return_v = new Vector<XMLNode>();

		Vector<?> v = e.getChildren();
		int size = v.size();
		for (int i = 0; i < size; i++) {
			XMLNode current = new XMLNode((XMLElement) v.get(i));
			if (current.getName().equals(name))
				return_v.add(current);
		}
		return return_v;
	}

	/**
	 * gets the value of the specified attribute
	 * 
	 * @param name
	 *            the attribute-name
	 * @return the value of the attribute
	 */
	public String getAttributeValue(String name) {
		Enumeration<?> attr = e.enumerateAttributeNames();
		while (attr.hasMoreElements()) {
			String key = (String) attr.nextElement();
			if (key.equalsIgnoreCase(name)) {
				return e.getStringAttribute(key);
			}

		}
		return null;
	}

	/**
	 * gets the child with the specified name
	 * 
	 * @param name
	 *            the name of the child
	 * @return the child
	 */
	public XMLNode getChild(String name) {
		Vector<?> v = e.getChildren();
		int size = v.size();
		for (int i = 0; i < size; i++) {
			XMLNode current = new XMLNode((XMLElement) v.get(i));
			if (current.getName().equals(name))
				return current;
		}
		return null;
	}

	/**
	 * gets the text value of the XMLElement
	 * 
	 * @return the text value
	 */
	public String getText() {
		return e.getContent();
	}

	/**
	 * returns the name of the element-node
	 * 
	 * @return the name of the element-node
	 */
	public String getName() {
		return e.getName();
	}
}