
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.udimacuestion.clases.TablaGrado" %>
<%@ page import="com.udimacuestion.clases.TablaAsignatura" %>




<% 											
				ArrayList listaAsignaturas = (ArrayList)request.getSession().getAttribute("listaAsignaturas");
				Integer asignaturaSelInt = ((Integer)request.getSession().getAttribute("asignaturaSel"));
				int asignaturaSeleccionada = 0;
				if (asignaturaSelInt != null) asignaturaSeleccionada = asignaturaSelInt.intValue();
				if (listaAsignaturas != null){
					out.println("<div class='form-group'>");
					out.println("<label for='sel2'>Listado de asignaturas</label>");		
					out.print("<select class='form-control' id='sel2' onchange='asignaturaSeleccion()'><option value=0>-- Selecciona una asignatura --</option>");
					for(int x=0;x<listaAsignaturas.size();x++) {
						if (asignaturaSeleccionada == ((TablaAsignatura)listaAsignaturas.get(x)).getIdAsignatura()){
							  out.println("<option value='" + ((TablaAsignatura)listaAsignaturas.get(x)).getIdAsignatura() + "'" 
									  +  " selected>");
							  out.println("" + ((TablaAsignatura)listaAsignaturas.get(x)).getNombreAsignatura() + " (" + ((TablaAsignatura)listaAsignaturas.get(x)).getNumControles() + ")</option>");  
						  }else{
							  out.println("<option value='" + ((TablaAsignatura)listaAsignaturas.get(x)).getIdAsignatura() + "'" 
									  +  ">");
							  out.println("" + ((TablaAsignatura)listaAsignaturas.get(x)).getNombreAsignatura() + " (" + ((TablaAsignatura)listaAsignaturas.get(x)).getNumControles() + ")</option>");
						  }
					}				
					out.println("</select></div>");
				}else{
					out.println("&nbsp;");
				}
					%>
					<p>
				<%@ include file="/listado/controllistado.jsp" %>
	