package Main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import View.*;
import Controller.*;

/*import Model.Contact;
import Model.GestionnaireUtilisateurs;*/

import ModelHibernate.*;

public class Main {
	
	public static void main(String[] args) {

		WindowUtilisateurs windowUtilisateurs = new WindowUtilisateurs();

		//gestionnaire.setMesContacts(new ArrayList<Contact>());
		ControllerAjoutUtilisateur controllerAjoutUtilsateur = new ControllerAjoutUtilisateur(windowUtilisateurs.getGestionnaire(), windowUtilisateurs.getNomTextField(), windowUtilisateurs.getAjouterButton());
		ControllerSupprUtilisateur controllerSupprUtilisateur = new ControllerSupprUtilisateur(windowUtilisateurs.getGestionnaire(), windowUtilisateurs.getAppareilsList(), windowUtilisateurs.getSupprimerButton());
		ControllerSelectUtilisateur controllerModifUtilisateur = new ControllerSelectUtilisateur(windowUtilisateurs.getGestionnaire(), windowUtilisateurs.getSelectionnerButton(), windowUtilisateurs.getAppareilsList());
		ControllerSelectModifUtilisateur controllerSelectModifUtilisateur = new ControllerSelectModifUtilisateur(windowUtilisateurs.getGestionnaire(), windowUtilisateurs.getNomTextField(), windowUtilisateurs.getAppareilsList());
		windowUtilisateurs.show();
	}

}