package com.udimacuestion.struts.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.udimacuestion.clases.TablaPregunta;
import com.udimacuestion.clases.TablaRespuesta;
import com.udimacuestion.dao.CuestionarioDAOGrabar;
import com.udimacuestion.dao.DaoException;
import com.udimacuestion.struts.form.CrearPreguntaRespuestaForm;

public class CrearPreguntaRespuestaAction extends Action {
	HttpSession sesion = null;
	private CrearPreguntaRespuestaForm crearPreguntaRespuestaForm = null;
	private TablaPregunta tablaPregunta = null;
	private TablaRespuesta tablaRespuesta = null;
	private CuestionarioDAOGrabar cuestionarioDAOGrabar = null;

	// CrearCuestionarioForm crearcuestionarioForm = null;
	// TablaCuestionario tablacuestionario = null;
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {		
		sesion = request.getSession();
		crearPreguntaRespuestaForm = (CrearPreguntaRespuestaForm) form;

		String respuestaHecha = request.getParameter("textoRespuesta0");
		if (respuestaHecha != null) {
			// se ha dado a guardar las respuestas de la pregunta			
			return mapping.findForward(altaRespuestasDePregunta(request));
		} else {
			tablaPregunta = new TablaPregunta();
			tablaPregunta.setTextoPregunta(crearPreguntaRespuestaForm.getTextoPregunta());
			tablaPregunta.setTipoPregunta(crearPreguntaRespuestaForm.getTipoPregunta());
			tablaPregunta.setNumeroRespuestas(crearPreguntaRespuestaForm.getNumeroRespuestas());

			if (tablaPregunta.getTextoPregunta() == null) {
				return mapping.findForward("fallo");
				// return mapping.findForward("hecho");
			} else {
				if (tablaPregunta.getTextoPregunta().equals("")) {
					sesion.setAttribute("textopregunta", "texto_pregunta_defecto");
					tablaPregunta.setTextoPregunta("texto_pregunta_defecto");
				} else {
					sesion.setAttribute("textopregunta", tablaPregunta.getTextoPregunta());
				}

				sesion.setAttribute("tipopregunta", tablaPregunta.getTipoPregunta());
				sesion.setAttribute("numerorespuestas", tablaPregunta.getNumeroRespuestas());
				return mapping.findForward("hecho");
			}

		}

	}

	public String altaRespuestasDePregunta(HttpServletRequest request) {
		ArrayList<TablaRespuesta> listaRespuestas = new ArrayList<TablaRespuesta>();
		tablaRespuesta = new TablaRespuesta();
		String numRespuestas = (String) sesion.getAttribute("numerorespuestas");
		int numeroRespuestas = 0;
		if (numRespuestas != null)
			numeroRespuestas = Integer.parseInt(numRespuestas);
		
		String respuesta = "textoRespuesta";
		String[] options = request.getParameterValues("respuestaCheck");
		List<String> selectionList = null;
		if (options != null) {
			selectionList = Arrays.asList(options);
			for (String o : options) {
				System.out.println("dentro de crearpreguntarespuestarespuesta*ooooooooooooo" + o);
			}
		}
		int contadorRespuestas = 0;
		String respuestaRec = "";
		int j = 0;
		for (int i = 0; i < numeroRespuestas; i++) {
			tablaRespuesta = new TablaRespuesta();
			// String myCheckBoxValue = request.getParameterValues(checkNamesList[i]);
			// 
			// request.getParameter(chequeo));
			j = i;
			respuestaRec = request.getParameter(respuesta + i);

			// NO HAGO el control de contador de respuestas, grabo y punto en la base de
			// datos. un blanco por defecto si no viene texto.
			// if (respuestaRec !=null && !respuestaRec.equals("")) contadorRespuestas++;
			contadorRespuestas++;
			if (respuestaRec == null || respuestaRec.equals("")) {
				tablaRespuesta.setTextoRespuesta("texto por defecto");
			} else {
				tablaRespuesta.setTextoRespuesta(respuestaRec);
			}

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
		tablaPregunta.setNumeroRespuestas(numeroRespuestas + "");

		sesion.setAttribute("listaRespuestas", listaRespuestas);

		for (int i = 0; i < listaRespuestas.size(); i++) {
			System.out.println("dfinalizando*" + listaRespuestas.get(i));
		}
		
		if (contadorRespuestas == numeroRespuestas) {
			// sesion.setAttribute("preguntarespuestaguardada", 0);
			sesion.setAttribute("textopregunta", null);
			sesion.setAttribute("tipopregunta", "1");
			sesion.setAttribute("numerorespuestas", "1");
			sesion.setAttribute("listaRespuestas", null);
			Integer primeraVezInsert = (Integer) sesion.getAttribute("preguntarespuestaguardada");
			int resultadoIns = prepararInsercion(primeraVezInsert);
			if (resultadoIns == 0) {
				sesion.setAttribute("preguntarespuestaguardada", 0);
			} else {
				// se ha realizado la inserción. dejo en preguntarespuestaguardada el id del
				// cuestionario para las siguientes inserciones.
				sesion.setAttribute("preguntarespuestaguardada", resultadoIns);
			}
			return "hecho";
		} else {
			sesion.setAttribute("preguntarespuestaguardada", 0);
			return "hecho";
		}

	}

	private int prepararInsercion(Integer primeraVezInsert) {
		// tengo aquí la tabla pregunta y respuesta.
		// despues recojo de la sesión el control, asignatura y grado.		
		int resultadoIns = 0;

		if (primeraVezInsert != null && primeraVezInsert.intValue() == 0) {

			Integer gradoIns = (Integer) sesion.getAttribute("gradoSel");
			Integer asignaturaIns = (Integer) sesion.getAttribute("asignaturaSel");
			String nombreControlIns = (String) sesion.getAttribute("nombreControl");
			String descControlIns = (String) sesion.getAttribute("descControl");
			TablaPregunta tablaPreguntaIns = tablaPregunta;
			cuestionarioDAOGrabar = new CuestionarioDAOGrabar();
			try {
				resultadoIns = cuestionarioDAOGrabar.insertarCuestionario(gradoIns.toString(), asignaturaIns.toString(),
						nombreControlIns, descControlIns, tablaPreguntaIns);
			} catch (DaoException | SQLException e) {
				e.printStackTrace();
			}

		} else {
			// segunda inserción sobre el cuestionario o control
			TablaPregunta tablaPreguntaIns = tablaPregunta;
			cuestionarioDAOGrabar = new CuestionarioDAOGrabar();
			try {
				resultadoIns = cuestionarioDAOGrabar.insertarPreguntaRespuesta(primeraVezInsert, tablaPreguntaIns);
			} catch (DaoException | SQLException e) {
				e.printStackTrace();
			}

		}

		return resultadoIns;
	}

}
