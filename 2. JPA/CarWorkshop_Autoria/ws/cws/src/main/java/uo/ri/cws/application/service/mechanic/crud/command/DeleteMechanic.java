package uo.ri.cws.application.service.mechanic.crud.command;

import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.util.BusinessChecks;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Mechanic;
import uo.ri.util.assertion.ArgumentChecks;

/**
 * Título: Clase DeleteMechanic
 * Descripción: Realiza la acción de borrar el mecánico de la base de datos
 *
 * @author Omar Teixeira González, UO281847
 * @version 16 nov 2022
 */
public class DeleteMechanic implements Command<Void> {
	/**
	 * Atributo mechanicId
	 */
	private String mechanicId;

	/**
	 * Constructor con el id del mecánico como parámetro
	 * 
	 * @param mechanicId, id del mecánico
	 */
	public DeleteMechanic(String mechanicId) {
		ArgumentChecks.isNotBlank(mechanicId, "El id del mecánico a eliminar no puede ser null ni estar vacío");
		this.mechanicId = mechanicId;
	}

	/**
	 * Método execute
	 * Ejecuta la acción de la clase
	 */
	public Void execute() throws BusinessException {
		Optional<Mechanic> om = Factory.repository.forMechanic().findById(mechanicId);
		BusinessChecks.isTrue(om.isPresent(), "El mecánico a eliminar no existe");
		
		Mechanic m = om.get();
		
		//GESTIÓN DE MECÁNICOS AMPLIADO - Verificación de borrado
//		List<WorkOrder> workOrders = Factory.repository.forWorkOrder().findByMechanic(mechanicId);
		BusinessChecks.isTrue(m.getAssigned().isEmpty(), "El mecánico a eliminar tiene averías asignados");
//		List<Intervention> interventions = Factory.repository.forIntervention().findByMechanicId(mechanicId);		
		BusinessChecks.isTrue(m.getInterventions().isEmpty(), "El mecánico a eliminar tiene intervenciones asignadas");
//		List<Contract> contracts = Factory.repository.forContract().findByMechanicId(mechanicId);
		BusinessChecks.isTrue(m.getContractInForce().isEmpty(), "El mecánico a eliminar tiene un contrato en vigor");
		BusinessChecks.isTrue(m.getTerminatedContracts().isEmpty(), "El mecánico a eliminar no tiene contratos terminados");
		
		Factory.repository.forMechanic().remove(m);
		return null;
	}
}