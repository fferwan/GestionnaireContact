package ModelHibernate;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="utilisateurs")
public class Utilisateur {

	@Id
	@Column(name="id_utilisateurs")
	private int id_utilisateurs;
	
	@Column(name = "name")
	private String nom;
	
	@Transient
	private GestionnaireContacts gestionnaire;
	
	@OneToMany(mappedBy="utilisateur",cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Contact> contacts;
	
	public Utilisateur(String nom)
	{
		this.nom = nom;
	}
	
	public Utilisateur(String nom, List<Contact> contacts) {
		this.nom = nom;
		this.contacts=contacts;
	}
	
	public Utilisateur() {
		
	}
	
	public int getId()
	{
		return this.id_utilisateurs;
	}
	
	public void setId(int nvId)
	{
		this.id_utilisateurs = nvId;
	}

	public String getNom()
	{
		return this.nom;
	}
	
	public void setNom(String nvNom)
	{
		this.nom = nvNom;
	}

	public GestionnaireContacts getGestionnaire() {
		return gestionnaire;
	}

	public void setGestionnaire(GestionnaireContacts gestionnaire) {
		this.gestionnaire = gestionnaire;
	}

	public int getId_utilisateurs() {
		return id_utilisateurs;
	}

	public void setId_utilisateurs(int id_utilisateurs) {
		this.id_utilisateurs = id_utilisateurs;
	}

	@Override
	public String toString() {
		return this.nom;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}
}
