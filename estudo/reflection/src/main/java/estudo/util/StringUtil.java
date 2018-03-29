package estudo.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

	private static StringUtil instance;
	private static final String DOIS_ESPACO = " ";
	private static final String ESPACO = " ";

	private StringUtil() {

	}

	public static StringUtil getInstance() {
		if (instance == null) {
			synchronized (StringUtil.class) {
				if (instance == null) {
					instance = new StringUtil();
				}
			}
		}
		return instance;
	}

	/**
	 * @param valor
	 * @return
	 */
	public String trim(String valor) {
		return valor != null ? removerEspacosDuplicados(rtrim(ltrim(valor))) : null;
	}

	/**
	 * 
	 * Remover os espaços duplicados entre as strings deixando somente um espaço
	 *
	 * @return
	 * @throws Exception
	 */
	private String removerEspacosDuplicados(String str) {
		String patternStr = "\\s+";
		String replaceStr = DOIS_ESPACO;
		Pattern pattern = Pattern.compile(patternStr);
		Matcher matcher = pattern.matcher(str);
		return matcher.replaceAll(replaceStr);
	}

	/**
	 * REMOVER OS ESPAÇO EM BRANCO NO FINAL DA STRING
	 * 
	 * @param valor
	 * @return
	 */
	private String rtrim(String valor) {
		return valor != null ? valor.replaceAll("\\s+$", ESPACO) : null;
	}

	/**
	 * REMOVER OS ESPAÇO EM BRANCO NO INICIO DA STRING
	 * 
	 * @param valor
	 * @return
	 */
	private String ltrim(String valor) {
		return valor != null ? valor.replaceAll("^\\s+", ESPACO) : null;
	}

	public void trim(Object objeto) {

	}
}
