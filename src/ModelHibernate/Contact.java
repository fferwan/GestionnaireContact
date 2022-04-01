package ModelHibernate;

import java.io.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


@Entity
@Table(name="contacts")
public class Contact implements Serializable {
    
    @Id
    @Column(name="id_contacts")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idContacts;
    
    @Column(name="first_name")
    private String prenom;
    
    @Column(name="last_name")
    private String nom;
    
    @Column(name="number")
    private String num;
    
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="id_utilisateurs")
    private Utilisateur utilisateur;
    
    public Contact() {
    }
    
    public Contact(String nom,String prenom,String num, Utilisateur utilisateur) {
        this.nom=nom;
        this.prenom=prenom;
        this.num=num;
        this.utilisateur = utilisateur;
    }
    
    public boolean equals(Contact c) {
        if(this.nom.equals(c.getNom()) && this.num.equals(c.getNum()) && this.prenom.equals(c.getPrenom())) {
        	System.out.println("true");
            return true;
        }
        else{
        	System.out.println("false");
            return false;
        }
    }
    
    @Override
	public String toString() {
    	return nom + " " + prenom + " " + num;
    }
    
    // Getters and setters
    public String getNom() {
        return this.nom;
    }
    public void setNom(String nvnom) {
        this.nom= nvnom;
    }
    public String getNum() {
        return this.num;
    }
    public void setNum(String nvnum) {
        this.num= nvnum;
    }
    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public String afficher() {
        return this.nom+this.num+this.prenom;
    }
	public int getIdContacts() {
		return idContacts;
	}
	public void setIdContacts(int idContacts) {
		this.idContacts = idContacts;
	}
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
}
