package br.com.evento.core.servico;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.evento.core.dao.exception.DataException;
import br.com.evento.core.dao.factory.FactoryDAO;
import br.com.evento.core.dao.repositorio.CidadeDAO;
import br.com.evento.core.dominio.Cidade;
import br.com.evento.core.dominio.Estado;
import br.com.evento.core.servico.exception.RNException;

public class CidadeRN {

	private CidadeDAO cidDao;

	public CidadeRN(EntityManager manager) {
		super();

		cidDao = FactoryDAO.criarCidadeDAO(manager);

	}

	public Cidade salvar(Cidade cidade) throws RNException {
		try {

			return cidDao.salvar(cidade);
		} catch (DataException e) {
			throw new RNException(e);
		}
	}

	public Cidade recuperarPorId(Integer id) throws RNException {
		try {
			return cidDao.recuperarPorId(id);
		} catch (DataException e) {
			throw new RNException(e);
		}
	}

	public void deletar(Cidade cidade) throws RNException {
		try {
			cidDao.deletar(cidade);
		} catch (DataException e) {
			throw new RNException(e);
		}
	}

	public List<Cidade> recuperarTodos() throws RNException {
		try {
			return cidDao.recuperarTodos();
		} catch (DataException e) {
			throw new RNException(e);
		}
	}

	public List<Cidade> consultaCidades(Estado estado) throws RNException,
			RNException {
		try {
			return cidDao.consultaCidades(estado);
		} catch (DataException e) {
			throw new RNException(e);
		}

	}

}
