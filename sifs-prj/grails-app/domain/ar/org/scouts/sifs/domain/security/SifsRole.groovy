package ar.org.scouts.sifs.domain.security

class SifsRole {

	String authority

	static mapping = {
		cache true
	}

	static constraints = {
		authority blank: false, unique: true
	}
}
