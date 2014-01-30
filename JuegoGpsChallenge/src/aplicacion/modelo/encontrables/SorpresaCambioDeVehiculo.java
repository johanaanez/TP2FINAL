package aplicacion.modelo.encontrables;

import aplicacion.modelo.escenario.Direccion;
import aplicacion.modelo.vehiculos.Vehiculo;

public class SorpresaCambioDeVehiculo extends Sorpresa {
	
	public void cruzadoPor(Vehiculo unVehiculo, Direccion unaDireccion){
		unVehiculo.miTipo().cruzar(this, unVehiculo, unaDireccion);
	}

}
