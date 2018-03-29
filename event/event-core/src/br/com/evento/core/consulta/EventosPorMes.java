package br.com.evento.core.consulta;

import java.util.Calendar;
import java.util.Date;

public class EventosPorMes {

	private int mes;
	private Long total;

	public int getMes() {
		return mes;
	}

	public EventosPorMes(int mes, Long total) {
		super();
		this.mes = mes;
		this.total = total;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}
	
}
