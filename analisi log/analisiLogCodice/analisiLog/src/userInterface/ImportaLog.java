package userInterface;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import controller.Controller;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class ImportaLog extends JFrame {

	private JPanel contentPane;
	private static Point p = null;
	private static String titolo = "Analisi Log";
	private JTextField fieldNome;
	private boolean errorFlag = false;
	private JTextField packagetField;
	private boolean flagPackage = false;
	private boolean flagNome= false;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ImportaLog frame = new ImportaLog(p);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ImportaLog(Point p) {
		super(titolo);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 900, 655);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		ImageIcon imgIcon = new ImageIcon(getClass().getResource("/userInterface/immagini/icona.PNG"));	
		setIconImage(imgIcon.getImage());

		if(p != null) {
			setLocation(p);
		}else {
			setLocationRelativeTo(null);
		}	

		JLabel titoloLabel = new JLabel("ANALISI LOG");
		titoloLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 60));
		titoloLabel.setBounds(238, 28, 424, 83);
		contentPane.add(titoloLabel);

		JLabel descrizioneLabel = new JLabel("Inserisci il nome con cui salvare il file di Log");
		descrizioneLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		descrizioneLabel.setBounds(142, 297, 616, 43);
		contentPane.add(descrizioneLabel);

		JLabel nomeLabel = new JLabel("Nome File di Log");
		nomeLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
		nomeLabel.setBounds(161, 355, 202, 43);
		contentPane.add(nomeLabel);

		fieldNome = new JTextField();
		fieldNome.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
		fieldNome.setBounds(389, 355, 350, 43);
		contentPane.add(fieldNome);
		fieldNome.setColumns(10);

		ImageIcon iconError= new ImageIcon(getClass().getResource("/userInterface/immagini/errorIco.PNG"));
		Image imgIconError= iconError.getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH);

		JLabel errorLabel = new JLabel("Il campo nome del log pu\u00F2 contenere solo lettere");		
		errorLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
		errorLabel.setHorizontalAlignment(SwingConstants.LEFT);
		errorLabel.setForeground(Color.RED);
		errorLabel.setBounds(192, 445, 571, 36);
		errorLabel.setVisible(false);
		contentPane.add(errorLabel);

		JLabel icoErrorLabel = new JLabel("");
		icoErrorLabel.setBounds(137, 445, 36, 36);
		icoErrorLabel.setIcon(new ImageIcon(imgIconError));
		icoErrorLabel.setVisible(false);
		contentPane.add(icoErrorLabel);
		
		JButton importaButton = new JButton("Importa");
		importaButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
		importaButton.setBounds(635, 525, 168, 63);
		importaButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		importaButton.setToolTipText("Importa Log");
		importaButton.setEnabled(false);
		contentPane.add(importaButton);
		
		JButton indietroButton = new JButton("Indietro");
		indietroButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
		indietroButton.setBounds(97, 525, 168, 63);
		indietroButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		indietroButton.setToolTipText("Indietro");
		contentPane.add(indietroButton);
		
		JLabel packageLabel = new JLabel("Inserisci nome del package in cui si trova il file di Log");
		packageLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		packageLabel.setBounds(81, 150, 738, 43);
		contentPane.add(packageLabel);
		
		packagetField = new JTextField();
		packagetField.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
		packagetField.setBounds(389, 208, 350, 43);
		contentPane.add(packagetField);
		packagetField.setColumns(10);
		
		JLabel nomePackageLabel = new JLabel("Nome package");
		nomePackageLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
		nomePackageLabel.setBounds(161, 208, 202, 43);
		contentPane.add(nomePackageLabel);
		
		
		indietroButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainFrame m = new MainFrame(getLocation(),errorFlag);
				m.setVisible(true);
				dispose();
			}
		});
		
		importaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Controller.importaLog(packagetField.getText(), fieldNome.getText());
					errorFlag = false;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					errorFlag= true;
				} finally {
					MainFrame m = new MainFrame(getLocation(),errorFlag);
					m.setVisible(true);
					dispose();
				}
			}
		});

		// Listen for changes in the text
		fieldNome.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				warn();
			}
			public void removeUpdate(DocumentEvent e) {
				warn();
			}
			public void insertUpdate(DocumentEvent e) {
				warn();
			}

			public void warn() {
				if(fieldNome.getText() != null && !fieldNome.getText().isEmpty()) {
					char [] listaCaratteri = fieldNome.getText().toCharArray();
					for(char c : listaCaratteri) {
						if(Character.isLetter(c) || Character.isDigit(c)) {
							flagNome = true;
							errorLabel.setVisible(false);
							icoErrorLabel.setVisible(false);
							importaButton.setEnabled(flagNome && flagPackage);
						}else {
							flagNome = false;
							errorLabel.setVisible(true);
							icoErrorLabel.setVisible(true);
							importaButton.setEnabled(false);
						}
					} 
				}else {
					flagNome = false;
					errorLabel.setVisible(false);
					icoErrorLabel.setVisible(false);
					importaButton.setEnabled(false);
				}
			}
		});
		
		packagetField.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				warn();
			}
			public void removeUpdate(DocumentEvent e) {
				warn();
			}
			public void insertUpdate(DocumentEvent e) {
				warn();
			}

			public void warn() {
				if(packagetField.getText() != null && !packagetField.getText().isEmpty()) {
					flagPackage = true;
					importaButton.setEnabled(flagNome && flagPackage);
				}else {
					flagPackage = false;
					importaButton.setEnabled(flagNome && flagPackage);
				}
			}
		});


	}
}
