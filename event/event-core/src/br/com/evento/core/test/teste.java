package br.com.evento.core.test;

import static org.junit.Assert.fail;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import br.com.evento.core.dominio.Evento;
import br.com.evento.core.servico.EventoRN;

public class teste {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EntityManager manager = Persistence.createEntityManagerFactory("event")
				.createEntityManager();

		try {
			manager.getTransaction().begin();
			EventoRN eventoRN = new EventoRN(manager);

			Calendar hoje = Calendar.getInstance();
			Calendar futuro = new GregorianCalendar();
			futuro.set(GregorianCalendar.DAY_OF_MONTH,futuro.getMaximum(GregorianCalendar.DAY_OF_MONTH));
			
			List<Evento> lista = eventoRN.recuperarIntervaloData(hoje.getTime(), futuro.getTime());
			System.out.println(lista.size());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		} finally {
			manager.close();
		}

	}

}
