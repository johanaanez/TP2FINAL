package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import aplicacion.modelo.encontrables.ControlPolicial;
import aplicacion.modelo.encontrables.Piquete;
import aplicacion.modelo.encontrables.Pozo;
import aplicacion.modelo.encontrables.SorpresaCambioDeVehiculo;
import aplicacion.modelo.encontrables.SorpresaDesfavorable;
import aplicacion.modelo.encontrables.SorpresaFavorable;
import aplicacion.modelo.escenario.Calle;
import aplicacion.modelo.escenario.Direccion;
import aplicacion.modelo.escenario.Esquina;
import aplicacion.modelo.vehiculos.CuatroXCuatro;
import aplicacion.modelo.vehiculos.Moto;
import aplicacion.modelo.vehiculos.Vehiculo;

public class MotoTest {
	
	@Test
	public void testConstructorDePruebas(){
		
		Moto unaMoto = new Moto();
		Vehiculo unVehiculo = new Vehiculo(unaMoto);
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
	public void testMotoAvanzaCalleSinObstaculo(){
				
		Moto unaMoto = new Moto();
		Vehiculo unVehiculo = new Vehiculo(unaMoto);
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
	public void testMotoAvanzaCalleConPiquete(){
				
		Moto unaMoto = new Moto();
		Vehiculo unVehiculo = new Vehiculo(unaMoto);
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
		assertEquals(3, resultado);
		
		assertEquals(otraEsquina,unVehiculo.posicion());
	}
	
	@Test
	public void testMotoAvanzaCalleConPozo(){
				
		Moto unaMoto = new Moto();
		Vehiculo unVehiculo = new Vehiculo(unaMoto);
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
	public void testMotoAvanzaCalleConControlPolicialConProbabilidad10(){
		
		Moto unaMoto = new Moto(10);
		Vehiculo unVehiculo = new Vehiculo(unaMoto);
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
	public void testMotoAvanzaCalleConControlPolicialConProbabilidad0(){
		
		Moto unaMoto = new Moto(0);
		Vehiculo unVehiculo = new Vehiculo(unaMoto);
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
	public void testMotoAvanzaCalleConSorpresaFavorable(){
		
		Moto unaMoto = new Moto();
		Vehiculo unVehiculo = new Vehiculo(unaMoto);
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
	public void testMotoAvanzaCalleConSorpresaDesfavorable(){
		
		Moto unaMoto = new Moto();
		Vehiculo unVehiculo = new Vehiculo(unaMoto);
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
	public void testMotoAvanzaCalleConSorpresaCambioDeVehiculoCambiaAAutoYAvanzaCalleConPozo(){
		
		Moto unaMoto = new Moto();
		Vehiculo unVehiculo = new Vehiculo(unaMoto);
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
		assertEquals(5, otroResultado);

		assertEquals(tercerEsquina,unVehiculo.posicion());

		
		
	}

}
