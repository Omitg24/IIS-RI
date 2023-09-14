package uo.ri.cws.application.service.mechanic.crud.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.mechanic.MechanicCrudService.MechanicDto;
import uo.ri.cws.application.util.DtoAssembler;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.ContractType;
import uo.ri.cws.domain.Mechanic;
import uo.ri.util.assertion.ArgumentChecks;

/**
 * Título: Clase FindMechanicsWithContractInForceInContractType
 * Descripción: Realiza la acción de buscar todos los mecánicos que tienen un 
 * contrato en vigor para un tipo de contrato en la base de datos 
 *
 * @author Omar Teixeira González, UO281847
 * @version 16 nov 2022
 */
public class FindMechanicsWithContractInForceInContractType implements Command<List<MechanicDto>>{
	/**
	 * Atributo contractTypeName
	 */
	private String contractTypeName;
	
	/**
	 * Constructor con el tipo de contrato de la clase 
	 * FindMechanicsWithContractInForceInContractType
	 * 
	 * @param contractTypeName, tipo de contrato
	 */
	public FindMechanicsWithContractInForceInContractType(String contractTypeName) {
		ArgumentChecks.isNotBlank(contractTypeName, "El nombre del tipo de contrato "
				+ "al cual buscar los mecánicos con contrato en vigor asociado "
				+ "no puede ser null ni estar vacío");
		this.contractTypeName = contractTypeName;
	}
	
	/**
	 * Método execute
	 * Ejecuta la acción de la clase
	 */
	@Override
	public List<MechanicDto> execute() throws BusinessException {		
		System.out.println(contractTypeName);
		Optional<ContractType> oct = Factory.repository.forContractType().findByName(contractTypeName);
//		BusinessChecks.isTrue(oct.isPresent(), "El tipo de contrato al que buscar "
//				+ "los mecánicos con dicho tipo de contrato y en vigor no existe");
		
		List<Mechanic> mechanics = new ArrayList<Mechanic>();
		if (oct.isPresent()) {
			ContractType ct = oct.get();
			mechanics = Factory.repository.forMechanic().findInForceInContractType(ct);	
		}
		
		return DtoAssembler.toMechanicDtoList(mechanics);
	}
}
