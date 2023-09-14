package uo.ri.ui.manager.payroll;

import uo.ri.ui.manager.payroll.action.DeleteLastPayrollsAction;
import uo.ri.ui.manager.payroll.action.DeleteLastPayrollForMechanicAction;
import uo.ri.ui.manager.payroll.action.GeneratePayrollsAction;
import uo.ri.ui.manager.payroll.action.GeneratePayrollsByDateAction;
import uo.ri.ui.manager.payroll.action.FindAllPayrollsAction;
import uo.ri.ui.manager.payroll.action.FindAllPayrollsByMechanicAction;
import uo.ri.ui.manager.payroll.action.FindPayrollDetailsAction;
import uo.ri.ui.manager.payroll.action.FindAllPayrollsByProfessionalGroupAction;
import uo.ri.util.menu.BaseMenu;

/**
 * Título: Clase PayrollManagementMenu
 * Descripción: Contiene el menú principal de gestión de las nóminas del taller
 * de coches 
 *
 * @author Omar Teixeira González, UO281847
 * @version 16 nov 2022
 */
public class PayrollsManagementMenu extends BaseMenu {
	/**
	 * Constructor de la clase PayrollManagementMenu
	 */
	public PayrollsManagementMenu() {
		menuOptions = new Object[][] { { "Manager > Payrolls management", null },

				{ "Generate payrolls", GeneratePayrollsAction.class },
				{ "Generate payrolls by date", GeneratePayrollsByDateAction.class},
				{ "Delete last payroll for mechanic", DeleteLastPayrollForMechanicAction.class },
				{ "Delete last payrolls", DeleteLastPayrollsAction.class },				
				{ "List payroll in detail", FindPayrollDetailsAction.class },
				{ "List all payrolls", FindAllPayrollsAction.class },
				{ "List all payrolls of a mechanic", FindAllPayrollsByMechanicAction.class },
				{ "List all payrolls of a professional group", FindAllPayrollsByProfessionalGroupAction.class } };
	}
}
