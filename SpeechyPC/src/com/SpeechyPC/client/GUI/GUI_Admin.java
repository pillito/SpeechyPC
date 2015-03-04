package com.SpeechyPC.client.GUI;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.VerticalPanel;

public class GUI_Admin extends Composite{
	public GUI_Admin() {
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		initWidget(absolutePanel);
		absolutePanel.setSize("1200px", "584px");
		
		Label lblWelcomeAdmin = new Label("WELCOME AMMINISTRATORE");
		absolutePanel.add(lblWelcomeAdmin, 10, 21);
		lblWelcomeAdmin.setSize("384px", "38px");
		
		Button btnCreaCategoria = new Button("Crea Categoria");
		absolutePanel.add(btnCreaCategoria, 10, 93);
		
		Button btnCancellaCategoriaSelezionata = new Button("Cancella Categoria Selezionata");
		absolutePanel.add(btnCancellaCategoriaSelezionata, 196, 93);
		
		VerticalPanel verticalPanel = new VerticalPanel();
		absolutePanel.add(verticalPanel, 10, 144);
		verticalPanel.setSize("550px", "430px");
		
		VerticalPanel verticalPanel_1 = new VerticalPanel();
		absolutePanel.add(verticalPanel_1, 590, 144);
		verticalPanel_1.setSize("600px", "430px");
		
		Button btnCancellaCommenti = new Button("Cancella Commenti");
		absolutePanel.add(btnCancellaCommenti, 590, 93);
	}
}
