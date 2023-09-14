package uo.ri.cws.application.service.contracttype.crud;

import java.util.List;
import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.contracttype.ContractTypeService;
import uo.ri.cws.application.service.contracttype.crud.command.AddContractType;
import uo.ri.cws.application.service.contracttype.crud.command.DeleteContractType;
import uo.ri.cws.application.service.contracttype.crud.command.FindAllContractTypes;
import uo.ri.cws.application.service.contracttype.crud.command.FindContractTypeByName;
import uo.ri.cws.application.service.contracttype.crud.command.UpdateContractType;
import uo.ri.cws.application.util.command.CommandExecutor;

/**
 * Título: Clase ContractTypeServiceImpl
 * Descripción: Implementación de la interfaz de servicio de los tipos de 
 * contrato 
 *
 * @author Omar Teixeira González, UO281847
 * @version 16 nov 2022
 */
public class ContractTypeServiceImpl implements ContractTypeService {
	/**
	 * Atributo executor
	 */
	private CommandExecutor executor = Factory.executor.forExecutor();
	
	/**
	 * Método addContractType
	 * 
	 * @param dto, the id field is meaningless for this operation
	 * @return dto with id
	 * @throws IllegalArgumentException if
	 * 		- arg is null
	 * 		- name is null or empty
	 * 		- the number of compensation days is negative
	 * @throws BusinessException if:
	 * 		- another type of contract with the same name already exists
	 */
	@Override
	public ContractTypeDto addContractType(ContractTypeDto dto) throws BusinessException {
		return executor.execute(new AddContractType(dto));
	}

	/**
	 * Método deleteContractType
	 * 
	 * @param name of the contract type
	 * @throws IllegalArgumentException if
	 * 		- name is null or empty
	 * @throws BusinessException if 
	 * 		- the contract type does not exist, or
	 * 		- there are contracts registered with the contract type
	 */
	@Override
	public void deleteContractType(String name) throws BusinessException {
		executor.execute(new DeleteContractType(name));
	}

	/**
	 * Método updateContractType
	 * 
	 * Updates just the compensation days
	 * @param dto
	 * @throws IllegalArgumentException if
	 * 		- arg is null
	 * 		- nanme is null or empty
	 * 		- the number of compensation days is negative
	 * @throws BusinessException if:
	 * 		- the contract type does not exist, or
	 */
	@Override
	public void updateContractType(ContractTypeDto dto) throws BusinessException {
		executor.execute(new UpdateContractType(dto));
	}

	/**
	 * Método findContractTypeByName
	 * 
	 * @param name, of the contract type
	 * @throws IllegalArgumentException if
	 * 		- name is null or empty
	 * @return the dto with all the fields set, or
	 * 		empty if does not exist the contract type
	 * @throws BusinessException DOES NOT
	 */
	@Override
	public Optional<ContractTypeDto> findContractTypeByName(String name) throws BusinessException {
		return executor.execute(new FindContractTypeByName(name));
	}
	
	/**
	 * Método findAllContractTypes
	 * 
	 * @return a list with all the contract types registered, or
	 * 		an empty list if ther are none
	 * @throws BusinessException DOES NOT
	 */
	@Override
	public List<ContractTypeDto> findAllContractTypes() throws BusinessException {
		return executor.execute(new FindAllContractTypes());
	}
}
