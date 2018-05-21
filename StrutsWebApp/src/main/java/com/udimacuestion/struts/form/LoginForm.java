package com.udimacuestion.struts.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * @author rarranz
 *
 */
public class LoginForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	// identificadores del tratamiento de formulario para la gesti�n de login
	private String userName = null;
	private String password = null;

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
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.password = null;
	}

}
