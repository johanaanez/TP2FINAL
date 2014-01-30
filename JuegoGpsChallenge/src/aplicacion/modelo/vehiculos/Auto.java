package aplicacion.modelo.vehiculos;


import javax.swing.ImageIcon;
import aplicacion.modelo.escenario.Direccion;

import aplicacion.modelo.encontrables.SorpresaCambioDeVehiculo;

public class Auto extends TipoDeVehiculo {
	
	//private ImageIcon imagenVehiculo = new ImageIcon("estaticos/vehiculos/auto.png");
	private String path="estaticos/vehiculos/auto.png";
	
	public Auto(){
		super(3);
	}
	
	public Auto(int probabilidad){
		super(probabilidad);
	}
	
	

    public void cruzar(SorpresaCambioDeVehiculo unaSorpresa, Vehiculo unVehiculo, Direccion unaDireccion){	
		cambioDeVehiculo(this,unVehiculo,unaDireccion);
		posicionarEnLaEsquinaSiguiente(unVehiculo,unaDireccion);
	}
    
    public String nombre(){
    	return "Auto";
    }
    
    /*public ImageIcon imagenVehiculo() {
		return imagenVehiculo;
	}*/
    
   
    public ImageIcon imagenVehiculo(){
    	return (new ImageIcon(path));
    }
    
}



