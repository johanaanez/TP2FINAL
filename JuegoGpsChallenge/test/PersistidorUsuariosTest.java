package tests;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import aplicacion.modelo.juego.GPSChallenge;
import aplicacion.modelo.juego.Ganador;
import aplicacion.modelo.juego.Jugador;
import aplicacion.modelo.juego.dificultad.Dificil;
import aplicacion.modelo.juego.dificultad.Facil;
import aplicacion.modelo.juego.dificultad.Medio;
import aplicacion.modelo.vehiculos.Moto;
import aplicacion.modelo.vehiculos.Vehiculo;
import aplicacion.persistencia.Persistidor;
import aplicacion.persistencia.PersistidorUsuarios;

public class PersistidorUsuariosTest {

	@Test
	public void test() {
		PersistidorUsuarios usuarios = new PersistidorUsuarios();
		assertTrue(usuarios != null);
		
	}
	
	@Test
	public void testPersistenciaSoloUsuarios(){
		
		Medio medio = new Medio();
		GPSChallenge GPS = new GPSChallenge(medio);
		
		String n1 = "maciel";
		String n2 = "andres";
		String n3 = "itchart";
		
		GPS.agregarUsuario(n1);
		GPS.agregarUsuario(n2);
		GPS.agregarUsuario(n3);
		
		PersistidorUsuarios perUsuarios = new PersistidorUsuarios();
		perUsuarios.persistirSoloUsuarios(GPS.usuarios());
		
		try{
		ArrayList<String> listaUsuarios = perUsuarios.recuperarSoloUsuarios();
		
		assertEquals(listaUsuarios.get(0) , n1);
		assertEquals(listaUsuarios.get(1) , n2);
		assertEquals(listaUsuarios.get(2) , n3);
		} catch (FileNotFoundException error){
			System.out.println("no se pudo recuperar la lista de usuarios");
		}
		
	}
	
	@Test
	public void testPersistenciaSoloPuntajes(){
		
		Dificil dificil = new Dificil();
		GPSChallenge GPS = new GPSChallenge(dificil);
		
		String n1 = "maciel";
		String n2 = "andres";
		String n3 = "itchart";
		
		Jugador unJugador = new Jugador(n1);	
		unJugador.setPuntaje(15);
		
		Jugador otroJugador = new Jugador(n2);	
		otroJugador.setPuntaje(22);
		
		Jugador otroJugadorMas = new Jugador(n3);	
		otroJugadorMas.setPuntaje(38);
		
		GPS.agregarGanadorAlRankingDePuntajes(unJugador);
		GPS.agregarGanadorAlRankingDePuntajes(otroJugador);
		GPS.agregarGanadorAlRankingDePuntajes(otroJugadorMas);

		PersistidorUsuarios perUsuarios = new PersistidorUsuarios();
		perUsuarios.persistirSoloPuntajes(GPS.ganadores());
		
		try{
		ArrayList<Ganador> ranking = perUsuarios.recuperarSoloPuntajes();
		
		assertEquals(ranking.get(0).getNombre() , n3);
		assertEquals(ranking.get(0).getPuntaje() , 38);

		assertEquals(ranking.get(1).getNombre() , n2);
		assertEquals(ranking.get(1).getPuntaje() , 22);

		assertEquals(ranking.get(2).getNombre() , n1);
		assertEquals(ranking.get(2).getPuntaje() , 15);

		} catch (FileNotFoundException error){
			System.out.println("no se pudo recuperar el ranking de ganadores");
		}
		
		
	}
	
	
	
	//@Test
	public void testPersistirUsuarios(){
		
		String n1 = "maciel";
		String n2 = "andres";
		String n3 = "itchart";
		
		Jugador jugador1 = new Jugador(n1);
		jugador1.setPuntaje(10);
		
		Jugador jugador2 = new Jugador(n2);
		jugador2.setPuntaje(20);

		Jugador jugador3 = new Jugador(n3);
		jugador3.setPuntaje(30);

		 
		Facil facil = new Facil();
		GPSChallenge GPS = new GPSChallenge(facil);
		
		GPS.agregarUsuario(n1);
		GPS.agregarUsuario(n2);
		GPS.agregarUsuario(n3);
		
		GPS.agregarGanadorAlRankingDePuntajes(jugador1);
		GPS.agregarGanadorAlRankingDePuntajes(jugador2);
		GPS.agregarGanadorAlRankingDePuntajes(jugador3);

		assertEquals(GPS.ganadores().get(0).getPuntaje(),30);
		assertEquals(GPS.ganadores().get(0).getNombre(), n3);

		assertEquals(GPS.ganadores().get(1).getPuntaje(),20);
		assertEquals(GPS.ganadores().get(1).getNombre(), n2);
		
		assertEquals(GPS.ganadores().get(2).getPuntaje(),10);
		assertEquals(GPS.ganadores().get(2).getNombre(), n1);
		
		
		PersistidorUsuarios perUsuarios = new PersistidorUsuarios();
		perUsuarios.persistirSoloUsuarios(GPS.usuarios());
		perUsuarios.persistirSoloPuntajes(GPS.ganadores());

		
		ArrayList<String> listaUsuarios;
		ArrayList<Ganador> ranking;
		
		try{
			listaUsuarios = perUsuarios.recuperarSoloUsuarios();
			ranking = perUsuarios.recuperarSoloPuntajes();

			
			assertEquals(listaUsuarios.get(0) , n1);
			assertEquals(listaUsuarios.get(1) , n2);
			assertEquals(listaUsuarios.get(2) , n3);
			
			
			assertEquals(ranking.get(0).getNombre(), n3 );
			assertEquals(ranking.get(0).getPuntaje(), 30 );
			
			assertEquals(ranking.get(1).getNombre(), n2 );
			assertEquals(ranking.get(1).getPuntaje(), 20 );
					
			assertEquals(ranking.get(2).getNombre(), n1 );
			assertEquals(ranking.get(2).getPuntaje(), 10 );


			
			} catch (FileNotFoundException error){
			System.out.println("no se pudo recuperar los usuarios test");
		}	
	}
	
	//@Test
	public void testPersistirUsuariosVacios(){
		 
		Facil facil = new Facil();
		GPSChallenge GPS = new GPSChallenge(facil);
		
				
		PersistidorUsuarios perUsuarios = new PersistidorUsuarios();
		perUsuarios.persistirSoloUsuarios(GPS.usuarios());
		perUsuarios.persistirSoloPuntajes(GPS.ganadores());
		
		try{
			ArrayList<String> listaUsuarios = perUsuarios.recuperarSoloUsuarios();
			ArrayList<Ganador> ranking = perUsuarios.recuperarSoloPuntajes();
			
			assertEquals(listaUsuarios.size() , 0);
			assertEquals(ranking.size() , 0);

			
	

			
			} catch (FileNotFoundException error){
			System.out.println("no se pudo recuperar los usuarios en test");
		}	
	}


	//@Test
	public void testPersistenciaCompleta(){
		
		
		
		
		//genero datos especificos y creo un GPSChallenge
		int movimientos = 30;
		int largada = 5;
		int llegada = 50;
		String nombre = "maciel";
		Moto unaMoto = new Moto();
		Vehiculo unVehiculo = new Vehiculo(unaMoto);
		unVehiculo.setMovimientos(movimientos);
		Jugador unJugador = new Jugador(nombre,unVehiculo);
		Facil facil = new Facil();
		GPSChallenge unModelo = new GPSChallenge(facil, unJugador, largada, llegada);
		String n1 = "maciel";
		String n2 = "andres";
		String n3 = "itchart";
		unModelo.agregarUsuario(n1);
		unModelo.agregarUsuario(n2);
		unModelo.agregarUsuario(n3);		
		
		//pruebo que los datos esten bien cargados en el juego
		assertEquals(unModelo.jugador().nombre() , nombre);
		assertEquals(unModelo.jugador().movimientos() , movimientos);
		assertEquals(unModelo.escenario().largada().getNumeroEsquina() , largada);
		assertEquals(unModelo.escenario().llegada().getNumeroEsquina() , llegada);
		assertEquals(unModelo.escenario().getAnchoDeMapa() , facil.anchoDeMapa());
		assertEquals(unModelo.usuarios().get(0),n1);
		assertEquals(unModelo.usuarios().get(1),n2);
		assertEquals(unModelo.usuarios().get(2),n3);

		//creo un persistidor de usuarios y guardo la lista de usuarios
		PersistidorUsuarios perUsuarios = new PersistidorUsuarios();
		perUsuarios.persistirSoloUsuarios(unModelo.usuarios());		
		perUsuarios.persistirSoloPuntajes(unModelo.ganadores());		


		//creo objeto persistidor y guardo en disco el modelo
		Persistidor unPer = new Persistidor();	
		
		try{
		unPer.guardarJuego(unModelo);
		} catch  (IOException error) {
			
			System.out.println("no se pudo guardar el modelo");

		}
		
		//recupero del disco el modelo guardado y comparo datos con los originales
		try{
		GPSChallenge modeloRecuperado = unPer.recuperarJuego(nombre);
		
		assertEquals(modeloRecuperado.jugador().nombre() , nombre);
		assertEquals(modeloRecuperado.jugador().movimientos() , movimientos);
		assertEquals(modeloRecuperado.escenario().largada().getNumeroEsquina() , 5);
		assertEquals(modeloRecuperado.escenario().llegada().getNumeroEsquina() , 50);
		assertEquals(modeloRecuperado.escenario().getAnchoDeMapa() , facil.anchoDeMapa());
		assertEquals(modeloRecuperado.usuarios().size(),3);
		assertEquals(unModelo.usuarios().get(0),n1);
		assertEquals(unModelo.usuarios().get(1),n2);
		assertEquals(unModelo.usuarios().get(2),n3);


		} catch (FileNotFoundException error) {
			System.out.println("no se pudo recuperar el modelo");
		}		
	}
	
	
	//@Test
	public void testInicializaciónDeUnModelo(){
		
		Facil facil = new Facil();
		GPSChallenge GPS = new GPSChallenge(facil);
		
		assertEquals(GPS.usuarios().size(),0);
		assertEquals(GPS.ganadores(), 0);

	}
	
}
