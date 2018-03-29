package estudo.carregar.dinamico;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@SuppressWarnings("unchecked")
public class Mapeador {
	private Map<Class<?>, Class<?>> mapa = new HashMap<>();

	public void load(String nomeArquivo) throws FileNotFoundException, IOException, ClassNotFoundException {
		Properties pro = new Properties();
		pro.load(new FileInputStream(new File(nomeArquivo).getCanonicalPath()));

		for (Object properties : pro.keySet()) {
			Class<?> interf = Class.forName(properties.toString());
			Class<?> impl = Class.forName(pro.get(properties).toString());
			if (interf.isAssignableFrom(impl)) {
				mapa.put(interf, impl);
			}
		}
	}

	public Class<?> getImplementacao(Class<?> interfa) {
		return mapa.get(interfa);
	}

	public <E> E getInstancia(Class<E> interf) throws InstantiationException, IllegalAccessException {
		Class<?> impl = mapa.get(interf);
		return ((E) impl.newInstance());
	}

	public <E> E getInstancia(Class<E> interf, Object... parametros) throws Exception {
		Class<?> impl = mapa.get(interf);

		Class<?>[] tiposConstrutor = new Class<?>[parametros.length];
		for (int i = 0; i < tiposConstrutor.length; i++) {
			tiposConstrutor[i] = parametros[i].getClass();
		}

		Constructor<?> c = impl.getConstructor(tiposConstrutor);

		return ((E) c.newInstance(parametros));
	}
}
