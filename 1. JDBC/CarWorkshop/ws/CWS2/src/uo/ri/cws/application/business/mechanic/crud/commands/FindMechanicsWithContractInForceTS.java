package uo.ri.cws.application.business.mechanic.crud.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.contract.ContractService.ContractBLDto;
import uo.ri.cws.application.business.contract.assembler.ContractAssembler;
import uo.ri.cws.application.business.mechanic.MechanicService.MechanicBLDto;
import uo.ri.cws.application.business.mechanic.assembler.MechanicAssembler;
import uo.ri.cws.application.business.util.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;

//GESTIÓN DE MECÁNICOS AMPLIADO - Listado de mecánicos con contrato en vigor
/**
 * Titulo: Clase FindMechanicWithContractInForceTS
 *
 * @author Omar Teixeira González, UO281847
 * @version 18 oct 2022
 */
public class FindMechanicsWithContractInForceTS implements Command<List<MechanicBLDto>>{
	/**
	 * Método execute
	 * Ejecuta la acción de la clase, FindMechanicWithContractInForceTS
	 * 
	 * @throws BusinessException
	 * @return mechanics
	 */
	@Override
	public List<MechanicBLDto> execute() throws BusinessException {
		List<MechanicBLDto> mechanics = new ArrayList<MechanicBLDto>();
		List<ContractBLDto> contractList;
		Optional<MechanicBLDto> mechanic;
		
		contractList = ContractAssembler.toDtoList(PersistenceFactory.forContract().findInForce());		
		for (ContractBLDto contract : contractList) {
			mechanic = MechanicAssembler.toBLDto(PersistenceFactory.forMechanic().findById(contract.dni));
			if (mechanic.isPresent()) {
				mechanics.add(mechanic.get());
			}				
		}
		return mechanics;
	}
}
