package com.SpeechyPC.client;

import com.SpeechyPC.shared.Strutture.Categoria;
import com.SpeechyPC.shared.Strutture.Utente;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("speech")
public interface SpeechService extends RemoteService {
	
	boolean registrazione(Utente utente);
	
	boolean login(String username, String password);
	
	boolean logout();
	
	boolean loginAdmin(String username, String password);
	
	boolean categoria (Categoria categoria);
	
	String verificaLogin();
	
}
