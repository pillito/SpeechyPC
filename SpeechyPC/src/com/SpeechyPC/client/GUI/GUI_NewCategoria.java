package com.SpeechyPC.client.GUI;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Button;

public class GUI_NewCategoria extends Composite {
	public GUI_NewCategoria() {
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		initWidget(absolutePanel);
		absolutePanel.setSize("318px", "190px");
		
		Label lblNomeCategoria = new Label("Nome Categoria");
		absolutePanel.add(lblNomeCategoria, 25, 59);
		
		TextBox textBox = new TextBox();
		absolutePanel.add(textBox, 147, 56);
		textBox.setSize("131px", "18px");
		
		Button btnCreaCategoria = new Button("Crea categoria");
		absolutePanel.add(btnCreaCategoria, 185, 117);
	}
}
