package uo.ri.cws.application.persistence.workorder.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jdbc.Jdbc;
import uo.ri.cws.application.persistence.PersistenceException;
import uo.ri.cws.application.persistence.util.Conf;
import uo.ri.cws.application.persistence.workorder.WorkOrderGateway;
import uo.ri.cws.application.persistence.workorder.assembler.WorkorderAssembler;

/**
 * Titulo: Clase WorkOrderGatewayImpl
 *
 * @author Omar Teixeira González, UO281847
 * @version 18 oct 2022
 */
public class WorkOrderGatewayImpl implements WorkOrderGateway {

	/**
	 * Método add
	 * @param workOrderDALDto
	 */
	@Override
	public void add(WorkOrderDALDto workOrderDALDto) {
		// TODO Auto-generated method stub

	}

	/**
	 * Método remove
	 * @param id
	 */
	@Override
	public void remove(String id) {
		// TODO Auto-generated method stub

	}

	/**
	 * Método update
	 * @param workOrderDALDto
	 */
	@Override
	public void update(WorkOrderDALDto workOrderDALDto) {
		Connection c = null;
		PreparedStatement pst = null;

		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(Conf.getInstance().getProperty("TWORKORDERS_UPDATE"));
			pst.setDouble(1, workOrderDALDto.amount);
			pst.setString(2, workOrderDALDto.description);
			pst.setString(3, workOrderDALDto.state);
			pst.setString(4, workOrderDALDto.invoice_id);
			pst.setString(5, workOrderDALDto.id);
			
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
	 * @return WorkOrderDALDto
	 */
	@Override
	public Optional<WorkOrderDALDto> findById(String workOrderId) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(Conf.getInstance().getProperty("TWORKORDERS_FINDBYID"));
			pst.setString(1, workOrderId);

			rs = pst.executeQuery();

			return WorkorderAssembler.toWorkOrderDALDto(rs);
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
	public List<WorkOrderDALDto> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	//GESTIÓN DE MECÁNICOS AMPLIADO ---- Verificación de borrado
	/**
	 * Método findByMechanic
	 * @param workOrders
	 */
	@Override
	public List<WorkOrderDALDto> findByMechanic(String mechanicId) {
		List<WorkOrderDALDto> workOrders = null;
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(Conf.getInstance().getProperty("TWORKORDERS_FINDBYMECHANIC"));
			pst.setString(1, mechanicId);

			rs = pst.executeQuery();

			workOrders = WorkorderAssembler.toWorkOrderDALDtoList(rs);
			return workOrders;
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
	 * Método findNotInvoicedForVehicles
	 * @param vehicleIds
	 * @return list
	 */
	@Override
	public List<WorkOrderDALDto> findNotInvoicedForVehicles(List<String> vehicleIds) {
		List<WorkOrderDALDto> workOrders = new ArrayList<WorkOrderDALDto>();
		List<WorkOrderDALDto> auxList = null;
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(Conf.getInstance().getProperty("TWORKORDERS_FINDNOTINVOICEDFORVEHICLES"));
			for (String id : vehicleIds) {
				pst.setString(1, id);
				rs = pst.executeQuery();
				auxList = WorkorderAssembler.toWorkOrderDALDtoList(rs);
				for (WorkOrderDALDto workOrder : auxList) {
					workOrders.add(workOrder);
				}
			}		
			
			return workOrders;
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
	public List<WorkOrderDALDto> findByVehicleId(String vehicleId) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Método findByIds
	 * @param ids
	 */
	@Override
	public List<WorkOrderDALDto> findByIds(List<String> ids) {
		List<WorkOrderDALDto> workOrders = new ArrayList<WorkOrderDALDto>();
		List<WorkOrderDALDto> auxList = null;
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(Conf.getInstance().getProperty("TWORKORDERS_FINDBYIDS"));
			for (String id : ids) {
				pst.setString(1, id);
				rs = pst.executeQuery();
				auxList = WorkorderAssembler.toWorkOrderDALDtoList(rs);
				for (WorkOrderDALDto workOrder : auxList) {
					workOrders.add(workOrder);
				}
			}			
			
			return workOrders;
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
	public List<WorkOrderDALDto> findByInvoice(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WorkOrderDALDto> findInvoiced() {
		// TODO Auto-generated method stub
		return null;
	}
}
