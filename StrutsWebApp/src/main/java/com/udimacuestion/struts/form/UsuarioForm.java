package com.udimacuestion.struts.form;

import org.apache.struts.action.ActionForm;

/**
 * 
 * @author rarranz
 *
 */
public class UsuarioForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	// identificadores del tratamiento de formulario para usuarios
	private String userName = null;
	private String password = null;
	private String email = "";
	private int superUsuario = 0;
	private int idUsuario = 0;

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
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

	public void setSuperUsuario(int superUser) {
		this.superUsuario = superUser;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UsuarioForm [userName=" + userName + ", password=" + password + ", email=" + email + ", superUsuario="
				+ superUsuario + ", idUsuario=" + idUsuario + "]";
	}

}
