class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }
		
		"/"(controller:'login')
		"/home"(view:'/index')
        "500"(view:'/error')
		"/contact" {
			controller = "contactForm"
		}
	}
}
