
package tspFinal;


import java.util.ArrayList;
import java.util.Random;

public class Cromosoma {
	
	private ArrayList<Integer> ciudadesCromosoma;
	int distTotal;
	double fitness;
	double funcion; //funcion objetivo

	
	public Cromosoma() {
		this.ciudadesCromosoma = new ArrayList<Integer>(24);//24
	}
	
	public ArrayList<Integer> getCiudadesCromosoma() {
		return ciudadesCromosoma;
	}

	public void setCiudadesCromosoma(ArrayList<Integer> ciudadesCromosoma) {
		this.ciudadesCromosoma = ciudadesCromosoma;
	}

	public void llenarCromosoma() {
	
		Random random = new Random();
		int numRandom;
		for (int i = 0; i< 24; i++) { //ciudades cromosoma son los numeros de los genes que representan las ciudades//24
			do {
				numRandom = random.nextInt(24);//24
			} while(this.ciudadesCromosoma.contains(numRandom));
			this.ciudadesCromosoma.add(numRandom);
		}
	}
	
	public void llenarHijos() { // lleno todos los hijos con el numero 24 el cual no pertenece a ningun indice, ya que si no no puedo diferenciar las posiciones en arraylist vacio
		for (int i = 0; i< 24; i++) { //24
			this.ciudadesCromosoma.add(24);
		}
		
	}
	
	public void agregarCiudad(int i, Cromosoma padre) {
		int k = padre.ciudadesCromosoma.get(i); 
		this.ciudadesCromosoma.set(i, k);
	}
	
	public boolean noExiste (int j, Cromosoma padre) {
		int k=padre.ciudadesCromosoma.get(j);
		return !(this.ciudadesCromosoma.contains(k));
	}
	
	public int buscarGen(int i, Cromosoma padre){
		int posNueva=0;
		int k=padre.ciudadesCromosoma.get(i);//guardo el numero que hay en padre 2 en pos
	    for (int j = 0; j < padre.ciudadesCromosoma.size(); j++) {//busco la posicion en la cual esta k en padre 1
	        if (this.ciudadesCromosoma.get(j).equals(k)) {
	            posNueva=j;
	            break;
	        }
	    }
	    return posNueva;
	}

	
	public boolean genVacio (int i) {
		int gen = this.ciudadesCromosoma.get(i);
		return (gen == 24);
	}
	
	public void mutar (int i, int j) {
		int k = this.ciudadesCromosoma.get(i);
		int l = this.ciudadesCromosoma.get(j);
		this.ciudadesCromosoma.set(j, k);
		this.ciudadesCromosoma.set(i, l);
	}
	
	public static void mostrarCiudades() {
		
		
		}
	
	
	@Override 
	public String toString(){
		String cromosoma = "";
		for (Integer i : ciudadesCromosoma) {
			cromosoma = cromosoma + (i) + " ";
		}
		return cromosoma;
	}

	public int getDistTotal() {
		return distTotal;
	}

	public void setDistTotal(int distTotal) {
		this.distTotal = distTotal;
	}

	public double getFitness() {
		return fitness;
	}
	
	public double getFuncion() {
		return funcion;
	}

	public void setFitness(double fitness) {
		this.fitness = fitness;
	}
	
	public void setFuncion(double funcion) {
		this.funcion = funcion;
	}
	
	}