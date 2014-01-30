package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import aplicacion.modelo.encontrables.SorpresaFavorable;

public class SorpresaFavorableTest {

	@Test
	public void testCrear(){
		SorpresaFavorable sorpresaFavorable = new SorpresaFavorable();
		assertTrue(sorpresaFavorable.porcentaje() == 20);
	}
	
}