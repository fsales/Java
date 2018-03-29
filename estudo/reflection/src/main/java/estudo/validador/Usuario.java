package estudo.validador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Usuario {
	private String login;
	private String senha;
	private String email;
	private String papel;
	private Boolean ativo;
	private List<String> lista;
	private Set<String> listaset;
	private Map<String, String> mapa;
	private Usuario usu;

	public Usuario() {
		mapa = new HashMap<>();
		listaset = new HashSet<>();
		lista = new ArrayList<>();
	}

	public Usuario(String login, String senha, String email, String papel, Boolean ativo) {
		super();
		this.login = login;
		this.senha = senha;
		this.email = email;
		this.papel = papel;
		this.ativo = ativo;

	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPapel() {
		return papel;
	}

	public void setPapel(String papel) {
		this.papel = papel;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public boolean validarEmail() {
		return email.contains("@");
	}

	public boolean validarSenha() {
		return senha.length() > 8;
	}

	public List<String> getLista() {
		return lista;
	}

	public void setLista(List<String> lista) {
		this.lista = lista;
	}

	public Set<String> getListaset() {
		return listaset;
	}

	public void setListaset(Set<String> listaset) {
		this.listaset = listaset;
	}

	public Map<String, String> getMapa() {
		return mapa;
	}

	public void setMapa(Map<String, String> mapa) {
		this.mapa = mapa;
	}

	public Usuario getUsu() {
		return usu;
	}

	public void setUsu(Usuario usu) {
		this.usu = usu;
	}

}
