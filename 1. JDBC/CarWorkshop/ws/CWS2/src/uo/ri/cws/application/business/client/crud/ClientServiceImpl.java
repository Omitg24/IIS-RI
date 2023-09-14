package uo.ri.cws.application.business.client.crud;

import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.client.ClientService;

public class ClientServiceImpl implements ClientService {

	@Override
	public ClientBLDto addClient(ClientBLDto client, String recommenderId) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteClient(String idClient) throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateClient(ClientBLDto client) throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<ClientBLDto> findAllClients() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<ClientBLDto> findClientById(String idClient) throws BusinessException {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public List<ClientBLDto> findClientsRecommendedBy(String sponsorID) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}
