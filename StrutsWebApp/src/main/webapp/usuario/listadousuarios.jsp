
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.udimacuestion.clases.TablaUsuario" %>

				<% ArrayList listaUsuarios = (ArrayList)request.getSession().getAttribute("listaUsuarios");
				out.println("<div class='col-6' style='background-color:#EFF2FB;'>&nbsp;<p>");				
				if (listaUsuarios != null){%>
					
					<div class='form-group'>
					<label for='selU2'>Listado de usuarios</label>		
					<select class='form-control' id="selU2"><option value=0>-- Selecciona un usuario --</option>
					<%for(int x=0;x<listaUsuarios.size();x++) {
						 out.println("<option value='" + ((TablaUsuario)listaUsuarios.get(x)).getIdUsuario()+ "'>");
						 out.println("" + ((TablaUsuario)listaUsuarios.get(x)).getUsuario() + "</option>"); 						   
					}%>		
					</select></div>
					<%out.println("<input type='button' value='Modificar' class='btn btn-info' onclick='controlSeleccionUsu()'>&nbsp;&nbsp;&nbsp;&nbsp;");
										
					
				}
				out.println("</div>");
					%>
					
				
	