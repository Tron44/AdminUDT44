
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.udimacuestion.clases.TablaGrado" %>
<%@ page import="com.udimacuestion.clases.TablaRespuesta" %>


<html:form action="/crearpreguntarespuesta">
<%
	
	String numRespuestas = (String)request.getSession().getAttribute("numerorespuestas");
	String tipoPreg = (String)request.getSession().getAttribute("tipopregunta");
	TablaRespuesta tablaRes = (TablaRespuesta)request.getSession().getAttribute("tablaRespuesta");
	ArrayList listaRespuestas = (ArrayList)request.getSession().getAttribute("listaRespuestas");
	
	
		int numRes=0;
		if (numRespuestas != null){
			numRes = Integer.parseInt(numRespuestas);
		}
		if(listaRespuestas == null || listaRespuestas.size() ==0){
			for (int i=0; i <numRes; i++){
				int j=i+1;%>
				<%if (tipoPreg.equals("Excluyente")){
					out.print("<input type='radio'  name='respuestaCheck' value='ok" + i + "'> Texto de la respuesta " + j);
				}else{
					out.print("<input type='checkbox'   name='respuestaCheck' value='ok" + i + "'> Texto de la respuesta " + j);
				}
				out.print("<textarea class='form-control' name='textoRespuesta" + i + "'></textarea><br>");
			}	
		}else{
			for (int i=0; i <numRes; i++){
				int j=i+1;%>
				<%if (tipoPreg.equals("Excluyente")){
					if (((TablaRespuesta)listaRespuestas.get(i)).isValido()){
						out.print("<input type='radio'  name='respuestaCheck' value='ok" + i + "' checked> Texto de la respuesta " + j);
					}
					else{
						out.print("<input type='radio'  name='respuestaCheck' value='ok" + i + "'> Texto de la respuesta " + j);
					}
				}else{
					if (((TablaRespuesta)listaRespuestas.get(i)).isValido()){
						out.print("<input type='checkbox'   name='respuestaCheck' value='ok" + i + "' checked> Texto de la respuesta " + j);	
					}else{
						out.print("<input type='checkbox'   name='respuestaCheck' value='ok" + i + "'> Texto de la respuesta " + j);
					}
						
					
				}
				out.print("<textarea class='form-control' name='textoRespuesta" + i + "'>" + ((TablaRespuesta)listaRespuestas.get(i)).getTextoRespuesta() + "</textarea><br>");
			}	
		}
		
	
	%>
	
	<input type='submit' value='Guardar' class='btn btn-primary'>
</html:form>