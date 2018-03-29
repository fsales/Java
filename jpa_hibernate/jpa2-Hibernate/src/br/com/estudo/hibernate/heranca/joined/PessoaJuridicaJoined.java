package br.com.estudo.hibernate.heranca.joined;

import javax.persistence.Entity;
import br.com.estudo.hibernate.heranca.joined.PessoaJoined;
@Entity
public class PessoaJuridicaJoined extends PessoaJoined {
	private String cnpj;

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
	
}
