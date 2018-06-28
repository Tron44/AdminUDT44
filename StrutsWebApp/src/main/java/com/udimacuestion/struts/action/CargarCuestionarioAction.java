package com.udimacuestion.struts.action;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.udimacuestion.clases.TablaPregunta;
import com.udimacuestion.clases.TablaRespuesta;
import com.udimacuestion.dao.CuestionarioDAOGrabar;
import com.udimacuestion.dao.DaoException;
import com.udimacuestion.struts.form.CargarCuestionarioForm;

public class CargarCuestionarioAction extends Action {

	HttpSession sesion = null;
	CuestionarioDAOGrabar cdaog = null;
	private TablaPregunta tablaPregunta = null;
	private TablaRespuesta tablaRespuesta = null;
	private ArrayList<TablaRespuesta> listaRespuestas = null;
	private int contadorValidos = 0;
	private boolean primeraVez = true;
	int contadorRespuestas = 0;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		sesion = request.getSession();
		request.setCharacterEncoding("UTF-8");

		if (request.getParameter("carga") != null) {
			sesion.setAttribute("procesado", "");
			return mapping.findForward("hecho");
		} else {
			return mapping.findForward(tratarFichero(form, request));
		}
	}

	

	public String tratarFichero(ActionForm form, HttpServletRequest request) throws Exception {

		primeraVez = true;
		Integer gradoIns = (Integer) sesion.getAttribute("gradoSel");
		Integer asignaturaIns = (Integer) sesion.getAttribute("asignaturaSel");
		String nombreControlIns = (String) sesion.getAttribute("nombreControl");
		String descControlIns = (String) sesion.getAttribute("descControl");
		Integer idUsuario = (Integer)sesion.getAttribute("idusuario");		
		int idCuestionario = 0;
		if (form instanceof CargarCuestionarioForm) {
			CargarCuestionarioForm theForm = (CargarCuestionarioForm) form;
			
			// mostramos los parametros del fichero
			FormFile theFile = theForm.getTheFile();
			int fileSize = theFile.getFileSize();
			byte[] recuperadoFic = theFile.getFileData();
			
			//theFile.setContentType("text/html; charset=UTF-8");
			FileOutputStream out = new FileOutputStream("archivo.txt");
			
			out.write(recuperadoFic);
			out.flush();
			out.close();
			
			String cadena;
		      FileReader f = new FileReader("archivo.txt");
		      BufferedReader b = new BufferedReader(f);
		      while((cadena = b.readLine())!=null) {
		    	  cadena.getBytes("UTF-8");		         
		      }
		      b.close();	
			
			String data = "";
			try {
				// guarda los datos del fichero
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				InputStream stream = theFile.getInputStream();
				String ss = IOUtils.toString(stream, "UTF-8");
				//control de almacenar la última pregunta
				ss = ss + "//: 0000000  name: 00 Â¿00?\n" + 
						"::00 00::[html]00{\n" + 
						"	~00\n" + 						
						"}";

				// solo si el archivo es de menos de 4MB
				if (fileSize < (4 * 1024000)) {
					byte[] buffer = new byte[8192];
					int bytesLeidos = 0;
					while ((bytesLeidos = stream.read(buffer, 0, 8192)) != -1) {
						baos.write(buffer, 0, bytesLeidos);
					}
					
					data = new String(baos.toByteArray(), "UTF-8");

					// antes de tratar la linea de las preguntas y respuestas, grabo el cuestionario
					cdaog = new CuestionarioDAOGrabar();
					idCuestionario = cdaog.insertarSoloCuestionario(gradoIns + "", asignaturaIns + "",
							nombreControlIns, idUsuario.intValue(), descControlIns);					
					Scanner scanner = new Scanner(ss);
					while (scanner.hasNextLine()) {
						String line = scanner.nextLine();
						tratarlinea(line, idCuestionario);
					}
					scanner.close();
				} else {
					data = new String(
							"Fichero de mas de 4MB: no pudo gestionarse. Tamano del fichero: " + fileSize + "  bytes.");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		sesion.setAttribute("procesado", "pok");		
		return "hecho";
	}

	public void tratarlinea(String linea, int idCuestionario) {
		String textoPregunta = "";
		String textoRespuesta = "";
		boolean valido = false;

		try {

			// llegados a este punto se ha grabado el cuestionario, ahora se grabarÃ¡n las
			// preguntas y respuestas de ese cuestionario
			if (linea == null || linea.equals("")) {
				//linea nula, no se trata 
			} else {
				if (linea.substring(0, 1).equals("{")) {
					//se ha recuperado {, no se trata
				} else {
					if (linea.substring(0, 1).equals("}")) {
						//se ha recuperado }, no se trata

					} else {
						if (linea.substring(0, 2).equals("$C")) {
							//se ha recuperado categoria, no se trata

						} else {
							if (linea.substring(0, 2).equals("//")) {
								//se ha recuperado comentario, no se trata

							} else {
								if (linea.substring(0, 2).equals("::")) {									
									if (!primeraVez) {
										// grabo en base de datos la pregunta con sus respuestas.
										cdaog = new CuestionarioDAOGrabar();
										try {
											cdaog.insertarPreguntaRespuesta(idCuestionario, tablaPregunta);
										} catch (DaoException | SQLException e) {
											e.printStackTrace();
										}

									} else {
										primeraVez = false;
									}
									// 
									//con la siguiente sentencia se elimina la posibilidad de nexttoken ::
									linea = linea.replace("\\:", "¡");									
									StringTokenizer st = new StringTokenizer(linea, "::");									
									String pregunta = st.nextToken().trim();
									//control de si el ultimo caracter de la pregunta es ;
									//se reemplaza por : nuevamente
									pregunta = pregunta.replace("¡", "\\:");									
									if (pregunta.startsWith(":"))
										pregunta = pregunta.substring(1);
									if (pregunta.endsWith("\\"))
										pregunta = pregunta.substring(0, pregunta.length() - 1) + ":";
									if (pregunta.endsWith("{"))
										pregunta = pregunta.substring(0, pregunta.length() - 1);
									// pregunta = pregunta.replaceAll("\\:", ":");									
									// ::[html]<p>Â¿Q...
									// ::[moodle]<p>Â¿Q
									// ::<p>Â¿Q
									// también hay que tratar la \: que es : y que puede ir lo siguiente \:::
									textoPregunta = convertFromUTF8(pregunta.substring(0));									
									String tp = textoPregunta;
									if (textoPregunta.contains("[html]"))
										textoPregunta = tp.trim().substring(6);
									if (textoPregunta.contains("[moodle]"))
										textoPregunta = tp.trim().substring(8);
									tablaPregunta = new TablaPregunta();
									tablaPregunta.setTextoPregunta(textoPregunta);
									
									listaRespuestas = new ArrayList<TablaRespuesta>();
									contadorValidos = 0;
									contadorRespuestas = 0;

								} else {
									if ((convertFromUTF8(linea.trim()).substring(0, 1).equals("="))
											|| (convertFromUTF8(linea.trim()).substring(0, 1).equals("~"))) {
										// =[moodle]Su contenido...
										// =[html]Su contenido...
										// ~Su contenido con virgulilla
										textoRespuesta = convertFromUTF8(linea.trim());										
										contadorRespuestas++;
										if (textoRespuesta.substring(0, 1).equals("=")) {
											valido = true;
											contadorValidos++;											
										}
										textoRespuesta = textoRespuesta.substring(1);
										
										if (textoRespuesta.substring(0, 1).equals("%")) {
											//se ha encontrado porcentaje de pregunta, no se trata
											StringTokenizer stporcentaje = new StringTokenizer(textoRespuesta, "%");
											stporcentaje.nextToken();
											textoRespuesta = stporcentaje.nextToken();
										}
																				
										if (textoRespuesta.contains("[html]"))
											textoRespuesta = textoRespuesta.substring(6);
										if (textoRespuesta.contains("[moodle]"))
											textoRespuesta = textoRespuesta.substring(8);
										
										if (textoRespuesta.substring(0, 1).equals("%")) {
											//se ha encontrado porcentaje de pregunta, no se trata
											StringTokenizer stporcentaje = new StringTokenizer(textoRespuesta, "%");
											stporcentaje.nextToken();
											textoRespuesta = stporcentaje.nextToken();
										}										
										
										tablaRespuesta = new TablaRespuesta();										
										tablaRespuesta.setTextoRespuesta(textoRespuesta);

										tablaRespuesta.setValido(valido);
										listaRespuestas.add(tablaRespuesta);
										if (contadorValidos > 1) {
											tablaPregunta.setTipoPregunta("1");
										} else {
											tablaPregunta.setTipoPregunta("0");
										}
										tablaPregunta.setNumeroRespuestas("" + contadorRespuestas);
										tablaPregunta.setListaRespuestas(listaRespuestas);
									} else {

									}
								}
							}
						}
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}	

	//este método ya no es necesario, se deja en comentario por dejar constancia de posibles problemas de encoding
	//de preguntas y respuestas. en este caso ya no se utiliza.
	public String convertFromUTF8(String s) {
		String out = s;
		//método no 
		/*try {
			out = new String(s.getBytes("ISO-8859-1"), "UTF-8");
		} catch (java.io.UnsupportedEncodingException e) {
			return null;
		}*/
		return out;
	}
}
