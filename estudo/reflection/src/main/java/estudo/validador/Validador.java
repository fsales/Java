package estudo.validador;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Validador {

	public static boolean validarObjeto(Object object) throws Exception {
		boolean resultado = Boolean.TRUE;
		Class<?> clazz = object.getClass();
		for (Method method : clazz.getDeclaredMethods()) {
			if (method.getName().startsWith("validar") && method.getReturnType() == boolean.class
					&& method.getParameterTypes().length == 0) {
				Boolean retorno = (Boolean) method.invoke(object);
				resultado = resultado && retorno.booleanValue();
			}
		}

		return resultado;
	}

	public static boolean getObjetoMethodTest(Object object) throws Exception {
		boolean resultado = Boolean.TRUE;
		Class<?> clazz = object.getClass();
		for (Method method : clazz.getDeclaredMethods()) {
			if (method.getName().startsWith("test") && method.getReturnType() == void.class
					&& method.getParameterTypes().length == 0) {
				try {
					method.invoke(object);
				} catch (InvocationTargetException ex) {
					throw new Exception(ex.getTargetException());
				}
			}
		}

		return resultado;
	}

	public static void main(String[] args) throws Exception {
		Usuario usu = new Usuario("login", "senhasenhasenha", "email@", "papel", true);
		System.out.println(Validador.validarObjeto(usu));
	}
}
