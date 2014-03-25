package edu

class HolaGrailsola {
	static void main(args) {
		println "Hola Grailsola!"
		
		// http://grails.asia/grails-tutorial-for-beginners-playing-with-groovy-language/
		// para tener en  cuenta!!
		def firstName = 'John'
		def lastName = 'Doe'
		def a = 3
		def b = 7
	
		// "" Strings que ejecutan operaciones
		// '' Strings puros
		println "${lastName}, ${firstName}"
		println "a + b = ${a + b}"
		println '${lastName}, ${firstName}'
		println 'a + b = ${a + b}'
		
		// fors
		5.times {
			println "Looping!!"
		}
	}
}
