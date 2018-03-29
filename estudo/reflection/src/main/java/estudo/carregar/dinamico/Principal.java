package estudo.carregar.dinamico;

import java.util.List;
import java.util.Map;

public class Principal {

	public static void main(String[] args) throws Exception {

		Mapeador map = new Mapeador();
		map.load("src\\main\\resources\\classes.prop");

		List<?> l = map.getInstancia(List.class);
		System.out.println(l.getClass());

		System.out.println(map.getImplementacao(List.class));
		System.out.println(map.getImplementacao(Map.class));

		IExemplo i = map.getInstancia(IExemplo.class, "Teste");
	}

}
