package com.udimacuestion.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.udimacuestion.clases.TablaLogin;
import com.udimacuestion.clases.TablaUsuario;

/**
 * DAO de gestión del login a la aplicación
 * 
 * @author rarranz
 *
 */
public class LoginDAO {
	private static final String NOMBREDAO = "LoginDAO ";
	private Connection conexion = null;

	public LoginDAO() {
		conexion = ConexionBd.getConnection("ias");
	}

	/**
	 * método para validar el login del usuario.
	 * 
	 * @param tablalogin
	 * @param tipo
	 * @return
	 * @throws DaoException
	 * @throws SQLException
	 */
	public TablaUsuario getLogin(TablaLogin tablalogin) throws DaoException, SQLException {

		// Si no hemos obtenido la conexion devolvemos una excepcion (DaoExcepcion)
		if (conexion == null) {
			throw new DaoException(10, NOMBREDAO, "getLogin", "getConexion", "conn",
					"No ha podido obtener conexion con la base de Datos");
		}

		PreparedStatement stmt = null;
		TablaUsuario tablaUsuario = null;
		try {
			stmt = conexion.prepareStatement(
					"SELECT idUsuario, nombreUsuario, emailUsuario, superUsuario FROM cuestionarioudima.usuario"
							+ " where nombreUsuario ='" + tablalogin.getUsuario() + "' and passwordUsuario='"
							+ tablalogin.getPassword() + "'");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				tablaUsuario = new TablaUsuario();
				tablaUsuario.setIdUsuario(rs.getInt("idUsuario"));
				tablaUsuario.setUsuario(rs.getString("nombreUsuario"));
				tablaUsuario.setPassword("**");
				tablaUsuario.setEmail(rs.getString("emailUsuario"));
				tablaUsuario.setSuperUsuario(rs.getInt("superUsuario"));
			}

		} catch (SQLException sqle) {
			throw new DaoException(20, NOMBREDAO, "getLogin", "getLogin", "conn", "Error al ejecutar la sentencia sql");
		} finally {
			// Cerramos la PreparedStatement
			if (stmt != null) {
				try {
					stmt.close();
					stmt = null;
				} catch (Exception ex2) {
					ex2.printStackTrace();
				}
			}
			// Cerramos la conexión con la base de datos
			if (conexion != null) {
				try {
					conexion.close();
					conexion = null;
				} catch (Exception ex3) {
					ex3.printStackTrace();
				}
			}
		}

		return tablaUsuario;
	}

}
