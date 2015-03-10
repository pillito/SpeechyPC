package com.SpeechyPC.client.GUI;

import com.SpeechyPC.client.SpeechServiceAsync;
import com.SpeechyPC.shared.FieldVerifier;
import com.SpeechyPC.shared.Strutture.Categoria;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.SpeechyPC.client.SpeechService;

public class GUI_NewCategoria extends Composite implements ClickHandler {
	
	private TextBox tbNewCategory;
	private final SpeechServiceAsync speechService = GWT.create(SpeechService.class);
	private Label lblNomeCategoria, erLBNome, erLBCampiVuoti;
	private PushButton btnCreaCategoria;
	
	public GUI_NewCategoria() {
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		initWidget(absolutePanel);
		absolutePanel.setSize("318px", "190px");
		
		lblNomeCategoria = new Label("Nome Categoria");
		absolutePanel.add(lblNomeCategoria, 25, 59);
		
		tbNewCategory = new TextBox();
		// controllo il minimo di caratteri da inserire durante l'inserimento.
		tbNewCategory.addKeyPressHandler(new KeyPressHandler() {
					public void onKeyPress(KeyPressEvent event) {
						if(tbNewCategory.getText().length() < 3){
							erLBNome.setText("Per favore inserire almeno 4 caratteri");
							btnCreaCategoria.setEnabled(false);
						} else{
							erLBNome.setText("");
							btnCreaCategoria.setEnabled(true);
						}
					}
				});
		absolutePanel.add(tbNewCategory, 147, 56);
		tbNewCategory.setSize("131px", "18px");
		
		erLBNome = new Label("");
		erLBNome.setStyleName("gwt-Label- Error");
		
		btnCreaCategoria = new PushButton("Crea categoria");
		btnCreaCategoria.addClickHandler(this);
		absolutePanel.add(btnCreaCategoria, 179, 109);
	}


	@Override
	public void onClick(ClickEvent event) {
		// TODO Auto-generated method stub
		PushButton control = (PushButton)event.getSource();
	
		if(control == btnCreaCategoria){
			//controllo la presenza di campi vuoti
			if(!FieldVerifier.isValidName(tbNewCategory.getText())){
				erLBCampiVuoti.setText("E' obbligatorio riempire i campi (obbligatori) vuoti con l'adeguata lunghezza");
				btnCreaCategoria.setEnabled(false);
			}
			else{
				String nomeCategoria = tbNewCategory.getText();
				
				// invia dati al server
				btnCreaCategoria.setEnabled(false);
				Categoria categoria = new Categoria(nomeCategoria);
				speechService.categoria(categoria, new AsyncCallback<Boolean>() {
					@Override
					 public void onFailure(Throwable caught) {
						// Show the RPC error message to the user
					PopupPanel popup = new PopupPanel(true);
						popup.setWidget(new HTML("<font color='red'>Impossibile inserire una nuova categoria: Errore nella connessione con il server. Per favore riprovare pi� tardi</font>"));
						popup.center();
					 }
					@Override
					public void onSuccess(Boolean result) {
						if(result == false){
							PopupPanel popup = new PopupPanel(true);
							popup.setWidget(new HTML("Inserzione categoria non riuscita, cambiare nome"));
							popup.center();
						 } else {
							 PopupPanel popup = new PopupPanel(true);
							 popup.setWidget(new HTML("<font color='green'size='5'>La categoria si è inserita corretamente!</font>"));
							 popup.center();
						 }	
					}
			     });
			}	
		}
		
		
		
	}
}
