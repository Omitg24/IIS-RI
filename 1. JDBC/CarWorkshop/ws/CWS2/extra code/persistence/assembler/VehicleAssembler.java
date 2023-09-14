package uo.ri.cws.application.persistence.vehicle.assembler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.persistence.vehicle.VehicleGateway.VehicleDALDto;

public class VehicleAssembler {
	public static List<VehicleDALDto> toVehicleDALDtoList(ResultSet rs) throws SQLException {
		List<VehicleDALDto>  result = new ArrayList<VehicleDALDto> ();
		while ( rs.next() ) {
			
			result.add(resultSetToVehicleDALDto(rs));
		}
		return result;
		
	}


	public static Optional<VehicleDALDto> toVehicleDALDto(ResultSet rs) throws SQLException {
		VehicleDALDto result = null;
		if (rs.next())
			result = resultSetToVehicleDALDto(rs);

		return Optional.ofNullable(result);
	}
	
	private static VehicleDALDto resultSetToVehicleDALDto(ResultSet rs) throws SQLException {
		VehicleDALDto record = new VehicleDALDto();
		record.version = rs.getLong("version");

		record.id = rs.getString("id");
		record.platenumber = rs.getString("platenumber");
		record.make = rs.getString("make");
		record.model = rs.getString("model");
		record.vehicletype_id = rs.getString("vehicletype_id");			
		record.client_id = rs.getString("client_id");	
		return record;
	}	

}
