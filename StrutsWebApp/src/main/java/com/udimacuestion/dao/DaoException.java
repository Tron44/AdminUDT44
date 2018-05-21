package com.udimacuestion.dao;

/**
 * clase especifica de excepcion de acceso a datos.
 * 
 * @author rarranz
 *
 */
public class DaoException extends Exception {

	private static final long serialVersionUID = -8362237116917104359L;

	private String nomdao = null;
	private String nommetodo = null;
	private String nominstru = null;
	private String nomvalor = null;
	private String mensaje = null;
	private int numero = 0;

	/**
	 * Constructor de la excepcion
	 * 
	 * @param vnumero
	 * @param vnomdao
	 * @param vnommetodo
	 * @param vnominstru
	 * @param vnomvalor
	 * @param vmensaje
	 */
	public DaoException(int vnumero, String vnomdao, String vnommetodo, String vnominstru, String vnomvalor,
			String vmensaje) {
		nomdao = vnomdao;
		nommetodo = vnommetodo;
		nominstru = vnominstru;
		nomvalor = vnomvalor;
		mensaje = vmensaje;
		numero = vnumero;
	}

	/**
	 * método de error, formato string
	 * 
	 * @return
	 */
	public String getError() {
		return numero + ", " + nomdao + ", " + nommetodo + ", " + nominstru + ", " + nomvalor + ", " + mensaje;
	}
}