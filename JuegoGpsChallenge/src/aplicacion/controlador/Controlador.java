package aplicacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.beans.XMLDecoder;

import javax.swing.ButtonModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import aplicacion.modelo.escenario.Direccion;
import aplicacion.modelo.juego.*;
import aplicacion.modelo.juego.dificultad.*;
import aplicacion.modelo.vehiculos.*;
import aplicacion.vista.*;
import aplicacion.persistencia.*;

public class Controlador {

	public GPSChallenge modelo;
	private VistaGPS vista;
	private VistaBienvenida vista2;
	
	
	public void cargarModelo(GPSChallenge modeloRecibido) {
		this.modelo = modeloRecibido;
	}

	public void cargarVista(VistaGPS vistaRecibida) {
		this.vista = vistaRecibida;
	}

	public Controlador(){
		
	}
	
	public GPSChallenge modelo(){
		return modelo;
	}
	
	public void cerrarVentana(JFrame unaVentana){
		unaVentana.setVisible(false);
		unaVentana.dispose();
	}
	
	public class ListenerBotonNuevoUsuario implements ActionListener {
		
		private VistaBienvenida ventanaAnterior;
		
		ListenerBotonNuevoUsuario(VistaBienvenida unaVentana){
			ventanaAnterior= unaVentana;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			GPSChallenge unModelo = ventanaAnterior.getModelo();
			VistaBienvenida bienvenida= new VistaBienvenida("Nuevo Usuario",400,300, unModelo);
			cerrarVentana(ventanaAnterior);
			bienvenida.usuarioNuevo();
		}
	}
	
	public ActionListener obtenerListenerNuevoUsuario(VistaBienvenida unaVentana) {
		return new ListenerBotonNuevoUsuario(unaVentana);
	}
	
	public class ListenerBotonUsuarioExistente implements ActionListener {
		
		private GPSChallenge miModelo;
		private VistaBienvenida ventanaAnterior;
		
		public ListenerBotonUsuarioExistente(GPSChallenge modelo, VistaBienvenida unaVentana){
			miModelo= modelo;
			ventanaAnterior= unaVentana;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			GPSChallenge unModelo = ventanaAnterior.getModelo();
			VistaBienvenida bienvenida= new VistaBienvenida("Usuario Logueado",300,700, unModelo);
			cerrarVentana(ventanaAnterior);
			bienvenida.usuarioExistente(miModelo.usuarios());
		}
	}
	
	public ActionListener obtenerListenerUsuarioExistente(VistaBienvenida unaVentana, GPSChallenge unModelo) {
		cargarModelo(unModelo);
		return (new ListenerBotonUsuarioExistente(this.modelo,unaVentana ));
	}
	
	
	public class ListenerBotonGuardarNuevoUsuario implements ActionListener {
		
		private JTextField campoNombre;
		private GPSChallenge miModelo;
		private VistaBienvenida ventanaAnterior;
		
        public ListenerBotonGuardarNuevoUsuario(JTextField unCampo, GPSChallenge unModelo, VistaBienvenida unaVentana){
    	   campoNombre = unCampo;
    	   miModelo= unModelo;
    	   ventanaAnterior= unaVentana;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			GPSChallenge unModelo = ventanaAnterior.getModelo();
			VistaBienvenida unMenu= new VistaBienvenida("GPS CHALLENGE", 300, 300, unModelo);
			String nombre= campoNombre.getText();
			miModelo.jugador().cambiarNombre(nombre);
			miModelo.agregarUsuario(nombre);
			cerrarVentana(ventanaAnterior);
			unMenu.verMenu(nombre);
		}
	}
	
	public ActionListener obtenerListenerBotonGuardarNuevoUsuario(JTextField campoNombre,VistaBienvenida unaVentana, GPSChallenge unModelo) {
		cargarModelo(unModelo);
		return new ListenerBotonGuardarNuevoUsuario(campoNombre, this.modelo(), unaVentana);
	}
	
	
    public class ListenerBotonElegirUsuarioExistente implements ActionListener {
		
		private JTextField campoNumero;
		private VistaBienvenida ventanaActual;
		private GPSChallenge miModelo;
		
		public ListenerBotonElegirUsuarioExistente(JTextField nroDeUsuario,  VistaBienvenida unaVentana){
			campoNumero= nroDeUsuario;
			ventanaActual= unaVentana;
			miModelo= ventanaActual.getModelo();
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			
			String indice= campoNumero.getText();
			int numeroDeUsuario = Integer.parseInt(indice); //se castea a int el valor del campo que ingreso el usuario
			String nombre=miModelo.getUsuario(numeroDeUsuario);
			
			if (nombre!= null){
				cerrarVentana(ventanaActual);			
				miModelo.cambiarJugadorActual(nombre);
				VistaBienvenida unMenu= new VistaBienvenida("Menu", 300, 300,miModelo);
				unMenu.verMenu(nombre);
			}
			else{
				VistaBienvenida unMjeDeError= new VistaBienvenida("Menu", 300, 100,miModelo);
				unMjeDeError.errorDeUsuario();
		
			}
		}
	}

	public ActionListener obtenerListenerBotonUsuarioExistente(JTextField unCampo,VistaBienvenida unaVentana) {
		return new ListenerBotonElegirUsuarioExistente(unCampo, unaVentana);
	}
	
	
	public class ListenerBotonAtrasLoguearUsuario implements ActionListener {
		
		private VistaBienvenida ventanaAnterior;
			
		public ListenerBotonAtrasLoguearUsuario(VistaBienvenida unaVentana){
			ventanaAnterior= unaVentana;
		}
			
		@Override
		public void actionPerformed(ActionEvent e) {
			GPSChallenge unModelo = ventanaAnterior.getModelo();
			VistaBienvenida unMenu= new VistaBienvenida("GPS CHALLENGE", 500, 400, unModelo);
			cerrarVentana(ventanaAnterior);
			unMenu.saludar();
		}
	}

		
	public ActionListener obtenerListenerBotonAtrasLoguearUsuario(VistaBienvenida unaVentana) {
		return new ListenerBotonAtrasLoguearUsuario(unaVentana);
	}
	

	public class ListenerBotonComenzarPartida implements ActionListener {
		
		private VistaBienvenida ventanaAnterior;
		private GPSChallenge modelo;
		
		public ListenerBotonComenzarPartida(VistaBienvenida ventana){
			ventanaAnterior= ventana;
			modelo = ventana.getModelo();
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			cerrarVentana (ventanaAnterior);
			Eleccion eleccionDeDificultad= new Eleccion(modelo);	
		}
	}
	
	public ActionListener obtenerListenerComenzarPartida( VistaBienvenida ventana) {
		return new ListenerBotonComenzarPartida(ventana);
	}
	
	public class ListenerBotonPuntajes implements ActionListener {
		
		private GPSChallenge miModelo;
		private VistaBienvenida ventanaActual;
		
		public ListenerBotonPuntajes(VistaBienvenida unaVentana){
			ventanaActual= unaVentana; //ventanaActual es el menu
			miModelo= unaVentana.getModelo();
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			cerrarVentana(ventanaActual);
			Puntaje ventanaDePuntajes= new Puntaje(miModelo);
		}
	}
	
	public ActionListener obtenerListenerPuntajes(VistaBienvenida unaVentana) {
		return new ListenerBotonPuntajes(unaVentana);
	}
	
	public class ListenerBotonGuardar implements ActionListener {
		private GPSChallenge modelo;

		public ListenerBotonGuardar(GPSChallenge modeloRecibido) {
			modelo = modeloRecibido;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (this.modelo.juegoGanado()) {
				JOptionPane.showOptionDialog(
						null,
						"El juego no se puede guardar en este estado",
						"Error",
						   JOptionPane.OK_OPTION,
						   JOptionPane.ERROR_MESSAGE,
						  null,    
						   new String[]{"Acepto"},   
						   null);

			} else {
				try {

					Persistidor persistidor = new Persistidor();
					persistidor.guardarJuego(this.modelo);

				} catch (IOException error) {
					JOptionPane.showOptionDialog(
							null,
							"No se pudo escribir en el disco.",
							"Error",
							   JOptionPane.OK_OPTION,
							   JOptionPane.ERROR_MESSAGE,
							  null,    
							   new String[]{"Acepto"},   
							   null);
				}
			}
		}

	}
	
	public ActionListener obtenerListenerBotonGuardar() {
		return new ListenerBotonGuardar(this.modelo);
	}

	
	public class ListenerBotonCargar implements ActionListener {
		private Controlador control;
		private VistaGPS vista;
		private GPSChallenge modelo;
		
		public ListenerBotonCargar(GPSChallenge unModelo, Controlador controlador) {
			this.control = controlador;
			this.modelo = unModelo;

		}

		@Override
		public void actionPerformed(ActionEvent e) {

			try {
				Persistidor recuperador = new Persistidor();
				GPSChallenge juegoRecuperado = recuperador.recuperarJuego(modelo.jugador().nombre());
			
				
				this.control.cargarModelo(juegoRecuperado);
				juegoRecuperado.ActualizarObservadores();
				VistaGPS vista = new VistaGPS(juegoRecuperado, control);


			} catch (FileNotFoundException  error) {
				JOptionPane.showOptionDialog(
						null,
						"No se pudo leer el archivo desde el disco.",
						"Error",
						   JOptionPane.OK_OPTION,
						   JOptionPane.ERROR_MESSAGE,
						  null,    
						   new String[]{"Acepto"},   
						   null);
			}
		}

	}
	
	public ActionListener obtenerListenerBotonCargar(GPSChallenge unModelo, Controlador control) {
		return new ListenerBotonCargar(unModelo, control);
	}
	
	public ActionListener obtenerListenerBotonArriba() {
		return new ListenerBotonArriba(this, vista);
	}
	
	public class ListenerBotonArriba implements ActionListener{
		private Controlador control;
		private VistaGPS vista;

		public ListenerBotonArriba(Controlador controlador, VistaGPS vista) {
			this.control = controlador;
			this.vista = vista;

		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			modelo.moverVehiculo(Direccion.ARRIBA);
			System.out.println("Arriba");
		}
		
	}
	
	public ActionListener obtenerListenerBotonIzquierda() {
		return new ListenerBotonIzquierda(this, vista);
	}
	
	public class ListenerBotonIzquierda implements ActionListener{
		private Controlador control;
		private VistaGPS vista;

		public ListenerBotonIzquierda(Controlador controlador, VistaGPS vista) {
			this.control = controlador;
			this.vista = vista;

		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			modelo.moverVehiculo(Direccion.IZQUIERDA);
			System.out.println("Izquierda");
		}
		
	}
	
	public ActionListener obtenerListenerBotonAbajo() {
		return new ListenerBotonAbajo(this, vista);
	}
	
	public class ListenerBotonAbajo implements ActionListener{
		private Controlador control;
		private VistaGPS vista;

		public ListenerBotonAbajo(Controlador controlador, VistaGPS vista) {
			this.control = controlador;
			this.vista = vista;

		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			modelo.moverVehiculo(Direccion.ABAJO);
			System.out.println("Abajo");
		}
		
	}
	
	public ActionListener obtenerListenerBotonDerecha() {
		return new ListenerBotonDerecha(this, vista);
	}
	
	public class ListenerBotonDerecha implements ActionListener{
		private Controlador control;
		private VistaGPS vista;

		public ListenerBotonDerecha(Controlador controlador, VistaGPS vista) {
			this.control = controlador;
			this.vista = vista;

		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			modelo.moverVehiculo(Direccion.DERECHA);
			System.out.println("Derecha");
		}
	}

	
	private Dificultad crearDificultad(String stringDificultad) {
		if (stringDificultad == "Facil"){
			return new Facil();
		}
		if (stringDificultad == "Medio"){
			return new Medio();
		}else{
			return new Dificil();
		}
	}

	private TipoDeVehiculo crearVehiculo(String stringTipoDeVehiculo) {
		if (stringTipoDeVehiculo == "Auto"){
			return new Auto();
		}
		if (stringTipoDeVehiculo == "Moto"){
			return new Moto();
		}else{
			return new CuatroXCuatro();
		}
	}

	public class ListenerBotonAceptar implements ActionListener{
		private GPSChallenge modelo;
		private Controlador controlador;
		private Eleccion eleccion;
		private VistaBienvenida ventanaAnterior;
		
		public ListenerBotonAceptar(Eleccion eleccion, Controlador controlador) {
			this.eleccion = eleccion;
			this.controlador = controlador;
			this.modelo = controlador.modelo();
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			Dificultad dificultad = controlador.crearDificultad(eleccion.getSeleccionDificultad());
			TipoDeVehiculo tipoDeVehiculo = controlador.crearVehiculo(eleccion.getSeleccionVehiculo());
			String unNombre = modelo.jugador().nombre();
			modelo = new GPSChallenge(tipoDeVehiculo,dificultad);
			modelo.jugador().cambiarNombre(unNombre);
			controlador.cargarModelo(modelo);
			
			// esto es necesario para que la vista se actualice la 1ra vez
			modelo.ActualizarObservadores();
			eleccion.cerrarVentana();
			VistaGPS vista = new VistaGPS(modelo, controlador);
		}	
	}
	
	public ActionListener obtenerListenerBotonAceptar(Eleccion eleccion) {
		return new ListenerBotonAceptar(eleccion, this);
	}
	
	public class ListenerBotonAtrasEleccion implements ActionListener{
		
		private GPSChallenge miModelo;
		private Eleccion ventanaActual;
		
		public ListenerBotonAtrasEleccion(Eleccion unaVentana){
			ventanaActual= unaVentana;	
			miModelo= ventanaActual.getModelo();
		}
		

		@Override
		public void actionPerformed(ActionEvent e) {
			GPSChallenge unModelo = ventanaActual.getModelo();
			VistaBienvenida unMenu= new VistaBienvenida("GPS CHALLENGE", 300, 300, unModelo);
			cerrarVentana(ventanaActual);
			unMenu.verMenu(miModelo.jugador().nombre());	
		}
	}
	
	
	public ActionListener obtenerListenerBotonAtrasEleccion(Eleccion unaVentana){
		return (new ListenerBotonAtrasEleccion(unaVentana));
	}
	
	//aca
	
       public class ListenerBotonAtrasPuntaje implements ActionListener{
		
		private GPSChallenge miModelo;
		private Puntaje ventanaActual;
		
		public ListenerBotonAtrasPuntaje(Puntaje unaVentana){
		     ventanaActual= unaVentana;
		     miModelo= ventanaActual.getModelo();			
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			VistaBienvenida unMenu= new VistaBienvenida("MENU", 300, 300);
			cerrarVentana(ventanaActual);
			unMenu.verMenu(miModelo.jugador().nombre());
		}
	}
       
	
	public ActionListener obtenerListenerBotonAtrasPuntaje(Puntaje unaVentana){
		return (new ListenerBotonAtrasPuntaje(unaVentana));
	}
	
	public class ListenerBotonAtrasEscenario implements ActionListener{
		
		private GPSChallenge miModelo;
		private VistaGPS ventanaActual;
		
		public ListenerBotonAtrasEscenario(VistaGPS unaVista){
			ventanaActual= unaVista;
			miModelo= unaVista.getModelo();
		}
		
		public void actionPerformed(ActionEvent e) {
			ventanaActual.cerrarVentana();
			Eleccion unaEleccion= new Eleccion(miModelo);
		}
	}
	
	public ActionListener obtenerListenerBotonAtrasEscenario(VistaGPS unaVista){
		return (new ListenerBotonAtrasEscenario(unaVista));
	}
	 
	// aca
	
	public class ListenerBotonGanador implements ActionListener{
		
		private GPSChallenge miModelo;
		private VistaGPS ventanaActual;
		
		public ListenerBotonGanador(VistaGPS unaVista){
			ventanaActual= unaVista; 
			miModelo= unaVista.getModelo();
		}
		
		public void actionPerformed(ActionEvent e) {
			ventanaActual.cerrarVentana();
			VistaBienvenida unaVentana= new VistaBienvenida ("GANASTE", 500, 300, miModelo);
			unaVentana.ganaste(miModelo.jugador().nombre());
		}
	}
	
	public ActionListener obtenerListenerBotonGanador(VistaGPS unaVista){
		return (new ListenerBotonGanador(unaVista));
	}
	
	
	public class ListenerBotonJugarOtraVez implements ActionListener{
		
		private GPSChallenge miModelo;
		private VistaBienvenida ventanaActual;
		
		public ListenerBotonJugarOtraVez(VistaBienvenida unaVentana){
			ventanaActual= unaVentana; 
			miModelo= unaVentana.getModelo();
		}
		
		public void actionPerformed(ActionEvent e) {
			cerrarVentana(ventanaActual);
			VistaBienvenida unaVentana= new VistaBienvenida ("Menu", 300, 300, miModelo);
			unaVentana.verMenu(miModelo.jugador().nombre());
		}
	}
	
	public ActionListener obtenerListenerBotonJugarOtraVez(VistaBienvenida unaVentana){
		return (new ListenerBotonJugarOtraVez(unaVentana))
;	}
	
	public void cargarVista(VistaBienvenida vista2) {
		this.vista2 = vista2;
	}
}
