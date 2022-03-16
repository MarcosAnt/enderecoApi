package br.com.enderecoApi.entity;

import java.io.Serializable;

/**
 * 
 * @author MarcosAnt
 *
 */

public class Regiao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String sigla;
	private String nome;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
