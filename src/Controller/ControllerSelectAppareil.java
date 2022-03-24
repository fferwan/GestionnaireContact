package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTextField;

import Model.GestionnaireContacts;
import Model.GestionnaireAppareil;
import View.Window;

public class ControllerSelectAppareil implements ActionListener{

	private JButton selectionnerButton;
	private GestionnaireAppareil gestionnaireAppareils;
	private JList listAppareils;
	
	public ControllerSelectAppareil(GestionnaireAppareil gestionnaire, JButton selectionnerButton, JList listAppareils) {
		this.gestionnaireAppareils = gestionnaire;
		this.selectionnerButton = selectionnerButton;
		this.listAppareils = listAppareils;
		
		this.selectionnerButton.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		int index = this.listAppareils.getSelectedIndex();
		GestionnaireContacts gestionnaireContacts = new GestionnaireContacts();
		gestionnaireContacts.setAppareil(this.gestionnaireAppareils.getMesAppareils().get(index));
		gestionnaireContacts.recupererContacts();
		this.gestionnaireAppareils.getMesAppareils().get(index).setGestionnaire(gestionnaireContacts);
		
		creerWindow(gestionnaireContacts);
	}
	
	private void creerWindow(GestionnaireContacts gestionnaireContacts) {
		Window window = new Window(gestionnaireContacts);
		new ControllerAjout(window.getNomTextField(), window.getPrenomTextField(), window.getNumTextField(), window.getGestionnaire(), window.getAjouterButton(), window.getContactsPanel());
		new ControllerModif(window.getGestionnaire(),window.getNomTextField(), window.getPrenomTextField(), window.getNumTextField(),  window.getModifierButton(), window.getContactsList());
		new ControllerSuppr(window.getGestionnaire(),window.getNomTextField(), window.getPrenomTextField(), window.getNumTextField(),  window.getSupprimerButton(), window.getContactsList());
		new ControllerSelectModif(window.getGestionnaire(), window.getNomTextField(), window.getPrenomTextField(), window.getNumTextField(), window.getContactsList());
		window.show();
	}
}
