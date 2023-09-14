package uo.ri.cws.application.business.vehicle.assembler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.business.vehicle.VehicleService.VehicleBLDto;
import uo.ri.cws.application.persistence.vehicle.VehicleGateway.VehicleDALDto;

//PRUEBA DE AUTORÍA
/**
 * Titulo: Clase VehicleAssembler
 *
 * @author Omar Teixeira González, UO281847
 * @version 28 oct 2022
 */
public class VehicleAssembler {
	/**
	 * Método toBLDto 
	 * Pasa un VehicleDALDto a un VehicleBLDto
	 * 
	 * @param arg
	 * @return result
	 */
	public static Optional<VehicleBLDto> toBLDto(Optional<VehicleDALDto> arg) {
		Optional<VehicleBLDto> result = arg.isEmpty() ? Optional.ofNullable(null)
				: Optional.ofNullable(toDto(arg.get()));
		return result;
	}
	
	/**
	 * Método toDto 
	 * Pasa un VehicleDALDto a un VehicleBLDto
	 * 
	 * @param rs
	 * @return record
	 * @throws SQLException
	 */
	public static VehicleBLDto toDto(VehicleDALDto vehicleDALDto) {
		VehicleBLDto record = new VehicleBLDto();
		record.id = vehicleDALDto.id;
		record.version = vehicleDALDto.version;
		record.plateNumber = vehicleDALDto.platenumber;
		record.make = vehicleDALDto.make;
		record.model = vehicleDALDto.model;
		record.clientId = vehicleDALDto.client_id;
		record.vehicleTypeId = vehicleDALDto.vehicletype_id;
		return record;
	}
	
	/**
	 * Método toDtoList 
	 * Pasa una lista de VehicleDALDto a una lista de VehicleBLDto
	 * 
	 * @param arg
	 * @return result
	 */
	public static List<VehicleBLDto> toDtoList(List<VehicleDALDto> arg) {
		List<VehicleBLDto> result = new ArrayList<VehicleBLDto>();
		for (VehicleDALDto mr : arg)
			result.add(toDto(mr));
		return result;
	}
}
