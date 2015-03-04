package com.SpeechyPC.client.GUI;

import com.SpeechyPC.client.SpeechySingleton;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.VerticalPanel;

public class GUI_Principale extends Composite implements ClickHandler {

	private DialogBox dialogRegistra;
	private RichTextArea richTextArea;
	private ListBox categorieComboBox, speechersComboBox;
	private Button btnRegistrati, btnLogin, btnLogout;
	private Button btnCommenta;
	
	public GUI_Principale() {
		AbsolutePanel absolutePanel = new AbsolutePanel();
		initWidget(absolutePanel);
		absolutePanel.setSize("1200px", "573px");
		
		Grid grid = new Grid(1, 7);
		grid.setCellSpacing(10);
		absolutePanel.add(grid, 0, 0);
		grid.setSize("1200px", "74px");
		
		categorieComboBox = new ListBox();
		categorieComboBox.addItem("categoria1");
		categorieComboBox.addItem("categoria2");
		grid.setWidget(0, 0, categorieComboBox);
		categorieComboBox.setWidth("155px");
		
		// contiene la lista di utenti che si seguono
		speechersComboBox = new ListBox();
		speechersComboBox.addItem("followed1");
		speechersComboBox.addItem("followed2");
		grid.setWidget(0, 1, speechersComboBox);
		speechersComboBox.setSize("138px", "25px");
		
		btnRegistrati = new Button("Registrati");
		btnRegistrati.addClickHandler(this);
		grid.setWidget(0, 4, btnRegistrati);
		
		btnLogin = new Button("Login");
		btnLogin.addClickHandler(this);
		grid.setWidget(0, 5, btnLogin);
		
		

		btnLogout = new Button("Logout");
		btnLogout.addClickHandler(this);
		grid.setWidget(0, 6, btnLogout);
		
		grid.getCellFormatter().setHorizontalAlignment(0, 4, HasHorizontalAlignment.ALIGN_RIGHT);
		grid.getCellFormatter().setVerticalAlignment(0, 4, HasVerticalAlignment.ALIGN_TOP);
		grid.getCellFormatter().setHorizontalAlignment(0, 6, HasHorizontalAlignment.ALIGN_RIGHT);
		grid.getCellFormatter().setVerticalAlignment(0, 6, HasVerticalAlignment.ALIGN_TOP);
		grid.getCellFormatter().setHorizontalAlignment(0, 5, HasHorizontalAlignment.ALIGN_RIGHT);
		grid.getCellFormatter().setVerticalAlignment(0, 5, HasVerticalAlignment.ALIGN_TOP);
		grid.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);
		grid.getCellFormatter().setVerticalAlignment(0, 1, HasVerticalAlignment.ALIGN_TOP);
		
		VerticalPanel verticalPanel = new VerticalPanel();
		verticalPanel.setSpacing(5);
		absolutePanel.add(verticalPanel, 10, 80);
		verticalPanel.setSize("556px", "187px");
		
		Label lblCommenta = new Label("COMMENTA");
		verticalPanel.add(lblCommenta);
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		verticalPanel.add(horizontalPanel);
		horizontalPanel.setWidth("555px");
		
		richTextArea = new RichTextArea();
		horizontalPanel.add(richTextArea);
		horizontalPanel.setCellVerticalAlignment(richTextArea, HasVerticalAlignment.ALIGN_BOTTOM);
		richTextArea.setWidth("466px");
		
		btnCommenta = new Button("Commenta");
		btnCommenta.addClickHandler(this);
		horizontalPanel.add(btnCommenta);
		horizontalPanel.setCellVerticalAlignment(btnCommenta, HasVerticalAlignment.ALIGN_BOTTOM);
		
		VerticalPanel verticalPanel_1 = new VerticalPanel();
		absolutePanel.add(verticalPanel_1, 634, 80);
		verticalPanel_1.setSize("556px", "483px");
	}
	
	@Override
	public void onClick(ClickEvent event) {
		
		Button control = (Button)event.getSource();
		if(control == btnRegistrati){
			dialogRegistra = new DialogBox();
			dialogRegistra.setText("Speechy Service Registrazione");
			dialogRegistra.setAnimationEnabled(true);
			final Button close = new Button("Chiudi");
			close.setSize("70px", "25px");
			VerticalPanel dialogVPanel = new VerticalPanel();
			dialogVPanel.add(new GUI_Registrazione()); // richiamo il pannello che gestisce la registrazione.
			dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
			dialogVPanel.add(close);
			dialogRegistra.setWidget(dialogVPanel);
			dialogRegistra.center();
			close.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					dialogRegistra.hide();
				}
			});
		}
		
		if(control == btnLogin){
			dialogRegistra = new DialogBox();
			dialogRegistra.setText("Speechy Service Login");
			dialogRegistra.setAnimationEnabled(true);
			final Button close = new Button("Chiudi");
			close.setSize("70px", "25px");
			VerticalPanel dialogVPanel = new VerticalPanel();
			dialogVPanel.add(new GUI_Login()); // richiamo il pannello che gestisce la registrazione.
			dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
			dialogVPanel.add(close);
			dialogRegistra.setWidget(dialogVPanel);
			dialogRegistra.center();
			close.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					dialogRegistra.hide();
				}
			});
		}
		if(control == btnLogout){
			SpeechySingleton.getSingletonIstance().logout();
		}
	//	if(control == pshbtnNewButton ){
	//	}
	}
}
