package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;

import Model.Gestionnaire;
import Model.Contact;
import View.Window;
import View.ContactView;

public class ControllerModif implements ActionListener {
	
	private JButton modifierButton;
	private JTextField nom, prenom, numero;
	private Gestionnaire gestionnaire;
	private JList listContacts;
	
	public ControllerModif(Gestionnaire gestionnaire, JTextField nom, JTextField prenom, JTextField numero, JButton modifierButton, JList listContacts)
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
		
		int index = this.listContacts.getSelectedIndex();
		Contact oldContact = gestionnaire.getMesContacts().get(index);
		Contact newContact = new Contact(this.nom.getText(), this.prenom.getText(), this.numero.getText());
		this.gestionnaire.modifier(newContact, oldContact, index);
	}
}
