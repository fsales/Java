package dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
	@NamedQuery(name="Pessoa.count",query="SELECT COUNT(p) FROM Pessoa p" ),
	@NamedQuery(name="Pessoa.findAll",query="SELECT p FROM Pessoa p" ),
	@NamedQuery(name="Pessao.findByIdade", query="SELECT p FROM Pessoa p WHERE p.idade > ?1"),
	@NamedQuery(name="Pessoa.findNome",query="SELECT p.nome FROM Pessoa p"),
	@NamedQuery(name="Pessoa.maxIdade",query="SELECT MAX(p.idade) FROM Pessoa p")
	
})
public class Pessoa {

	@Id
	@GeneratedValue
	private Long id;

	private String nome;
	
	private int idade;
	
	
	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
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

	@Override
	public String toString() {
		return "Pessoa [id=" + id + ", nome=" + nome + ", idade=" + idade + "]";
	}

	
	
	

}
