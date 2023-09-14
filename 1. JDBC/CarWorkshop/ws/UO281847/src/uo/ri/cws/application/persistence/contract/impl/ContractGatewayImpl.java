package uo.ri.cws.application.persistence.contract.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import jdbc.Jdbc;
import uo.ri.cws.application.persistence.PersistenceException;
import uo.ri.cws.application.persistence.contract.ContractGateway;
import uo.ri.cws.application.persistence.contract.assembler.ContractAssembler;
import uo.ri.cws.application.persistence.util.Conf;

/**
 * Titulo: Clase ContractGatewayImpl
 *
 * @author Omar Teixeira González, UO281847
 * @version 19 oct 2022
 */
public class ContractGatewayImpl implements ContractGateway {

	@Override
	public void add(ContractDALDto t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(ContractDALDto t) {
		// TODO Auto-generated method stub

	}

	@Override
	public Optional<ContractDALDto> findById(String id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public List<ContractDALDto> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Método findByContractTypeName
	 * @return ContractDALDto
	 */
	@Override
	public Optional<ContractDALDto> findByContractTypeId(String id) {
		Optional<ContractDALDto> contract = null;
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			c = Jdbc.getCurrentConnection();
			
			pst = c.prepareStatement(Conf.getInstance().getProperty("TCONTRACTS_FINDBYCONTRACTTYPENAME"));			
			pst.setString(1, id);
			
			rs = pst.executeQuery();
			
			contract = ContractAssembler.toContractDALDto(rs);	
			return contract;
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
	 * Método findByContractTypeNameInForce
	 * @return contracts
	 */
	@Override
	public List<ContractDALDto> findByContractTypeIdInForce(String id) {
		List<ContractDALDto> contracts = null;
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			c = Jdbc.getCurrentConnection();
			
			pst = c.prepareStatement(Conf.getInstance().getProperty("TCONTRACTS_FINDBYCONTRACTTYPENAMEINFORCE"));			
			pst.setString(1, id);
			
			rs = pst.executeQuery();
			
			contracts = ContractAssembler.toContractList(rs);			
			return contracts;
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
	 * Método findByMechanic
	 * @param id
	 * @return contracts
	 */
	@Override
	public List<ContractDALDto> findByMechanic(String id) {
		List<ContractDALDto> contracts = null;
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			c = Jdbc.getCurrentConnection();
			
			pst = c.prepareStatement(Conf.getInstance().getProperty("TCONTRACTS_FINDBYMECHANIC"));			
			pst.setString(1, id);
			
			rs = pst.executeQuery();
			
			contracts = ContractAssembler.toContractList(rs);			
			return contracts;
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
	 * Método findByMechanicInForce
	 * @param id
	 * @return contract
	 */
	@Override
	public Optional<ContractDALDto> findByMechanicInForce(String id) {
		Optional<ContractDALDto> contract = null;
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			c = Jdbc.getCurrentConnection();
			
			pst = c.prepareStatement(Conf.getInstance().getProperty("TCONTRACTS_FINDBYMECHANICINFORCE"));			
			pst.setString(1, id);
			
			rs = pst.executeQuery();
			
			contract = ContractAssembler.toContractDALDto(rs);	
			return contract;
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
	 * Método findByProfessionalGroup
	 * @param id
	 * @return contracts
	 */
	@Override
	public List<ContractDALDto> findByProfessionalGroup(String id) {
		List<ContractDALDto> contracts = null;
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			c = Jdbc.getCurrentConnection();
			
			pst = c.prepareStatement(Conf.getInstance().getProperty("TCONTRACTS_FINDBYPROFESSIONALGROUP"));			
			pst.setString(1, id);
			
			rs = pst.executeQuery();
			
			contracts = ContractAssembler.toContractList(rs);			
			return contracts;
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
	 * Método findInForce
	 * @return contracts
	 */
	@Override
	public List<ContractDALDto> findInForce() {
		List<ContractDALDto> contracts = null;
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			c = Jdbc.getCurrentConnection();
			
			pst = c.prepareStatement(Conf.getInstance().getProperty("TCONTRACTS_FINDINFORCE"));			
			
			rs = pst.executeQuery();
			
			contracts = ContractAssembler.toContractList(rs);			
			return contracts;
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
	 * Método findInForceOrEndingThisMonth
	 * @param month
	 * @param year
	 */
	@Override
	public List<ContractDALDto> findInForceOrEndingThisMonth(int month, int year) {
		List<ContractDALDto> contracts = null;
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			c = Jdbc.getCurrentConnection();
			
			pst = c.prepareStatement(Conf.getInstance().getProperty("TCONTRACTS_FINDINFORCEORENDINGTHISMONTH"));			
			pst.setInt(1, month);
			pst.setInt(2, year);
			
			rs = pst.executeQuery();
			
			contracts = ContractAssembler.toContractList(rs);			
			return contracts;
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

