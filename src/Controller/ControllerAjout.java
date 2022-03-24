package Controller;

import Model.GestionnaireContacts;
import Model.Contact;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerAjout implements ActionListener {

	private GestionnaireContacts gestionnaire;
	private JTextField nom, prenom, num;
	private JButton ajouterButton;
	
	public ControllerAjout(JTextField nom, JTextField prenom, JTextField num, GestionnaireContacts gestionnaire, JButton ajouterButton, JPanel contactsPanel){
		this.gestionnaire = gestionnaire;
		this.ajouterButton = ajouterButton;
		this.nom = nom;
		this.prenom = prenom;
		this.num = num;
		this.ajouterButton.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Contact nouveauContact = new Contact(this.nom.getText(), this.prenom.getText(), this.num.getText());
		this.gestionnaire.ajouter(nouveauContact);
	}
}