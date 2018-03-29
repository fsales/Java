package br.com.estudo.hibernate.heranca.joined;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity

@Inheritance(strategy=InheritanceType.JOINED)
public class PessoaJoined {

	@Id @GeneratedValue
	private Long id;
	private String noe;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNoe() {
		return noe;
	}
	public void setNoe(String noe) {
		this.noe = noe;
	}
	
}
