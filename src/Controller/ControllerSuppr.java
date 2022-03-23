package Controller;

import Model.Gestionnaire;
import Model.Contact;
import View.*;
import javax.swing.*;
import View.Window;
import java.awt.event.*;

public class ControllerSuppr implements ActionListener{
	
	private Gestionnaire gestionnaire;
	private JButton supprimerButton;
	private JTextField nom;
	private JTextField prenom;
	private JTextField num;
	private JList listContacts;
	
	public ControllerSuppr(Gestionnaire gestionnaire, JTextField nom, JTextField prenom, JTextField num, JButton supprimerButton, JList listContacts){
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
		System.out.println("Bouton supprimer cliqué");
		// TODO Auto-generated method stub
		int index = this.listContacts.getSelectedIndex();
		this.gestionnaire.supprimer(index);
		gestionnaire.afficher();
	}
}