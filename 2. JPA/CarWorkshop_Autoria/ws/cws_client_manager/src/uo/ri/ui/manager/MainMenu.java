package uo.ri.ui.manager;

import uo.ri.ui.manager.contracttype.ContractTypesMenu;
import uo.ri.ui.manager.mechanic.MechanicsMenu;
import uo.ri.ui.manager.payroll.PayrollsManagementMenu;
import uo.ri.ui.manager.sparepart.SparepartsMenu;
import uo.ri.ui.manager.vehicletype.VehicleTypesMenu;
import uo.ri.util.menu.BaseMenu;
import uo.ri.util.menu.NotYetImplementedAction;

/**
 * Título: Clase MainMenu
 * Descripción: Contiene el menú principal del gestor del taller de coches 
 *
 * @author Omar Teixeira González, UO281847
 * @version 15 nov 2022
 */
public class MainMenu extends BaseMenu {{
		menuOptions = new Object[][] {
			{ "Manager", null },

			{ "Mechanics management", MechanicsMenu.class },
			{ "Spare parts management", SparepartsMenu.class },
			{ "Vehicle types management", VehicleTypesMenu.class }, 
			{ "Contracts management", NotYetImplementedAction.class },
			{ "Contract types management", ContractTypesMenu.class},
			{ "Payroll management", PayrollsManagementMenu.class }
		};
}}