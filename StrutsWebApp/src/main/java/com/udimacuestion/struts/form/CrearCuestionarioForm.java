package com.udimacuestion.struts.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * @author rarranz
 *
 */
public class CrearCuestionarioForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	
	// identificadores del tratamiento de formulario para la gestión de cuestionarios 
	private String nombreCuestionario = null;
	private String nombreGrado = null;

	public String getNombreGrado() {
		return nombreGrado;
	}

	public void setNombreGrado(String nombreGrado) {
		this.nombreGrado = nombreGrado;
	}

	public String getNombreCuestionario() {
		return nombreCuestionario;
	}

	public void setNombreCuestionario(String nombreCuestionario) {
		this.nombreCuestionario = nombreCuestionario;
	}

	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {

	}

}
