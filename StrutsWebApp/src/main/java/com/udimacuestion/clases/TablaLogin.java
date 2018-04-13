package com.udimacuestion.clases;

import java.io.Serializable;

/**
 * 
 * @author rarranz
 *
 */
public class TablaLogin implements Serializable {

	private static final long serialVersionUID = 1L;

	// atributos del objeto de Login. TablaLogin. control de entrada de acceso
	private String usuario = null;
	private String password = null;

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "TablaLogin [usuario=" + usuario + ", password=" + password + "]";
	}

}
