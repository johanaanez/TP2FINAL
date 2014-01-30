package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import aplicacion.modelo.encontrables.SorpresaPorcentual;

public class SorpresaPorcentualTest {

	@Test
	public void testCrear(){
		SorpresaPorcentual sorpresaPorcentual = new SorpresaPorcentual(20);
		assertTrue(sorpresaPorcentual.porcentaje() == 20);
	}
	
}