package br.com.evento.core.dao.factory;

import javax.persistence.EntityManager;

import br.com.evento.core.dao.hibernate.CidadeDAOImpl;
import br.com.evento.core.dao.hibernate.EstadoDAOImpl;
import br.com.evento.core.dao.hibernate.EventoDAOImpl;
import br.com.evento.core.dao.hibernate.UsuarioDAOImpl;
import br.com.evento.core.dao.repositorio.CidadeDAO;
import br.com.evento.core.dao.repositorio.EstadoDAO;
import br.com.evento.core.dao.repositorio.EventoDAO;
import br.com.evento.core.dao.repositorio.UsuarioDAO;

public class FactoryDAO {

	public static UsuarioDAO criarUsuarioDAO(EntityManager manager) {

		UsuarioDAO usuDao = new UsuarioDAOImpl(manager);
		return usuDao;
	}

	public static EventoDAO criarEventoDAO(EntityManager manager) {

		EventoDAO eveDao = new EventoDAOImpl(manager);
		return eveDao;
	}

	public static EstadoDAO criarEstadoDAO(EntityManager manager) {

		EstadoDAO estDao = new EstadoDAOImpl(manager);

		return estDao;
	}

	public static CidadeDAO criarCidadeDAO(EntityManager manager) {

		CidadeDAO cidDAO = new CidadeDAOImpl(manager);
		return cidDAO;
	}
}
