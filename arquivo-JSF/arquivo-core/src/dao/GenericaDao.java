package dao;

import java.util.List;

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
public interface GenericaDao<T> {

	/***
	 * 
	 * @param objeto
	 *            a ser alterado. Exemplo: Usuario alterar(usuario);
	 * @return objeto alterado. Exemplo: Usuario
	 * @throws DataAcessException
	 */
	T alterar(T objeto) throws DataAcessException;

	/***
	 * 
	 * @param objeto
	 *            a ser salvo. Exemplo: salvar(usuario);
	 * @return void
	 * @throws DataAcessException
	 */
	void salvar(T objeto) throws DataAcessException;

	/***
	 * 
	 * @param id
	 *            do objeto a ser deletado. Exemplo: deletar(1);
	 * @return void
	 * @throws DataAcessException
	 */
	void deletar(Integer id) throws DataAcessException;

	/***
	 * 
	 * @return uma lista de todos os atributos da tabela. Exemplo:
	 *         Collection<Usuario> usuarios = new HashSet<Usuario>(); usuarios =
	 *         d.recuperarTodos();
	 * @return List
	 * @throws DataAcessException
	 */
	List<T> recuperarTodos() throws DataAcessException;

	/***
	 * 
	 * @param id
	 *            do objeto a ser pesquisado. Exemplo: Usuario usu =
	 *            recuperarPorId(1);
	 * @return o objeto apos a consulta. Exemplo: usu
	 * @throws DataAcessException
	 */
	T recuperarPorId(Integer id) throws DataAcessException;

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
	List<T> paginacao(int posicao, int quantidade) throws DataAcessException;

}
