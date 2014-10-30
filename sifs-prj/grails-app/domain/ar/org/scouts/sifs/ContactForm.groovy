package ar.org.scouts.sifs

class ContactForm {
	String yourFullName = ""
	String yourEmailAddress = ""
	String subject = ""
	String message = ""
	String captcha = ""

	static constraints = {
		yourFullName(blank:false, size:2..250)
		yourEmailAddress(blank:false, email:true, size:5..250)
		subject(blank:false, size:2..250)
		message(blank:false, size:2..10000)
		captcha(blank:false, size:2..6)
	}
   
}
