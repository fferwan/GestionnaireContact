package Main;

import View.Window;
import View.WindowAppareils;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JTextField;

import Controller.*;
import Model.Contact;
import Model.GestionnaireAppareil;
public class Main {

	public static void main(String[] args) {
		WindowAppareils windowAppareils = new WindowAppareils();
		
		//gestionnaire.setMesContacts(new ArrayList<Contact>());
		ControllerAjoutAppareil controllerAjoutAppareil = new ControllerAjoutAppareil(windowAppareils.getGestionnaire(), windowAppareils.getNomTextField(), windowAppareils.getAjouterButton());
		ControllerSupprAppareil controllerSupprAppareil = new ControllerSupprAppareil(windowAppareils.getGestionnaire(), windowAppareils.getAppareilsList(), windowAppareils.getSupprimerButton());
		ControllerSelectAppareil controllerModifAppareil = new ControllerSelectAppareil(windowAppareils.getGestionnaire(), windowAppareils.getNomTextField(), windowAppareils.getSelectionnerButton(), windowAppareils.getAppareilsList());
		windowAppareils.show();
	}
}