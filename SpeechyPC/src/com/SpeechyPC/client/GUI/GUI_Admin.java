package com.SpeechyPC.client.GUI;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;

public class GUI_Admin extends Composite implements ClickHandler  {
	
	private DialogBox dialogNewCategory;
	private Button btnCreaCategoria;
	
	public GUI_Admin() {
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		initWidget(absolutePanel);
		absolutePanel.setSize("1200px", "584px");
		
		Label lblWelcomeAdmin = new Label("WELCOME AMMINISTRATORE");
		absolutePanel.add(lblWelcomeAdmin, 10, 21);
		lblWelcomeAdmin.setSize("384px", "38px");
		
		btnCreaCategoria = new Button("Crea Categoria");
		btnCreaCategoria.addClickHandler(this); 
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
		
		Button btnLogout = new Button("Logout");
		absolutePanel.add(btnLogout, 1108, 93);
	}

	@Override
	public void onClick(ClickEvent event) {
		// TODO Auto-generated method stub
		Button control = (Button)event.getSource();
		if(control == btnCreaCategoria){
			dialogNewCategory = new DialogBox();
			dialogNewCategory.setText("Speechy Service Nuova Categoria");
			dialogNewCategory.setAnimationEnabled(true);
			final Button close = new Button("Chiudi");
			close.setSize("70px", "25px");
			VerticalPanel dialogVPanel = new VerticalPanel();
			dialogVPanel.add(new GUI_NewCategoria()); // richiamo il pannello che gestisce la creazione di una nuova categoria.
			dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
			dialogVPanel.add(close);
			dialogNewCategory.setWidget(dialogVPanel);
			dialogNewCategory.center();
			close.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					dialogNewCategory.hide();
				}
			});
		}
	}
	
}
