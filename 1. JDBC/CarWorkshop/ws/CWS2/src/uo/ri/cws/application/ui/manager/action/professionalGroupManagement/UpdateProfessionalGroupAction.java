package uo.ri.cws.application.ui.manager.action.professionalGroupManagement;

import console.Console;
import menu.Action;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.professionalgroup.ProfessionalGroupService.ProfessionalGroupBLDto;

public class UpdateProfessionalGroupAction implements Action {

	@Override
	public void execute() throws BusinessException {

		String name = Console.readString("Professional group name ");
		double t = Console.readDouble("Professional group triennium salary ");
		double p = Console.readDouble("Professional group productivity salary ");

		ProfessionalGroupBLDto dto = new ProfessionalGroupBLDto();
		dto.name = name;
		dto.productivityRate = p;
		dto.trieniumSalary = t;

		Console.print("Professional group successfully updated");
	}

}
