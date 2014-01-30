package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import aplicacion.modelo.encontrables.Piquete;

public class PiqueteTest {
	
	@Test
	public void testCrear(){
		Piquete piquete = new Piquete();
		assertTrue(piquete.penalizacion() == 2);
	}

}