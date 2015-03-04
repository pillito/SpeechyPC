package com.SpeechyPC.client.GUI;

import com.SpeechyPC.client.SpeechySingleton;
import com.SpeechyPC.shared.FieldVerifier;
import com.SpeechyPC.shared.Strutture.Utente;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.datepicker.client.DateBox.DefaultFormat;
import com.google.gwt.i18n.client.DateTimeFormat;
import java.util.Date;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;

public class GUI_Registrazione extends Composite implements ClickHandler {
	
	private TextBox tBNome, tBLuogo, tBUsername, tBEmail;
	private Label erLBNome, erLBLuogo, erLBUsername, erLBPassword, erLBEmail, erLBCampiVuoti;
	private DateBox dateBox;
	private PasswordTextBox passwordTextBox;
	private ListBox sexBox;
	private PushButton button_Registra;
	private String sesso_Scelto;
	
	public GUI_Registrazione() {
		
		VerticalPanel verticalPanel = new VerticalPanel();
		verticalPanel.setSpacing(5);
		initWidget(verticalPanel); // lego il pannello al Composite
		
		Grid grid = new Grid(13, 2);
		grid.setCellSpacing(5);
		verticalPanel.add(grid);
		grid.setSize("100%", "34px");
		
		Label lbNome = new Label("Nome");
		grid.setWidget(0, 0, lbNome);
		
		tBNome = new TextBox();
		// controllo il minimo di caratteri da inserire durante l'inserimento.
		tBNome.addKeyPressHandler(new KeyPressHandler() {
			public void onKeyPress(KeyPressEvent event) {
				if(tBNome.getText().length() < 3){
					erLBNome.setText("Per favore inserire almeno 4 caratteri");
					button_Registra.setEnabled(false);
				} else{
					erLBNome.setText("");
					button_Registra.setEnabled(true);
				}
			}
		});
		grid.setWidget(0, 1, tBNome);
		tBNome.setSize("250px", "15px");
		
		erLBNome = new Label("");
		erLBNome.setStyleName("gwt-Label- Error");
		grid.setWidget(1, 1, erLBNome);
		
		Label lbLuogo = new Label("Luogo di nascita");
		grid.setWidget(2, 0, lbLuogo);
		
		tBLuogo = new TextBox();
		// controllo il minimo di caratteri da inserire durante l'inserimento.
		tBLuogo.addKeyPressHandler(new KeyPressHandler() {
			public void onKeyPress(KeyPressEvent event) {
				if(tBLuogo.getText().length() < 3){
					erLBLuogo.setText("Per favore inserire almeno 4 caratteri");
					button_Registra.setEnabled(false);
				} else{
					erLBLuogo.setText("");
					button_Registra.setEnabled(true);
				}
			}
		});
		grid.setWidget(2, 1, tBLuogo);
		tBLuogo.setSize("250px", "15px");
		
		erLBLuogo = new Label("");
		erLBLuogo.setStyleName("gwt-Label- Error");
		grid.setWidget(3, 1, erLBLuogo);
		
		Label lbData = new Label("Data di nascita");
		grid.setWidget(4, 0, lbData);
		
		dateBox = new DateBox();
		dateBox.setValue(new Date());
		dateBox.setFormat(new DefaultFormat(DateTimeFormat.getFormat("dd/MM/yyyy")));
		dateBox.setFocus(false);
		grid.setWidget(4, 1, dateBox);
		dateBox.setHeight("15px");
		
		Label lbSesso = new Label("Sesso");
		grid.setWidget(5, 0, lbSesso);
		
		sexBox = new ListBox();
		//sesso_Scelto = sexBox.getValue(sexBox.getSelectedIndex());
		sexBox.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				int indice = sexBox.getSelectedIndex();
				sesso_Scelto = sexBox.getValue(indice);
			}
		});
		sexBox.addItem("Femmina");
		sexBox.addItem("Maschio");
		sexBox.setSelectedIndex(0);// imposto valore di default
		grid.setWidget(5, 1, sexBox);
		
		Label lbUsername = new Label("Username");
		grid.setWidget(7, 0, lbUsername);
		
		tBUsername = new TextBox();
		// controllo il minimo di caratteri da inserire durante l'inserimento.
		tBUsername.addKeyPressHandler(new KeyPressHandler() {
			public void onKeyPress(KeyPressEvent event) {
				if(tBUsername.getText().length()< 3){
					erLBUsername.setText("Per favore inserire almeno 4 caratteri");
					button_Registra.setEnabled(false);
				} else{
					erLBUsername.setText("");
					button_Registra.setEnabled(true);
				}
				
			}
		});
		grid.setWidget(7, 1, tBUsername);
		tBUsername.setSize("250px", "15px");
		
		erLBUsername = new Label("");
		erLBUsername.setStyleName("gwt-Label- Error");
		grid.setWidget(8, 1, erLBUsername);
		
		Label lbPassword = new Label("Passoword");
		grid.setWidget(9, 0, lbPassword);
		
		passwordTextBox = new PasswordTextBox();
		// controllo il minimo di caratteri da inserire durante l'inserimento.
		passwordTextBox.addKeyPressHandler(new KeyPressHandler() {
			public void onKeyPress(KeyPressEvent event) {
				if(passwordTextBox.getText().length() < 8){
					erLBPassword.setText("Per favore inserire almeno 8 caratteri");
					button_Registra.setEnabled(false);
				} else{
					erLBPassword.setText("");
					button_Registra.setEnabled(true);
				}
			}
		});
		grid.setWidget(9, 1, passwordTextBox);
		passwordTextBox.setSize("250px", "15px");
		
		erLBPassword = new Label("");
		erLBPassword.setStyleName("gwt-Label- Error");
		grid.setWidget(10, 1, erLBPassword);
		
		Label lbEmail = new Label(" Email (opzionale)");
		grid.setWidget(11, 0, lbEmail);
		
		tBEmail = new TextBox();
		// controllo il minimo di caratteri da inserire durante l'inserimento.
		tBEmail.addKeyPressHandler(new KeyPressHandler() {
			public void onKeyPress(KeyPressEvent event) {
				if(tBEmail.getText().length() < 6  ){
					erLBEmail.setText("Per favore inserire almeno 4 caratteri");
					button_Registra.setEnabled(false);
				} else {
					erLBEmail.setText("");
					button_Registra.setEnabled(true);
				}
			}
		});
		grid.setWidget(11, 1, tBEmail);
		tBEmail.setSize("250px", "15px");
		grid.getCellFormatter().setVerticalAlignment(1, 1, HasVerticalAlignment.ALIGN_MIDDLE);
		grid.getCellFormatter().setHorizontalAlignment(1, 1, HasHorizontalAlignment.ALIGN_LEFT);
		
		erLBEmail = new Label("");
		erLBEmail.setStyleName("gwt-Label- Error");
		grid.setWidget(12, 1, erLBEmail);
		
		erLBCampiVuoti = new Label("");
		erLBCampiVuoti.setStyleName("gwt-Label- Error");
		verticalPanel.add(erLBCampiVuoti);
		verticalPanel.setCellHorizontalAlignment(erLBCampiVuoti, HasHorizontalAlignment.ALIGN_CENTER);
		
		grid.getCellFormatter().setHorizontalAlignment(4, 1, HasHorizontalAlignment.ALIGN_LEFT);
		grid.getCellFormatter().setVerticalAlignment(4, 1, HasVerticalAlignment.ALIGN_MIDDLE);
		grid.getCellFormatter().setHorizontalAlignment(2, 1, HasHorizontalAlignment.ALIGN_LEFT);
		grid.getCellFormatter().setVerticalAlignment(2, 1, HasVerticalAlignment.ALIGN_TOP);
		grid.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_LEFT);
		grid.getCellFormatter().setVerticalAlignment(0, 1, HasVerticalAlignment.ALIGN_MIDDLE);
		grid.getCellFormatter().setHorizontalAlignment(5, 1, HasHorizontalAlignment.ALIGN_LEFT);
		grid.getCellFormatter().setVerticalAlignment(5, 1, HasVerticalAlignment.ALIGN_MIDDLE);
		grid.getCellFormatter().setHorizontalAlignment(7, 1, HasHorizontalAlignment.ALIGN_LEFT);
		grid.getCellFormatter().setVerticalAlignment(7, 1, HasVerticalAlignment.ALIGN_MIDDLE);
		grid.getCellFormatter().setHorizontalAlignment(9, 1, HasHorizontalAlignment.ALIGN_LEFT);
		grid.getCellFormatter().setVerticalAlignment(9, 1, HasVerticalAlignment.ALIGN_MIDDLE);
		grid.getCellFormatter().setHorizontalAlignment(11, 1, HasHorizontalAlignment.ALIGN_LEFT);
		grid.getCellFormatter().setVerticalAlignment(11, 1, HasVerticalAlignment.ALIGN_MIDDLE);
		
		button_Registra = new PushButton("Salva");
		button_Registra.addClickHandler(this);
		verticalPanel.add(button_Registra);
		verticalPanel.setCellHorizontalAlignment(button_Registra, HasHorizontalAlignment.ALIGN_RIGHT);
		verticalPanel.setCellVerticalAlignment(button_Registra, HasVerticalAlignment.ALIGN_MIDDLE);
		button_Registra.setSize("70px", "15px");
	}

	@Override
	public void onClick(ClickEvent event) {
		PushButton control = (PushButton)event.getSource();
		
		if(control == button_Registra){
			//controllo la presenza di campi vuoti
			if(!FieldVerifier.isValidName(tBNome.getText())|| !FieldVerifier.isValidName(tBLuogo.getText())|| !FieldVerifier.isValidName(tBUsername.getText())|| !FieldVerifier.controllaPassword(passwordTextBox.getText())){
				erLBCampiVuoti.setText("E' obbligatorio riempire i campi (obbligatori) vuoti con l'adeguata lunghezza");
				button_Registra.setEnabled(false);
			}
			else{
				button_Registra.setEnabled(true);
				
				String nome = tBNome.getText();
				String luogo = tBLuogo.getText();
				String username = tBUsername.getText();
				String email = tBEmail.getText();
				String password = passwordTextBox.getText();
				Date d = dateBox.getValue();
				DateTimeFormat fmt = DateTimeFormat.getFormat("dd/MM/yyyy");
				String data = fmt.format(d);
				sesso_Scelto = sexBox.getValue(sexBox.getSelectedIndex());
				
				// invia dati al server, tramite la classe singleton
				button_Registra.setEnabled(false);
				Utente utente = new Utente("", nome, luogo, data, sesso_Scelto, username, password, email);
				SpeechySingleton.getSingletonIstance().registrazione(utente);
				
			}	
		}
	}

}
