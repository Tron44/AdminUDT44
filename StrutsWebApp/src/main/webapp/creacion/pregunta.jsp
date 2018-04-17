
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.udimacuestion.clases.TablaGrado" %>

<div class="col-6" style="background-color:#EFF2FB;">
			
			<%
			String textoPregunta = ((String)request.getSession().getAttribute("textopregunta"));
			String tipoPregunta = ((String)request.getSession().getAttribute("tipopregunta"));
			String numeroRespuestas = ((String)request.getSession().getAttribute("numerorespuestas"));
			Integer preguntarespuestaguardada = (Integer) request.getSession().getAttribute("preguntarespuestaguardada");	
			if (textoPregunta != null){				
				out.println("<label>Texto de la pregunta</label><br>");			
				out.println("<textarea class='form-control' name='textoPregunta' disabled>" + textoPregunta + "</textarea>");
				out.println(tipoPregunta);	
				out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;N&uacute;mero de respuestas: " + numeroRespuestas);
				out.println("<br><br>");%>
				<%@ include file="/creacion/respuesta.jsp"%>
			<%}else{%>
				<html:form action="/crearpreguntarespuesta">
				<label>Texto de la pregunta</label><br>				
				<textarea class='form-control' name='textoPregunta'></textarea>
				<br>		
				<input type='radio'  name='tipoPregunta' value='Incluyente' checked>Incluyente&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type='radio' name='tipoPregunta' value='Excluyente'>Excluyente	
				<br>Num. de respuestas:	<input type='number'  Style='width:45px' min='2' max='10' name='numeroRespuestas' value='1'>&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type='submit' value='>> respuestas' class='btn btn-primary'><br>
				<br><br><br>
				<a href="/cuestionariosUDT44/cargarcuestionario.do?carga=si" class="btn btn-primary">Cargar cuestionario desde fichero</a>
				
				
				
				</html:form>
				<%
				if (preguntarespuestaguardada !=null && preguntarespuestaguardada!=0){
					request.getSession().setAttribute("preguntarespuestaguardada", preguntarespuestaguardada);
					out.println("<br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label style='color:green'>Pulsa el botón<b> >> </b>para añadir más respuestas</label><br>");	
				}
			}%>			
			
	</div>