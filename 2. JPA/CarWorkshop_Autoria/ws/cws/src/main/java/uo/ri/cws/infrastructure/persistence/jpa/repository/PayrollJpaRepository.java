package uo.ri.cws.infrastructure.persistence.jpa.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.repository.PayrollRepository;
import uo.ri.cws.domain.Payroll;
import uo.ri.cws.infrastructure.persistence.jpa.util.BaseJpaRepository;
import uo.ri.cws.infrastructure.persistence.jpa.util.Jpa;

public class PayrollJpaRepository extends BaseJpaRepository<Payroll> implements PayrollRepository {

	@Override
	public List<Payroll> findByContract(String contractId) {
		return Jpa.getManager()
				.createNamedQuery("Payroll.findByContract", Payroll.class)
				.setParameter(1, contractId)
				.getResultList();
	}

	@Override
	public List<Payroll> findCurrentMonthPayrolls() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Payroll> findCurrentMonthByContractId(String contractId) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}
	
	@Override
	public List<Payroll> findInDates(LocalDate date) {
		return Jpa.getManager()
				.createNamedQuery("Payroll.findInDates", Payroll.class)
				.setParameter(1, date.getMonthValue())
				.setParameter(2, date.getYear())
				.getResultList();
	}

	@Override
	public List<Payroll> findByContractsIdsInDates(List<String> contractIds, LocalDate date) {
		return Jpa.getManager()
				.createNamedQuery("Payroll.findByContractsIdsInDates", Payroll.class)
				.setParameter(1, contractIds)
				.setParameter(2, date.getMonthValue())
				.setParameter(3, date.getYear())
				.getResultList();
	}
}
