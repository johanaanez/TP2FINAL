package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import aplicacion.modelo.encontrables.Obstaculo;

public class ObstaculoTest {
	
	@Test
	public void testCrear(){
		Obstaculo obstaculo = new Obstaculo(1);
		assertTrue(obstaculo != null);
		assertTrue(obstaculo.penalizacion() == 1);
	}

}
