package br.com.eventweb.web;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import br.com.evento.core.dominio.Cidade;
import br.com.evento.core.dominio.Estado;
import br.com.evento.core.servico.CidadeRN;
import br.com.evento.core.servico.exception.RNException;
import br.com.eventweb.web.util.FacesUtil;

@ManagedBean
@ViewScoped
public class CidadeBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7989483424855167039L;
	private Cidade cidade = new Cidade();
	private List<Cidade> lista = null;
	private CidadeRN cidadeRN;
	private List<Cidade> cidadesPorUf = null;
	private Estado estado = new Estado();

	/**
	 * retorna as cidades apos selecionado a uf via ajax
	 * 
	 * @param event
	 */
	public void listaCidadesPorUf(AjaxBehaviorEvent event) {

		try {
			EntityManager manager = this.getEntityManager();
			this.cidadeRN = new CidadeRN(manager);
			this.cidadesPorUf = this.cidadeRN.consultaCidades(estado);

		} catch (Exception e) {
			FacesUtil.erroMsg("Erro: ", e.getMessage());
		}

	}

	public void novo() {

		try {
			EntityManager manager = this.getEntityManager();
			this.cidadeRN = new CidadeRN(manager);
			this.cidadeRN.salvar(this.cidade);

			if (this.cidade.getId() == 0) {
				FacesUtil.sucessoMsg("Sucesso: ","cidade " + this.cidade.getCidade()+ " salvo com sucesso.");
			} else {
				FacesUtil.sucessoMsg("Sucesso: ","cidade " + this.cidade.getCidade()+ " alterado com sucesso.");
			}

			this.lista = null;
			this.cidade = new Cidade();

		} catch (RNException e) {
			FacesUtil.erroMsg("Erro: ", e.getMessage());
		}
	}

	public void excluir() {

		try {
			EntityManager manager = this.getEntityManager();
			this.cidadeRN = new CidadeRN(manager);
			this.cidadeRN.deletar(this.cidade);
			FacesUtil.sucessoMsg("Sucesso: ", "cidade " + this.cidade.getId()+ " deletada com sucesso.");
			this.lista = null;

		} catch (RNException e) {
			FacesUtil.erroMsg("Erro: ", e.getMessage());
		}
	}

	public List<Cidade> getLista() {
		if (this.lista == null) {
			try {
				EntityManager manager = this.getEntityManager();
				this.cidadeRN = new CidadeRN(manager);
				this.lista = this.cidadeRN.recuperarTodos();
			} catch (RNException e) {
				FacesUtil.erroMsg("Erro: ", e.getMessage());
			}
		}
		return lista;
	}

	public EntityManager getEntityManager() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) ec.getRequest();
		EntityManager manager = (EntityManager) request.getAttribute("EntityManager");
		return manager;
	}

	//
	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public List<Cidade> getCidadesPorUf() {
		return cidadesPorUf;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

}
