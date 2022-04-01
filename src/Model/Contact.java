package Model;

import java.io.*;

import javax.persistence.*;

@Entity
@Table(name="contacts")
public class Contact implements Serializable {
	
    @Id
	@Column(name="id_contacts")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
    
	@Column(name="first_name")
    private String prenom;
	
    @Column(name="last_name")
    private String nom;
    
    @Column(name="number")
    private String num;
    
    @ManyToOne(cascade=CascadeType.ALL)//eager par defaut
	@JoinColumn(name="id_utilisateurs")
    private Utilisateur utilisateur;
    
    public Contact() {
    }
    public Contact(String nom,String prenom,String num) {
        this.nom=nom;
        this.prenom=prenom;
        this.num=num;
    }
     public Contact(String nom,String prenom,String num, Utilisateur utilisateur) {
        this.nom=nom;
        this.prenom=prenom;
        this.num=num;
        this.utilisateur=utilisateur;
    }
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
    public Utilisateur getUtilisateur(){
        return this.utilisateur;
    }
    public void setUtilisateur(Utilisateur utilisateur){
        this.utilisateur=utilisateur;
    }
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
    public String toString() {
        return this.nom+" "+this.prenom+" "+this.num;
    }
}
