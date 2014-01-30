package aplicacion.vista;
import aplicacion.controlador.Controlador;
import aplicacion.modelo.juego.GPSChallenge;
import aplicacion.modelo.juego.Ganador;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.border.Border;

public class Puntaje extends JFrame {

	/* 
	 * 
	 */
	
	private JTextArea areaDePuntajes;
	private Controlador control;
	private GPSChallenge modelo;
	
	public Puntaje(GPSChallenge unModelo){
		super("Puntajes");
		control= new Controlador();
		modelo=unModelo;
		setLayout( new FlowLayout() ); 
		
		
		Container contenedor = getContentPane();
		contenedor.setLayout(new GridLayout(0, 1));
	    Image icono = Toolkit.getDefaultToolkit().getImage("estaticos/icono.png");
	    setIconImage(icono);
		String puntajes = "";
		
		//final ImageIcon imageIcon = new ImageIcon("estaticos/Fondos/fondoInstrucciones.jpg");
	    areaDePuntajes = new JTextArea(){
	    private static final long serialVersionUID = 1L;
			//Image image = imageIcon.getImage();
	           {
	             setOpaque(false);
	           }

	        public void paint(Graphics g) {
	             //g.drawImage(image, 0, 0, this);
	             super.paint(g);
	           }
	         };
	    //this.setResizable(false);
	    areaDePuntajes.setText(puntajes);
	    areaDePuntajes.setForeground(Color.WHITE);
	    areaDePuntajes.setEditable(false);
	    areaDePuntajes.setLineWrap(true);
	    areaDePuntajes.setWrapStyleWord(true);
	    
	    Font fuente = new Font ( "ARIAL", Font.PLAIN, 24 ) ;
	  
	    
	    /*int cantPuntajes=10;
	    if (ganadores.size() <= cantPuntajes){
	    	cantPuntajes= ganadores.size();
	    }*/
	    
	    
	    
		for (int i=0 ; i< (modelo.ganadores().size()) ; i++){
		    JLabel nombreDelJugador = new JLabel (modelo.ganadores().get(i).getNombre()+"      "+modelo.ganadores().get(i).getPuntaje());
		    nombreDelJugador.setFont ( fuente ) ;
			contenedor.add(nombreDelJugador) ;
		   }

	    //contenedor.add(areaDePuntajes);
	    
    	JButton atras= new JButton("Atras");
    	atras.setFont(fuente);//515
    	atras.addActionListener(control.obtenerListenerBotonAtrasPuntaje(this));
    
    	add(atras);
    	
	    setSize( 250, 700 );
	    setVisible( true );
	    Border border = BorderFactory.createLineBorder(Color.BLACK);
	    areaDePuntajes.setBorder(BorderFactory.createCompoundBorder(border, 
	               BorderFactory.createEmptyBorder(20, 20, 20, 20)));
	}
	
	public GPSChallenge getModelo(){
		return modelo;
	}
	


}
