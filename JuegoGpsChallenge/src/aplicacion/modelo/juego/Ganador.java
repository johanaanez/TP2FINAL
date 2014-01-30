package aplicacion.modelo.juego;

public class Ganador {
	
	private String nombre;
	private int puntaje;
	
	public Ganador (){
		
		this.nombre = null;
		this.puntaje = -1;
	}
	
	public void setNombre(String unNombre){
		
		this.nombre = unNombre;
	}
	 public void setPuntaje(int cantMovimientos){
		 
		 this.puntaje = cantMovimientos;
	 }
	 
	 public String getNombre(){
		 
		 return this.nombre;
	 }

	 public int getPuntaje(){
		 
		 return this.puntaje;
	 }

}
