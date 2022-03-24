package Model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class GestionnaireContacts extends Observable{

    private ArrayList<Contact> mesContacts;
    private Appareil appareil;
	private final String urlBdd = "jdbc:mysql://localhost:3306/bddcontacts?autoReconnect=true&useSSL=false";
	private final String user = "root";
	private final String password ="root";
	private Connection connection;
	private Statement statement;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	
    public GestionnaireContacts() {
        this.mesContacts = new ArrayList<Contact>();

        //Connexion à la BDD
        try {
			connection = DriverManager.getConnection(urlBdd, user, password);
			statement = connection.createStatement();
			preparedStatement = connection.prepareStatement("");
        } catch (SQLException e) {
			System.out.println("Erreur de connexion à la base de données");
		}
    }
    
    public void ajouter(Contact contact) {
    	//Ajout dans le modèle
        this.mesContacts.add(contact);
        
        //Ajout dans la BDD
        String req = "INSERT INTO Contacts VALUES(?,?,?);";
		try {
			//Préparation de la requête
			this.preparedStatement = this.connection.prepareStatement(req);
			this.preparedStatement.setString(1, contact.getNom()); // Remplace le ? n°1
			this.preparedStatement.setString(2, contact.getPrenom()); // Remplace le ? n°2
	        this.preparedStatement.setString(3, contact.getNum()); // Remplace le ? n°3
	        
	        //Envoi de la requête et récupération des données dans resultSet
			this.preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		this.setChanged();
        this.notifyObservers();
	}
    
    public void recupererContacts(){
      try {
            String requetePrep = "SELECT * FROM Contacts ";
            requetePrep += "INNER JOIN appareils_contacts ON contacts.number = appareils_contacts.number ";
            requetePrep += "INNER JOIN appareils ON appareils_contacts.id_appareils = appareils.id_appareils ";
            requetePrep += "WHERE appareils.name = ?;";
            this.preparedStatement.setString(1, this.appareil.getNom()); 
            this.preparedStatement = this.connection.prepareStatement(requetePrep);
            resultSet = this.preparedStatement.executeQuery();
            while (resultSet.next()) {
	            this.mesContacts.add(new Contact(resultSet.getString("name"),resultSet.getString("first_name"), resultSet.getString("number")));
	        }
        }
        catch(SQLException esql) {
            System.out.println(esql);
        }
    }   
    
    public void supprimer(Contact contact){	//non utilisée (normalement)
        this.mesContacts.remove(contact);
        this.setChanged();
        this.notifyObservers();
        
    }
    
    public void supprimer(int index){

        //sauvegarder les données ici

        try {
            String requetePrep = "DELETE FROM Contacts WHERE number = ?";
            this.preparedStatement = this.connection.prepareStatement(requetePrep);
            String num = this.mesContacts.get(index).getNum();
            this.preparedStatement.setString(1, num); 
            this.preparedStatement.executeUpdate();
        }
        catch(SQLException esql) {
            System.out.println(esql);
        }
        this.mesContacts.remove(index);
        this.setChanged();
        this.notifyObservers();
        
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
                //sauvegarder les données ici
            }
        }
        this.setChanged();
        this.notifyObservers();
    }
    
    public void modifier(Contact newContact, Contact contactAModifier, int index) {

        try {
            String requetePrep = "UPDATE contacts SET last_name = ?, first_name = ?, number = ? where number = ?";
            this.preparedStatement = connection.prepareStatement(requetePrep);
            this.preparedStatement.setString(4, contactAModifier.getNum());
            this.preparedStatement.setString(1, newContact.getNom());
            this.preparedStatement.setString(2, newContact.getPrenom());
            this.preparedStatement.setString(3, newContact.getNum());
            this.preparedStatement.executeUpdate();
        }
        catch(SQLException esql) {
            System.out.println(esql);
        }
        
        this.mesContacts.get(index).setNom(newContact.getNom());
        this.mesContacts.get(index).setPrenom(newContact.getPrenom());
        this.mesContacts.get(index).setNum(newContact.getNum());
        
        this.setChanged();
        this.notifyObservers();
    }
    
    public ArrayList<Contact> getMesContacts() {
        return mesContacts;
    }
    
    public void setMesContacts(ArrayList<Contact> mesContacts) {
        this.mesContacts = mesContacts; 
        this.setChanged();
        this.notifyObservers();
    }

	public Appareil getAppareil() {
		return appareil;
	}

	public void setAppareil(Appareil appareil) {
		this.appareil = appareil;
	}
}