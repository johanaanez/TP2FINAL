package aplicacion.modelo.encontrables;

import aplicacion.modelo.vehiculos.Vehiculo;
import aplicacion.modelo.escenario.Direccion;

public class ControlPolicial extends Obstaculo {

	final static int miPenalizacion = 3;
	
	public ControlPolicial(){
		/* Llama a constructor de Obstaculo */
		super(miPenalizacion);
	}

    public void cruzadoPor(Vehiculo unVehiculo,Direccion unaDireccion){
		
		unVehiculo.miTipo().cruzar(this, unVehiculo, unaDireccion);
		
	}
	
}
