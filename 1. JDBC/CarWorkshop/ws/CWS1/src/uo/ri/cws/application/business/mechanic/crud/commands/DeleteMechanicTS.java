package uo.ri.cws.application.business.mechanic.crud.commands;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.persistence.PersistenceFactory;

/**
 * Titulo: Clase DeleteMechanic
 *
 * @author Omar Teixeira González, UO281847
 * @version 13 oct 2022
 */
public class DeleteMechanicTS {
	
	/**
	 * Atributo idMechanic
	 */
	private String idMechanic;

	/**
	 * Constructor idMechanic
	 * 
	 * @param idMechanic
	 */
	public DeleteMechanicTS(String idMechanic) {
		this.idMechanic = idMechanic;
	}

	/**
	 * Método execute
	 * 
	 * @throws BusinessException
	 */
	public void execute() throws BusinessException {		
		PersistenceFactory.forMechanic().remove(idMechanic);
	}
}
