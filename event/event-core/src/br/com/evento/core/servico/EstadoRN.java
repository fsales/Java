package br.com.evento.core.servico;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.evento.core.dao.exception.DataException;
import br.com.evento.core.dao.factory.FactoryDAO;
import br.com.evento.core.dao.repositorio.EstadoDAO;
import br.com.evento.core.dominio.Estado;
import br.com.evento.core.servico.exception.RNException;

public class EstadoRN {

	private EstadoDAO estDao;

	public EstadoRN(EntityManager manager) {
		super();

		estDao = FactoryDAO.criarEstadoDAO(manager);

	}

	public Estado recuperarPorId(Integer id) throws RNException {
		try {
			return estDao.recuperarPorId(id);
		} catch (DataException e) {

			throw new RNException(e);
		}
	}

	public void deletar(Estado estado) throws RNException {
		try {
			estDao.deletar(estado);
		} catch (DataException e) {
			throw new RNException(e);
		}
	}

	public List<Estado> recuperarTodos() throws RNException {
		try {
			return estDao.recuperarTodos();
		} catch (DataException e) {
			throw new RNException(e);
		}
	}

	public Estado salvar(Estado estado) throws RNException {
		try {
			estado.setUf(estado.getUf().toUpperCase());
			return estDao.salvar(estado);
		} catch (DataException e) {
			throw new RNException(e);
		}
	}

}
