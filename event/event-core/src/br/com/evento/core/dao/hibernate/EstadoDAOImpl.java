package br.com.evento.core.dao.hibernate;

import javax.persistence.EntityManager;

import br.com.evento.core.dao.repositorio.EstadoDAO;
import br.com.evento.core.dominio.Estado;

public class EstadoDAOImpl extends GenericaDAOImpl<Estado> implements EstadoDAO {

	public EstadoDAOImpl(EntityManager manager) {
		super(manager);
		
	}

}
