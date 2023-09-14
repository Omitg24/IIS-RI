package uo.ri.cws.infrastructure.persistence.jpa.repository;

import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.repository.ClientRepository;
import uo.ri.cws.domain.Client;
import uo.ri.cws.infrastructure.persistence.jpa.util.BaseJpaRepository;
import uo.ri.cws.infrastructure.persistence.jpa.util.Jpa;

public class ClientJpaRepository extends BaseJpaRepository<Client> implements ClientRepository {

	@Override
	public Optional<Client> findByDni(String dni) {
		return Jpa.getManager()
				.createNamedQuery("Client.findByDni", Client.class)
				.setParameter(1, dni)
				.getResultStream()
				.findFirst();
	}

	@Override
	public List<Client> findSponsoredByClient(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Client> findRecomendedBy(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Client> findWithThreeUnusedWorkOrders() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Client> findWithRecomendationsDone() {
		// TODO Auto-generated method stub
		return null;
	}

}
