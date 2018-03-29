package estudo.util.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang3.StringUtils;

/**
 * Classe util reflection
 *
 */
public class FieldNameImplUtils implements IFieldUtil {

	private static final String CARACTER_PONTO_FINAL = ".";
	private static final Logger LOG = Logger.getLogger(FieldNameImplUtils.class.getName());
	private static IFieldUtil instance;

	private FieldNameImplUtils() {

	}

	private void fieldValido(Field field, String fieldName) throws NoSuchFieldException {
		if (field == null) {
			throw new NoSuchFieldException(fieldName + "Field não encontrado!");
		}
	}

	private Field buscarFieldRecursivo(Class<?> clazz, String fieldName) throws Exception {
		Field field = buscarField(clazz.getDeclaredFields(), fieldName);

		return field != null ? field : getField(clazz.getSuperclass(), fieldName);
	}

	private Field buscarField(Field[] declaredFields, String fieldName) throws Exception {
		Field field = null;
		for (Field fieldTemp : declaredFields) {
			fieldTemp.setAccessible(Boolean.TRUE);
			if (fieldTemp.getName().equalsIgnoreCase(fieldName)) {
				field = fieldTemp;
				break;
			}
		}
		return field;
	}

	/**
	 * Obter o campo especificado na classe. Se o campo não é encontrado no
	 * Própria classe irá verificar de forma recursiva os superclasse
	 * 
	 * @param clazz
	 *            a definição de classe que contém o campo
	 * @param fieldName
	 *            definição do field a ser recuperado
	 * @return o campo , se for encontrado , caso contrário, uma exceção é
	 *         lançada
	 */
	public Field getField(Class<?> clazz, String fieldName) throws Exception {
		try {
			Field field = buscarFieldRecursivo(clazz, fieldName);
			fieldValido(field, fieldName);
			return field;

		} catch (InvocationTargetException invocationTargetException) {
			throw new Exception(invocationTargetException.getTargetException());
		} catch (NoSuchFieldException ise) {
			LOG.log(Level.SEVERE, ise.getMessage(), ise);
			throw new RuntimeException(ise);
		} catch (IllegalAccessException iae) {
			throw new RuntimeException(iae);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see estudo.alura.util.reflection.IFieldUtil#get(java.lang.Object,
	 * java.lang.String)
	 */
	@Override
	public Object get(Object bean, String fieldName) throws Exception {
		String[] nestedFields = StringUtils.split(fieldName, CARACTER_PONTO_FINAL);
		Class<?> componentClass = bean.getClass();
		Object value = bean;

		try {

			for (String nestedField : nestedFields) {
				Field field = getField(componentClass, nestedField);
				field.setAccessible(true);

				value = field.get(value);
				if (value != null) {
					componentClass = value.getClass();
				}
			}
		} catch (InvocationTargetException invocationTargetException) {
			throw new Exception(invocationTargetException.getTargetException());
		} catch (IllegalAccessException iae) {
			throw new RuntimeException(iae);
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new RuntimeException(illegalArgumentException);
		}

		return value;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see estudo.alura.util.reflection.IFieldUtil#set(java.lang.Object,
	 * java.lang.String, java.lang.Object)
	 */
	@Override
	public void set(Object bean, String fieldName, Object newValue) throws Exception {
		String[] nestedFields = StringUtils.split(fieldName, CARACTER_PONTO_FINAL);
		Class<?> componentClass = bean.getClass();
		Object value = bean;
		Field field = null;

		try {
			for (int i = 0; i < nestedFields.length; i++) {
				String nestedField = nestedFields[i];
				field = getField(componentClass, nestedField);
				field.setAccessible(true);

				if (i == nestedFields.length - 1) {
					break;
				}

				value = field.get(value);
				if (value != null) {
					componentClass = value.getClass();
				}
			}

			field.set(value, newValue);
		} catch (InvocationTargetException invocationTargetException) {
			throw new Exception(invocationTargetException.getTargetException());
		} catch (IllegalAccessException iae) {
			throw new IllegalStateException(iae);
		}
	}

	/**
	 * retorna a instancia da classe
	 * 
	 * @return
	 */
	public static IFieldUtil getInstance() {
		if (instance == null) {
			synchronized (FieldNameImplUtils.class) {
				if (instance == null) {
					instance = new FieldNameImplUtils();
				}
			}
		}
		return instance;
	}
}
