package uo.ri.cws.application.business.contracttype.crud.commands;

import assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.contracttype.ContractTypeService.ContractTypeBLDto;
import uo.ri.cws.application.business.util.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.contracttype.assembler.ContractTypeAssembler;

//GESTI�N DE TIPOS DE CONTRATO - Actualizar tipos de contrato
/**
 * Titulo: Clase UpdateContractTypeTS
 *
 * @author Omar Teixeira Gonz�lez, UO281847
 * @version 18 oct 2022
 */
public class UpdateContractTypeTS implements Command<Void>{
	/**
	 * Atributo contractTypeBLDto
	 */
	private ContractTypeBLDto contractTypeBLDto;
	
	/**
	 * Constructor UpdateContractTypeTS
	 * @param contractTypeBLDto
	 */
	public UpdateContractTypeTS(ContractTypeBLDto contractTypeBLDto) {
		checkArguments(contractTypeBLDto);
		this.contractTypeBLDto=contractTypeBLDto;
	}

	/**
	 * M�todo checkArguments
	 * Comprueba la integridad de los argumentos
	 * 
	 * @param contractTypeBLDto
	 */
	private void checkArguments(ContractTypeBLDto contractTypeBLDto) {
		Argument.isNotNull(contractTypeBLDto, "El tipo de contrato a actualizar no puede ser null");		
		Argument.isNotEmpty(contractTypeBLDto.name, "El nombre del tipo de contrato a actualizar no puede ser null ni estar vac�o");
		Argument.isTrue(contractTypeBLDto.compensationDays>=0, "Los d�as de compensaci�n del "
				+ "tipo de contrato a actualizar no pueden ser menores que 0");
	}

	/**
	 * M�todo execute
	 * Ejecuta la funcionalidad de la clase, AddContractType
	 * 
	 * @throws BusinessException
	 */
	@Override
	public Void execute() throws BusinessException {		
		if (PersistenceFactory.forContractType().findByName(contractTypeBLDto.name).isEmpty()) {
			throw new BusinessException("El tipo de contrato que intenta actualizar no existe");
		}
		
		PersistenceFactory.forContractType().update(ContractTypeAssembler.toDALDto(contractTypeBLDto));		
		return null;
	}
}
