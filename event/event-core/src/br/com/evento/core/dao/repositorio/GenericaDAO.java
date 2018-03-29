package br.com.evento.core.dao.repositorio;

import java.util.List;

import br.com.evento.core.dao.exception.DataException;

public interface GenericaDAO<T> {

	T salvar(T objeto) throws DataException;

	T recuperarPorId(Integer id) throws DataException;

	void deletar(T entity) throws DataException;

	List<T> recuperarTodos() throws DataException;
	

}
