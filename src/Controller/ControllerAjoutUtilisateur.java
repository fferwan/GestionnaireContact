package Controller;

/*
import Model.GestionnaireUtilisateurs;
import Model.Contact;
*/

import ModelHibernate.GestionnaireUtilisateurs;
import ModelHibernate.Contact;
import ModelHibernate.Utilisateur;

import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerAjoutUtilisateur implements ActionListener {
	
	private JButton buttonAjout;
	private GestionnaireUtilisateurs gestionnaireUtilisateur;
	private JTextField nomUtilisateur;
	
	public ControllerAjoutUtilisateur(GestionnaireUtilisateurs gestionnaireAppareil, JTextField nomUtilisateur, JButton buttonAjout)
	{
		this.gestionnaireUtilisateur = gestionnaireAppareil;
		this.nomUtilisateur = nomUtilisateur;
		this.buttonAjout = buttonAjout;
		this.buttonAjout.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println(this.nomUtilisateur.getText());
		Utilisateur newUtilisateur = new Utilisateur(this.nomUtilisateur.getText());
		this.gestionnaireUtilisateur.ajouterUtilisateur(newUtilisateur);
	}
}