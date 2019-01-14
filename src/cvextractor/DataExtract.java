package cvextractor;

import cvformatgui.CVFormatGUI;

public class DataExtract {

	public String formatted;
	String newline = System.getProperty("line.separator");

	public String formatText() {
		formatted = 
				CVFormatGUI.companyString  + newline +
				CVFormatGUI.roleString + newline +
				CVFormatGUI.datesString  + newline +
				newline +
				"Responsibilities: "  + newline +
				CVFormatGUI.respString;
		return formatted;
	}

}
