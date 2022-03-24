package Model;

import java.util.Observable;
import java.util.*;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GestionnaireUtilisateurs extends Observable{

	private ArrayList<Utilisateur> mesUtilisateurs;
	private String nom;
	private int id;
	private final String urlBdd = "jdbc:mysql://localhost:3306/bddcontacts3?autoReconnect=true&useSSL=false";
	private final String user = "root";
	private final String password ="root";
	private Connection connection;
	private Statement statement;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public GestionnaireUtilisateurs() {	 
		this.mesUtilisateurs = new ArrayList<Utilisateur>();

		try {
			connection = DriverManager.getConnection(urlBdd, user, password);
			statement = connection.createStatement();
			preparedStatement = connection.prepareStatement("");
			recupererUtilisateurs();
		} catch (SQLException e) {
			System.out.println("Erreur de connexion à la base de données");
		}
	}
	
	public GestionnaireUtilisateurs(int id,String nom) {	 
		this.id=id;
		this.nom=nom;
	}

	public void ajouterUtilisateur(Utilisateur utilisateur) {
		//Ajout dans le modèle
		this.mesUtilisateurs.add(utilisateur);

		//Ajout dans la BDD
		String req = "INSERT INTO utilisateurs(name) VALUES(?);";
		try {
			//Préparation de la requête
			this.preparedStatement = this.connection.prepareStatement(req);
			this.preparedStatement.setString(1, utilisateur.getNom()); // Remplace le ? n°2


			//Envoi de la requête et récupération des données dans resultSet
			this.preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Erreur lors de l'ajout de l'utilsateur");
			e.printStackTrace();
		}
		this.setChanged();
        this.notifyObservers();
	}
	
	public void recupererUtilisateurs(){
		try {
			String requetePrep = "SELECT * FROM utilisateurs;";
			this.preparedStatement = this.connection.prepareStatement(requetePrep);
			resultSet = this.preparedStatement.executeQuery();
			while (resultSet.next()) {
				this.mesUtilisateurs.add(new Utilisateur(resultSet.getString("name")));
			}
		}
		catch(SQLException esql) {
			System.out.println("Erreur lors de la récupération des utilisateurs dans la base");
			esql.printStackTrace();
		}
	} 
	
	public void supprimerUtilisateur(int index){

		//sauvegarder les données ici
		try {
			String requetePrep = "DELETE FROM utilisateurs WHERE name = ?";
			this.preparedStatement = this.connection.prepareStatement(requetePrep);
			String nom = this.mesUtilisateurs.get(index).getNom();
			this.preparedStatement.setString(1, nom);
			this.preparedStatement.executeUpdate();
		}
		catch(SQLException esql) {
			System.out.println("Erreur lors de la suppression de l'utilisateur dans la base");
			System.out.println(esql);
		}
		this.mesUtilisateurs.remove(index);
		this.setChanged();
		this.notifyObservers();

	}

	public ArrayList<Utilisateur> getMesUtilisateurs() {
		return mesUtilisateurs;
	}

	public void setMesUtilisateurs(ArrayList<Utilisateur> mesUtilisateurs) {
		this.mesUtilisateurs = mesUtilisateurs;
	}
}