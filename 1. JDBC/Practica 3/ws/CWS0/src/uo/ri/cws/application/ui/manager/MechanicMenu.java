package uo.ri.cws.application.ui.manager;

import menu.BaseMenu;
import menu.NotYetImplementedAction;
import uo.ri.cws.application.ui.manager.action.AddMechanicAction;
import uo.ri.cws.application.ui.manager.action.DeleteMechanicAction;
import uo.ri.cws.application.ui.manager.action.FindAllMechanicsAction;
import uo.ri.cws.application.ui.manager.action.UpdateMechanicAction;

public class MechanicMenu extends BaseMenu {

	public MechanicMenu() {
		menuOptions = new Object[][] { { "Manager > Mechanics management", null },

				{ "Add mechanic", AddMechanicAction.class }, { "Update mechanic", UpdateMechanicAction.class },
				{ "Delete mechanic", DeleteMechanicAction.class }, { "List mechanic", NotYetImplementedAction.class },
				{ "List mechanics", FindAllMechanicsAction.class }, };
	}

}
