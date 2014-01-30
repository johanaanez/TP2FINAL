package aplicacion.modelo.encontrables;

public class Obstaculo extends Encontrable {
	
	protected int penalizacion;
	
	public Obstaculo(int penalizacion){
		this.penalizacion = penalizacion;
	}
	
	public int penalizacion(){
		return penalizacion;
	}
	
}
