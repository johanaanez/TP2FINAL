package aplicacion.modelo.escenario;

import java.util.ArrayList;

import aplicacion.modelo.escenario.Calle;
import aplicacion.modelo.escenario.Esquina;
import aplicacion.modelo.juego.dificultad.*;
import aplicacion.modelo.encontrables.*;


public class Escenario {
	
	private Esquina[] esquinas;
	private ArrayList<Calle> callesConEncontrables;
	private Esquina largada;
	private Esquina llegada;
	private int anchoDeMapa;
	private int cantidadDeEsquinas;
	
	public Escenario(int ancho){
		
		this.cantidadDeEsquinas = ancho*ancho;
		this.anchoDeMapa = ancho;
		Calle[][] calles = new Calle[cantidadDeEsquinas][cantidadDeEsquinas];
		
		for (int x=0 ; x < (cantidadDeEsquinas) ; x++ ){
		
			for (int y=0 ; y < (cantidadDeEsquinas) ; y++){
				
				calles[x][y] = null;
			}
		}
			
			this.esquinas = new Esquina[cantidadDeEsquinas];
		
			for (int x=0 ; x < (cantidadDeEsquinas) ; x++){
				
				this.esquinas[x] = null;
			}
			
		llenarDeEsquinas();
		
		llenarDeCalles(calles);
		
		unirEsquinasCon4Calles(calles);
		
		unirCallesCon2Esquinas(calles);
		
		asignarLargada();
		
		asignarLlegada();
		
	}
	
	



	public Escenario(int ancho, int posLargada, int posLlegada){
		
		this.cantidadDeEsquinas = ancho*ancho;
		this.anchoDeMapa = ancho;
		
		Calle[][] calles = new Calle[cantidadDeEsquinas][cantidadDeEsquinas];
		
		for (int x=0 ; x < (cantidadDeEsquinas) ; x++ ){
		
			for (int y=0 ; y < (cantidadDeEsquinas) ; y++){
				
				calles[x][y] = null;
			}
		}
			
			this.esquinas = new Esquina[cantidadDeEsquinas];
		
			for (int x=0 ; x < (cantidadDeEsquinas) ; x++){
				
				this.esquinas[x] = null;
			}
			
		llenarDeEsquinas();
		
		llenarDeCalles(calles);
		
		unirEsquinasCon4Calles(calles);
		
		unirCallesCon2Esquinas(calles);

		this.largada = esquinas[posLargada];
		
		this.llegada = esquinas[posLlegada];
		
	}
	
	public Esquina getEsquina(int posEsquina){
		
		//devuelve la esquina que esté en la posicion pasada por parámetro
		//recordar que el verctor inicia en la posicion 0
	
		return this.esquinas[posEsquina];
	}

	public int getAnchoDeMapa(){
		return this.anchoDeMapa;
	}
	
	public Esquina largada(){
		return largada;
	}
	
	public Esquina llegada() {
		return llegada;
	}
	
	public	void llenarDeEsquinas(){
		
		//lleno el vector de esquinas con objetos Esquinas
	
		for (int x=0 ; x < (cantidadDeEsquinas) ; x++ ){
		
			Esquina unaEsquina = new Esquina();
			unaEsquina.setNumeroDeEsquina(x); 	
			this.esquinas[x] = unaEsquina;
		}
	}
	


	public void llenarDeCalles(Calle[][] calles){
		
		//lleno una matriz de calles con objetos Calle
		
		for (int y=0 ; y < (cantidadDeEsquinas) ; y++){
			
			this.cargarCalle(calles,y,y+1);
		}
			
		for (int y=0 ; y < (cantidadDeEsquinas) ; y++){
				
			this.cargarCalle(calles,y,y + anchoDeMapa);		
		}
	}
	


	public void unirEsquinasCon4Calles(Calle[][] calles){
		
		//uno cada objeto Esquina del pama con sus respectivas calles de abajo, arriba, derecha e izquierda, segun corresponda
	
		for (int posEnVector=0; posEnVector < (cantidadDeEsquinas); posEnVector++){
		
			boolean resultado = false;
			resultado = this.esquinas[posEnVector].llevaCalleArriba(posEnVector,anchoDeMapa);
			if (resultado == true){ 
			
				this.insertarCalleArriba(calles,posEnVector);
			}
		}
	
	
		for (int posEnVector=0; posEnVector < (cantidadDeEsquinas); posEnVector++){
		
			boolean resultado = true;
			resultado = this.esquinas[posEnVector].llevaCalleDerecha(posEnVector,anchoDeMapa);
			if (resultado == true){ 
			
				this.insertarCalleDerecha(calles,posEnVector);
			}
		}
	

		for (int posEnVector=0; posEnVector < (cantidadDeEsquinas); posEnVector++){
		
			boolean resultado = true;
			resultado = this.esquinas[posEnVector].llevaCalleAbajo(posEnVector,anchoDeMapa);
			if (resultado == true){ 
			
				this.insertarCalleAbajo(calles,posEnVector);
			}
		}

		for (int posEnVector=0; posEnVector < (cantidadDeEsquinas); posEnVector++){
	
			boolean resultado = true;
			resultado = this.esquinas[posEnVector].llevaCalleIzquierda(posEnVector,anchoDeMapa);
			if (resultado == true){ 
		
				this.insertarCalleIzquierda(calles,posEnVector);
			}
		}
	}	
	
	private void unirCallesCon2Esquinas(Calle[][] calles) {
		
		//selecciono las calles y le asigo sus esquinas de cada cada punta
		//en la esquina1 esta la esquina de menor posicion, y en la esquina2 la esquina de mayor posicion
		
		for (int posVector = 0; posVector < anchoDeMapa*anchoDeMapa; posVector++ ){
		
			Calle unaCalle = this.esquinas[posVector].getCalleDerecha();
			if (unaCalle != null){
				Esquina esquina1 = this.esquinas[posVector];
				Esquina esquina2 = this.esquinas[posVector+1];
				this.esquinas[posVector].getCalleDerecha().setEsquinas(esquina1, esquina2);
			}
		
			Calle otraCalle = this.esquinas[posVector].getCalleAbajo();
			if (otraCalle != null){
				Esquina esquina1 = this.esquinas[posVector];
				Esquina esquina2 = this.esquinas[posVector+anchoDeMapa];
				this.esquinas[posVector].getCalleAbajo().setEsquinas(esquina1, esquina2);
			}
		}
	}
	
	
	public void asignarLargada(){
		boolean invalido=true;
		int n;
		
		while(invalido){
			n = (int)(Math.random() *  (this.anchoDeMapa));
			if ( n >= 1 & n <= anchoDeMapa){
				n = ((n-1) * this.anchoDeMapa);
				this.largada = esquinas[n];
				invalido = false;
			}
		}
	}
	
	public void asignarPosicionEnEsquina(int posicion){
		this.largada = esquinas[posicion];
	}
	
	public void asignarLlegada(){
		boolean invalido=true;
		int n;
		
		while(invalido){
			n = (int)(Math.random() *  this.anchoDeMapa);
			if ( n >= 1 & n <= anchoDeMapa){
				n = (n * (this.anchoDeMapa) - 1);
				this.llegada = esquinas[n];
				invalido = false;
			}
		}
	}
	
public void insertarObstaculosYSorpresas(Dificultad unaDificultad){
		
		//este metodo reparte encontrables a lo largo del escenario
		
		int posList = 0;
		int cantMax = unaDificultad.cantidadEncontrables();
		ArrayList<Encontrable> listaDeEncontrables = llenarListaAuxiliar(cantMax);
		callesConEncontrables = new ArrayList<Calle>();
		
		cargarEncontrablesEnCallesHorizontales(listaDeEncontrables, unaDificultad, posList, callesConEncontrables);
	
		cargarEncontrablesEnCallesVerticales(listaDeEncontrables, unaDificultad, posList, callesConEncontrables);
		
	}
	
	public ArrayList<Calle> getCallesConEncontrables(){
		return callesConEncontrables;
	}
	
	public ArrayList<Calle> cargarEncontrablesEnCallesHorizontales(ArrayList<Encontrable> unaLista, Dificultad unaDificultad, int posList, ArrayList<Calle> callesConEncontrables ){
		
		//este metodo reparte encontrables en calles horizontales a lo largo del escenario
				int ancho = unaDificultad.anchoDeMapa();
				
				for (int esq=2; esq < ancho*ancho-1; esq= esq+14){
					Calle calle = this.esquinas[esq].getCalleDerecha();
					if (calle == null){
						esq = esq +2;
						this.esquinas[esq].getCalleDerecha().agregarEncontrable(unaLista.get(posList)); 
						callesConEncontrables.add(this.esquinas[esq].getCalleDerecha()); 
						posList++;
					}
					else {
						this.esquinas[esq].getCalleDerecha().agregarEncontrable(unaLista.get(posList));
						callesConEncontrables.add(this.esquinas[esq].getCalleDerecha()); 
						posList++;
					}
				}
				
				for (int esq=5; esq < ancho*ancho-1; esq= esq+14){
					Calle calle = this.esquinas[esq].getCalleDerecha();
					if (calle == null){
						esq = esq +2;
						this.esquinas[esq].getCalleDerecha().agregarEncontrable(unaLista.get(posList));
						callesConEncontrables.add(this.esquinas[esq].getCalleDerecha()); 
						posList++;
					}
					else{
						this.esquinas[esq].getCalleDerecha().agregarEncontrable(unaLista.get(posList));
						callesConEncontrables.add(this.esquinas[esq].getCalleDerecha()); 
						posList++;
					}
				}
				return callesConEncontrables;
			}

	public ArrayList<Calle> cargarEncontrablesEnCallesVerticales(ArrayList<Encontrable> unaLista, Dificultad unaDificultad, int posList, ArrayList<Calle> callesConEncontrables ){
		
		//este metodo reparte encontrables en calles verticales a lo largo del escenario
		int ancho = unaDificultad.anchoDeMapa();
		
		for (int esq=0; esq < ancho*ancho-ancho-1; esq= esq+14){
			
				this.esquinas[esq].getCalleAbajo().agregarEncontrable(unaLista.get(posList)); 
				callesConEncontrables.add(this.esquinas[esq].getCalleAbajo()); 
				posList++;
			
		}
		
		for (int esq=4; esq < ancho*ancho-ancho-1; esq= esq+14){
			
				this.esquinas[esq].getCalleAbajo().agregarEncontrable(unaLista.get(posList));
				callesConEncontrables.add(this.esquinas[esq].getCalleAbajo()); 
				posList++;
		}
		return callesConEncontrables;
	}

	
	
	public ArrayList<Encontrable> llenarListaAuxiliar(int unaCantidad){
		
		//usando una lista auxiliar con los 6 encontrables posibles, lleno una lista
		//definitiva con la cantidad necesaria para colocar en un escenario
		
		int max = unaCantidad;
		
		ControlPolicial control = new ControlPolicial();
		SorpresaFavorable sorpFav = new SorpresaFavorable();
		Pozo pozo = new Pozo();
		SorpresaCambioDeVehiculo sorpCamb = new SorpresaCambioDeVehiculo();
		Piquete piquete = new Piquete();
		SorpresaDesfavorable sorpDes = new SorpresaDesfavorable();
		
		ArrayList<Encontrable> listAux = new ArrayList<Encontrable>();
		listAux.add(control);
		listAux.add(sorpFav);
		listAux.add(pozo);
		listAux.add(sorpCamb);
		listAux.add(piquete);
		listAux.add(sorpDes);
		
		ArrayList<Encontrable> listaDeEncontrables = new ArrayList<Encontrable>();


	    do{
			for (int pos =0; pos < listAux.size(); pos++){
				
				listaDeEncontrables.add(listAux.get(pos));
				
				max = max -1;
				
				if (max == 0) {return listaDeEncontrables;} 
			}
		} while (max != 0);
		
	return listaDeEncontrables;
	
	}

	
	public void insertarCalleArriba(Calle[][] calles, int posEsquina){
		
		//asigno a una esquina su calle de arriba
	
		this.esquinas[posEsquina].setCalleArriba(calles[posEsquina][posEsquina-anchoDeMapa]);
	}

	

	public void insertarCalleAbajo(Calle[][] calles,int posEsquina){
		
		//asigno a una esquina su calle de aabajo

	
		this.esquinas[posEsquina].setCalleAbajo(calles[posEsquina][posEsquina+anchoDeMapa]);
	}

	
	
	public void insertarCalleDerecha(Calle[][] calles,int posEsquina){
		
		//asigno a una esquina su calle derecha

	
		this.esquinas[posEsquina].setCalleDerecha(calles[posEsquina][posEsquina+1]);
	}

	
	
	public void insertarCalleIzquierda(Calle[][] calles,int posEsquina){
		
		//asigno a una esquina su calle izquierda

	
		this.esquinas[posEsquina].setCalleIzquierda(calles[posEsquina][posEsquina-1]);
	}




	public void cargarCalle (Calle[][] calles,int filaMatriz, int colMatriz){
		
		//carga con objetos Calles las posiciones de matriz correctas
	
		if ((filaMatriz < (this.cantidadDeEsquinas)) && (colMatriz < (this.cantidadDeEsquinas))){
			Calle unaCalle = new Calle();
	
	
			calles[filaMatriz][colMatriz] = unaCalle;
			calles[colMatriz][filaMatriz] = unaCalle;
		}
	}

	public boolean estaVacio(Calle[][] calles){
		
		//devuelve true si no estan cargadas las esquinas y las calles
	
		boolean resultado = true;
		
		for (int fila=0 ; fila < (this.cantidadDeEsquinas) ; fila++ ){
			
			for (int columna=0 ; columna < (this.cantidadDeEsquinas) ; columna++){
				
				if (calles[fila][columna] == null){}
					
				else resultado = false;
					
			}
		}
		for (int fila=0 ; fila < (this.cantidadDeEsquinas) ; fila++){
			
			if (this.esquinas[fila] == null){}
			
			else resultado = false;
			}
		
		return resultado;
	}

	
	public boolean esquinasCargadas(){
	
		//devuelve true si las esquinas estan correctamente cargadas en el vector.
	
		boolean resultado = true;
	
		for (int x=0 ; x < (this.cantidadDeEsquinas) ; x++ ){
		
			if (this.esquinas[x] == null){
			
				return false;
			}
			else {
			
				resultado = true;	
			}
		}
		return resultado;
	}



	public boolean callesCargadas(Calle[][] calles){
	
		//devuelve true si las calles estan correctamente cargadas en la matriz
	
		boolean resultado = true;
	
		for (int y=0 ; y < (this.cantidadDeEsquinas) ; y++){
		
			if ((y + (this.anchoDeMapa)) >= cantidadDeEsquinas)
				return resultado;
		
			if (calles[y][y + anchoDeMapa] == null){
			
				return false;
			}		
			else{
			
			resultado = true;
			}
		
		}
	
		for (int y=0 ; y < (this.cantidadDeEsquinas) ; y++){
		
			if (calles[y+anchoDeMapa][y] == null){
			
				return false;
			}		
			else{
			
				resultado = true;
			}
		}
		
		for (int y=0 ; y < (this.cantidadDeEsquinas) ; y++){
			
			if ((calles[y][y+1] == null)||(calles[y+1][y] == null)){
			
				return false;
			
			}
			else {
			
				resultado = true;
			}
		}

		return resultado;
	}
	
	
	
	
	public int cantidadDeEncontrables (){
		
		//devuelve la cantidad de encontrables en el escenario
		
		int contador =0;
		
		for (int cantEsq=0; cantEsq <anchoDeMapa*anchoDeMapa-1; cantEsq++){
			
			Calle calle = this.esquinas[cantEsq].getCalleDerecha();
			if (calle != null){
			Encontrable encontrable = calle.getEncontrable();
			if (encontrable != null){contador++;}
			}
		}
		
		for (int cantEsq=0; cantEsq <anchoDeMapa*anchoDeMapa-1-anchoDeMapa; cantEsq++){
			
			Calle calle = this.esquinas[cantEsq].getCalleAbajo();
			Encontrable encontrable = calle.getEncontrable();
			
			if (encontrable != null){contador++;}
		}
		
		return contador;
	}
	
	public boolean tienenCalleArriba(){

		for (int posEsqEnVector=8 ; posEsqEnVector < this.cantidadDeEsquinas ; posEsqEnVector++){
				
			Calle calle = this.esquinas[posEsqEnVector].getCalleArriba();	
			if (calle == null) return false;			
			}

			return true;
		}


	public boolean noTienenCalleArriba(){
	
		boolean resultado = false;
	
		for (int posEsqEnVector=0 ; posEsqEnVector < 7 ; posEsqEnVector++){
				
				Calle calleArriba = this.esquinas[posEsqEnVector].getCalleArriba();		
				if (calleArriba == null) resultado = true;
				else return false;
				}
		
		return resultado;
	}
	
	
	public boolean tienenCalleAbajo(){
	
		for (int posEsqEnVector=0 ; posEsqEnVector < (this.cantidadDeEsquinas - this.anchoDeMapa) ; posEsqEnVector++){
				
			Calle calleAbajo = this.esquinas[posEsqEnVector].getCalleAbajo();		
			if (calleAbajo == null) return false;
		}
		return true;
	}
	
	public boolean noTienenCalleAbajo(){
		
		int a = this.cantidadDeEsquinas - this.anchoDeMapa;
		
		for (int posEsqEnVector = a ; posEsqEnVector < this.cantidadDeEsquinas ; posEsqEnVector++){
				
				Calle calleAbajo = this.esquinas[posEsqEnVector].getCalleAbajo();
				if (calleAbajo != null) return false;
				}
		
		return true;
	}
	
	
	public boolean tienenCalleDerecha(){
		
		for (int posEsqEnVector=0 ; posEsqEnVector < this.cantidadDeEsquinas ; posEsqEnVector++){
			
			if ((posEsqEnVector+1)%this.anchoDeMapa != 0 ){
				
				Calle calleDerecha = this.esquinas[posEsqEnVector].getCalleDerecha();
				if (calleDerecha == null) return false;
			}
		}
		return true;
	}
	
	
	public boolean noTienenCalleDerecha(){
		
		
		for (int posEsqEnVector=0 ; posEsqEnVector < this.cantidadDeEsquinas ; posEsqEnVector++){
			
			if ((posEsqEnVector+1)%this.anchoDeMapa == 0 ){
				
				Calle calleDerecha = this.esquinas[posEsqEnVector].getCalleDerecha();
				if (calleDerecha != null) return false;
			}
		}
		
		return true;
	}
		
	
	public boolean tienenCalleIzquierda(){
	
		for (int posEsqEnVector=0 ; posEsqEnVector < this.cantidadDeEsquinas ; posEsqEnVector++){
		
			if ((posEsqEnVector)%this.anchoDeMapa == 0 ){
			
				Calle calleIzquierda = this.esquinas[posEsqEnVector].getCalleIzquierda();
				if (calleIzquierda != null) return false;
			}
		}
		return true;
	}
	
	
	public boolean noTienenCalleIzquierda(){
	
		for (int posEsqEnVector=0 ; posEsqEnVector < this.cantidadDeEsquinas ; posEsqEnVector++){
		
			if ((posEsqEnVector)%this.anchoDeMapa == 0 ){
			
				Calle calleIzquierda = this.esquinas[posEsqEnVector].getCalleIzquierda();
				if (calleIzquierda != null) return false;			
			}
		}
		return true;
	}


	public boolean lasCallesEstanCorrectas(){
	
		for (int posEsqEnVec=0 ; posEsqEnVec < cantidadDeEsquinas; posEsqEnVec++){
		
			if ((posEsqEnVec+1)%anchoDeMapa == 0){}
			else {
			
				Calle unaCalle = esquinas[posEsqEnVec].getCalleDerecha();
				Calle otraCalle = esquinas[posEsqEnVec+1].getCalleIzquierda();		
				if (unaCalle != otraCalle){return false;}
			}
		}
	
		for (int posEsqEnVec=0 ; posEsqEnVec < cantidadDeEsquinas-anchoDeMapa; posEsqEnVec++){
			
			Calle unaCalle = esquinas[posEsqEnVec].getCalleAbajo();
			Calle otraCalle = esquinas[posEsqEnVec+anchoDeMapa].getCalleArriba();		
			if (unaCalle != otraCalle){return false;}
		}
	
		return true;
	}

	
	

	

	public boolean validarEsquinaLargada(){
		
		for (int x = 0; x < 50; x++){
		
			int n = (int)(Math.random() *  this.anchoDeMapa);
			if ( n >= 1 & n <= anchoDeMapa){
				n = ((n-1) * this.anchoDeMapa);
				if ((n % this.anchoDeMapa)!=0){return false;}
				}
			}
		return true;
	}

	public boolean validarEsquinaLlegada(){
	
		for (int x = 0; x < 50; x++){
	
			int n = (int)(Math.random() *  this.anchoDeMapa);
			if ( n >= 1 & n <= anchoDeMapa){
				n = (n * (this.anchoDeMapa) - 1);
				n=n+1;
				if ((n % this.anchoDeMapa)!=0){return false;}
				}
			}
		return true;
	}


}
	

