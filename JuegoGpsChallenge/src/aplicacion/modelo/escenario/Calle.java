package aplicacion.modelo.escenario;

import java.util.Random; //Ver para que esta este import, si no sirve, sacarlo

import aplicacion.modelo.encontrables.Encontrable;
import aplicacion.modelo.escenario.Esquina;
import aplicacion.modelo.vehiculos.Vehiculo;

public class Calle {
	
	private Esquina esquina1;
	private Esquina esquina2;
	private	Encontrable objetoEncontrable;
	
	public Calle(){
		esquina1 = null;
		esquina2 = null;
		objetoEncontrable = null;
	}
	
	/* Setea las esquinas de la calle
	 */
	public void setEsquinas(Esquina esquina1, Esquina esquina2){
		this.esquina1 = esquina1;
		this.esquina2 = esquina2;
	}
	
	
	/* Devuelve la esquina opuesta a donde esta parado el vehiculo
	 */
	public Esquina proximaEsquina(Vehiculo vehiculo){
		if (vehiculo.posicion() == esquina1){
			return esquina2;
		}
		else{
			return esquina1;
		}
	}	
	
	/* Agrega el encontrable a la calle
	 */
	public void agregarEncontrable(Encontrable objetoEncontrable){
		this.objetoEncontrable = objetoEncontrable;
	}

	/* Devuelve el encontrable de la calle
	 */
	public Encontrable getEncontrable(){		
		return this.objetoEncontrable;
	}
	
	/* Chequea que haya algun tipo de encontrable en la calle
	 */
	public boolean tieneEncontrable(){
		return (objetoEncontrable != null);
	}

	public Esquina getEsquina1() {
		return esquina1;
	}

	public Esquina getEsquina2() {
		return esquina2;
	}

	

}
