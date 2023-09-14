package uo.ri.cws.application.business.mechanic.crud.commands;

import assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.util.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;

/**
 * Titulo: Clase DeleteMechanic
 *
 * @author Omar Teixeira Gonz�lez, UO281847
 * @version 13 oct 2022
 */
public class DeleteMechanicTS implements Command<Void> {
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
		checkArguments(idMechanic);
		this.idMechanic = idMechanic;
	}

	/**
	 * M�todo checkArguments 
	 * Comprueba la integridad de los argumentos
	 * 
	 * @param idMechanic
	 */
	private void checkArguments(String idMechanic) {
		Argument.isNotEmpty(idMechanic, "El id del mec�nico a borrar no puede ser null ni estar vac�o");
	}

	/**
	 * M�todo execute Ejecuta la accion de la clase, delete
	 * 
	 * @throws BusinessException
	 */
	public Void execute() throws BusinessException {
		if (PersistenceFactory.forMechanic().findById(idMechanic).isEmpty()) {
			throw new BusinessException("El mec�nico que intenta borrar no existe");
		}
		//GESTI�N DE MEC�NICOS AMPLIADO - Verificaci�n de borrado
		if (!PersistenceFactory.forWorkOrder().findByMechanic(idMechanic).isEmpty()) {
			throw new BusinessException("El mec�nico que intenta borrar tiene workOrders asignados");
		}
		if (!PersistenceFactory.forIntervention().findByMechanic(idMechanic).isEmpty()) {
			throw new BusinessException("El mec�nico que intenta borrar tiene intervenciones asignadas");
		}
		if (!PersistenceFactory.forContract().findByMechanic(idMechanic).isEmpty()) {
			throw new BusinessException("El mec�nico que intenta borrar tiene contratos asignados");
		}
		
		PersistenceFactory.forMechanic().remove(idMechanic);
		return null;
	}
}
