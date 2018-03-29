package estudo.util.reflection;

public interface IFieldUtil {

	/**
	 * 
	 * Retorna o valor de um campo
	 * 
	 * @param bean
	 *            objeto
	 * @param fieldName
	 *            o nome do campo , com o " . " separando propriedades aninhadas
	 * @return o valor do atributo da classe
	 */
	Object get(Object bean, String fieldName) throws Exception;

	/**
	 * Define o valor de um campo
	 * 
	 * @param bean
	 *            objeto
	 * @param fieldName
	 *            o nome do campo , com o " . " separando propriedades aninhadas
	 * @param newValue
	 *            novo valor
	 */
	void set(Object bean, String fieldName, Object newValue) throws Exception;

}