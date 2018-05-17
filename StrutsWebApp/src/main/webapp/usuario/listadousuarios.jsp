
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.udimacuestion.clases.TablaUsuario" %>

				<% ArrayList listaUsuarios = (ArrayList)request.getSession().getAttribute("listaUsuarios");
				out.println("<div class='col-6' style='background-color:#EFF2FB;'>&nbsp;<p>");				
				
				
				if (listaUsuarios != null){%>
					<div class='form-group'>
					<form name="formusu" action='/cuestionariosUDT44/adminusuario'>
					<label for='selU2'><b>Listado de usuarios</b></label><p>
					<%for(int x=0;x<listaUsuarios.size();x++) {
						if (((TablaUsuario)listaUsuarios.get(x)).getSuperUsuario()==1){						
						  out.print("<input type='radio'  name='usuSel' value='" + ((TablaUsuario)listaUsuarios.get(x)).getIdUsuario() + "'> " + ((TablaUsuario)listaUsuarios.get(x)).getUsuario() + " &nbsp;&nbsp;&nbsp;&nbsp;(" + ((TablaUsuario)listaUsuarios.get(x)).getEmail() + ")&nbsp;&nbsp;&nbsp;&nbsp;- SuperUsuario<br>");
						}else{
						  out.print("<input type='radio'  name='usuSel' value='" + ((TablaUsuario)listaUsuarios.get(x)).getIdUsuario() + "'> " + ((TablaUsuario)listaUsuarios.get(x)).getUsuario() + " &nbsp;&nbsp;&nbsp;&nbsp;(" + ((TablaUsuario)listaUsuarios.get(x)).getEmail() + ")<br>");
						}
					}%>		
					</div>
					<%out.println("<input type='button' value='Seleccionar' class='btn btn-info' onclick='controlSeleccionUsu()'>&nbsp;&nbsp;&nbsp;&nbsp;");
	
				}
				
				out.println("</form></div>");
					%>
					
				
	