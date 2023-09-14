package uo.ri.cws.application.ui.manager.action.contractManagement;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

import menu.Action;
import uo.ri.cws.application.business.BusinessException;

/**
 * Clase que crea un tipo de contrato
 * 
 * @author
 *
 */
public class AddContractAction implements Action {

	/**
	 * Crea un contrato el cual el dni del mecanico, el id del tipo del contrato, el
	 * id de la categoria, el mes y el salario base son pasados por consola
	 */
	@Override
	public void execute() throws BusinessException {

//		String mechanicdNI = Console.readString("Mechanic DNI ");
//		LocalDate startDate = getStartOfNextMonth();
//		Console.println("Contract type");
//		Console.println("PERMANENT \t SEASONAL \t FIXED_TERM");
//		String typeName = Console.readString("Contract type name ");
//		LocalDate endDate = null;
//		if (typeName.toUpperCase().compareTo("PERMANENT") != 0) {
//			endDate = Console.readDate("Type end date ");
//		}
//
//		Console.println("Professional group");
//		Console.println("I \t II \t III \t IV \t V \t VI \t VII");
//		String categoryName = Console.readString("Professional group name ");
//		double yearBaseSalary = Console.readDouble("Annual base salary");
//
//		Console.println("Contract registered ");

	}

	public static LocalDate getStartOfNextMonth() {
		return LocalDate.now().with(TemporalAdjusters.firstDayOfNextMonth());
	}

}
