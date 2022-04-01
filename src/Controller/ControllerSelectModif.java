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
import ModelHibernate.Contact;

public class ControllerSelectModif implements MouseListener {
	
	private JTextField nom;
	private JTextField prenom;
	private JTextField numero;
	private GestionnaireContacts gestionnaire;
	private JList listContacts;
	
	public ControllerSelectModif(GestionnaireContacts gestionnaire, JTextField nom, JTextField prenom, JTextField numero, JList listContacts)
	{
		this.gestionnaire = gestionnaire;
		this.nom = nom;
		this.prenom = prenom;
		this.numero = numero;
		this.listContacts = listContacts;
		this.listContacts.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		int index = this.listContacts.getSelectedIndex();
		// Afficher dans les textFields les infos du contact
		Contact contact = gestionnaire.getMesContacts().get(index);
		this.nom.setText(contact.getNom());
		this.prenom.setText(contact.getPrenom());
		this.numero.setText(contact.getNum());
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
