
<%@ page import="ar.org.scouts.sifs.ContactForm" %>
<html>
    <head>
        <meta name="layout" content="main" />
        
        <style type="text/css" media="screen">
			#status {
				background-color: #eee;
				border: .2em solid #fff;
				margin: 2em 2em 1em;
				padding: 1em;
				width: 12em;
				float: left;
				-moz-box-shadow: 0px 0px 1.25em #ccc;
				-webkit-box-shadow: 0px 0px 1.25em #ccc;
				box-shadow: 0px 0px 1.25em #ccc;
				-moz-border-radius: 0.6em;
				-webkit-border-radius: 0.6em;
				border-radius: 0.6em;
			}

			.ie6 #status {
				display: inline; /* float double margin fix http://www.positioniseverything.net/explorer/doubled-margin.html */
			}

			#status ul {
				font-size: 0.9em;
				list-style-type: none;
				margin-bottom: 0.6em;
				padding: 0;
			}

			#status li {
				line-height: 1.3;
			}

			#status h1 {
				text-transform: uppercase;
				font-size: 1.1em;
				margin: 0 0 0.3em;
			}

			#page-body {
				margin: 2em 1em 1.25em 18em;
			}

			h2 {
				margin-top: 1em;
				margin-bottom: 0.3em;
				font-size: 1em;
			}

			p {
				line-height: 1.5;
				margin: 0.25em 0;
			}

			#controller-list ul {
				list-style-position: inside;
			}

			#controller-list li {
				line-height: 1.3;
				list-style-position: inside;
				margin: 0.25em 0;
			}

			@media screen and (max-width: 480px) {
				#status {
					display: none;
				}

				#page-body {
					margin: 0 1em 1em;
				}

				#page-body h1 {
					margin-top: 0;
				}
			}
			
			legend {
			    display: none;
			}
			
			.button {
			    font-size: 1.05em;
			    color: #FFFFFF;
			    background-color: #b2c951;
			    border: 1px solid #245416;
			    padding: 5px;
			    margin: 10px 5px 0px 0px;
			}
			.button:hover {
			    background-color: #c9e06d;
			}
			
			#loading {
			    position: absolute;
			    top: 125px;
			    left: 75%;
			}
			
			/*
			 * Flash message
			*/
			#flashMessage, #errorMessage {
			    background-color: #BFC4FF;
			    background-image: url("../images/icon/information.png"); 
			    background-repeat: no-repeat;
			    background-position: 10px 10px;
			    border: 1px solid #8F93BF;
			    color: #000000;
			    margin-top: 20px;
			    margin-right: 0px;
			    margin-bottom: 20px;
			    margin-left: 0px;
			    padding-top: 5px;
			    padding-right: 5px;
			    padding-bottom: 5px;
			    padding-left: 35px;
			}
			
		</style>
        
        <g:set var="entityName" value="${message(code: 'contactForm.label', default: 'ContactForm')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
    	<div id="page-body" role="main">
                   <h1><g:message code="uk.co.anthonycampbell.grails.contactform.ContactForm.create.label" /></h1>
                   <g:render template="create" model="['contactFormInstance': contactFormInstance]" />
        </div>            
    </body>
</html>
