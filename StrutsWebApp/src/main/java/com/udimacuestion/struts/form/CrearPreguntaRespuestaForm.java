package com.udimacuestion.struts.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * @author rarranz
 *
 */
public class CrearPreguntaRespuestaForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	// identificadores del tratamiento de formulario para la gestión de preguntas y
	// respuestas exclusivamente
	private String textoPregunta = null;
	private String tipoPregunta = null;
	private String numeroRespuestas = null;
	private String textoRespuesta = null;
	private String chequeo = null;

	public String getTextoPregunta() {
		return textoPregunta;
	}

	public void setTextoPregunta(String textoPregunta) {
		this.textoPregunta = textoPregunta;
	}

	public String getTipoPregunta() {
		return tipoPregunta;
	}

	public void setTipoPregunta(String tipoPregunta) {
		this.tipoPregunta = tipoPregunta;
	}

	public String getNumeroRespuestas() {
		return numeroRespuestas;
	}

	public void setNumeroRespuestas(String numeroRespuestas) {
		this.numeroRespuestas = numeroRespuestas;
	}

	public String getTextoRespuesta() {
		return textoRespuesta;
	}

	public void setTextoRespuesta(String textoRespuesta) {
		this.textoRespuesta = textoRespuesta;
	}

	public String getChequeo() {
		return chequeo;
	}

	public void setChequeo(String chequeo) {
		this.chequeo = chequeo;
	}

	@Override
	public String toString() {
		return "CrearPreguntaRespuestaForm [textoPregunta=" + textoPregunta + ", tipoPregunta=" + tipoPregunta
				+ ", numeroRespuestas=" + numeroRespuestas + "]";
	}

	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {

	}

}
