package br.com.estudo.hibernate.heranca.tableperclass;

import javax.persistence.Entity;

@Entity
public class PessoaJuridicaTablePerClass extends PessoaTablePerClass {
	private String cnpj;

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
	
}
