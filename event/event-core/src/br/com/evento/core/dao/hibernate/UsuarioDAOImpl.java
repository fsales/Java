package br.com.evento.core.dao.hibernate;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.evento.core.dao.exception.DataException;
import br.com.evento.core.dao.repositorio.UsuarioDAO;
import br.com.evento.core.dominio.Usuario;

public class UsuarioDAOImpl extends GenericaDAOImpl<Usuario> implements
		UsuarioDAO {
	private EntityManager manager;

	public UsuarioDAOImpl(EntityManager manager) {
		super(manager);
		this.manager = manager;

	}

	@Override
	public Usuario buscaPorLogin(String login) throws DataException {
		Usuario usuario = null;
		try {
			String hql = "SELECT u FROM Usuario u WHERE u.email = ?1";
			Query consulta = manager.createQuery(hql);
			consulta.setParameter(1, login);
			usuario = (Usuario) consulta.getSingleResult();
		} catch (Exception e) {
			throw new DataException(e);

		}

		return usuario;

	}
}
