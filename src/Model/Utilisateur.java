package Model;

import java.io.*;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="utilisateurs")
public class Utilisateur {
	
	@Id
	@Column(name="id_utilisateurs")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="name")
	private String nom;
	
	@OneToMany(mappedBy="utilisateur", cascade=CascadeType.ALL)
	private List<Contact> contacts;
	
	@Transient
	private GestionnaireContacts gestionnaire;
	
	public Utilisateur(){
    
	}
	
	public Utilisateur(String nom)
	{
		this.nom = nom;
	}

	public String getNom()
	{
		return this.nom;
	}
	
	public void setNom(String nvNom)
	{
		this.nom = nvNom;
	}
	public String toString() {
		return nom;
	}

	public GestionnaireContacts getGestionnaire() {
		return gestionnaire;
	}

	public void setGestionnaire(GestionnaireContacts gestionnaire) {
		this.gestionnaire = gestionnaire;
	}
}
