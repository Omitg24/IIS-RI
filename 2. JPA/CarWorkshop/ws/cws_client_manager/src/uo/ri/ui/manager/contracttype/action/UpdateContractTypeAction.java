package uo.ri.ui.manager.contracttype.action;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.contracttype.ContractTypeService;
import uo.ri.cws.application.service.contracttype.ContractTypeService.ContractTypeDto;
import uo.ri.ui.util.Printer;
import uo.ri.util.console.Console;
import uo.ri.util.menu.Action;

//GESTI�N DE TIPOS DE CONTRATO - Actualizar tipos de contrato
/**
 * Titulo: Clase UpdateContractTypeAction
 * Descripci�n: Contiene la acci�n de actualizar un tipo de contrato de la base 
 * de datos
 *
 * @author Omar Teixeira Gonz�lez, UO281847
 * @version 16 nov 2022
 */
public class UpdateContractTypeAction implements Action {
	/**
	 * M�todo execute
	 * Ejecuta la acci�n de la clase
	 */
	@Override
	public void execute() throws BusinessException {
		// Ask the user for the mechanic id
		String name = Console.readString("Type contract type name to update ");
		Double compensationDays = Console.readDouble("Compensation days ");

		// Invoke the service
		ContractTypeService ctService = Factory.service.forContractTypeService();
		ContractTypeDto ct = new ContractTypeDto();
		ct.compensationDays = compensationDays;
		ct.name = name;
		ctService.updateContractType(ct);
		
		// Show result
		Console.println("Contract type succesfully updated");	
		Printer.printContractType(ct);
	}
}
