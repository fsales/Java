package br.com.estudo.hibernate.heranca.tableperclass;

import javax.persistence.Entity;

@Entity
public class PessoaFisicaTablePerClass extends PessoaTablePerClass {

	private String cpf;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	
}
