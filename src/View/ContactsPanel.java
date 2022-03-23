package View;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import Model.Contact;
import Model.Gestionnaire;
import Controller.ControllerModif;

public class ContactsPanel extends JPanel implements Observer{

	private ArrayList<ContactView> contactViews;
	private GridBagConstraints constraints;
	private Gestionnaire gestionnaire;

	public ContactsPanel() {
		this.contactViews = new ArrayList<ContactView>();
		this.setLayout(new GridBagLayout());
		constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.insets = new Insets(5,5,5,5);
		constraints.weightx = 1;
		constraints.gridx = 0;
		constraints.gridy = 0;
		this.gestionnaire = gestionnaire;
		//this.gestionnaire.addObserver(this);
	}
	
	public void ajouterContact(Contact contact) {
		//ControllerModif controlleurModif = new ControllerModif();
		this.contactViews.add(new ContactView(contact));
		afficherContacts();
	}

	public void ajouterContacts(ArrayList<Contact> contacts) {
		for(int i = 0; i < contacts.size(); i++) {
			this.contactViews.add(new ContactView(contacts.get(i)));
		}
		afficherContacts();
	}
	
	private void afficherContacts() {
		this.removeAll();
		for(int i = 0; i < contactViews.size(); i++) {
			constraints.gridy = i;
			System.out.println(constraints.gridy);
			this.add(contactViews.get(i), constraints);
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.println("Le gestionnaire a subit des modifications");
		Gestionnaire g = (Gestionnaire) o;
		ajouterContacts(g.getMesContacts());
		this.afficherContacts();
	}
}