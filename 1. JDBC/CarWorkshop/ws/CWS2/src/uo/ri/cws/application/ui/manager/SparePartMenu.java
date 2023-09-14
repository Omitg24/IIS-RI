package uo.ri.cws.application.ui.manager;

import menu.BaseMenu;
import menu.NotYetImplementedAction;

public class SparePartMenu extends BaseMenu {

	public SparePartMenu() {
		menuOptions = new Object[][] { { "Manager > Spare parts management", null },

				{ "Add spare part", NotYetImplementedAction.class },
				{ "Update spare part", NotYetImplementedAction.class },
				{ "Delete spare part", NotYetImplementedAction.class },
				{ "List spare parts", NotYetImplementedAction.class }, };
	}

}
