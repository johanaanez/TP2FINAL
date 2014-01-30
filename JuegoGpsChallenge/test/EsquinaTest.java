package tests;

import static org.junit.Assert.*;
import aplicacion.modelo.escenario.*;

import org.junit.Assert;
import org.junit.Test;

public class EsquinaTest {
	
	@Test
	public void testCrear(){
		Esquina esquina = new Esquina();
		assertTrue(esquina != null);
	}

	@Test
	public void testEsquinaVacia(){
		
		Esquina unaEsquina = new Esquina();
		
		Calle calle = unaEsquina.getCalleArriba();
		
		Assert.assertEquals (null , calle);
		
		Calle calle1 = unaEsquina.getCalleArriba();
		
		Assert.assertEquals (null , calle1);
		
		Calle calle2 = unaEsquina.getCalleArriba();
		
		Assert.assertEquals (null , calle2);
		
		Calle calle3 = unaEsquina.getCalleArriba();
		
		Assert.assertEquals (null , calle3);
		
		
		
	}
	
	
}