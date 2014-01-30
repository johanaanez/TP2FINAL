package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import aplicacion.modelo.vehiculos.*;
import aplicacion.modelo.escenario.*;

public class VehiculoTest {

	@Test
	public void testCrear(){
		Vehiculo vehiculo = new Vehiculo();
		assertTrue(vehiculo != null);
	}
	
	@Test
	public void testCrearComoAuto(){
		Vehiculo vehiculo = new Vehiculo(new Auto());
		assertTrue(vehiculo != null);
	}

	@Test
	public void testCrearComoMoto(){
		Vehiculo vehiculo = new Vehiculo(new Moto());
		assertTrue(vehiculo != null);
	}
	
	@Test
	public void testCrearComo4x4(){
		Vehiculo vehiculo = new Vehiculo(new CuatroXCuatro());
		assertTrue(vehiculo != null);
	}
	
	@Test
    public void testSetearPosicion(){
    	Esquina unaEsquina= new Esquina();
    	Esquina otraEsquina= new Esquina();
    	Vehiculo unVehiculo= new Vehiculo(unaEsquina);
    	unVehiculo.setPosicion(otraEsquina);
    	
    	assertTrue( unVehiculo.posicion()== otraEsquina);
	}
		
	@Test
	public void testAgregarMovimientos(){
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.agregarMovimientos(5);
		assertTrue( vehiculo.movimientos() == 5 );
	}
	
	@Test
	public void testDescontarMovimientos(){
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.agregarMovimientos(5);
		vehiculo.descontarMovimientos(3);
		assertTrue( vehiculo.movimientos() == 2 );
	}
	
	@Test
	public void testSumarPorcentajeDeMovimientos(){
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.agregarMovimientos(8);
		vehiculo.sumarPorcentajeDeMovimientos(25);
		assertTrue( vehiculo.movimientos() == 10 );
	}
	
	@Test
	public void testDescontarPorcentajeDeMovimientos(){
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.agregarMovimientos(10);
		vehiculo.descontarPorcentajeDeMovimientos(20);
		assertTrue( vehiculo.movimientos() == 8 );
	}
	
}