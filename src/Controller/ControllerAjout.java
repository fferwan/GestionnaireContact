package Controller;

import Model.Gestionnaire;
import Model.Contact;

import javax.swing.JPanel;
import javax.swing.JTextField;

import View.ContactsPanel;
import View.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerAjout implements ActionListener {

	private Gestionnaire gestionnaire;
	private JTextField nom, prenom, num;
	private Window window;
	
	public ControllerAjout(JTextField nom, JTextField prenom, JTextField num, Gestionnaire gestionnaire, Window window, JPanel contactsPanel){
		this.gestionnaire = gestionnaire;
		this.window = window;
		this.nom = nom;
		this.prenom = prenom;
		this.num = num;
		
		this.window.getAjouterButton().addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Contact nouveauContact = new Contact(this.nom.getText(), this.prenom.getText(), this.num.getText());
		this.gestionnaire.ajouter(nouveauContact);
	}
}
