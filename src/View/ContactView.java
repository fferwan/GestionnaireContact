package View;

/*
import Model.GestionnaireContacts;
import Model.Contact;
*/

import ModelHibernate.GestionnaireContacts;
import ModelHibernate.Contact;
import View.Window;
import Controller.ControllerModif;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ContactView extends JPanel{

	private static final long serialVersionUID = -3566658283246849500L;
	protected JLabel nom;
	protected JLabel prenom;
	protected JLabel numTel;
	protected JButton supprimerButton;
	protected JButton modifierButton;
	protected GridBagConstraints grid;
	protected ControllerModif controllerModif;
	protected Contact contact;
	 
	ContactView(Contact contact){

		grid = new GridBagConstraints();
				
		this.controllerModif = controllerModif;
		this.contact = contact;
		this.nom = new JLabel(contact.getNom());
		this.prenom = new JLabel(contact.getPrenom());
		this.numTel = new JLabel(contact.getNum());
		this.supprimerButton = new JButton("Supprimer");
		this.modifierButton = new JButton("Modifier");
		setLayout(new GridBagLayout());
		grid.fill = GridBagConstraints.HORIZONTAL;
		grid.insets = new Insets(5,5,5,5);
		grid.weightx = 1;
		grid.gridx = 0;
		grid.gridy = 1;
		add(nom, grid);
		grid.gridy = 2;
		add(prenom, grid);
		grid.gridy = 3;
		add(numTel, grid);
		grid.gridy = 4;
		add(this.supprimerButton, grid);
		grid.gridx = 1;
		add(this.modifierButton, grid);
	}
	
	public JLabel getNom() {
		return nom;
	}
	public void setNom(JLabel nom) {
		this.nom = nom;
	}
	public JLabel getPrenom() {
		return prenom;
	}
	public void setPrenom(JLabel prenom) {
		this.prenom = prenom;
	}
	public JLabel getNumTel() {
		return numTel;
	}
	public void setNumTel(JLabel numTel) {
		this.numTel = numTel;
	}
	public GridBagConstraints getGrid() {
		return grid;
	}
	public void setGrid(GridBagConstraints grid) {
		this.grid = grid;
	}
}