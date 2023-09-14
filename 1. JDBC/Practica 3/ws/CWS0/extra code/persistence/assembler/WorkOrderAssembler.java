package uo.ri.cws.application.persistence.workorder.assembler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.persistence.client.ClientGateway.ClientDALDto;
import uo.ri.cws.application.persistence.vehicle.VehicleGateway.VehicleDALDto;
import uo.ri.cws.application.persistence.workorder.WorkOrderGateway.WorkOrderDALDto;

public class WorkOrderAssembler {



	public static Optional<WorkOrderDALDto> toWorkOrderDALDto ( ResultSet rs ) throws SQLException {
		WorkOrderDALDto record = null;
		
		if (rs.next()) {
			record = resultSetToWorkOrderDALDto(rs);
			}
		return Optional.ofNullable(record);
		
	}

	public static List<WorkOrderDALDto> toWorkOrderDALDtoList(ResultSet rs) throws SQLException {
		List<WorkOrderDALDto> res = new ArrayList<>();
		while(rs.next()) {
			res.add( resultSetToWorkOrderDALDto(rs)	);
		}
		
		return res;
	}
	
	
	private static WorkOrderDALDto resultSetToWorkOrderDALDto ( ResultSet rs ) throws SQLException {
		WorkOrderDALDto record = new WorkOrderDALDto();
		
		record.id = rs.getString("id");
		record.version = rs.getLong("version");

		record.vehicle_id = rs.getString( "vehicle_Id");
		record.description = rs.getString( "description");
		record.date =  rs.getTimestamp("date").toLocalDateTime();
		record.amount = rs.getDouble("amount");
		record.state = rs.getString( "state");
		record.mechanic_id = rs.getString( "mechanic_Id");
		record.invoice_id = rs.getString( "invoice_Id");
		
		return record;		
	}
	
	public static Optional<ClientDALDto> toClientDALDto(ResultSet rs) throws SQLException {
		if (rs.next()) {
			return Optional.of(resultSetToClientDALDto( rs ));
		}
		else 	
			return Optional.ofNullable(null);
	}


	private static ClientDALDto resultSetToClientDALDto(ResultSet rs) throws SQLException {
		ClientDALDto record = new ClientDALDto();
		record.id = rs.getString("id");
		record.version = rs.getLong("version");
		
		record.dni = rs.getString("dni");
		record.name = rs.getString("name");
		record.surname = rs.getString("surname");
		record.phone = rs.getString("phone");
		record.email = rs.getString("email");
		record.street = rs.getString("street");
		record.city = rs.getString("City");
		record.zipcode = rs.getString("Zipcode");
		return record;
	}

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
