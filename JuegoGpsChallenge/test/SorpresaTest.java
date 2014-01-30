package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import aplicacion.modelo.encontrables.Sorpresa;

public class SorpresaTest {

	@Test
	public void testCrear(){
		Sorpresa sorpresa = new Sorpresa();
		assertTrue(sorpresa != null);
	}
	
}
