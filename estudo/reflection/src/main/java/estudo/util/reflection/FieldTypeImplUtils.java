package estudo.util.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FieldTypeImplUtils implements IFieldUtil {

	private static final String CARACTER_PONTO_FINAL = ".";
	private static final Logger LOG = Logger.getLogger(FieldTypeImplUtils.class.getName());

	/**
	 * Obter o campo especificado na classe. Se o campo não é encontrado no
	 * Própria classe irá verificar de forma recursiva os superclasse
	 * 
	 * @param clazz
	 *            a definição de classe que contém o campo
	 * @param type
	 *            tipo
	 * @return o campo , se for encontrado , caso contrário, uma exceção é
	 *         lançada
	 * @throws Exception
	 */
	public List<Field> getField(Class<?> clazz, FieldUtilTypeEnum type) throws Exception {
		try {
			return buscarFieldRecursivo(clazz, type);

		} catch (InvocationTargetException invocationTargetException) {
			throw new Exception(invocationTargetException.getTargetException());
		} catch (NoSuchFieldException ise) {
			LOG.log(Level.SEVERE, ise.getMessage(), ise);
			throw new RuntimeException(ise);
		} catch (IllegalAccessException iae) {
			throw new RuntimeException(iae);
		}
	}

	private List<Field> buscarFieldRecursivo(Class<?> clazz, FieldUtilTypeEnum type) throws Exception {
		if (clazz == null) {
			return null;
		}

		List<Field> listaField = buscarField(clazz.getDeclaredFields(), type);

		return listaField != null && !listaField.isEmpty() ? listaField
				: clazz.getSuperclass() == null ? null : getField(clazz.getSuperclass(), type);
	}

	private List<Field> buscarField(Field[] declaredFields, FieldUtilTypeEnum type) throws Exception {
		List<Field> listaField = new ArrayList<Field>();
		for (Field fieldTemp : declaredFields) {
			fieldTemp.setAccessible(Boolean.TRUE);
			if (fieldTemp.getType() != null
					&& fieldTemp.getType().getName().equalsIgnoreCase(type.getType().getName())) {
				listaField.add(fieldTemp);
			}else{
				
				getField(fieldTemp.getType(), type);
			}
		}
		return listaField;
	}

	public void set(Object bean, FieldUtilTypeEnum type, Object newValue) throws Exception {

		Class<?> componentClass = bean.getClass();
		Object value = bean;
		List<Field> listaField = null;
		try {
			listaField = getField(componentClass, type);
			for (Field field : listaField) {
				if (field.isAccessible() && field.get(bean) != null) {

					value = trimObject(value, field);
				}
			}
		} catch (InvocationTargetException invocationTargetException) {
			throw new Exception(invocationTargetException.getTargetException());
		} catch (IllegalAccessException iae) {
			throw new RuntimeException(iae);
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new RuntimeException(illegalArgumentException);
		}
	}

	private Object trimObject(Object value, Field field) throws IllegalAccessException {
		Object fieldTemp = field.get(value);
		Object valorRetorno = null;
		if (fieldTemp != null) {
			field.set(value, trim((String) fieldTemp));
			valorRetorno = field.get(value);
		}
		return valorRetorno;
	}

	private String trim(String string) {
		return "aaa";
	}

	@Override
	public Object get(Object bean, String fieldName) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void set(Object bean, String fieldName, Object newValue) throws Exception {
		// TODO Auto-generated method stub

	}

}
