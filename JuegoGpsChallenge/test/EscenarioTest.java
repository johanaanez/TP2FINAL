package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Assert;
import org.junit.Test;

import aplicacion.modelo.encontrables.Encontrable;
import aplicacion.modelo.escenario.*;
import aplicacion.modelo.juego.dificultad.*;
import aplicacion.modelo.juego.*;


public class EscenarioTest {
	
	
	@Test
	public void testCrear(){
		Escenario escenario = new Escenario(8);
		assertTrue(escenario != null);
	}
	
	
	@Test
	public void testMapaNoEstaVacio() {
		
		Escenario escenario = new Escenario(8);
		
		boolean resultado1 = escenario.esquinasCargadas();
		
		Assert.assertEquals (true , resultado1);
		
	}
	
	@Test
	public void testEsquinasTienenCalleArriba(){
		
		Escenario escenario = new Escenario(8);
		
		boolean resultado = escenario.tienenCalleArriba();
		
		Assert.assertEquals (true , resultado);
		
	}
	
	@Test
	public void testEsquinasNoTienenCalleArriba(){
		
		Escenario escenario = new Escenario(8);
		
		boolean resultado = escenario.noTienenCalleArriba();
		
		Assert.assertEquals (true , resultado);
		
		
		
	}

	
	
	
	
	@Test
	public void testEsquinasTienenCalleAbajo(){
		
		Escenario escenario = new Escenario(8);
		boolean resultado = escenario.tienenCalleAbajo();
		
		Assert.assertEquals (true , resultado);

	}
	
	@Test
	public void testEsquinasNoTienenCalleAbajo(){
		
		Escenario escenario = new Escenario(8);
		boolean resultado = escenario.noTienenCalleAbajo();
		
		Assert.assertEquals (true , resultado);

	}
	
	@Test
	public void testEsquinasTienenCalleDerecha(){
		
		Escenario escenario = new Escenario(8);
		boolean resultado = escenario.tienenCalleDerecha();
		
		Assert.assertEquals (true , resultado);

	}
	
	@Test
	public void testEsquinasNoTienenCalleDerecha(){
		
		Escenario escenario = new Escenario(8);
		
		boolean resultado = escenario.noTienenCalleDerecha();
		
		Assert.assertEquals (true , resultado);

	}
	
	@Test
	public void testEsquinasTienenCalleIzquierda(){
		
		Escenario escenario = new Escenario(8);
		
		boolean resultado = escenario.tienenCalleIzquierda();
		
		Assert.assertEquals (true , resultado);

	}
	
	@Test
	public void testEsquinasNoTienenCalleIzquierda(){
		
		Escenario escenario = new Escenario(8);
		//escenario.llenarDeEsquinas();
		//escenario.llenarDeCalles();
		//escenario.unirEsquinasYCalles();
		boolean resultado = escenario.noTienenCalleIzquierda();
		
		Assert.assertEquals (true , resultado);

	}
	
	
	@Test
	public void testPruebaDeCalles(){
		
		Escenario escenario = new Escenario(8);
		
		Esquina unaEsquina = escenario.getEsquina(30);
		
		Esquina otraEsquina = escenario.getEsquina(38);
		
		Calle unaCalle = unaEsquina.getCalleAbajo();
		
		Calle otraCalle = otraEsquina.getCalleArriba();
		
		Assert.assertEquals(unaCalle, otraCalle);
		
	}
	
	@Test
	public void testCallesCorrectas(){
		
		Escenario escenario = new Escenario(8);
		
		boolean resultado = escenario.lasCallesEstanCorrectas();
		
		Assert.assertEquals (true , resultado);

	}
	
	
	@Test
	public void testEsquinaDeLargadaValida(){		
		
		Escenario escenario = new Escenario(8);
		assertTrue(escenario.validarEsquinaLargada());
		
	}
	
	
	@Test
	public void testEsquinaDeLlegadaValida(){		
		
		Escenario escenario = new Escenario(8);
		assertTrue(escenario.validarEsquinaLlegada());
		
	}	
	
	@Test
	public void testCantidadDeEncontrablesEnFacil(){
		Facil facil = new Facil();
		GPSChallenge GPS = new GPSChallenge(facil);
		int cantidad = GPS.escenario().cantidadDeEncontrables();
		
		assertEquals(cantidad,17);
	}
	
	@Test
	public void testCantidadDeEncontrablesEnModerado(){
		Medio moderado = new Medio();
		GPSChallenge GPS = new GPSChallenge(moderado);
		int cantidad = GPS.escenario().cantidadDeEncontrables();
		
		assertEquals(cantidad,28);
	}
	

	
	@Test
	public void testCantidadDeEncontrablesEnDificil(){
		Dificil dificil = new Dificil();
		GPSChallenge GPS = new GPSChallenge(dificil);
		int cantidad = GPS.escenario().cantidadDeEncontrables();
	
		assertEquals(cantidad,41);
		
		ArrayList<Calle> callesConEncontrables = GPS.escenario().getCallesConEncontrables();
		
		assertEquals(callesConEncontrables.size(),41);

	}
	
	@Test
	public void testEscenarioEnFacilConEncontrables(){
		
		Facil facil = new Facil();
		GPSChallenge GPS = new GPSChallenge(facil);
		
		Esquina esquina1 = GPS.escenario().getEsquina(58);
		Encontrable obstaculo1 = esquina1.getCalleDerecha().getEncontrable();
		
		Esquina esquina2 = GPS.escenario().getEsquina(49);
		Encontrable obstaculo2 = esquina2.getCalleDerecha().getEncontrable();
		
		Esquina esquina3 = GPS.escenario().getEsquina(42);
		Encontrable obstaculo3 = esquina3.getCalleAbajo().getEncontrable();
		
		Esquina esquina4 = GPS.escenario().getEsquina(46);
		Encontrable obstaculo4 = esquina4.getCalleAbajo().getEncontrable();
		
		assertTrue(obstaculo1 != null);
		
		assertTrue(obstaculo2 != null);
		
		assertTrue(obstaculo3 != null);

		assertTrue(obstaculo4 != null);

		
	}
	
	@Test
	public void testEscenarioEnModeradoConEncontrables(){
		

		Medio moderado = new Medio();
		GPSChallenge GPS = new GPSChallenge(moderado);
		
		Esquina esquina1 = GPS.escenario().getEsquina(86);
		Encontrable obstaculo1 = esquina1.getCalleDerecha().getEncontrable();
		
		Esquina esquina2 = GPS.escenario().getEsquina(95);
		Encontrable obstaculo2 = esquina2.getCalleDerecha().getEncontrable();
		
		Esquina esquina3 = GPS.escenario().getEsquina(84);
		Encontrable obstaculo3 = esquina3.getCalleAbajo().getEncontrable();
		
		Esquina esquina4 = GPS.escenario().getEsquina(88);
		Encontrable obstaculo4 = esquina4.getCalleAbajo().getEncontrable();
		
		assertTrue(obstaculo1 != null);
		
		assertTrue(obstaculo2 != null);
		
		assertTrue(obstaculo3 != null);

		assertTrue(obstaculo4 != null);
		
	}
	
	@Test
	public void testEscenarioEnDificilConEncontrables(){
		

		Dificil dificil = new Dificil();
		GPSChallenge GPS = new GPSChallenge(dificil);
		
		Esquina esquina1 = GPS.escenario().getEsquina(135);
		Encontrable obstaculo1 = esquina1.getCalleDerecha().getEncontrable();
		
		Esquina esquina2 = GPS.escenario().getEsquina(142);
		Encontrable obstaculo2 = esquina2.getCalleDerecha().getEncontrable();
		
		Esquina esquina3 = GPS.escenario().getEsquina(126);
		Encontrable obstaculo3 = esquina3.getCalleAbajo().getEncontrable();
		
		Esquina esquina4 = GPS.escenario().getEsquina(130);
		Encontrable obstaculo4 = esquina4.getCalleAbajo().getEncontrable();
		
		assertTrue(obstaculo1 != null);
		
		assertTrue(obstaculo2 != null);
		
		assertTrue(obstaculo3 != null);

		assertTrue(obstaculo4 != null);
		
	}
	
	@Test
	public void testEscenarioCallesConEcontrablesTienenEsquinas(){
		GPSChallenge GPS = new GPSChallenge(new Facil());
		ArrayList<Calle> listaCalles = GPS.escenario().getCallesConEncontrables();
		Iterator<Calle> iteradorCalles = listaCalles.iterator();
		
		
		while (iteradorCalles.hasNext()){
			Calle calle = iteradorCalles.next();
			assertTrue(calle.getEsquina1() != null);
			assertTrue(calle.getEsquina2() != null);
		}
	}
	
	@Test
	public void testCallesDeEscenarioTienenEsquinasCorrectas(){
		
		GPSChallenge GPS = new GPSChallenge(new Facil());
		ArrayList<Calle> listaCalles = GPS.escenario().getCallesConEncontrables();
		Iterator<Calle> iteradorCalles = listaCalles.iterator();
		
		
		while (iteradorCalles.hasNext()){
			Calle calle = iteradorCalles.next();
			int numEsquina1 = calle.getEsquina1().getNumeroEsquina();
			int numEsquina2 = calle.getEsquina2().getNumeroEsquina();
			int ancho = GPS.escenario().getAnchoDeMapa();

			assertTrue((numEsquina1 == numEsquina2-1) || (numEsquina1 == numEsquina2-ancho));
		}
		
	}
	
	
	
	@Test
	public void testEscenarioFacilCon17CallesConEcontrables(){
		GPSChallenge GPS = new GPSChallenge(new Facil());
		ArrayList<Calle> listaCalles = GPS.escenario().getCallesConEncontrables();
		
		assertTrue(listaCalles.size() == 17);
	}
	
	@Test
	public void testEscenarioMedioCon28CallesConEcontrables(){
		GPSChallenge GPS = new GPSChallenge(new Medio());
		ArrayList<Calle> listaCalles = GPS.escenario().getCallesConEncontrables();
		
		assertTrue(listaCalles.size() == 28);
	}
	
	@Test
	public void testEscenarioDificilcon41CallesConEcontrables(){
		GPSChallenge GPS = new GPSChallenge(new Dificil());
		ArrayList<Calle> listaCalles = GPS.escenario().getCallesConEncontrables();
		
		assertTrue(listaCalles.size() == 41);
	}
	
}