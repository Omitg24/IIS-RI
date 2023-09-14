package uo.ri.cws.application.ui.foreman.action;

import java.util.List;

import console.Console;
import menu.Action;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.vehicle.VehicleService.VehicleBLDto;
import uo.ri.cws.application.ui.util.Printer;

/**
 * Titulo: Clase FindByClientAction
 *
 * @author Omar Teixeira González, UO281847
 * @version 28 oct 2022
 */
public class FindByClientAction implements Action {

	/**
	 * Método execute
	 * 
	 * @throws BusinessException
	 */
	@Override
	public void execute() throws BusinessException {
		//Get info
		String dni = Console.readString("Client dni ");
		
		//Execute logic
		List<VehicleBLDto> vehicles;
		vehicles = BusinessFactory.forVehicle().findByClientDni(dni);
		
		//Print results
		Console.println("\nList of vehicles by client\n");
		Printer.printVehicles(vehicles);
	}
}
