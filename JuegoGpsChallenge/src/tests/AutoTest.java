package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


import aplicacion.modelo.encontrables.*;
import aplicacion.modelo.escenario.Calle;
import aplicacion.modelo.escenario.Direccion;
import aplicacion.modelo.escenario.Esquina;
import aplicacion.modelo.vehiculos.Auto;
import aplicacion.modelo.vehiculos.Vehiculo;

public class AutoTest {
	

	@Test
	public void testConstructorDePruebas(){
		
		Auto unAuto = new Auto();
		Vehiculo unVehiculo = new Vehiculo(unAuto);
		Calle unaCalle = new Calle();
		Esquina unaEsquina = new Esquina();
		Esquina otraEsquina = new Esquina();
		
		
		unaEsquina.setCalleDerecha(unaCalle);
		otraEsquina.setCalleIzquierda(unaCalle);
		unVehiculo.setPosicion(unaEsquina);
		unaCalle.setEsquinas(unaEsquina, otraEsquina);
		
		assertTrue(unaEsquina.tieneCalle(Direccion.DERECHA));
		
		assertEquals(unaEsquina.getCalle(Direccion.DERECHA),unaCalle);
	
		assertEquals(unaEsquina,unVehiculo.posicion());
		
		assertEquals(otraEsquina,unaCalle.proximaEsquina(unVehiculo));
		
		
	}
	
	
	@Test
	public void testAutoAvanzaCalleSinObstaculo(){
				
		Auto unAuto = new Auto();
		Vehiculo unVehiculo = new Vehiculo(unAuto);
		Calle unaCalle = new Calle();
		Esquina unaEsquina = new Esquina();
		Esquina otraEsquina = new Esquina();
		
		
		unaEsquina.setCalleDerecha(unaCalle);
		otraEsquina.setCalleIzquierda(unaCalle);
		unVehiculo.setPosicion(unaEsquina);
		unaCalle.setEsquinas(unaEsquina, otraEsquina);
		
		
		
		unVehiculo.avanzarHacia(Direccion.DERECHA);
		
		int resultado = unVehiculo.movimientos();
		assertEquals(1, resultado);
		
		assertEquals(otraEsquina,unVehiculo.posicion());
		
		
	}
	
	@Test
	public void testAutoAvanzaCalleConPozo(){
				
		Auto unAuto = new Auto();
		Vehiculo unVehiculo = new Vehiculo(unAuto);
		Calle unaCalle = new Calle();
		Esquina unaEsquina = new Esquina();
		Esquina otraEsquina = new Esquina();
		
		Pozo unPozo = new Pozo();
		unaCalle.agregarEncontrable(unPozo);
		
		unaEsquina.setCalleDerecha(unaCalle);
		otraEsquina.setCalleIzquierda(unaCalle);
		unVehiculo.setPosicion(unaEsquina);
		unaCalle.setEsquinas(unaEsquina, otraEsquina);
			
		unVehiculo.avanzarHacia(Direccion.DERECHA);
		
		int resultado = unVehiculo.movimientos();
		assertEquals(4, resultado);
		
		assertEquals(otraEsquina,unVehiculo.posicion());
	}
	
	@Test
	public void testAutoAvanzaCalleConPiquete(){
				
		Auto unAuto = new Auto();
		Vehiculo unVehiculo = new Vehiculo(unAuto);
		Calle unaCalle = new Calle();
		Esquina unaEsquina = new Esquina();
		Esquina otraEsquina = new Esquina();
		Piquete unPiquete = new Piquete();
		unaCalle.agregarEncontrable(unPiquete);
		
		unaEsquina.setCalleDerecha(unaCalle);
		otraEsquina.setCalleIzquierda(unaCalle);
		unVehiculo.setPosicion(unaEsquina);
		unaCalle.setEsquinas(unaEsquina, otraEsquina);
			
		unVehiculo.avanzarHacia(Direccion.DERECHA);
		
		int resultado = unVehiculo.movimientos();
		assertEquals(1, resultado);
		
		assertEquals(unaEsquina,unVehiculo.posicion());
	}
	
	
	
	@Test
	public void testAutoAvanzaCalleConControlPolicialConProbabilidadDeDetencion10(){
		
		Auto unAuto = new Auto(10);
		Vehiculo unVehiculo = new Vehiculo(unAuto);
		Calle unaCalle = new Calle();
		Esquina unaEsquina = new Esquina();
		Esquina otraEsquina = new Esquina();
		
		ControlPolicial unControlPolicial = new ControlPolicial();
		unaCalle.agregarEncontrable(unControlPolicial);
		
		unaEsquina.setCalleDerecha(unaCalle);
		otraEsquina.setCalleIzquierda(unaCalle);
		unVehiculo.setPosicion(unaEsquina);
		unaCalle.setEsquinas(unaEsquina, otraEsquina);
			
		unVehiculo.avanzarHacia(Direccion.DERECHA);
		
		int resultado = unVehiculo.movimientos();
		assertEquals(4, resultado);
		
		assertEquals(otraEsquina,unVehiculo.posicion());
	}
	
	
	@Test
	public void testAutoAvanzaCalleConControlPolicialConProbabilidadDeDEtencion0(){
		
		Auto unAuto = new Auto(0);
		Vehiculo unVehiculo = new Vehiculo(unAuto);
		Calle unaCalle = new Calle();
		Esquina unaEsquina = new Esquina();
		Esquina otraEsquina = new Esquina();
		
		ControlPolicial unControlPolicial = new ControlPolicial();
		unaCalle.agregarEncontrable(unControlPolicial);
		
		unaEsquina.setCalleDerecha(unaCalle);
		otraEsquina.setCalleIzquierda(unaCalle);
		unVehiculo.setPosicion(unaEsquina);
		unaCalle.setEsquinas(unaEsquina, otraEsquina);
		
		unVehiculo.avanzarHacia(Direccion.DERECHA);
		
		int resultado = unVehiculo.movimientos();
		assertEquals(1, resultado);
		
		assertEquals(otraEsquina,unVehiculo.posicion());
	}
	
	@Test
	public void testAutoAvanzaCalleConSorpresaFavorable(){
		
		Auto unAuto = new Auto();
		Vehiculo unVehiculo = new Vehiculo(unAuto);
		Calle unaCalle = new Calle();
		Esquina unaEsquina = new Esquina();
		Esquina otraEsquina = new Esquina();
		
		unVehiculo.agregarMovimientos(10);
		
		SorpresaFavorable unaSorpresa = new SorpresaFavorable();
		unaCalle.agregarEncontrable(unaSorpresa);
		
		unaEsquina.setCalleDerecha(unaCalle);
		otraEsquina.setCalleIzquierda(unaCalle);
		unVehiculo.setPosicion(unaEsquina);
		unaCalle.setEsquinas(unaEsquina, otraEsquina);
		
		unVehiculo.avanzarHacia(Direccion.DERECHA);
		
		int resultado = unVehiculo.movimientos();
		assertEquals(9, resultado);
		
		assertEquals(otraEsquina,unVehiculo.posicion());
	}
	
	@Test
	public void testAutoAvanzaCalleConSorpresaDesfavorable(){
		
		Auto unAuto = new Auto();
		Vehiculo unVehiculo = new Vehiculo(unAuto);
		Calle unaCalle = new Calle();
		Esquina unaEsquina = new Esquina();
		Esquina otraEsquina = new Esquina();
		
		unVehiculo.agregarMovimientos(8);
		
		SorpresaDesfavorable unaSorpresa = new SorpresaDesfavorable();
		unaCalle.agregarEncontrable(unaSorpresa);
		
		unaEsquina.setCalleDerecha(unaCalle);
		otraEsquina.setCalleIzquierda(unaCalle);
		unVehiculo.setPosicion(unaEsquina);
		unaCalle.setEsquinas(unaEsquina, otraEsquina);
			
		unVehiculo.avanzarHacia(Direccion.DERECHA);
		
		int resultado = unVehiculo.movimientos();
		assertEquals(11, resultado);
		
		assertEquals(otraEsquina,unVehiculo.posicion());
	}	
	
	@Test
	public void testAutoAvanzaCalleConSorpresaCambioDeVehiculoCambiaA4X4YAvanzaCalleConPiquete(){
		
		Auto unAuto = new Auto();
		Vehiculo unVehiculo = new Vehiculo(unAuto);
		Calle unaCalle = new Calle();
		Calle otraCalle = new Calle();
		Esquina unaEsquina = new Esquina();
		Esquina otraEsquina = new Esquina();
		Esquina tercerEsquina = new Esquina();
		
		SorpresaCambioDeVehiculo unaSorpresa = new SorpresaCambioDeVehiculo();
		unaCalle.agregarEncontrable(unaSorpresa);
		
		Piquete unPiquete = new Piquete();
		otraCalle.agregarEncontrable(unPiquete);
		
		unaEsquina.setCalleDerecha(unaCalle);
		otraEsquina.setCalleIzquierda(unaCalle);
		otraEsquina.setCalleArriba(otraCalle);
		tercerEsquina.setCalleAbajo(otraCalle);
		otraCalle.setEsquinas(otraEsquina, tercerEsquina);
		unVehiculo.setPosicion(unaEsquina);
		unaCalle.setEsquinas(unaEsquina, otraEsquina);
			
		unVehiculo.avanzarHacia(Direccion.DERECHA);
		
		int resultado = unVehiculo.movimientos();
		assertEquals(1, resultado);
		
		assertEquals(otraEsquina,unVehiculo.posicion());
		
		unVehiculo.avanzarHacia(Direccion.ARRIBA);
		
		int otroResultado = unVehiculo.movimientos();
		assertEquals(2, otroResultado);

		assertEquals(otraEsquina,unVehiculo.posicion());

		
		
	}
	
	
	@Test
	public void testAutoAvanzaCalleConSorpresaCambioDeVehiculoCambiaA4X4YAvanzaCalleConPozo(){
		
		Auto unAuto = new Auto();
		Vehiculo unVehiculo = new Vehiculo(unAuto);
		Calle unaCalle = new Calle();
		Calle otraCalle = new Calle();
		Esquina unaEsquina = new Esquina();
		Esquina otraEsquina = new Esquina();
		Esquina tercerEsquina = new Esquina();
		
		SorpresaCambioDeVehiculo unaSorpresa = new SorpresaCambioDeVehiculo();
		unaCalle.agregarEncontrable(unaSorpresa);
		
		Pozo unPozo = new Pozo();
		otraCalle.agregarEncontrable(unPozo);
		
		unaEsquina.setCalleDerecha(unaCalle);
		otraEsquina.setCalleIzquierda(unaCalle);
		otraEsquina.setCalleArriba(otraCalle);
		tercerEsquina.setCalleAbajo(otraCalle);
		otraCalle.setEsquinas(otraEsquina, tercerEsquina);
		unVehiculo.setPosicion(unaEsquina);
		unaCalle.setEsquinas(unaEsquina, otraEsquina);
			
		unVehiculo.avanzarHacia(Direccion.DERECHA);
		
		int resultado = unVehiculo.movimientos();
		assertEquals(1, resultado);
		
		assertEquals(otraEsquina,unVehiculo.posicion());
		
		unVehiculo.avanzarHacia(Direccion.ARRIBA);
		
		int otroResultado = unVehiculo.movimientos();
		assertEquals(2, otroResultado);

		assertEquals(tercerEsquina,unVehiculo.posicion());

		
		
	}

}
