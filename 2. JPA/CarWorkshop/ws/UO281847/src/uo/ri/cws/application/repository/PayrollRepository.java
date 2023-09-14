package uo.ri.cws.application.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import uo.ri.cws.domain.Payroll;

public interface PayrollRepository extends Repository<Payroll> {
	
	List<Payroll> findByContract(String contractId);

	List<Payroll> findCurrentMonthPayrolls();

	Optional<Payroll> findCurrentMonthByContractId(String contractId);
	
	List<Payroll> findInDates(LocalDate date);
	
	List<Payroll> findByContractsIdsInDates(List<String> contractIds, LocalDate date);
}
