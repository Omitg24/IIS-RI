package uo.ri.cws.application.business.contracttype.crud.commands;

import java.util.Optional;

import assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.util.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.contracttype.ContractTypeGateway.ContractTypeDALDto;

//GESTI�N DE TIPOS DE CONTRATO - Borrar tipos de contrato
/**
 * Titulo: Clase DeleteContractTypeTS
 *
 * @author Omar Teixeira Gonz�lez, UO281847
 * @version 18 oct 2022
 */
public class DeleteContractTypeTS implements Command<Void>{
	/**
	 * Atributo name
	 */
	private String name;
	
	/**
	 * Constructor DeleteContractTypeTS
	 * @param name
	 */
	public DeleteContractTypeTS(String name) {
		checkArguments(name);
		this.name=name;
	}

	/**
	 * M�todo checkArguments
	 * Comprueba la integridad de los argumentos
	 * 
	 * @param name
	 */
	private void checkArguments(String name) {
		Argument.isNotEmpty(name, "El nombre del tipo de contrato a borrar no puede ser null ni estar vac�o");	
	}

	/**
	 * M�todo execute
	 * Ejecuta la funcionalidad de la clase, AddContractType
	 * 
	 * @throws BusinessException
	 */
	@Override
	public Void execute() throws BusinessException {		
		Optional<ContractTypeDALDto> contractType;
		
		contractType = PersistenceFactory.forContractType().findByName(name);
		if (contractType.isPresent() ) {
			if (PersistenceFactory.forContract().findByContractTypeId(contractType.get().id).isPresent()) {
				throw new BusinessException("No se pueden borrar tipos de contrato que tengan contratos asociados");
			}
		} else {
			throw new BusinessException("El tipo de contrato a eliminar no existe");
		}
		
		PersistenceFactory.forContractType().remove(name);	
		return null;
	}
}
