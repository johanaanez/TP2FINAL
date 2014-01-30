package aplicacion.modelo.vehiculos;

import javax.swing.ImageIcon;

import aplicacion.modelo.encontrables.Pozo;
import aplicacion.modelo.encontrables.SorpresaCambioDeVehiculo;
import aplicacion.modelo.escenario.Calle;
import  aplicacion.modelo.escenario.Direccion;

public class CuatroXCuatro extends TipoDeVehiculo {

	//private ImageIcon imagenVehiculo = new ImageIcon("estaticos/vehiculos/cuatro por cuatro.png");
	private String path="estaticos/vehiculos/cuatro por cuatro.png";
	
	public CuatroXCuatro(){
		super(5);
	}
	
	public CuatroXCuatro(int probabilidad){
		super(probabilidad);
	}
	
    public void cruzar(Pozo unPozo, Vehiculo unVehiculo, Direccion unaDireccion){
    	
    	posicionarEnLaEsquinaSiguiente(unVehiculo,unaDireccion);
    	
	}
    
    public void cruzar(SorpresaCambioDeVehiculo unaSorpresa, Vehiculo unVehiculo, Direccion unaDireccion){	
		cambioDeVehiculo(this,unVehiculo,unaDireccion);
		posicionarEnLaEsquinaSiguiente(unVehiculo,unaDireccion);
	}
    
    public String nombre(){
    	return "4 X 4";
    }
    
    /*public ImageIcon imagenVehiculo() {
		return imagenVehiculo;
	}*/
    
    public ImageIcon imagenVehiculo(){
    	return (new ImageIcon(path));
    }
    
}
