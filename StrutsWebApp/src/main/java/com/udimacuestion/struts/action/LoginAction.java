package com.udimacuestion.struts.action;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.udimacuestion.clases.TablaLogin;
import com.udimacuestion.clases.TablaUsuario;
import com.udimacuestion.dao.DaoException;
import com.udimacuestion.dao.LoginDAO;
import com.udimacuestion.struts.form.LoginForm;

public class LoginAction extends Action {

	private HttpSession sesion = null;
	private LoginForm loginForm = null;
	private TablaLogin tablalogin = null;
	private LoginDAO ldao = null;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		sesion = request.getSession(true);
		loginForm = (LoginForm) form;
		tablalogin = new TablaLogin();
		tablalogin.setUsuario(loginForm.getUserName());
		tablalogin.setPassword(loginForm.getPassword());
		TablaUsuario resultadoLogin = null;

		String logoff = (String) request.getParameter("logoff");
		if (logoff != null) {
			sesion.invalidate();
			return mapping.findForward("fallo");
		}

		if (tablalogin.getUsuario() == null || tablalogin.getPassword() == null) {
			sesion.invalidate();
			return mapping.findForward("fallo");
		} else {
			ldao = new LoginDAO();
			resultadoLogin = logarse(tablalogin);
			if (resultadoLogin == null) {
				sesion.invalidate();
				return mapping.findForward("fallo");
			} else {
				sesion.setAttribute("superusuario", resultadoLogin.getSuperUsuario());
				sesion.setAttribute("idusuario", resultadoLogin.getIdUsuario());
				sesion.setAttribute("usuario", resultadoLogin.getUsuario());
				return mapping.findForward("hecho");
			}
		}
	}

	public TablaUsuario logarse(TablaLogin tablaLogin) throws DaoException, SQLException {
		return ldao.getLogin(tablalogin);
	}

}
