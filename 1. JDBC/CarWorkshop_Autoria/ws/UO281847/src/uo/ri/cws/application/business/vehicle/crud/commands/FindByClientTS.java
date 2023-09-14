package uo.ri.cws.application.business.vehicle.crud.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.util.Command;
import uo.ri.cws.application.business.vehicle.VehicleService.VehicleBLDto;
import uo.ri.cws.application.business.vehicle.assembler.VehicleAssembler;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.client.ClientGateway.ClientDALDto;

//PRUEBA DE AUTORÍA
/**
 * Titulo: Clase FindByClientDni
 *
 * @author Omar Teixeira González, UO281847
 * @version 28 oct 2022
 */
public class FindByClientTS implements Command<List<VehicleBLDto>> {
	/**
	 * Atributo dni
	 */
	private String dni;
	
	/**
	 * Constructor FindByClientDni
	 * @param dni
	 */
	public FindByClientTS(String dni) {
		checkArguments(dni);
		this.dni=dni;
	}
	
	/**
	 * Método checkArguments 
	 * Comprueba la integridad de los argumentos
	 * 
	 * @param dni
	 */
	private void checkArguments(String dni) {
		Argument.isNotEmpty(dni, "El dni del cliente con vehiculos a buscar no puede ser null ni estar vacío");
	}
	
	/**
	 * Método execute
	 * Ejecuta la acción de la clase
	 * 
	 * @return vehicles
	 */
	@Override
	public List<VehicleBLDto> execute() throws BusinessException {
		List<VehicleBLDto> vehicles = new ArrayList<VehicleBLDto>();
		Optional<ClientDALDto> client;
		
		client = PersistenceFactory.forClient().findByDni(dni);
		
		if (client.isPresent()) {
			vehicles = VehicleAssembler.toDtoList(PersistenceFactory.forVehicle().findByClient(client.get().id));
		}
		
		return vehicles;
	}

}
