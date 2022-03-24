package Controller;

import Model.GestionnaireAppareil;
import Model.Appareil;

import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerAjoutAppareil implements ActionListener {
	
	private JButton buttonAjout;
	private GestionnaireAppareil gestionnaireAppareil;
	private JTextField nomAppareil;
	
	public ControllerAjoutAppareil(GestionnaireAppareil gestionnaireAppareil, JTextField nomAppareil, JButton buttonAjout)
	{
		this.gestionnaireAppareil = gestionnaireAppareil;
		this.nomAppareil = nomAppareil;
		this.buttonAjout = buttonAjout;
		this.buttonAjout.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println(this.nomAppareil.getText());
		Appareil newAppareil = new Appareil(this.nomAppareil.getText());
		this.gestionnaireAppareil.ajouterAppareil(newAppareil);
	}
}
