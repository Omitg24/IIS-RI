package uo.ri.cws.domain;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import uo.ri.cws.domain.WorkOrder.WorkOrderState;
import uo.ri.cws.domain.base.BaseEntity;
import uo.ri.util.assertion.ArgumentChecks;
import uo.ri.util.math.Round;

/**
 * Titulo: Clase Payroll
 * Descripción: Contiene la entidad que corresponde a la nómina
 *
 * @author Omar Teixeira González, UO281847
 * @version 13 nov 2022
 */
public class Payroll extends BaseEntity{
	/**
	 * Constante NIC
	 */
	public static final double NIC_PERCENTAGE = 0.05;
	// natural attributes
	/**
	 * Atributo date
	 */
	private LocalDate date;
	/**
	 * Atributo monthlyWage
	 */
	private double monthlyWage;
	/**
	 * Atributo bonus
	 */
	private double bonus;
	/**
	 * Atributo productivityBonus
	 */
	private double productivityBonus;
	/**
	 * Atributo trienniumPayment
	 */
	private double trienniumPayment;
	/**
	 * Atributo incomeTax
	 */
	private double incomeTax;
	/**
	 * Atributo nic
	 */
	private double nic;
	
	// accidental attributes
	/**
	 * Atributo contract
	 */
	private Contract contract;

	/**
	 * Constructor sin parámetros de la clase Payroll
	 */
	Payroll() {}
	
	/**
	 * Constructor con el contrato de la nómina como parámetros de la clase 
	 * Payroll
	 * 
	 * @param contract, contrato de la nómina
	 */
	public Payroll(Contract contract) {
		this(contract, LocalDate.now() );
	}
	
	/**
	 * Constructor con el contrato y la fecha de la nómina como parámetros de 
	 * la clase Payroll
	 * 
	 * @param contract, contrato de la nómina
	 * @param date, fecha de la nómina
	 */
	public Payroll(Contract contract, LocalDate date) {
		ArgumentChecks.isNotNull(contract, "El contrato de la nómina no puede ser null");
		ArgumentChecks.isNotNull(date, "La fecha de la nómina no puede ser null");
		
		this.contract = contract;
		this.date = date;
		this.monthlyWage = getMonthlyWage(contract.getAnnualBaseWage());
		this.bonus = getBonus(contract.getAnnualBaseWage());
		this.productivityBonus = getProductivityBonus(contract);
		this.trienniumPayment = getTrienniumPayment(contract);
		this.incomeTax = getIncomeTax(contract.getAnnualBaseWage());
		this.nic = getNic(contract.getAnnualBaseWage());
		
		Associations.Run.link(this, contract);
	}

	/**
	 * Constructor con el contrato, la fecha, el salario mensual, el bonus, el
	 * bonus de productividad, el pago trimestral, el IRPF y el NIC de la nómina
	 * como parámetro de la clase Payroll
	 * 
	 * @param contract, contrato de la nómina
	 * @param date, fecha de la nómina
	 * @param monthlyWage, salario de la nómina
	 * @param bonus, bonus de la nómina
	 * @param productivityBonus, bonus de productividad de la nómina
	 * @param trienniumPayment, pago trimestral de la nómina
	 * @param incomeTax, IRPF de la nómina
	 * @param nic, NIC de la nómina
	 */
	public Payroll(Contract contract, LocalDate date, double monthlyWage, 
			double bonus, double productivityBonus, double trienniumPayment,
			double incomeTax, double nic) {
		ArgumentChecks.isNotNull(contract, "El contrato de la nómina no puede ser null");
		ArgumentChecks.isNotNull(date, "La fecha de la nómina no puede ser null");
		ArgumentChecks.isTrue(monthlyWage >= 0, "El salario mensual debe ser mayor o igual que cero");
		ArgumentChecks.isTrue(bonus >= 0, "El bonus debe ser mayor o igual que cero");
		ArgumentChecks.isTrue(productivityBonus >= 0, "El bonus de productividad debe ser mayor o igual que cero");
		ArgumentChecks.isTrue(trienniumPayment >= 0, "El pago trimestral debe ser mayor o igual que cero");
		ArgumentChecks.isTrue(incomeTax >= 0, "El IRPF debe ser mayor o igual que cero");
		ArgumentChecks.isTrue(nic >= 0, "El NIC debe ser mayor o igual que cero");
		
		this.contract = contract;
		this.date = date;
		this.monthlyWage = monthlyWage;
		this.bonus = bonus;
		this.productivityBonus = productivityBonus;
		this.trienniumPayment = trienniumPayment;
		this.incomeTax = incomeTax;
		this.nic = nic;		
		
		Associations.Run.link(this, contract);
	}
	
	/**
	 * Método getMonthlyWage
	 * Devuelve el salario anual de la nómina
	 * 
	 * @param annualBaseWage, salario anual de la nómina
	 * @return monthlyWage, salario mensual de la nómina
	 */
	private double getMonthlyWage(double annualBaseWage) {
		double monthlyWage = annualBaseWage/14;
		return monthlyWage;
	}	

	/**
	 * Método getBonus
	 * Devuelve el bonus de la nómina
	 * 
	 * @param annualBaseWage, salario anual de la nómina
	 * @return bonus, bonus de la nómina
	 */
	private double getBonus(double annualBaseWage) {
		double bonus = 0;
		if (date.getMonth()==Month.JUNE || date.getMonth()==Month.DECEMBER) {
			bonus = annualBaseWage/14;
		}
		return bonus;
	}
	
	/**
	 * M�todo getProductivityBonus
	 * @param annualBaseWage
	 * @return productivityBonus
	 */
	private double getProductivityBonus(Contract contract) {			
		Set<WorkOrder> workOrders = new HashSet<WorkOrder>();		
		int amount = 0;
				
		Optional<Mechanic> om = contract.getMechanic();
		Mechanic m;
		if (om.isPresent()) {
			m = om.get();			
		} else {
			m = contract.getFiredMechanic().get();
		}
		workOrders = m.getAssigned();
		for (WorkOrder workOrder : workOrders) {
			if (workOrder.getDate().getMonthValue() == date.getMonthValue() 
					&& workOrder.getDate().getYear() == date.getYear() 
					&& workOrder.getState().equals(WorkOrderState.INVOICED)) {
				amount+=workOrder.getAmount();
			}			
		}
		
		ProfessionalGroup pg = contract.getProfessionalGroup();
		double productivityRate = pg.getProductivityBonus();		
		double productivityBonus = Round.twoCents(amount*(productivityRate/100)); 
		return productivityBonus;
	}
	
	/**
	 * Método getTrienniumPayment
	 * Devuelve el pago trimestral de la nómina
	 * 
	 * @param contract, contrato
	 * @return trienniumPayment, pago trimestral de la nómina
	 */
	private double getTrienniumPayment(Contract contract) {		
		Optional<ProfessionalGroup> professionalGroup;
		double trienniumSalary = 0;
		int yearsWithContract = 0;
		
		yearsWithContract = (int) (ChronoUnit.YEARS.between(contract.getStartDate(), LocalDate.now()))/3;
		professionalGroup = Optional.ofNullable(contract.getProfessionalGroup());
		if (professionalGroup.isPresent()) {
			trienniumSalary = professionalGroup.get().getTrienniumPayment();
		}
		
		double trienniumPayment = Round.twoCents(yearsWithContract*trienniumSalary);
		return trienniumPayment;
	}
	
	/**
	 * Método getIncomeTax
	 * Devuelve el IRPF de la nómina
	 * 
	 * @param annualBaseWage, salario anual del contrato
	 * @return incomeTax, IRPF de la nómina
	 */
	private double getIncomeTax(double annualBaseWage) {
		double grossWage = this.monthlyWage + this.bonus 
				+ this.productivityBonus + this.trienniumPayment;
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
		return Round.twoCents((incomeTax/100.0)*grossWage);
	}
	
	/**
	 * Método getNic
	 * Devuelve el NIC de la nómina
	 * 
	 * @param annualBaseWage, salario anual del contrato
	 * @return nic, NIC de la nómina
	 */
	private double getNic(double annualBaseWage) {
		double nic = (annualBaseWage)/12*NIC_PERCENTAGE;
		return nic;
	}
	
	/**
	 * Método getDate
	 * Devuelve la fecha de la nómina
	 * 
	 * @return date, fecha de la nómina
	 */
	public LocalDate getDate() {
		return date;
	}

	/**
	 * Método setDate
	 * Modifica la fecha de la nómina
	 * 
	 * @param date, fecha de la nómina
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	/**
	 * Método getMonthlyWage
	 * Devuelve el salario mensual de la nómina
	 * 
	 * @return monthlyWage, salario mensual de la nómina
	 */
	public double getMonthlyWage() {
		return monthlyWage;
	}

	/**
	 * Método setMonthlyWage
	 * Modifica el salario mensual de la nómina
	 * 
	 * @param monthlyWage, salario mensual de la nómina
	 */
	public void setMonthlyWage(double monthlyWage) {
		this.monthlyWage = monthlyWage;
	}

	/**
	 * Método getBonus
	 * Devuelve el bonus de la nómina
	 * 
	 * @return bonus, bonus de la nómina
	 */
	public double getBonus() {
		return bonus;
	}

	/**
	 * Método setBonus
	 * Modifica el bonus de la nómina
	 * 
	 * @param bonus, bonus de la nómina
	 */
	public void setBonus(double bonus) {
		this.bonus = bonus;
	}

	/**
	 * Método getProductivityBonus
	 * Devuelve el bonus de productividad de la nómina
	 * 
	 * @return productivityBonus, bonus de productividad de la nómina
	 */
	public double getProductivityBonus() {
		return productivityBonus;
	}

	/**
	 * Método setProductivityBonus
	 * Modifica el bonus de productividad de la nómina
	 * 
	 * @param productivityBonus, bonus de productividad de la nómina
	 */
	public void setProductivityBonus(double productivityBonus) {
		this.productivityBonus = productivityBonus;
	}

	/**
	 * Método getTrienniumPayment
	 * Devuelve el pago trimestral de la nómina
	 * 
	 * @return trienniumPayment, pago trimestral de la nómina
	 */
	public double getTrienniumPayment() {
		return trienniumPayment;
	}

	/**
	 * Método setTrienniumPayment
	 * Modifica el pago trimestral de la nómina
	 * 
	 * @param trienniumPayment, pago trimestral de la nómina
	 */
	public void setTrienniumPayment(double trienniumPayment) {
		this.trienniumPayment = trienniumPayment;
	}

	/**
	 * Método getIncomeTax
	 * Devuelve el IRPF de la nómina
	 * 
	 * @return incomeTax, IRPF de la nómina
	 */
	public double getIncomeTax() {
		return incomeTax;
	}

	/**
	 * Método setIncomeTax
	 * Modifica el IRPF de la nómina
	 * 
	 * @param incomeTax, IRPF de la nómina
	 */
	public void setIncomeTax(double incomeTax) {
		this.incomeTax = incomeTax;
	}

	/**
	 * Método getNic
	 * Devuelve el NIC de la nómina
	 * 
	 * @return nic, NIC de la nómina
	 */
	public double getNIC() {
		return nic;
	}

	/**
	 * Método setNic
	 * Modifica el NIC de la nómina
	 * 
	 * @param nic, NIC de la nómina
	 */
	public void setNIC(double nic) {
		this.nic = nic;
	}

	/**
	 * Método getContract
	 * Devuelve el contrato de la nómina
	 * 
	 * @return contract, contrato de la nómina
	 */
	public Contract getContract() {
		return contract;
	}

	/**
	 * Método setContract
	 * Modifica el contrato de la nómina
	 * 
	 * @param contract, contrato de la nómina
	 */
	public void setContract(Contract contract) {
		this.contract = contract;
	}

	/**
	 * Método hashCode 
	 * Devuelve el hashCode del objeto
	 * 
	 * @return hashCode, hashCode de la entidad
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(contract, date);
		return result;
	}

	/**
	 * Método equals 
	 * Devuelve true si dos objetos son iguales
	 * 
	 * @return true o false, en función de si los objetos son iguales
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Payroll other = (Payroll) obj;
		return Objects.equals(contract, other.contract) && Objects.equals(date, other.date);
	}

	/**
	 * Método toString 
	 * Devuelve una cadena con los datos de la clase
	 * 
	 * @return string, cadena con los datos de la clase
	 */
	@Override
	public String toString() {
		return "Payroll [date=" + date + ", monthlyWage=" + monthlyWage + ", bonus=" + bonus + ", productivityBonus="
				+ productivityBonus + ", trienniumPayment=" + trienniumPayment + ", incomeTax=" + incomeTax + ", nic="
				+ nic + ", contract=" + contract + "]";
	}
}
