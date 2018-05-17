
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
				
				if (listaUsuarios != null){%>
					<div class='form-group'>
					<label for='selUser'>Listado de usuarios</label>
					<%for(int x=0;x<listaUsuarios.size();x++) {
						if (((TablaUsuario)listaUsuarios.get(x)).getSuperUsuario()==1){						
						  out.print("<input type='radio'  name='usuarios' value='" + ((TablaUsuario)listaUsuarios.get(x)).getIdUsuario() + "'>" + ((TablaUsuario)listaUsuarios.get(x)).getUsuario() + "  (" + ((TablaUsuario)listaUsuarios.get(x)).getUsuario() + ")  SuperUsuario");
						}else{
						  out.print("<input type='radio'  name='usuarios' value='" + ((TablaUsuario)listaUsuarios.get(x)).getIdUsuario() + "'>" + ((TablaUsuario)listaUsuarios.get(x)).getUsuario() + "  (" + ((TablaUsuario)listaUsuarios.get(x)).getUsuario() + ")");
						}
					}%>		
					</div>
					<%out.println("<input type='button' value='Modificar' class='btn btn-info' onclick='controlSeleccionUsu()'>&nbsp;&nbsp;&nbsp;&nbsp;");
	
				}
				
				out.println("</div>");
					%>
					
				
	