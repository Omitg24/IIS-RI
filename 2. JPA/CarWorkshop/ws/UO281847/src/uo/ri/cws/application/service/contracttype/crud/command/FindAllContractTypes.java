package uo.ri.cws.application.service.contracttype.crud.command;

import java.util.List;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.contracttype.ContractTypeService.ContractTypeDto;
import uo.ri.cws.application.util.DtoAssembler;
import uo.ri.cws.application.util.command.Command;

//GESTI�N DE TIPOS DE CONTRATO - Listado de tipos de contrato
/**
 * Titulo: Clase FindAllContractType
 * Descripción: Realiza la acción de buscar todos los tipos de contrato en la 
 * base de datos 
 *
 * @author Omar Teixeira González, UO281847
 * @version 16 nov 2022
 */
public class FindAllContractTypes implements Command<List<ContractTypeDto>>{
	/**
	 * Método execute
	 * Ejecuta la acción de la clase
	 */	
	@Override
	public List<ContractTypeDto> execute() throws BusinessException {
		return DtoAssembler.toContractTypeDtoList(Factory.repository.forContractType().findAll()); 
	}
}
