package aplicacion.modelo.juego;


import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;

import javax.swing.ImageIcon;

import aplicacion.modelo.escenario.*;
import aplicacion.modelo.juego.dificultad.Dificultad;
import aplicacion.modelo.vehiculos.TipoDeVehiculo;
import aplicacion.persistencia.PersistidorUsuarios;

public class GPSChallenge extends Observable implements Serializable {
	
	private Jugador jugador;
	private ArrayList<String> usuarios;
	private ArrayList<Ganador> ganadores;
	private Escenario escenario;
	private Dificultad dificultad;
	
	public GPSChallenge(){
		
		jugador = new Jugador();
		
		try{
			PersistidorUsuarios recuperadorDeGanadores = new PersistidorUsuarios();
			this.ganadores = recuperadorDeGanadores.recuperarSoloPuntajes();
		}catch (FileNotFoundException error) {
			System.out.println("no hay ranking de puntaje guardado. ");
			this.ganadores = new ArrayList<Ganador>();

		}

		try{
			PersistidorUsuarios recuepradorDeUsuarios = new PersistidorUsuarios();
			this.usuarios = recuepradorDeUsuarios.recuperarSoloUsuarios();
		} catch (FileNotFoundException  error) {
			System.out.println("no hay usuarios guardados. Se creará una nueva lista de usuarios");
			this.usuarios = new ArrayList<String>();
		}		

		
	}
	
	public GPSChallenge(TipoDeVehiculo tipoDeVehiculo, Dificultad dificultad){
		this.jugador = new Jugador();
		this.dificultad = dificultad;
		this.escenario = new Escenario(this.dificultad.anchoDeMapa());
		Esquina posInicial = this.escenario.largada();
		this.escenario.insertarObstaculosYSorpresas(dificultad);
		this.jugador().vehiculo().setPosicion(posInicial);
		this.cambiarVehiculo(tipoDeVehiculo);
		
		try{
			PersistidorUsuarios recuperadorDeGanadores = new PersistidorUsuarios();
			this.ganadores = recuperadorDeGanadores.recuperarSoloPuntajes();
		}catch (FileNotFoundException error) {
			System.out.println("no hay ranking de puntaje guardado. ");
			this.ganadores = new ArrayList<Ganador>();

		}

		try{
			PersistidorUsuarios recuepradorDeUsuarios = new PersistidorUsuarios();
			this.usuarios = recuepradorDeUsuarios.recuperarSoloUsuarios();
		} catch (FileNotFoundException  error) {
			System.out.println("no hay usuarios guardados. Se creará una nueva lista de usuarios");
			this.usuarios = new ArrayList<String>();
		}		

	}
	
	
	public GPSChallenge(Dificultad dificultad, Jugador unJugador, int largada, int llegada){
		this.jugador = unJugador;
		this.dificultad = dificultad;
		this.escenario = new Escenario(this.dificultad.anchoDeMapa(), largada, llegada);
		Esquina posInicial = this.escenario.largada();
		this.jugador().vehiculo().setPosicion(posInicial);
		this.escenario.insertarObstaculosYSorpresas(dificultad);
		

		try{
			PersistidorUsuarios recuperadorDeGanadores = new PersistidorUsuarios();
			this.ganadores = recuperadorDeGanadores.recuperarSoloPuntajes();
		}catch (FileNotFoundException error) {
			System.out.println("no hay ranking de puntaje guardado. ");
			this.ganadores = new ArrayList<Ganador>();

		}

		try{
			PersistidorUsuarios recuepradorDeUsuarios = new PersistidorUsuarios();
			this.usuarios = recuepradorDeUsuarios.recuperarSoloUsuarios();
		} catch (FileNotFoundException  error) {
			System.out.println("no hay usuarios guardados. Se creará una nueva lista de usuarios");
			this.usuarios = new ArrayList<String>();
		}		

		
	}


	public GPSChallenge(Dificultad dificultad) {
		this.jugador = new Jugador();
		this.dificultad = dificultad;
		this.escenario = new Escenario(this.dificultad.anchoDeMapa());
		Esquina posInicial = this.escenario.largada();
		this.escenario.insertarObstaculosYSorpresas(dificultad);
		this.jugador().vehiculo().setPosicion(posInicial);
		

		try{
			PersistidorUsuarios recuperadorDeGanadores = new PersistidorUsuarios();
			this.ganadores = recuperadorDeGanadores.recuperarSoloPuntajes();
		}catch (FileNotFoundException error) {
			System.out.println("no hay ranking de puntaje guardado. ");
			this.ganadores = new ArrayList<Ganador>();

		}

		try{
			PersistidorUsuarios recuepradorDeUsuarios = new PersistidorUsuarios();
			this.usuarios = recuepradorDeUsuarios.recuperarSoloUsuarios();
		} catch (FileNotFoundException  error) {
			System.out.println("no hay usuarios guardados. Se creará una nueva lista de usuarios");
			this.usuarios = new ArrayList<String>();
		}		

	}

	public Jugador jugador() {
		return jugador;
	}
	
	public ArrayList<String> usuarios(){
		return usuarios;
	}
	
	public ArrayList<Ganador> ganadores(){
		
		return ganadores;
	}
	

	public Dificultad dificultad(){
		return dificultad;
	}

	public Escenario escenario() {
		return escenario;
	}
	
	public void agregarUsuario(String nombreJugador){
		if (usuarioNoExistente(nombreJugador) == true){
			usuarios.add(nombreJugador);
			PersistidorUsuarios per = new PersistidorUsuarios();
			per.persistirSoloUsuarios(usuarios);
		}
	}
	
	public boolean usuarioNoExistente(String unNombre){
		
		for (int pos=0 ; pos < this.usuarios.size() ; pos++ ){
			String nombre = (String)this.usuarios.get(pos);
			if (unNombre == nombre){
				System.out.println("el usuario "+unNombre+" ya existe");
				return false;
			}
		}
		return true;
	}
	
	
	public void setGanadores(ArrayList<Ganador> rankingGanadores){
		
		ganadores = rankingGanadores;
	}
	
	public boolean tieneJugador(Jugador unJugador){
		return (usuarios.contains(unJugador));
	}
	
	public Jugador buscarJugador(Jugador unJugador){
		if (tieneJugador(unJugador)){
			return unJugador;
		}
		return null;
	}
	
	public void ActualizarObservadores(){
		setChanged();
		notifyObservers();		
	}
	
	public void asignarPuntajeAJugador(){
		
		this.jugador.setPuntaje(puntosDelJugador());
	}

	public int puntosDelJugador() {
		return ( (dificultad.limiteMovimientos() - jugador.movimientos()) * dificultad.puntajeMovimientoSobrante());
	}

	public boolean juegoGanado() {
		if (jugador.posicion() == escenario.llegada()) {
			return true;
		}else{
			return false;
		}
	}
	
	public boolean juegoPerdido(){
		if (jugador.movimientos() > dificultad.limiteMovimientos()){
			return true;
		}
		return false;
	}

	public int movimientosLimites() {
		return dificultad.limiteMovimientos();
	}

	public int movimientosHechos() {
		return jugador.movimientos();
	}

	public String nombreDelVehiculo() {
		return jugador.nombreDelVehiculo();
	}

	public void moverVehiculo(Direccion direccion) {
		jugador.mover(direccion);
		if (juegoGanado() & (puntosDelJugador() >= 0)){
			asignarPuntajeAJugador();
			agregarGanadorAlRankingDePuntajes(jugador);
			PersistidorUsuarios per = new PersistidorUsuarios();
			per.persistirSoloPuntajes(ganadores);
		}
		this.ActualizarObservadores();
	}

	public Esquina llegada() {
		return escenario.llegada();
	}

	public Esquina esquinaVehiculo() {
		return jugador.esquinaVehiculo();
	}

	public ImageIcon imagenMapa() {
		return dificultad.imagenMapa();
	}

	public ImageIcon imagenVehiculo() {
		return jugador.imagenVehiculo();
	}

	public int anchoMapa() {
		return dificultad.anchoDeMapa();
	}
	
	public boolean agregarGanadorAlRankingDePuntajes(Jugador unJugador){
		
		Ganador unGanador = new Ganador();
		unGanador.setPuntaje(unJugador.puntaje());
		unGanador.setNombre(unJugador.nombre());
		
		for (int pos=0; pos < ganadores.size(); pos++){
			if(ganadores.get(pos).getPuntaje() < unJugador.puntaje())
			{
				ganadores.add(pos,unGanador);
				return true;
			}
		}
		ganadores.add(unGanador);
		return false;
	}

	public void cambiarVehiculo(TipoDeVehiculo tipoDeVehiculo) {
		jugador.cambiarVehiculo(tipoDeVehiculo);
	}

	public void cambiarJugadorActual(String unNombre){
		jugador.cambiarNombre(unNombre);
	}
	
	public String getUsuario(int numeroDeUsuario){
		if (numeroDeUsuario <= usuarios.size()){ //hacer un metodo que te devuelva el nro de usuario en GPSChallenge
			numeroDeUsuario= numeroDeUsuario - 1 ;
			String nombre= usuarios.get(numeroDeUsuario);
			return nombre;
		}
		return null;
	}
}
