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
   <!--Estilos-->
  <link rel="stylesheet" href="assets/styles.css"/>
  
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
	<nav id="nav" class="col-12">
		<ul>
			<li><a>Login</a></li>			
		</ul>
	</nav>	
	</div>
		<bordefila class="row">			
			<div class="col-2" style="background-color:white;">&nbsp;</div>
			<div class="col-10 align-self-center" style="background-color:white;">
			<html:form action="/login" focus="userName">
				<br>
				<label>
				Usuario		
				</label>
				<br>				
				<html:text property="userName" value="" size='50' style='height:40px'/>
				
				<br><br>
				<label>
				Password			
				</label>
				<br>
		 		<html:password property="password" value="" size='50' style='height:40px'/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		 		<input type="submit" value='Login' class="btn btn-primary">
				<br>
				<br>
			</div>
			
		</bordefila>	
	</html:form>
		
	<div class="row">		
		<footer id="footer" class="col-12">
			<p>Ra&uacute;l Arranz Molinero - Administraci&oacute;n de Cuestionarios UDIMA</p>
		</footer>
	</div>
	
</div>



	
</body>
</html>