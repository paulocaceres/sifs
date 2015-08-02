package ar.org.scouts.sifs.security

class Rol {

	String authority
	String name

	static mapping = {
		cache true
	}

	static constraints = {
		authority blank: false, unique: true
	}
}
