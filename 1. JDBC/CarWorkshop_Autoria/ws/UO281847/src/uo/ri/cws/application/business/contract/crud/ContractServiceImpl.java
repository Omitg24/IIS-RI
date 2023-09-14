package uo.ri.cws.application.business.contract.crud;

import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.contract.ContractService;

public class ContractServiceImpl implements ContractService {

	@Override
	public ContractBLDto add(ContractBLDto c) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateContract(ContractBLDto dto) throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteContract(String id) throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public void terminateContract(String contractId) throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public Optional<ContractBLDto> findContractById(String id) throws BusinessException {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public List<ContractSummaryBLDto> findContractsByMechanic(String mechanicDni) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ContractSummaryBLDto> findAllContracts() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}
