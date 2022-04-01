package ModelHibernate;

import java.util.*;

import org.hibernate.Session;
import org.hibernate.query.Query;

public class GestionnaireUtilisateurs extends Observable{

	private List<Utilisateur> mesUtilisateurs;
	private String nom;
	private int id;
	private Session s;
	
	public GestionnaireUtilisateurs() {
		this.mesUtilisateurs = new ArrayList<Utilisateur>();
		recupererUtilisateurs();
	}
	
	public void recupererUtilisateurs(){
		try {
			this.s = SFGest.getSF().getCurrentSession();
			this.s.beginTransaction();
			Query<Utilisateur> query = this.s.createQuery("FROM Utilisateur", Utilisateur.class);
			this.mesUtilisateurs = query.getResultList();
			this.s.getTransaction().commit();
			this.s.close();
		}
		catch(Exception e) {
			System.out.println("Erreur lors de la récupération des utilisateurs dans la base");
			e.printStackTrace();
		}
	} 
	
	public void ajouterUtilisateur(Utilisateur utilisateur) {
		//Ajout dans le modèle
		this.mesUtilisateurs.add(utilisateur);

		//Ajout dans la BDD
		
		try {
			this.s = SFGest.getSF().getCurrentSession();
			this.s.beginTransaction();
			this.s.save(utilisateur);			this.s.getTransaction().commit();
			this.s.close();
		} catch (Exception e) { 
			System.out.println("Erreur lors de l'ajout de l'utilsateur");
			e.printStackTrace();
		}
		this.setChanged();
        this.notifyObservers();
	}
	
	public void supprimerUtilisateur(int index){

		//sauvegarder les données ici
					
		try {
			this.s = SFGest.getSF().getCurrentSession();
			this.s.beginTransaction();
			Utilisateur utilisateur = this.mesUtilisateurs.get(index);
			this.s.remove(utilisateur);
			this.s.getTransaction().commit();
			this.s.close();
		}
		catch(Exception e) {
			System.out.println("Erreur lors de la suppression de l'utilisateur dans la base");
			System.out.println(e);
		}
		this.mesUtilisateurs.remove(index);
		this.setChanged();
		this.notifyObservers();

	}

    /***************************************************************
     * Getters and setters
     **************************************************************/

	public List<Utilisateur> getMesUtilisateurs() {
		return mesUtilisateurs;
	}

	public void setMesUtilisateurs(List<Utilisateur> mesUtilisateurs) {
		this.mesUtilisateurs = mesUtilisateurs;
	}
	
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
}