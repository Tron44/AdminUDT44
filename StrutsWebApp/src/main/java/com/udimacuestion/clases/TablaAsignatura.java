package com.udimacuestion.clases;

import java.io.Serializable;

/**
 * 
 * @author rarranz
 *
 */
public class TablaAsignatura implements Serializable {

	private static final long serialVersionUID = 1L;

	// atributos del objeto de asignatura. TablaAsignatura
	private int idAsignatura = 0;
	private String nombreAsignatura = null;
	private String codigoAsignatura = null;
	private int idGrado = 0;

	public int getIdAsignatura() {
		return idAsignatura;
	}

	public void setIdAsignatura(int idAsignatura) {
		this.idAsignatura = idAsignatura;
	}

	public String getNombreAsignatura() {
		return nombreAsignatura;
	}

	public void setNombreAsignatura(String nombreAsignatura) {
		this.nombreAsignatura = nombreAsignatura;
	}

	public String getCodigoAsignatura() {
		return codigoAsignatura;
	}

	public void setCodigoAsignatura(String codigoAsignatura) {
		this.codigoAsignatura = codigoAsignatura;
	}

	public int getIdGrado() {
		return idGrado;
	}

	public void setIdGrado(int idGrado) {
		this.idGrado = idGrado;
	}

	@Override
	public String toString() {
		return "TablaAsignatura [idAsignatura=" + idAsignatura + ", nombreAsignatura=" + nombreAsignatura
				+ ", codigoAsignatura=" + codigoAsignatura + ", idGrado=" + idGrado + "]";
	}

}
