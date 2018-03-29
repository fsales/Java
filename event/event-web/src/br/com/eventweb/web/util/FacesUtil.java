package br.com.eventweb.web.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

public class FacesUtil {

	/**
	 * SEVERITY_ERRO.
	 * 
	 * @param sumary
	 * @param detail
	 */
	public static void erroMsg(String sumary, String detail) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, sumary, detail));
	}

	/**
	 * SEVERITY_INFO
	 * 
	 * @param sumary
	 * @param detail
	 */
	public static void sucessoMsg(String sumary, String detail) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, sumary, detail));
	}

	/**
	 * pegar o mapa da sessao
	 * 
	 * @return Object
	 */
	public static Object getSessaoMap(String key) {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getRequestMap().get(key);
	}

	
	public static String pegarIp() {
		HttpServletRequest request = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();

		return request.getRemoteAddr();
	}

	
}
