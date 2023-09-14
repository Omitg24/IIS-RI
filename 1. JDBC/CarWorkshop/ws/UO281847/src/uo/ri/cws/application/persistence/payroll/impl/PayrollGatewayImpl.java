package uo.ri.cws.application.persistence.payroll.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jdbc.Jdbc;
import uo.ri.cws.application.persistence.PersistenceException;
import uo.ri.cws.application.persistence.payroll.PayrollGateway;
import uo.ri.cws.application.persistence.payroll.assembler.PayrollAssembler;
import uo.ri.cws.application.persistence.util.Conf;

/**
 * Titulo: Clase PayrollGatewayImpl
 *
 * @author Omar Teixeira González, UO281847
 * @version 26 oct 2022
 */
public class PayrollGatewayImpl implements PayrollGateway {
	/**
	 * Método add
	 * @param payrollDALDto
	 */
	@Override
	public void add(PayrollDALDto payrollDALDto) {
		Connection c = null;
		PreparedStatement pst = null;

		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(Conf.getInstance().getProperty("TPAYROLLS_ADD"));
			pst.setString(1, payrollDALDto.id);
			pst.setString(2, payrollDALDto.contractId);
			pst.setDate(3, java.sql.Date.valueOf(payrollDALDto.date));
			pst.setDouble(4, payrollDALDto.monthlyWage);
			pst.setDouble(5, payrollDALDto.bonus);
			pst.setDouble(6, payrollDALDto.productivityBonus);
			pst.setDouble(7, payrollDALDto.trienniumPayment);
			pst.setDouble(8, payrollDALDto.incomeTax);
			pst.setDouble(9, payrollDALDto.nic);
			pst.setLong(10, payrollDALDto.version);			
			
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
	 * @param id
	 */
	@Override
	public void remove(String id) {
		Connection c = null;
		PreparedStatement pst = null;
		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(Conf.getInstance().getProperty("TPAYROLLS_REMOVE"));
			pst.setString(1, id);

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

	@Override
	public void update(PayrollDALDto t) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Método findById
	 * @param id
	 * @return payroll
	 */
	@Override
	public Optional<PayrollDALDto> findById(String id) { 
		Optional<PayrollDALDto> payroll = null;
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			c = Jdbc.getCurrentConnection();
			
			pst = c.prepareStatement(Conf.getInstance().getProperty("TPAYROLLS_FINDBYID"));
			pst.setString(1, id);
			
			rs = pst.executeQuery();
			
			payroll = PayrollAssembler.toPayrollDALDto(rs);
			return payroll;
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
	 * @return payrolls
	 */
	@Override
	public List<PayrollDALDto> findAll() {
		List<PayrollDALDto> payrolls = null; 
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			c = Jdbc.getCurrentConnection();
			
			pst = c.prepareStatement(Conf.getInstance().getProperty("TPAYROLLS_FINDALL"));
			
			rs = pst.executeQuery();
			
			payrolls = PayrollAssembler.toPayrollDALDtoList(rs);
			return payrolls;
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
	 * Método findByContract
	 * @param contractIds
	 * @return payrolls
	 */
	@Override
	public List<PayrollDALDto> findByContractsIds(List<String> contractIds) {
		List<PayrollDALDto> payrolls = new ArrayList<PayrollDALDto>();
		List<PayrollDALDto> auxList = null;
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(Conf.getInstance().getProperty("TPAYROLLS_FINDBYCONTRACTIDS"));
			
			for (String id : contractIds) {
				pst.setString(1, id);
				rs = pst.executeQuery();
				auxList = PayrollAssembler.toPayrollDALDtoList(rs);
				for (PayrollDALDto payroll : auxList) {
					payrolls.add(payroll);
				}
			}		
			
			return payrolls;
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
	 * Método findByContractsIdsInDates
	 * @param contractIds
	 * @param month
	 * @param year
	 * @return payrolls
	 */
	@Override
	public List<PayrollDALDto> findByContractsIdsInDates(List<String> contractIds, int month, int year) {
		List<PayrollDALDto> payrolls = new ArrayList<PayrollDALDto>();
		List<PayrollDALDto> auxList = null;
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(Conf.getInstance().getProperty("TPAYROLLS_FINDBYCONTRACTIDSINDATES"));
			
			for (String id : contractIds) {
				pst.setString(1, id);
				pst.setInt(2, month);
				pst.setInt(3, year);
				rs = pst.executeQuery();
				auxList = PayrollAssembler.toPayrollDALDtoList(rs);
				for (PayrollDALDto payroll : auxList) {
					payrolls.add(payroll);
				}
			}		
			
			return payrolls;
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
	 * Método findInDates
	 * @param month
	 * @param year
	 * @return payrolls
	 */
	@Override
	public List<PayrollDALDto> findInDates(int month, int year) {
		List<PayrollDALDto> payrolls;		
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(Conf.getInstance().getProperty("TPAYROLLS_FINDINDATES"));				
			pst.setInt(1, month);
			pst.setInt(2, year);
			
			rs = pst.executeQuery();				
			
			payrolls = PayrollAssembler.toPayrollDALDtoList(rs);
			return payrolls;
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
