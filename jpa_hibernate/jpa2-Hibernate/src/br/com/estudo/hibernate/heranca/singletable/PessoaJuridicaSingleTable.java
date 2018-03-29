package br.com.estudo.hibernate.heranca.singletable;

import javax.persistence.Entity;

@Entity
public class PessoaJuridicaSingleTable extends PessoaSingleTable {
	private String cnpj;

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
	
}
