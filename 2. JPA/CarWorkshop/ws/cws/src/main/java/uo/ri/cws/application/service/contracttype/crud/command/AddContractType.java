package uo.ri.cws.application.service.contracttype.crud.command;

import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.contracttype.ContractTypeService.ContractTypeDto;
import uo.ri.cws.application.util.BusinessChecks;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.ContractType;
import uo.ri.util.assertion.ArgumentChecks;

//GESTIÓN DE TIPOS DE CONTRATO - Añadir tipos de contrato
/**
 * Titulo: Clase AddContractType
 * Descripción: Realiza la acción de añadir el tipo de contrato a la base de 
 * datos 
 *
 * @author Omar Teixeira González, UO281847
 * @version 16 nov 2022
 */
public class AddContractType implements Command<ContractTypeDto>{
	/**
	 * Atributo dto
	 */
	private ContractTypeDto dto;
	
	/**
	 * Constructor con el dto del tipo de contrato como parámetro
	 * 
	 * @param dto, dto del tipo de contrato
	 */
	public AddContractType(ContractTypeDto dto) {
		ArgumentChecks.isNotNull(dto, "El tipo de contrato a añadir no puede ser null");		
		ArgumentChecks.isNotBlank(dto.name, "El nombre del tipo de contrato a "
				+ "añadir no puede ser null ni estar vacío");		
		ArgumentChecks.isTrue(dto.compensationDays>=0, "Los días de compensación del "
				+ "tipo de contrato a añadir no pueden ser menores que 0");
		this.dto=dto;
	}

	/**
	 * Método execute
	 * Ejecuta la acción de la clase
	 */
	@Override
	public ContractTypeDto execute() throws BusinessException {
		Optional<ContractType> oct = Factory.repository.forContractType().findByName(dto.name);
		BusinessChecks.isTrue(oct.isEmpty(), "El tipo de contrato a añadir ya existe");
		
		ContractType ct = new ContractType(dto.name, dto.compensationDays);
		Factory.repository.forContractType().add(ct);
		
		dto.id = ct.getId();
		return dto;
	}
}
