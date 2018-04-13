<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta charset="UTF-8"/>
<title>Gesti&oacute;n Cuestionarios UDIMA</title>
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel='shortcut icon' type='image/x-icon' href='assets/favicon.ico' />


<!--jQuery-->
  <script type="text/javascript" src="assets/jquery.min.js"></script>

  <!--Bootstrap-->
  <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css"/>
  <script type="text/javascript" src="assets/bootstrap/js/bootstrap.min.js"></script>
   <!--Estilos-->
  <link rel="stylesheet" href="assets/styles.css"/>
  <script type="text/javascript">
  var destino="";
   function gradoSeleccion() {
	    if (sel1.options[sel1.selectedIndex].value !=0){
       	destino='/cuestionariosUDT44/listarcuestionario.do?gradoSel=' +sel1.options[sel1.selectedIndex].value;
    	window.location.href=destino;
	    }
   }
   function asignaturaSeleccion() {
	    if (sel2.options[sel2.selectedIndex].value !=0){
      	destino='/cuestionariosUDT44/listarcuestionario.do?asignaturaSel=' +sel2.options[sel2.selectedIndex].value;
   		window.location.href=destino;
	    }
  }
   function controlSeleccion() {
	    if (sel3.options[sel3.selectedIndex].value !=0){
     	destino='/cuestionariosUDT44/listarcuestionario.do?controlSel=' +sel3.options[sel3.selectedIndex].value;
  		window.location.href=destino;
	    }
 	}
   
   
   
    
 
   </script>
</head>
<body>
<div class="container">
	<div class="row">
		
		<header id="header" class="col-11">
			<h1>
			  Administraci&oacute;n de Cuestionarios UDIMA 
			</h1>
		</header>
		<div class="col-1 uno"><br><img align='right' alt="Universidad a distancia Madrid" src='assets/udima.jpg' width='50px' height='60px'/></div>
		
	</div>
	<div class="row">	
	<nav id="nav" class="col-11">
		<ul>
			<li><a href="/cuestionariosUDT44/login.do?logoff=S">Logoff</a></li>			
			<li><a class="activado">Listar cuestionarios</a></li>
			<li><a href="/cuestionariosUDT44/crearcuestionario.do">Crear cuestionario</a></li>			
			<%if (((Integer)request.getSession().getAttribute("superusuario")).intValue()==1)	
			out.println("<li><a href='/cuestionariosUDT44/adminusuario.do'>Administrar usuarios</a></li>"); %>
		</ul>
	</nav>
	<nav id="nav" class="col-1">
		<ul>
			<li>${sessionScope.usuario}&nbsp;&nbsp;&nbsp;</li>		
		</ul>
	</nav>		
	</div>
		<bordefila class="row">			
			<div class="col-6" style="background-color:white;">
				
				<% ArrayList listaGrados = (ArrayList)request.getSession().getAttribute("listaGrados");
				Integer gradoSelInt = ((Integer)request.getSession().getAttribute("gradoSel"));
				int gradoSeleccionado = 0;
				if (gradoSelInt != null) gradoSeleccionado = gradoSelInt.intValue();
				out.println("<div class='form-group'>");
				out.println("<label for='sel1'>Listado de grados</label>");		
				out.print("<select class='form-control' id='sel1' onchange='gradoSeleccion()'><option value=0>-- Selecciona un grado --</option>");
				for(int x=0;x<listaGrados.size();x++) {
					  if (gradoSeleccionado == ((TablaGrado)listaGrados.get(x)).getIdGrado()){
						  out.println("<option value='" + ((TablaGrado)listaGrados.get(x)).getIdGrado() + "' selected>");
						  out.println("" + ((TablaGrado)listaGrados.get(x)).getNombreGrado() + "</option>");  
					  }else{
						  out.println("<option value='" + ((TablaGrado)listaGrados.get(x)).getIdGrado() + "'>");
						  out.println("" + ((TablaGrado)listaGrados.get(x)).getNombreGrado() + "</option>");
					  }
					  
				}				
				out.println("</select>");
				
				
				out.println("</div>");
				%>
				
				<%@ include file="/listado/asignaturalistado.jsp" %>
				
		
		</bordefila>	
	
		
	<div class="row">		
		<footer id="footer" class="col-12">
			<p>Ra&uacute;l Arranz Molinero - Administraci&oacute;n de Cuestionarios UDIMA</p>
		</footer>
	</div>
	
</div>












</body>
</html>