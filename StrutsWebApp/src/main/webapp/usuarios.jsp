<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta charset="UTF-8">

<title>Gesti&oacute;n Cuestionarios UDIMA</title>
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel='shortcut icon' type='image/x-icon' href='assets/favicon.ico' />


<!--jQuery-->
  <script type="text/javascript" src="assets/jquery.min.js"></script>

  <!--Bootstrap-->
  <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css"/>
  <script type="text/javascript" src="assets/bootstrap/js/bootstrap.min.js"></script>
   <!--Estilos  actual fic -->
  <link rel="stylesheet" href="assets/styles.css"/>
  
   <script type="text/javascript">
   
  var destino="";
   
   function controlSeleccionUsu() {	   
	    if (selU2.options[selU2.selectedIndex].value !=0){
     	destino='/cuestionariosUDT44/adminusuario.do?usuSel=' +selU2.options[selU2.selectedIndex].value;
  		window.location.href=destino;
	    }
 	}
   
   function controlSeleccionUsuDel() {
	    if (selU2.options[selU2.selectedIndex].value !=0){
    	destino='/cuestionariosUDT44/adminusuario.do?usuSelDel=' +selU2.options[selU2.selectedIndex].value;
 		window.location.href=destino;
	    }
	}
   
   </script>
  
</head>
<body>
<%@ page import="com.udimacuestion.clases.TablaUsuario" %>
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
			<li><a href="/cuestionariosUDT44/listarcuestionario.do">Listar cuestionarios</a></li>
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
			<% TablaUsuario tablaUsuario = (TablaUsuario)request.getSession().getAttribute("tablaUsuario");
			if (tablaUsuario ==null) tablaUsuario = new TablaUsuario();%>
			
			<html:form action="/adminusuario" focus="userName">
				<br>
				<label>
				Nombre de Usuario		
				</label>
				<br>				
				<%out.println("<input type='text' name='userName' value='"+ tablaUsuario.getUsuario() + "' size='50' style='height:40px'>");%>
				<p>
				<label>
				Email del Usuario		
				</label>
				<br>				
				<%out.println("<input type='text' name='email' value='"+ tablaUsuario.getEmail() + "' size='50' style='height:40px'>");%>
				<p>
				<label>
				Password			
				</label>
				<br>
		 		<%out.println("<input type='password' name='password' value='"+ tablaUsuario.getPassword() + "' size='50' style='height:40px'>");
		 		out.println("<input type='hidden' name='idUsuario' value='"+ tablaUsuario.getIdUsuario() + "'>");%>
		 				 		
				<p>
				<label>
				SuperUsuario 						
				</label>
				<%
				if (tablaUsuario.getSuperUsuario()==1){
					out.println("<input type='radio' name='superUsuario' value='1' checked>SI");
					out.println("<input type='radio' name='superUsuario' value='0'>NO");
				}else{
					out.println("<input type='radio' name='superUsuario' value='1'>SI");
					out.println("<input type='radio' name='superUsuario' value='0' checked>NO");
				}
				%>
				<p>
				<%
				if (tablaUsuario.getIdUsuario() ==0){
					out.println("<input type='submit' value='Alta Usuario' name='altausuario' class='btn btn-primary'>");
				}else{
					out.println("<input type='submit' value='Modificar Usuario' name='modusuario' class='btn btn-primary'>");
					out.println("<input type='submit' value='Borrar' class='btn btn-danger' name='borrarusuario' onclick=\"return confirm('¿Estás seguro de eliminar al usuario seleccionado?')\"/>");
				}
				%>
			</div>
			<%@ include file="/usuario/listadousuarios.jsp" %>
			
			
		</bordefila>	
	</html:form>	
	
	
	
</div>












</body>
</html>