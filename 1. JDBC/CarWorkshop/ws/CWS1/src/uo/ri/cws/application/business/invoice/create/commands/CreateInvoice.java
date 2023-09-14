package uo.ri.cws.application.business.invoice.create.commands;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import math.Round;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.invoice.InvoicingService.InvoiceBLDto;
import uo.ri.cws.application.business.invoice.InvoicingService.InvoiceBLDto.InvoiceState;

/**
 * Titulo: Clase CreateInvoice
 *
 * @author Omar Teixeira González, UO281847
 * @version 13 oct 2022
 */
public class CreateInvoice {
	/**
	 * Constante SQL_CHECK_WORKORDER_STATE
	 */
	private static final String SQL_CHECK_WORKORDER_STATE = "select state from TWorkOrders where id = ?";
	/**
	 * Constante SQL_LAST_INVOICE_NUMBER
	 */
	private static final String SQL_LAST_INVOICE_NUMBER = "select max(number) from TInvoices";
	/**
	 * Constante SQL_FIND_WORKORDER_AMOUNT
	 */
	private static final String SQL_FIND_WORKORDER_AMOUNT = "select amount from TWorkOrders where id = ?";
	/**
	 * Constante SQL_INSERT_INVOICE
	 */
	private static final String SQL_INSERT_INVOICE = "insert into TInvoices(id, number, date, vat, amount, state, version) "
			+ "	values(?, ?, ?, ?, ?, ?, ?)";
	/**
	 * Constante SQL_LINK_WORKORDER_TO_INVOICE
	 */
	private static final String SQL_LINK_WORKORDER_TO_INVOICE = "update TWorkOrders set invoice_id = ? where id = ?";
	/**
	 * Constante SQL_MARK_WORKORDER_AS_INVOICED
	 */
	private static final String SQL_MARK_WORKORDER_AS_INVOICED = "update TWorkOrders set state = 'INVOICED' where id = ?";
	/**
	 * Constante SQL_FIND_WORKORDERS
	 */
	private static final String SQL_FIND_WORKORDERS = "select * from TWorkOrders where id = ?";
	/**
	 * Constante SQL_UPDATEVERSION_WORKORDERS
	 */
	private static final String SQL_UPDATEVERSION_WORKORDERS = "update TWorkOrders set version=version+1 where id = ?";	
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
	 * Atributo connection
	 */
	private Connection connection;
	/**
	 * Atributo workOrderIds
	 */
	private List<String> workOrderIds;
	
	/**
	 * Constructor CreateInvoice
	 * @param workOrderIds
	 */
	public CreateInvoice(List<String> workOrderIds) {
		this.workOrderIds=workOrderIds;
	}
	
	
	/**
	 * Método execute
	 * @return invoice
	 * @throws BusinessException
	 */
	public InvoiceBLDto execute() throws BusinessException {
		InvoiceBLDto invoice = new InvoiceBLDto();
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);

			if (!checkWorkOrdersExist(workOrderIds))
				throw new BusinessException("Workorder does not exist");
			if (!checkWorkOrdersFinished(workOrderIds))
				throw new BusinessException("Workorder is not finished yet");

			long numberInvoice = generateInvoiceNumber();
			LocalDate dateInvoice = LocalDate.now();
			double amount = calculateTotalInvoice(workOrderIds); // vat not included
			double vat = vatPercentage(amount, dateInvoice);
			double total = amount * (1 + vat / 100); // vat included
			total = Round.twoCents(total);

			String idInvoice = createInvoice(numberInvoice, dateInvoice, vat, total);
			linkWorkOrdersToInvoice(idInvoice, workOrderIds);
			markWorkOrderAsInvoiced(workOrderIds);
			updateVersion(workOrderIds);
			
			invoice.id=idInvoice;
			invoice.total=total;
			invoice.vat=vat;
			invoice.number=numberInvoice;
			invoice.date=dateInvoice;
			invoice.state=InvoiceState.NOT_YET_PAID;

			connection.commit();
			
			return invoice;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					/* ignore */ }
		}
	}
	
	/**
	 * Método updateVersion
	 * @param workOrderIds
	 * @throws SQLException
	 */
	private void updateVersion(List<String> workOrderIds) throws SQLException {
		PreparedStatement pst = null;

		try {
			pst = connection.prepareStatement(SQL_UPDATEVERSION_WORKORDERS);

			for (String workOrderID : workOrderIds) {
				pst.setString(1, workOrderID);
				pst.executeUpdate();
			}
		} finally {
			if (pst != null)
				try {
					pst.close();
				} catch (SQLException e) {
					/* ignore */ }
		}
	}
	
	
	/**
	 * Método checkWorkOrderExist
	 * checks whether every work order exist
	 * @param workOrderIDS
	 * @return boolean
	 * @throws SQLException
	 * @throws BusinessException
	 */
	private boolean checkWorkOrdersExist(List<String> workOrderIDS) throws SQLException, BusinessException {
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = connection.prepareStatement(SQL_FIND_WORKORDERS);

			for (String workOrderID : workOrderIDS) {
				pst.setString(1, workOrderID);

				rs = pst.executeQuery();
				if (rs.next() == false) {
					return false;
				}

			}
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
		return true;
	}
	
	/**
	 * Método checkWorkOrdersFinished
	 * checks whether every work order id is FINISHED
	 * @param workOrderIDS
	 * @return boolean
	 * @throws SQLException
	 * @throws BusinessException
	 */
	private boolean checkWorkOrdersFinished(List<String> workOrderIDS) throws SQLException, BusinessException {
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = connection.prepareStatement(SQL_CHECK_WORKORDER_STATE);

			for (String workOrderID : workOrderIDS) {
				pst.setString(1, workOrderID);

				rs = pst.executeQuery();
				rs.next();
				String state = rs.getString(1);
				if (!"FINISHED".equalsIgnoreCase(state)) {
					return false;
				}

			}
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
		return true;
	}
	
	
	/**
	 * Método generateInvoiceNumber
	 * Generates next invoice number (not to be confused with the inner id)
	 * @return long
	 * @throws SQLException
	 */
	private Long generateInvoiceNumber() throws SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = connection.prepareStatement(SQL_LAST_INVOICE_NUMBER);
			rs = pst.executeQuery();

			if (rs.next()) {
				return rs.getLong(1) + 1; // +1, next
			} else { // there is none yet
				return 1L;
			}
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
	 * Método calculateTotalInvoice
	 * Compute total amount of the invoice (as the total of individual work orders'
	 * amount
	 * @param workOrderIDS
	 * @return totalInvoice
	 * @throws BusinessException
	 * @throws SQLException
	 */
	private double calculateTotalInvoice(List<String> workOrderIDS) throws BusinessException, SQLException {

		double totalInvoice = 0.0;
		for (String workOrderID : workOrderIDS) {
			totalInvoice += getWorkOrderTotal(workOrderID);
		}
		return totalInvoice;
	}

	/**
	 * Método getWorkOrderTotal
	 * checks whether every work order id is FINISHED
	 * @param workOrderID
	 * @return money
	 * @throws SQLException
	 * @throws BusinessException
	 */
	private Double getWorkOrderTotal(String workOrderID) throws SQLException, BusinessException {
		PreparedStatement pst = null;
		ResultSet rs = null;
		Double money = 0.0;

		try {
			pst = connection.prepareStatement(SQL_FIND_WORKORDER_AMOUNT);
			pst.setString(1, workOrderID);

			rs = pst.executeQuery();
			if (rs.next() == false) {
				throw new BusinessException("Workorder " + workOrderID + " doesn't exist");
			}

			money = rs.getDouble(1);

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
		return money;

	}
	
	/**
	 * Método vatPercentage
	 * returns vat percentage
	 * @param totalInvoice
	 * @param dateInvoice
	 * @return double
	 */
	private double vatPercentage(double totalInvoice, LocalDate dateInvoice) {
		return LocalDate.parse("2012-07-01").isBefore(dateInvoice) ? 21.0 : 18.0;

	}

	/**
	 * Método createInvoice
	 * Creates the invoice in the database; returns the id
	 * @param numberInvoice
	 * @param dateInvoice
	 * @param vat
	 * @param total
	 * @return idInvoice
	 * @throws SQLException
	 */
	private String createInvoice(long numberInvoice, LocalDate dateInvoice, double vat, double total)
			throws SQLException {

		PreparedStatement pst = null;
		String idInvoice;

		try {
			idInvoice = UUID.randomUUID().toString();

			pst = connection.prepareStatement(SQL_INSERT_INVOICE);
			pst.setString(1, idInvoice);
			pst.setLong(2, numberInvoice);
			pst.setDate(3, java.sql.Date.valueOf(dateInvoice));
			pst.setDouble(4, vat);
			pst.setDouble(5, total);
			pst.setString(6, "NOT_YET_PAID");
			pst.setLong(7, 1L);

			pst.executeUpdate();

		} finally {
			if (pst != null)
				try {
					pst.close();
				} catch (SQLException e) {
					/* ignore */ }
		}
		return idInvoice;
	}

	/**
	 * Método linkWorkOrdersToInvoice
	 * Set the invoice number field in work order table to the invoice number
	 * generated
	 * @param invoiceId
	 * @param workOrderIDS
	 * @throws SQLException
	 */
	private void linkWorkOrdersToInvoice(String invoiceId, List<String> workOrderIDS) throws SQLException {

		PreparedStatement pst = null;
		try {
			pst = connection.prepareStatement(SQL_LINK_WORKORDER_TO_INVOICE);

			for (String workOrderId : workOrderIDS) {
				pst.setString(1, invoiceId);
				pst.setString(2, workOrderId);

				pst.executeUpdate();
			}
		} finally {
			if (pst != null)
				try {
					pst.close();
				} catch (SQLException e) {
					/* ignore */ }
		}
	}

	/**
	 * Método markWorkOrderAsInvoiced
	 * Sets state to INVOICED for every workorder
	 * @param ids
	 * @throws SQLException
	 */
	private void markWorkOrderAsInvoiced(List<String> ids) throws SQLException {

		PreparedStatement pst = null;
		try {
			pst = connection.prepareStatement(SQL_MARK_WORKORDER_AS_INVOICED);

			for (String id : ids) {
				pst.setString(1, id);

				pst.executeUpdate();
			}
		} finally {
			if (pst != null)
				try {
					pst.close();
				} catch (SQLException e) {
					/* ignore */ }
		}
	}
}
