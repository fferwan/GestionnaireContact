package Controller;

/*
import Model.GestionnaireUtilisateurs;
import Model.Utilisateurs;
*/

import ModelHibernate.GestionnaireUtilisateurs;
import ModelHibernate.Utilisateur;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JList;

import View.ContactsPanel;
import View.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerSupprUtilisateur implements ActionListener {
	
	private JButton buttonSuppr;
	private GestionnaireUtilisateurs gestionnaireUtilisateurs;
	private JTextField nomUtilisateur;
	private JList listAppareil;
	
	public ControllerSupprUtilisateur(GestionnaireUtilisateurs gestionnaireAppareil, JList listAppareil, JButton buttonSuppr)
	{
		this.gestionnaireUtilisateurs = gestionnaireAppareil;
		this.buttonSuppr = buttonSuppr;
		this.listAppareil = listAppareil;
		
		this.buttonSuppr.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		int index = this.listAppareil.getSelectedIndex();
		this.gestionnaireUtilisateurs.supprimerUtilisateur(index); //à modifier selon la méthode créée
		this.gestionnaireUtilisateurs.recupererUtilisateurs();
	}

}
