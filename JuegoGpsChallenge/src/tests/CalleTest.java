package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import aplicacion.modelo.encontrables.Pozo;
import aplicacion.modelo.escenario.Calle;

public class CalleTest {
	
	@Test
	public void testCrear(){
		Calle calle = new Calle();
		assertTrue(calle != null);
	}

	@Test
	public void testSettearEncontrable(){
		
		Calle unaCalle = new Calle();
		Pozo unPozo = new Pozo();
		unaCalle.agregarEncontrable(unPozo);
		
		boolean actual = unaCalle.tieneEncontrable();
		assertTrue(actual);
	}

}


