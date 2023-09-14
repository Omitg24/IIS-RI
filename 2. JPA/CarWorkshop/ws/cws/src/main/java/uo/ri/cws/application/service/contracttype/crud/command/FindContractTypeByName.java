package uo.ri.cws.application.service.contracttype.crud.command;

import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.contracttype.ContractTypeService.ContractTypeDto;
import uo.ri.cws.application.util.DtoAssembler;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.ContractType;
import uo.ri.util.assertion.ArgumentChecks;

//GESTI�N DE TIPOS DE CONTRATO - Listado de tipos de contrato por nombre
/**
 * Titulo: Clase FindContractTypeByName
 * Descripción: Realiza la acción de buscar un tipo de contrato por nombre de la 
 * base de datos 
 *
 * @author Omar Teixeira González, UO281847
 * @version 16 nov 2022
 */
public class FindContractTypeByName implements Command<Optional<ContractTypeDto>>{
	/**
	 * Atributo name
	 */
	private String name;
	
	/**
	 * Constructor con el nombre del tipo de contrato como parámetro
	 * 
	 * @param name, nombre del tipo de contrato
	 */
	public FindContractTypeByName(String name) {
		ArgumentChecks.isNotBlank(name, "El nombre del tipo de contrato a buscar "
				+ "no puede ser null ni estar vac�o");
		this.name = name;
	}
	
	/**
	 * Método execute
	 * Ejecuta la acción de la clase
	 */
	@Override
	public Optional<ContractTypeDto> execute() throws BusinessException {
		Optional<ContractType> oct = Factory.repository.forContractType().findByName(name);
		return oct.isPresent() ? Optional.ofNullable(DtoAssembler.toDto(oct.get())) : Optional.empty(); 
	}
}
