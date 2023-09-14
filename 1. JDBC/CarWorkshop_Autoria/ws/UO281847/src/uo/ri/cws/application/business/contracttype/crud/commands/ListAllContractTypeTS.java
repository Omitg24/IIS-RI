package uo.ri.cws.application.business.contracttype.crud.commands;

import java.util.List;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.contracttype.ContractTypeService.ContractTypeBLDto;
import uo.ri.cws.application.business.contracttype.assembler.ContractTypeAssembler;
import uo.ri.cws.application.business.util.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;

//GESTIÓN DE TIPOS DE CONTRATO - Listado de tipos de contrato
/**
 * Titulo: Clase ListAllContractTypeTS
 *
 * @author Omar Teixeira González, UO281847
 * @version 19 oct 2022
 */
public class ListAllContractTypeTS implements Command<List<ContractTypeBLDto>>{
	/**
	 * Método execute
	 * Ejecuta la acción de la clase, ListAllContractType
	 * 
	 * @throws BusinessException
	 */
	@Override
	public List<ContractTypeBLDto> execute() throws BusinessException {
		return ContractTypeAssembler.toDtoList(PersistenceFactory.forContractType().findAll()); 
	}
}
