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

public class GestionnaireAppareil extends Observable{

	private ArrayList<Appareil> mesAppareils;
	private String nom;
	private int id;
	private final String urlBdd = "jdbc:mysql://localhost:3306/bddcontacts?autoReconnect=true&useSSL=false";
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
	
	public GestionnaireAppareil() {	 
		this.mesAppareils = new ArrayList<Appareil>();

		try {
			connection = DriverManager.getConnection(urlBdd, user, password);
			statement = connection.createStatement();
			preparedStatement = connection.prepareStatement("");
			recupererAppareils();
		} catch (SQLException e) {
			System.out.println("Erreur de connexion à la base de données");
		}
	}
	
	public GestionnaireAppareil(int id,String nom) {	 
		this.id=id;
		this.nom=nom;
	}

	public void ajouterAppareil(Appareil appareil) {
		//Ajout dans le modèle
		this.mesAppareils.add(appareil);

		//Ajout dans la BDD
		String req = "INSERT INTO appareils(name) VALUES(?);";
		try {
			//Préparation de la requête
			this.preparedStatement = this.connection.prepareStatement(req);
			this.preparedStatement.setString(1, appareil.getNom()); // Remplace le ? n°2


			//Envoi de la requête et récupération des données dans resultSet
			this.preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.setChanged();
        this.notifyObservers();
	}
	
	public void recupererAppareils(){
		try {
			String requetePrep = "SELECT * FROM appareils;";
			this.preparedStatement = this.connection.prepareStatement(requetePrep);
			resultSet = this.preparedStatement.executeQuery();
			while (resultSet.next()) {
				this.mesAppareils.add(new Appareil(resultSet.getString("name")));
			}
		}
		catch(SQLException esql) {
			System.out.println(esql);
		}
	} 
	
	public void supprimerAppareil(int index){

		//sauvegarder les données ici
		try {
			String requetePrep = "DELETE FROM appareils WHERE name = ?";
			this.preparedStatement = this.connection.prepareStatement(requetePrep);
			String nom = this.mesAppareils.get(index).getNom();
			this.preparedStatement.setString(1, nom);
			this.preparedStatement.executeUpdate();
		}
		catch(SQLException esql) {
			System.out.println(esql);
		}
		this.mesAppareils.remove(index);
		this.setChanged();
		this.notifyObservers();

	}

	public ArrayList<Appareil> getMesAppareils() {
		return mesAppareils;
	}

	public void setMesAppareils(ArrayList<Appareil> mesAppareils) {
		this.mesAppareils = mesAppareils;
	}
}