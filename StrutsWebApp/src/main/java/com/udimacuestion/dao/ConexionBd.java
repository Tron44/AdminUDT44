package com.udimacuestion.dao;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author rarranz Clase que genera la conexión contra la base de datos
 *
 */
public class ConexionBd {

	public static Connection getConnection(String modoConexion) {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String sURL = "jdbc:mysql://localhost:3306/MySQL";
			con = DriverManager.getConnection(sURL, "root", "tron44");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}

}