package br.com.evento.core.dominio;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Estado implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8176765380954864557L;

	@Id
	@GeneratedValue
	private Integer id;

	@Column(nullable = false)
	private String uf;

	public void setUf(String uf) {
		this.uf = uf;
	}

	@Column(nullable = false)
	private String estado;

	
	private Date criadoEm;

	private Date alteradoEm;

	public Estado() {
		super();
	
		if (this.id == null|| this.id==0) {
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

	public String getUf() {
		return uf;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	
	@Override
	public String toString() {
		return "Estado [id=" + id + ", uf=" + uf + ", estado=" + estado
				+ ", criadoEm=" + criadoEm + ", alteradoEm=" + alteradoEm + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((alteradoEm == null) ? 0 : alteradoEm.hashCode());
		result = prime * result
				+ ((criadoEm == null) ? 0 : criadoEm.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((uf == null) ? 0 : uf.hashCode());
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
		Estado other = (Estado) obj;
		if (alteradoEm == null) {
			if (other.alteradoEm != null)
				return false;
		} else if (!alteradoEm.equals(other.alteradoEm))
			return false;
		if (criadoEm == null) {
			if (other.criadoEm != null)
				return false;
		} else if (!criadoEm.equals(other.criadoEm))
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
		if (uf == null) {
			if (other.uf != null)
				return false;
		} else if (!uf.equals(other.uf))
			return false;
		return true;
	}

}
