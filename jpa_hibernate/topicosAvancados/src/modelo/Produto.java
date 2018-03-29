package modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PostPersist;
import javax.persistence.PrePersist;
import javax.swing.JOptionPane;

@Entity
public class Produto {

	@Id @GeneratedValue
	private Long id;
	
	private String nome;
	
	private Double preco;

	@PrePersist
	public void prePersist(){
		JOptionPane.showMessageDialog(null, "persistindo.......");
	}
	@PostPersist
	public void postPersist(){
		JOptionPane.showMessageDialog(null, "já persistindo.......");
	}
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

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
	
}
