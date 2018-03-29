package estudo.gerador.xml;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class GeradorXML {

	public static String getXML(Object object) throws Exception {
		Class<?> clazz = object.getClass();
		StringBuilder sb = new StringBuilder("<").append(clazz.getSimpleName()).append("> \n");

		for (Field field : clazz.getDeclaredFields()) {
			sb.append("<").append(field.getName()).append("> \n");
			field.setAccessible(Boolean.TRUE);
			sb.append(field.get(object));
			sb.append("</").append(field.getName()).append("> \n");
		}

		sb.append("</").append(clazz.getSimpleName()).append("> \n");
		return sb.toString();
	}

	public static Map<String, Object> getValorAtributos(Object object) throws Exception {
		Map<String, Object> retorno = new HashMap<String, Object>();

		Class<?> clazz = object.getClass();

		for (Field field : clazz.getDeclaredFields()) {
			field.setAccessible(Boolean.TRUE);

			retorno.put(field.getName(), field.get(object));
		}

		return retorno;
	}

	public static void main(String[] args) throws Exception {
		Usuario u = new Usuario("login", "senha", "email", "papel", Boolean.TRUE);
		String xml = GeradorXML.getXML(u);

		System.out.println(xml);
		
		System.out.println(GeradorXML.getValorAtributos(u));
	}
}
