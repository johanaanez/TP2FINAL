package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import aplicacion.modelo.juego.Jugador;

public class JugadorTest {
	
	@Test
	public void testCrear(){
		Jugador jugador = new Jugador();
		assertTrue(jugador.vehiculo() != null);
		assertTrue(jugador.nombre() == "Jugador1");
	}
	
	public void testCrearConNombre(){
		Jugador jugador = new Jugador("Pedro");
		assertTrue(jugador.vehiculo() != null);
		assertTrue(jugador.nombre() == "Pedro");
	}
	
	@Test
	public void testCambiarNombreJugador(){
		Jugador jugador = new Jugador();
		jugador.cambiarNombre("Pedro");
		assertTrue(jugador.nombre() == "Pedro");
	}

}
