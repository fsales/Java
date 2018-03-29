package br.com.eventweb.web.contexto;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import br.com.evento.core.dominio.Usuario;
import br.com.evento.core.servico.UsuarioRN;
import br.com.evento.core.servico.exception.RNException;
import br.com.eventweb.web.util.Constantes;
import br.com.eventweb.web.util.FacesUtil;

@ManagedBean
@SessionScoped
public class ContextoBean {

	private Usuario usuarioLogado = null;

	public Usuario getUsuarioLogado() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext external = context.getExternalContext();
		String login = external.getRemoteUser();
		if(this.usuarioLogado==null || !login.equals(this.usuarioLogado.getEmail())){
			if(login !=null){
				EntityManager manager = (EntityManager) FacesUtil.getSessaoMap(Constantes.ENTITY_MANAGER);
				UsuarioRN usuarioRN = new UsuarioRN(manager);
				try {
					this.usuarioLogado = usuarioRN.buscaPorLogin(login);
					
				} catch (RNException e) {
					FacesUtil.erroMsg("Erro: ", e.getMessage());					
				}
			}
		}
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
	
	

}
