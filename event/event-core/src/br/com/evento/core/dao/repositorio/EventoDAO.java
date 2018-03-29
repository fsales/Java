package br.com.evento.core.dao.repositorio;

import java.util.Date;
import java.util.List;

import br.com.evento.core.consulta.EventosPorEstado;
import br.com.evento.core.dao.exception.DataException;
import br.com.evento.core.dominio.Evento;
import br.com.evento.core.dominio.Usuario;

public interface EventoDAO extends GenericaDAO<Evento> {

	List<Evento> recuperarIntervaloData(Date dataInicio, Date dataFim) throws DataException;
	List<Evento> recuperarTodos(Usuario usuario, boolean participante) throws DataException;
	List<EventosPorEstado> totalEventosPorEstado() throws DataException;
}
