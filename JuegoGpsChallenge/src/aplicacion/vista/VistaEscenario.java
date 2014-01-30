package aplicacion.vista;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import aplicacion.modelo.escenario.Calle;
import aplicacion.modelo.escenario.Esquina;
import aplicacion.modelo.juego.GPSChallenge;
import aplicacion.controlador.Controlador;

public class VistaEscenario extends JPanel{

	private ImageIcon dibujoVehiculo;
	private ImageIcon dibujoCiudad;
	private ImageIcon dibujoMeta;
	private VistaEncontrables encontrables = new VistaEncontrables();
	GPSChallenge modelo;
	private Controlador unControl;
	private int distanciaEntreEsquinas=60;
	
	public VistaEscenario(GPSChallenge modelo){
		dibujoVehiculo = modelo.imagenVehiculo();
		dibujoCiudad = modelo.imagenMapa();
		dibujoMeta = new ImageIcon("estaticos/escenarios/meta.png");
		this.modelo = modelo;
		this.unControl= new Controlador();
	}
		
	public void paintComponent( Graphics g ) {
		 int metaPosX;
		 int metaPosY;
		 int vehiculoPosX;
		 int vehiculoPosY;
		
		 super.paintComponent( g );
		 g.drawImage(dibujoCiudad.getImage(), 0, 0,dibujoCiudad.getIconWidth(),dibujoCiudad.getIconHeight(), this);
		 
		 metaPosX = this.getPosicionX(modelo.llegada());
		 metaPosY = this.getPosicionY(modelo.llegada());
		 
		 g.drawImage(dibujoMeta.getImage(), metaPosX, metaPosY,30,30, this);
		 
		 vehiculoPosX = this.getPosicionX(modelo.esquinaVehiculo()); 
		 vehiculoPosY = this.getPosicionY(modelo.esquinaVehiculo());
		 
		 dibujarVehiculo(g,vehiculoPosX,vehiculoPosY);
		 dibujarEncontrables(g);
		 dibujarVista(g, vehiculoPosX, vehiculoPosY, metaPosX, metaPosY);
	
	}
	
	public void dibujarVehiculo(Graphics g, int posPartidaX, int posPartidaY){
		 g.drawImage(dibujoVehiculo.getImage(), posPartidaX,posPartidaY,30,30, this);
	}
	
	public void dibujarVista(Graphics g2, int vehiculoPosX, int vehiculoPosY, int metaPosX, int metaPosY){
		
		g2.fillRect(0, 0, vehiculoPosX-90, alto()); // izquierda
		g2.fillRect(vehiculoPosX+120, 0, ancho()-(vehiculoPosX+150), alto());  //derecha
		
		
		if ( (vehiculoPosX+90) < metaPosX ){
			g2.fillRect(vehiculoPosX-90, 0, 210,vehiculoPosY-90); // arriba
			g2.fillRect(vehiculoPosX-90,vehiculoPosY+120 , 210, alto()-(vehiculoPosY+80));  //abajo
		 }   
		else{
			g2.fillRect(vehiculoPosX-90, 0, 90, vehiculoPosY-90); // arriba
			g2.fillRect(vehiculoPosX, 0, ancho()- (vehiculoPosX+30), vehiculoPosY-90);
			
			g2.fillRect(vehiculoPosX-90, vehiculoPosY+120 , 90, alto()-(vehiculoPosY+90)); //abajo
			g2.fillRect(vehiculoPosX, vehiculoPosY+120 , ancho()- 30- vehiculoPosX, alto()-(vehiculoPosY+90));  
		}
		
		dibujarVistaMeta(g2, metaPosX, metaPosY, vehiculoPosX, vehiculoPosY);
	    //<  >
	}
	
	public void dibujarVistaMeta(Graphics g2, int metaPosX, int metaPosY, int vehiculoPosX, int vehiculoPosY){
		
		if ( (vehiculoPosX+90) < metaPosX ){
		g2.fillRect(metaPosX, 0, 30, metaPosY); 
		g2.fillRect(metaPosX, metaPosY+30, 30,alto()-(metaPosY+30));
		}
		
		else {
			if (metaPosX >= ( vehiculoPosX) ){  //FIJARSE QUE CUANDO EL AUTO ESTA A UNA CUADRA DE LA META SE MUESTRA MAL LA VISTA
				if ( (vehiculoPosY-30) >= metaPosY ) { //si la meta esta por arriba del vehiculo
				    g2.fillRect(metaPosX, 0, 60, metaPosY); // arriba de la meta
				    g2.fillRect(metaPosX, vehiculoPosY+120, 60, alto()- (vehiculoPosY+90) ); //abajo del vehiculo
				    g2.fillRect(metaPosX, metaPosY+30, 60, vehiculoPosY-120- metaPosY); //entre la meta y el vehiculo
			    }
			
			    if ( (vehiculoPosY+30)<= metaPosY ) { // Si el vehiculo esta arriba de la meta
				g2.fillRect(metaPosX, 0, 60, vehiculoPosY-90 );  //arriba del vehiculo
				g2.fillRect(metaPosX, vehiculoPosY+120, 60, metaPosY- (vehiculoPosY+120) ); //entre la meta y el vehiculo
				g2.fillRect(metaPosX, metaPosY+30, 60,alto()-(metaPosY+30)); //abajo de la meta
			    }
			   if (metaPosY == vehiculoPosY){
					g2.fillRect(metaPosX, 0, 60, vehiculoPosY-90); 
					g2.fillRect(metaPosX, metaPosY+120, 60, alto()- (vehiculoPosY+90));  
			   }
			}
		}
	}
	
	public void dibujarEncontrables(Graphics g){
		ArrayList<Calle> listaCalles = modelo.escenario().getCallesConEncontrables();
		Iterator<Calle> iteradorCalles = listaCalles.iterator();
		
		
		while (iteradorCalles.hasNext()){
			Calle calle = iteradorCalles.next();
			int posX = this.getPosicionX(calle);
			int posY = this.getPosicionY(calle);			
			
			g.drawImage(encontrables.dibujoEncontrable(calle.getEncontrable()).getImage(), posX, posY,30,30, this);
		}
	}
	
	private int getPosicionX(Esquina esquina) {
		int posX;
		int numeroEsquina = esquina.getNumeroEsquina();

		while (numeroEsquina > ( modelo.anchoMapa() - 1) ) {
			numeroEsquina -= modelo.anchoMapa();
		}
		
		numeroEsquina = numeroEsquina * 60; //60 es la cantidad de pixeles que hay entre el comienzo de una esquina y la otra
		posX = numeroEsquina;
		
		return posX;
	}
	
	private int getPosicionY(Esquina esquina) {
		int posY;
		int numeroEsquina = esquina.getNumeroEsquina();
		
		numeroEsquina = (int) (numeroEsquina / modelo.anchoMapa());		
		posY = numeroEsquina * 60;
				
		return posY;
	}

	private int getPosicionY(Calle calle) {
		int posY1 = this.getPosicionY(calle.getEsquina1());
		int posY2 = this.getPosicionY(calle.getEsquina2());
				
		int resultado = (( posY1 + posY2 ) / 2);
		
		return resultado;
	}

	private int getPosicionX(Calle calle) {
		int posX1 = this.getPosicionX(calle.getEsquina1());
		int posX2 = this.getPosicionX(calle.getEsquina2());
		
		int resultado = (( posX1 + posX2 ) / 2);
		
		return resultado;
	}

	public int ancho() {
		//deberia ser un atributo variable, segun la cantidad de esquinas del mapa
		return dibujoCiudad.getIconWidth();
	}
	
	public int alto() {
		//deberia ser un atributo variable, segun la cantidad de esquinas del mapa
		return dibujoCiudad.getIconHeight();
	}
	
	public int distanciaEntreEsquinas(){
		return distanciaEntreEsquinas;
	}
	

}
