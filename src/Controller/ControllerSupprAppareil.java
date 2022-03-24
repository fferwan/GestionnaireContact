package Controller;

import Model.GestionnaireAppareil;
import Model.Appareil;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JList;

import View.ContactsPanel;
import View.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerSupprAppareil implements ActionListener {
	
	private JButton buttonSuppr;
	private GestionnaireAppareil gestionnaireAppareil;
	private JTextField nomAppareil;
	private JList listAppareil;
	
	public ControllerSupprAppareil(GestionnaireAppareil gestionnaireAppareil, JList listAppareil, JButton buttonSuppr)
	{
		this.gestionnaireAppareil = gestionnaireAppareil;
		this.buttonSuppr = buttonSuppr;
		this.listAppareil = listAppareil;
		
		this.buttonSuppr.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		int index = this.listAppareil.getSelectedIndex();
		this.gestionnaireAppareil.supprimerAppareil(index); //à modifier selon la méthode créée
		this.gestionnaireAppareil.recupererAppareils();
	}

}
