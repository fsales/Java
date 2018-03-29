package br.com.evento.core.dao.repositorio;

import br.com.evento.core.dao.exception.DataException;
import br.com.evento.core.dominio.Usuario;

public interface UsuarioDAO extends GenericaDAO<Usuario> {
	Usuario buscaPorLogin(String login) throws DataException;
}
