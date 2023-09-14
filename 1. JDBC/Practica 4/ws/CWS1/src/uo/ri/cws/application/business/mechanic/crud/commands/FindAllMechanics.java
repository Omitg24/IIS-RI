package uo.ri.cws.application.business.mechanic.crud.commands;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.mechanic.MechanicService.MechanicBLDto;
import uo.ri.cws.application.business.mechanic.assembler.MechanicAssembler;

/**
 * Titulo: Clase FindAllMechanics
 *
 * @author Omar Teixeira González, UO281847
 * @version 13 oct 2022
 */
public class FindAllMechanics {
	/**
	 * Constante SQL
	 */
	private static final String SQL = "select id, dni, name, surname, version from TMechanics";
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
	 * Método execute
	 * @return list
	 * @throws BusinessException
	 */
	public List<MechanicBLDto> execute() throws BusinessException {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = DriverManager.getConnection(URL, USER, PASSWORD);

			pst = c.prepareStatement(SQL);

			rs = pst.executeQuery();
			List<MechanicBLDto> mechanics = MechanicAssembler.toMechanicDtoList(rs);
			return mechanics;
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
