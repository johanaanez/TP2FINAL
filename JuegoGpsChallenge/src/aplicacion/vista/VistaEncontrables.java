package aplicacion.vista;

import javax.swing.ImageIcon;

import aplicacion.modelo.encontrables.*;

public class VistaEncontrables {
	
	private ImageIcon dibujoPozo;
	private ImageIcon dibujoPiquete;
	private ImageIcon dibujoControlPolicial;
	private ImageIcon dibujoSorpresa;
	
	public VistaEncontrables(){
		dibujoPozo = new ImageIcon("estaticos/encontrables/pozo.png");	
		dibujoPiquete = new ImageIcon("estaticos/encontrables/piquete.png");	
	    dibujoControlPolicial = new ImageIcon("estaticos/encontrables/control policial.png");
	    dibujoSorpresa = new ImageIcon("estaticos/encontrables/sorpresa.png");
	}
	
	public ImageIcon dibujoEncontrable(Encontrable encontrable) {
		if (encontrable.getClass() == Pozo.class){
			return dibujoPozo;
		}else{
			if (encontrable.getClass() == Piquete.class){
				return dibujoPiquete;
			}else{
				if (encontrable.getClass() == ControlPolicial.class){
					return dibujoControlPolicial;
				}else{
					return dibujoSorpresa;
				}
			}
		}
	}

}
