package aplicacion.modelo.vehiculos;

import javax.swing.ImageIcon;

import aplicacion.modelo.escenario.Esquina;
import aplicacion.modelo.escenario.Calle;
import aplicacion.modelo.escenario.Direccion;
import aplicacion.modelo.encontrables.*;


public class Vehiculo {
	
	protected int movimientos;
	protected TipoDeVehiculo tipoDeVehiculo;
    protected Esquina posicion;
	
	public Vehiculo(){
		movimientos = 0;
	}
	
	public Vehiculo(Esquina unaEsquina){
		movimientos = 0;
		posicion= unaEsquina;
	}
	
	public Vehiculo(TipoDeVehiculo tipoDeVehiculo){
		this.tipoDeVehiculo = tipoDeVehiculo;
		movimientos = 0;
	}

	public Vehiculo(TipoDeVehiculo tipoDeVehiculo, Esquina unaEsquina){
		this.tipoDeVehiculo = tipoDeVehiculo;
		posicion = unaEsquina;
		movimientos = 0;
	}
	
	public int movimientos() {
		return movimientos;
	}
	
	public void setMovimientos (int unaCantidad){
		movimientos = unaCantidad;
	}
	
	public Esquina posicion(){
		return posicion;
	}
	
	public void setPosicion(Esquina unaEsquina){
		posicion= unaEsquina;
	}
	
	public TipoDeVehiculo miTipo(){
		
		return this.tipoDeVehiculo;
	}
	
	public void setTipoDeVehiculo(TipoDeVehiculo unTipo){
		
		this.tipoDeVehiculo = unTipo;
		
	}
	
	public void agregarMovimientos(int movimientos) {
		this.movimientos += movimientos;	
	}

	public void descontarMovimientos(int movimientos) {
		this.movimientos -= movimientos;
	}
	
    public void sumarPorcentajeDeMovimientos(int porcentaje){
    	int movimientosASumar;
		movimientosASumar= (porcentaje*this.movimientos)/100;
		this.movimientos += movimientosASumar;
	}
	
	public void descontarPorcentajeDeMovimientos(int porcentaje){
		int movimientosADescontar;
		movimientosADescontar= (porcentaje*this.movimientos)/100;
		this.movimientos -= movimientosADescontar;
	}
	
	public void avanzarHacia(Direccion unaDireccion) {	
		
		if ( posicion.tieneCalle(unaDireccion) ){
			Calle nuevaCalle= this.posicion.getCalle(unaDireccion);
		//	this.setPosicion(nuevaCalle.proximaEsquina(this));
		    this.cruzarCalle(nuevaCalle,unaDireccion);
		    this.agregarMovimientos(1);
		}
	}
	
	public void cruzarCalle(Calle unaCalle, Direccion unaDireccion){
		
		if ( unaCalle.tieneEncontrable() ) {
			Encontrable unObjeto = unaCalle.getEncontrable();
			unObjeto.cruzadoPor(this, unaDireccion);
		}
		
		else{
			Calle nuevaCalle= this.posicion.getCalle(unaDireccion);
			this.setPosicion(nuevaCalle.proximaEsquina(this));
		}
	}

	public String nombreDelVehiculo() {
		return tipoDeVehiculo.nombre();
	}

	public ImageIcon imagenVehiculo() {
		return tipoDeVehiculo.imagenVehiculo();
	}

}
