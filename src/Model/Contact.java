package Model;

import java.io.*;

public class Contact implements Serializable {
    private String prenom;
    private String nom;
    private String num;
    public Contact() {
    }
    public Contact(String nom,String prenom,String num) {
        this.nom=nom;
        this.prenom=prenom;
        this.num=num;
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
            return true;
        }
        else{
            return false;
        }
    }
    public String toString() {
        return this.nom+" "+this.prenom+" "+this.num;
    }
}
