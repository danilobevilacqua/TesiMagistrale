package userInterface;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RamiNonEseguiti extends JFrame {

	private JPanel contentPane;
	private static String titolo = "Analisi Log";
	private static Point p = null;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RamiNonEseguiti frame = new RamiNonEseguiti(p,null,null);
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
	public RamiNonEseguiti(Point p, String nomeScript, ArrayList<String> listaRamiNonEseguiti) {
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
		
		JLabel descrizioneLabel = new JLabel("Rami non eseguiti di "+nomeScript+":");
		descrizioneLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
		descrizioneLabel.setBounds(49, 130, 800, 38);
		contentPane.add(descrizioneLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(49, 175, 802, 362);
		contentPane.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		textArea.setFont(new Font("Arial Unicode MS", Font.PLAIN, 20));
		
		JButton okButton = new JButton("Ok");
		okButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
		okButton.setBounds(396, 550, 108, 57);
		okButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		okButton.setToolTipText("Ok");
		contentPane.add(okButton);
		
		String rami="";
		for(String s : listaRamiNonEseguiti) {
			rami += s+"\n";
		}
		
		textArea.setText(rami);		
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Analisi a = new Analisi(getLocation());
				a.setVisible(true);
				dispose();
			}
		});
	}
}
