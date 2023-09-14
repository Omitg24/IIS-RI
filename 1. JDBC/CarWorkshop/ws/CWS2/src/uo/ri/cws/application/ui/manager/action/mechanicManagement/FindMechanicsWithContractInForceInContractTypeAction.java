package uo.ri.cws.application.ui.manager.action.mechanicManagement;

import java.util.List;

import console.Console;
import menu.Action;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.mechanic.MechanicService.MechanicBLDto;
import uo.ri.cws.application.ui.util.Printer;

/**
 * Titulo: Clase FindMechanicsWithContractInForceInContractTypeAction
 *
 * @author Omar Teixeira González, UO281847
 * @version 19 oct 2022
 */
public class FindMechanicsWithContractInForceInContractTypeAction implements Action {
	/**
	 * Método execute
	 * 
	 * @throws BusinessException
	 */
	@Override
	public void execute() throws BusinessException {
		//Get info
		String contractTypeName = Console.readString("Contract type name ");
		
		//Execute logic
		List<MechanicBLDto> mechanics;
		mechanics = BusinessFactory.forMechanicService().findMechanicsWithContractInForceInContractType(contractTypeName);
		
		//Print result
		Console.println("\nList of mechanics with contract in force in contract type\n");
		Printer.printMechanics(mechanics);
		Console.println("\tNumber of workers: " + mechanics.size());
	}
}
