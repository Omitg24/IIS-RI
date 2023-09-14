package uo.ri.cws.application.business.mechanic.crud.commands;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.mechanic.MechanicService.MechanicBLDto;

/**
 * Titulo: Clase AddMechanic
 *
 * @author Omar Teixeira González, UO281847
 * @version 13 oct 2022
 */
public class AddMechanic {
	/**
	 * Constante SQL
	 */
	private static String SQL = "insert into TMechanics(id, dni, name, surname, version) values (?, ?, ?, ?, ?)";
	/**
	 * Constante URL
	 */
	private static final String URL = "jdbc:hsqldb:hsql://localhost";
	/**
	 * Constante USER
	 */
	private static final String USER = "sa";
	/**
	 * Constante PASSWORD
	 */
	private static final String PASSWORD = "";

	/**
	 * Atributo mechanic
	 */
	private MechanicBLDto mechanic;

	public AddMechanic(MechanicBLDto mechanic) {
		this.mechanic = mechanic;
	}

	/**
	 * Método execute
	 * 
	 * @throws BusinessException
	 */
	public MechanicBLDto execute() throws BusinessException {
		this.mechanic.id = UUID.randomUUID().toString();
		this.mechanic.version = 1L;

		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = DriverManager.getConnection(URL, USER, PASSWORD);

			pst = c.prepareStatement(SQL);
			pst.setString(1, mechanic.id);
			pst.setString(2, mechanic.dni);
			pst.setString(3, mechanic.name);
			pst.setString(4, mechanic.surname);
			pst.setLong(5, mechanic.version);

			pst.executeUpdate();
			return mechanic;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					/* ignore */ }
			if (pst != null)
				try {
					pst.close();
				} catch (SQLException e) {
					/* ignore */ }
			if (c != null)
				try {
					c.close();
				} catch (SQLException e) {
					/* ignore */ }
		}
	}
}
