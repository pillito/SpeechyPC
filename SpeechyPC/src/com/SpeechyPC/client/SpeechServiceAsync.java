package com.SpeechyPC.client;

import com.SpeechyPC.shared.Strutture.Categoria;
import com.SpeechyPC.shared.Strutture.Utente;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface SpeechServiceAsync {
	
	void registrazione(Utente utente, AsyncCallback<Boolean> asyncCallback);

	void login(String username, String password, AsyncCallback<Boolean> callback);
	
	void loginAdmin(String username, String password, AsyncCallback<Boolean> callback);
	
	void logout(AsyncCallback<Boolean> callback);

	void verificaLogin(AsyncCallback<String> callback);

	void categoria(Categoria categoria, AsyncCallback<Boolean> callback);
	
}
