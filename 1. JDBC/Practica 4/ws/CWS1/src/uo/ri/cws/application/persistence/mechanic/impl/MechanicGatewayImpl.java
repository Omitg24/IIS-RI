package uo.ri.cws.application.persistence.mechanic.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import jdbc.Jdbc;
import uo.ri.cws.application.persistence.mechanic.MechanicGateway;
import uo.ri.cws.application.persistence.util.Conf;

/**
 * Titulo: Clase MechanicGatewayImpl
 *
 * @author Omar Teixeira González, UO281847
 * @version 14 oct 2022
 */
public class MechanicGatewayImpl implements MechanicGateway {
	/**
	 * Método add
	 * @param mechanic
	 */
	@Override
	public void add(MechanicDALDto mechanic) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(Conf.getInstance().getProperty("TMECHANICS_ADD"));
			pst.setString(1, mechanic.id);
			pst.setString(2, mechanic.dni);
			pst.setString(3, mechanic.name);
			pst.setString(4, mechanic.surname);
			pst.setLong(5, mechanic.version);

			pst.executeUpdate();
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

	/**
	 * Método remove
	 * @param idMechanic
	 */
	@Override
	public void remove(String idMechanic) {
		Connection c = null;
		PreparedStatement pst = null;		
		ResultSet rs = null;
		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(Conf.getInstance().getProperty("TMECHANICS_DELETE"));
			pst.setString(1, idMechanic);

			pst.executeUpdate();

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

	@Override
	public void update(MechanicDALDto t) {
		// TODO Auto-generated method stub

	}

	@Override
	public Optional<MechanicDALDto> findById(String id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public List<MechanicDALDto> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<MechanicDALDto> findByDni(String dni) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

}
