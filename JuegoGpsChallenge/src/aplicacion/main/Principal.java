package aplicacion.main;

import aplicacion.controlador.Controlador;
import aplicacion.modelo.juego.GPSChallenge;
import aplicacion.modelo.juego.dificultad.*;
import aplicacion.modelo.vehiculos.*;
import aplicacion.vista.VistaBienvenida;
import aplicacion.vista.VistaGPS;

public class Principal {

	public static void main(String[] args) {

		// creamos la vista
		VistaBienvenida vista = new VistaBienvenida("GPS CHALLENGE",400,400);
		vista.saludar();
		
	}

}
