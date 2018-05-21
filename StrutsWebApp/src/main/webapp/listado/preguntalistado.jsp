
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.udimacuestion.clases.TablaPregunta" %>
<%@ page import="com.udimacuestion.clases.TablaRespuesta" %>


			
				<%
				ArrayList listaPreguntasRespuestas = (ArrayList)request.getSession().getAttribute("listaPreguntasRespuestas");
				Integer controlSelInt2 = ((Integer)request.getSession().getAttribute("controlSel"));
				String publicadoControl = ((String)request.getSession().getAttribute("publicadoControl"));
				TablaPregunta tablaPregunta;
				TablaRespuesta tablaRespuesta;
				String tipoPregunta = "";
				boolean esValidaRespuesta=false;
				ArrayList listaRespuestas = new ArrayList();
				int controlSeleccionado2 = 0;
				if (controlSelInt2 != null) controlSeleccionado2 = controlSelInt2.intValue();
				if (listaPreguntasRespuestas != null){
					out.println("<div class='col-6' style='background-color:#EFF2FB;'>&nbsp;<p>");
					
					if(publicadoControl.equals("0")){
						out.println("<a href='/cuestionariosUDT44/listarcuestionario.do?publicar=1' class='btn btn-success'>Publicar cuestionario</a>");
					}else{
						out.println("<a href='/cuestionariosUDT44/listarcuestionario.do?publicar=0' class='btn btn-info'>Ocultar cuestionario</a>");
					}
					
					%>					
					<input type="button" value='Refrescar' class="btn btn-info" onclick="controlSeleccion()">
					<p>
					<%out.println("<form action='/cuestionariosUDT44/listarcuestionario.do' name='formModDel'>");
					for(int x=0;x<listaPreguntasRespuestas.size();x++) {
						tablaPregunta = (TablaPregunta)listaPreguntasRespuestas.get(x);							
						String preguntaMod = ((String)request.getSession().getAttribute("preguntaMod"));
						String numResp="";
						//control de hay modificación de pregunta
						if (preguntaMod != null && !preguntaMod.equals("") && Integer.parseInt(preguntaMod) == tablaPregunta.getIdPregunta()){		
							numResp = tablaPregunta.getNumeroRespuestas();
							out.println("<input type='hidden' name='numRespuestas' value='" + numResp + "'>");
								out.println("<br><hr><textarea class='form-control' name='textoPregunta'>" + tablaPregunta.getTextoPregunta() + "</textarea><br>");							
								tipoPregunta = tablaPregunta.getTipoPregunta();
								listaRespuestas = (ArrayList)tablaPregunta.getListaRespuestas();
								for(int y=0;y<listaRespuestas.size();y++) {
									tablaRespuesta = (TablaRespuesta)listaRespuestas.get(y);
									esValidaRespuesta = tablaRespuesta.isValido();
									if (tipoPregunta.equals("Incluyente")){
										if(esValidaRespuesta){
											out.print("<input type='checkbox' name='respuestaCheck' value='ok" + y + "' checked>");
											
										}else{
											out.print("<input type='checkbox' name='respuestaCheck' value='ok" + y + "'>");
										}		
										out.println("<input type='hidden' name='idRespuesta" + y + "' value='" + tablaRespuesta.getIdRespuesta() + "'>");
										out.print("<textarea class='form-control' name='textoRespuesta" + y + "'>" + tablaRespuesta.getTextoRespuesta() + "</textarea><br>");
									
									}else{
										if(esValidaRespuesta){
											out.print("<input type='radio' name='respuestaCheck' value='ok" + y + "' checked>");
										}else{
											out.print("<input type='radio' name='respuestaCheck' value='ok" + y + "'>");
										}
										out.println("<input type='hidden' name='idRespuesta" + y + "' value='" + tablaRespuesta.getIdRespuesta() + "'>");
										out.print("<textarea class='form-control' name='textoRespuesta" + y + "'>" + tablaRespuesta.getTextoRespuesta() + "</textarea><br>");
									}
								}
								out.print("<input type='submit' value='Modificacion' class='btn btn-warning' name='modificacionpregresp' onclick=\"return confirm('¿Estás seguro de modificar la pregunta seleccionada?')\"/>");
								out.print("<hr>");
						
						}
						else{
							//no hay modificación, hay listado normal.
							out.println("<p><input type='radio' name='preguntaDelMod' Style='background:red;'  value='" + tablaPregunta.getIdPregunta() + "'>&nbsp;&nbsp;<b>" 
								+ tablaPregunta.getTextoPregunta() + "</b><br>");
							tipoPregunta = tablaPregunta.getTipoPregunta();
							listaRespuestas = (ArrayList)tablaPregunta.getListaRespuestas();
							for(int y=0;y<listaRespuestas.size();y++) {
								tablaRespuesta = (TablaRespuesta)listaRespuestas.get(y);
								esValidaRespuesta = tablaRespuesta.isValido();
								if (tipoPregunta.equals("Incluyente")){
									if(esValidaRespuesta){
										out.print("<input type='checkbox' name='respuestaCheck" + tablaPregunta.getIdPregunta() + "' value='ok' checked disabled>" + tablaRespuesta.getTextoRespuesta()+ "<br>");
									}else{
										out.print("<input type='checkbox' name='respuestaCheck" + tablaPregunta.getIdPregunta() + "' value='ok' disabled>" + tablaRespuesta.getTextoRespuesta()+ "<br>");
									}						
								
								}else{
									if(esValidaRespuesta){
										out.print("<input type='radio' name='respuestaCheck" + tablaPregunta.getIdPregunta() + "' value='ok' checked disabled>" + tablaRespuesta.getTextoRespuesta()+ "<br>");
									}else{
										out.print("<input type='radio' name='respuestaCheck" + tablaPregunta.getIdPregunta() + "' value='ok' disabled>" + tablaRespuesta.getTextoRespuesta()+ "<br>");
									}
								}
							}							
						}
					}
					
					request.getSession().setAttribute("preguntarespuestaguardada", controlSeleccionado2);	
					//request.getSession().setAttribute("nombreControl"));
					%>	
					<p>			
					<a href="/cuestionariosUDT44/crearcuestionario.do" class="btn btn-primary">Añadir Pregunta</a>					
					<input type="submit" value='Eliminar Pregunta' class="btn btn-primary" name="eliminarpregunta" onclick="return confirm('¿Estás seguro de eliminar la pregunta seleccionada?')"/>
					<input type="submit" value='Modificar Pregunta' class="btn btn-primary" name="modificarpregunta">
					
					
					
					
					<%out.println("</form>");%>
					
				<%}else{
					out.println("<div class='col-6' style='background-color:white;'>");
				}
			
				%>			
			
	</div>