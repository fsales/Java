package dao.hibernate;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;

import dao.GenericaDao;
import entity.manager.JPAUtil;
import exception.DataAcessException;

/***
 * 
 * @author Fabio Sales
 * 
 * @param <T>
 *            Classe Generica para salvar, alterar, remover, listar todos,
 *            listar por id e pagina, qualquer objeto basta passar a classe no
 *            lugar do T. Exemplo: GenericaDao<Usuario>
 */

public class GenericaDaoImpl<T> extends JPAUtil implements GenericaDao<T> {

	private EntityManager entityManager;
	private EntityTransaction transaction;

	@SuppressWarnings("rawtypes")
	private Class classe = ((Class) ((ParameterizedType) getClass()
			.getGenericSuperclass()).getActualTypeArguments()[0]);

	private static Logger logger = Logger.getLogger(GenericaDaoImpl.class
			.getCanonicalName());

	public GenericaDaoImpl() {
		try {

			entityManager = getFactory()
					.createEntityManager();
			transaction = entityManager.getTransaction();

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getStackTrace());
		}
	}

	/***
	 * 
	 * @param objeto
	 *            a ser alterado. Exemplo: Usuario alterar(usuario);
	 * @return objeto alterado. Exemplo: Usuario
	 * @throws DataAcessException
	 */

	@Override
	public T alterar(T objeto) throws DataAcessException {
		try {
			transaction.begin();
			T obj = entityManager.merge(objeto);
			transaction.commit();
			return obj;
		} catch (Exception e) {
			transaction.rollback();
			throw new DataAcessException(e);
		} finally {
			// entityManager.clear();
			// entityManager.close();
		}

	}

	/***
	 * 
	 * @param objeto
	 *            a ser salvo. Exemplo: salvar(usuario);
	 * @return void
	 * @throws DataAcessException
	 */

	@Override
	public void salvar(T objeto) throws DataAcessException {
		try {
			transaction.begin();
			entityManager.persist(objeto);
			transaction.commit();

		} catch (Exception e) {
			transaction.rollback();
			throw new DataAcessException(e);
		} finally {
			// entityManager.clear();
			// entityManager.close();
		}

	}

	/***
	 * 
	 * @param id
	 *            do objeto a ser deletado. Exemplo: deletar(1);
	 * @return void
	 * @throws DataAcessException
	 */

	@Override
	public void deletar(Integer id) throws DataAcessException {
		try {
			transaction.begin();
			// System.out.println(">>>>>>>>>>>>"+recuperarPorId(id));
			entityManager.remove(recuperarPorId(id));
			transaction.commit();

		} catch (Exception e) {
			transaction.rollback();
			throw new DataAcessException(e);
		} finally {
			// entityManager.clear();
			// entityManager.close();
		}

	}

	/***
	 * 
	 * @return uma lista de todos os atributos da tabela. Exemplo:
	 *         Collection<Usuario> usuarios = new HashSet<Usuario>(); usuarios =
	 *         d.recuperarTodos();
	 * @return List
	 * @throws DataAcessException
	 */

	@SuppressWarnings("unchecked")
	@Override
	public List<T> recuperarTodos() throws DataAcessException {
		try {

			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
						
			CriteriaQuery<T> c = cb.createQuery(classe);
			Root<T> g = c.from(classe);
			c.select(g).getOrderList();

			TypedQuery<T> query = entityManager.createQuery(c);
			return query.getResultList();
		} catch (Exception e) {

			throw new DataAcessException(e);
		} finally {
			// entityManager.clear();
			// entityManager.close();
		}
	}

	/***
	 * 
	 * @param id
	 *            do objeto a ser pesquisado. Exemplo: Usuario usu =
	 *            recuperarPorId(1);
	 * @return o objeto apos a consulta. Exemplo: usu
	 * @throws DataAcessException
	 */

	@Override
	public T recuperarPorId(Integer id) throws DataAcessException {

		try {

			@SuppressWarnings("unchecked")
			T obj = ((T) entityManager.find(classe, id));

			return obj;
		} catch (Exception e) {

			throw new DataAcessException(e);
		} finally {
			// entityManager.clear();
			// entityManager.close();
		}

	}

	/***
	 * 
	 * @param posicao
	 *            indica de qual posição os objetos devem começar a ser
	 *            carregados.
	 * @param quantidade
	 *            indica o máximo de objetos que devem ser carregados. Exemplo:
	 *            Collection<Usuario> usuarios = new HashSet<Usuario>();
	 *            usuarios = d.paginacao(0, 3);
	 * @return retorna uma lista com os objetos paginados
	 * @throws DataAcessException
	 */

	@SuppressWarnings("unchecked")
	@Override
	public List<T> paginacao(int posicao, int quantidade)
			throws DataAcessException {

		try {

			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<T> c = cb.createQuery(classe);
			Root<T> g = c.from(classe);
			c.select(g);

			TypedQuery<T> query = entityManager.createQuery(c);
			query.setFirstResult(posicao);
			query.setMaxResults(quantidade);
			return query.getResultList();
		} catch (Exception e) {

			throw new DataAcessException(e);
		} finally {
			// entityManager.clear();
			// entityManager.close();
		}

	}

	public synchronized EntityManager getEntityManager() {
		return entityManager;
	}

	public synchronized EntityTransaction getTransaction() {
		return transaction;
	}

	@Override
	protected void finalize() throws Throwable {

		super.finalize();
		entityManager.clear();
		entityManager.close();
	}

}
