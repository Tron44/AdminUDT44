package com.udimacuestion.clases;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 
 * @author rarranz
 *
 */
public class TablaGrado implements Serializable {

	private static final long serialVersionUID = 1L;

	// atributos del objeto de grado. TablaGrado
	private int idGrado = 0;
	private String nombreGrado = null;
	private ArrayList<TablaAsignatura> listaAsignaturas = null;

	public ArrayList<TablaAsignatura> getListaAsignaturas() {
		return listaAsignaturas;
	}

	public void setListaAsignaturas(ArrayList<TablaAsignatura> listaAsignaturas) {
		this.listaAsignaturas = listaAsignaturas;
	}

	public String getNombreGrado() {
		return nombreGrado;
	}

	public int getIdGrado() {
		return idGrado;
	}

	public void setIdGrado(int idGrado) {
		this.idGrado = idGrado;
	}

	public void setNombreGrado(String nombreGrado) {
		this.nombreGrado = nombreGrado;
	}

	@Override
	public String toString() {
		return "TablaGrado [nombreGrado=" + nombreGrado + "]";
	}

}
