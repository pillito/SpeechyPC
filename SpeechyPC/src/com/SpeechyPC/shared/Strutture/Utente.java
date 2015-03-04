package com.SpeechyPC.shared.Strutture;

import java.io.Serializable;
import com.google.gwt.user.client.rpc.IsSerializable;

public class Utente implements Serializable, IsSerializable {
	
	private static final long serialVersionUID = 1L;
	private String idU, nome,luogo_Nascita, data_Nascita, sesso, username, password, email;

	public Utente(){}

	public Utente(String idI, String nomeI, String luogoI, String dataI, String sessoI, String usernameI, String passwordI, String emailI){
		this.idU = idI;
		this.nome= nomeI;
		this.luogo_Nascita = luogoI;
		this.data_Nascita = dataI;
		this.sesso = sessoI;
		this.username = usernameI;
		this.password = passwordI;
		this.email = emailI;
	}
	
	public String getIdUtente(){
		return idU;
	}
	public String getNomeUtente(){
		return nome;
	}
	public String getLuogoDiNascita(){
		return luogo_Nascita;
	}
	public String getDataDiNascita(){
		return data_Nascita;
	}
	public String getSesso(){
		return sesso;
	}
	public String getUsername(){
		return username;
	}
	public String getPassword(){
		return password;
	}

	public String getEmail(){
		return email;
	}

	public String toString(){
		return idU + " - " + nome + " - " + luogo_Nascita+ " - " + data_Nascita + " - "+ sesso+ " - " +
	           username + " - " + password + " - " + email;
	}

}

	

