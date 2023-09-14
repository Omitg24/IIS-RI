package uo.ri.cws.application.persistence.client.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import jdbc.Jdbc;
import uo.ri.cws.application.persistence.PersistenceException;
import uo.ri.cws.application.persistence.client.ClientGateway;
import uo.ri.cws.application.persistence.util.Conf;
import uo.ri.cws.application.persistence.workorder.assembler.WorkorderAssembler;

/**
 * Titulo: Clase ClientGatewayImpl
 *
 * @author Omar Teixeira González, UO281847
 * @version 18 oct 2022
 */
public class ClientGatewayImpl implements ClientGateway {
	/**
	 * Método add
	 * @param clientDALDto
	 */
	@Override
	public void add(ClientDALDto clientDALDto) {
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
	 * @param clientDALDto
	 */
	@Override
	public void update(ClientDALDto clientDALDto) {
		// TODO Auto-generated method stub

	}

	/**
	 * Método findById
	 * @param id
	 * @return ClientDALDto
	 */
	@Override
	public Optional<ClientDALDto> findById(String id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	/**
	 * Método findAll
	 * @return list
	 */
	@Override
	public List<ClientDALDto> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Método findByDni
	 * @param clientDni
	 * @return ClientDALDto
	 */
	@Override
	public Optional<ClientDALDto> findByDni(String clientDni) {
		Optional<ClientDALDto> client = null;
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			c = Jdbc.getCurrentConnection();
			
			pst = c.prepareStatement(Conf.getInstance().getProperty("TCLIENTS_FINDBYDNI"));
			pst.setString(1, clientDni);
			
			rs = pst.executeQuery();
			
			client = WorkorderAssembler.toClientDALDto(rs); 
			return client;
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
