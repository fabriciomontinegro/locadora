package com.br.locadora.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
public class Filme {
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String nome;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String genero;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String autor;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private boolean status;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "filme")
	private List<Ator> atores;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "filme")
	private List<Avaliacao> avaliacoes;

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

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public List<Ator> getAtores() {
		return atores;
	}

	public void setAtores(List<Ator> atores) {
		this.atores = atores;
	}

	public List<Avaliacao> getAvaliacoes() {
		return avaliacoes;
	}

	public void setAvaliacoes(List<Avaliacao> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}

}
