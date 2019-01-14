package cvextractor;

import cvformatgui.CVFormatGUI;
import cvformatgui.DisplayOutputGUI;

public class CVExtractor {

	public static boolean isGUIOpen = true;
	public static boolean isSecondOpen = false;
	public static String inputText;
	public static String outputThing;

	String[] str = { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October",
			"November", "December" };

	public static void main(String[] args) throws InterruptedException {

		CVFormatGUI GUI = new CVFormatGUI();
		GUI.main(args);

		while (isGUIOpen) {
			Thread.sleep(10);
		}
		
		DataExtract extractor = new DataExtract();
		outputThing = extractor.formatText();		
		System.out.println(extractor.formatText());
		
		DisplayOutputGUI secondGUI = new DisplayOutputGUI();
		secondGUI.main(args);
		
		while (isSecondOpen) {
			Thread.sleep(10);
		}
		
	}

}
