package br.com.eventweb.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import br.com.evento.core.dominio.Cidade;
import br.com.evento.core.servico.CidadeRN;
import br.com.evento.core.servico.exception.RNException;

@FacesConverter(forClass = Cidade.class)
public class CidadeConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent componet,
			String value) {
		if (value != null && !value.equals("")) {
			try {
				Integer codigo = Integer.valueOf(value);
				EntityManager manager = this.getEntityManager();
				CidadeRN cidadeRN = new CidadeRN(manager);

				return cidadeRN.recuperarPorId(codigo);
			} catch (RNException e) {
				throw new ConverterException(
						"Não foi possível encontrar a cidade de código "
								+ value + ". " + e.getMessage());
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value instanceof Cidade) {
			Cidade cidade = (Cidade) value;
			return String.valueOf(cidade.getId());
		}
		return null;
	}
	
	public EntityManager getEntityManager() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) ec.getRequest();
		EntityManager manager = (EntityManager) request.getAttribute("EntityManager");
		return manager;
	}


}
