package estudo.util.reflection;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ReflectionUtil {
	private static final String SET = "set";
	private static final String GET = "get";

	private ReflectionUtil() {
		super();

	}

	/**
	 * Método recursivo utilizado para remover espaços de uma string.<br>
	 * Entra na hierarquia de objetos para retirar os espaços em branco<br>
	 * <br>
	 * Ex.: "test ", " teste", " teste teste2 ", "teste ". Ao aplicar o método,
	 * ele retira os espaços em "branco".<br>
	 * 
	 * Resultado: "teste" ou "teste teste2"
	 *
	 * @param object
	 * @return
	 * @throws Exception
	 */
	public static Object trimReflective(Object object) throws Exception {
		if (object == null) {
			return null;
		}

		Class<? extends Object> c = object.getClass();

		// Introspector usage to pick the getters conveniently thereby
		// excluding the Object getters
		iterarDescriptor(object, c);

		return object;
	}

	private static void iterarDescriptor(Object object, Class<? extends Object> c) throws Exception {
		for (PropertyDescriptor propertyDescriptor : Introspector.getBeanInfo(c, Object.class)
				.getPropertyDescriptors()) {
			Method method = propertyDescriptor.getReadMethod();
			String name = method.getName();

			// If the current level of Property is of type String
			trimString(method, object, name, c);

			// If an Object Array of Properties - added additional check to
			// avoid getBytes returning a byte[] and process
			trimObjectArray(method, object);

			// If a String array
			trimStringArray(method, object, name, c);

			// Collections start
			trimColleciton(method.getReturnType(), object, method);

			// Separate placement for Map with special conditions to process
			// keys and values
			trimMapObject(method, object);
		}
	}

	private static void trimMapObject(Method method, Object object) throws Exception {
		// Separate placement for Map with special conditions to process
		// keys and values
		if (method.getReturnType().equals(Map.class)) {
			Map mapProperty = (Map) method.invoke(object);
			trimMapProperty(mapProperty);
		} else {
			Object property = (Object) method.invoke(object);
			trimObjetcProperti(property);
		}
	}

	private static void trimObjetcProperti(Object property) throws Exception {
		if (property != null) {
			trimReflective(property);
		}
	}

	private static void trimMapProperty(Map mapProperty) throws Exception {
		if (mapProperty != null) {
			// Keys
			trimKeysMap(mapProperty);

			// Values
			trimValuesMap(mapProperty);
		}
	}

	private static void trimValuesMap(Map mapProperty) throws Exception {
		// Values
		for (Map.Entry entry : (Set<Map.Entry>) mapProperty.entrySet()) {

			if (entry.getValue() instanceof String) {
				String element = (String) entry.getValue();
				trimSetElement(entry, element);
			} else {
				// Recursively revisit with the current property
				trimReflective(entry.getValue());
			}
		}
	}

	private static void trimSetElement(Map.Entry entry, String element) {
		if (element != null) {
			// entry.setValue(Util.trim(element));
		}
	}

	private static void trimKeysMap(Map mapProperty) throws Exception {
		// Keys
		for (int index = 0; index < mapProperty.keySet().size(); index++) {
			if (mapProperty.keySet().toArray()[index] instanceof String) {
				String element = (String) mapProperty.keySet().toArray()[index];
				trimMapProprtyString(mapProperty, element);
			} else {
				// Recursively revisit with the current property
				trimReflective(mapProperty.get(index));
			}

		}
	}

	private static void trimMapProprtyString(Map mapProperty, String element) {
		if (element != null) {
			// mapProperty.put(Util.trim(element), mapProperty.get(element));
			mapProperty.remove(element);
		}
	}

	private static void trimObjectArray(Method method, Object object) throws Exception {

		// If an Object Array of Properties - added additional check to
		// avoid getBytes returning a byte[] and process
		if (method.getReturnType().isArray() && !method.getReturnType().isPrimitive()
				&& !method.getReturnType().equals(String[].class) && !method.getReturnType().equals(byte[].class)) {

			// Type check for primitive arrays (would fail typecasting
			// in case of int[], char[] etc)
			if (method.invoke(object) instanceof Object[]) {
				Object[] objectArray = (Object[]) method.invoke(object);
				trimArrayObject(objectArray);
			}

		}
	}

	private static void trimArrayObject(Object[] objectArray) throws Exception {
		if (objectArray != null) {
			iterarArrayObjetos(objectArray);
		}
	}

	private static void iterarArrayObjetos(Object[] objectArray) throws Exception {
		for (Object obj : (Object[]) objectArray) {
			// Recursively revisit with the current property
			trimReflective(obj);
		}
	}

	private static void trimString(Method method, Object object, String name, Class<? extends Object> clazz)
			throws Exception {
		// If the current level of Property is of type String
		if (method.getReturnType().equals(String.class)) {
			String property = (String) method.invoke(object);
			if (property != null && !property.isEmpty()) {
				String nomeMetodo = SET + name.substring(3);

				if (isNameMethodExists(clazz, nomeMetodo)) {
					Method setter = clazz.getMethod(SET + name.substring(3), new Class<?>[] { String.class });
					trimObject(object, property, setter);
				}
			}
		}
	}

	/**
	 * 
	 *
	 * @param clazz
	 * @param nomeMetodo
	 */
	private static boolean isNameMethodExists(Class<? extends Object> clazz, String nomeMetodo) {
		if (clazz != null && clazz.getMethods() != null) {
			for (Method methodTemp : clazz.getMethods()) {
				if (methodTemp.getName().equals(nomeMetodo)) {
					return Boolean.TRUE;
				}
			}
		}
		return Boolean.FALSE;
	}

	private static void trimObject(Object object, String property, Method setter)
			throws IllegalAccessException, InvocationTargetException {
		if (setter != null) {
			// Setter to trim and set the trimmed String value
			// setter.invoke(object, Util.trim(property));
		}
	}

	private static void trimStringArray(Method method, Object object, String name, Class<? extends Object> clazz)
			throws Exception {
		if (method.getReturnType().equals(String[].class)) {
			String[] propertyArray = (String[]) method.invoke(object);
			if (propertyArray != null) {

				String nomeMetodo = SET + name.substring(3);
				if (isNameMethodExists(clazz, nomeMetodo)) {
					Method setter = clazz.getMethod(nomeMetodo, new Class<?>[] { String[].class });
					trimSetter(object, propertyArray, setter);
				}

			}
		}
	}

	private static void trimSetter(Object object, String[] propertyArray, Method setter)
			throws IllegalAccessException, InvocationTargetException {
		if (setter != null) {
			String[] modifiedArray = new String[propertyArray.length];
			iterarArrayString(propertyArray, modifiedArray);
			// Explicit wrapping
			setter.invoke(object, new Object[] { modifiedArray });
		}
	}

	private static void iterarArrayString(String[] propertyArray, String[] modifiedArray) {
		for (int i = 0; i < propertyArray.length; i++) {
			if (propertyArray[i] != null) {
				// modifiedArray[i] = Util.trim(propertyArray[i]);
			}
		}
	}

	// Collections start
	private static void trimColleciton(Class<?> returnType, Object object, Method method) throws Exception {
		if (Collection.class.isAssignableFrom(returnType)) {
			Collection collectionProperty = (Collection) method.invoke(object);
			if (collectionProperty != null) {
				iterarCollecao(collectionProperty);
			}
		}
	}

	private static void iterarCollecao(Collection collectionProperty) throws Exception {
		for (int index = 0; index < collectionProperty.size(); index++) {
			if (collectionProperty.toArray()[index] instanceof String) {
				String element = (String) collectionProperty.toArray()[index];

				checkListArray(element, collectionProperty, index);
			} else {
				// Recursively revisit with the current property
				trimReflective(collectionProperty.toArray()[index]);
			}
		}
	}

	private static void checkListArray(String element, Collection collectionProperty, int index) throws Exception {
		if (element != null) {
			// Check if List was created with
			// Arrays.asList (non-resizable Array)
			if (collectionProperty instanceof List) {
				// ((List) collectionProperty).set(index, Util.trim(element));
			} else {
				collectionProperty.remove(element);
				// collectionProperty.add(Util.trim(element));
			}
		}
	}
}
