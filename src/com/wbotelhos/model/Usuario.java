package com.wbotelhos.model;

import java.io.Serializable;

/**
 * @author Washington Botelho dos Santos
 * @artigo http://wbotelhos.com/2009/12/07/iniciando-com-vraptor-3
 */

public class Usuario implements Serializable {

	private static final long serialVersionUID = 2062623561206196776L;

	private Integer id;
	private String nome;
	private String senha;
	private String email;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}