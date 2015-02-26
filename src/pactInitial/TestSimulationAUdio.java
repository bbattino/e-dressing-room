package pactInitial;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import fenetres.FenetreDepart;

public class TestSimulationAUdio extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private ArrayList<JButton> alphabet = new ArrayList<JButton>();
	private JPanel alphabetPanel = new JPanel();

	public TestSimulationAUdio() {
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setUndecorated(true);
		alphabetPanel.setLayout(new GridLayout(10,10));
		for (int i = 0; i < 60; i++) {
			alphabet.add(new JButton("" + i));
			alphabetPanel.add(alphabet.get(i));
		}

		this.setVisible(true);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(alphabetPanel, BorderLayout.CENTER);

		for (JButton bouton : alphabet) {
			bouton.addActionListener(this);
		}
	}

	public void actionPerformed(ActionEvent e) {
		JButton bouton = (JButton) e.getSource();
		int numero = Integer.parseInt(bouton.getText());
		Main.actionEventAudio(numero);

	}

	public static void main(String[] args) {
		new FenetreDepart();
		new TestSimulationAUdio();
	}

}
