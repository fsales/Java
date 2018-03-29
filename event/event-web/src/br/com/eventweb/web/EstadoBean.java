package br.com.eventweb.web;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import br.com.evento.core.dominio.Estado;
import br.com.evento.core.servico.EstadoRN;
import br.com.evento.core.servico.exception.RNException;
import br.com.eventweb.web.util.FacesUtil;

@ManagedBean
@ViewScoped
public class EstadoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6361111815959874725L;
	private Estado editado = new Estado();;
	private EstadoRN estadoRN;
	private List<Estado> list = null;

	public void novo() {

		try {
			EntityManager manager = this.getEntityManager();
			this.estadoRN = new EstadoRN(manager);
			this.estadoRN.salvar(this.editado);
			if (this.editado.getId() == 0) {
				FacesUtil.sucessoMsg("Sucesso: ","Estado " + this.editado.getEstado()+ " salvo com sucesso.");
			} else {
				FacesUtil.sucessoMsg("Sucesso: ","Estado " + this.editado.getEstado()+ " alterado com sucesso.");
			}
			this.editado = new Estado();
			this.list = null;
		} catch (RNException e) {
			FacesUtil.erroMsg("Erro: ", e.getMessage());

		}
	}

	public void excluir() {

		try {
			EntityManager manager = this.getEntityManager();
			this.estadoRN = new EstadoRN(manager);
			this.estadoRN.deletar(this.editado);
			this.list = null;
			FacesUtil.sucessoMsg("Sucesso: ","estado " + this.editado.getEstado()+ " excluído com sucesso.");
			this.editado = new Estado();
		} catch (RNException e) {
			FacesUtil.erroMsg("Erro: ", e.getMessage());
		}
	}

	public List<Estado> getList() {

		try {
			EntityManager manager = this.getEntityManager();
			this.estadoRN = new EstadoRN(manager);
			this.list = this.estadoRN.recuperarTodos();
		} catch (RNException e) {
			FacesUtil.erroMsg("Erro: ", e.getMessage());
		}
		return list;
	}

	public EntityManager getEntityManager() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) ec.getRequest();
		EntityManager manager = (EntityManager) request.getAttribute("EntityManager");
		return manager;
	}

	//
	public void setList(List<Estado> list) {
		this.list = list;
	}

	public Estado getEditado() {
		return editado;
	}

	public void setEditado(Estado editado) {
		this.editado = editado;
	}

}
