package uo.ri.cws.application.business.workorder.assembler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.business.workorder.WorkorderService.WorkOrderBLDto;
import uo.ri.cws.application.persistence.workorder.WorkOrderGateway.WorkOrderDALDto;

/**
 * Titulo: Clase WorkOrderAssembler
 *
 * @author Omar Teixeira González, UO281847
 * @version 27 oct 2022
 */
public class WorkOrderAssembler {
	/**
	 * Método toBLDto 
	 * Pasa un WorkOrderDALDto a un WorkOrderBLDto
	 * 
	 * @param arg
	 * @return result
	 */
	public static Optional<WorkOrderBLDto> toBLDto(Optional<WorkOrderDALDto> arg) {
		Optional<WorkOrderBLDto> result = arg.isEmpty() ? Optional.ofNullable(null)
				: Optional.ofNullable(toDto(arg.get()));
		return result;
	}
	
	/**
	 * Método toDto 
	 * Pasa un WorkOrderDALDto a un WorkOrderBLDto
	 * 
	 * @param rs
	 * @return record
	 * @throws SQLException
	 */
	public static WorkOrderBLDto toDto(WorkOrderDALDto workOrderDALDto) {
		WorkOrderBLDto record = new WorkOrderBLDto();
		record.id = workOrderDALDto.id;
		record.version = workOrderDALDto.version;
		record.total = workOrderDALDto.amount;
		record.date = workOrderDALDto.date;
		record.description = workOrderDALDto.description;
		record.state = workOrderDALDto.state;
		record.invoiceId = workOrderDALDto.invoice_id;
		record.mechanicId = workOrderDALDto.mechanic_id;
		record.vehicleId = workOrderDALDto.vehicle_id;
		record.usedForVoucher = workOrderDALDto.usedforvoucher;
		return record;
	}
	
	/**
	 * Método toDtoList 
	 * Pasa una lista de WorkOrderDALDto a una lista de WorkOrderBLDto
	 * 
	 * @param arg
	 * @return result
	 */
	public static List<WorkOrderBLDto> toDtoList(List<WorkOrderDALDto> arg) {
		List<WorkOrderBLDto> result = new ArrayList<WorkOrderBLDto>();
		for (WorkOrderDALDto mr : arg) {
			result.add(toDto(mr));
		}
		return result;
	}
}
