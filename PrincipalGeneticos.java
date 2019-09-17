package tspFinal;

import java.util.ArrayList;

public class PrincipalGeneticos {

	static int longPoblacion = 10;
	static int longCromosoma = 24;//24
	static double probCrossover=0.9;
	static double probMutacion=0.05;
	static int cantGeneraciones = 1000;
	static int[] pool = new int[100];
	static int[] distancias = new int[longCromosoma];
	static ArrayList<Cromosoma> poblacion = new ArrayList<Cromosoma>();
	static ArrayList<Cromosoma> poblacionAuxiliar = new ArrayList<Cromosoma>();
	static int padres[] = new int[longPoblacion];
	static Cromosoma padre2=new Cromosoma();
	static Cromosoma padre1=new Cromosoma();
	static Cromosoma hijo1=new Cromosoma();
	static Cromosoma hijo2=new Cromosoma();
	static int generacion=0;
	static String[] recorrido = new String[longCromosoma];
	public ArrayList<String> ciudades;
	static double sumFitness = 0;
	
	
	public static void main(String[] args) {
		
		crearPoblacionInicial();
		Ciudades.crearMatriz();
		setearDistancias ();		
		sumFitness=evaluarFitness();
		System.out.println("\nPoblacion Inicial - Generacion: " + (generacion) + "\n");
		mostrar(poblacion);
		
		while((generacion<cantGeneraciones)){
			generacion++;
	
		setearDistancias ();		
		sumFitness=evaluarFitness();
		System.out.println("\nGeneracion: " + (generacion) + "\n" );
		mostrar(poblacion);
		generarPool();
		determinarPadres();
		crossoverCiclico();
		hacerMutacion();
		generarNuevaPoblacion();	
		
		
	}
		
		mostrarCiudades(poblacion);
	}
	
	
	static void setearDistancias() {
		for (int i= 0;  i < longPoblacion; i++) {
		int localDistancia = 0;
		//mostrar(poblacion);
		for (int j = 0; j < (longCromosoma-1); j++) {
			
			//getDistanciaBetween es un metodo estatico de Ciudades que devuelve la distancia dadas
			// dos ciudades como parametros, estas se obtienen de la poblacion.
			
			 localDistancia = localDistancia + Ciudades.getDistanciaBetween(
							poblacion.get(i).getCiudadesCromosoma().get(j), 
							poblacion.get(i).getCiudadesCromosoma().get(j+1));
		}
		poblacion.get(i).setDistTotal(localDistancia);
	}
	}
	
	static double evaluarFitness() {
		int summ = 0;
		double sumobj = 0;
		double sumfit=0;
		for (Cromosoma cr : poblacion) {
			summ+=cr.getDistTotal();
		}
		
		// LA FUNCION OBJETIVO ES LA DIFERENCIA ENTRE EL TOTAL DE KM Y LA QUE RECORRIO CADA CROMOSOMA,
		
		for(Cromosoma cr : poblacion) {
			cr.setFuncion( ((double)summ - cr.getDistTotal())); 
			sumobj += cr.getFuncion();
		}
		for(Cromosoma cr : poblacion) {
			cr.setFitness(cr.getFuncion() / (double)sumobj);    //  ((double)summ  (longPoblacion-1)))
			sumfit += cr.getFitness();
		}
		return (sumfit);
	}
	
	//LLENAR CROMOSOMA ES UN METODO PROPIO DE CADA CROMOSOMA QUE LLENA ALEATORIAMENTE LAS 24 CIUDADES
	static void crearPoblacionInicial() {
		for (int i = 0; i < longPoblacion; i++) {
	           poblacion.add(new Cromosoma());
	           poblacion.get(i).llenarCromosoma();
		}
	}
	
	static void generarPool(){
		int actualPos=0;
		for (int i=0; i<longPoblacion; i++) {
			
				for (int posibilidades=0; posibilidades<(int)(Math.round(poblacion.get(i).getFitness()*100)); posibilidades++) {
					if (actualPos==100) break; 
					else {
						pool[actualPos]=i;
						actualPos++;
						}
				}
		}
	}
	
	static void determinarPadres(){
		
		for (int i=0; i<longPoblacion; i++) {
			padres[i] = pool[(int)(Math.random()*100)]; //padres me guarda los indices de la pool que son los indices de poblacion
		}
	}
	
	static void crossoverCiclico() {
		for (int parejas=0; parejas<longPoblacion; parejas+=2) {			
			int hacerCrossover = (int)(Math.random()*101); //¿HACER O NO EL CROSSOVER?//
			int index1=parejas;
			int index2=(parejas+1);
			padre1 = poblacion.get(padres[index1]);
			padre2 = poblacion.get(padres[index2]);
			//System.out.println("Cromosoma " + padres[index1] + "- Padre " + index1 + ": " + padre1.toString()  );
			//System.out.println("Cromosoma " + padres[index2] + "- Padre " + index2 + ": " + padre2.toString()  );
			if (hacerCrossover<=(int)(probCrossover*100)) {
				//System.out.println("C");
				int pos1=0; 
				int pos2=0; 
				poblacionAuxiliar.add(index1, new Cromosoma()); //agrego hijo 1
				poblacionAuxiliar.add(index2, new Cromosoma()); 	// agrego hijo 2
				hijo1=poblacionAuxiliar.get(index1);
				hijo2=poblacionAuxiliar.get(index2);
				hijo1.llenarHijos(); //tengo que llenarlos para poder referenciar a las posiciones 
				hijo2.llenarHijos();
				hijo1.agregarCiudad(pos1, padre1);
				hijo2.agregarCiudad(pos2, padre2);
				//HIJO 1	
				pos1=padre1.buscarGen(pos1, padre2);				
				for (int i=1; i<longCromosoma; i++) {							//VER DE PONER UN BREAK PARA QUE NO SIGA DANDO VUELTAS EN EL FOR					
					if(hijo1.noExiste(pos1, padre1)) {
						hijo1.agregarCiudad(pos1, padre1);
						pos1=padre1.buscarGen(pos1, padre2);
						}
					}
				//cuando sale rellena el resto de los genes que quedaron vacios
				for (int i=0;i<longCromosoma;i++) {
					if (hijo1.genVacio(i)) {
						hijo1.agregarCiudad(i, padre2);
					}
				}
				
				//HIJO 2
				pos2=padre2.buscarGen(pos2, padre1);
				for (int i=1; i<longCromosoma; i++) {					
					if(hijo2.noExiste(pos2, padre2)) {
							hijo2.agregarCiudad(pos2, padre2); 	
							pos2=padre2.buscarGen(pos2, padre1);
						}
					}
				//cuando sale rellena el resto de los genes que quedaron vacios
				for (int i=0;i<longCromosoma;i++) {
					if (hijo2.genVacio(i)) {
						hijo2.agregarCiudad(i, padre1);
					}
				}
			
				}else { //copio los padres tal cual
					poblacionAuxiliar.add(padre1);
					poblacionAuxiliar.add(padre2);	
				}	
				//System.out.println("Hijo " + index1 + ": " + poblacionAuxiliar.get(index1).toString()   );
				//System.out.println("Hijo " + index2 + ": " + poblacionAuxiliar.get(index2).toString()   );
			
		}
	}
	
	
	public static void hacerMutacion(){
		
		for (int i=0; i<longPoblacion; i++) {
			int hacerMutacion = (int)(Math.random()*101);			
			if (hacerMutacion<=(int)(probMutacion*100)) {
				int posRandom1=(int)(Math.random()*(longCromosoma-1));
				int posRandom2=(int)(Math.random()*(longCromosoma-1));
				poblacionAuxiliar.get(i).mutar(posRandom1, posRandom2);
				//System.out.println("Hijo " + i + " mutado en: " + posRandom1 + " y " + posRandom2 + ": " + poblacionAuxiliar.get(i).toString() );
			}	
		}
	}
	//traspasar poblacion
	public static void generarNuevaPoblacion(){
		Cromosoma var=new Cromosoma();
		for (int i=0; i<longPoblacion; i++) {		
				var = poblacionAuxiliar.get(i);
				poblacion.set(i, var);
			
		}
	}
	
	public static void mostrar(ArrayList<Cromosoma> pob) {	
		int summ = 0;
		for (Cromosoma cr : pob) {
					
					summ +=cr.getDistTotal();
					int index = pob.indexOf(cr);
					System.out.println("Cromosoma " + index + ": " + cr.toString() +
									   "- Distancia: "+ cr.getDistTotal()+ 
									   "- Fitness: " + cr.getFitness());
				}
		System.out.println("\nSuma total de distancias: " + summ);
		System.out.println("Sumatoria fitness: " + sumFitness + "\n");
		System.out.println("------------------------------------------------------------------------------------------------------------------------");	
	}
	
	public static void mostrarCiudades(ArrayList<Cromosoma> pob) {
		System.out.println("------------------------------------------------------------------------------------------------------------------------");	
		System.out.println("\n\nGeneracion final: " + generacion + "\n");
		for (Cromosoma cr : pob) {	
			for (int j = 0; j < (longCromosoma-1); j++) {
				 recorrido [j] = Ciudades.getCiudad(cr.getCiudadesCromosoma().get(j));
			}			
			int index = pob.indexOf(cr);
			System.out.println("\nRecorrido cromosoma " + index);
			for (int l = 0; l < (longCromosoma-1); l++) {
				System.out.println(l + " - " + recorrido[l]);
			}
		}
	}
	
}