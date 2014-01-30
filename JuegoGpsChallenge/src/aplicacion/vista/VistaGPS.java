package aplicacion.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import aplicacion.controlador.Controlador;
import aplicacion.modelo.juego.GPSChallenge;

import javax.swing.JLabel;

import java.awt.Canvas;
import java.awt.Panel;

public class VistaGPS implements Observer, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private GPSChallenge modelo;
	private Boolean juegoIniciado = false;
	private JFrame VentanaAnterior;
    
	public JFrame frameGPS = new JFrame("GPS Challenge"); //Frame del juego
	
	private JPanel panelBotones = new JPanel(); //Panel de botones de  opciones
	private JButton botonesOpciones[] = new JButton[ 2 ]; //Botones de iniciar, guardar, cargar y salir
    
	private JPanel panelMovimientos = new JPanel(); //Creamos el panel que contiene los movimientos y el vehiculo
	private Label labelMovLimites = new Label("# Movimientos Limites:"); //Etiqueta de movimientos limites
	private JTextField textoMovLimites = new JTextField(); //Texto que mostrara los movimientos limites
    private Label labelMovHechos = new Label("# Movimientos Hechos:"); //Etiqueta de movimientos hechos
	private JTextField textoMovHechos = new JTextField(); //Texto que mostrara los movimientos hechos
    private Label labelVehiculo = new Label("Vehiculo:"); //Etiqueta del vehiculo
	private JTextField textoVehiculo = new JTextField(); //Texto que mostrara el tipo de vehiculo
    
	private VistaEscenario panelEscenario; //Creamos el panel que contiene el escenario
	
    private JPanel panelPuntaje = new JPanel(); //Creamos el panel que contiene el puntaje
    private JTextField textoPuntos = new JTextField(); //Texto que mostrara el puntaje
    private Label labelPuntos = new Label("Puntos:"); //Etiqueta de puntos
    
    private final JButton btnArriba = new JButton("Arriba");
    private final JButton btnDerecha = new JButton("Derecha");
    private final JButton btnIzquierda = new JButton("Izquierda");
    private final JButton btnAbajo = new JButton("Abajo");
    
    
    //Constructor de la vista
	public VistaGPS(GPSChallenge modelo, Controlador control){

		frameGPS.setVisible(true);
		frameGPS.setAlwaysOnTop(false); //En false para poder minimizar la pantalla
        frameGPS.setResizable(false);
        frameGPS.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
        frameGPS.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameGPS.getContentPane().setLayout(new BoxLayout(frameGPS.getContentPane(), BoxLayout.X_AXIS));
          
        panelBotones.setLayout(new GridLayout(0, 1));
        	  	  
        botonesOpciones[ 0 ] = new JButton ("Salir del Juego");
        JButton botonSalir = botonesOpciones [ 0 ];
        
        /*botonesOpciones[ 1 ] = new JButton ("Atras");
        JButton botonAtras = botonesOpciones [ 1 ];
        botonAtras.addActionListener(control.obtenerListenerBotonAtrasEscenario(this));*/
        
        botonesOpciones[ 1 ] = new JButton ("Guardar Juego");
        JButton botonGuardar = botonesOpciones [ 1 ];
        botonGuardar.setEnabled(true); 
        botonGuardar.addActionListener(control.obtenerListenerBotonGuardar());
        
        //Personaliza los botones y los agrega al panel
        for(int i = 0; i < 2; i++){
        	botonesOpciones[ i ].setBackground(Color.BLACK);
        	botonesOpciones[ i ].setForeground(Color.WHITE);
        	panelBotones.add( botonesOpciones[ i ] );
        }

        botonSalir.addActionListener(new ActionListener ()
        {
        	   public void actionPerformed (ActionEvent e)
        	   {
        		   if (e.getSource()== botonesOpciones [ 0 ]) {
        	            System.exit(0);
        	   } 
        	}
        });

        frameGPS.getContentPane().add(panelBotones);
      
        panelMovimientos.setBackground(Color.BLACK);
        labelVehiculo.setForeground(Color.WHITE);
		
        frameGPS.getContentPane().add(panelMovimientos); 
        //se setean los tamanyos de esta nueva columna
        GridBagLayout gbl_panelMovimientos = new GridBagLayout();
        gbl_panelMovimientos.columnWidths = new int[]{0, 62, 80, 0};
        gbl_panelMovimientos.rowHeights = new int[]{22, 0, 0, 0};
        gbl_panelMovimientos.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
        gbl_panelMovimientos.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
        // se "aplican" los cambios de la coluna (con la 1ra linea ya se dibuja,
        // pero no se aplican los tamanyos asignados)
        panelMovimientos.setLayout(gbl_panelMovimientos);
        
        
        // Escribe "Movimientos Limites"
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.anchor = GridBagConstraints.NORTHWEST;
        gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel.gridx = 1;
        gbc_lblNewLabel.gridy = 1;
        labelMovLimites.setForeground(Color.WHITE);
        panelMovimientos.add(labelMovLimites, gbc_lblNewLabel);
        
        // Crea y dibuja caja con los movimientos limites        
        GridBagConstraints gbc_textField = new GridBagConstraints();
        gbc_textField.fill = GridBagConstraints.BOTH;
        gbc_textField.gridx = 2;
        gbc_textField.gridy = 1;
        textoMovLimites.setEditable(false);
        textoMovLimites.setBackground(Color.BLACK);
        textoMovLimites.setForeground(Color.WHITE);
        panelMovimientos.add(textoMovLimites, gbc_textField);
        
        // Escribe "Movimientos Hechos"
        GridBagConstraints gbc_lblNewLabel1 = new GridBagConstraints();
        gbc_lblNewLabel1.anchor = GridBagConstraints.NORTHWEST;
        gbc_lblNewLabel1.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel1.gridx = 1;
        gbc_lblNewLabel1.gridy = 2;
        labelMovHechos.setForeground(Color.WHITE);
        panelMovimientos.add(labelMovHechos, gbc_lblNewLabel1);
        
        // Crea y dibuja caja con los movimientos hechos        
        GridBagConstraints gbc_textField1 = new GridBagConstraints();
        gbc_textField1.fill = GridBagConstraints.BOTH;
        gbc_textField1.gridx = 2;
        gbc_textField1.gridy = 2;
        textoMovHechos.setEditable(false);
        textoMovHechos.setBackground(Color.BLACK);
        textoMovHechos.setForeground(Color.WHITE);
        panelMovimientos.add(textoMovHechos, gbc_textField1);
        
        // Escribe "Vehiculo"
        GridBagConstraints gbc_labelVehiculo = new GridBagConstraints();
        gbc_labelVehiculo.anchor = GridBagConstraints.NORTHWEST;
        gbc_labelVehiculo.insets = new Insets(0, 0, 0, 5);
        gbc_labelVehiculo.gridx = 1;
        gbc_labelVehiculo.gridy = 4;
        panelMovimientos.add(labelVehiculo, gbc_labelVehiculo);
        
        // Crea y dibuja caja con el tipo de vehiculo
        GridBagConstraints gbc_textoVehiculo = new GridBagConstraints();
        gbc_textoVehiculo.fill = GridBagConstraints.BOTH;
        gbc_textoVehiculo.gridx = 2;
        gbc_textoVehiculo.gridy = 4;
        textoVehiculo.setEditable(false);
        textoVehiculo.setBackground(Color.BLACK);
        textoVehiculo.setForeground(Color.WHITE);
        panelMovimientos.add(textoVehiculo, gbc_textoVehiculo);
        
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        
        panelEscenario = new VistaEscenario(modelo);

        //frameGPS.getContentPane().add(panelEscenario);
        GridBagLayout gbl_panelEscenario = new GridBagLayout();
        gbl_panelEscenario.columnWidths = new int[] {panelEscenario.ancho()};
        gbl_panelEscenario.rowHeights = new int[] {panelEscenario.alto()};
        gbl_panelEscenario.columnWeights = new double[]{Double.MIN_VALUE};
        gbl_panelEscenario.rowWeights = new double[]{Double.MIN_VALUE};
        panelEscenario.setLayout(gbl_panelEscenario);
        
        frameGPS.getContentPane().add(panelEscenario);

        //Obtnego el listener del teclado
        
        panelPuntaje.setBackground(Color.BLACK);
        frameGPS.getContentPane().add(panelPuntaje);
        
        GridBagLayout gbl_panelPuntaje = new GridBagLayout();
        gbl_panelPuntaje.columnWidths = new int[]{0, 62, 80, 0};
        gbl_panelPuntaje.rowHeights = new int[]{22, 0, 0, 0, 0};
        gbl_panelPuntaje.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
        gbl_panelPuntaje.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        panelPuntaje.setLayout(gbl_panelPuntaje);
 
        //Escribe "Puntos"        
        GridBagConstraints gbc_labelPuntos = new GridBagConstraints();
        gbc_labelPuntos.anchor = GridBagConstraints.NORTHWEST;
        gbc_labelPuntos.insets = new Insets(0, 0, 5, 5);
        gbc_labelPuntos.gridx = 1;
        gbc_labelPuntos.gridy = 1;
        labelPuntos.setForeground(Color.WHITE);
        panelPuntaje.add(labelPuntos, gbc_labelPuntos);
        
        //Crea y dibuja caja con los puntos
        GridBagConstraints gbc_textoPuntos = new GridBagConstraints();
        gbc_textoPuntos.insets = new Insets(0, 0, 5, 0);
        gbc_textoPuntos.fill = GridBagConstraints.BOTH;
        gbc_textoPuntos.gridx = 2;
        gbc_textoPuntos.gridy = 1;
        textoPuntos.setEditable(false);
        textoPuntos.setBackground(Color.BLACK);
        textoPuntos.setForeground(Color.WHITE);
        panelPuntaje.add(textoPuntos, gbc_textoPuntos);
        
        //Botones de movimiento
        GridBagConstraints gbc_btnArriba = new GridBagConstraints();
        gbc_btnArriba.insets = new Insets(0, 0, 5, 5);
        gbc_btnArriba.gridx = 1;
        gbc_btnArriba.gridy = 5;
        btnArriba.setBackground(Color.BLACK);
    	btnArriba.setForeground(Color.WHITE);
    	btnArriba.addActionListener(control.obtenerListenerBotonArriba());
        panelPuntaje.add(btnArriba, gbc_btnArriba);
        
        GridBagConstraints gbc_btnIzquierda = new GridBagConstraints();
        gbc_btnIzquierda.insets = new Insets(0, 0, 0, 5);
        gbc_btnIzquierda.gridx = 0;
        gbc_btnIzquierda.gridy = 6;
        btnIzquierda.setBackground(Color.BLACK);
    	btnIzquierda.setForeground(Color.WHITE);
    	btnIzquierda.addActionListener(control.obtenerListenerBotonIzquierda());
        panelPuntaje.add(btnIzquierda, gbc_btnIzquierda);
        
        GridBagConstraints gbc_btnAbajo = new GridBagConstraints();
        gbc_btnAbajo.insets = new Insets(0, 0, 0, 5);
        gbc_btnAbajo.gridx = 1;
        gbc_btnAbajo.gridy = 6;
        btnAbajo.setBackground(Color.BLACK);
    	btnAbajo.setForeground(Color.WHITE);
    	btnAbajo.addActionListener(control.obtenerListenerBotonAbajo());
        panelPuntaje.add(btnAbajo, gbc_btnAbajo);
        
        GridBagConstraints gbc_btnDerecha = new GridBagConstraints();
        gbc_btnDerecha.gridx = 2;
        gbc_btnDerecha.gridy = 6;
        btnDerecha.setBackground(Color.BLACK);
    	btnDerecha.setForeground(Color.WHITE);
    	btnDerecha.addActionListener(control.obtenerListenerBotonDerecha());
        panelPuntaje.add(btnDerecha, gbc_btnDerecha);
        
        // Asigna el tamanyo correcto a la ventana para que entre todo
        frameGPS.pack(); 
        
		// Conectamos esta vista con el modelo
		this.modelo = modelo;
		this.modelo.addObserver(this); 
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if (modelo.juegoGanado()){
			System.out.println("TERMINADO");
			cerrarVentana();
			VistaBienvenida unaVentana= new VistaBienvenida ("GANASTE", 500, 300, modelo);
			unaVentana.ganaste(modelo.jugador().nombre());
		}
		if (modelo.juegoPerdido()){
			System.out.println("PERDISTE");
			cerrarVentana();
			VistaBienvenida unaVentana= new VistaBienvenida ("PERDISTE", 500, 300, modelo);
			unaVentana.perdiste(modelo.jugador().nombre());
		}
		actualizarLabels();
		panelEscenario.repaint();
		
	}
	
	public void actualizarEscenario(GPSChallenge modelo2) {
		panelEscenario = new VistaEscenario(modelo2);
    }

	public GPSChallenge getModelo(){
		return modelo;
	}
	
	private void actualizarLabels(){
		int movLimites = this.modelo.movimientosLimites();
		int movHechos = this.modelo.movimientosHechos();
		String nombreVehiculo = this.modelo.nombreDelVehiculo();
		int puntaje = this.modelo.puntosDelJugador();
		
		textoMovLimites.setText(Integer.toString(movLimites));
		textoMovHechos.setText(Integer.toString(movHechos));
		textoVehiculo.setText(nombreVehiculo);
		textoPuntos.setText(Integer.toString(puntaje));
	}
	
	public void cambiarModelo(GPSChallenge modelo){
		this.modelo.deleteObservers();		
		this.modelo = modelo;
		this.modelo.deleteObservers();
		this.modelo.addObserver(this);
	}

	public void habilitarJuego() {
		juegoIniciado = true;
		this.actualizarLabels();
	}
	
	public void cerrarVentana(){
		frameGPS.setVisible(false);
		frameGPS.dispose();
	}

}
