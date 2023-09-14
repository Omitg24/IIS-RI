package uo.ri.ui.manager.mechanic.action;

import java.util.List;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.mechanic.MechanicCrudService;
import uo.ri.cws.application.service.mechanic.MechanicCrudService.MechanicDto;
import uo.ri.ui.util.Printer;
import uo.ri.util.console.Console;
import uo.ri.util.menu.Action;

//GESTI�N DE MEC�NICOS AMPLIADO - Listado de mec�nicos con contrato en vigor
/**
 * T�tulo: Clase FindMechanicsInForceAction
 * Descripci�n: Contiene la acci�n de listar los mec�nicos con contrato en vigor
 * de la base de datos
 *
 * @author Omar Teixeira Gonz�lez, UO281847
 * @version 16 nov 2022
 */
public class FindMechanicsInForceAction implements Action {
	/**
	 * M�todo execute
	 * Ejecuta la acci�n de la clase
	 */
	@Override
	public void execute() throws BusinessException {		
		// Invoke the service
		MechanicCrudService mService = Factory.service.forMechanicCrudService();
		List<MechanicDto> mechanics = mService.findMechanicsInForce();
		
		// Show result
		Console.println("\nList of mechanics with contract in force\n");
		Printer.printMechanics(mechanics);
		Console.println("\tNumber of workers: " + mechanics.size());
	}
}
