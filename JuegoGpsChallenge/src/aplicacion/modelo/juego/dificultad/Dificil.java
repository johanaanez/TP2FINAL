package aplicacion.modelo.juego.dificultad;

import javax.swing.ImageIcon;

public class Dificil implements Dificultad {

	private static final int anchoDeMapa = 12;
	private static final int limiteMovimientos = 50;
	private static final int puntajeMovimientoSobrante = 3;
	private static final int cantidadEncontrables = 41;
	//private static final ImageIcon imagenMapa = new ImageIcon("estaticos/escenarios/mapaDificil.png");
	private String path="estaticos/escenarios/mapaDificil.png";

	@Override
	public int anchoDeMapa() {
		return anchoDeMapa;
	}

	@Override
	public int limiteMovimientos() {
		return limiteMovimientos;
	}

	@Override
	public int puntajeMovimientoSobrante() {
		return puntajeMovimientoSobrante;
	}

	@Override
	public int cantidadEncontrables() {
		return cantidadEncontrables;
	}

	/*@Override
	public ImageIcon imagenMapa() {
		return imagenMapa;
	}*/
	
	@Override
	public ImageIcon imagenMapa(){
		return (new ImageIcon(path));
	}

}
