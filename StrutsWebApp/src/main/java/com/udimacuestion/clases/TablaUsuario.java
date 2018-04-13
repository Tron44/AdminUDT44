package com.udimacuestion.clases;

import java.io.Serializable;

/**
 * 
 * @author rarranz
 *
 */
public class TablaUsuario implements Serializable {

	private static final long serialVersionUID = 1L;

	// atributos del objeto de usuario. TablaUsuario
	private String usuario = "";
	private String password = "";
	private int superUsuario = 0;
	private String email = "";
	private int idUsuario = 0;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getSuperUsuario() {
		return superUsuario;
	}

	public void setSuperUsuario(int superUsuario) {
		this.superUsuario = superUsuario;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	@Override
	public String toString() {
		return "TablaUsuario [usuario=" + usuario + ", password=" + password + ", superUsuario=" + superUsuario
				+ ", email=" + email + ", idUsuario=" + idUsuario + "]";
	}

}
