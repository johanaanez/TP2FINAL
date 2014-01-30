package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import aplicacion.modelo.encontrables.ControlPolicial;

public class ControlPolicialTest {
	
	@Test
	public void testCrear(){
		ControlPolicial controlPolicial = new ControlPolicial();
		assertTrue(controlPolicial.penalizacion() == 3);
	}

}