package uo.ri.ui.manager.mechanic.action;

import java.util.List;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.mechanic.MechanicCrudService;
import uo.ri.cws.application.service.mechanic.MechanicCrudService.MechanicDto;
import uo.ri.ui.util.Printer;
import uo.ri.util.console.Console;
import uo.ri.util.menu.Action;

//GESTIÓN DE MECÁNICOS AMPLIADO - Listado de mecánicos con contrato en vigor
/**
 * Título: Clase FindMechanicsInForceAction
 * Descripción: Contiene la acción de listar los mecánicos con contrato en vigor
 * de la base de datos
 *
 * @author Omar Teixeira González, UO281847
 * @version 16 nov 2022
 */
public class FindMechanicsInForceAction implements Action {
	/**
	 * Método execute
	 * Ejecuta la acción de la clase
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
