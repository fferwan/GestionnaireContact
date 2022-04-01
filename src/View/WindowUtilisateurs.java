package View;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
import Model.GestionnaireContacts;
import Model.Contact;
*/

import ModelHibernate.GestionnaireUtilisateurs;
import ModelHibernate.Utilisateur;

public class WindowUtilisateurs extends JFrame implements Observer{
	
	private static final long serialVersionUID = 0;
	private final static double screenWidth = Toolkit.getDefaultToolkit( ).getScreenSize().getWidth();
	private final static double screenHeight = Toolkit.getDefaultToolkit( ).getScreenSize().getHeight();
	private final static int windowWidth = 500;
	private final static int windowHeight = 600;

	private GridBagConstraints leftPanelConstraints;
	private GridBagConstraints contactsConstraints;
	
	private JPanel leftPanel;				//Left panel
	private JPanel contactsPanel;	//Right panel

	//Dans leftPanel
	private JButton ajouterButton;
	private JButton supprimerButton;
	private JButton selectionnerButton;
	private JTextField nomTextField;
	
	//Dans contactsPanel
	private JList appareilsList;
	private DefaultListModel model;
	
	private GestionnaireUtilisateurs gestionnaire;
	
	public WindowUtilisateurs(){
		this.gestionnaire = new GestionnaireUtilisateurs();
		
		//Init Components
		this.ajouterButton = new JButton("Ajouter");
		this.selectionnerButton = new JButton("Selectionner");
		this.supprimerButton = new JButton("Supprimer");
		this.nomTextField = new JTextField("Nom");
		
		//Init leftPanel
		leftPanel = new JPanel(new GridBagLayout());
		leftPanelConstraints = new GridBagConstraints();
		leftPanelConstraints.insets = new Insets(10,10,10,10);
		leftPanelConstraints.fill = GridBagConstraints.HORIZONTAL;
		leftPanelConstraints.weightx = 1;
		leftPanelConstraints.gridx = 0;
		leftPanelConstraints.gridy = 0;
		leftPanel.add(this.nomTextField, leftPanelConstraints);
		leftPanelConstraints.gridy = 1;
		leftPanel.add(this.ajouterButton, leftPanelConstraints);
		leftPanelConstraints.gridy = 2;
		leftPanel.add(this.supprimerButton, leftPanelConstraints);
		leftPanelConstraints.gridy = 3;
		leftPanel.add(this.selectionnerButton, leftPanelConstraints);
		
		//Init contactsPanel
		contactsPanel = new JPanel();
		this.setLayout(new GridBagLayout());
		contactsConstraints = new GridBagConstraints();
		contactsConstraints.fill = GridBagConstraints.HORIZONTAL;
		contactsConstraints.insets = new Insets(5,5,5,5);
		contactsConstraints.weightx = 1;
		contactsConstraints.gridx = 0;
		contactsConstraints.gridy = 0;
		model = new DefaultListModel();
		this.appareilsList = new JList(model);
		ajouterAppareils(gestionnaire.getMesUtilisateurs());
		contactsPanel.add(appareilsList);
		
		this.gestionnaire.addObserver(this);
		//Init this frame
		this.getContentPane().setLayout(new GridLayout(1, 2));
		this.add(leftPanel);
		this.add(contactsPanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		this.setTitle ("Utilisateur");
		this.setSize(windowWidth, windowHeight); 
		this.setLocation((int) screenWidth/2 - windowWidth/2, (int) screenHeight/2 - windowHeight/2);
	}
	
	public void ajouterAppareil(Utilisateur appareil) {
		System.out.println("Ajout d'un contact");
		model.addElement(appareil);
		appareilsList.updateUI();
	}

	public void ajouterAppareils(List<Utilisateur> appareils) {
		this.model.removeAllElements();
		for(int i = 0; i < appareils.size(); i++) {
			model.addElement(appareils.get(i));
		}
	}
	
	public void afficherAppareils() {
		this.model.removeAllElements();
		for(int i = 0; i < gestionnaire.getMesUtilisateurs().size(); i++) {
			model.addElement(gestionnaire.getMesUtilisateurs().get(i));
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		GestionnaireUtilisateurs g = (GestionnaireUtilisateurs) o;
		this.afficherAppareils();
	}

	public JButton getAjouterButton() {
		return ajouterButton;
	}

	public void setAjouterButton(JButton ajouterButton) {
		this.ajouterButton = ajouterButton;
	}

	public JButton getSupprimerButton() {
		return supprimerButton;
	}

	public void setSupprimerButton(JButton supprimerButton) {
		this.supprimerButton = supprimerButton;
	}

	public JButton getSelectionnerButton() {
		return selectionnerButton;
	}

	public void setSelectionnerButton(JButton selectionnerButton) {
		this.selectionnerButton = selectionnerButton;
	}

	public JTextField getNomTextField() {
		return nomTextField;
	}

	public void setNomTextField(JTextField nomTextField) {
		this.nomTextField = nomTextField;
	}

	public JList getAppareilsList() {
		return appareilsList;
	}

	public void setAppareilsList(JList appareilsList) {
		this.appareilsList = appareilsList;
	}

	public GestionnaireUtilisateurs getGestionnaire() {
		return gestionnaire;
	}

	public void setGestionnaire(GestionnaireUtilisateurs gestionnaire) {
		this.gestionnaire = gestionnaire;
	}
}
