package uo.ri.cws.application.persistence.professionalgroup.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import jdbc.Jdbc;
import uo.ri.cws.application.persistence.PersistenceException;
import uo.ri.cws.application.persistence.professionalgroup.ProfessionalGroupGateway;
import uo.ri.cws.application.persistence.professionalgroup.assembler.ProfessionalGroupAssembler;
import uo.ri.cws.application.persistence.util.Conf;

/**
 * Titulo: Clase ProfessionalGroupGatewayImpl
 *
 * @author Omar Teixeira González, UO281847
 * @version 26 oct 2022
 */
public class ProfessionalGroupGatewayImpl implements ProfessionalGroupGateway {

	@Override
	public void add(ProfessionalGroupDALDto t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(ProfessionalGroupDALDto t) {
		// TODO Auto-generated method stub

	}

	/**
	 * Método findById
	 * @param id
	 * @return professionalGroup
	 */
	@Override
	public Optional<ProfessionalGroupDALDto> findById(String id) {
		Optional<ProfessionalGroupDALDto> professionalGroup;
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(Conf.getInstance().getProperty("TPROFESSIONALGROUPS_FINDBYID"));
			pst.setString(1, id);

			rs = pst.executeQuery();

			professionalGroup = ProfessionalGroupAssembler.toProfessionalGroupDALDto(rs);
			return professionalGroup;
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

	@Override
	public List<ProfessionalGroupDALDto> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Método findByName
	 * @param name
	 * @return professionalGroup
	 */
	@Override
	public Optional<ProfessionalGroupDALDto> findByName(String name) {
		Optional<ProfessionalGroupDALDto> professionalGroup;
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(Conf.getInstance().getProperty("TPROFESSIONALGROUPS_FINDBYNAME"));
			pst.setString(1, name);

			rs = pst.executeQuery();

			professionalGroup = ProfessionalGroupAssembler.toProfessionalGroupDALDto(rs);
			return professionalGroup;
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
