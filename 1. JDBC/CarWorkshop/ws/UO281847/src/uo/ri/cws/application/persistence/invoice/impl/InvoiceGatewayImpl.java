package uo.ri.cws.application.persistence.invoice.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import jdbc.Jdbc;
import uo.ri.cws.application.persistence.PersistenceException;
import uo.ri.cws.application.persistence.invoice.InvoiceGateway;
import uo.ri.cws.application.persistence.util.Conf;

/**
 * Titulo: Clase InvoiceGatewayImpl
 *
 * @author Omar Teixeira González, UO281847
 * @version 15 oct 2022
 */
public class InvoiceGatewayImpl implements InvoiceGateway {
	/**
	 * Método add
	 * 
	 * @param invoiceDALDto
	 */
	@Override
	public void add(InvoiceDALDto invoiceDALDto) {
		Connection c = null;
		PreparedStatement pst = null;

		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(Conf.getInstance().getProperty("TINVOICES_ADD"));
			pst.setString(1, invoiceDALDto.id);
			pst.setLong(2, invoiceDALDto.number);
			pst.setDate(3, java.sql.Date.valueOf(invoiceDALDto.date));
			pst.setDouble(4, invoiceDALDto.vat);
			pst.setDouble(5, invoiceDALDto.amount);
			pst.setString(6, invoiceDALDto.state);
			pst.setLong(7, invoiceDALDto.version);

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
	 * 
	 * @param id
	 */
	@Override
	public void remove(String id) {
		// TODO Auto-generated method stub

	}

	/**
	 * Método update
	 * 
	 * @param invoiceDALDto
	 */
	@Override
	public void update(InvoiceDALDto invoiceDALDto) {
		// TODO Auto-generated method stub

	}

	/**
	 * Método findById
	 * 
	 * @param id
	 * @return invoiceDALDto
	 */
	@Override
	public Optional<InvoiceDALDto> findById(String id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	/**
	 * Método findAll
	 * 
	 * @return list
	 */
	@Override
	public List<InvoiceDALDto> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Método findByNumber
	 * 
	 * @param number
	 * @return invoiceDALDto
	 */
	@Override
	public Optional<InvoiceDALDto> findByNumber(Long number) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	/**
	 * Método getNextInvoiceNumber
	 * 
	 * @return long
	 */
	@Override
	public Long getNextInvoiceNumber() {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(Conf.getInstance().getProperty("TINVOICES_GETNEXTINVOICENUMBER"));
			rs = pst.executeQuery();

			if (rs.next()) {
				return rs.getLong(1) + 1; // +1, next
			} else { // there is none yet
				return 1L;
			}
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
