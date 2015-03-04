package com.SpeechyPC.client;

import com.SpeechyPC.shared.Strutture.Utente;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.PopupPanel;

public class SpeechySingleton {

	private static SpeechySingleton istanza;
	//Create a remote service proxy to talk to the server-side Speechy service.
	private final SpeechServiceAsync speechService = GWT.create(SpeechService.class);
	
	private SpeechySingleton(){}
	
	// punto globale di accesso
	public static SpeechySingleton getSingletonIstance(){
		if(istanza == null){
			synchronized(SpeechySingleton.class){
				if(istanza == null){
					istanza = new SpeechySingleton();
				}
			}	
		}
		return istanza;
	}
	
	// richiamo il proxy per parlare con il lato server.
	public SpeechServiceAsync getSpeechService() {
		return speechService;
	}
	
	// qui inseriamo i metodi per inviare o estrarre dati dal server.
	
	public void registrazione(Utente utente){
		getSpeechService().registrazione(utente, new AsyncCallback<Boolean>() {
			@Override
			 public void onFailure(Throwable caught) {
				// Show the RPC error message to the user
			PopupPanel popup = new PopupPanel(true);
				popup.setWidget(new HTML("<font color='red'>Impossibile inserire una nuova registrazione: Errore nella connessione con il server. Per favore riprovare più tardi</font>"));
				popup.center();
			 }
			@Override
			public void onSuccess(Boolean result) {
				if(result == false){
					PopupPanel popup = new PopupPanel(true);
					popup.setWidget(new HTML("Registrazione non riuscita, cambiare username"));
					popup.center();
				 } else {
					 PopupPanel popup = new PopupPanel(true);
					 popup.setWidget(new HTML("<font color='green'size='5'>Benvenuto in Speechy Service!</font>"));
					 popup.center();
				 }	
			}
	     });
	}
	
	public void verificaLogin(){
		getSpeechService().verificaLogin( new AsyncCallback<String>(){

			@Override
			public void onFailure(Throwable caught) {
				PopupPanel popup = new PopupPanel(true);
				popup.setWidget(new HTML("<font color='red'>Errore nella connessione con il server</font>"));
				popup.center();	
			}

			@Override
			public void onSuccess(String result) {
				if(result == null){
					PopupPanel popup = new PopupPanel(true);
					popup.setWidget(new HTML("<font color='red'>L'utente non è loggato</font>"));
					popup.center();
				} else {
					// ottengo l' ID dell'utente
				}
			}
			
			
		});
	}
	
	public void login(String username, String password){
		getSpeechService().login(username, password, new AsyncCallback<Boolean>() {

			@Override
			public void onFailure(Throwable caught) {
				// Show the RPC error message to the user
				PopupPanel popup = new PopupPanel(true);
					popup.setWidget(new HTML("<font color='red'>Impossibile effetuare il login: Errore nella connessione con il server</font>"));
					popup.center();	 
			}
			@Override
			public void onSuccess(Boolean result) {
				if(result == false){
					PopupPanel popup = new PopupPanel(true);
					popup.setWidget(new HTML("Login non riuscito, l' utente non e' registrato"));
					popup.center();
				 } else {
					 PopupPanel popup = new PopupPanel(true);
					 popup.setWidget(new HTML("<font color='green'size='5'>Benvenuto in Speechy Service!</font>"));
					 popup.center();
				 }	
			}
			
		});
	}
	
	public void logout(){
		getSpeechService().logout( new AsyncCallback<Boolean>(){

			@Override
			public void onFailure(Throwable caught) {
				PopupPanel popup = new PopupPanel(true);
				popup.setWidget(new HTML("<font color='red'>Impossibile effettuare il logout: Errore nella connessione con il server</font>"));
				popup.center();
			}

			@Override
			public void onSuccess(Boolean result) {
				if(result == true){
					PopupPanel popup = new PopupPanel(true);
					popup.setWidget(new HTML("<font color='green'>Logout eseguito</font>"));
					popup.center();
				}
			}	
		});
	}
	
	
}
