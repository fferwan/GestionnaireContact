package Main;

import View.Window;

import java.util.ArrayList;

import Controller.*;
import Model.Contact;
import Model.Gestionnaire;
public class Main {

	public static void main(String[] args) {
		Window window = new Window();
		
		//gestionnaire.setMesContacts(new ArrayList<Contact>());
		ControllerAjout controllerAjout = new ControllerAjout(window.getNomTextField(), window.getPrenomTextField(), window.getNumTextField(), window.getGestionnaire(), window, window.getContactsPanel());
		window.show();
	}
}