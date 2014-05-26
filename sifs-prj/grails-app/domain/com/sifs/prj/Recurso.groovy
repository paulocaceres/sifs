package com.sifs.prj

class Recurso {

	String nombre
	Integer cantidad
	Direccion direccion

    static constraints = {
    }

	String toString() {
		"$nombre, $cantidad"
	}
	
}
