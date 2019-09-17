package tspFinal;


public class Celda {

	private String ciudadDesde;
	private String ciudadHasta;
	private int distanciaEntreAmbas;
	private Boolean fueVisitada;
	
	public Celda(String cDesde, String cHasta) {
		this.ciudadDesde = cDesde;
		this.ciudadHasta = cHasta;
		this.fueVisitada = false;
	}
	
	
	
	public Boolean getFueVisitada() {
		return fueVisitada;
	}
	public void setFueVisitada(Boolean fueVisitada) {
		this.fueVisitada = fueVisitada;
	}
	public String getCiudadDesde() {
		return ciudadDesde;
	}
	public void setCiudadDesde(String ciudadDesde) {
		this.ciudadDesde = ciudadDesde;
	}
	public String getCiudadHasta() {
		return ciudadHasta;
	}
	public void setCiudadHasta(String ciudadHasta) {
		this.ciudadHasta = ciudadHasta;
	}
	public int getDistanciaEntreAmbas() {
		return distanciaEntreAmbas;
	}
	public void setDistanciaEntreAmbas(int distanciaEntreAmbas) {
		this.distanciaEntreAmbas = distanciaEntreAmbas;
	}
}