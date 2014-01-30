package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import aplicacion.modelo.encontrables.Pozo;

public class PozoTest {
	
	@Test
	public void testCrear(){
		Pozo pozo = new Pozo();
		assertTrue(pozo.penalizacion() == 3);
	}

}
