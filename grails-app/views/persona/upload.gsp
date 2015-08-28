<%@ page import="ar.org.scouts.sifs.Persona" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <title>Cargar archivo</title>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'jquery-ui.css')}" type="text/css">
	<script src="/sifs-prj/static/js/jquery-ui.js" type="text/javascript" ></script>
	<script src="/sifs-prj/static/js/jprogress.js" type="text/javascript" ></script>
</head>

<body>
<div class="nav" role="navigation">
    <ul>
        <li><g:link class="list" controller="persona" action="index">Person List</g:link></li>
    </ul>
</div>

<div id="upload-data" class="content scaffold-create" role="main">
    <div class="content scaffold-create" role="main">
        <h1>Cargar Personas desde archivo</h1>
        <g:if test="${flash.message}"><div class="message" role="status">${flash.message}</div></g:if>
        <g:uploadForm action="doUpload">
            <fieldset class="form">
                <input type="file" name="file" />
            </fieldset>
            <fieldset class="buttons">
                <g:submitButton name="doUpload" value="Cargar" onsubmit="return importStart()"/>
            </fieldset>
        </g:uploadForm>
        <g:jprogressDialog message="Espere unos instantes, procesando archivo de carga masiva..." progressId="cargaExcelProgress" trigger="doUpload"/>
    </div>
</div>
</body>
</html>