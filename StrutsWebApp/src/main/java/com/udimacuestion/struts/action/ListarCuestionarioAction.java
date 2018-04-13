package com.udimacuestion.struts.action;

import com.udimacuestion.clases.TablaPregunta;
import com.udimacuestion.clases.TablaRespuesta;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.udimacuestion.clases.TablaAsignatura;
import com.udimacuestion.clases.TablaCuestionario;
import com.udimacuestion.clases.TablaGrado;
import com.udimacuestion.dao.CuestionarioDAO;
import com.udimacuestion.dao.CuestionarioDAOGrabar;
import com.udimacuestion.dao.DaoException;

public class ListarCuestionarioAction extends Action {

	HttpSession sesion = null;
	CuestionarioDAO cdao = null;
	CuestionarioDAOGrabar cdaograbar = null;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		sesion = request.getSession();
		String redireccion = controlEntrada(form, request, response);
		return mapping.findForward(redireccion);

	}

	public String controlEntrada(ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String gradoSel = (String) request.getParameter("gradoSel");
		if (gradoSel != null) {
			// si se ha seleccionado un grado, hay que reiniciliar el valor de la
			// asignatura, nombrecontrol, preguntas y respuestas
			// y tb request.getSession().setAttribute("preguntarespuestaguardadaprimera",
			// "si");;
			return gradoSeleccionadoBusquedaAsig(gradoSel);
		}
		String asignaturaSel = (String) request.getParameter("asignaturaSel");
		if (asignaturaSel != null) {
			// si se ha seleccionado un asignatura, hay que reiniciliar el valor
			// nombrecontrol, preguntas y respuestas
			// y tb request.getSession().setAttribute("preguntarespuestaguardadaprimera",
			// "si");;
			return asignaturaSeleccionadaBusquedaControl(asignaturaSel);
		}
		String controlSel = (String) request.getParameter("controlSel");		
		if (controlSel != null) {
			// se ha seleccionado un nombre del control ya se puede habilitar la zona de
			// preguntas
			return nombreControlSeleccionadoBusquedaPregunta(controlSel);
		}

		String eliminarpregunta = (String) request.getParameter("eliminarpregunta");
		
		if (eliminarpregunta != null) {
			// eliminación de pregunta y respuestas asociadas
			String preguntaDel = (String) request.getParameter("preguntaDelMod");		
			return eliminarPregunta(preguntaDel);
		}
		String modificarpregunta = (String) request.getParameter("modificarpregunta");
		if (modificarpregunta != null) {
			// modificación de pregunta y respuestas asociadas
			String preguntaMod = (String) request.getParameter("preguntaDelMod");			
			return modificarPregunta(preguntaMod);
		}

		String modificacionpreguntaresp = (String) request.getParameter("modificacionpregresp");
		
		if (modificacionpreguntaresp != null) {
			// modificación de pregunta y respuestas asociadas
			// String preguntaMod = (String) request.getParameter("preguntaDelMod");
			return modificacionPreguntaRespuesta(request);
		}

		String publicacion = (String) request.getParameter("publicar");
		
		if (publicacion != null) {
			// publicación de cuestionario
			
			return publicarCuestionario((Integer) request.getSession().getAttribute("controlSel"), publicacion);
		}

		String eliminacuestionario = (String) request.getParameter("eliminarcuestionario");
		
		if (eliminacuestionario != null) {
			// publicación de cuestionario
			
			return eliminarCuestionario(eliminacuestionario);
		}

		String modificacuestionario = (String) request.getParameter("modificarcuestionario");
		
		if (modificacuestionario != null) {
			// publicación de cuestionario
			
			String campotextoenvionuevo = (String) request.getParameter("campotexto");
			String controlSeleccionadoCambio = (String) request.getParameter("controlSeleccionado");

			return modificarCuestionario(controlSeleccionadoCambio, campotextoenvionuevo);
		}

		/*
		 * Para crear un cuestionario es preciso primero conseguir el grado y la
		 * asignatura de ese grado para poder almacenar los datos.
		 */

		// estas lineas son las que se ejecutan la primera vez.
		// recuperamos los datos del grado.
		cdao = new CuestionarioDAO();
		ArrayList<TablaGrado> listaGrados = cdao.getGrados();



		if (listaGrados == null) {
			return "fallo";
		} else {
			sesion.setAttribute("listaGrados", listaGrados);
			return "hecho";
		}
	}

	// se ha seleccionado un grado, busqueda asignaturas
	public String gradoSeleccionadoBusquedaAsig(String gradoSel) {
		
		// dejo en sesion el gradoseleccionado y busco de este grado las asignaturas
		sesion.setAttribute("listaAsignaturas", null);
		sesion.setAttribute("listaControles", null);
		sesion.setAttribute("asignaturaSel", null);
		sesion.setAttribute("listaPreguntasRespuestas", null);

		sesion.setAttribute("gradoSel", Integer.parseInt(gradoSel));
		sesion.setAttribute("preguntarespuestaguardada", 0);
		sesion.setAttribute("opcionlistado", "si");

		// recuperamos los datos del grado.
		cdao = new CuestionarioDAO();
		ArrayList<TablaAsignatura> listaAsignaturas = null;
		try {
			listaAsignaturas = cdao.getAsignaturasByGrado(Integer.parseInt(gradoSel),
					((Integer) sesion.getAttribute("superusuario")).intValue(),
					((Integer) sesion.getAttribute("idusuario")).intValue());
		} catch (NumberFormatException | DaoException | SQLException e) {
			e.printStackTrace();
		}
		

		if (listaAsignaturas == null) {
			return "fallo";
		} else {
			sesion.setAttribute("listaAsignaturas", listaAsignaturas);
			return "gradosel";
		}

	}

	// se ha seleccionado una asignatura, se puede proceder a la visualización
	// del campo control para darlo de alta
	public String asignaturaSeleccionadaBusquedaControl(String asignaturaSel) {

		
		// dejo en sesion la asignatura seleccionada y busco de esta asig. los controles
		// asociados
		sesion.setAttribute("asignaturaSel", Integer.parseInt(asignaturaSel));
		sesion.setAttribute("listaControles", null);
		sesion.setAttribute("listaPreguntasRespuestas", null);
		sesion.setAttribute("controlSel", null);
		sesion.setAttribute("preguntaMod", "");

		sesion.setAttribute("opcionlistado", "si");
		// recuperamos la lista de controles.
		cdao = new CuestionarioDAO();
		ArrayList<TablaCuestionario> listaControles = null;
		try {
			listaControles = cdao.getCuestionariosByAsignatura(Integer.parseInt(asignaturaSel));
		} catch (NumberFormatException | DaoException | SQLException e) {
			e.printStackTrace();
		}
		System.out.println("listaGrados en crearcuestionarioaction" + listaControles);

		if (listaControles == null) {
			return "fallo";
		} else {
			sesion.setAttribute("listaControles", listaControles);
			return "asignaturasel";
		}

	}

	// se ha seleccionado un control o cuestionario, se busca las preguntas y
	// respuestas asociadas a este control para su visualización
	public String nombreControlSeleccionadoBusquedaPregunta(String controlSel) {
		sesion.setAttribute("textopregunta", null);
		sesion.setAttribute("tipopregunta", null);
		sesion.setAttribute("numerorespuestas", null);
		sesion.setAttribute("preguntarespuestaguardada", 0);
		sesion.setAttribute("opcionlistado", "si");
		sesion.setAttribute("preguntaMod", "");

		// recojo el codigo del cuestionario y su nombre, conel nombre controlo que
		// desde el listado se pueda acceder al alta de preguntas desde el listado.
		StringTokenizer st = new StringTokenizer(controlSel, "_");
		String numeroControl = st.nextToken();
		String nombreControl = st.nextToken();
		String publicadoControl = st.nextToken();

		sesion.setAttribute("controlSel", Integer.parseInt(numeroControl));
		sesion.setAttribute("nombreControl", nombreControl);

		// consigo el valor de publicación del cuestionario.
		cdao = new CuestionarioDAO();
		int publicado = 0;
		try {
			publicado = cdao.getPublicacionCuestionario(Integer.parseInt(numeroControl));
		} catch (NumberFormatException | DaoException | SQLException e) {
			e.printStackTrace();
		}
		sesion.setAttribute("publicadoControl", publicado + "");

		System.out.println(
				"nombreControlSeleccionadoAltaPregunta en crearcuestionarioactionpublicadoControlpublicadoContro((((((((((((((((((("
						+ controlSel + ":::" + publicadoControl);
		// recuperamos las preguntas y respuestas del cuestionario seleccionado.
		cdao = new CuestionarioDAO();
		ArrayList<TablaPregunta> listaPreguntasRespuestas = null;
		try {
			listaPreguntasRespuestas = cdao.getPreguntasRespuestasByCuestionario(Integer.parseInt(numeroControl));
		} catch (NumberFormatException | DaoException | SQLException e) {
			e.printStackTrace();
		}
		System.out.println("*****************************************");
		System.out.println("listaPreguntasRespuestas en listarrcuestionarioaction" + listaPreguntasRespuestas);
		System.out.println("*****************************************");
		if (listaPreguntasRespuestas == null) {
			return "fallo";
		} else {
			sesion.setAttribute("listaPreguntasRespuestas", listaPreguntasRespuestas);
			return "controlsel";
		}
	}

	// se ha seleccionadoel borrado de una pregunta con sus respuestas asociadas
	public String eliminarPregunta(String preguntaBorr) {
		System.out.println("*********************preguntaBorr********" + preguntaBorr);
		sesion.setAttribute("preguntaMod", "");
		if (preguntaBorr != null) {
			cdaograbar = new CuestionarioDAOGrabar();
			try {
				cdaograbar.eliminarPregunta(preguntaBorr);
			} catch (DaoException | SQLException e) {
				e.printStackTrace();
			}
		}
		return "controlsel";
	}

	// se ha seleccionadoel modficiacion de una pregunta con sus respuestas
	// asociadas
	public String modificarPregunta(String preguntaMod) {
		System.out.println("*********************preguntaMODDDDDDDDDDDDDD********" + preguntaMod);
		if (preguntaMod != null) {
			sesion.setAttribute("preguntaMod", preguntaMod);
		} else {
			sesion.setAttribute("preguntaMod", "");
		}

		return "controlsel";
	}

	public String modificacionPreguntaRespuesta(HttpServletRequest request) {
		ArrayList<TablaRespuesta> listaRespuestas = new ArrayList<TablaRespuesta>();
		TablaRespuesta tablaRespuesta = new TablaRespuesta();
		TablaPregunta tablaPregunta = new TablaPregunta();

		String respuesta = "textoRespuesta";
		String idRespuesta = "idRespuesta";

		String[] options = request.getParameterValues("respuestaCheck");
		List<String> selectionList = null;
		if (options != null) {
			selectionList = Arrays.asList(options);
			for (String o : options) {
				System.out.println("dentro demodificacionPreguntaRespuesta****************oooooooooo" + o);
			}
		}

		String textoPregunta = request.getParameter("textoPregunta");
		String numRespuestas = request.getParameter("numRespuestas");
		int respuestasNum = 0;
		if (numRespuestas != null)
			respuestasNum = Integer.parseInt(numRespuestas);

		System.out.println("dentro demodificacionPreguntaRespuesta****************textoPregunta" + textoPregunta);

		System.out.println("dentro demodificacionPreguntaRespuesta****************numRespuestas" + numRespuestas);

		String preguntaMod = (String) sesion.getAttribute("preguntaMod");
		System.out.println("dentro demodificacionPreguntaRespuesta****************preguntaMod" + preguntaMod);

		String respuestaRec = "";
		String idRespuestaRec = "";
		int j = 0;
		for (int i = 0; i < respuestasNum; i++) {
			tablaRespuesta = new TablaRespuesta();
			// String myCheckBoxValue = request.getParameterValues(checkNamesList[i]);
			// System.out.println("dentro de crearpreguntarespuestarespuestaCheck*" +
			// request.getParameter(chequeo));
			j = i;
			respuestaRec = request.getParameter(respuesta + i);
			idRespuestaRec = request.getParameter(idRespuesta + i);
			if (respuestaRec == null || respuestaRec.equals("")) {
				tablaRespuesta.setTextoRespuesta("texto por defecto");
			} else {
				tablaRespuesta.setTextoRespuesta(respuestaRec);
			}
			tablaRespuesta.setIdRespuesta(Integer.parseInt(idRespuestaRec));

			String comparador = "ok" + j;
			if (selectionList != null && selectionList.contains(comparador)) {
				tablaRespuesta.setValido(true);
			} else {
				tablaRespuesta.setValido(false);
			}
			sesion.setAttribute("tablaRespuesta", tablaRespuesta);
			listaRespuestas.add(tablaRespuesta);

		}
		tablaPregunta.setListaRespuestas(listaRespuestas);
		tablaPregunta.setIdPregunta(Integer.parseInt(preguntaMod));
		tablaPregunta.setTextoPregunta(textoPregunta);

		sesion.setAttribute("listaRespuestas", listaRespuestas);

		for (int i = 0; i < listaRespuestas.size(); i++) {
			System.out.println("dfinalizando*" + listaRespuestas.get(i));
		}

		prepararModificacion(tablaPregunta);

		return "controlsel";

	}

	private int prepararModificacion(TablaPregunta tablaPregunta) {
		// tengo aquí la tabla pregunta y respuesta.
		// despues recojo de la sesión el control, asignatura y grado.
		System.out.println(
				"dentro de prepararModificacion y listo para la modifciar***************************" + tablaPregunta);
		int resultadoIns = 0;
		cdaograbar = new CuestionarioDAOGrabar();
		try {
			resultadoIns = cdaograbar.modificarPreguntaRespuesta(tablaPregunta);
		} catch (DaoException | SQLException e) {
			e.printStackTrace();
		}

		return resultadoIns;
	}

	// se ha seleccionadoel bpublicacion
	public String publicarCuestionario(Integer control, String publicacion) {
		System.out.println("*********************publicarCuestionario********" + control);
		cdaograbar = new CuestionarioDAOGrabar();
		int resultadoPub = 0;
		try {
			resultadoPub = cdaograbar.publicarCuestionario(control, publicacion);
		} catch (DaoException | SQLException e) {
			e.printStackTrace();
		}
		sesion.setAttribute("publicadoControl", resultadoPub + "");
		return "controlsel";
	}

	// se ha seleccionadoel la opción de borrado de cuestionarios
	public String eliminarCuestionario(String cuestionarioBorn) {
		System.out.println("*********************cuestionarioBorn********" + cuestionarioBorn);
		cdaograbar = new CuestionarioDAOGrabar();
		try {
			cdaograbar.eliminarCuestionario(cuestionarioBorn);
		} catch (DaoException | SQLException e) {
			e.printStackTrace();
		}
		sesion.setAttribute("controlSel", null);
		sesion.setAttribute("asignaturaSel", null);
		sesion.setAttribute("listaAsignaturas", null);
		sesion.setAttribute("listaControles", null);
		sesion.setAttribute("asignaturaSel", null);
		sesion.setAttribute("listaPreguntasRespuestas", null);
		sesion.setAttribute("gradoSel", null);
		return "gradosel";
	}

	// se ha seleccionadoel la opción de borrado de cuestionarios
	public String modificarCuestionario(String cuestionarioMod, String campotextonuevo) {
		System.out.println("*********************cuestionarioMod********" + cuestionarioMod);

		cdaograbar = new CuestionarioDAOGrabar();
		try {
			cdaograbar.modificarCuestionario(cuestionarioMod, campotextonuevo);
		} catch (DaoException | SQLException e) {
			e.printStackTrace();
		}
		sesion.setAttribute("controlSel", null);
		sesion.setAttribute("asignaturaSel", null);
		sesion.setAttribute("listaAsignaturas", null);
		sesion.setAttribute("listaControles", null);
		sesion.setAttribute("asignaturaSel", null);
		sesion.setAttribute("listaPreguntasRespuestas", null);
		sesion.setAttribute("gradoSel", null);
		return "gradosel";
	}

}
