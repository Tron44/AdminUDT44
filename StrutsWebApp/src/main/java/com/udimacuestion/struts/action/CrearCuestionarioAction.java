package com.udimacuestion.struts.action;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.udimacuestion.clases.TablaAsignatura;
import com.udimacuestion.clases.TablaGrado;
import com.udimacuestion.dao.CuestionarioDAO;
import com.udimacuestion.dao.DaoException;

public class CrearCuestionarioAction extends Action {
	HttpSession sesion = null;
	CuestionarioDAO cdao = null;

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
			// y tb request.getSession().setAttribute("preguntarespuestaguardadaprimera", "si");;
			return gradoSeleccionadoBusquedaAsig(gradoSel);
		}
		String asignaturaSel = (String) request.getParameter("asignaturaSel");
		if (asignaturaSel != null) {
			// si se ha seleccionado un asignatura, hay que reiniciliar el valor
			// nombrecontrol, preguntas y respuestas
			// y tb request.getSession().setAttribute("preguntarespuestaguardadaprimera", "si");;
			return asignaturaSeleccionadaAltaControl(asignaturaSel);
		}
		String nombreControl = (String) request.getParameter("nombreControl");
		String descControl = (String) request.getParameter("descControl");
		if (nombreControl != null) {
			// se ha seleccionado un nombre del control ya se puede habilitar la zona de
			// preguntas
			return nombreControlSeleccionadoAltaPregunta(nombreControl, descControl);
		}
		
		//opción de añadido pregunta desde el listado.
		String addpreg = (String) request.getParameter("addpreg");
		if (addpreg != null) {
			cdao = new CuestionarioDAO();
			ArrayList<TablaGrado> listaGrados = cdao.getGrados();		

			if (listaGrados == null) {
				return "fallo";
			} else {
				sesion.setAttribute("listaGrados", listaGrados);
				return "hecho";
			}
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
			sesion.setAttribute("listaAsignaturas", null);
			sesion.setAttribute("listaControles", null);
			sesion.setAttribute("asignaturaSel", null);
			sesion.setAttribute("listaPreguntasRespuestas", null);
			
			Integer gradoSeleccionado = (Integer)request.getSession().getAttribute("gradoSel");
			if (gradoSeleccionado !=null) gradoSeleccionadoBusquedaAsig(gradoSeleccionado+"");
			return "hecho";
		}

	}

	// se ha seleccionado un grado, busqueda asignaturas
	public String gradoSeleccionadoBusquedaAsig(String gradoSel) {		
		// dejo en sesion el gradoseleccionado y busco de este grado las asignaturas
		sesion.setAttribute("listaAsignaturas", null);
		sesion.setAttribute("asignaturaSel", null);
		sesion.setAttribute("gradoSel", Integer.parseInt(gradoSel));
		sesion.setAttribute("preguntarespuestaguardada", 0);
		sesion.setAttribute("opcionlistado", "no");
		// recuperamos los datos del grado.
		cdao = new CuestionarioDAO();
		ArrayList<TablaAsignatura> listaAsignaturas = null;
		try {
			listaAsignaturas = cdao.getAsignaturasByGrado(Integer.parseInt(gradoSel),1);
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
	public String asignaturaSeleccionadaAltaControl(String asignaturaSel) {		
		sesion.setAttribute("nombreControl", null);
		sesion.setAttribute("preguntarespuestaguardada", 0);
		// dejo en sesion el gradoseleccionado y busco de este grado las asignaturas
		sesion.setAttribute("asignaturaSel", Integer.parseInt(asignaturaSel));

		return "asignaturasel";
	}

	// se ha seleccionado una asignatura, se puede proceder a la visualización
	// del campo control para darlo de alta
	public String nombreControlSeleccionadoAltaPregunta(String nombreControl, String descControl) {
		sesion.setAttribute("textopregunta", null);
		sesion.setAttribute("tipopregunta", null);
		sesion.setAttribute("numerorespuestas", null);
		sesion.setAttribute("preguntarespuestaguardada", 0);
		
		// dejo en sesion el gradoseleccionado y busco de este grado las asignaturas		
		sesion.setAttribute("nombreControl", nombreControl);
		sesion.setAttribute("descControl", descControl);
		return "nombrecontrolsel";
	}

}
