package aplicacion.modelo.encontrables;

public class SorpresaPorcentual extends Sorpresa {
	
	protected int porcentaje;
	
	public SorpresaPorcentual(int porcentaje){
		this.porcentaje = porcentaje;
	}
	
	public int porcentaje(){
		return porcentaje;
	}

}
