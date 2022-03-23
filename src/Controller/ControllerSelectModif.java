package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JList;

import Model.Contact;
import Model.Gestionnaire;

public class ControllerSelectModif implements MouseListener {
	
	private JTextField nom;
	private JTextField prenom;
	private JTextField numero;
	private Gestionnaire gestionnaire;
	private JList listContacts;
	
	public ControllerSelectModif(Gestionnaire gestionnaire, JTextField nom, JTextField prenom, JTextField numero, JList listContacts)
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
