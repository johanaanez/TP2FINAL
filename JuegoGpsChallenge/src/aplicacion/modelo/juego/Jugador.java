package aplicacion.modelo.juego;

import javax.swing.ImageIcon;

import aplicacion.modelo.escenario.Direccion;
import aplicacion.modelo.escenario.Esquina;
import aplicacion.modelo.vehiculos.*;

public class Jugador {
	
	private String nombre;
	private Vehiculo vehiculo;
	private int puntaje;
	
	public Jugador(){
		nombre = "Jugador1";
		vehiculo = new Vehiculo(new Auto());
		puntaje= 0;
	}

	public Jugador(String nombre) {
		this.nombre = nombre;
		vehiculo = new Vehiculo();
		puntaje= 0;
	}
	
	public Jugador(String nombre, Vehiculo unVehiculo) {
		this.nombre = nombre;
		vehiculo = unVehiculo;
		puntaje= 0;
	}

	public Vehiculo vehiculo() {
		return vehiculo;
	}

	public String nombre() {
		return nombre;
	}
	
	public int puntaje(){
		return puntaje;
	}

	public void setPuntaje(int puntos){
		
		puntaje = puntos;
	}
	
	public void cambiarNombre(String nombre) {
		this.nombre = nombre;
	}

	public Esquina posicion() {
		return vehiculo.posicion();
	}
	

	public int movimientos() {
		return vehiculo.movimientos();
	}

	public String nombreDelVehiculo() {
		return vehiculo.nombreDelVehiculo();
	}

	public void mover(Direccion direccion) {
		vehiculo.avanzarHacia(direccion);
	}

	public Esquina esquinaVehiculo() {
		return vehiculo.posicion();
	}

	public ImageIcon imagenVehiculo() {
		return vehiculo.imagenVehiculo();
	}

	public void cambiarVehiculo(TipoDeVehiculo tipoDeVehiculo) {
		vehiculo.setTipoDeVehiculo(tipoDeVehiculo);
	}
	

}
