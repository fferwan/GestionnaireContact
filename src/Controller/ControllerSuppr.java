package Controller;

import Model.Gestionnaire;
import Model.Contact;
import View.*;
import javax.swing.*;
import View.Window;
import java.awt.event.*;

public class ControllerSuppr implements ActionListener{
	
	private Gestionnaire gestionnaire;
	private JButton boutonSupprimer;
	private JLabel nom;
	private JLabel prenom;
	private JLabel num;
	private Window window;
	private ContactView contactView;
	
	public ControllerSuppr(Gestionnaire gestionnaire, Window window, JLabel nom, JLabel prenom, JLabel num, ContactView contactView, JButton bouton){
		this.gestionnaire = gestionnaire;
		this.window = window;
		this.nom = nom;
		this.prenom = prenom;
		this.num = num;
		this.contactView=contactView;
		this.boutonSupprimer=bouton;
	
		this.boutonSupprimer.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		int index = gestionnaire.rechercher(this.num.getText());
		Contact contact = gestionnaire.getMesContacts().get(index);
		this.gestionnaire.supprimer(contact);
	}
}