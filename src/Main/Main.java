package Main;

import View.Window;
import View.WindowUtilisateurs;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JTextField;

import Controller.*;
import Model.Contact;
import Model.GestionnaireUtilisateurs;
public class Main {

	static int a;
	public static void main(String[] args) {
		WindowUtilisateurs windowAppareils = new WindowUtilisateurs();

		//gestionnaire.setMesContacts(new ArrayList<Contact>());
		ControllerAjoutUtilisateur controllerAjoutAppareil = new ControllerAjoutUtilisateur(windowAppareils.getGestionnaire(), windowAppareils.getNomTextField(), windowAppareils.getAjouterButton());
		ControllerSupprUtilisateur controllerSupprAppareil = new ControllerSupprUtilisateur(windowAppareils.getGestionnaire(), windowAppareils.getAppareilsList(), windowAppareils.getSupprimerButton());
		ControllerSelectUtilisateur controllerModifAppareil = new ControllerSelectUtilisateur(windowAppareils.getGestionnaire(), windowAppareils.getSelectionnerButton(), windowAppareils.getAppareilsList());
		windowAppareils.show();
	}

}