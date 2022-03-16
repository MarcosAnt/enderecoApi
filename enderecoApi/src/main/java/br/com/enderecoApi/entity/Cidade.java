package br.com.enderecoApi.entity;

import java.io.Serializable;

/**
 * 
 * @author MarcosAnt
 *
 */

public class Cidade implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private long id;
	private String nome;
	private MicrorRegiao microrregiao;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public MicrorRegiao getMicrorregiao() {
		return microrregiao;
	}
	public void setMicrorregiao(MicrorRegiao microrregiao) {
		this.microrregiao = microrregiao;
	}
	
}
