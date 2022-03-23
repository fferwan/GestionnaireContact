package Model;

import java.util.*;

public class Gestionnaire extends Observable{
	
    SerializationAndDeseralization sr;
    ArrayList<Contact> mesContacts;
    
    public Gestionnaire() {
        this.mesContacts=new ArrayList<Contact>();
        sr=new SerializationAndDeseralization();
        this.mesContacts=sr.charger();//Deserialization des contacts 

    }
    
    public void ajouter(Contact contact) {  
        this.mesContacts.add(contact);
        this.setChanged();
        this.notifyObservers();
        try {
            sr.ecrireContact(mesContacts);//serialization des contacts 
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //sauvegarder les données ici
    }
    public void supprimer(Contact contact){
        this.mesContacts.remove(contact);
        this.setChanged();
        this.notifyObservers();
        try {
            sr.ecrireContact(mesContacts);//serialization des contacts 
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //sauvegarder les données ici
    }
    public void afficher(){
        for (int i=0; i<this.mesContacts.size() ; i++){
            System.out.println(mesContacts.get(i).afficher());
        }
    }
    
    public int rechercher (String num) {
    for (int i=0;i<this.mesContacts.size();i++)
        {
            if( this.mesContacts.get(i).getNum().equals(num)) {
                return i;
            }
        }
    	return -1;
    }
    
    public void modifier(Contact newcontact,Contact contactAmodifier) {
        for (int i=0;i<this.mesContacts.size();i++) {
            if( this.mesContacts.get(i).equals(contactAmodifier)) { 
                this.mesContacts.get(i).setNom(newcontact.getNom());
                this.mesContacts.get(i).setPrenom(newcontact.getPrenom());
                this.mesContacts.get(i).setNum(newcontact.getNum());
                this.setChanged();
                this.notifyObservers();
                try {
                    sr.ecrireContact(mesContacts);//serialization des contacts 
                } catch (ClassNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                //sauvegarder les données ici
            }
        }
    }
    
    public ArrayList<Contact> getMesContacts() {
        return mesContacts;
    }
    
    public void setMesContacts(ArrayList<Contact> mesContacts) {
        this.mesContacts = mesContacts; 
        this.setChanged();
        this.notifyObservers();
        try {
            sr.ecrireContact(mesContacts); //serialization des contacts 
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}