package com.udimacuestion.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.udimacuestion.clases.TablaUsuario;

/**
 * Clase UsuarioDAO. gestión de acceso a datos sobre usuarios de la plataforma
 * de gestión de cuestionarios.
 * 
 * @author rarranz
 *
 */
public class UsuarioDAO {
	private static final String NOMBREDAO = "UsuarioDAO ";
	private Connection conexion = null;

	public UsuarioDAO() {
		conexion = ConexionBd.getConnection("ias");
	}

	/**
	 * método que inserta, da de alta usuarios.
	 * 
	 * @param tablaUsuario
	 * @return
	 * @throws DaoException
	 * @throws SQLException
	 */
	public int altaUsuario(TablaUsuario tablaUsuario) throws DaoException, SQLException {

		// Si no hemos obtenido la conexion devolvemos una excepcion (DaoExcepcion)
		if (conexion == null) {
			throw new DaoException(10, NOMBREDAO, "altaUsuario", "getConexion", "conn",
					"No ha podido obtener conexion con la base de Datos");
		}

		PreparedStatement stmt = null;
		int resultadoUpdate = 0;
		try {
			stmt = conexion.prepareStatement(
					"INSERT INTO `cuestionarioudima`.`usuario` (`idUsuario`, `nombreUsuario`, `passwordUsuario`, `emailUsuario`, `superUsuario`)"
							+ " VALUES ((SELECT cuestionarioudima.nextvalusu('sec_usuario') as next_sequence), '"
							+ tablaUsuario.getUsuario() + "', '" + tablaUsuario.getPassword() + "', '"
							+ tablaUsuario.getEmail() + "'," + tablaUsuario.getSuperUsuario() + ");");
			resultadoUpdate = stmt.executeUpdate();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new DaoException(20, NOMBREDAO, "altaUsuario", "altaUsuario", "conn",
					"Error al ejecutar la sentencia sql");
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

		return resultadoUpdate;
	}

	/**
	 * método que modifica el usuario seleccionado.
	 * 
	 * @param tablaUsuario
	 * @param tipo
	 * @return
	 * @throws DaoException
	 * @throws SQLException
	 */
	public int modUsuario(TablaUsuario tablaUsuario) throws DaoException, SQLException {

		// Si no hemos obtenido la conexion devolvemos una excepcion (DaoExcepcion)
		if (conexion == null) {
			throw new DaoException(10, NOMBREDAO, "modUsuario", "getConexion", "conn",
					"No ha podido obtener conexion con la base de Datos");
		}

		PreparedStatement stmt = null;
		int resultadoUpdate = 0;
		try {
			stmt = conexion.prepareStatement("UPDATE `cuestionarioudima`.`usuario` SET " + "`nombreUsuario` = '"
					+ tablaUsuario.getUsuario() + "', `passwordUsuario` = '" + tablaUsuario.getPassword()
					+ "', `emailUsuario` = '" + tablaUsuario.getEmail() + "', `superUsuario` = "
					+ tablaUsuario.getSuperUsuario() + " WHERE `idUsuario` = '" + tablaUsuario.getIdUsuario() + "'");

			resultadoUpdate = stmt.executeUpdate();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new DaoException(20, NOMBREDAO, "modUsuario", "modUsuario", "conn",
					"Error al ejecutar la sentencia sql");
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

		return resultadoUpdate;
	}

	/**
	 * método que borra un usuario seleccionado.
	 * 
	 * @param tablaUsuario
	 * @param tipo
	 * @return
	 * @throws DaoException
	 * @throws SQLException
	 */
	public int delUsuario(TablaUsuario tablaUsuario) throws DaoException, SQLException {

		// Si no hemos obtenido la conexion devolvemos una excepcion (DaoExcepcion)
		if (conexion == null) {
			throw new DaoException(10, NOMBREDAO, "delUsuario", "getConexion", "conn",
					"No ha podido obtener conexion con la base de Datos");
		}

		PreparedStatement stmt = null;
		int resultadoUpdate = 0;
		try {
			stmt = conexion.prepareStatement("delete from `cuestionarioudima`.`usuario` WHERE `idUsuario` = '"
					+ tablaUsuario.getIdUsuario() + "'");

			resultadoUpdate = stmt.executeUpdate();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new DaoException(20, NOMBREDAO, "delUsuario", "delUsuario", "conn",
					"Error al ejecutar la sentencia sql");
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

		return resultadoUpdate;
	}

	/**
	 * método que recupera los usuarios de la aplicación
	 * 
	 * @param tipo
	 * @return
	 * @throws DaoException
	 * @throws SQLException
	 */
	public ArrayList<TablaUsuario> listadoUsuarios(int tipo) throws DaoException, SQLException {

		// Si no hemos obtenido la conexion devolvemos una excepcion (DaoExcepcion)
		if (conexion == null) {
			throw new DaoException(10, NOMBREDAO, "listadoUsuarios", "getConexion", "conn",
					"No ha podido obtener conexion con la base de Datos");
		}

		PreparedStatement stmt = null;
		TablaUsuario tablaUsuario = null;
		ArrayList<TablaUsuario> listaUsuario = new ArrayList<TablaUsuario>();
		try {
			stmt = conexion.prepareStatement(
					"SELECT idUsuario, nombreUsuario, passwordUsuario, emailUsuario, superUsuario FROM cuestionarioudima.usuario");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				tablaUsuario = new TablaUsuario();
				tablaUsuario.setIdUsuario(rs.getInt("idUsuario"));
				tablaUsuario.setUsuario(rs.getString("nombreUsuario"));
				listaUsuario.add(tablaUsuario);
			}

		} catch (SQLException sqle) {
			throw new DaoException(20, NOMBREDAO, "listadoUsuarios", "listadoUsuarios", "conn",
					"Error al ejecutar la sentencia sql");
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

		return listaUsuario;
	}

	/**
	 * método que localiza un usuario por su id.
	 * 
	 * @param idUsu
	 * @return
	 * @throws DaoException
	 * @throws SQLException
	 */
	public TablaUsuario buscarUsuario(String idUsu) throws DaoException, SQLException {

		// Si no hemos obtenido la conexion devolvemos una excepcion (DaoExcepcion)
		if (conexion == null) {
			throw new DaoException(10, NOMBREDAO, "buscarUsuario", "getConexion", "conn",
					"No ha podido obtener conexion con la base de Datos");
		}

		PreparedStatement stmt = null;
		TablaUsuario tablaUsuario = new TablaUsuario();

		try {
			stmt = conexion.prepareStatement(
					"SELECT idUsuario, nombreUsuario, passwordUsuario, emailUsuario, superUsuario FROM cuestionarioudima.usuario where idUsuario="
							+ idUsu);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				tablaUsuario = new TablaUsuario();
				tablaUsuario.setIdUsuario(rs.getInt("idUsuario"));
				tablaUsuario.setUsuario(rs.getString("nombreUsuario"));
				tablaUsuario.setPassword(rs.getString("passwordUsuario"));
				tablaUsuario.setEmail(rs.getString("emailUsuario"));
				tablaUsuario.setSuperUsuario(rs.getInt("superUsuario"));
			}

		} catch (SQLException sqle) {
			throw new DaoException(20, NOMBREDAO, "buscarUsuario", "buscarUsuario", "conn",
					"Error al ejecutar la sentencia sql");
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
