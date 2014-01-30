package aplicacion.persistencia;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import aplicacion.modelo.juego.GPSChallenge;
import aplicacion.modelo.juego.Ganador;
import aplicacion.modelo.vehiculos.TipoDeVehiculo;

public class PersistidorUsuarios {
	
	private ArrayList<String> usuarios;
	private ArrayList<Ganador> ganadores;
	
	public void persistirUsuarios(GPSChallenge modelo){
		
		try{
			FileOutputStream fileOutputStream1 = new FileOutputStream("Usuarios.xtml");
			XMLEncoder  serializadorUsuarios = new XMLEncoder(fileOutputStream1);
			//serializo la instancia con lso datos importantes del juego
			serializadorUsuarios.writeObject(modelo.usuarios()); 
			serializadorUsuarios.close();
		}
		catch (IOException   error) {
			System.out.println("no se pudo guardar la lista de usuarios");
		}
		
		try{
			FileOutputStream fileOutputStream1 = new FileOutputStream("RankingGanadores.xtml");
			XMLEncoder  serializadorRankingGanadores = new XMLEncoder(fileOutputStream1);
			//serializo la instancia con lso datos importantes del juego
			serializadorRankingGanadores.writeObject(modelo.ganadores()); 
			serializadorRankingGanadores.close();
		}catch (IOException   error) {
			System.out.println("no se pudo guardar la lista de ganadores");
		}
	}
		
	public void persistirSoloPuntajes(ArrayList<Ganador> ganadores){
			
			try{
				FileOutputStream fileOutputStream1 = new FileOutputStream("RankingGanadores.xtml");
				XMLEncoder  serializadorRankingGanadores = new XMLEncoder(fileOutputStream1);
				//serializo la instancia con lso datos importantes del juego
				serializadorRankingGanadores.writeObject(ganadores); 
				serializadorRankingGanadores.close();
			}catch (IOException   error) {
				System.out.println("no se pudo guardar la lista de ganadores");
			}
		}
	
	public void persistirSoloUsuarios(ArrayList<String> usuarios){
		
		try{
			FileOutputStream fileOutputStream1 = new FileOutputStream("Usuarios.xtml");
			XMLEncoder  serializadorUsuarios = new XMLEncoder(fileOutputStream1);
			//serializo la instancia con lso datos importantes del juego
			serializadorUsuarios.writeObject(usuarios); 
			serializadorUsuarios.close();
		}
		catch (IOException   error) {
			System.out.println("no se pudo guardar la lista de usuarios");
		}
	}
	
	
	public ArrayList<Ganador> recuperarSoloPuntajes() throws FileNotFoundException {
		
		FileInputStream fileInputStream3 = new FileInputStream("RankingGanadores.xtml");
		XMLDecoder  deserializadorRankingGanadores = new XMLDecoder(fileInputStream3);
		ArrayList<Ganador> rankingGanadores = (ArrayList)deserializadorRankingGanadores.readObject(); 
		deserializadorRankingGanadores.close();
		
		return rankingGanadores;
	}
	
	public ArrayList<String> recuperarSoloUsuarios() throws FileNotFoundException {
		
		FileInputStream fileInputStream3 = new FileInputStream("Usuarios.xtml");
		XMLDecoder  deserializadorUsuarios = new XMLDecoder(fileInputStream3);
		ArrayList<String> usuarios = (ArrayList)deserializadorUsuarios.readObject(); 
		deserializadorUsuarios.close();
		
		return usuarios;
	}
	

	public void setUsuarios(ArrayList<String> listaUsuarios) {
 
		usuarios = listaUsuarios;
	}
	
	public void setGanadores(ArrayList<Ganador> listaGanadores){
		
		ganadores = listaGanadores;
	}


	public ArrayList<String> getUsuarios() {
 
		return usuarios;
	}

	public ArrayList<Ganador> getGanadores(){
		
		return ganadores;
	}
	
	
}
