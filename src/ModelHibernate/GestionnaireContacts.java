package ModelHibernate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class GestionnaireContacts extends Observable{

    private List<Contact> mesContacts;
    private Utilisateur utilisateur;
	private SFGest sfg;
	
    public GestionnaireContacts() {
        this.mesContacts = new ArrayList<Contact>();
    }
    
   public void recupContact(){
        try {
			Session session = sfg.getSF().getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery("FROM Contact WHERE id_utilisateurs = :utilisateur", Contact.class);
			query.setParameter("utilisateur", this.utilisateur.getId());
			mesContacts = query.getResultList();
			session.getTransaction().commit();
			session.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
   }
    
    public void addContact(String nom, String prenom, String numero){
        try {
			Session session = sfg.getSF().getCurrentSession();
			session.beginTransaction();
			System.out.println(utilisateur.toString());
			Contact contactAjouté = new Contact(nom, prenom, numero, utilisateur);
			session.save(contactAjouté);
			session.getTransaction().commit();
			session.close();
			this.setChanged();
	        this.notifyObservers();
		}catch(Exception e) {
			e.printStackTrace();
		}
    }

    public void removeContact(int indexList){
        Contact contact = this.mesContacts.get(indexList);
        try {
			Session session = sfg.getSF().getCurrentSession();
			session.beginTransaction();
			contact.setUtilisateur(null);
			session.remove(contact);
			session.getTransaction().commit();
			session.close();
			this.setChanged();
	        this.notifyObservers();
		}catch(Exception e) {
			e.printStackTrace();
		}
    }
    
    public void modifyContact(int indexList, String nom, String prenom, String num){
        Contact contact = this.mesContacts.get(indexList);
        contact.setNom(nom);
        contact.setPrenom(prenom);
        contact.setNum(num);
        try {
			Session session = sfg.getSF().getCurrentSession();
			session.beginTransaction();
			session.update(contact);
			session.getTransaction().commit();
			session.close();
			this.setChanged();
	        this.notifyObservers();
		}catch(Exception e) {
			e.printStackTrace();
		}
    }
    
    /*
    public void ajouterImage(File file){
		String requete;
		requete = "UPDATE Employees SET cv = ? WHERE first_name='David' AND last_name='Waters'";
		try {
			FileInputStream in = new FileInputStream(file);
			preparedStatement = connection.prepareStatement("");
			this.preparedStatement = this.connection.prepareStatement(requete);
			this.preparedStatement.setBinaryStream(1, in); // Remplace le ? n°1
			this.preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void recupererImage(String chemin, String condition){
		String requete;
		InputStream in;
		File file = new File(chemin);
		FileOutputStream out;

		System.out.println(requete);
		try {
			out = new FileOutputStream(file);
			this.resultSet = this.statement.executeQuery(requete);
			resultSet.next();
			in = this.resultSet.getBinaryStream("cv");
			byte[] cvTab = new byte[2048];
			while(in.read(cvTab)>0) {
				out.write(cvTab);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	*/
    
	public int rechercher (String num) {
	    for (int i=0;i<this.mesContacts.size();i++){
	            if( this.mesContacts.get(i).getNum().equals(num)) {
	                return i;
	            }
	       }
	    return -1;
	}
	    
    public void afficher(){
        for (int i=0; i<this.mesContacts.size() ; i++){
            System.out.println(mesContacts.get(i).afficher());
        }
    }
    
    /***************************************************************
     * Getters and setters
     **************************************************************/
    
    public List<Contact> getMesContacts() {
        return mesContacts;
    }
    
    public void setMesContacts(List<Contact> mesContacts) {
        this.mesContacts = mesContacts; 
        this.setChanged();
        this.notifyObservers();
    }

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	
	/*
    public int getIdFromUtilisateur(Utilisateur utilisateur) {
    	String req = "SELECT utilisateurs.id_utilisateurs FROM utilisateurs WHERE name = ?;";
    	int id = -1;
    	try {
	        //Préparation de la requête pour récupérer l'id de l'utilisateur utilisé 
	        this.preparedStatement = this.connection.prepareStatement(req);
			this.preparedStatement.setString(1, this.utilisateur.getNom());  
	        this.resultSet=this.preparedStatement.executeQuery();
	        resultSet.next();
	        id=resultSet.getInt("id_utilisateurs");

		} catch (SQLException e) {
			System.out.println("Erreur à la récupération de l'id de l'utilisateurs");
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
    */
	
}