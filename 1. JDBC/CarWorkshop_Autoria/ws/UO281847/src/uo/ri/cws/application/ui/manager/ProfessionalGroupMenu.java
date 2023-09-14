package uo.ri.cws.application.ui.manager;

import menu.BaseMenu;
import menu.NotYetImplementedAction;

public class ProfessionalGroupMenu extends BaseMenu {

	public ProfessionalGroupMenu() {
		menuOptions = new Object[][] { { "Manager > Professional Group management", null },

				{ "Add Professional Group ", NotYetImplementedAction.class },
				{ "Update Professional Group ", NotYetImplementedAction.class },
				{ "Delete Professional Group ", NotYetImplementedAction.class },
				{ "List all Professional Group ", NotYetImplementedAction.class },
				{ "List Professional Group By name ", NotYetImplementedAction.class }, };
	}

}
