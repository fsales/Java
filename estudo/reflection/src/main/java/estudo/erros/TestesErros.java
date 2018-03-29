package estudo.erros;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestesErros {

	public static void main(String[] args) throws Exception {
		TestesErros obj = new TestesErros();
		Class<?> clazz = obj.getClass();
		/** **/
		try {
			Method m = clazz.getDeclaredMethod("metodo", String.class);
			m.invoke(obj, "");
		} catch (InvocationTargetException ex) {
			throw new Exception(ex.getTargetException());
		}

	}

	public void metodo(String s) {
		System.out.println(".....");
	}
}
