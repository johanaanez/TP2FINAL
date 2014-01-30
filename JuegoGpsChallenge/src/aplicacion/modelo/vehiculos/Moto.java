package aplicacion.modelo.vehiculos;

import javax.swing.ImageIcon;

import aplicacion.modelo.encontrables.Piquete;
import aplicacion.modelo.encontrables.SorpresaCambioDeVehiculo;
import aplicacion.modelo.escenario.Calle;
import aplicacion.modelo.escenario.Direccion;

public class Moto extends TipoDeVehiculo {
	
	//private ImageIcon imagenVehiculo = new ImageIcon("estaticos/vehiculos/moto.png");
	private String path="estaticos/vehiculos/moto.png";
	
	public Moto(){
		super(8);
	}
	
	public Moto(int probabilidad){
		super(probabilidad);
	}
	
    public void cruzar(Piquete unPiquete,Vehiculo unVehiculo, Direccion unaDireccion){
    	unVehiculo.agregarMovimientos(unPiquete.penalizacion());
    	posicionarEnLaEsquinaSiguiente(unVehiculo,unaDireccion);
	}
    
    public void cruzar(SorpresaCambioDeVehiculo unaSorpresa, Vehiculo unVehiculo, Direccion unaDireccion){	
		cambioDeVehiculo(this,unVehiculo,unaDireccion);
		posicionarEnLaEsquinaSiguiente(unVehiculo,unaDireccion);
	}
    
    public String nombre(){
    	return "Moto";
    }
    
    /*public ImageIcon imagenVehiculo() {
		return imagenVehiculo;
	}*/
    
    public ImageIcon imagenVehiculo(){
    	return (new ImageIcon(path));
    }
}
