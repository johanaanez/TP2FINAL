package aplicacion.modelo.juego.dificultad;

import javax.swing.ImageIcon;

public interface Dificultad {
	
	public int anchoDeMapa();
	public int limiteMovimientos();
	public int puntajeMovimientoSobrante();
	public int cantidadEncontrables();
	
	public ImageIcon imagenMapa();
	
}

	
