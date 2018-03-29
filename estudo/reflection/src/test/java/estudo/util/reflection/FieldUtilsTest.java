package estudo.util.reflection;

import static org.junit.Assert.assertEquals;

import javax.sound.midi.Synthesizer;

import org.junit.Test;

import estudo.util.reflection.FieldNameImplUtils;
import estudo.util.reflection.FieldTypeImplUtils;
import estudo.util.reflection.FieldUtilTypeEnum;

public class FieldUtilsTest {

	private static final String VALOR_ESPERADO = "teste";

	@Test
	public void getValueRecursivo() throws Exception {
		A classeA = new A();
		classeA.setClasseB(new B());
		classeA.getClasseB().setClasseC(new C());
		classeA.getClasseB().getClasseC().setNome(VALOR_ESPERADO);
		classeA.setNome("fabio");
		FieldNameImplUtils.getInstance().get(classeA, "nome");
		assertEquals(VALOR_ESPERADO, FieldNameImplUtils.getInstance().get(classeA, "classeB.classeC.nome"));
	}

	@Test
	public void getFieldPorType() throws Exception {
		A classeA = new A();
		classeA.setClasseB(new B());
		classeA.getClasseB().setClasseC(new C());
		classeA.getClasseB().getClasseC().setNome(VALOR_ESPERADO);
		classeA.setNome(VALOR_ESPERADO);
		
		// FieldNameImplUtils.getInstance().getFieldPorTypeName(new A(),
		// Long.class);
		FieldTypeImplUtils s = new FieldTypeImplUtils();
		s.set(classeA, FieldUtilTypeEnum.STRING, "teste2");
		System.out.println(classeA.getClasseB().getClasseC().getNome());
		System.out.println(classeA.getNome());
	}

}