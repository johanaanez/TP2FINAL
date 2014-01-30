package aplicacion.modelo.encontrables;

import aplicacion.modelo.escenario.Direccion;
import aplicacion.modelo.vehiculos.Vehiculo;

public class SorpresaFavorable extends SorpresaPorcentual{
	
	final static int miPorcentaje = 20;

	public SorpresaFavorable(){
		/* Llama a constructor de SorpresaPorcentual */
		super(miPorcentaje);
	}
	
	public void cruzadoPor(Vehiculo unVehiculo, Direccion unaDireccion){
		unVehiculo.miTipo().cruzar(this, unVehiculo, unaDireccion);
	}
}
