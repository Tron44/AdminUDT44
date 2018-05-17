package com.udimacuestion.struts.action;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.udimacuestion.clases.TablaUsuario;
import com.udimacuestion.dao.DaoException;
import com.udimacuestion.dao.UsuarioDAO;
import com.udimacuestion.struts.form.UsuarioForm;

public class UsuarioAction extends Action {

	private HttpSession sesion = null;
	private UsuarioForm usuarioForm = null;
	private TablaUsuario tablaUsuario = null;
	private UsuarioDAO udao = null;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		sesion = request.getSession();
		String redireccion = controlEntrada(form, request, response);
		return mapping.findForward(redireccion);
	}

	public String controlEntrada(ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		tablaUsuario = new TablaUsuario();
		String alta_usuario = request.getParameter("altausuario");
		String resultado = "";
		if (alta_usuario != null) {
			resultado = altaUsuario(form);
			if (resultado.equals("OK")) {
				return listadoUsuarios();
			} else {
				return "fallo";
			}
		}

		String mod_usuario = request.getParameter("modusuario");
		String resultadomod = "";
		if (mod_usuario != null) {
			resultadomod = modUsuario(form);
			if (resultadomod.equals("OK")) {
				return listadoUsuarios();
			} else {
				return "fallo";
			}
		}

		String del_usuario = request.getParameter("borrarusuario");
		String resultadodel = "";
		if (del_usuario != null) {
			resultadodel = delUsuario(form);
			if (resultadodel.equals("OK")) {
				return listadoUsuarios();
			} else {
				return "fallo";
			}
		}

		String usuSel = request.getParameter("usuSel");
		TablaUsuario tablaUsuario = new TablaUsuario();
		if (usuSel != null) {
			tablaUsuario = buscarUsuario(usuSel);
			if (tablaUsuario == null) {
				return "fallo";
			} else {
				sesion.setAttribute("usuSel", usuSel);
			}
		}

		return listadoUsuarios();
	}

	public String altaUsuario(ActionForm form) {

		usuarioForm = (UsuarioForm) form;
		String userName = usuarioForm.getUserName();
		if (userName.equals("")) {
			// no se graba en base de datos, pero se accede al listado de usuarios
			return "OK";
		}
		tablaUsuario.setUsuario(usuarioForm.getUserName());
		tablaUsuario.setPassword(usuarioForm.getPassword());
		tablaUsuario.setEmail(usuarioForm.getEmail());
		tablaUsuario.setSuperUsuario(usuarioForm.getSuperUsuario());
		
		udao = new UsuarioDAO();
		try {
			udao.altaUsuario(tablaUsuario);
		} catch (DaoException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "OK";

	}

	public String modUsuario(ActionForm form) {

		usuarioForm = (UsuarioForm) form;
		String userName = usuarioForm.getUserName();
		if (userName.equals("")) {
			// no se graba en base de datos, pero se accede al listado de usuarios
			return "OK";
		}
		tablaUsuario.setUsuario(usuarioForm.getUserName());
		tablaUsuario.setPassword(usuarioForm.getPassword());
		tablaUsuario.setEmail(usuarioForm.getEmail());
		tablaUsuario.setSuperUsuario(usuarioForm.getSuperUsuario());
		tablaUsuario.setIdUsuario(usuarioForm.getIdUsuario());
		
		udao = new UsuarioDAO();
		try {
			udao.modUsuario(tablaUsuario);
		} catch (DaoException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "OK";

	}

	public String delUsuario(ActionForm form) {

		usuarioForm = (UsuarioForm) form;

		tablaUsuario.setUsuario(usuarioForm.getUserName());
		tablaUsuario.setPassword(usuarioForm.getPassword());
		tablaUsuario.setEmail(usuarioForm.getEmail());
		tablaUsuario.setSuperUsuario(usuarioForm.getSuperUsuario());
		tablaUsuario.setIdUsuario(usuarioForm.getIdUsuario());

		
		udao = new UsuarioDAO();
		try {
			udao.delUsuario(tablaUsuario);
		} catch (DaoException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "OK";

	}

	public String listadoUsuarios() {
		
		udao = new UsuarioDAO();
		ArrayList<TablaUsuario> listaUsuario = new ArrayList<TablaUsuario>();
		try {
			listaUsuario = udao.listadoUsuarios(0);
			sesion.setAttribute("listaUsuarios", listaUsuario);
			sesion.setAttribute("tablaUsuario", null);
		} catch (DaoException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "hecho";

	}

	public TablaUsuario buscarUsuario(String idUsu) {

		tablaUsuario = new TablaUsuario();
		udao = new UsuarioDAO();
		try {
			tablaUsuario = udao.buscarUsuario(idUsu);			
			sesion.setAttribute("tablaUsuario", tablaUsuario);

		} catch (DaoException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tablaUsuario;

	}

}
