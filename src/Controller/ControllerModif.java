package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;

import Model.Gestionnaire;
import Model.Contact;
import View.Window;
import View.ContactView;

public class ControllerModif implements ActionListener {
	
	private JButton modifierButton;
	private JTextField nom, prenom, numero;
	private Gestionnaire gestionnaire;
	private ContactView contactView;
	
	public ControllerModif(Gestionnaire gestionnaire, JTextField nom, JTextField prenom, JTextField numero, JButton modifierButton, ContactView contactView)
	{
		this.gestionnaire = gestionnaire;
		this.nom = nom;
		this.prenom = prenom;
		this.numero = numero;
		this.modifierButton = modifierButton;
		this.contactView = contactView;
		
		this.modifierButton.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		this.contactView.getNom().getText();
		int index = gestionnaire.rechercher(this.contactView.getNumTel().getText());
		Contact oldContact = gestionnaire.getMesContacts().get(index);
		Contact newContact = new Contact(this.nom.getText(), this.prenom.getText(), this.numero.getText());
		this.gestionnaire.modifier(oldContact, newContact);
	}
}
