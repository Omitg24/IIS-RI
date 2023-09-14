package uo.ri.cws.application.business.contracttype.crud.commands;

import java.util.UUID;

import assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.contracttype.ContractTypeService.ContractTypeBLDto;
import uo.ri.cws.application.business.util.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.contracttype.assembler.ContractTypeAssembler;

//GESTIÓN DE TIPOS DE CONTRATO - Añadir tipos de contrato
/**
 * Titulo: Clase AddContractTypeTS
 *
 * @author Omar Teixeira González, UO281847
 * @version 18 oct 2022
 */
public class AddContractTypeTS implements Command<ContractTypeBLDto>{
	/**
	 * Atributo contractTypeBLDto
	 */
	private ContractTypeBLDto contractTypeBLDto;
	
	/**
	 * Constructor AddContractTypeTS
	 * @param contractTypeBLDto
	 */
	public AddContractTypeTS(ContractTypeBLDto contractTypeBLDto) {
		checkArguments(contractTypeBLDto);
		this.contractTypeBLDto=contractTypeBLDto;
	}

	/**
	 * Método checkArguments
	 * Comprueba la integridad de los argumentos
	 * 
	 * @param contractTypeBLDto
	 */
	private void checkArguments(ContractTypeBLDto contractTypeBLDto) {
		Argument.isNotNull(contractTypeBLDto, "El tipo de contrato a añadir no puede ser null");		
		Argument.isNotEmpty(contractTypeBLDto.name, "El nombre del tipo de contrato a añadir no puede ser null ni estar vacío");		
		Argument.isTrue(contractTypeBLDto.compensationDays>=0, "Los días de compensación del "
				+ "tipo de contrato a añadir no pueden ser menores que 0");
	}

	/**
	 * Método execute
	 * Ejecuta la funcionalidad de la clase, AddContractType
	 * 
	 * @throws BusinessException
	 */
	@Override
	public ContractTypeBLDto execute() throws BusinessException {
		if (PersistenceFactory.forContractType().findByName(contractTypeBLDto.name).isPresent()) {
			throw new BusinessException("El tipo de contrato que intenta añadir ya existe");
		}
		
		this.contractTypeBLDto.id=UUID.randomUUID().toString();
		this.contractTypeBLDto.version = 1L;
		
		PersistenceFactory.forContractType().add(ContractTypeAssembler.toDALDto(contractTypeBLDto));
		
		return contractTypeBLDto;
	}
}
