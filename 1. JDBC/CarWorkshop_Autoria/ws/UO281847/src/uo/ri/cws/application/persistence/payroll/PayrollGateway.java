package uo.ri.cws.application.persistence.payroll;

import java.time.LocalDate;
import java.util.List;

import uo.ri.cws.application.persistence.Gateway;
import uo.ri.cws.application.persistence.payroll.PayrollGateway.PayrollDALDto;

/**
 * Titulo: Interfaz PayrollGateway
 *
 * @author Omar Teixeira González, UO281847
 * @version 26 oct 2022
 */
public interface PayrollGateway extends Gateway<PayrollDALDto> {	
	/**
	 * Método findByContract
	 * @param contractIds
	 * @return list
	 */
	List<PayrollDALDto> findByContractsIds(List<String> contractIds);
	
	/**
	 * Método findByContractIdsInDate
	 * @param contractIds
	 * @param month
	 * @param year
	 * @return list
	 */
	List<PayrollDALDto> findByContractsIdsInDates(List<String> contractIds, int month, int year);
	
	/**
	 * Método findByContractIdsInDate
	 * @param contractIds
	 * @param month
	 * @param year 
	 */
	List<PayrollDALDto> findInDates(int month, int year);
	
	/**
	 * Titulo: Clase PayrollDALDto
	 *
	 * @author Omar Teixeira González, UO281847
	 * @version 26 oct 2022
	 */
	public class PayrollDALDto {
		/**
		 * Atributo id
		 */
		public String id;
		/**
		 * Atributo version
		 */
		public long version;
		/**
		 * Atributo contractId
		 */
		public String contractId;
		/**
		 * Atributo date
		 */
		public LocalDate date;
		// Earnings
		/**
		 * Atributo monthlyWage
		 */
		public double monthlyWage;
		/**
		 * Atributo bonus
		 */
		public double bonus;
		/**
		 * Atributo productivityBonus
		 */
		public double productivityBonus;
		/**
		 * Atributo trienniumPayment
		 */
		public double trienniumPayment;
		// Deductions
		/**
		 * Atributo incomeTax
		 */
		public double incomeTax;
		/**
		 * Atributo nic
		 */
		public double nic;
		// Net wage
		/**
		 * Atributo netWage
		 */
		public double netWage;
	}
}
