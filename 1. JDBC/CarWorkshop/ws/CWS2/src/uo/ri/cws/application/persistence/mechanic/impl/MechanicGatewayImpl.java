package uo.ri.cws.application.persistence.mechanic.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import jdbc.Jdbc;
import uo.ri.cws.application.persistence.PersistenceException;
import uo.ri.cws.application.persistence.mechanic.MechanicGateway;
import uo.ri.cws.application.persistence.mechanic.assembler.MechanicAssembler;
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
	 * 
	 * @param mechanicDALDto
	 */
	@Override
	public void add(MechanicDALDto mechanicDALDto) {
		Connection c = null;
		PreparedStatement pst = null;

		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(Conf.getInstance().getProperty("TMECHANICS_ADD"));
			pst.setString(1, mechanicDALDto.id);
			pst.setString(2, mechanicDALDto.dni);
			pst.setString(3, mechanicDALDto.name);
			pst.setString(4, mechanicDALDto.surname);
			pst.setLong(5, mechanicDALDto.version);

			pst.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e);
		} finally {if (pst != null)
				try {
					pst.close();
				} catch (SQLException e) {
					/* ignore */ }
		}
	}

	/**
	 * Método remove
	 * 
	 * @param idMechanic
	 */
	@Override
	public void remove(String idMechanic) {
		Connection c = null;
		PreparedStatement pst = null;
		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(Conf.getInstance().getProperty("TMECHANICS_REMOVE"));
			pst.setString(1, idMechanic);

			pst.executeUpdate();

		} catch (SQLException e) {
			throw new PersistenceException(e);
		} finally {
			if (pst != null)
				try {
					pst.close();
				} catch (SQLException e) {
					/* ignore */ }
		}
	}

	/**
	 * Método update
	 * 
	 * @param mechanicDALDto
	 */
	@Override
	public void update(MechanicDALDto mechanicDALDto) {
		Connection c = null;
		PreparedStatement pst = null;

		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(Conf.getInstance().getProperty("TMECHANICS_UPDATE"));
			pst.setString(1, mechanicDALDto.name);
			pst.setString(2, mechanicDALDto.surname);
			pst.setString(3, mechanicDALDto.id);

			pst.executeUpdate();

		} catch (SQLException e) {
			throw new PersistenceException(e);
		} finally {
			if (pst != null)
				try {
					pst.close();
				} catch (SQLException e) {
					/* ignore */ }
		}
	}

	/**
	 * Método findById
	 * 
	 * @param id
	 */
	@Override
	public Optional<MechanicDALDto> findById(String id) {
		Optional<MechanicDALDto> mechanic = null;
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(Conf.getInstance().getProperty("TMECHANICS_FINDBYID"));
			pst.setString(1, id);

			rs = pst.executeQuery();

			mechanic = MechanicAssembler.toMechanicDALDto(rs);
			return mechanic;
		} catch (SQLException e) {
			throw new PersistenceException(e);
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
		}
	}

	/**
	 * Método findAll
	 * 
	 * @return mechanics
	 */
	@Override
	public List<MechanicDALDto> findAll() {
		List<MechanicDALDto> mechanics = null;
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(Conf.getInstance().getProperty("TMECHANICS_FINDALL"));

			rs = pst.executeQuery();
			mechanics = MechanicAssembler.toMechanicDALDtoList(rs);
			return mechanics;
		} catch (SQLException e) {
			throw new PersistenceException(e);
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
		}
	}

	/**
	 * Método findByDni
	 * 
	 * @param dni
	 */
	@Override
	public Optional<MechanicDALDto> findByDni(String dni) {
		Optional<MechanicDALDto> mechanic = null;
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(Conf.getInstance().getProperty("TMECHANICS_FINDBYDNI"));
			pst.setString(1, dni);

			rs = pst.executeQuery();

			mechanic = MechanicAssembler.toMechanicDALDto(rs);
			return mechanic;
		} catch (SQLException e) {
			throw new PersistenceException(e);
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
		}
	}

}
