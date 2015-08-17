package ar.org.scouts.sifs

class Calificacion {
	
	String nombre

    static constraints = {
		nombre(matches: "[a-z\\sA-Z\\s0-9]+");
    }
	
	public String toString() {
		return nombre
	}
	
}
