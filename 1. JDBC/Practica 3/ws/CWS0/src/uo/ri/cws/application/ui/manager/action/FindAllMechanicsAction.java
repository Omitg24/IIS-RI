package uo.ri.cws.application.ui.manager.action;

import java.util.List;

import console.Console;
import menu.Action;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.mechanic.MechanicService.MechanicBLDto;
import uo.ri.cws.application.ui.util.Printer;

/**
 * Titulo: Clase FindAllMechanicsAction
 *
 * @author Omar Teixeira González, UO281847
 * @version 13 oct 2022
 */
public class FindAllMechanicsAction implements Action {	
	/**
	 * Método execute
	 * @throws BusinessException 
	 */
	@Override
	public void execute() throws BusinessException {
		//Show method
		Console.println("\nList of mechanics \n");
		
		//Execute logic
		List<MechanicBLDto> mechanics = BusinessFactory.forMechanicService().findAllMechanics();
		
		//Print result
		Printer.printMechanics(mechanics);
	}
}
