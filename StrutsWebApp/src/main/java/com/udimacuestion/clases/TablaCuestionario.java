package com.udimacuestion.clases;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 
 * @author rarranz
 *
 */
public class TablaCuestionario implements Serializable {

	private static final long serialVersionUID = 1L;

	// atributos del objeto de cuestionario. TablaCuestionario
	private String nombreCuestionario = null;
	private String descCuestionario = null;
	private int idCuestionario = 0;
	private int publicacion = 0;
	private int idAsignatura = 0;

	public String getNombreCuestionario() {
		return nombreCuestionario;
	}

	public void setNombreCuestionario(String nombreCuestionario) {
		this.nombreCuestionario = nombreCuestionario;
	}
	
	public String getDescCuestionario() {
		return descCuestionario;
	}

	public void setDescCuestionario(String descCuestionario) {
		this.descCuestionario = descCuestionario;
	}

	public int getIdCuestionario() {
		return idCuestionario;
	}

	public void setIdCuestionario(int idCuestionario) {
		this.idCuestionario = idCuestionario;
	}

	public int getPublicacion() {
		return publicacion;
	}

	public void setPublicacion(int publicacion) {
		this.publicacion = publicacion;
	}

	public int getIdAsignatura() {
		return idAsignatura;
	}

	public void setIdAsignatura(int idAsignatura) {
		this.idAsignatura = idAsignatura;
	}

	private ArrayList<TablaPregunta> listaPreguntas = null;

	public ArrayList<TablaPregunta> getListaPreguntas() {
		return listaPreguntas;
	}

	public void setListaPreguntas(ArrayList<TablaPregunta> listaPreguntas) {
		this.listaPreguntas = listaPreguntas;
	}

	@Override
	public String toString() {
		return "TablaCuestionario [nombreCuestionario=" + nombreCuestionario + ", idCuestionario=" + idCuestionario
				+ ", publicacion=" + publicacion + ", idAsignatura=" + idAsignatura + ", listaPreguntas="
				+ listaPreguntas + "]";
	}

}
