package br.com.evento.core.servico;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


import javax.persistence.EntityManager;

import br.com.evento.core.consulta.EventosPorEstado;
import br.com.evento.core.dao.exception.DataException;
import br.com.evento.core.dao.factory.FactoryDAO;
import br.com.evento.core.dao.repositorio.EventoDAO;
import br.com.evento.core.dominio.Evento;
import br.com.evento.core.dominio.Usuario;
import br.com.evento.core.servico.exception.RNException;

public class EventoRN {

	private EventoDAO evenDao;

	public EventoRN(EntityManager manager) {
		super();

		evenDao = FactoryDAO.criarEventoDAO(manager);

	}

	public Evento salvar(Evento evento) throws RNException {
		Date diaAtual = Calendar.getInstance().getTime();
		try {
			if (diaAtual.after(evento.getDataInicio())
					|| diaAtual.after(evento.getDataTermino())) {

				throw new RNException(
						"A data não pode ser anterior a data atual.");
			}
			if (evento.getDataInicio().after(evento.getDataTermino())) {
				throw new RNException(
						"A data de termino não pode ser anterior a data de inicio.");

			}
			return evenDao.salvar(evento);
		} catch (DataException e) {
			throw new RNException(e);
		}
	}

	public Evento recuperarPorId(Integer id) throws RNException {
		try {
			return evenDao.recuperarPorId(id);
		} catch (DataException e) {
			throw new RNException(e);
		}
	}

	public void deletar(Evento evento) throws RNException {
		try {
			evenDao.deletar(evento);
		} catch (DataException e) {
			throw new RNException(e);
		}
	}

	public List<Evento> recuperarTodos() throws RNException {
		try {
			return evenDao.recuperarTodos();
		} catch (DataException e) {
			throw new RNException(e);
		}
	}

	public List<Evento> recuperarTodos(Usuario usuario, boolean participante)
			throws RNException {
		try {
			return evenDao.recuperarTodos(usuario, participante);
		} catch (DataException e) {
			throw new RNException(e);
		}
	}

	/***
	 * Recupera o evento passando um intervalo de datas com o usuario
	 * 
	 * @param usuario
	 * @param dataInicio
	 * @param dataFim
	 * @return
	 * @throws RNException
	 */
	public List<Evento> recuperarIntervaloData(Date dataInicio, Date dataFim) throws RNException {
		try {

			if (dataInicio.after(dataFim)) {
				throw new RNException(
						"A data de termino não pode ser anterior a data de inicio.");
			}
			
			return evenDao.recuperarIntervaloData(dataInicio, dataFim);
		} catch (DataException e) {
			throw new RNException(e);
		}
	}

	/***
	 * Retorna uma lista com a quantidade de evento por estado
	 * @return List<EventosPorEstado>
	 * @throws RNException
	 */
	public List<EventosPorEstado> totalEventosPorEstado() throws RNException {
		try {
			return evenDao.totalEventosPorEstado();
		} catch (DataException e) {
			throw new RNException(e);
		}
	}

}
