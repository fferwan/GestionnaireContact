package Model;

public class Appareil {
	
	private String nom;
	private GestionnaireContacts gestionnaire;
	
	public Appareil(String nom)
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
