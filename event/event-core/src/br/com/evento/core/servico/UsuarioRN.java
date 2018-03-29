package br.com.evento.core.servico;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.evento.core.dao.exception.DataException;
import br.com.evento.core.dao.factory.FactoryDAO;
import br.com.evento.core.dao.repositorio.UsuarioDAO;
import br.com.evento.core.dominio.Usuario;
import br.com.evento.core.servico.exception.RNException;

public class UsuarioRN {

	private UsuarioDAO usuDao;

	public UsuarioRN(EntityManager manager) {
		super();

		usuDao = FactoryDAO.criarUsuarioDAO(manager);

	}

	public Usuario salvar(Usuario usuario) throws RNException {
		try {
			return usuDao.salvar(usuario);
		} catch (DataException e) {
			throw new RNException(e);
		}
	}

	public Usuario recuperarPorId(Integer id) throws RNException {
		try {
			return usuDao.recuperarPorId(id);
		} catch (DataException e) {
			throw new RNException(e);
		}
	}

	public void deletar(Usuario usuario) throws RNException {
		try {
			usuDao.deletar(usuario);
		} catch (DataException e) {
			throw new RNException(e);
		}
	}

	public List<Usuario> recuperarTodos() throws RNException {
		try {
			return usuDao.recuperarTodos();
		} catch (DataException e) {
			throw new RNException(e);
		}
	}

	public Usuario buscaPorLogin(String login) throws RNException {
		try {
			return usuDao.buscaPorLogin(login);
		} catch (DataException e) {
			throw new RNException(e);
		}

	}

}
