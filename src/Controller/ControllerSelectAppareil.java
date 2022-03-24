package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTextField;

import Model.Contact;
import Model.GestionnaireContacts;
import Model.GestionnaireAppareil;
import View.Window;

public class ControllerSelectAppareil implements ActionListener{

	private JButton selectionnerButton;
	private JTextField nom;
	private GestionnaireAppareil gestionnaireAppareils;
	private JList listAppareils;
	
	public ControllerSelectAppareil(GestionnaireAppareil gestionnaire, JTextField nom, JButton selectionnerButton, JList listAppareils) {
		this.gestionnaireAppareils = gestionnaire;
		this.nom = nom;
		this.selectionnerButton = selectionnerButton;
		this.listAppareils = listAppareils;
		
		selectionnerButton.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		int index = this.listAppareils.getSelectedIndex();
		System.out.println(this.gestionnaireAppareils);
		GestionnaireContacts gestionnaireContacts = new GestionnaireContacts();
		gestionnaireContacts.setAppareil(this.gestionnaireAppareils.getMesAppareils().get(index));
		gestionnaireContacts.recupererContacts();
		this.gestionnaireAppareils.getMesAppareils().get(index).setGestionnaire(gestionnaireContacts);
		
		Window window = new Window(gestionnaireContacts);
		ControllerAjout controllerAjout = new ControllerAjout(window.getNomTextField(), window.getPrenomTextField(), window.getNumTextField(), window.getGestionnaire(), window.getAjouterButton(), window.getContactsPanel());
		ControllerModif controllerModif = new ControllerModif(window.getGestionnaire(),window.getNomTextField(), window.getPrenomTextField(), window.getNumTextField(),  window.getModifierButton(), window.getContactsList());
		ControllerSuppr controllerSuppr = new ControllerSuppr(window.getGestionnaire(),window.getNomTextField(), window.getPrenomTextField(), window.getNumTextField(),  window.getSupprimerButton(), window.getContactsList());
		ControllerSelectModif controllerSelectModif = new ControllerSelectModif(window.getGestionnaire(), window.getNomTextField(), window.getPrenomTextField(), window.getNumTextField(), window.getContactsList());
		window.show();
	}
}
