package uo.ri.cws.application.persistence.vehicle.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import jdbc.Jdbc;
import uo.ri.cws.application.persistence.PersistenceException;
import uo.ri.cws.application.persistence.util.Conf;
import uo.ri.cws.application.persistence.vehicle.VehicleGateway;
import uo.ri.cws.application.persistence.workorder.assembler.WorkorderAssembler;

/**
 * Titulo: Clase VehicleGatewayImpl
 *
 * @author Omar Teixeira González, UO281847
 * @version 18 oct 2022
 */
public class VehicleGatewayImpl implements VehicleGateway {
	/**
	 * Método add
	 * @param vehicleDALDto
	 */
	@Override
	public void add(VehicleDALDto vehicleDALDtot) {
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
	 * @param vehicleDALDto
	 */
	@Override
	public void update(VehicleDALDto vehicleDALDto) {
		// TODO Auto-generated method stub

	}

	/**
	 * Método findById
	 * @param id
	 * @return VehicleDALDto
	 */
	@Override
	public Optional<VehicleDALDto> findById(String id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	/**
	 * Método findAll
	 * @return list
	 */
	@Override
	public List<VehicleDALDto> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Método findByClient
	 * @param clientId
	 * @return list
	 */
	@Override
	public List<VehicleDALDto> findByClient(String clientId) {
		List<VehicleDALDto> vehicles = null;		
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			c = Jdbc.getCurrentConnection();
			
			pst = c.prepareStatement(Conf.getInstance().getProperty("TVEHICLES_FINDBYCLIENT"));
			pst.setString(1, clientId);
			
			rs = pst.executeQuery();
			
			vehicles = WorkorderAssembler.toVehicleDALDtoList(rs);
			return vehicles;
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
