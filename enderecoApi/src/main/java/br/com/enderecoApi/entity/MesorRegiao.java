package br.com.enderecoApi.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author MarcosAnt
 *
 */

public class MesorRegiao {

	private Long id;
	private String nome;
	@JsonProperty("UF")
	private Estado UF;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Estado getUF() {
		return UF;
	}
	public void setUF(Estado UF) {
		this.UF = UF;
	}
	
}
