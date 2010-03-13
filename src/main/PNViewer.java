/**
 * 
 */
package main;

/**
 * @author Robert Heim
 *
 */
public class PNViewer {

	private String		version	= "0.0.1a";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PNViewer PNV = new PNViewer();
		System.out.print(PNV.getVersion());

	}

	public PNViewer()
	{
		
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getVersion() {
		return version;
	}
}
