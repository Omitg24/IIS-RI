package uo.ri.cws.application.business.invoice.create.commands;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.invoice.InvoicingService.WorkOrderForInvoicingBLDto;
import uo.ri.cws.application.business.mechanic.assembler.MechanicAssembler;

/**
 * Titulo: Clase FindNotInvoicedWorkOrders
 *
 * @author Omar Teixeira González, UO281847
 * @version 13 oct 2022
 */
public class FindNotInvoicedWorkOrders {
	/**
	 * Constante SQL
	 * Process:
	 * 
	 * - Ask customer dni
	 * 
	 * - Display all uncharged workorder (state <> 'INVOICED'). For each workorder,
	 * display id, vehicle id, date, state, amount and description
	 */
	private static final String SQL = "select a.id, a.description, a.date, a.state, a.amount "
			+ "from TWorkOrders as a, TVehicles as v, TClients as c " + "where a.vehicle_id = v.id "
			+ "	and v.client_id = c.id " + "	and state <> 'INVOICED'" + "	and dni like ?";
	/**
	 * Constante URL
	 */
	private static final String URL = "jdbc:hsqldb:hsql://localhost";
	/**
	 * Constante USER
	 */
	private static final String USER = "sa";
	/**
	 * Constante PASS
	 */
	private static final String PASS = "";
	
	/**
	 * Atributo dni
	 */
	private String dni;
	
	/**
	 * Constructor FindNotInvoicedWorkOrders
	 * @param dni
	 */
	public FindNotInvoicedWorkOrders(String dni) {
		this.dni=dni;
	}
	
	/**
	 * Método execute
	 * @throws BusinessException
	 */
	public List<WorkOrderForInvoicingBLDto> execute() throws BusinessException {
		
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = DriverManager.getConnection(URL, USER, PASS);

			pst = c.prepareStatement(SQL);
			pst.setString(1, dni);

			rs = pst.executeQuery();			
			return MechanicAssembler.toWorkOrderForInvoicingDtoList(rs);
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
