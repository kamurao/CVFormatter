package cvformatgui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import cvextractor.CVExtractor;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

public class CVFormatGUI {

	private JFrame frmCvformat;
	public static String roleString;
	public static String companyString;
	public static String datesString = "";
	public static String respString;
	public static String inputText;
	private boolean isGUIOpen = true;
	public Object redLast = null, blueLast = null, greenLast = null, yellowLast = null;

	/**
	 * Launch the application.
	 */
	public void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CVFormatGUI window = new CVFormatGUI();
					window.frmCvformat.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CVFormatGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCvformat = new JFrame();
		frmCvformat.setTitle("CVFormat");
		frmCvformat.setBounds(100, 100, 766, 617);
		frmCvformat.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCvformat.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(10, 11, 530, 552);
		frmCvformat.getContentPane().add(panel);
		panel.setLayout(null);

		JTextArea textArea = new JTextArea();
		textArea.setColumns(1);
		textArea.setRows(1);
		textArea.setBounds(10, 39, 510, 502);
		panel.add(textArea);

		JLabel lblInsert = new JLabel("Insert Job Experience");
		lblInsert.setBounds(10, 11, 117, 17);
		panel.add(lblInsert);

		JButton btnExtract = new JButton("Extract");
		btnExtract.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CVExtractor.inputText = textArea.getText().toString();
				CVExtractor.isGUIOpen = false;
				CVExtractor.isSecondOpen = true;

				frmCvformat.setVisible(false);
			}
		});
		btnExtract.setBounds(624, 412, 89, 23);
		frmCvformat.getContentPane().add(btnExtract);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(550, 11, 190, 390);
		frmCvformat.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JTextArea roleTA = new JTextArea();
		roleTA.setBounds(10, 44, 170, 31);
		panel_1.add(roleTA);

		JLabel lblRole = new JLabel("Position:");
		lblRole.setBounds(10, 19, 93, 14);
		panel_1.add(lblRole);

		Highlighter roleHighlight = textArea.getHighlighter();
		JButton btnNewButton = new JButton("Extract");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textArea.getSelectedText() != null) { // See if they selected something 
					roleString = textArea.getSelectedText();
					roleTA.setText(roleString);

					int rolePos = 0;
					highlightText(textArea, roleString, roleHighlight, yellowPainter, rolePos, yellowLast);
				}
			}
		});
		btnNewButton.setBounds(113, 15, 67, 23);
		panel_1.add(btnNewButton);


		JTextArea companyTA = new JTextArea();
		companyTA.setBounds(10, 115, 170, 31);
		panel_1.add(companyTA);

		Highlighter companyHighlight = textArea.getHighlighter();
		JButton companyBtn = new JButton("Extract");
		companyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textArea.getSelectedText() != null) { // See if they selected something 
					companyString = textArea.getSelectedText();
					companyTA.setText(companyString);

					int companyPos = 0;
					highlightText(textArea, companyString, companyHighlight, bluePainter, companyPos, blueLast);
				}
			}
		});
		companyBtn.setBounds(113, 86, 67, 23);
		panel_1.add(companyBtn);

		JLabel lblCompany = new JLabel("Company");
		lblCompany.setBounds(10, 90, 93, 14);
		panel_1.add(lblCompany);

		JButton datesBtn = new JButton("Extract");
		datesBtn.setBounds(113, 157, 67, 23);
		panel_1.add(datesBtn);

		JLabel Date = new JLabel("Dates:");
		Date.setBounds(10, 161, 93, 14);
		panel_1.add(Date);

		JTextArea datesTA = new JTextArea();
		datesTA.setBounds(10, 186, 170, 31);
		panel_1.add(datesTA);

		JLabel lblResponsibilities = new JLabel("Responsibilities:");
		lblResponsibilities.setBounds(10, 232, 88, 14);
		panel_1.add(lblResponsibilities);


		JTextArea respTA = new JTextArea();
		respTA.setBounds(10, 257, 170, 31);
		panel_1.add(respTA);

		Highlighter respHighlight = textArea.getHighlighter();
		JButton respBtn = new JButton("Extract");
		respBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textArea.getSelectedText() != null) { // See if they selected something 
					respString = textArea.getSelectedText();
					respTA.setText(respString);

					int respPos = 0;
					highlightText(textArea, respString, respHighlight, greenPainter, respPos, greenLast);
				}
			}
		});
		respBtn.setBounds(113, 228, 67, 23);
		panel_1.add(respBtn);
		Highlighter datesHighlight = textArea.getHighlighter();

		datesBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textArea.getSelectedText() != null) { // See if they selected something 
					datesString = textArea.getSelectedText();
					datesTA.setText(datesString);

					int datesPos = 0;
					highlightText(textArea, datesString, datesHighlight, redPainter, datesPos, redLast);
				}
			}
		});		
	}

	Highlighter.HighlightPainter redPainter = new DefaultHighlighter.DefaultHighlightPainter(Color.RED);
	Highlighter.HighlightPainter greenPainter = new DefaultHighlighter.DefaultHighlightPainter(Color.GREEN);
	Highlighter.HighlightPainter bluePainter = new DefaultHighlighter.DefaultHighlightPainter(Color.BLUE);
	Highlighter.HighlightPainter yellowPainter = new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW);

	public void highlightText(JTextArea textarea, String subText, Highlighter h, Highlighter.HighlightPainter painter, int pos, Object last) {
		pos = textarea.getText().toString().indexOf(subText);
		System.out.println(last);
		if(last != null) {
			textarea.getHighlighter().removeHighlight(last);
		}
		try {
			if(painter == redPainter) {
				redLast = h.addHighlight(pos,pos +subText.length(),painter);
			} else if(painter == bluePainter) {
				blueLast = h.addHighlight(pos,pos +subText.length(),painter);
			} else if(painter == greenPainter) {
				greenLast = h.addHighlight(pos,pos +subText.length(),painter);
			} else if(painter == yellowPainter) {
				yellowLast = h.addHighlight(pos,pos +subText.length(),painter);
			}
			
		} catch (BadLocationException e1) {
			e1.printStackTrace();
		}
	}	
	
	public String getInputText() {
		return inputText;
	}

	public boolean isGUIOpen() {
		return isGUIOpen;
	}

	public void setGUIOpen(boolean isGUIOpen) {
		this.isGUIOpen = isGUIOpen;
	}
}
