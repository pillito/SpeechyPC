package com.SpeechyPC.client.GUI;

import com.SpeechyPC.client.SpeechySingleton;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;

public class GUI_Login extends Composite {
	
	private Label lbUsername, lbPassword, erLBCampiVuoti;
	private TextBox tBUsername;
	private PasswordTextBox passwordTextBox;
	private PushButton button_Login;
	
	public GUI_Login() {
		
		VerticalPanel verticalPanel = new VerticalPanel();
		verticalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		verticalPanel.setStyleName("gwt-Label- Error");
		initWidget(verticalPanel);
		verticalPanel.setSize("360px", "132px");
		
		Grid grid = new Grid(2, 2);
		grid.setCellSpacing(5);
		verticalPanel.add(grid);
		grid.setSize("345px", "83px");
		
		lbUsername = new Label("Username");
		lbUsername.setStyleName("gwt-Label");
		grid.setWidget(0, 0, lbUsername);
		
		tBUsername = new TextBox();
		grid.setWidget(0, 1, tBUsername);
		tBUsername.setSize("205px", "15px");
		grid.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_LEFT);
		grid.getCellFormatter().setVerticalAlignment(0, 1, HasVerticalAlignment.ALIGN_MIDDLE);
		
		lbPassword = new Label("Password");
		grid.setWidget(1, 0, lbPassword);
		
		passwordTextBox = new PasswordTextBox();
		grid.setWidget(1, 1, passwordTextBox);
		passwordTextBox.setSize("205px", "15px");
		grid.getCellFormatter().setHorizontalAlignment(1, 1, HasHorizontalAlignment.ALIGN_LEFT);
		grid.getCellFormatter().setVerticalAlignment(1, 1, HasVerticalAlignment.ALIGN_MIDDLE);
		
		erLBCampiVuoti = new Label("");
		erLBCampiVuoti.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		erLBCampiVuoti.setStyleName("gwt-Label- Error");
		verticalPanel.add(erLBCampiVuoti);
		
		button_Login = new PushButton("Login");
		button_Login.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if(tBUsername.getText().length() == 0 || passwordTextBox.getText().length() == 0){
					erLBCampiVuoti.setText(" E' necessario riempire i campi vuoti");
					button_Login.setEnabled(false);
				} else {
					button_Login.setEnabled(true);
					
					String username = tBUsername.getText();
					String password = passwordTextBox.getText();
					
					// invio i dati al server
					SpeechySingleton.getSingletonIstance().login(username, password);
				}	
			}
		});
		verticalPanel.add(button_Login);
		verticalPanel.setCellHorizontalAlignment(button_Login, HasHorizontalAlignment.ALIGN_RIGHT);
		button_Login.setSize("60px", "15px");
	}	
}
