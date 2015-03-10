package com.SpeechyPC.client;

import com.SpeechyPC.client.GUI.GUI_Principale;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class SpeechyPC implements EntryPoint {
	

	private static SpeechyPC instance;
	private RootPanel rootPanel;
	private GUI_Principale gui_Principale;

	public static SpeechyPC getInstance() {
		if (instance == null) {
			instance = new SpeechyPC();
		}
		return instance;
	}

	public SpeechyPC() {
		rootPanel = RootPanel.get("container");
		gui_Principale = new GUI_Principale();
	}

	@Override

	public void onModuleLoad() {
		this.rootPanel.setSize("100%", "100%");
		this.rootPanel.add(this.gui_Principale);
	}

	public GUI_Principale getstartRU() {
		return gui_Principale;
	}
	
}

