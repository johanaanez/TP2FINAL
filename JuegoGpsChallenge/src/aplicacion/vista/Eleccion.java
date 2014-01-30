package aplicacion.vista;

import javax.swing.JFrame;

import java.awt.Color;

import javax.swing.JSplitPane;

import java.awt.BorderLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import java.awt.GridBagLayout;

import javax.swing.JRadioButton;

import aplicacion.controlador.Controlador;
import aplicacion.modelo.juego.GPSChallenge;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class Eleccion extends JFrame {
	
	private final static Controlador control = new Controlador();
	private ButtonGroup listaVehiculos = new ButtonGroup();
	private ButtonGroup listaDificultades = new ButtonGroup();
	private JButton botonesOpciones[] = new JButton[ 2 ];
	private JPanel panelBotones = new JPanel();
	private GPSChallenge modelo;
	
	public GPSChallenge getModelo(){
		return modelo;
	}

	
	public Eleccion(GPSChallenge unModelo){
		super("GPS Challenge");
		setResizable(false); 
		this.modelo = unModelo;
		this.control.cargarModelo(unModelo);
		
		getContentPane().setBackground(Color.BLACK);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[] {0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
			
		JRadioButton rdbtnAuto = new JRadioButton("Auto");
		rdbtnAuto.setActionCommand("Auto");
		rdbtnAuto.setSelected(true);
		rdbtnAuto.setForeground(Color.WHITE);
		rdbtnAuto.setBackground(Color.BLACK);
		GridBagConstraints gbc_rdbtnAuto = new GridBagConstraints();
		gbc_rdbtnAuto.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnAuto.gridx = 1;
		gbc_rdbtnAuto.gridy = 1;
		getContentPane().add(rdbtnAuto, gbc_rdbtnAuto);
		
		JRadioButton rdbtnMoto = new JRadioButton("Moto");
		rdbtnMoto.setActionCommand("Moto");
		rdbtnMoto.setBackground(Color.BLACK);
		rdbtnMoto.setForeground(Color.WHITE);
		GridBagConstraints gbc_rdbtnMoto = new GridBagConstraints();
		gbc_rdbtnMoto.insets = new Insets(0, 0, 0, 5);
		gbc_rdbtnMoto.gridx = 1;
		gbc_rdbtnMoto.gridy = 2;
		getContentPane().add(rdbtnMoto, gbc_rdbtnMoto);
		
		JRadioButton rdbtnCuatroXCuatro = new JRadioButton("4 X 4");
		rdbtnCuatroXCuatro.setForeground(Color.WHITE);
		rdbtnCuatroXCuatro.setBackground(Color.BLACK);
		GridBagConstraints gbc_rdbtnCuatroXCuatro = new GridBagConstraints();
		gbc_rdbtnCuatroXCuatro.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnCuatroXCuatro.gridx = 1;
		gbc_rdbtnCuatroXCuatro.gridy = 3;
		getContentPane().add(rdbtnCuatroXCuatro, gbc_rdbtnCuatroXCuatro);
		
		JRadioButton rdbtnFacil = new JRadioButton("Facil");
		rdbtnFacil.setActionCommand("Facil");
		rdbtnFacil.setSelected(true);
		rdbtnFacil.setForeground(Color.WHITE);
		rdbtnFacil.setBackground(Color.BLACK);
		GridBagConstraints gbc_rdbtnFacil = new GridBagConstraints();
		gbc_rdbtnFacil.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnFacil.gridx = 3;
		gbc_rdbtnFacil.gridy = 1;
		getContentPane().add(rdbtnFacil, gbc_rdbtnFacil);
		
		JRadioButton rdbtnMedio = new JRadioButton("Medio");
		rdbtnMedio.setActionCommand("Medio");
		rdbtnMedio.setForeground(Color.WHITE);
		rdbtnMedio.setBackground(Color.BLACK);
		GridBagConstraints gbc_rdbtnMedio = new GridBagConstraints();
		gbc_rdbtnMedio.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnMedio.gridx = 3;
		gbc_rdbtnMedio.gridy = 2;
		getContentPane().add(rdbtnMedio, gbc_rdbtnMedio);
		
		JRadioButton rdbtnDificil = new JRadioButton("Dificil");
		rdbtnDificil.setForeground(Color.WHITE);
		rdbtnDificil.setBackground(Color.BLACK);
		GridBagConstraints gbc_rdbtnDificil = new GridBagConstraints();
		gbc_rdbtnDificil.insets = new Insets(0, 0, 0, 5);
		gbc_rdbtnDificil.gridx = 3;
		gbc_rdbtnDificil.gridy = 3;
		getContentPane().add(rdbtnDificil, gbc_rdbtnDificil);
		
		listaVehiculos.add(rdbtnAuto);
		listaVehiculos.add(rdbtnMoto);
		listaVehiculos.add(rdbtnCuatroXCuatro);
		
		listaDificultades.add(rdbtnFacil);
		listaDificultades.add(rdbtnMedio);
		listaDificultades.add(rdbtnDificil);
		
		Font fuente = new Font ( "ARIAL", Font.PLAIN, 18 ) ;
		
		botonesOpciones[ 0 ] = new JButton ("Aceptar");
        JButton botonAceptar = botonesOpciones [ 0 ];
        
		botonesOpciones[1 ] = new JButton ("Atras");
        JButton botonAtras = botonesOpciones [ 1 ];
        
        //Personaliza los botones y los agrega al panel
        for(int i = 0; i < 2; i++){
        	botonesOpciones[ i ].setEnabled(true);
        	botonesOpciones[ i ].setFont(fuente);
        	panelBotones.add( botonesOpciones[ i ] );
        }
		
		botonAceptar.addActionListener(control.obtenerListenerBotonAceptar(this));
		botonAtras.addActionListener(control.obtenerListenerBotonAtrasEleccion(this));
		
		fuente = new Font ( "ARIAL", Font. BOLD, 28 ) ;
		JLabel opciones= new JLabel (" OPCIONES ");
		opciones.setFont(fuente);
		opciones.setForeground(Color.WHITE);
		
		add(opciones);
		add(panelBotones);
		
		setVisible(true);
		pack();
		
	}

	public void cerrarVentana(){
		setVisible(false);
		dispose();
	}
	public String getSeleccionDificultad() {
		return listaDificultades.getSelection().getActionCommand();
	}
	
	public String getSeleccionVehiculo() {
		return listaVehiculos.getSelection().getActionCommand();
	}
}
