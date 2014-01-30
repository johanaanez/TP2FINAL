package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import aplicacion.modelo.encontrables.SorpresaDesfavorable;

public class SorpresaDesfavorableTest {

	@Test
	public void testCrear(){
		SorpresaDesfavorable sorpresaDesfavorable = new SorpresaDesfavorable();
		assertTrue(sorpresaDesfavorable.porcentaje() == 25);
	}
	
}