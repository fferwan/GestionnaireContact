package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTextField;

import Model.GestionnaireContacts;
import Model.GestionnaireUtilisateurs;
import View.Window;

public class ControllerSelectUtilisateur implements ActionListener{

	private JButton selectionnerButton;
	private GestionnaireUtilisateurs gestionnaireUtilisateurs;
	private JList listUtilisateurs;
	
	public ControllerSelectUtilisateur(GestionnaireUtilisateurs gestionnaire, JButton selectionnerButton, JList listUtilisateurs) {
		this.gestionnaireUtilisateurs = gestionnaire;
		this.selectionnerButton = selectionnerButton;
		this.listUtilisateurs = listUtilisateurs;
		
		this.selectionnerButton.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		int index = this.listUtilisateurs.getSelectedIndex();
		GestionnaireContacts gestionnaireContacts = new GestionnaireContacts();
		gestionnaireContacts.setUtilisateur(this.gestionnaireUtilisateurs.getMesUtilisateurs().get(index));
		gestionnaireContacts.recupererContacts();
		this.gestionnaireUtilisateurs.getMesUtilisateurs().get(index).setGestionnaire(gestionnaireContacts);
		
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
