package br.com.evento.core.dao.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.evento.core.dao.exception.DataException;
import br.com.evento.core.dao.repositorio.CidadeDAO;
import br.com.evento.core.dominio.Cidade;
import br.com.evento.core.dominio.Estado;

public class CidadeDAOImpl extends GenericaDAOImpl<Cidade> implements CidadeDAO {

	private EntityManager manager;
	public CidadeDAOImpl(EntityManager manager) {
		super(manager);
		this.manager = manager;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cidade> consultaCidades(Estado estado) throws DataException {
		List<Cidade> cidades = null;
		try {
			String sql="SELECT c FROM Cidade c  WHERE c.estado = ?1";
			Query query = this.manager.createQuery(sql);
			query.setParameter(1, estado);
			cidades = query.getResultList();
		} catch (Exception e) {
			throw new DataException(e);
		}
		return cidades;
	}

}
