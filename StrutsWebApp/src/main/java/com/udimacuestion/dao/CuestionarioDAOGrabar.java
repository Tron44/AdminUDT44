package com.udimacuestion.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.udimacuestion.clases.TablaPregunta;
import com.udimacuestion.clases.TablaRespuesta;

/**
 * @author rarranz Clase DAOGrabar de acceso a los grados, asignaturas,
 *         cuestionarios y preguntas/respuestas del modelo de datos, para
 *         inserciones y updates
 *
 */
public class CuestionarioDAOGrabar {
	private static final String NOMBREDAO = "CuestionarioDAOGrabar ";
	private Connection conexion = null;

	public CuestionarioDAOGrabar() {
		conexion = ConexionBd.getConnection("ias");
	}

	/**
	 * método de inserción de un cuestionario con preguntas asociadas
	 * 
	 * @param grado
	 * @param asignatura
	 * @param nombreControl
	 * @param tablaPregunta
	 * @return
	 * @throws DaoException
	 * @throws SQLException
	 */
	public int insertarCuestionario(String grado, String asignatura, String nombreControl, String descControl, TablaPregunta tablaPregunta)
			throws DaoException, SQLException {

		// Si no hemos obtenido la conexion devolvemos una excepcion (DaoExcepcion)
		if (conexion == null) {
			throw new DaoException(10, NOMBREDAO, "insertarCuestionario", "getConexion", "conn",
					"No ha podido obtener conexion con la base de Datos");
		}
		conexion.setAutoCommit(false);
		PreparedStatement stmt = null;
		ResultSet rs = null;
		TablaRespuesta tablaRespuesta = null;
		int resultadoUpdate = 0;
		int idCuestionario = 0;
		int idPregunta = 0;
		int valido = 0;
		try {

			stmt = conexion
					.prepareStatement("SELECT cuestionarioudima.nextvalcuest('sec_cuestionario') as next_sequence");
			rs = stmt.executeQuery();
			if (rs.next())
				idCuestionario = rs.getInt(1);
			stmt.close();
			stmt = conexion
					.prepareStatement("INSERT INTO `cuestionarioudima`.`cuestionario`\r\n" + "(`idCuestionario`,\r\n"
							+ "`nombreCuestionario`,\r\n" + "`publicacion`,\r\n" + "`idAsignatura`,\r\n" + "`descCuestionario`)\r\n" + "VALUES\r\n"
							+ "(" + idCuestionario + ", '" + nombreControl + "', 0," + asignatura + ", '" + descControl+ "');");

			resultadoUpdate = stmt.executeUpdate();
			if (resultadoUpdate > 0) {
				// se ha insertado correctamente el nombre del control. ahora voy con la
				// pregunta y sus respuestas asociadas.
				stmt.close();
				stmt = conexion
						.prepareStatement("SELECT cuestionarioudima.nextvalpreg('sec_pregunta') as next_sequence");
				rs = stmt.executeQuery();
				if (rs.next())
					idPregunta = rs.getInt(1);
				stmt.close();
				stmt = conexion.prepareStatement("INSERT INTO `cuestionarioudima`.`pregunta`\r\n"
						+ "	(`idPregunta`,\r\n" + "	`textoPregunta`,\r\n"
						+ "	`tipoPregunta`, `idCuestionario`, `numRespuestas`)\r\n" + "	VALUES\r\n" + "	(" + idPregunta
						+ ", '" + tablaPregunta.getTextoPregunta().replaceAll("'", "\"") + "', '"
						+ tablaPregunta.getTipoPregunta() + "', " + idCuestionario + ", "
						+ tablaPregunta.getNumeroRespuestas() + ");");
				resultadoUpdate = stmt.executeUpdate();
				if (resultadoUpdate > 0) {
					ArrayList<TablaRespuesta> listaRespuestasRec = tablaPregunta.getListaRespuestas();
					for (int i = 0; i < listaRespuestasRec.size(); i++) {
						stmt.close();
						tablaRespuesta = listaRespuestasRec.get(i);
						tablaRespuesta.getTextoRespuesta();
						valido = 0;
						if (tablaRespuesta.isValido())
							valido = 1;
						stmt = conexion.prepareStatement("INSERT INTO `cuestionarioudima`.`respuesta`\r\n"
								+ "	(`idRespuesta`,\r\n" + "	`textoRespuesta`,\r\n" + "	`idPreguntaResp`,\r\n"
								+ "	`validaRespuesta`)\r\n" + "	VALUES\r\n"
								+ "	((SELECT cuestionarioudima.nextvalresp('sec_respuesta') as next_sequence), '"
								+ tablaRespuesta.getTextoRespuesta().replaceAll("'", "\"") + "', " + idPregunta + ", "
								+ valido + ");");
						resultadoUpdate = stmt.executeUpdate();
						if (resultadoUpdate == 1) {
							conexion.commit();
						} else {
							conexion.rollback();
						}
					}
				}
			}

		} catch (SQLException sqle) {
			resultadoUpdate = 0;
			conexion.rollback();
			sqle.printStackTrace();
			throw new DaoException(20, NOMBREDAO, "insertarCuestionario", "insertarCuestionario", "conn",
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
		if (resultadoUpdate != 0)
			resultadoUpdate = idCuestionario;
		return resultadoUpdate;
	}

	/**
	 * método de insercion de cuestionario sin preguntas y respuestas
	 * 
	 * @param grado
	 * @param asignatura
	 * @param nombreControl
	 * @return
	 * @throws DaoException
	 * @throws SQLException
	 */
	public int insertarSoloCuestionario(String grado, String asignatura, String nombreControl, String descControl)
			throws DaoException, SQLException {

		// Si no hemos obtenido la conexion devolvemos una excepcion (DaoExcepcion)
		if (conexion == null) {
			throw new DaoException(10, NOMBREDAO, "insertarSoloCuestionario", "getConexion", "conn",
					"No ha podido obtener conexion con la base de Datos");
		}

		PreparedStatement stmt = null;
		ResultSet rs = null;
		int resultadoUpdate = 0;
		int idCuestionario = 0;

		try {

			stmt = conexion
					.prepareStatement("SELECT cuestionarioudima.nextvalcuest('sec_cuestionario') as next_sequence");
			rs = stmt.executeQuery();
			if (rs.next())
				idCuestionario = rs.getInt(1);
			stmt.close();		
			stmt = conexion
					.prepareStatement("INSERT INTO `cuestionarioudima`.`cuestionario`\r\n" + "(`idCuestionario`,\r\n"
							+ "`nombreCuestionario`,\r\n" + "`publicacion`,\r\n" + "`idAsignatura`,\r\n" + "`descCuestionario`)\r\n" + "VALUES\r\n"
							+ "(" + idCuestionario + ", '" + nombreControl + "', 0," + asignatura + ", '" + descControl+ "');");
			
			resultadoUpdate = stmt.executeUpdate();
		} catch (SQLException sqle) {
			resultadoUpdate = 0;
			sqle.printStackTrace();
			throw new DaoException(20, NOMBREDAO, "insertarSoloCuestionario", "insertarSoloCuestionario", "conn",
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
		if (resultadoUpdate != 0)
			resultadoUpdate = idCuestionario;
		return resultadoUpdate;
	}

	/**
	 * método insertarPreguntaRespuesta, método que se ejecuta existiendo
	 * obligatoriamente un cuestionario. se da alta preguntas con sus respuestas
	 * asociadas.
	 * 
	 * @param idControl
	 * @param tablaPregunta
	 * @return
	 * @throws DaoException
	 * @throws SQLException
	 */
	public int insertarPreguntaRespuesta(int idControl, TablaPregunta tablaPregunta) throws DaoException, SQLException {
		// Si no hemos obtenido la conexion devolvemos una excepcion (DaoExcepcion)
		if (conexion == null) {
			throw new DaoException(10, NOMBREDAO, "insertarPreguntaRespuesta", "insertarPreguntaRespuesta", "conn",
					"No ha podido obtener conexion con la base de Datos");
		}
		conexion.setAutoCommit(false);
		PreparedStatement stmt = null;
		ResultSet rs = null;
		TablaRespuesta tablaRespuesta = null;
		int resultadoUpdate = 0;
		int idPregunta = 0;
		int valido = 0;
		try {
			stmt = conexion.prepareStatement("SELECT cuestionarioudima.nextvalpreg('sec_pregunta') as next_sequence");
			rs = stmt.executeQuery();
			if (rs.next())
				idPregunta = rs.getInt(1);
			stmt.close();
			stmt = conexion.prepareStatement("INSERT INTO `cuestionarioudima`.`pregunta`\r\n" + "	(`idPregunta`,\r\n"
					+ "	`textoPregunta`,\r\n" + "	`tipoPregunta`, `idCuestionario`, `numRespuestas`)\r\n"
					+ "	VALUES\r\n" + "	(" + idPregunta + ", '" + tablaPregunta.getTextoPregunta().replaceAll("'", "\"")
					+ "', '" + tablaPregunta.getTipoPregunta() + "', " + idControl + ", "
					+ tablaPregunta.getNumeroRespuestas() + ");");
			resultadoUpdate = stmt.executeUpdate();
			if (resultadoUpdate > 0) {
				ArrayList<TablaRespuesta> listaRespuestasRec = tablaPregunta.getListaRespuestas();
				for (int i = 0; i < listaRespuestasRec.size(); i++) {
					stmt.close();
					tablaRespuesta = listaRespuestasRec.get(i);
					tablaRespuesta.getTextoRespuesta();
					valido = 0;
					if (tablaRespuesta.isValido())
						valido = 1;
					stmt = conexion.prepareStatement("INSERT INTO `cuestionarioudima`.`respuesta`\r\n"
							+ "	(`idRespuesta`,\r\n" + "	`textoRespuesta`,\r\n" + "	`idPreguntaResp`,\r\n"
							+ "	`validaRespuesta`)\r\n" + "	VALUES\r\n"
							+ "	((SELECT cuestionarioudima.nextvalresp('sec_respuesta') as next_sequence), '"
							+ tablaRespuesta.getTextoRespuesta().replaceAll("'", "\"") + "', " + idPregunta + ", "
							+ valido + ");");
					resultadoUpdate = stmt.executeUpdate();
					if (resultadoUpdate > 0) {
						conexion.commit();
					} else {
						conexion.rollback();
					}
				}
			}

		} catch (SQLException sqle) {
			resultadoUpdate = 0;
			conexion.rollback();
			sqle.printStackTrace();
			throw new DaoException(20, NOMBREDAO, "insertarPreguntaRespuesta", "insertarPreguntaRespuesta", "conn",
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
		if (resultadoUpdate != 0)
			resultadoUpdate = idControl;
		return resultadoUpdate;
	}

	/**
	 * método de eliminación de pregunta, borra además sus respuestas asociadas.
	 * 
	 * @param preguntaDel
	 * @return
	 * @throws DaoException
	 * @throws SQLException
	 */
	public int eliminarPregunta(String preguntaDel) throws DaoException, SQLException {
		// Si no hemos obtenido la conexion devolvemos una excepcion (DaoExcepcion)
		if (conexion == null) {
			throw new DaoException(10, NOMBREDAO, "eliminarPregunta", "getConexion", "conn",
					"No ha podido obtener conexion con la base de Datos");
		}
		conexion.setAutoCommit(false);
		PreparedStatement stmt = null;
		int resultadoDelete = 0;
		try {
			stmt = conexion
					.prepareStatement("delete from cuestionarioudima.respuesta where idPreguntaResp=" + preguntaDel);
			resultadoDelete = stmt.executeUpdate();
			if (resultadoDelete > 0) {
				stmt.close();
				stmt = conexion
						.prepareStatement("delete from cuestionarioudima.pregunta where idPregunta=" + preguntaDel);
				resultadoDelete = stmt.executeUpdate();
				if (resultadoDelete > 0) {
					conexion.commit();
				} else {
					conexion.rollback();
				}
			}

		} catch (SQLException sqle) {
			resultadoDelete = 0;
			conexion.rollback();
			sqle.printStackTrace();
			throw new DaoException(20, NOMBREDAO, "eliminarPregunta", "eliminarPregunta", "conn",
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
		return resultadoDelete;
	}

	/**
	 * método que modifica la pregunta y la respuesta seleccionada.
	 * 
	 * @param tablaPregunta
	 * @return
	 * @throws DaoException
	 * @throws SQLException
	 */
	public int modificarPreguntaRespuesta(TablaPregunta tablaPregunta) throws DaoException, SQLException {
		// Si no hemos obtenido la conexion devolvemos una excepcion (DaoExcepcion)
		if (conexion == null) {
			throw new DaoException(10, NOMBREDAO, "modificarPreguntaRespuesta", "getConexion", "conn",
					"No ha podido obtener conexion con la base de Datos");
		}
		conexion.setAutoCommit(false);
		PreparedStatement stmt = null;
		int resultadoUpdate = 0;
		TablaRespuesta tablaRespuesta = null;
		int valido = 0;
		try {
			stmt = conexion.prepareStatement("UPDATE `cuestionarioudima`.`pregunta` SET `textoPregunta` = '"
					+ tablaPregunta.getTextoPregunta().replaceAll("'", "\"") + "' WHERE `idPregunta` ="
					+ tablaPregunta.getIdPregunta());
			resultadoUpdate = stmt.executeUpdate();
			if (resultadoUpdate > 0) {
				ArrayList<TablaRespuesta> listaRespuestasRec = tablaPregunta.getListaRespuestas();
				for (int i = 0; i < listaRespuestasRec.size(); i++) {

					tablaRespuesta = listaRespuestasRec.get(i);
					valido = 0;
					if (tablaRespuesta.isValido())
						valido = 1;
					stmt.close();
					stmt = conexion.prepareStatement("UPDATE `cuestionarioudima`.`respuesta` SET `textoRespuesta` = '"
							+ tablaRespuesta.getTextoRespuesta().replaceAll("'", "\"") + "', `validaRespuesta` = "
							+ valido + " WHERE `idRespuesta` = " + tablaRespuesta.getIdRespuesta());
					resultadoUpdate = stmt.executeUpdate();
					if (resultadoUpdate > 0) {
						conexion.commit();
					} else {
						conexion.rollback();
					}
				}
			}

		} catch (SQLException sqle) {
			resultadoUpdate = 0;
			conexion.rollback();
			sqle.printStackTrace();
			throw new DaoException(20, NOMBREDAO, "modificarPreguntaRespuesta", "modificarPreguntaRespuesta", "conn",
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
	 * método que publica un cuestionario, haciéndolo o no accesible.
	 * 
	 * @param controlPub
	 * @param publicacion
	 * @return
	 * @throws DaoException
	 * @throws SQLException
	 */
	public int publicarCuestionario(Integer controlPub, String publicacion) throws DaoException, SQLException {
		// Si no hemos obtenido la conexion devolvemos una excepcion (DaoExcepcion)
		if (conexion == null) {
			throw new DaoException(10, NOMBREDAO, "publicarCuestionario", "getConexion", "conn",
					"No ha podido obtener conexion con la base de Datos");
		}
		PreparedStatement stmt = null;
		int resultadoUpdate = 0;
		try {
			stmt = conexion.prepareStatement("UPDATE `cuestionarioudima`.`cuestionario` SET `publicacion` = "
					+ publicacion + " WHERE `idCuestionario` =" + controlPub.toString());
			resultadoUpdate = stmt.executeUpdate();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new DaoException(20, NOMBREDAO, "publicarCuestionario", "publicarCuestionario", "conn",
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
		if (resultadoUpdate != 0)
			resultadoUpdate = Integer.parseInt(publicacion);
		return resultadoUpdate;
	}

	/**
	 * método que elimina un cuestionario.
	 * 
	 * @param cuestionarioDel
	 * @return
	 * @throws DaoException
	 * @throws SQLException
	 */
	public int eliminarCuestionario(String cuestionarioDel) throws DaoException, SQLException {
		// Si no hemos obtenido la conexion devolvemos una excepcion (DaoExcepcion)
		if (conexion == null) {
			throw new DaoException(10, NOMBREDAO, "eliminarCuestionario", "getConexion", "conn",
					"No ha podido obtener conexion con la base de Datos");
		}
		PreparedStatement stmt = null;
		int resultadoDelete = 0;
		try {
			stmt = conexion.prepareStatement(
					"DELETE FROM `cuestionarioudima`.`cuestionario` WHERE `idCuestionario` =" + cuestionarioDel);
			resultadoDelete = stmt.executeUpdate();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new DaoException(20, NOMBREDAO, "eliminarCuestionario", "eliminarCuestionario", "conn",
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

		return resultadoDelete;
	}

	/**
	 * método que modifica el nombre del cuestionario
	 * 
	 * @param cuestionarioMod
	 * @param camponuevo
	 * @return
	 * @throws DaoException
	 * @throws SQLException
	 */
	public int modificarCuestionario(String cuestionarioMod, String camponuevo, String camponuevoDesc) throws DaoException, SQLException {
		// Si no hemos obtenido la conexion devolvemos una excepcion (DaoExcepcion)
		if (conexion == null) {
			throw new DaoException(10, NOMBREDAO, "modificarCuestionario", "getConexion", "conn",
					"No ha podido obtener conexion con la base de Datos");
		}
		PreparedStatement stmt = null;
		int resultadoUpdate = 0;
		try {
			stmt = conexion.prepareStatement("UPDATE `cuestionarioudima`.`cuestionario` "
					+ "SET `nombreCuestionario` = '"
					+ camponuevo.replaceAll("'", "\"") 
					+ "', `descCuestionario` = '"
					+ camponuevoDesc.replaceAll("'", "\"")
					
					+ "' WHERE `idCuestionario` =" + cuestionarioMod);			
			resultadoUpdate = stmt.executeUpdate();
		} catch (SQLException sqle) {			
			sqle.printStackTrace();
			throw new DaoException(20, NOMBREDAO, "modificarCuestionario", "modificarCuestionario", "conn",
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

}
