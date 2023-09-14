package uo.ri.cws.application.business.payroll.crud.commands;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.payroll.PayrollService.PayrollBLDto;
import uo.ri.cws.application.business.payroll.assembler.PayrollAssembler;
import uo.ri.cws.application.business.util.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.contract.ContractGateway.ContractDALDto;
import uo.ri.cws.application.persistence.payroll.PayrollGateway.PayrollDALDto;
import uo.ri.cws.application.persistence.professionalgroup.ProfessionalGroupGateway.ProfessionalGroupDALDto;
import uo.ri.cws.application.persistence.workorder.WorkOrderGateway.WorkOrderDALDto;

//GESTIÓN DE NÓMINAS - Generar nóminas
/**
 * Titulo: Clase GeneratePayrollsTS
 *
 * @author Omar Teixeira González, UO281847
 * @version 27 oct 2022
 */
public class GeneratePayrollsTS implements Command<List<PayrollBLDto>> {
	/**
	 * Constante NIC
	 */
	public static final double NIC_PERCENTAGE = 0.05;
	
	/**
	 * Atributo present
	 */
	private LocalDate present;
	
	/**
	 * Constructor GeneratePayrollsTS
	 * @param present
	 */
	public GeneratePayrollsTS(LocalDate present) {
		if (present == null) {
			this.present = LocalDate.now();
		} else {
			this.present=present;
		}		
	}
		
	/**
	 * Método checkIfPayrollExists
	 * @throws BusinessException
	 */
	private void checkIfPayrollIsAlreadyGenerated() throws BusinessException {
		List<PayrollDALDto> payrolls;
		payrolls = PersistenceFactory.forPayroll().findInDates(present.getMonthValue(), present.getYear());
		
		if (!payrolls.isEmpty()) {
			throw new BusinessException("Ya existe una nómina generada este mes");
		}		
	}
	
	/**
	 * Método getMonthlyWage
	 * @param annualBaseWage
	 * @return monthlyWage
	 */
	private double getMonthlyWage(double annualBaseWage) {
		double monthlyWage = annualBaseWage/14;
		return monthlyWage;
	}	
	
	/**
	 * Método getBonus
	 * @param annualBaseWage
	 * @return bonus
	 */
	private double getBonus(double annualBaseWage) {
		double bonus = 0;
		if (present.getMonth()==Month.JUNE || present.getMonth()==Month.DECEMBER) {
			bonus = annualBaseWage/14;
		}
		return bonus;
	}
	
	/**
	 * Método getProductivityBonus
	 * @param annualBaseWage
	 * @return productivityBonus
	 */
	private double getProductivityBonus(ContractDALDto contract) {
		List<WorkOrderDALDto> workOrders;		
		Optional<ProfessionalGroupDALDto> professionalGroup;
		double productivityRate = 0;
		int amount = 0;
				
		workOrders = PersistenceFactory.forWorkOrder().findByMechanic(contract.dni);		
		for (WorkOrderDALDto workOrder : workOrders) {
			//SE HA TENIDO QUE HACER ASI POR NO PODER MODIFICAR LA WORKORDERGATEWAY
			if (workOrder.date.getMonthValue() == present.getMonthValue() 
					&& workOrder.date.getYear() == present.getYear() && workOrder.state.equals("INVOICED")) {
				amount+=workOrder.amount;
			}			
		}
		professionalGroup = PersistenceFactory.forProfessionalGroup().findById(contract.professionalGroupName);
		if (professionalGroup.isPresent()) {
			productivityRate = professionalGroup.get().productivityRate;
		}
		
		double productivityBonus = Math.round(amount*(productivityRate/100)*100.0)/100.0; 
		return productivityBonus;
	}
	
	/**
	 * Método getTrienniumPayment
	 * @param contract
	 * @return trienniumPayment
	 */
	private double getTrienniumPayment(ContractDALDto contract) {		
		Optional<ProfessionalGroupDALDto> professionalGroup;
		double trienniumSalary = 0;
		int yearsWithContract = 0;
		
		yearsWithContract = (int) (ChronoUnit.YEARS.between(contract.startDate, LocalDate.now()))/3;
		professionalGroup = PersistenceFactory.forProfessionalGroup().findById(contract.professionalGroupName);
		if (professionalGroup.isPresent()) {
			trienniumSalary = professionalGroup.get().trieniumSalary;
		}
		
		double trienniumPayment = Math.round(yearsWithContract*trienniumSalary*100.0)/100.0;
		return trienniumPayment;
	}
	
	/**
	 * Método calculateIncomeTax
	 * @param annualBaseWage
	 * @return incomeTax
	 */
	private double getIncomeTax(double annualBaseWage, double grossWage) {
		double incomeTax = 0;
		if ((annualBaseWage >= 0) && (annualBaseWage <= 12450)) {
			incomeTax = 19;
		} else if ((annualBaseWage > 12450) && (annualBaseWage <= 20200)) {
			incomeTax = 24;
		} else if ((annualBaseWage > 20200) && (annualBaseWage <= 35200)) {
			incomeTax = 30;
		} else if ((annualBaseWage > 35200) && (annualBaseWage <= 60000)) {
			incomeTax = 37;
		} else if ((annualBaseWage > 60000) && (annualBaseWage <= 300000)) {
			incomeTax = 45;
		} else if (annualBaseWage > 300000) {
			incomeTax = 47;
		}
		return Math.round((incomeTax/100.0)*grossWage*100.0)/100.0;
	}
	
	/**
	 * Método getNic
	 * @param annualBaseWage
	 * @return nic
	 */
	private double getNic(double annualBaseWage) {
		double nic = (annualBaseWage)/12*NIC_PERCENTAGE;
		return nic;
	}
	
	/**
	 * Método generatePayroll
	 * @param nic 
	 * @param incomeTax 
	 * @param trienniumPayment 
	 * @param productivityBonus 
	 * @param bonus 
	 * @param monthlyWage 
	 */
	private PayrollBLDto generatePayroll(double monthlyWage, double bonus, 
			double productivityBonus, double trienniumPayment, double incomeTax, double nic) {		
		PayrollDALDto payrollDALDto = new PayrollDALDto();
		payrollDALDto.id = UUID.randomUUID().toString();
		payrollDALDto.version = 1L;
		payrollDALDto.date = present;
		payrollDALDto.monthlyWage = monthlyWage;
		payrollDALDto.bonus = bonus;
		payrollDALDto.productivityBonus = productivityBonus;
		payrollDALDto.trienniumPayment = trienniumPayment;
		payrollDALDto.incomeTax = incomeTax;
		payrollDALDto.nic = nic;
		
		PersistenceFactory.forPayroll().add(payrollDALDto);
		
		return PayrollAssembler.toDto(payrollDALDto);
	}	
	
	/**
	 * Método execute
	 * Ejecuta la acción de la clase, GeneratePayrolls
	 * 
	 * @return PayrollBLDto
	 */
	@Override
	public List<PayrollBLDto> execute() throws BusinessException {
		List<PayrollBLDto> payrolls = new ArrayList<PayrollBLDto>();
		List<ContractDALDto> contracts;
		double grossWage;
		
		checkIfPayrollIsAlreadyGenerated();
		
		contracts = PersistenceFactory.forContract().findInForceOrEndingThisMonth(
				present.getMonthValue(), present.getYear());	
		
		for (ContractDALDto contract : contracts) {			
			double monthlyWage = getMonthlyWage(contract.annualBaseWage);
			double bonus = getBonus(contract.annualBaseWage);
			double productivityBonus = getProductivityBonus(contract);
			double trienniumPayment = getTrienniumPayment(contract);
			grossWage = monthlyWage+bonus+productivityBonus+trienniumPayment;
			
			double incomeTax = getIncomeTax(contract.annualBaseWage, grossWage);
			double nic = getNic(contract.annualBaseWage);			
			
			PayrollBLDto payrollBLDto = generatePayroll(monthlyWage, bonus, productivityBonus, trienniumPayment, incomeTax, nic);
			payrolls.add(payrollBLDto);
		}			
		return payrolls;
	}
}
