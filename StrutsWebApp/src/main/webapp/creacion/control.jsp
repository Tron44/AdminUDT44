
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.udimacuestion.clases.TablaGrado" %>


<% 
				Integer asignaturaSelInt2 = ((Integer)request.getSession().getAttribute("asignaturaSel"));
				int asignaturaSel2 = 0;
				if (asignaturaSelInt2 != null){
					asignaturaSel2 = asignaturaSelInt2.intValue();	
					String nombreControlSel2 = ((String)request.getSession().getAttribute("nombreControl"));
					String descControlSel2 = ((String)request.getSession().getAttribute("descControl"));
							
					
					if (nombreControlSel2 != null){
						out.println("<label>Nombre del control</label><br>");				
						out.println("<input type='text' size='50' name='nombreC'");
						out.println("class='form-control' value='"+ nombreControlSel2 +"' disabled>");
						out.println("<br>");
						out.println("<label>Descripción del control</label><br>");				
						out.println("<input type='text' size='50' name='nombreC'");
						out.println("class='form-control' value='"+ descControlSel2 +"' disabled>");
						out.println("<br><br>");
						out.println("</div>");%>
						<%@ include file="/creacion/pregunta.jsp"%>
					<% }else{
						out.println("<label>Nombre del control</label><br>");				
						out.println("<input type='text' size='50' id='nombreC'");
						out.println("class='form-control' value='Introduce el nombre del control'>");
						out.println("<br>");
						out.println("<label>Descripci&oacute;n del control</label><br>");				
						out.println("<input type='text' size='50' id='descC'");
						out.println("class='form-control' value='Introduce la descripción del control (máx 100)' onblur='nombreControl(nombreC.value, descC.value)'>");
						out.println("<br><br>");	
						out.println("</div>");
						
					}
					
					
				}else{
					out.println("</div>");
				}
				
				%>
				
	