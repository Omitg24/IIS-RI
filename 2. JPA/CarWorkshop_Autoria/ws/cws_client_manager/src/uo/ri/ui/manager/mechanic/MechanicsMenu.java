package uo.ri.ui.manager.mechanic;

import uo.ri.ui.manager.mechanic.action.AddMechanicAction;
import uo.ri.ui.manager.mechanic.action.DeleteMechanicAction;
import uo.ri.ui.manager.mechanic.action.FindAllMechanicsAction;
import uo.ri.ui.manager.mechanic.action.FindMechanicByIdAction;
import uo.ri.ui.manager.mechanic.action.FindMechanicsInForceAction;
import uo.ri.ui.manager.mechanic.action.FindMechanicsWithContractInForceInContractTypeAction;
import uo.ri.ui.manager.mechanic.action.FindMechanicsWithExpensiveRepairsAction;
import uo.ri.ui.manager.mechanic.action.UpdateMechanicAction;
import uo.ri.util.menu.BaseMenu;

/**
 * Título: Clase MechanicsMenu
 * Descripción: Contiene el menú principal de gestión de los mecánicos del taller
 * de coches 
 *
 * @author Omar Teixeira González, UO281847
 * @version 15 nov 2022
 */
public class MechanicsMenu extends BaseMenu {
	/**
	 * Constructor de la clase MechanicsMenu
	 */
	public MechanicsMenu() {
		menuOptions = new Object[][] { 
			{"Manager > Mechanics management", null},
			
			{ "Add mechanic", AddMechanicAction.class }, 
			{ "Update mechanic", UpdateMechanicAction.class },
			{ "Delete mechanic", DeleteMechanicAction.class },
			{ "List mechanic by id", FindMechanicByIdAction.class },
			{ "List mechanics", FindAllMechanicsAction.class },
			{ "List mechanics with contract in force", FindMechanicsInForceAction.class },
			{ "List mechanics with expensive repairs", FindMechanicsWithExpensiveRepairsAction.class }, 
			{ "List mechanics with contract in force by contract type", FindMechanicsWithContractInForceInContractTypeAction.class }, 
		};
	}

}
