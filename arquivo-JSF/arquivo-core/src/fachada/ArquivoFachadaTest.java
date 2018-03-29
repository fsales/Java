package fachada;

import static org.junit.Assert.*;

import org.junit.Test;

import dominio.Arquivo;

public class ArquivoFachadaTest {

	@Test
	public void testSalvar() {
		
		try {
			ArquivoFachada fach = new ArquivoFachada();
			Arquivo arq = new Arquivo();
			arq.setArquivoContentType("text/javascript");
			arq.setArquivoFileName("teste");
			fach.salvar(arq);
		} catch (Exception e) {
			fail(e.getMessage());
		}
		
	}

}
