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
	private final String urlBdd = "jdbc:mysql://localhost:3306/bddcontacts3?autoReconnect=true&useSSL=false";
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
    
    public int getIdFromAppareil(Appareil appareil) {
    	String req = "SELECT appareils.id_appareils FROM appareils WHERE name = ?;";
    	int id = -1;
    	try {
	        //Préparation de la requête pour récupérer l'id de l'appareil utilisé 
	        this.preparedStatement = this.connection.prepareStatement(req);
			this.preparedStatement.setString(1, this.appareil.getNom());  
	        this.resultSet=this.preparedStatement.executeQuery();
	        resultSet.next();
	        id=resultSet.getInt("id_appareils");

		} catch (SQLException e) {
			System.out.println("Erreur à la récupération de l'id de l'appareil");
			e.printStackTrace();
		}
    	return id;
    }
    
    public int getIdFromContact(Contact contact) {
    	String req = "SELECT contacts.id_contacts FROM contacts "
    			+ "WHERE last_name = ? AND first_name = ? AND number = ?;";
    	int id = -1;
    	try {
	        //Préparation de la requête pour récupérer l'id de l'appareil utilisé 
	        this.preparedStatement = this.connection.prepareStatement(req);
			this.preparedStatement.setString(1, contact.getNom()); 
			System.out.println();
			this.preparedStatement.setString(2, contact.getPrenom());  
			this.preparedStatement.setString(3, contact.getNum()); 
			System.out.println(this.preparedStatement.toString());
	        this.resultSet=this.preparedStatement.executeQuery();
	        resultSet.next();
	        id=resultSet.getInt("id_contacts");
	        System.out.println("id trouvé : " + id);
		} catch (SQLException e) {
			System.out.println("Erreur à la récupération de l'id_contact");
			e.printStackTrace();
		}
    	return id;
    }
    
    public void ajouter(Contact contact) {
    	
    	int id;
        String req = "INSERT INTO Contacts(last_name, first_name, number,id_appareils) VALUES(?,?,?,?);";
    	//Ajout dans le modèle
        this.mesContacts.add(contact);
        
        //Ajout dans la BDD
		try {
			id=getIdFromAppareil(appareil);
			//Préparation de la requête d'ajout du contact
			this.preparedStatement = this.connection.prepareStatement(req);
			this.preparedStatement.setString(1, contact.getNom()); 
			this.preparedStatement.setString(2, contact.getPrenom()); 
	        this.preparedStatement.setString(3, contact.getNum()); 
	        this.preparedStatement.setInt(4, id); 
	        System.out.println(this.preparedStatement.toString());
	        this.preparedStatement.executeUpdate();
	        
		} catch (SQLException e) {
			System.out.println("Erreur à l'ajout dans contacts dans la base de données ...");
			e.printStackTrace();
		}
		
		this.setChanged();
        this.notifyObservers();
	}
    
    public void recupererContacts(){
      try {
            String requetePrep = "SELECT contacts.last_name, contacts.first_name, contacts.number FROM Contacts "
            +"INNER JOIN appareils ON appareils.id_appareils = contacts.id_appareils "
            +"WHERE appareils.name = ?;";
            this.preparedStatement = this.connection.prepareStatement(requetePrep);
            this.preparedStatement.setString(1, this.appareil.getNom()); 
            System.out.println(this.preparedStatement.toString());
            resultSet = this.preparedStatement.executeQuery();
            while (resultSet.next()) {
	            this.mesContacts.add(new Contact(resultSet.getString("last_name"),resultSet.getString("first_name"), resultSet.getString("number")));
	        }
        }
        catch(SQLException esql) {
            System.out.println("Erreur lors de la récupération des contacts depuis la base de données");
            System.out.println(esql);
        }
    }   

    public void supprimer(int index){

        try {
            String requetePrep = "DELETE FROM Contacts WHERE id_contacts = ?";
            //String num = this.mesContacts.get(index).getNum();
            int id=getIdFromContact(this.getMesContacts().get(index));
            this.preparedStatement = this.connection.prepareStatement(requetePrep);
            this.preparedStatement.setInt(1, id); 
            this.preparedStatement.executeUpdate();
        }
        catch(SQLException esql) {
            System.out.println("Erreur lors de la suppression du contact dans la base de données");
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
    
    public void modifier(Contact newContact, Contact contactAModifier, int index) {

    	String requete;
        try {
        	int id = this.getIdFromContact(contactAModifier);
            requete = "UPDATE contacts SET first_name = ?, last_name = ?, number = ? WHERE id_contacts = ?;";
            this.preparedStatement = this.connection.prepareStatement(requete);
            this.preparedStatement.setString(1, newContact.getPrenom());
            System.out.println(this.preparedStatement.toString());
            this.preparedStatement.setString(2, newContact.getNom());
            this.preparedStatement.setString(3, newContact.getNum());
            
            this.preparedStatement.setInt(4, id);
            System.out.println("modification !");
            this.preparedStatement.executeUpdate();
        }
        catch(SQLException esql) {
            System.out.println("Erreur lors de la modification du contact dans la base de données");
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