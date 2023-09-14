package uo.ri.cws.application.ui.manager.action.mechanicManagement;

import java.util.List;

import console.Console;
import menu.Action;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.mechanic.MechanicService.MechanicBLDto;
import uo.ri.cws.application.ui.util.Printer;

/**
 * Titulo: Clase FindMechanicsWithContractInForceAction
 *
 * @author Omar Teixeira González, UO281847
 * @version 18 oct 2022
 */
public class FindMechanicsWithContractInForceAction implements Action {
	/**
	 * Método execute
	 * 
	 * @throws BusinessException
	 */
	@Override
	public void execute() throws BusinessException {		
		//Execute logic
		List<MechanicBLDto> mechanics;
		mechanics = BusinessFactory.forMechanicService().findMechanicsInForce();
		
		//Print results		
		Console.println("\nList of mechanics with contract in force\n");
		Printer.printMechanics(mechanics);
	}
}
