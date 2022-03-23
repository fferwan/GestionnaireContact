package View;


import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import Model.Contact;
import Model.Gestionnaire;

public class Window extends JFrame implements Observer{

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
	private JPanel choixPanel;
	private JButton ajouterButton;
	private JButton modifierButton;
	private JButton supprimerButton;
	private JTextField nomTextField;
	private JTextField prenomTextField;
	private JTextField numTextField;
	
	//Dans contactsPanel
	private JList contactsList;
	DefaultListModel model;
	
	private Gestionnaire gestionnaire;
	
	public Window(){

		this.gestionnaire = new Gestionnaire();
		
		//Init Components
		this.ajouterButton = new JButton("Ajouter");
		this.modifierButton = new JButton("Modifier");
		this.supprimerButton = new JButton("Supprimer");
		this.nomTextField = new JTextField("Nom");
		this.prenomTextField = new JTextField("Prenom");
		this.numTextField = new JTextField("Num");
		
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
		leftPanel.add(this.prenomTextField, leftPanelConstraints);
		leftPanelConstraints.gridy = 2;
		leftPanel.add(this.numTextField, leftPanelConstraints);
		leftPanelConstraints.gridy = 3;
		leftPanel.add(this.ajouterButton, leftPanelConstraints);
		leftPanelConstraints.gridy = 4;
		leftPanel.add(this.modifierButton, leftPanelConstraints);
		leftPanelConstraints.gridy = 5;
		leftPanel.add(this.supprimerButton, leftPanelConstraints);
		
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
		contactsList = new JList(model);
		ajouterContacts(gestionnaire.getMesContacts());
		contactsPanel.add(contactsList);
		
		this.gestionnaire.addObserver(this);
		//Init this frame
		this.getContentPane().setLayout(new GridLayout(1, 2));
		this.add(leftPanel);
		this.add(contactsPanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		this.setTitle ("Telephone");
		this.setSize(windowWidth, windowHeight); 
		this.setLocation((int) screenWidth/2 - windowWidth/2, (int) screenHeight/2 - windowHeight/2);
		
	}

	public void ajouterContact(Contact contact) {
		System.out.println("Ajout d'un contact");
		model.addElement(contact);
		contactsList.updateUI();
	}

	public void ajouterContacts(ArrayList<Contact> contacts) {
		this.model.removeAllElements();
		for(int i = 0; i < contacts.size(); i++) {
			model.addElement(contacts.get(i));
		}
	}
	
	public void afficherContacts() {
		this.model.removeAllElements();
		for(int i = 0; i < gestionnaire.getMesContacts().size(); i++) {
			model.addElement(gestionnaire.getMesContacts().get(i));
		}
	}
	
	public JPanel getLeftPanel() {
		return leftPanel;
	}

	public void setLeftPanel(JPanel leftPanel) {
		this.leftPanel = leftPanel;
	}

	public JPanel getContactsPanel() {
		return contactsPanel;
	}

	public void setContactsPanel(JPanel contactsPanel) {
		this.contactsPanel = contactsPanel;
	}

	public JPanel getChoixPanel() {
		return choixPanel;
	}

	public void setChoixPanel(JPanel choixPanel) {
		this.choixPanel = choixPanel;
	}

	public JButton getAjouterButton() {
		return ajouterButton;
	}

	public void setAjouterButton(JButton ajouterButton) {
		this.ajouterButton = ajouterButton;
	}

	public JTextField getNomTextField() {
		return nomTextField;
	}

	public void setNomTextField(JTextField nomTextField) {
		this.nomTextField = nomTextField;
	}

	public JTextField getPrenomTextField() {
		return prenomTextField;
	}

	public void setPrenomTextField(JTextField prenomTextField) {
		this.prenomTextField = prenomTextField;
	}

	public JTextField getNumTextField() {
		return numTextField;
	}

	public void setNumTextField(JTextField numTextField) {
		this.numTextField = numTextField;
	}

	public JList getContactsList() {
		return contactsList;
	}

	public void setContactsList(JList contactsList) {
		this.contactsList = contactsList;
	}

	public Gestionnaire getGestionnaire() {
		return gestionnaire;
	}

	public void setGestionnaire(Gestionnaire gestionnaire) {
		this.gestionnaire = gestionnaire;
	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.println("Le gestionnaire a subit des modifications");
		Gestionnaire g = (Gestionnaire) o;
		this.afficherContacts();
	}
}
