package Controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTextField;
import javax.swing.JList;

/*
import Model.GestionnaireContacts;
import Model.Contact;
*/

import ModelHibernate.GestionnaireContacts;
import ModelHibernate.GestionnaireUtilisateurs;
import ModelHibernate.Utilisateur;
import ModelHibernate.Contact;

public class ControllerSelectModifUtilisateur implements MouseListener {
	
	private JTextField nom;
	private GestionnaireUtilisateurs gestionnaire;
	private JList listUtilisateurs;
	
	public ControllerSelectModifUtilisateur(GestionnaireUtilisateurs gestionnaire, JTextField nom, JList listUtilisateurs)
	{
		this.gestionnaire = gestionnaire;
		this.nom = nom;
		this.listUtilisateurs = listUtilisateurs;
		this.listUtilisateurs.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		int index = this.listUtilisateurs.getSelectedIndex();
		// Afficher dans les textFields les infos du contact
		Utilisateur utilisateur = gestionnaire.getMesUtilisateurs().get(index);
		this.nom.setText(utilisateur.getNom());
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {		
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

}
