package aplicacion.persistencia;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.beans.XMLEncoder;
import java.beans.XMLDecoder;

import aplicacion.modelo.juego.*;
import aplicacion.modelo.vehiculos.*;
import aplicacion.modelo.juego.dificultad.*;
import aplicacion.modelo.escenario.*;


public class Persistidor {
	
	private int movimientos;
	private int posicion;
	private String nombre;
	private int posLargada;
	private int posLlegada;

	public void Persistidor(){
		
		 int movimientos =0;
		 int posicion =0;
		 String nombre = "";
		 int posLargada =0;
		 int posLlegada =0;
	}
	
	
	public int getPosicion() {
		return posicion;
	}

	public int getMovimientos() {
		return movimientos;
	}

	public String getNombre() {
		return nombre;
	}
	
	public int getPosLlegada() {
		return posLlegada;
	}

	public int getPosLargada() {
		return posLargada;
	}
	
	public void setPosicion(int unaPosicion) {
		posicion = unaPosicion;
	}

	public void setMovimientos(int unosMovimientos) {
		movimientos = unosMovimientos;
	}

	public void setNombre(String unNombre) {
		nombre = unNombre;
	}
	
	public void setPosLlegada(int llegada) {
		posLlegada = llegada;
	}

	public void setPosLargada(int largada) {
		posLargada = largada;
	}
	
	
	public void guardarJuego(GPSChallenge unModelo) throws IOException {
		
		//con el modelo recibido, recupero datos importantes para guardar
		TipoDeVehiculo unTipo = unModelo.jugador().vehiculo().miTipo();
		
		Dificultad unaDificultad = unModelo.dificultad();
		
		int movimientos = unModelo.jugador().movimientos();
		
		int posicion = unModelo.jugador().posicion().getNumeroEsquina();
		
		int posLargada = unModelo.escenario().largada().getNumeroEsquina();
		
		int posLlegada = unModelo.escenario().llegada().getNumeroEsquina();
		
		String unNombre =unModelo.jugador().nombre();
		
		//creo una instancia de Persistidor y le seteo los datos importantes tomados arriba
		Persistidor unPersistidor = new Persistidor();
		unPersistidor.setMovimientos(movimientos);
		unPersistidor.setNombre(unNombre);
		unPersistidor.setPosicion(posicion);
		unPersistidor.setPosLargada(posLargada);
		unPersistidor.setPosLlegada(posLlegada);
		String direccionTipoDeVehiculo = unModelo.jugador().nombre()+"TipoDeVehiculo.xtml";
		String direccionDificultad = unModelo.jugador().nombre()+"Dificultad.xtml";
		String direccionDatosDeJuego = unModelo.jugador().nombre()+"DatosDeJuego.xtml";
		
		FileOutputStream fileOutputStream1 = new FileOutputStream(direccionTipoDeVehiculo);
		XMLEncoder  serializadorDeTipoDeVehiculo = new XMLEncoder(fileOutputStream1);
		//serializo la instancia con lso datos importantes del juego
		serializadorDeTipoDeVehiculo.writeObject(unTipo); 
		serializadorDeTipoDeVehiculo.close();
	
		FileOutputStream fileOutputStream2 = new FileOutputStream(direccionDificultad);
		XMLEncoder  serializadorDeDificultad = new XMLEncoder(fileOutputStream2);
		serializadorDeDificultad.writeObject(unaDificultad); 
		serializadorDeDificultad.close();
		
		//creo un escritor en disco con la direccion a guardar
		FileOutputStream fileOutputStream3 = new FileOutputStream(direccionDatosDeJuego);
		//Creo un serializador en XML
		XMLEncoder  serializadorDeDatosDeJuego = new XMLEncoder(fileOutputStream3);
		//serializo la instancia con los datos importantes del juego
		serializadorDeDatosDeJuego.writeObject(unPersistidor); 
		//cierro el serializador
		serializadorDeDatosDeJuego.close();
		
		
	}
	
	public GPSChallenge recuperarJuego(String nombreUsuario) throws FileNotFoundException{
		//basicamente hago lo inverso del guardarJuego, igual comento 
		String nombre = nombreUsuario;
		String direccionTipoDeVehiculo = nombre+"TipoDeVehiculo.xtml";
		String direccionDificultad = nombre+"Dificultad.xtml";
		String direccionDatosDeJuego = nombre+"DatosDeJuego.xtml";
		
		FileInputStream fileInputStream1 = new FileInputStream(direccionTipoDeVehiculo);
		XMLDecoder  deserializadorDeTipoDeVehiculo = new XMLDecoder(fileInputStream1);
		TipoDeVehiculo unTipo = (TipoDeVehiculo)deserializadorDeTipoDeVehiculo.readObject(); 
		deserializadorDeTipoDeVehiculo.close();
		
		FileInputStream fileInputStream2 = new FileInputStream(direccionDificultad);
		XMLDecoder  deserializadorDeDificultad = new XMLDecoder(fileInputStream2);
		Dificultad unaDificultad = (Dificultad)deserializadorDeDificultad.readObject(); 
		deserializadorDeDificultad.close();
		
		//creo el lector de disco
		FileInputStream fileInputStream3 = new FileInputStream(direccionDatosDeJuego);
		//creo el hidratador de XML
		XMLDecoder  deserializadorDeDatosDeJuego = new XMLDecoder(fileInputStream3);
		//hidrato el objeto guardado, tipando lo recibido y asignandolo a una variable tipo Persistor
		Persistidor datosDeJuego = (Persistidor)deserializadorDeDatosDeJuego.readObject(); 
		//cierro el hidratador
		deserializadorDeDatosDeJuego.close();
		
		//recupero todos los datos del objeto guardardo en disco
		String nombreRecuperado = datosDeJuego.getNombre();
		int movimientosRecuperados = datosDeJuego.getMovimientos();
		int posicionRecuperada = datosDeJuego.getPosicion();
		int posLargada = datosDeJuego.getPosLargada();
		int posLlegada = datosDeJuego.getPosLlegada();
		
		//Creo un vehiculo, pasandole por parametro el TipoDeVehiculo guardado
		Vehiculo unVehiculo = new Vehiculo(unTipo);
		
		//al vehiculo le asigno los movimientos guardados
		unVehiculo.setMovimientos(movimientosRecuperados);
		
		//creo un jugador, pasandole por parametro el nombre guardado y el vehiculo recien creado		
		Jugador jugador = new Jugador(nombreRecuperado,unVehiculo);
		
		//creo un nuvo juego, pasandole por parametros la dificultad hidratada, el jugador recien creado, y las
		//respectivas posiciones de Largada y Llegada hidratadas recientemente
		GPSChallenge nuevoModelo = new GPSChallenge(unaDificultad, jugador, posLargada, posLlegada);
		//finalmente al jugador del juego le seteo la posicion recien hidratada				
		nuevoModelo.jugador().vehiculo().setPosicion(nuevoModelo.escenario().getEsquina(posicionRecuperada));
		
		//devuelvo el nuevo modelo creado, con los datos recuperados
		
		return nuevoModelo;
	}
	
}
