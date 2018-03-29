package br.com.estudo.hibernate.heranca.joined;

import javax.persistence.Entity;
import br.com.estudo.hibernate.heranca.joined.PessoaJoined;
@Entity
public class PessoaFisicaJoined extends PessoaJoined {

	private String cpf;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	
}
