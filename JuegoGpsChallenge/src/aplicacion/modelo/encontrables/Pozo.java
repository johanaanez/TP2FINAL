package aplicacion.modelo.encontrables;

import aplicacion.modelo.escenario.Direccion;
import aplicacion.modelo.vehiculos.*;


public class Pozo extends Obstaculo{
	
	final static int miPenalizacion = 3;
	
	public Pozo(){
		/* Llama a constructor de Obstaculo */
		super(miPenalizacion);
	}
	
   public void cruzadoPor(Vehiculo unVehiculo, Direccion unaDireccion){
		
		unVehiculo.miTipo().cruzar(this, unVehiculo, unaDireccion);
		
	}
	
}
