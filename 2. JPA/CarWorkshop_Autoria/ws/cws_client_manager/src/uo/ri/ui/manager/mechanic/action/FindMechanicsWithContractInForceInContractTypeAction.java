package uo.ri.ui.manager.mechanic.action;

import java.util.List;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.mechanic.MechanicCrudService;
import uo.ri.cws.application.service.mechanic.MechanicCrudService.MechanicDto;
import uo.ri.ui.util.Printer;
import uo.ri.util.console.Console;
import uo.ri.util.menu.Action;

/**
 * Título: Clase FindMechanicsWithContractInForceInContractTypeAction
 * Descripción: Contiene la acción de listar los mecánicos con contrato en vigor
 * para un tipo de contrato de la base de datos
 *
 * @author Omar Teixeira González, UO281847
 * @version 16 nov 2022
 */
public class FindMechanicsWithContractInForceInContractTypeAction implements Action {
	/**
	 * Método execute
	 * Ejecuta la acción de la clase
	 */
	@Override
	public void execute() throws BusinessException {
		// Ask the user for data
		String contractTypeName = Console.readString("Contract type name ");
		
		// Invoke the service
		MechanicCrudService mService = Factory.service.forMechanicCrudService();
		List<MechanicDto> mechanics = mService.findMechanicsWithContractInForceInContractType(contractTypeName);			
		
		// Show result
		Console.println("\nList of mechanics with contract in force in contract type\n");
		Printer.printMechanics(mechanics);
		Console.println("\tNumber of workers: " + mechanics.size());
	}
}
