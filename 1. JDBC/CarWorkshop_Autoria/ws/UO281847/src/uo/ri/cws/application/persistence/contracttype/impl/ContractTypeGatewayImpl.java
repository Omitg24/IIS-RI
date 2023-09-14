package uo.ri.cws.application.persistence.contracttype.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import jdbc.Jdbc;
import uo.ri.cws.application.persistence.PersistenceException;
import uo.ri.cws.application.persistence.contracttype.ContractTypeGateway;
import uo.ri.cws.application.persistence.contracttype.assembler.ContractTypeAssembler;
import uo.ri.cws.application.persistence.util.Conf;

/**
 * Titulo: Clase ContractTypeGatewayImpl
 *
 * @author Omar Teixeira González, UO281847
 * @version 18 oct 2022
 */
public class ContractTypeGatewayImpl implements ContractTypeGateway {
	/**
	 * Método add
	 * @param contractTypeDALDto
	 */
	@Override
	public void add(ContractTypeDALDto contractTypeDALDto) {
		Connection c = null;
		PreparedStatement pst = null;
		
		try {
			c = Jdbc.getCurrentConnection();
			
			pst = c.prepareStatement(Conf.getInstance().getProperty("TCONTRACTTYPES_ADD"));
			pst.setString(1, contractTypeDALDto.id);
			pst.setString(2, contractTypeDALDto.name);
			pst.setDouble(3, contractTypeDALDto.compensationDays);
			pst.setLong(4, contractTypeDALDto.version);
			
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
	 * Método remove
	 * @param name
	 */
	@Override
	public void remove(String name) {
		Connection c = null;
		PreparedStatement pst = null;
		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(Conf.getInstance().getProperty("TCONTRACTTYPES_REMOVE"));
			pst.setString(1, name);

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
	 * @param contractTypeDALDto
	 */
	@Override
	public void update(ContractTypeDALDto contractTypeDALDto) {
		Connection c = null;
		PreparedStatement pst = null;

		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(Conf.getInstance().getProperty("TCONTRACTTYPES_UPDATE"));
			pst.setDouble(1, contractTypeDALDto.compensationDays);
			pst.setString(2, contractTypeDALDto.name);

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
	 * @param id
	 * @return contractType
	 */
	@Override
	public Optional<ContractTypeDALDto> findById(String id) {
		Optional<ContractTypeDALDto> contractType = null;
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(Conf.getInstance().getProperty("TCONTRACTTYPES_FINDBYID"));
			pst.setString(1, id);

			rs = pst.executeQuery();

			contractType = ContractTypeAssembler.toContractTypeDALDto(rs);
			return contractType;
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
	 * @return contractTypes 
	 */
	@Override
	public List<ContractTypeDALDto> findAll() {
		List<ContractTypeDALDto> contractTypes = null;
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			c = Jdbc.getCurrentConnection();
			
			pst = c.prepareStatement(Conf.getInstance().getProperty("TCONTRACTTYPES_FINDALL"));
			
			rs = pst.executeQuery();
			
			contractTypes = ContractTypeAssembler.toContractTypeList(rs);
			return contractTypes;
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
	 * Método findByName
	 * @param name
	 * @return ContractTypeDALDto
	 */
	@Override
	public Optional<ContractTypeDALDto> findByName(String name) {
		Optional<ContractTypeDALDto> contractType = null;
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(Conf.getInstance().getProperty("TCONTRACTTYPES_FINDBYNAME"));
			pst.setString(1, name);

			rs = pst.executeQuery();

			contractType = ContractTypeAssembler.toContractTypeDALDto(rs);
			return contractType;
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