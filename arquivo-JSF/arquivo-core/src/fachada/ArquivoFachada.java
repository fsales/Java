package fachada;

import java.util.List;

import org.apache.log4j.Logger;

import dao.ArquivoDao;
import dominio.Arquivo;
import exception.RegraNegocioException;

public class ArquivoFachada {

	private ArquivoDao arqDao;

	private static Logger logger = Logger.getLogger(ArquivoFachada.class
			.getCanonicalName());

	public ArquivoFachada() {

		try {
			arqDao = (ArquivoDao) Class.forName(
					"dao.hibernate.ArquivoDaoImpl")
					.newInstance();
		} catch (Exception e) {
			logger.equals(e);
		}
	}

	public Arquivo alterar(Arquivo arq) throws RegraNegocioException {
		try {
			return arqDao.alterar(arq);
		} catch (Exception e) {
			throw new RegraNegocioException(e);
		}
	}

	public void salvar(Arquivo arq) throws RegraNegocioException {
		try {
			arqDao.salvar(arq);
		} catch (Exception e) {
			throw new RegraNegocioException(e);
		}
	}

	public void deletar(Integer id) throws RegraNegocioException {
		try {
			arqDao.deletar(id);
		} catch (Exception e) {
			throw new RegraNegocioException(e);
		}
	}

	public Arquivo recuperarPorId(Integer id) throws RegraNegocioException {
		try {
			return arqDao.recuperarPorId(id);
		} catch (Exception e) {
			throw new RegraNegocioException(e);
		}
	}

	public List<Arquivo> recuperarTodos() throws RegraNegocioException {
		try {
			return arqDao.recuperarTodos();
		} catch (Exception e) {
			throw new RegraNegocioException(e);
		}
	}

	public List<Arquivo> paginacao(int posicao, int quantidade)
			throws RegraNegocioException {
		try {
			return arqDao.paginacao(posicao, quantidade);
		} catch (Exception e) {
			throw new RegraNegocioException(e);
		}
	}
}
