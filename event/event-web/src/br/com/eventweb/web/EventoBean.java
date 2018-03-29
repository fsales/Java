package br.com.eventweb.web;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import br.com.evento.core.consulta.EventosPorEstado;
import br.com.evento.core.dominio.Contato;
import br.com.evento.core.dominio.Endereco;
import br.com.evento.core.dominio.Evento;
import br.com.evento.core.servico.EventoRN;
import br.com.evento.core.servico.exception.RNException;
import br.com.eventweb.web.contexto.ContextoBean;
import br.com.eventweb.web.contexto.ContextoUtil;
import br.com.eventweb.web.util.FacesUtil;

@ManagedBean
@ViewScoped
public class EventoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7959221421873490343L;
	private Evento editado = new Evento();;
	private List<Evento> lista = null;
	private Endereco endereco = new Endereco();
	private Contato contato = new Contato();
	private List<Evento> eventosFuturos = null;
	private List<EventosPorEstado> eventosPorEstados = null;
	private String uf;

	public String eventosPorUf() {
		return "/restrito/evento/eventosPorUf";
	}

	public void participar() {
		ContextoBean contextBean = ContextoUtil.getContextoBean();
		try {
			EntityManager manager = this.getEntityManager();
			EventoRN eventoRN = new EventoRN(manager);
			this.editado = eventoRN.recuperarPorId(this.editado.getId());
			this.editado.getParticipantes().add(contextBean.getUsuarioLogado());
			eventoRN.salvar(editado);
			FacesUtil.sucessoMsg("Sucesso: ", " inscrição no evento "
					+ this.editado.getNome() + " realizado com sucesso.");
		} catch (RNException e) {
			FacesUtil.erroMsg("Erro: ", e.getMessage());
		}

	}

	public void novo() {
		try {
			ContextoBean contextBean = ContextoUtil.getContextoBean();

			EntityManager manager = this.getEntityManager();
			EventoRN eventoRN = new EventoRN(manager);
			this.editado.setEndereco(this.endereco);
			this.editado.setContato(this.contato);
			this.editado.setUsuario(contextBean.getUsuarioLogado());

			eventoRN.salvar(editado);

			FacesUtil.sucessoMsg("Sucesso: ",
					"Evento " + this.editado.getNome() + " salvo com sucesso.");

			this.editado = new Evento();
			this.contato = new Contato();
			this.endereco = new Endereco();
			this.lista = null;
			this.eventosFuturos = null;
			this.eventosPorEstados = null;
		} catch (RNException e) {
			FacesUtil.erroMsg("Erro: ", e.getMessage());
		}
	}

	public void excluir() {
		try {
			EntityManager manager = this.getEntityManager();
			EventoRN eventoRN = new EventoRN(manager);
			eventoRN.deletar(editado);
			FacesUtil.erroMsg("Sucesso: ", "Evento " + this.editado.getNome()
					+ " excluido com sucesso.");
			this.editado = new Evento();
			this.lista = null;
			this.eventosFuturos = null;
			this.eventosPorEstados = null;
		} catch (RNException e) {
			FacesUtil.erroMsg("Erro: ", e.getMessage());
		}
	}

	public List<Evento> getLista() {
		try {
			if (this.lista == null) {
				ContextoBean contextoBean = ContextoUtil.getContextoBean();
				EntityManager manager = this.getEntityManager();
				EventoRN eventoRN = new EventoRN(manager);
				this.lista = eventoRN.recuperarTodos(
						contextoBean.getUsuarioLogado(), false);
			}
		} catch (RNException e) {
			FacesUtil.erroMsg("Erro: ", e.getMessage());
		}
		return lista;
	}

	public List<EventosPorEstado> getEventosPorEstados() {
		try {
			if (this.eventosPorEstados == null) {
				EntityManager manager = this.getEntityManager();
				EventoRN eventoRN = new EventoRN(manager);
				this.eventosPorEstados = eventoRN.totalEventosPorEstado();
			}
		} catch (Exception e) {
			FacesUtil.erroMsg("Erro: ", e.getMessage());
		}
		return eventosPorEstados;
	}

	public List<Evento> getEventosFuturos() {
		if (this.eventosFuturos == null) {
			try {
				Calendar hoje = Calendar.getInstance();
				Calendar futuro = new GregorianCalendar();
				futuro.set(GregorianCalendar.DAY_OF_MONTH,
						futuro.getMaximum(GregorianCalendar.DAY_OF_MONTH));
				
				EntityManager manager = this.getEntityManager();
				EventoRN eventoRN = new EventoRN(manager);
				eventoRN = new EventoRN(manager);
				this.eventosFuturos = eventoRN.recuperarIntervaloData(
						hoje.getTime(), futuro.getTime());
			} catch (RNException e) {
				FacesUtil.erroMsg("Erro: ", e.getMessage());
			}
		}
		return eventosFuturos;
	}

	public EntityManager getEntityManager() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) ec.getRequest();
		EntityManager manager = (EntityManager) request
				.getAttribute("EntityManager");
		return manager;
	}

	//
	public Evento getEditado() {
		return editado;
	}

	public void setEditado(Evento editado) {
		this.editado = editado;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

}
