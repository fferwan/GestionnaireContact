package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;

import Model.GestionnaireContacts;
import Model.Contact;

public class ControllerModif implements ActionListener {
	
	private JButton modifierButton;
	private JTextField nom, prenom, numero;
	private GestionnaireContacts gestionnaire;
	private JList listContacts;
	
	public ControllerModif(GestionnaireContacts gestionnaire, JTextField nom, JTextField prenom, JTextField numero, JButton modifierButton, JList listContacts)
	{
		this.gestionnaire = gestionnaire;
		this.nom = nom;
		this.prenom = prenom;
		this.numero = numero;
		this.modifierButton = modifierButton;
		this.listContacts = listContacts;
		this.modifierButton.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("coucou");
		int index = this.listContacts.getSelectedIndex();
		System.out.println("index : " + index);
		Contact oldContact = gestionnaire.getMesContacts().get(index);
		System.out.println(oldContact.getNum());
		Contact newContact = new Contact(this.nom.getText(), this.prenom.getText(), this.numero.getText());
		System.out.println(newContact.getNum());
		this.gestionnaire.modifier(newContact, oldContact, index);
		System.out.println("on a modifié");
	}
}
