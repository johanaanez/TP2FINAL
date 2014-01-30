package aplicacion.modelo.vehiculos;

import java.util.Random;

import javax.swing.ImageIcon;

import aplicacion.modelo.vehiculos.Vehiculo;
import aplicacion.modelo.encontrables.*;
import aplicacion.modelo.escenario.Calle;
import aplicacion.modelo.escenario.Direccion;


public class TipoDeVehiculo {
	
	protected int probabilidadDeDetencion;
	protected ImageIcon imagenVehiculo = new ImageIcon("estaticos/vehiculos/nada.png");
	
	
	public TipoDeVehiculo(int probabilidad){
		
		probabilidadDeDetencion = probabilidad;
		
	}//esta bien poner aca este atributo (es una pregunta)
	
	public TipoDeVehiculo(){
				
	}
	
	
	public void posicionarEnLaEsquinaSiguiente(Vehiculo unVehiculo, Direccion unaDireccion){
		
		Calle nuevaCalle = unVehiculo.posicion.getCalle(unaDireccion);
    	unVehiculo.setPosicion(nuevaCalle.proximaEsquina(unVehiculo));
		
	}
	
	public void cruzar(SorpresaCambioDeVehiculo unaSorpresa,Vehiculo unVehiculo,Direccion unaDireccion){}


	
	
	
	
	public void cruzar(SorpresaFavorable unaSorpresa, Vehiculo unVehiculo, Direccion unaDireccion){	
		unVehiculo.descontarPorcentajeDeMovimientos( unaSorpresa.porcentaje() );
		posicionarEnLaEsquinaSiguiente(unVehiculo,unaDireccion);
	}
	
	public void cruzar(SorpresaDesfavorable unaSorpresa, Vehiculo unVehiculo, Direccion unaDireccion){	
		unVehiculo.sumarPorcentajeDeMovimientos( unaSorpresa.porcentaje() );
		posicionarEnLaEsquinaSiguiente(unVehiculo,unaDireccion);
	}
	
	public void cruzar(Pozo unPozo, Vehiculo unVehiculo, Direccion unaDireccion){
	    	unVehiculo.agregarMovimientos(unPozo.penalizacion());
	    	posicionarEnLaEsquinaSiguiente(unVehiculo,unaDireccion);
	}
    
    public void cruzar(Piquete unPiquete,Vehiculo unVehiculo, Direccion unaDireccion){
    	
    	//no hace nada para Auto y 4X4. En moto esta redefinido.
    	
	}
    
    public int pedirValorRandomEntreUnoYDiez(){
    	
    	Random proba = new Random();
    	return proba.nextInt(10);
    }
    
    public void cruzar(ControlPolicial unControlPolicial, Vehiculo unVehiculo, Direccion unaDireccion){

    	
    	int valorProba = pedirValorRandomEntreUnoYDiez();
    	
    	if (valorProba < probabilidadDeDetencion){
    	unVehiculo.agregarMovimientos(unControlPolicial.penalizacion() );	
    	}
    	
    	posicionarEnLaEsquinaSiguiente(unVehiculo,unaDireccion);
    	
	}
	
    
    public void cambioDeVehiculo(Moto unaMoto,Vehiculo unVehiculo,Direccion unaDireccion){
    	
    	Auto unAuto = new Auto();
    	unVehiculo.setTipoDeVehiculo(unAuto);

    	
    }
    
    public void cambioDeVehiculo(Auto unAuto,Vehiculo unVehiculo,Direccion unaDireccion){
    	
    	CuatroXCuatro unaCamioneta = new CuatroXCuatro();
    	unVehiculo.setTipoDeVehiculo(unaCamioneta);

    	
    }

	public void cambioDeVehiculo(CuatroXCuatro unaCamioneta,Vehiculo unVehiculo,Direccion unaDireccion){
	
	Moto unaMoto = new Moto();
	unVehiculo.setTipoDeVehiculo(unaMoto);

	}

	public String nombre() {
		// TODO Auto-generated method stub
		return null;
	}

	public ImageIcon imagenVehiculo() {
		return imagenVehiculo;
	}


}