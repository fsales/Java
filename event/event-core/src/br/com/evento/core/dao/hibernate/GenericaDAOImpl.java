package br.com.evento.core.dao.hibernate;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.QueryTimeoutException;

import br.com.evento.core.dao.exception.DataException;
import br.com.evento.core.dao.repositorio.GenericaDAO;

public abstract class GenericaDAOImpl<T> implements GenericaDAO<T> {

	private EntityManager manager;
	@SuppressWarnings("rawtypes")
	private Class classe = (Class) ((ParameterizedType) getClass()
			.getGenericSuperclass()).getActualTypeArguments()[0];

	public GenericaDAOImpl(EntityManager manager) {
		super();
		this.manager = manager;
	}

	
	@Override
	public T salvar(T objeto) throws DataException {
		T obj = null;
		try {

			obj = manager.merge(objeto);
			manager.flush();
			
		} catch (EntityExistsException e) {
			throw new DataException(e);
		} catch (PersistenceException e) {
			throw new DataException(e);
		}
		return obj;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T recuperarPorId(Integer id) throws DataException {
		T objeto = null;
		try {

			objeto = (T) manager.find(classe, id);
		} catch (EntityNotFoundException e) {
			throw new DataException(e);
		} catch (NonUniqueResultException e) {
			throw new DataException(e);
		} catch (NoResultException e) {
			throw new DataException(e);
		} catch (QueryTimeoutException e) {
			throw new DataException(e);
		} catch (PersistenceException e) {
			throw new DataException(e);
		}
		return objeto;
	}

	@Override
	public void deletar(T entity) throws DataException {
		try {

			T entityToBeRemoved = manager.merge(entity);
			manager.remove(entityToBeRemoved);
			manager.flush();
			manager.clear();
		} catch (PersistenceException e) {
			throw new DataException(e);
		}

	}

	@SuppressWarnings({ "unchecked" })
	@Override
	public List<T> recuperarTodos() throws DataException {
		List<T> objetos = null;
		try {
			// String ordem = null;
			StringBuffer querys = new StringBuffer("SELECT obj FROM "
					+ classe.getCanonicalName() + " obj");
			// if(ordem!=null){
			// querys.append("order by"+ordem);
			// }
			Query query = manager.createQuery(querys.toString());
			objetos = query.getResultList();
		} catch (EntityNotFoundException e) {
			throw new DataException(e);
		} catch (NonUniqueResultException e) {
			throw new DataException(e);
		} catch (NoResultException e) {
			throw new DataException(e);
		} catch (QueryTimeoutException e) {
			throw new DataException(e);
		} catch (PersistenceException e) {
			throw new DataException(e);
		}
		return objetos;
	}

}
