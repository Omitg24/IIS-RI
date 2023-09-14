package uo.ri.cws.application.ui.manager.action.professionalGroupManagement;

import console.Console;
import menu.Action;
import uo.ri.cws.application.business.professionalgroup.ProfessionalGroupService.ProfessionalGroupBLDto;

public class AddProfessionalGroupAction implements Action {

	@Override
	public void execute() throws Exception {

		// Get info
		String name = Console.readString("Name ");
		Double triennium = Console.readDouble("Triennium Salary ");
		Double productivityR = Console.readDouble("Productivity rate ");

		// Process

		ProfessionalGroupBLDto pg = new ProfessionalGroupBLDto();
		pg.trieniumSalary = triennium;
		pg.name = name;
		pg.productivityRate = productivityR;

		// Print result
		Console.println("New Professional Group succesfully added");
		// Printer.printProfessionalGroup(result);
	}

}
