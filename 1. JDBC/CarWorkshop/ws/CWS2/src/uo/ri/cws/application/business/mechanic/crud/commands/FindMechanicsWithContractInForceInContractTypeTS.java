package uo.ri.cws.application.business.mechanic.crud.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.contract.ContractService.ContractBLDto;
import uo.ri.cws.application.business.contract.assembler.ContractAssembler;
import uo.ri.cws.application.business.contracttype.ContractTypeService.ContractTypeBLDto;
import uo.ri.cws.application.business.contracttype.assembler.ContractTypeAssembler;
import uo.ri.cws.application.business.mechanic.MechanicService.MechanicBLDto;
import uo.ri.cws.application.business.mechanic.assembler.MechanicAssembler;
import uo.ri.cws.application.business.util.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;

//GESTIÓN DE TIPOS DE CONTRATO - Listado de trabajadores con contrato en vigor por nombre de tipo de contrato
/**
 * 
 * Titulo: Clase FindMechanicsWithContractInForceInContractTypeTS
 *
 * @author Omar Teixeira González, UO281847
 * @version 19 oct 2022
 */
public class FindMechanicsWithContractInForceInContractTypeTS implements Command<List<MechanicBLDto>>{
	/**
	 * Atributo name
	 */
	private String name;
	
	/**
	 * Constructor FindMechanicsWithContractInForceInContractTypeTS
	 * @param name
	 */
	public FindMechanicsWithContractInForceInContractTypeTS(String name) {
		checkArguments(name);
		this.name = name;
	}
	
	/**
	 * Método checkArguments 
	 * Comprueba la integridad de los argumentos
	 * 
	 * @param idMechanic
	 */
	private void checkArguments(String name) {
		Argument.isNotEmpty(name, "El nombre del tipo de contrato para buscarlo a buscar no puede ser null ni estar vacío");
	}

	/**
	 * Método execute
	 * 
	 * @throws BusinessException
	 */
	@Override
	public List<MechanicBLDto> execute() throws BusinessException {		
		List<MechanicBLDto> mechanics = new ArrayList<MechanicBLDto>();
		List<ContractBLDto> contractList;
		Optional<ContractTypeBLDto> contractType;
		Optional<MechanicBLDto> mechanic;
		
		contractType = ContractTypeAssembler.toBLDto(PersistenceFactory.forContractType().findByName(name));
		
		if (contractType.isPresent()) {
			contractList = ContractAssembler.toDtoList(PersistenceFactory.forContract().findByContractTypeIdInForce(contractType.get().id));		
			for (ContractBLDto contract : contractList) {
				mechanic = MechanicAssembler.toBLDto(PersistenceFactory.forMechanic().findById(contract.dni));
				if (mechanic.isPresent()) {
					mechanics.add(mechanic.get());
				}				
			}		
		}		
		return mechanics;
	}
}
