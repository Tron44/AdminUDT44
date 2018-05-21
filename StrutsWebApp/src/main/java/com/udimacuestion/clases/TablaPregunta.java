package com.udimacuestion.clases;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 
 * @author rarranz
 *
 */
public class TablaPregunta implements Serializable {

	private static final long serialVersionUID = 1L;

	// atributos del objeto de pregunta. TablaPregunta
	private int idPregunta = 0;
	private String textoPregunta = null;
	private String tipoPregunta = null;
	private String numeroRespuestas = null;
	private ArrayList<TablaRespuesta> listaRespuestas = null;

	public int getIdPregunta() {
		return idPregunta;
	}

	public void setIdPregunta(int idPregunta) {
		this.idPregunta = idPregunta;
	}

	public ArrayList<TablaRespuesta> getListaRespuestas() {
		return listaRespuestas;
	}

	public void setListaRespuestas(ArrayList<TablaRespuesta> listaRespuestas) {
		this.listaRespuestas = listaRespuestas;
	}

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

	@Override
	public String toString() {
		return "TablaPregunta [idPregunta=" + idPregunta + ", textoPregunta=" + textoPregunta + ", tipoPregunta="
				+ tipoPregunta + ", numeroRespuestas=" + numeroRespuestas + ", listaRespuestas=" + listaRespuestas
				+ "]";
	}

}
