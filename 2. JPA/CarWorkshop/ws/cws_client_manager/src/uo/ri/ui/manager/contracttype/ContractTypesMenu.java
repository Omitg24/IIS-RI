package uo.ri.ui.manager.contracttype;

import uo.ri.ui.manager.contracttype.action.AddContractTypeAction;
import uo.ri.ui.manager.contracttype.action.DeleteContractTypeAction;
import uo.ri.ui.manager.contracttype.action.FindAllContractTypeAction;
import uo.ri.ui.manager.contracttype.action.FindContractTypeByNameAction;
import uo.ri.ui.manager.contracttype.action.UpdateContractTypeAction;
import uo.ri.util.menu.BaseMenu;

/**
 * Título: Clase ContractTypesMenu
 * Descripción: Contiene el menú principal de gestión de los tipos de contrato 
 * del taller de coches 
 *
 * @author Omar Teixeira González, UO281847
 * @version 16 nov 2022
 */
public class ContractTypesMenu extends BaseMenu {
	/**
	 * Constructor de la clase ContractTypesMenu
	 */
	public ContractTypesMenu() {
		menuOptions = new Object[][] { { "Manager > contract type type management", null },

				{ "Add contract type ", AddContractTypeAction.class },
				{ "Update contract type ", UpdateContractTypeAction.class },
				{ "Delete contract type ", DeleteContractTypeAction.class },
				{ "List contract type by name", FindContractTypeByNameAction.class },
				{ "List contract types", FindAllContractTypeAction.class}};
				
	}
}
