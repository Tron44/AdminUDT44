
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.udimacuestion.clases.TablaCuestionario" %>



<% 
				ArrayList listaControles = (ArrayList)request.getSession().getAttribute("listaControles");
				Integer controlSelInt = ((Integer)request.getSession().getAttribute("controlSel"));
				int controlSeleccionado = 0;
				if (controlSelInt != null) controlSeleccionado = controlSelInt.intValue();
				if (listaControles != null){
					out.println("<div class='form-group'>");
					out.println("<label for='sel3'>Listado de controles</label>");		
					out.print("<select class='form-control' id='sel3' onchange='controlSeleccion()'><option value=0>-- Selecciona un cuestionario --</option>");
					for(int x=0;x<listaControles.size();x++) {
						if (controlSeleccionado == ((TablaCuestionario)listaControles.get(x)).getIdCuestionario())
						{
							  out.println("<option value='" + ((TablaCuestionario)listaControles.get(x)).getIdCuestionario() + "_" + ((TablaCuestionario)listaControles.get(x)).getNombreCuestionario() +  
									   "_" + ((TablaCuestionario)listaControles.get(x)).getPublicacion() + "' selected>");
							  out.println("" + ((TablaCuestionario)listaControles.get(x)).getNombreCuestionario() + "</option>");  
						  }else{
							  out.println("<option value='" + ((TablaCuestionario)listaControles.get(x)).getIdCuestionario() + "_"  + ((TablaCuestionario)listaControles.get(x)).getNombreCuestionario() +
									  "_" + ((TablaCuestionario)listaControles.get(x)).getPublicacion() + "'>");
							  out.println("" + ((TablaCuestionario)listaControles.get(x)).getNombreCuestionario() + "</option>");
						  }
					}				
					out.println("</select></div>");
					if(controlSeleccionado!=0){
						out.println("<br><a href='/cuestionariosUDT44/listarcuestionario.do?eliminarcuestionario=" + controlSeleccionado + "' class='btn btn-danger' onclick=\"return confirm('¿Estás seguro de eliminar el cuestionario seleccionado?')\"/>Eliminar Cuestionario</a>");
						out.println("<input type=button class='btn btn-primary' value='Modificar Cuestionario' onclick=\"campotexto.disabled = false; campotexto.focus(); campotexto.value='';\"><p><br>");
						out.println("<form action='/cuestionariosUDT44/listarcuestionario.do' name='modificarcuestionarioform'>");
						out.println("<input type='text' value='Introduce el nuevo nombre del cuestionario' name='campotexto' size='50' style='height:40px' id='campotexto' disabled='true'>");
						out.println("<input type='hidden' name='controlSeleccionado' id='controlSeleccionado' value='" + controlSeleccionado + "'>");
						out.println("<input type='submit' value=' >> ' class='btn btn-primary' name='modificarcuestionario'>");
						out.println("</form><p>");
						//out.println("<a href='/cuestionariosUDT44/listarcuestionario.do?modificarcuestionario=" + controlSeleccionado + "' class='btn btn-primary'> >> </a>");
						
					}
					out.println("</div>");%>
					
					<%@ include file="/listado/preguntalistado.jsp"%>					
				<%}else{
					out.println("");
				}
					%>
					<p>
				
	