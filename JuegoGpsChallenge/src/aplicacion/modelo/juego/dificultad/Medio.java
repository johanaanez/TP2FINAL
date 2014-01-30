package aplicacion.modelo.juego.dificultad;

import javax.swing.ImageIcon;

public class Medio implements Dificultad {

	private static final int anchoDeMapa = 10;
	private static final int limiteMovimientos = 60;
	private static final int puntajeMovimientoSobrante = 2;
	private static final int cantidadEncontrables = 28;
	//private static final ImageIcon imagenMapa = new ImageIcon("estaticos/escenarios/mapaMedio.png");
	private String path="estaticos/escenarios/mapaMedio.png";
	

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
