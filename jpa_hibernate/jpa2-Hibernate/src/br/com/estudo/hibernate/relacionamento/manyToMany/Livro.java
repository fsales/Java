package br.com.estudo.hibernate.relacionamento.manyToMany;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Livro {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToMany
	@JoinTable(name = "liv_aut", joinColumns = @JoinColumn(name = "liv_id"), inverseJoinColumns = @JoinColumn(name = "aut_id"))
	private Collection<Autor> autores = new ArrayList<Autor>();

	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Collection<Autor> getAutores() {
		return autores;
	}

	public void setAutores(Collection<Autor> autores) {
		this.autores = autores;
	}
}
