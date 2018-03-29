package br.com.evento.core.dao.hibernate;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.evento.core.consulta.EventosPorEstado;
import br.com.evento.core.dao.exception.DataException;
import br.com.evento.core.dao.repositorio.EventoDAO;
import br.com.evento.core.dominio.Evento;
import br.com.evento.core.dominio.Usuario;



public class EventoDAOImpl extends GenericaDAOImpl<Evento> implements EventoDAO {

	private EntityManager manager;

	public EventoDAOImpl(EntityManager manager) {
		super(manager);
		this.manager = manager;

	}

	@SuppressWarnings("unchecked")
	public List<Evento> recuperarTodos(Usuario usuario, boolean participante)
			throws DataException {
		List<Evento> eventos = null;
		String sql = null;
		try {

			if (participante == true) {
				sql = "SELECT DISTINCT e FROM Evento e left join fetch e.participantes where e.usuario =? and e.participantes is not empty ORDER BY e.dataInicio DESC";

			} else {
				sql = "SELECT e FROM Evento e WHERE e.usuario =?1 ORDER BY e.dataInicio DESC";

			}

			Query query = this.manager.createQuery(sql);
			query.setParameter(1, usuario);
			eventos = query.getResultList();
		} catch (Exception e) {
			throw new DataException(e);
		}
		return eventos;
	}

	@SuppressWarnings("unchecked")
	public List<Evento> recuperarPorUf(String uf) throws DataException {
		List<Evento> eventos = null;
		try {
			Query query = this.manager
					.createQuery("SELECT e FROM Evento e WHERE e.cidade.estado.uf=?1");
			query.setParameter(1, uf);
			eventos = query.getResultList();
		} catch (Exception e) {
			throw new DataException(e);
		}
		return eventos;
	}

	/***
	 * metodo que recuper o intervalo de datas passando usuario dataInicio e
	 * dataFIm
	 */
	@SuppressWarnings("unchecked")
	public List<Evento> recuperarIntervaloData(Date dataInicio, Date dataFim) throws DataException {
		List<Evento> eventos = null;

		try {

			StringBuilder myQuery = new StringBuilder();
			myQuery.append("SELECT e FROM Evento e, Usuario u WHERE e.usuario = u AND e.dataInicio BETWEEN  :dataInicio AND :dataFim ORDER BY e.dataInicio ASC ");
			//myQuery.append("SELECT e FROM Evento e WHERE e.usuario = :usuario AND e.dataInicio BETWEEN  :dataInicio AND :dataFim ORDER BY e.dataInicio ASC ");
			Query query = this.manager.createQuery(myQuery.toString());

			//query.setParameter("usuario", usuario);
			query.setParameter("dataInicio", dataInicio);
			query.setParameter("dataFim", dataFim);

			eventos = query.getResultList();
		} catch (Exception e) {
			throw new DataException(e);
		}
		return eventos;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EventosPorEstado> totalEventosPorEstado() throws DataException {
		List<EventosPorEstado> eventos = null;
		String sql = null;
		try {

			sql = "select new br.com.evento.core.consulta.EventosPorEstado(count(e),e.cidade.estado.uf,e.cidade.estado.id)from Evento e   " +
					"JOIN  e.cidade  JOIN  e.cidade.estado GROUP BY e.cidade.estado.uf";

			Query query = this.manager.createQuery(sql);
			eventos = query.getResultList();
		} catch (Exception e) {
			throw new DataException(e);
		}

		return eventos;
	}

}
