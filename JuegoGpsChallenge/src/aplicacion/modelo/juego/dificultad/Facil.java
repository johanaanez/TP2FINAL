package aplicacion.modelo.juego.dificultad;

import javax.swing.ImageIcon;

public class Facil implements Dificultad {

	private static final int anchoDeMapa = 8;
	private static final int limiteMovimientos = 80;
	private static final int puntajeMovimientoSobrante = 1;
	private static final int cantidadEncontrables = 17;
	//private static final ImageIcon imagenMapa = new ImageIcon("estaticos/escenarios/mapaFacil.png");
	private String path="estaticos/escenarios/mapaFacil.png";

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
