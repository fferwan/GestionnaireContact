package Controller;

/*
import Model.GestionnaireContacts;
import Model.Contact;
*/

import ModelHibernate.GestionnaireContacts;
import ModelHibernate.Contact;
import View.*;
import javax.swing.*;
import View.Window;
import java.awt.event.*;

public class ControllerSuppr implements ActionListener{
	
	private GestionnaireContacts gestionnaire;
	private JButton supprimerButton;
	private JTextField nom;
	private JTextField prenom;
	private JTextField num;
	private JList listContacts;
	
	public ControllerSuppr(GestionnaireContacts gestionnaire, JTextField nom, JTextField prenom, JTextField num, JButton supprimerButton, JList listContacts){
		this.gestionnaire = gestionnaire;
		this.nom = nom;
		this.prenom = prenom;
		this.num = num;
		this.supprimerButton=supprimerButton;
		this.listContacts = listContacts;
		this.supprimerButton.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Bouton supprimer cliqu?");
		int indexList = this.listContacts.getSelectedIndex();
		this.gestionnaire.removeContact(indexList);
		gestionnaire.afficher();
	}
}