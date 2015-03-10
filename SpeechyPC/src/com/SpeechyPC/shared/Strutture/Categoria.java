package com.SpeechyPC.shared.Strutture;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;


public class Categoria implements Serializable, IsSerializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2106838655158053263L;
	
	private String /*idC,*/ nomeCategoria;

	public Categoria(){}

	public Categoria(/*String idI,*/ String nomeCategoriaI){
		//this.idC = idI;
		this.nomeCategoria = nomeCategoriaI;
		}
	/*
	public String getIdCategoria(){
		return idC;
	}
	*/
	public String getNomeCategoria(){
		return nomeCategoria;
	}

	
	public String toString(){
		return  nomeCategoria;
	}
	
	
}
