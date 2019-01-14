package cvformatgui;

import java.awt.Color;
import java.awt.EventQueue;
import java.io.FileOutputStream;
import java.math.BigInteger;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFTextParagraph;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSpacing;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STLineSpacingRule;

import cvextractor.CVExtractor;

public class DisplayOutputGUI {

	private JFrame frmOutput;

	/**
	 * Launch the application.
	 */
	public void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DisplayOutputGUI window = new DisplayOutputGUI();
					window.frmOutput.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DisplayOutputGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmOutput = new JFrame();
		frmOutput.setTitle("Output");
		frmOutput.setBounds(100, 100, 665, 507);
		frmOutput.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmOutput.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 629, 447);
		frmOutput.getContentPane().add(panel);
		panel.setLayout(null);
		
		XWPFDocument document = new XWPFDocument();
		FileOutputStream output;
		
		XWPFParagraph paragraph = document.createParagraph();
		XWPFRun run = paragraph.createRun();
		setSingleLineSpacing(paragraph);
		paragraph.setSpacingAfter(150);
		
		XWPFParagraph blueText = document.createParagraph();
		XWPFRun runBlue = blueText.createRun();
		setSingleLineSpacing(blueText);
		blueText.setSpacingAfter(150);
		
		XWPFParagraph blackText = document.createParagraph();
		XWPFRun runBlack = blackText.createRun();
		setSingleLineSpacing(blackText);
		blackText.setSpacingAfter(150);
		

		
		String respLines[] = CVFormatGUI.respString.split("\\r?\\n");
		
		run.setFontFamily("Arial");
		run.setFontSize(9);
		run.setColor("66b2e4");
		run.setBold(true);
		run.setText(CVFormatGUI.companyString);

		runBlue.setFontFamily("Arial");
		runBlue.setFontSize(9);
		runBlue.setBold(true);
		runBlue.setColor("95c11f");
		runBlue.setText(CVFormatGUI.roleString);
		runBlue.addCarriageReturn(); 
		runBlue.addCarriageReturn(); 
		runBlue.setText(CVFormatGUI.datesString);
		
		runBlack.setFontFamily("Arial");
		runBlack.setFontSize(9);
		runBlack.setColor("5d6061");
		runBlack.setText("Responsibilities: ");
		runBlack.addCarriageReturn();   
		runBlack.setBold(true);
		for(int i = 0; i < respLines.length; i++) {
			String trimmmed = respLines[i].trim();
			String newoutput = trimmmed.replace("[^\\p{L}\\p{Z}]","");
			newoutput = newoutput.replace("•", "");
			runBlack.setText("     •" + newoutput);
			runBlack.addCarriageReturn();
		}		
		
		
		try {
			output = new FileOutputStream("Tester.docx");
			document.write(output);
			output.close();
			document.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JTextPane outputPane = new JTextPane();
		outputPane.setBounds(10, 11, 609, 436);
		panel.add(outputPane);
		outputPane.setText(CVExtractor.outputThing);
	}

	public void setSingleLineSpacing(XWPFParagraph para) {
	    CTPPr ppr = para.getCTP().getPPr();
	    if (ppr == null) ppr = para.getCTP().addNewPPr();
	    CTSpacing spacing = ppr.isSetSpacing()? ppr.getSpacing() : ppr.addNewSpacing();
	    spacing.setAfter(BigInteger.valueOf(0));
	    spacing.setBefore(BigInteger.valueOf(0));
	    spacing.setLineRule(STLineSpacingRule.AUTO);
	    spacing.setLine(BigInteger.valueOf(240));
	}
}
