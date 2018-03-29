package br.com.estudo.hibernate.heranca.singletable;

import javax.persistence.Entity;

@Entity
public class PessoaFisicaSingleTable extends PessoaSingleTable {

	private String cpf;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	
}
