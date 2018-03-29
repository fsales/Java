package br.com.eventweb.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import br.com.evento.core.dominio.Estado;
import br.com.evento.core.servico.EstadoRN;

@FacesConverter(forClass = Estado.class)
public class EstadoConvert implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent componet,
			String value) {

		if (value != null && !value.equals("")) {
			try {
				Integer codigo = Integer.valueOf(value);

				EntityManager manager = this.getEntityManager();

				EstadoRN estadoRN = new EstadoRN(manager);
				return estadoRN.recuperarPorId(codigo);
			} catch (Exception e) {
				throw new ConverterException(
						"Não foi possível encontrar o estado de código "
								+ value + ". " + e.getMessage());
			}

		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value instanceof Estado) {
			Estado estado = (Estado) value;
			return String.valueOf(estado.getId());
		}
		return "";
	}

	public EntityManager getEntityManager() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) ec.getRequest();
		EntityManager manager = (EntityManager) request.getAttribute("EntityManager");
		return manager;
	}
}
