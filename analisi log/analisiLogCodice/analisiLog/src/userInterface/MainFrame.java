package userInterface;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.Controller;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import javax.swing.ScrollPaneConstants;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private static Point p = null;
	private static String titolo = "Analisi Log";
	private static JPanel panel;
	private static JButton analizzaButton;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame(p,false);
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
	public MainFrame(Point p, boolean errorFlag) {
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
		
		JLabel importaLogLabel = new JLabel("Log da dispositivo o ");
		importaLogLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		importaLogLabel.setBounds(279, 171, 284, 50);
		contentPane.add(importaLogLabel);

		JButton importaLogButton = new JButton("Importa");
		importaLogButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		importaLogButton.setBounds(125, 171, 150, 50);
		importaLogButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		importaLogButton.setToolTipText("Importa Log da dispositivo");
		contentPane.add(importaLogButton);

		JLabel esitoImportLabel = new JLabel("");		
		esitoImportLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
		esitoImportLabel.setHorizontalAlignment(SwingConstants.CENTER);
		esitoImportLabel.setBounds(211, 242, 544, 36);
		esitoImportLabel.setVisible(false);
		
		JLabel importaLog2Label = new JLabel("da PC");
		importaLog2Label.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		importaLog2Label.setBounds(693, 171, 82, 50);
		contentPane.add(importaLog2Label);
		
		JButton caricaLogButton = new JButton("Carica");
		caricaLogButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		caricaLogButton.setBounds(560, 171, 128, 50);
		caricaLogButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		caricaLogButton.setToolTipText("Carica Log da PC");
		contentPane.add(caricaLogButton);
		contentPane.add(esitoImportLabel);
		
		ImageIcon iconSucces= new ImageIcon(getClass().getResource("/userInterface/immagini/successIco.PNG"));
		Image imgIconSucces = iconSucces.getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH);
		
		ImageIcon iconError= new ImageIcon(getClass().getResource("/userInterface/immagini/errorIco.PNG"));
		Image imgIconError= iconError.getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH);
		
		JLabel icoImportLabel = new JLabel("");
		icoImportLabel.setBounds(145, 242, 36, 36);
		icoImportLabel.setIcon(new ImageIcon(imgIconSucces));
		icoImportLabel.setVisible(false);
		contentPane.add(icoImportLabel);

		JLabel caricaScriptLabel = new JLabel("Carica Script contenenti sonde");
		caricaScriptLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		caricaScriptLabel.setBounds(115, 340, 445, 50);
		contentPane.add(caricaScriptLabel);

		JButton caricaScriptButton = new JButton("Carica Script");
		caricaScriptButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		caricaScriptButton.setBounds(627, 340, 158, 50);
		caricaScriptButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		caricaScriptButton.setToolTipText("Carica Script");
		caricaScriptButton.setEnabled(false);
		contentPane.add(caricaScriptButton);


		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setPreferredSize(new Dimension(367, 121));
		panel.setLayout(null);
		JScrollPane scrollFrame = new JScrollPane(panel);
		scrollFrame.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollFrame.setBounds(115, 432, 370, 124);
		scrollFrame.getVerticalScrollBar().setUnitIncrement(40);
		panel.setAutoscrolls(true);
		scrollFrame.setPreferredSize(new Dimension(360,114));
		contentPane.add(scrollFrame);

		analizzaButton = new JButton("Analizza");		
		analizzaButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
		analizzaButton.setBounds(702, 548, 169, 56);
		analizzaButton.setEnabled(false);
		analizzaButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		analizzaButton.setToolTipText("Analizza");
		contentPane.add(analizzaButton);
		
		costruzionePannelloInterno(Controller.getNomiScript());	
		if (Controller.presenzaLogFile() && !errorFlag) {
			esitoImportLabel.setForeground(new Color(60, 179, 113));
			esitoImportLabel.setText("File di Log importato e caricato correttamente");
			esitoImportLabel.setVisible(true);
			icoImportLabel.setIcon(new ImageIcon(imgIconSucces));
			icoImportLabel.setVisible(true);
			caricaScriptButton.setEnabled(true);
		}else if(errorFlag) {
			icoImportLabel.setIcon(new ImageIcon(imgIconError));
			icoImportLabel.setVisible(true);
			esitoImportLabel.setForeground(Color.RED);
			esitoImportLabel.setText("Errore nel caricamento del File di Log");
			esitoImportLabel.setVisible(true);
			caricaScriptButton.setEnabled(false);
		}
		
		importaLogButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ImportaLog i = new ImportaLog(getLocation());
				i.setVisible(true);
				dispose();
			}
		});
		
		caricaLogButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser(""+System.getProperty("user.home")+ "/Desktop");
				chooser.setFileFilter(new FileNameExtensionFilter("solo txt", "txt"));
				chooser.showOpenDialog(null);
				File f = chooser.getSelectedFile();
				if(f != null) {
					try {
						Controller.caricaLog(f);
						esitoImportLabel.setForeground(new Color(60, 179, 113));
						esitoImportLabel.setText("File di Log importato e caricato correttamente");
						esitoImportLabel.setVisible(true);
						icoImportLabel.setIcon(new ImageIcon(imgIconSucces));
						icoImportLabel.setVisible(true);
						caricaScriptButton.setEnabled(true);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						icoImportLabel.setIcon(new ImageIcon(imgIconError));
						icoImportLabel.setVisible(true);
						esitoImportLabel.setForeground(Color.RED);
						esitoImportLabel.setText("Errore nel caricamento del File di Log");
						esitoImportLabel.setVisible(true);
						caricaScriptButton.setEnabled(false);
					}
				}
				
			}
		});

		caricaScriptButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser(""+System.getProperty("user.home")+ "/Desktop");
				chooser.setFileFilter(new FileNameExtensionFilter("solo Script", "cs", "java", "c","cpp", "py"));
				chooser.showOpenDialog(null);
				File f = chooser.getSelectedFile();
				if(f != null) {
					try {
						Controller.aggiungiScript(f);
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}					
					pulisciPannelloInterno();
					costruzionePannelloInterno(Controller.getNomiScript());					
				}
			}
		});
		
		analizzaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Analisi a = new Analisi(getLocation());
				a.setVisible(true);
				dispose();
			}
		});
	}

	private void costruzionePannelloInterno(final List<String> nomiScript) {
		
		if (nomiScript.size() > 0 ) {
			analizzaButton.setEnabled(true);
		}else {
			analizzaButton.setEnabled(false);
		}
		 
		if (nomiScript.size() > 3) {
			panel.setPreferredSize(new Dimension(367, 121+40*(nomiScript.size()-3)));
		}


		ImageIcon iconDelete = new ImageIcon(getClass().getResource("/userInterface/immagini/icoDelete.PNG"));
		Image imgDelete = iconDelete.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);


		for(int i=0; i<nomiScript.size();i++) {
			JLabel nomeScriptLabel = new JLabel(nomiScript.get(i));
			nomeScriptLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
			nomeScriptLabel.setBounds(10, 11+(40*i), 250, 30);
			panel.add(nomeScriptLabel);

			JButton deleteButton = new JButton("");
			deleteButton.setBounds(310, 4+(40*i), 35, 35);
			deleteButton.setIcon(new ImageIcon(imgDelete));
			deleteButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			deleteButton.setToolTipText("Elimina Script");
			panel.add(deleteButton);


			final int index = i;
			deleteButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					pulisciPannelloInterno();
					nomiScript.remove(index);
					Controller.rimuoviScript(index);
					costruzionePannelloInterno(nomiScript);
				}
			});

		}
	}

	public void pulisciPannelloInterno() {
		Component[] componentList = panel.getComponents();
		for(Component c : componentList){					    
			panel.remove(c);					    
		}
		panel.revalidate();
		panel.repaint();
	}
}
