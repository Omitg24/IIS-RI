package uo.ri.cws.application.service.mechanic.crud.command;

import java.util.List;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.mechanic.MechanicCrudService.MechanicDto;
import uo.ri.cws.application.util.DtoAssembler;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Mechanic;

//GESTIÓN DE MECÁNICOS AMPLIADO - Listado de mecánicos con contrato en vigor
/**
 * Título: Clase FindMechanicsInForce
 * Descripción: Realiza la acción de buscar todos los mecánicos que tienen un 
 * contrato en vigor en la base de datos 
 *
 * @author Omar Teixeira González, UO281847
 * @version 16 nov 2022
 */
public class FindMechanicsInForce implements Command<List<MechanicDto>> {
	/**
	 * Método execute
	 * Ejecuta la acción de la clase
	 */
	@Override
	public List<MechanicDto> execute() throws BusinessException {
//		1ª FORMA:
//			POR SEPARADO			
//		List<Mechanic> mechanics = new ArrayList<Mechanic>();		
//		
//		List<Contract> contracts = Factory.repository.forContract().findAllInForce();		
//		for (Contract contract : contracts) {
//			Optional<Mechanic> om = contract.getMechanic();
//			if (om.isPresent()) {
//				Mechanic m = om.get();
//				mechanics.add(Factory.repository.forMechanic().findById(m.getId()).get());
//			}
//		}
		
//		2ª FORMA:
//			JUNTOS		
		List<Mechanic> mechanics = Factory.repository.forMechanic().findAllInForce();
		
		return DtoAssembler.toMechanicDtoList(mechanics);
	}
}
