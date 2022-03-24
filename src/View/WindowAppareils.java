package View;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Model.Gestionnaire;

public class WindowAppareils {
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
	this.contactsList = new JList(model);
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
