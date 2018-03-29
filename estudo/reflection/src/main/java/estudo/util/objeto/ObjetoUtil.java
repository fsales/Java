package estudo.util.objeto;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang3.StringUtils;

import estudo.util.reflection.FieldNameImplUtils;

public class ObjetoUtil {

	private static ObjetoUtil instance;
	private static final Logger LOG = Logger.getLogger(ObjetoUtil.class.getName());

	private ObjetoUtil() {

	}

	public static ObjetoUtil getInstance() {
		if (instance == null) {
			synchronized (ObjetoUtil.class) {
				if (instance == null) {
					instance = new ObjetoUtil();
					LOG.log(Level.INFO, "criado instancia da classe");
				}
			}
		}
		return instance;
	}

	/**
	 * verica se o atributo do objeto esta NULL
	 * 
	 * @param objeto
	 * @param nomeAtributo
	 * @return
	 * @throws Exception
	 */
	public Boolean isAtributoNull(Object objeto, String nomeAtributo) throws Exception {
		boolean retorno = Boolean.TRUE;
		if (objeto != null && !StringUtils.isBlank(nomeAtributo)) {

			Object object = FieldNameImplUtils.getInstance().get(objeto, nomeAtributo);
			retorno = object == null;
		}
		return retorno;
	}

	/**
	 * @param object
	 * @return
	 */
	public boolean isObjectNull(Object object) {

		return object == null;
	}
}
