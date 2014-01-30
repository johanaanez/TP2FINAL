package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import aplicacion.modelo.juego.GPSChallenge;
import aplicacion.modelo.juego.dificultad.*;
import aplicacion.modelo.juego.*;

import java.beans.DefaultPersistenceDelegate;
import java.beans.XMLEncoder;
import java.beans.XMLDecoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class GPSChallengeTest {
	
	@Test
	public void testCrear(){
		GPSChallenge gpsChallenge = new GPSChallenge(new Facil());
		assertTrue(gpsChallenge.jugador() != null);
		assertTrue(gpsChallenge.escenario() != null);
	}
	
	//@Test
	public void testAgregarJugador(){
		GPSChallenge GPS = new GPSChallenge(new Facil());
		String miNombre = "macielitus";
		GPS.agregarUsuario(miNombre);
		
		assertTrue(GPS.usuarios().get(0) == miNombre);
		
	}

}