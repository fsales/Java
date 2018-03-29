package br.com.evento.core.dao.repositorio;

import java.util.List;

import br.com.evento.core.dao.exception.DataException;
import br.com.evento.core.dominio.Cidade;
import br.com.evento.core.dominio.Estado;

public interface CidadeDAO extends GenericaDAO<Cidade> {
	
	List<Cidade> consultaCidades(Estado estado) throws DataException;

}
