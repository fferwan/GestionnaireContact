package Model;

public class Utilisateur {
	
	private String nom;
	private GestionnaireContacts gestionnaire;
	
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
