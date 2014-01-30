package aplicacion.modelo.encontrables;

import aplicacion.modelo.escenario.Direccion;
import aplicacion.modelo.vehiculos.Vehiculo;

public class SorpresaDesfavorable extends SorpresaPorcentual {
	
	final static int miPorcentaje = 25;

	public SorpresaDesfavorable(){
		/* Llama a constructor de SorpresaPorcentual */
		super(miPorcentaje);
	}
	
	public void cruzadoPor(Vehiculo unVehiculo, Direccion unaDireccion){
		unVehiculo.miTipo().cruzar(this, unVehiculo, unaDireccion);
	}
	
}
