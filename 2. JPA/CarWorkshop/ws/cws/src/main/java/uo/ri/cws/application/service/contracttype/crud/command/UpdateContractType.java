package uo.ri.cws.application.service.contracttype.crud.command;

import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.contracttype.ContractTypeService.ContractTypeDto;
import uo.ri.cws.application.util.BusinessChecks;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.ContractType;
import uo.ri.util.assertion.ArgumentChecks;

//GESTI�N DE TIPOS DE CONTRATO - Actualizar tipos de contrato
/**
 * Titulo: Clase UpdateContractType
 * Descripción: Realiza la acción de actualizar el tipo de contrato a la base de 
 * datos 
 *
 * @author Omar Teixeira González, UO281847
 * @version 16 nov 2022
 */
public class UpdateContractType implements Command<Void>{
	/**
	 * Atributo dto
	 */
	private ContractTypeDto dto;
	
	/**
	 * Constructor con el dto del tipo de contrato como parámetro
	 * @param dto, dto del tipo de contrato
	 */
	public UpdateContractType(ContractTypeDto dto) {
		ArgumentChecks.isNotNull(dto, "El tipo de contrato a actualizar no puede ser null");		
		ArgumentChecks.isNotBlank(dto.name, "El nombre del tipo de contrato a "
				+ "actualizar no puede ser null ni estar vacío");
		ArgumentChecks.isTrue(dto.compensationDays>=0, "Los días de compensación del "
				+ "tipo de contrato a actualizar no pueden ser menores que 0");
		this.dto=dto;
	}

	/**
	 * Método execute
	 * Ejecuta la acción de la clase
	 */	
	@Override
	public Void execute() throws BusinessException {		
		Optional<ContractType> oct = Factory.repository.forContractType().findByName(dto.name);
		BusinessChecks.isTrue(oct.isPresent(), "El tipo de contrato a actualizar no existe");
		
		ContractType ct = oct.get();
		
		ct.setName(dto.name);
		ct.setCompensationDays(dto.compensationDays);
		
		return null;
	}
}
