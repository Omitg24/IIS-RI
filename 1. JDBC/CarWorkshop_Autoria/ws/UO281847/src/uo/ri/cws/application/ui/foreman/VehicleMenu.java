package uo.ri.cws.application.ui.foreman;

import menu.BaseMenu;
import menu.NotYetImplementedAction;
import uo.ri.cws.application.ui.foreman.action.FindByClientAction;

public class VehicleMenu extends BaseMenu {

	public VehicleMenu() {
		menuOptions = new Object[][] { { "Foreman > Vehicles management ", null },

				{ "Add vehicle", NotYetImplementedAction.class }, 
				{ "Update vehicle", NotYetImplementedAction.class },
				{ "Delete vehicle", NotYetImplementedAction.class },
				{ "List vehicles", NotYetImplementedAction.class }, 
				{ "List vehicles by client dni", FindByClientAction.class}};
	}

}
