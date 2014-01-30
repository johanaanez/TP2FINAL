package aplicacion.vista;
import aplicacion.controlador.Controlador;
import aplicacion.modelo.juego.GPSChallenge;
import aplicacion.modelo.juego.Jugador;


import aplicacion.persistencia.Persistidor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Dialog.ModalExclusionType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


public class VistaBienvenida extends JFrame {
		
	private GPSChallenge modelo;
	private Controlador control;
	
		public VistaBienvenida (String nombreDeLaVentana,int ancho, int alto) {
			super(nombreDeLaVentana);
		    setSize(ancho ,alto);
		    //setResizable(false);
		    setVisible (true);
		    setDefaultCloseOperation(EXIT_ON_CLOSE);
		    modelo= new GPSChallenge();
		    control= new Controlador();
	 }
		
		public VistaBienvenida (String nombreDeLaVentana,int ancho, int alto, GPSChallenge unModelo) {
			super(nombreDeLaVentana);
		    setSize(ancho ,alto);
		    //setResizable(false);
		    setVisible (true);
		    setDefaultCloseOperation(EXIT_ON_CLOSE);
		    modelo= unModelo;
		    control= new Controlador();
	 }
		
		public VistaBienvenida(int ancho, int alto){
			super("Ventana");
			setSize(ancho, alto);
			setVisible (true);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
		}
		
		public VistaBienvenida (String nombre) {
			super(nombre);
		    //setSize(400,300);
		    setVisible (true);
		    setDefaultCloseOperation(EXIT_ON_CLOSE);
		}
		
		public GPSChallenge getModelo(){
			return modelo;
		}
       
		public void setModelo(GPSChallenge unModelo){	
			this.modelo = unModelo;
		}
		
        public void saludar(){
			
			this.setAlwaysOnTop(false); //En false para poder minimizar la pantalla
	        this.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
	       
	        setLayout( new FlowLayout() );
	     
			JLabel etiquetaBienvenida= new JLabel("Bienvenido");
            Font fuente = new Font ( "GEORGIA", Font.ITALIC, 60 ) ;
			etiquetaBienvenida.setFont ( fuente ) ;
			
			fuente = new Font ( "TIMES NEW ROMAN", Font.BOLD, 32 ) ;
			JButton botonesOpciones[] = new JButton[2];
			JPanel panelInicio = new JPanel ( ) ;
			panelInicio.setLayout(new GridLayout(0, 1));
			
			botonesOpciones[ 0 ] = new JButton("Soy nuevo"); 
		    JButton boton1 = botonesOpciones[ 0 ];
		    boton1.setFont ( fuente ) ;
		    boton1.setForeground(Color.white);
		    boton1.setBackground(Color.blue);
		    boton1.addActionListener(control.obtenerListenerNuevoUsuario(this));
		    
		    botonesOpciones[ 1 ] = new JButton ("Tengo usuario");
		    JButton boton2 = botonesOpciones [ 1 ];
		    boton2.setFont ( fuente ) ;
		   
		    boton2.setForeground(Color.white);
		    boton2.setBackground(Color.blue);
		    
		    boton2.setEnabled(true); 
		    boton2.addActionListener(control.obtenerListenerUsuarioExistente(this, modelo));
		    
		    
		    panelInicio.add(etiquetaBienvenida); 
		    panelInicio.add( botonesOpciones[0 ] );
		    panelInicio.add( botonesOpciones[1 ] );
			getContentPane().add(panelInicio); 
			add(panelInicio);
			pack();
		}
       
	    public void usuarioNuevo(){ 
		       
	    	   setLayout( new FlowLayout() ); 
	    	   
			   JPanel panel = new JPanel ( ) ;
			   panel.setLayout(new GridLayout(0, 1));
	    	   
	    	   JLabel etiquetaNombre= new JLabel("Por favor elija un nombre");
	    	   Font fuente = new Font ( "ARIAL", Font.BOLD, 28 ) ;
			   etiquetaNombre.setFont ( fuente ) ;
	    	   
			   fuente = new Font ( "ARIAL", Font.PLAIN, 18 ) ;
	    	   JButton botonGuardar= new JButton("Guardar");
	    	   botonGuardar.setFont(fuente);

	    	   JTextField campoNombre = new JTextField (30) ;
	    	  
			   botonGuardar.addActionListener(control.obtenerListenerBotonGuardarNuevoUsuario(campoNombre, this, this.modelo));
			   
			   JButton botonAtras= new JButton("Atras");
			   botonAtras.setFont(fuente);
			   botonAtras.addActionListener(control.obtenerListenerBotonAtrasLoguearUsuario(this));
		
	    	   add (etiquetaNombre) ;
	    	   add ( campoNombre) ; 
	    	   add (botonGuardar);
	    	   add (botonAtras);
	   }
		
	    public void usuarioExistente(ArrayList<String> usuarios){
		   
		   setLayout( new FlowLayout() ); 
    	   JLabel etiquetaUsuario= new JLabel("USUARIOS ");
    	   Font fuente = new Font ( "IMPACT", Font.BOLD, 32 ) ;
		   etiquetaUsuario.setFont ( fuente ) ;
		   
		   ButtonGroup grupo = new ButtonGroup ( ) ;
		   JPanel panelDeJugadores= new JPanel ( ) ;
		   panelDeJugadores.setLayout (new GridLayout ( 0 , 1 ) ) ;
		   
		   fuente = new Font ( "ARIAL", Font.PLAIN, 22 ) ;
		   
		   for (int i=0 ; i< (usuarios.size()) ; i++){
		         JLabel nombreDelJugador = new JLabel ((i+1)+" : "+usuarios.get(i));
		         nombreDelJugador.setFont ( fuente ) ;
			     panelDeJugadores.add(nombreDelJugador) ;
		   }
		   
		   JLabel elegir= new JLabel("Elija el numero de su usuario");
		   elegir.setFont(fuente);
		   JTextField campoNroUsuario = new JTextField (10) ;
		   
		   JPanel panelBotones = new JPanel();
		   JButton grupoBotones[] = new JButton[ 2 ];
		  
		   grupoBotones[ 0 ] = new JButton ("Aceptar");
	       JButton botonAceptar = grupoBotones [ 0 ];
	       botonAceptar.addActionListener( control.obtenerListenerBotonUsuarioExistente(campoNroUsuario, this));
		   
		   grupoBotones[ 1 ] = new JButton ("Atras");
		   JButton botonAtras = grupoBotones [ 1 ];
		   botonAtras.addActionListener(control.obtenerListenerBotonAtrasLoguearUsuario(this));
		   
	       for(int i = 0; i < 2; i++){
                grupoBotones[ i ].setEnabled(true);
	        	grupoBotones[ i ].setFont(fuente);
	        	panelBotones.add(grupoBotones[ i ] );
	        }
	    
	       add (etiquetaUsuario);
    	   add( panelDeJugadores) ;
    	   add(elegir);
    	   add(campoNroUsuario);
    	   add (panelBotones);	   
		} 
		
	   
	    public void verMenu(String nombre){
		   
		   this.setAlwaysOnTop(false); //En false para poder minimizar la pantalla
	        this.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
	        
	        setLayout( new FlowLayout() );
			JLabel etiquetaBienvenida= new JLabel("Hola "+ nombre+ "!");
            Font fuente = new Font ( "IMPACT", Font.PLAIN, 36 ) ;
			etiquetaBienvenida.setFont ( fuente ) ;
			
			this.add(etiquetaBienvenida, BorderLayout.NORTH); 
			
			JButton botonesOpciones[] = new JButton[4];
			JPanel panelInicio = new JPanel ( ) ;
			panelInicio.setLayout(new GridLayout(0, 1));
			
			botonesOpciones[ 0 ] = new JButton("Comenzar Partida"); 
		    JButton botonComenzarPartida = botonesOpciones[ 0 ];
		    botonComenzarPartida.setEnabled(true); 
		    botonComenzarPartida.addActionListener(control.obtenerListenerComenzarPartida(this));
		    
		    botonesOpciones[ 1 ] = new JButton ("Retomar Partida Guardada");
		    JButton botonRetomarPartida = botonesOpciones [ 1 ];
		    botonRetomarPartida.setEnabled(true); 
		    botonRetomarPartida.addActionListener(control.obtenerListenerBotonCargar(modelo, control));

		    
		    botonesOpciones[ 2 ] = new JButton ("Ver puntajes");
		    JButton botonPuntajes = botonesOpciones [ 2 ];
		    botonPuntajes.setEnabled(true); 
		    botonPuntajes.addActionListener(control.obtenerListenerPuntajes(this));
		    
		    botonesOpciones[ 3 ] = new JButton ("Atras");
		    JButton botonAtras = botonesOpciones [ 3 ];
		    botonAtras.setEnabled(true); 
		    botonAtras.addActionListener(control.obtenerListenerBotonAtrasLoguearUsuario(this));
		    
		    panelInicio.add( botonesOpciones[0 ] );
		    panelInicio.add( botonesOpciones[1 ] );
		    panelInicio.add( botonesOpciones[2 ] );
		    panelInicio.add( botonesOpciones[3 ] );
			this.getContentPane().add(panelInicio, BorderLayout.CENTER); 
		}

	   
	    public void errorDeUsuario(){
	    	
	       setLayout( new FlowLayout() ); 
		   JLabel error= new JLabel("El numero de usuario elegido no existe");
		   JLabel etiquetaIngresar= new JLabel("Porfavor ingrese otro numero de usuario");
		   
		   add(error);
		   add(etiquetaIngresar);
	   }
	   
	    public void ganaste(String nombre){
	    	
	    	setLayout( new FlowLayout() ); 
	    	
	    	String cantMov = String.valueOf(modelo.jugador().movimientos());
	    	String cantPuntos= String.valueOf( modelo.jugador().puntaje() );
	    	
	    	Font fuente = new Font ( "IMPACT", Font.PLAIN, 36 ) ;
	    	JLabel etiquetaNombre= new JLabel (nombre);
	    	etiquetaNombre.setFont(fuente);
	    	
	    	JLabel ganador= new JLabel (" G A N A S T E !!!");
	    	ganador.setFont(fuente);
	    	ganador.setForeground(Color.BLUE);
	   	
	    	fuente = new Font ( "Arial", Font.PLAIN, 22 ) ;
	    	JLabel movimientos= new JLabel ("Te sobraron "+cantMov+" movimientos");
	    	movimientos.setFont(fuente);
	    	
	    	fuente = new Font ( "Arial", Font.ITALIC, 24 ) ;
	    	 
	    	JLabel puntos= new JLabel ("Obtuviste un puntaje de "+cantPuntos+ " puntos");
	    	puntos.setFont(fuente);
	    	puntos.setForeground(Color.blue);
	    	
	    	JButton jugarOtraVez= new JButton("Jugar otra vez");
	    	jugarOtraVez.setFont(fuente);
	    	jugarOtraVez.addActionListener(control.obtenerListenerBotonJugarOtraVez(this));
	    	
	    	add(etiquetaNombre);
	    	add(ganador);
	    	add(movimientos);
	    	add(puntos);
	    	add(jugarOtraVez);
	    }
	    
	    public void perdiste(String nombre){
	    	
	    	setLayout( new FlowLayout() ); 
	    			
	    	Font fuente = new Font ( "IMPACT", Font.PLAIN, 36 ) ;
	    	JLabel etiquetaNombre= new JLabel (nombre);
	    	etiquetaNombre.setFont(fuente);
	    	
	    	JLabel perdedor= new JLabel (" P E R D I S T E ");
	    	perdedor.setFont(fuente);
	    	perdedor.setForeground(Color.RED);
	    	
	    	
	    	JButton jugarOtraVez= new JButton("Jugar otra vez");
	    	jugarOtraVez.setFont(fuente);
	    	jugarOtraVez.addActionListener(control.obtenerListenerBotonJugarOtraVez(this));
	    	
	    	add(etiquetaNombre);
	    	add(perdedor);
	    	add(jugarOtraVez);
	    }
	    
	    private class ManejadorBoton implements ActionListener {
			// manejar evento del boton
			public void actionPerformed( ActionEvent event ) {
				JOptionPane.showMessageDialog(VistaBienvenida.this, String.format("Ha presionado: %s", event.getActionCommand() ) );
			 }
		}	   
		   
	   }

		

