package estudo.reflection;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import estudo.validador.Usuario;

public class BuscarString {

	/**
	 * @param objeto
	 * @param filtro
	 * @return
	 * @throws Exception
	 */
	public static List<String> buscarStringAtributos(Object objeto, String filtro) throws Exception {
		try {
			List<String> lista = new ArrayList<String>();
			Class<?> classe = objeto.getClass();
			for (Field field : classe.getDeclaredFields()) {
				field.setAccessible(Boolean.TRUE);
				System.out.println(field.getName());
				Object value = field.get(objeto);
				if (value != null) {
					String valueSTR = value.toString();
					if (filtro.equalsIgnoreCase(valueSTR)) {
						lista.add(field.getName());
					}
				}
			}

			return lista;
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	public static void main(String[] args) throws Exception {
		BuscarString bbb = new BuscarString();
		Usuario usu = new Usuario();
		usu.setEmail("teste");
		List<String> buscarStringAtributos = bbb.buscarStringAtributos(usu, "email");
		//System.out.println(buscarStringAtributos);
	}

}
