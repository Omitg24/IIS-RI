package uo.ri.cws.application.business.contracttype.crud;

import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.contracttype.ContractTypeService;
import uo.ri.cws.application.business.contracttype.crud.commands.AddContractTypeTS;
import uo.ri.cws.application.business.contracttype.crud.commands.DeleteContractTypeTS;
import uo.ri.cws.application.business.contracttype.crud.commands.ListAllContractTypeTS;
import uo.ri.cws.application.business.contracttype.crud.commands.ListContractTypeByNameTS;
import uo.ri.cws.application.business.contracttype.crud.commands.UpdateContractTypeTS;
import uo.ri.cws.application.business.util.CommandExecutor;

//GESTIÓN DE TIPOS DE CONTRATO
/**
 * Titulo: Clase ContractTypeServiceImpl
 *
 * @author Omar Teixeira González, UO281847
 * @version 18 oct 2022
 */
public class ContractTypeServiceImpl implements ContractTypeService {
	/**
	 * Método addContractType
	 * @param contractTypeBLDto
	 * @return ContractTypeBLDto
	 */
	@Override
	public ContractTypeBLDto addContractType(ContractTypeBLDto contractTypeBLDto) throws BusinessException {
		return new CommandExecutor().execute(new AddContractTypeTS(contractTypeBLDto));
	}

	/**
	 * Método deleteContractType
	 * @param name
	 */
	@Override
	public void deleteContractType(String name) throws BusinessException {
		new CommandExecutor().execute(new DeleteContractTypeTS(name));
	}

	/**
	 * Método updateContractType
	 * @param contractTypeBLDto
	 */
	@Override
	public void updateContractType(ContractTypeBLDto contractTypeBLDto) throws BusinessException {
		new CommandExecutor().execute(new UpdateContractTypeTS(contractTypeBLDto));
	}
	
	/**
	 * Método findContractTypeByName
	 * @param name
	 * @return ContractTypeBLDto
	 */
	@Override
	public Optional<ContractTypeBLDto> findContractTypeByName(String name) throws BusinessException {
		return new CommandExecutor().execute(new ListContractTypeByNameTS(name));
	}

	/**
	 * Método findAllContractTypes
	 * @return contractTypes
	 */
	@Override
	public List<ContractTypeBLDto> findAllContractTypes() throws BusinessException {
		return new CommandExecutor().execute(new ListAllContractTypeTS());
	}

}
