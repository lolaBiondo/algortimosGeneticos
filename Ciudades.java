package tspFinal;



public class Ciudades {
	
	private static Celda[][] matriz;
	private static String[] ciudades = {
			("Cdad de Buenos Aires"),
			("Cordoba"),
			("Corrientes"),
			("Formosa"),
			("La Plata"),
			("La Rioja"),
			("Mendoza"),
			("Neuquen"),
			("Parana"),
			("Posadas"),
			("Rawson"),
			("Resistencia"),
			("Rio Gallegos"),
			("S.F.d.V.d. Catamarca"),
			("S.M. de Tucuman"),
			("S.S. de Jujuy"),
			("Salta"),
			("San Juan"),
			("San Luis"),
			("Santa Fe"),
			("Santa Rosa"),
			("Sgo. Del Estero"),
			("Ushuaia"),
			("Viedma")};
			
	private static int [] distancias = new int[] {
			0,	646,    792,    933,	53,		986,	985,	989,	375,	834,	1127,	794,	2082,	979,	1080,	1334,	1282,	1005,	749,	393,	579,	939,	2373,	799,
			0,	677,	824,	698,	340,	466,	907,	348,	919,	1321,	669,	2281,	362,	517,	809,	745,	412,	293,	330,	577,	401,	2618,	1047,	
			0,	157,	830,	814,	1131,	1534,	500,	291,	1845,	13,		2819,	691,	633,	742,	719,	1039,	969,	498,	1136,	535,	3131,	1527,
			0,  968,	927,	1269,	1690,	656,	263,	1999,	161,	2974,	793,	703,	750,	741,	1169,	1117,	654,	1293,	629,	3284,	1681,
			0,	1038,	1029,	1005,	427,	857,	1116,	833,	2064,	1030,	1132,	1385,	1333,	1053,	795,	444,	602,	991,	2350,	789,
			0,	427,	1063,	659,	1098,	1548,	802,	2473,	149,	330,	600,	533,	283,	435,	640,	834,	311,	2821,	1311,
			0,	676,	790,	1384,	1201,	1121,	2081,	569,	756,	1023,	957,	152,	235,	775,	586,	713,	2435,	1019,
			0,	1053,	1709,	543,	1529,	1410,	1182,	1370,	1658,	1591,	824,	643,	1049,	422,	1286,	1762,	479,
			0,	658,	1345,	498,	2320,	622,	707,	959,	906,	757,	574,	19,		642,	566,	2635,	1030,
			0,	1951,	305,	2914,	980,	924,	1007,	992,	1306,	1200,	664,	1293,	827,	3207,	1624,
			0,	1843,	975,	1647,	1827,	2120,	2054,	1340,	1113,	1349,	745,	1721,	1300,	327,
			0,	2818,	678,	620,	729,	706,	1029,	961,	495,	1132,	523,	3130,	1526,
			0,	2587,	2773,	3063,	2997,	2231,	2046,	2325,	1712,	2677,	359,	1294,
			0,	189,	477,	410,	430,	540,	602,	915,	166,	2931,	1391,
			0,	293,	228,	612,	727,	689,	1088,	141,	3116,	1562,
			0,	67,		874,	1017,	942,	1382,	414,	3408,	1855,
			0,	808,	950,	889,	1316,	353,	3341,	1790,
			0,	284,	740,	686,	583,	2585,	1141,
			0,	560,	412,	643,	2392,	882,
			0,	641,	547,	2641,	1035,
			0,	977,	2044,	477,
			0,	3016,	1446,
			0,	1605,
			0
			

			};
	

	public static void crearMatriz() {
	 matriz = new Celda[24][24];
	 
		for (int i = 0; i < 24; i++) {
			for (int j = 0; j < 24; j++) {
				
				matriz[i][j] = new Celda(ciudades[i], ciudades[j]);
			}
		}
		
		int k=0;
		int l=0;
		for( int i = 0; i<ciudades.length; i++) {
			for (int j = k; j<ciudades.length; j++) {
				int dist = distancias [l];
				matriz[i][j].setDistanciaEntreAmbas(dist);		
				matriz[j][i].setDistanciaEntreAmbas(dist);
				l++;
				
			}
			k++;
		}
	}
	
	public static Celda[][] getMatriz(){
		return matriz;
	}
	
	public static Celda encontrarProximaCiudad(String ciudadDesde) {
		int indexI = 50, indexJ = 50;
		int minimaActual = 10000;
		for (int i = 0; i < ciudades.length; i++) {
			if (matriz[i][0].getCiudadDesde().equalsIgnoreCase(ciudadDesde)) {
				 // encontrar la minima distancia
				
				 for (int j = 0; j < ciudades.length; j++) {
					   int dist = matriz[i][j].getDistanciaEntreAmbas();
					   Boolean yaFueVisitada = matriz[i][j].getFueVisitada();
					   		if (dist!=0 && dist < minimaActual && yaFueVisitada==false) {
					   			minimaActual = dist;
					   			indexI = i;
					   			indexJ = j;
					   		}
				 }
			  // sale del for para no seguir buscando 
			}
		}
		setearVisitada(matriz[indexI][indexJ].getCiudadDesde());
		return matriz[indexI][indexJ];
	}
	
	public static Celda encontrarCiudadInicial(String cInicial) {
		//Celda celdaInicial = null;
		int index=50;
		for (int i = 0; i < ciudades.length; i++) {
			if (matriz[i][0].getCiudadDesde().equalsIgnoreCase(cInicial)) 
				index = i; 
		}
		setearVisitada(matriz[index][0].getCiudadDesde());
		return matriz[index][0];
	}
	
	public static void setearVisitada(String ciu) {
		int index=50;
		for (int k=0; k < ciudades.length; k++) {
			if (matriz[k][0].getCiudadDesde().equalsIgnoreCase(ciu)) 
				index = k;
			}
		
		for (int n = 0; n < ciudades.length; n++) {
			matriz[n][index].setFueVisitada(true);
		}
	}
	
	public static int volverAlInicio(String cInicial, String cFinal) {
		int distancia=0;
		 for (int i = 0; i < ciudades.length; i++) {
			 if ( matriz[i][0].getCiudadDesde().equalsIgnoreCase(cInicial)) {
				 	for (int j = 0; j < ciudades.length; j++) {
						if (matriz[i][j].getCiudadHasta().equalsIgnoreCase(cFinal)) {
							 distancia = matriz[i][j].getDistanciaEntreAmbas();
						}
				 	}
			 }
		 }
		 return distancia;
		 }

	public static Celda getMejorRecorrido() {
		
		
		int mejorDistancia=150000;
		String mejorCiudad="";
		String ultimaCiudad="";
		for (int i = 0; i < ciudades.length; i++) {
			crearMatriz();
			int distanciaTotal = 0;
			encontrarCiudadInicial(ciudades[i]);
			String nextCiudad = ciudades[i];
				for (int j = 0; j < ciudades.length-1; j++) {
					encontrarProximaCiudad(nextCiudad);					
					Celda cell = Ciudades.encontrarProximaCiudad(nextCiudad);
					nextCiudad = (cell.getCiudadHasta());
					distanciaTotal += cell.getDistanciaEntreAmbas();
					ultimaCiudad = cell.getCiudadHasta();
					}
				distanciaTotal = distanciaTotal + Ciudades.volverAlInicio(ciudades[i], ultimaCiudad);
					if (distanciaTotal<mejorDistancia) {
						mejorDistancia = distanciaTotal;
						mejorCiudad = ciudades[i];
					}
				}
		Celda cell = new Celda(mejorCiudad, " ");
		cell.setDistanciaEntreAmbas(mejorDistancia);
		return cell;
		}
	public static String[] getCiudades() {
		return ciudades;
	}
	
	////////// ALGORITMOS GENETICOS //////////////
	
	public static int getDistanciaBetween(int ciudadA, int ciudadB) {
		int distancia = matriz[ciudadA][ciudadB].getDistanciaEntreAmbas();
		return distancia;
	}
	
	public static String getCiudad( int i)
	{
		return(ciudades[i]);
	}
	
}