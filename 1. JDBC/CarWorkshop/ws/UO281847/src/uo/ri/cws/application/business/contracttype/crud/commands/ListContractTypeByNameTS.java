package uo.ri.cws.application.business.contracttype.crud.commands;

import java.util.Optional;

import assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.contracttype.ContractTypeService.ContractTypeBLDto;
import uo.ri.cws.application.business.contracttype.assembler.ContractTypeAssembler;
import uo.ri.cws.application.business.util.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;

//GESTIÓN DE TIPOS DE CONTRATO - Listado de tipos de contrato por nombre
/**
 * Titulo: Clase ListContractTypeByNameTS
 *
 * @author Omar Teixeira González, UO281847
 * @version 19 oct 2022
 */
public class ListContractTypeByNameTS implements Command<Optional<ContractTypeBLDto>>{
	/**
	 * Atributo name
	 */
	private String name;
	
	/**
	 * Constructor ListContractTypeByNameTS
	 * @param name
	 */
	public ListContractTypeByNameTS(String name) {
		checkArguments(name);
		this.name = name;
	}
	
	/**
	 * Método checkArguments 
	 * Comprueba la integridad de los argumentos
	 * 
	 * @param name
	 */
	private void checkArguments(String name) {
		Argument.isNotEmpty(name, "El nombre del tipo de contrato a buscar no puede ser null ni estar vacío");
	}
	
	/**
	 * Método execute
	 * Ejecuta la acción de la clase, ListContractTypeByName
	 * 
	 * @throws BusinessException
	 */
	@Override
	public Optional<ContractTypeBLDto> execute() throws BusinessException {
		return ContractTypeAssembler.toBLDto(PersistenceFactory.forContractType().findByName(name));
	}
}
