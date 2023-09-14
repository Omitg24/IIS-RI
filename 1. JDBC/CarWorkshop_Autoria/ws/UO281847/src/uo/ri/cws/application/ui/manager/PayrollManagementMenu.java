package uo.ri.cws.application.ui.manager;

import menu.BaseMenu;
import uo.ri.cws.application.ui.manager.action.payrollManagement.DeleteLastPayrollAction;
import uo.ri.cws.application.ui.manager.action.payrollManagement.DeletePayrollForMechanicAction;
import uo.ri.cws.application.ui.manager.action.payrollManagement.GeneratePayrollsAction;
import uo.ri.cws.application.ui.manager.action.payrollManagement.GeneratePayrollsByDateAction;
import uo.ri.cws.application.ui.manager.action.payrollManagement.ListAllPayrollsSummariesAction;
import uo.ri.cws.application.ui.manager.action.payrollManagement.ListPayrollByMechanicAction;
import uo.ri.cws.application.ui.manager.action.payrollManagement.ListPayrollDetailAction;
import uo.ri.cws.application.ui.manager.action.payrollManagement.ListPayrollsByProfessionalGroupAction;

public class PayrollManagementMenu extends BaseMenu {

	/**
	 * Menu que muestra las distintas operaciones que puedes hacer con las nóminas
	 */
	public PayrollManagementMenu() {
		menuOptions = new Object[][] { { "Manager > Payrolls management", null },

				{ "Generate payrolls", GeneratePayrollsAction.class },
				{ "Generate payrolls by date", GeneratePayrollsByDateAction.class},
				{ "Delete last payroll for mechanic", DeletePayrollForMechanicAction.class },
				{ "Delete last payrolls", DeleteLastPayrollAction.class },
				{ "Display all payrolls", ListAllPayrollsSummariesAction.class },
				{ "Display payroll in detail", ListPayrollDetailAction.class },
				{ "Display payroll of a mechanic", ListPayrollByMechanicAction.class },
				{ "Display payrolls of a professional group", ListPayrollsByProfessionalGroupAction.class } };
	}
}
