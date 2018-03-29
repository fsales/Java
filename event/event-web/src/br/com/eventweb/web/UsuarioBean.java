package br.com.eventweb.web;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import br.com.evento.core.dominio.Usuario;
import br.com.eventweb.web.contexto.ContextoBean;
import br.com.eventweb.web.contexto.ContextoUtil;
import br.com.eventweb.web.util.FacesUtil;

@ManagedBean
@RequestScoped
public class UsuarioBean {

	private Usuario usuarioLogado = null;

	public Usuario getUsuarioLogado() {
		ContextoBean contextoBean = ContextoUtil.getContextoBean();
		if (this.usuarioLogado == null && contextoBean.getUsuarioLogado()!=null) {
			
			this.usuarioLogado = contextoBean.getUsuarioLogado();
		}
		return usuarioLogado;
	}

	public String getIp() {

		return FacesUtil.pegarIp().toString();
	}
	
	public EntityManager getEntityManager() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) ec.getRequest();
		EntityManager manager = (EntityManager) request.getAttribute("EntityManager");
		return manager;
	}

}
