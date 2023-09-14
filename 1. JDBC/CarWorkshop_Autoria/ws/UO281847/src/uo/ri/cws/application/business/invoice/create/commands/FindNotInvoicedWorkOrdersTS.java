package uo.ri.cws.application.business.invoice.create.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.invoice.InvoicingService.WorkOrderForInvoicingBLDto;
import uo.ri.cws.application.business.invoice.assembler.InvoicingAssembler;
import uo.ri.cws.application.business.util.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.client.ClientGateway.ClientDALDto;
import uo.ri.cws.application.persistence.vehicle.VehicleGateway.VehicleDALDto;

/**
 * Titulo: Clase FindNotInvoicedWorkOrders
 *
 * @author Omar Teixeira González, UO281847
 * @version 13 oct 2022
 */
public class FindNotInvoicedWorkOrdersTS implements Command<List<WorkOrderForInvoicingBLDto>> {
	/**
	 * Atributo dni
	 */
	private String dni;

	/**
	 * Constructor FindNotInvoicedWorkOrders
	 * 
	 * @param dni
	 */
	public FindNotInvoicedWorkOrdersTS(String dni) {
		checkArguments(dni);
		this.dni = dni;
	}

	/**
	 * Método checkArguments Comprueba la integridad de los argumentos
	 * 
	 * @param dni
	 */
	private void checkArguments(String dni) {
		Argument.isNotEmpty(dni, "El dni a buscar no puede ser null ni estar vacío");
	}

	/**
	 * Método execute Ejecuta la acción de la clase, findNotInvoicedWorkOrders
	 * 
	 * @throws BusinessException
	 */
	public List<WorkOrderForInvoicingBLDto> execute() throws BusinessException {
		Optional<ClientDALDto> client;
		List<VehicleDALDto> vehicles;
		List<String> vehicleIds;
		List<WorkOrderForInvoicingBLDto> workOrders;
		
		client = PersistenceFactory.forClient().findByDni(dni);		
		if (client.isEmpty()) {
			throw new BusinessException("El cliente debe existir");
		}
		
		vehicles = PersistenceFactory.forVehicle().findByClient(client.get().id);		
		vehicleIds = getVehicleIds(vehicles);
		
		workOrders = InvoicingAssembler.toInvoicingWorkOrderList(
				PersistenceFactory.forWorkOrder().findNotInvoicedForVehicles(vehicleIds));				
		return workOrders;
	}
	
	/**
	 * Método getVehicleIds
	 * @param vehicles
	 * @return vehicleIds
	 */
	public List<String> getVehicleIds(List<VehicleDALDto> vehicles){
		List<String> vehicleIds = new ArrayList<String>();
		
		for (VehicleDALDto vehicle : vehicles) {
			vehicleIds.add(vehicle.id);
		}
		
		return vehicleIds;
	}
}
