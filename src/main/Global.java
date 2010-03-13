/**
 * This file contains all global variables
 */

package main;

import models.Language;

/**
 * The class contains all variables that have to be accessible from everywhere.
 * 
 * @author Robert Heim
 */
public class Global
{
	public static Language			lang;
	/** the string to use for unknown words */
	public static final String		UNKNOWN_WORD	= "(?)";
	public static final Boolean		DEBUG			= true;

}
