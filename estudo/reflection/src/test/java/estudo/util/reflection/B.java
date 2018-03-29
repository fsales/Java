package estudo.util.reflection;

public class B {

	private C classeC;
	private long id;
	private long[] lista = { 1L, 2L, 3 };
	private String login;

	public B() {
		id = 1L;
	}

	public C getClasseC() {
		return classeC;
	}

	public void setClasseC(C classeC) {
		this.classeC = classeC;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long[] getLista() {
		return lista;
	}

	public void setLista(long[] lista) {
		this.lista = lista;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

}
