package br.com.enderecoApi.entity;

/**
 * 
 * @author MarcosAnt
 *
 */

public class MicrorRegiao {
	
	private Long id;
	private String nome;
	private MesorRegiao mesorregiao;
	
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
	public MesorRegiao getMesorregiao() {
		return mesorregiao;
	}
	public void setMesorregiao(MesorRegiao mesorregiao) {
		this.mesorregiao = mesorregiao;
	}
	
}
