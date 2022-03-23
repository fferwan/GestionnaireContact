package Model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.*;
import java.util.ArrayList;

public class SerializationAndDeseralization {

	private final String CHEMIN = System.getProperty("user.dir");
	private final String DESTINATION = "\\contacts.dat";
	public void ecrireContact(ArrayList<Contact> contact) throws ClassNotFoundException {
		try {
			ecrireDestination(contact);
		}
		catch(IOException exception) {
			System.out.println("Exception: "+exception);
		}
	}
	public ArrayList<Contact> charger() {
		ObjectInputStream in = null;//deserialiser
		ArrayList<Contact> liste = new ArrayList<Contact>();
		try {
			in = new ObjectInputStream(new FileInputStream(CHEMIN + DESTINATION));

			Object objet;
			while ((objet = in.readObject()) != null) {
				Contact c = (Contact) objet;//
				liste.add(c);

			}
			in.close();
		}
		catch(FileNotFoundException e) {
			System.out.println("Impossible de créer / Ecrire dans le fichier de sauvegarde " + CHEMIN + DESTINATION);
		}
		catch(IOException e) {
			System.out.println(e.getStackTrace()+e.getMessage()+e.getSuppressed());
		}
		catch(Exception e) {
			System.out.println(e);
		}
		System.out.println("Lecture des objets dans :\t" + CHEMIN + DESTINATION);
		return liste;
	}
	private void ecrireDestination(ArrayList<Contact> c) throws IOException{
		ObjectOutputStream out=null;
		int n=c.size();
		System.out.println(n);
		try {
			out=new ObjectOutputStream(new FileOutputStream(CHEMIN+DESTINATION));
			for (int i=0; i<n;i++) {
				out.writeObject(c.get(i));
			}
		}
		catch(IOException exception) {
			System.out.println("Exception: "+exception);
		}
		finally {
			if(out!=null) {
				out.close();
			}
		}
		
		System.out.println("Ecriture de "+n+" objets dans:\t"+CHEMIN+DESTINATION);
	}



}
