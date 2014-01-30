package tests;

import static org.junit.Assert.*;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.Test;

import aplicacion.modelo.juego.GPSChallenge;
import aplicacion.persistencia.*;
import aplicacion.modelo.juego.*;
import aplicacion.modelo.juego.dificultad.*;
import aplicacion.modelo.vehiculos.*;





public class PersistidorTest {

	@Test
	public void testPersistidor() {

		Persistidor unPer = new Persistidor();	
	
		assertTrue(unPer != null);
	}
	
	
	@Test
	public void testPersistenciaDeGPSAtributosNoNull(){
		
		Facil facil = new Facil();
		GPSChallenge unModelo = new GPSChallenge(facil);
		Persistidor unPer = new Persistidor();	
		try{
		unPer.guardarJuego(unModelo);
		} catch  (IOException error) {}
		
		try{
		GPSChallenge modeloRecuperado = unPer.recuperarJuego("Jugador1");
		assertTrue(modeloRecuperado.jugador() != null);
		assertTrue(modeloRecuperado.escenario() != null);
		assertTrue(modeloRecuperado.dificultad() != null);

		} catch (FileNotFoundException error) {}		
	}
	
	
	
	@Test
	public void testPersistirPersistidor(){
		
		//genero datos especificos y creo el juego con esos datos
		int movimientos = 30;
		int posLargada = 5;
		int posLlegada = 50;
		String unNombre = "maciel";
		int posicion = 15;
		Moto unaMoto = new Moto();
		Vehiculo unVehiculo = new Vehiculo(unaMoto);
		unVehiculo.setMovimientos(movimientos);
		Jugador unJugador = new Jugador(unNombre,unVehiculo);
		Facil facil = new Facil();
		GPSChallenge unModelo = new GPSChallenge(facil, unJugador, posLargada, posLlegada);
		unModelo.jugador().vehiculo().setPosicion(unModelo.escenario().getEsquina(posicion));
	
		//recupero esos datos
			int unosMovimientos = unModelo.jugador().movimientos();	
			int unaPosicion = unModelo.jugador().posicion().getNumeroEsquina();	
			int unaPosLargada = unModelo.escenario().largada().getNumeroEsquina();	
			int unaPosLlegada = unModelo.escenario().llegada().getNumeroEsquina();	
			String elNombre =unModelo.jugador().nombre();	
			
			//creo un persistidor y le seteo los datos
			Persistidor unPersistidor = new Persistidor();
			
			unPersistidor.setMovimientos(unosMovimientos);
			unPersistidor.setNombre(elNombre);
			unPersistidor.setPosicion(unaPosicion);
			unPersistidor.setPosLargada(unaPosLargada);
			unPersistidor.setPosLlegada(unaPosLlegada);
			
			//pruebo que los datos se carguen correctamente
			assertEquals(unPersistidor.getMovimientos(), movimientos);
			assertEquals(unPersistidor.getPosicion(), posicion);
			assertEquals(unPersistidor.getPosLargada(), posLargada);
			assertEquals(unPersistidor.getPosLlegada(), posLlegada);
			assertEquals(unPersistidor.getNombre(), unNombre);
			
			//guardp mi objeto persistidor en disco
			try{
			FileOutputStream fileOutputStream = new FileOutputStream("C:/Users/Maciel/Documents/DatosDeJuego.xtml");
			XMLEncoder  serializadorDeTipoDeVehiculo = new XMLEncoder(fileOutputStream);
			serializadorDeTipoDeVehiculo.writeObject(unPersistidor); 
			serializadorDeTipoDeVehiculo.close();
			
			}catch (IOException error) {
				System.out.println("excepcion en testPersistirPersistidor");

			}
			
			//recupero los datos de disco y los comparo con los originales
			try{
			FileInputStream fileInputStream = new FileInputStream("C:/Users/Maciel/Documents/DatosDeJuego.xtml");
			XMLDecoder  deserializadorDeDatosDeJuego = new XMLDecoder(fileInputStream);
			Persistidor datosDeJuego = (Persistidor)deserializadorDeDatosDeJuego.readObject(); 
			deserializadorDeDatosDeJuego.close();
			
			assertEquals(datosDeJuego.getMovimientos(), movimientos);
			assertEquals(datosDeJuego.getPosicion(), posicion);
			assertEquals(datosDeJuego.getPosLargada(), posLargada);
			assertEquals(datosDeJuego.getPosLlegada(), posLlegada);
			assertEquals(datosDeJuego.getNombre(), unNombre);


			
			}catch (FileNotFoundException error) {
				
				System.out.println("excepcion en testPersistirPersistidor");
			}
			
	}
	
	@Test
	public void testPersistirDificultad(){
		
		Facil facil = new Facil();
		
		try{
			FileOutputStream fileOutputStream = new FileOutputStream("C:/Users/Maciel/Documents/Dificultad.xtml");
			XMLEncoder  serializadorDeTipoDeVehiculo = new XMLEncoder(fileOutputStream);
			serializadorDeTipoDeVehiculo.writeObject(facil); 
			serializadorDeTipoDeVehiculo.close();
			
			}catch (IOException error) {
				System.out.println("excepcion en testDificultad");

			}
		
		try {
		FileInputStream fileInputStream = new FileInputStream("C:/Users/Maciel/Documents/Dificultad.xtml");
		XMLDecoder  deserializadorDeDatosDeJuego = new XMLDecoder(fileInputStream);
		Dificultad dificultadRecuperada = (Dificultad)deserializadorDeDatosDeJuego.readObject(); 
		deserializadorDeDatosDeJuego.close();
		
		assertEquals(dificultadRecuperada.cantidadEncontrables(), facil.cantidadEncontrables());
		assertEquals(dificultadRecuperada.anchoDeMapa(), facil.anchoDeMapa());
		assertEquals(dificultadRecuperada.limiteMovimientos(), facil.limiteMovimientos());
		assertEquals(dificultadRecuperada.puntajeMovimientoSobrante(), facil.puntajeMovimientoSobrante());
		
		} catch (FileNotFoundException error) {
			
			System.out.println("excepcion en testDificultad");

		}
		
	}
	
	
	@Test
	public void testPersistirAuto(){
		
		Auto auto = new Auto();
		
		String nombre = "Auto";
		
		try{
			FileOutputStream fileOutputStream = new FileOutputStream("C:/Users/Maciel/Documents/Auto.xtml");
			XMLEncoder  serializadorDeAuto = new XMLEncoder(fileOutputStream);
			serializadorDeAuto.writeObject(auto); 
			serializadorDeAuto.close();
			
			}catch (IOException error) {
				System.out.println("excepcion en testPersistirPersistidor");

			}
		
		try{
			FileInputStream fileInputStream = new FileInputStream("C:/Users/Maciel/Documents/Auto.xtml");
			XMLDecoder  deserializadorDeAuto = new XMLDecoder(fileInputStream);
			Auto autoRecuperado = (Auto)deserializadorDeAuto.readObject(); 
			deserializadorDeAuto.close();
			
			assertEquals(autoRecuperado.nombre(), nombre);
			
			}catch (FileNotFoundException error) {
				
				System.out.println("excepcion en testPersistirPersistidor");
			}
			
	}
	
	
	@Test
	public void testPersistenciaDeGPSAtributosPersistidos(){
		
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
		
		//pruebo que los datso esten bien cargados en el juego
		assertEquals(unModelo.jugador().nombre() , nombre);
		assertEquals(unModelo.jugador().movimientos() , movimientos);
		assertEquals(unModelo.escenario().largada().getNumeroEsquina() , largada);
		assertEquals(unModelo.escenario().llegada().getNumeroEsquina() , llegada);
		assertEquals(unModelo.escenario().getAnchoDeMapa() , facil.anchoDeMapa());

	//creo objeto persistidor y guardo en disco el modelo
		Persistidor unPer = new Persistidor();	
		
		try{
		unPer.guardarJuego(unModelo);
		} catch  (IOException error) {
			
			System.out.println("excepcion en testPersistenciaDeGPSAtributosPersistidos guardando");

		}
		
		//recupero del disco el modelo guardado y comparo datos con los originales
		try{
		GPSChallenge modeloRecuperado = unPer.recuperarJuego(nombre);
		
		assertEquals(modeloRecuperado.jugador().nombre() , nombre);
		assertEquals(modeloRecuperado.jugador().movimientos() , movimientos);
		assertEquals(modeloRecuperado.escenario().largada().getNumeroEsquina() , 5);
		assertEquals(modeloRecuperado.escenario().llegada().getNumeroEsquina() , 50);
		assertEquals(modeloRecuperado.escenario().getAnchoDeMapa() , facil.anchoDeMapa());
		assertTrue(modeloRecuperado.jugador().vehiculo().miTipo() != null);



		} catch (FileNotFoundException error) {
			
			System.out.println("excepcion en testPersistenciaDeGPSAtributosPersistidos recuperando");

		}		
	}


}
