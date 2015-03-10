package com.SpeechyPC.server;

import java.io.File;
import java.util.UUID;
import java.util.concurrent.ConcurrentNavigableMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.Fun;
import org.mapdb.Fun.Tuple2;

import com.SpeechyPC.client.SpeechService;
import com.SpeechyPC.shared.Strutture.Categoria;
import com.SpeechyPC.shared.Strutture.Utente;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
public class SpeechServiceImpl extends RemoteServiceServlet implements
		SpeechService {
	
	private static final long serialVersionUID = 7190468326673458784L;

	@Override
	public boolean registrazione(Utente utente) {
		String idI = escapeHtml(utente.getIdUtente());
		String nomeI = escapeHtml(utente.getNomeUtente());
		String luogoI = escapeHtml(utente.getLuogoDiNascita());
		String dataI = escapeHtml(utente.getDataDiNascita());
		String sessoI = escapeHtml(utente.getSesso());
		String usernameI = escapeHtml(utente.getUsername());
		String passwordI = escapeHtml(utente.getPassword());
		String emailI = escapeHtml(utente.getEmail());
		
		Tuple2<String, String> chiaveMapLogin = new Tuple2<String, String>(usernameI, passwordI); // key di mapLogin
		
		DB db = DBMaker.newFileDB(new File("SpeechPCDB")).closeOnJvmShutdown().make();
		ConcurrentNavigableMap<Fun.Tuple2<String, String>, Utente> mapUtenti = db.getTreeMap(MapKey.MappaUtenti.toString());
		if(mapUtenti.isEmpty() || !mapUtenti.containsKey(chiaveMapLogin)){
			idI = UUID.randomUUID().toString();
			Utente nuovoUtente = new Utente(idI, nomeI, luogoI, dataI, sessoI, usernameI, passwordI, emailI);
			mapUtenti.put(chiaveMapLogin, nuovoUtente);
			db.commit();
			db.close();
			creaSessione(usernameI);
			System.out.println("Registrazione - Utente : " + nuovoUtente.toString()); // controllo dei dati
			return true; // registrazione inserita
		} else{
			db.close();
			return false; // registrazione non avvenuta
		
			
		}		
	}
	
	@Override
	public String verificaLogin() {
		HttpServletRequest request = this.getThreadLocalRequest();
		//non creare una nuova -> false
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("usernameUtente") == null){
			return "";
		}
		// sessione e idUtente disponibili, l'utente sembra loggato correttamente.
		System.out.print(session.getAttribute("usernameUtente").toString());
		return session.getAttribute("usernameUtente").toString();
	}
	
	//LoginADMIN
	public boolean loginAdmin(String username, String password) {
		username = escapeHtml(username);
		password = escapeHtml(password);
		Tuple2<String, String> chiaveMapLogin = new Tuple2<String, String>(username, password);
		
		DB db = DBMaker.newFileDB(new File("SpeechPCDB")).closeOnJvmShutdown().make();
		ConcurrentNavigableMap<Fun.Tuple2<String, String>, Utente> mapUtenti = db.getTreeMap(MapKey.MappaUtenti.toString());
		if(!mapUtenti.isEmpty()){
			
			for(Tuple2<String, String> tupla: mapUtenti.keySet()){
				if(tupla.equals(chiaveMapLogin) && (mapUtenti.get(tupla)).getNomeUtente().equals("admin")){
					db.close();
					creaSessione(username);
					return true;
				}
			}
		} else {
			db.close();
			return false;
		}
		return false;
	}
	
	
	@Override
	public boolean login(String username, String password) {
		username = escapeHtml(username);
		password = escapeHtml(password);
		Tuple2<String, String> chiaveMapLogin = new Tuple2<String, String>(username, password); // key di mapLogin
		
		DB db = DBMaker.newFileDB(new File("SpeechPCDB")).closeOnJvmShutdown().make();
		ConcurrentNavigableMap<Fun.Tuple2<String, String>, Utente> mapUtenti = db.getTreeMap(MapKey.MappaUtenti.toString());
		if(!mapUtenti.isEmpty()){
			String idEstratto = "";
			// controllo se la chiave � presente nella mapLogin, ed estraggo l'id a cui � associato.
			for(Tuple2<String, String> tupla: mapUtenti.keySet()){
				if(tupla.equals(chiaveMapLogin)){
					Utente utenteEstratto = mapUtenti.get(tupla);
					idEstratto = utenteEstratto.getIdUtente();
				}
			}
			if(idEstratto.length() != 0){
				db.close();
				System.out.println("Login - Id dell'utente: " + idEstratto);
				creaSessione(idEstratto);
				return true;
			} else {
				db.close();
				return false;
			}	
		} else {
			db.close();
			return false;
		}
	}
	
	@Override
	public boolean logout() {
		HttpServletRequest request = this.getThreadLocalRequest();
		HttpSession session = request.getSession(false);// non creare una nuova sessione se non esiste -> false
		if (session == null){
			return false;
		}
		session.invalidate();// invalida la sessione e rilascia gli oggetti ad essa legata.
		return true;
	}
	
	/*
	 * Controlla che la sessione esista gi� per l'utente, se si ritorna l'oggetto sessione, 
	 * altrimenti crea una nuova sessione (true) e ritorna  il nuovo oggetto sessione.
	 * return --> l'id dell'utente appena loggato.
	 */
	public String creaSessione(String idU) {
		HttpServletRequest request = this.getThreadLocalRequest();
		//creare una nuova sessione -> true
		HttpSession session = request.getSession(true);
		session.setAttribute("idUtente", idU);
		return session.getAttribute("idUtente").toString();
	}
	
	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html the html string to escape
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;");
	}

	@Override
	public boolean categoria(Categoria categoria) {
		// TODO Auto-generated method stub
		String nomeCategoriaI = escapeHtml(categoria.getNomeCategoria());
		
		DB db = DBMaker.newFileDB(new File("SpeechPCDB")).closeOnJvmShutdown().make();
		ConcurrentNavigableMap<String, Categoria> mapCategoria = db.getTreeMap(MapKey.MappaCategoria.toString());
		if(mapCategoria.isEmpty() || !mapCategoria.values().contains(nomeCategoriaI)){
			Categoria nuovaCategoria = new Categoria(nomeCategoriaI);
			String idC = Integer.toString(mapCategoria.keySet().size());
			mapCategoria.put(idC, categoria);
			db.commit();
			db.close();
			System.out.println("Creazione - Categoria : " + nuovaCategoria.toString()); // controllo dei dati
			return true; // Categoria inserita
		} else{
			db.close();
		return false;
		}
	}
}
