package br.com.evento.core.dominio;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Cidade implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9006262964285008759L;

	@Id
	@GeneratedValue
	private Integer id;

	@Column(nullable = false)
	private String cidade;
	
	@ManyToOne(cascade=CascadeType.DETACH)
	private Estado estado;

	private Date criadoEm;

	private Date alteradoEm;

	public Cidade() {
		super();
		this.estado = new Estado();
		if (this.id == null || this.id==0) {
			this.criadoEm = Calendar.getInstance().getTime();
		} else {
			this.alteradoEm = Calendar.getInstance().getTime();
		}

	}

	public Date getCriadoEm() {
		return criadoEm;
	}

	public Date getAlteradoEm() {
		return alteradoEm;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Cidade [id=" + id + ", cidade=" + cidade + ", estado=" + estado
				+ ", criadoEm=" + criadoEm + ", alteradoEm=" + alteradoEm + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cidade == null) ? 0 : cidade.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cidade other = (Cidade) obj;
		if (cidade == null) {
			if (other.cidade != null)
				return false;
		} else if (!cidade.equals(other.cidade))
			return false;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
