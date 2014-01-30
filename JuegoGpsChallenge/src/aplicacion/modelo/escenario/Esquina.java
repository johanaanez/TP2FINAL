package aplicacion.modelo.escenario;

import aplicacion.modelo.escenario.*;
import aplicacion.modelo.juego.Jugador;

public class Esquina {

	private Calle calleArriba;
	private Calle calleAbajo;
	private Calle calleDerecha; 
	private Calle calleIzquierda;
	private int numeroDeEsquina;
	private Jugador jugador; 
	
	
	public Esquina(){
		
		this.calleArriba = null;
		this.calleAbajo = null;
		this.calleDerecha = null;
		this.calleIzquierda = null;
		this.numeroDeEsquina = -1;
		this.jugador = null;
	}

	public boolean llevaCalleArriba(int x, int anchoDeMapa){
	
		if (x < anchoDeMapa) return false;
		else return true;
	}	


	public boolean llevaCalleAbajo(int x, int anchoDeMapa){
	
		int a = anchoDeMapa;
	
		if ( ((a*a)-a) > x) {return true;}
		return false;
	}


	public boolean llevaCalleDerecha(int x, int anchoDeMapa){
	
		int a = x +1;
		
		if (a % anchoDeMapa == 0) return false;
		else return true;
	}


	public boolean llevaCalleIzquierda(int x, int anchoDeMapa){
	
		if (x % anchoDeMapa == 0) return false;
		else return true;
	}


	

	public void setJugadorEstacionado(Jugador j){	
		this.jugador = j;
	}

	public Jugador getJugadorEstacionado(){	
		return this.jugador;
	}

	public void setNumeroDeEsquina(int x){	
		this.numeroDeEsquina = x;
	}
	
	public int getNumeroEsquina(){
		return numeroDeEsquina;
	}

	public void setCalleArriba(Calle calleArriba){	
		this.calleArriba = calleArriba;
	}

	public void setCalleAbajo(Calle calleAbajo){	
		this.calleAbajo = calleAbajo;
	}

	public void setCalleDerecha(Calle calleDerecha){	
		this.calleDerecha = calleDerecha;
	}

	public void setCalleIzquierda(Calle calleIzquierda){	
		this.calleIzquierda = calleIzquierda;
	}


	public Calle getCalleArriba(){	
		return this.calleArriba;
	}

	public Calle getCalleAbajo(){	
		return this.calleAbajo;
	}

	public Calle getCalleDerecha(){	
		return this.calleDerecha;
	}

	public Calle getCalleIzquierda(){
		return this.calleIzquierda;
	}

	
	/* Devuelve la calle en la direccion pasada por parametro
	 */
	public Calle getCalle(Direccion direccion){
		if (direccion == Direccion.ARRIBA){
			return calleArriba;
		}
		else if (direccion == Direccion.ABAJO){
			return calleAbajo;
		}
		else if (direccion == Direccion.DERECHA){
			return calleDerecha;
		}
		else return calleIzquierda;
	}
	
	
	/* Chequea si hay una calle en la direccion pasada por parametro
	 */
	public boolean tieneCalle(Direccion direccion){
		if ((direccion == Direccion.ARRIBA) & (calleArriba != null)){
			return true;
		}
		else if ((direccion == Direccion.ABAJO) & (calleAbajo != null)){
			return true;
		}
		else if ((direccion == Direccion.DERECHA) & (calleDerecha != null)){
			return true;
		}
		else if ((direccion == Direccion.IZQUIERDA) & (calleIzquierda != null)){
			return true;
		}
		else return false;
	}
	
	
}
