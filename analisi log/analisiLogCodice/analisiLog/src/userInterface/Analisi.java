package userInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controller.Controller;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import java.awt.Component;
import java.awt.Cursor;
import javax.swing.JButton;

public class Analisi extends JFrame {

	private JPanel contentPane;
	private static String titolo = "Analisi Log";
	private static Point p = null;
	private static JPanel panelCopertura;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Analisi frame = new Analisi(p);
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
	public Analisi(Point p) {
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

		JLabel esecuzioniLabel = new JLabel("Numero esecuzioni per ogni ramo contenuto nel log:");
		esecuzioniLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		esecuzioniLabel.setBounds(32, 144, 481, 31);
		contentPane.add(esecuzioniLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(32, 176, 829, 149);
		contentPane.add(scrollPane);

		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setFont(new Font("Arial Unicode MS", Font.PLAIN, 20));
		scrollPane.setViewportView(textArea);

		JLabel coperturaLabel = new JLabel("Percentuale di copertura dei rami del codice:");
		coperturaLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		coperturaLabel.setBounds(32, 352, 481, 31);
		contentPane.add(coperturaLabel);

		JScrollPane scrollFrameCopertura = new JScrollPane((Component) null);
		scrollFrameCopertura.setPreferredSize(new Dimension(360, 142));
		scrollFrameCopertura.setBounds(32, 384, 829, 142);
		contentPane.add(scrollFrameCopertura);

		panelCopertura = new JPanel();
		panelCopertura.setLayout(null);
		panelCopertura.setPreferredSize(new Dimension(367, 139));
		panelCopertura.setBackground(Color.WHITE);
		panelCopertura.setAutoscrolls(true);
		scrollFrameCopertura.setViewportView(panelCopertura);

		JButton indietroButton = new JButton("Indietro");
		indietroButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
		indietroButton.setBounds(373, 550, 154, 57);
		indietroButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		indietroButton.setToolTipText("Indietro");
		contentPane.add(indietroButton);

		textArea.setText(Controller.numeroEsecuzioniPerRamo());
		try {
			costruzionePannelloInterno(Controller.coperturaCodice());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		indietroButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainFrame mf = new MainFrame(getLocation(),false);
				mf.setVisible(true);
				dispose();
			}
		});

	}

	private void costruzionePannelloInterno(final HashMap<String,ArrayList<String>> mappaStatistiche) {

		int count= 0;
		for(String s : mappaStatistiche.keySet()) {

			if(11+45*count>140) {
				panelCopertura.setPreferredSize(new Dimension(367, 11+35+42*count));
			}

			String nomeScript = s.split(" ")[0];
			Double percentuale = Double.parseDouble(s.split(" ")[1].split("%")[0]);

			JLabel nomeScriptLabel = new JLabel(s);
			nomeScriptLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
			nomeScriptLabel.setBounds(10, 11+(42*count), 250, 25);
			if(percentuale < 31.0) {
				nomeScriptLabel.setForeground(Color.RED);
			}else if (percentuale >= 75.0) {
				nomeScriptLabel.setForeground(new Color(60, 179, 113));
			}else {
				nomeScriptLabel.setForeground(Color.ORANGE);
			}
			panelCopertura.add(nomeScriptLabel);

			if(mappaStatistiche.get(s).size()>0) {
				JButton visualizzaButton = new JButton("Rami non eseguiti");
				visualizzaButton.setBounds(610, 5+(42*count), 180, 35);
				visualizzaButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
				visualizzaButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				visualizzaButton.setToolTipText("Rami non eseguiti");
				panelCopertura.add(visualizzaButton);

				visualizzaButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						RamiNonEseguiti r = new RamiNonEseguiti(getLocation(),nomeScript,mappaStatistiche.get(s));
						r.setVisible(true);
						dispose();
					}
				});
			}
			count++;
		}
	}
}
