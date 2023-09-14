package uo.ri.cws.domain;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import uo.ri.cws.domain.base.BaseEntity;
import uo.ri.util.assertion.ArgumentChecks;
import uo.ri.util.assertion.StateChecks;
import uo.ri.util.math.Round;

/**
 * Titulo: Clase Contract
 * Descripción: Contiene la entidad que corresponde al contrato 
 *
 * @author Omar Teixeira González, UO281847
 * @version 13 nov 2022
 */
public class Contract extends BaseEntity{
	/**
	 * Titulo: Enumeración ContractState
	 * Descripción: Contiene la enumeración del estado del contrato
	 *
	 * @author Omar Teixeira González, UO281847
	 * @version 13 nov 2022
	 */
	public enum ContractState {
		/**
		 * Estado IN_FORCE
		 */
		IN_FORCE,
		/**
		 * Estado TERMINATED
		 */
		TERMINATED
	}
	
	// natural attributes
	/**
	 * Atributo startDate
	 */
	private LocalDate startDate;
	/**
	 * Atributo endDate
	 */
	private LocalDate endDate;
	/**
	 * Atributo annualBaseWage
	 */
	private double annualBaseWage;
	/**
	 * Atributo settlement
	 */
	private double settlement;
	/**
	 * Atributo state
	 */
	private ContractState state = ContractState.IN_FORCE;
	
	// accidental attributes
	/**
	 * Atributo mechanic
	 */
	private Mechanic mechanic;
	/**
	 * Atributo firedMechanic
	 */
	private Mechanic firedMechanic;
	/**
	 * Atributo contractType
	 */
	private ContractType contractType;
	/**
	 * Atributo professionalGroup
	 */
	private ProfessionalGroup professionalGroup;	
	/**
	 * Atributo payrolls
	 */
	private Set<Payroll> payrolls = new HashSet<>();
	
	/**
	 * Constructor sin parámetros de la clase Contrato
	 */
	Contract() {}
	
	/**
	 * Constructor con el mecánico contratado, el tipo de contrato, el grupo de 
	 * professionalGroup y el salario anual del contrato como parámetros de la 
	 * clase Contract
	 * 
	 * @param mechanic, mecánico contratado
	 * @param type, tipo del contrato
	 * @param professionalGroup, grupo profesional del contrato
	 * @param annualWage, salario anual del contrato
	 */
	public Contract(Mechanic mechanic, ContractType type, 
					ProfessionalGroup professionalGroup, double annualWage) {		
		this(mechanic, type, professionalGroup, LocalDate.now(), annualWage);
	}
	
	
	/**
	 * Constructor con el mecánico contratados, el tipo de contrato, el grupo de 
	 * professionalGroup, la fecha de inicio y el salario anual del contrato 
	 * como parámetros de la clase Contract
	 * 
	 * @param mechanic, mecánico contratado
	 * @param type, tipo del contrato
	 * @param professionalGroup, grupo profesional del contrato
	 * @param startDate, fecha de inicio del contrato
	 * @param annualWage, salario anual del contrato
	 */
	public Contract(Mechanic mechanic, ContractType type, 
					ProfessionalGroup professionalGroup, LocalDate startDate, 
					double annualWage) {		
		ArgumentChecks.isNotNull(mechanic, "El mecánico no puede ser null");
		ArgumentChecks.isNotNull(type, "El tipo de contrato no puede ser null");
		ArgumentChecks.isNotNull(professionalGroup, "El grupo profesional no puede ser null");
		ArgumentChecks.isNotNull(startDate, "La fecha del contrato no puede ser null");
		ArgumentChecks.isTrue(annualWage >= 0, "El salario anual del contrato debe ser mayor o igual que cero");
		
		this.mechanic = mechanic;
		this.contractType = type;
		this.professionalGroup = professionalGroup;
		this.startDate = startDate;
		this.annualBaseWage = annualWage;
		this.settlement = calculateSettlement();
		
		Associations.Hire.link(mechanic, this);
		Associations.Group.link(this, professionalGroup);
		Associations.Type.link(this, type);
	}
	
	/**
	 * Método calculateSettlement
	 * Devuelve la indemnizacion del contrato
	 * 
	 * @return settlementValue, indemnizacion del contrato
	 */
	public double calculateSettlement() {
		double totalGrossWage = 0,				 
				settlementValue = 0, 
				compensationDays = 0;
		double yearsWithContract = ChronoUnit.YEARS.between(startDate, LocalDate.now());		

		if (yearsWithContract >= 1) {
			compensationDays = getContractType().getCompensationDays();
			totalGrossWage = getPayrolls().stream()
					.filter((payroll) -> payroll.getDate().isAfter(LocalDate.now().minusMonths(12)))				
					.map((payroll) -> payroll.getMonthlyWage() + payroll.getBonus() 
					+ payroll.getProductivityBonus() + payroll.getTrienniumPayment())
					.reduce(0d, Double::sum);
			
			
			settlementValue = totalGrossWage/365 * compensationDays * yearsWithContract;
		}			
		
		return Round.twoCents(settlementValue);
	}
	
	/**
	 * Método terminate
	 * Marca el contrato como terminado
	 */
	public void terminate() {
		// asserts the contract is is IN_FORCE state
		// sets the state to TERMINATED
		// sets the endDate to the last day of the actual month
		// recalculates the settlement
		// links the contract of fire
		// unlinks the contract of hire
		StateChecks.isTrue(state == ContractState.IN_FORCE);
		setState(ContractState.TERMINATED);
		this.endDate = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
		this.settlement = calculateSettlement();		
		Associations.Fire.link(this);
		Associations.Hire.unlink(this, mechanic);
	}

	/**
	 * Método isInForce
	 * Devuelve true o false en función de si el estado del contrato es en vigor
	 * (IN_FORCE)
	 * 
	 * @return true o false, en función de si el estado del contrato es en vigor
	 */
	public boolean isInForce() {
		return state == ContractState.IN_FORCE;
	}
	
	/**
	 * Método getStartDate
	 * Devuelve la fecha de inicio del contrato
	 * 
	 * @return startDate, fecha de inicio del contrato
	 */
	public LocalDate getStartDate() {
		return startDate;
	}

	/**
	 * Método setStartDate
	 * Modifica la fecha de inicio del contrato 
	 * 
	 * @param startDate, fecha de inicio del contrato
	 */
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	/**
	 * Método getEndDate
	 * Devuelve la fecha de finalización del contrato
	 * 
	 * @return endDate, fecha de finalización del contrato
	 */
	public Optional<LocalDate> getEndDate() {
		return endDate == null ? Optional.empty() : Optional.of(endDate);
	}

	/**
	 * Método setEndDate
	 * Modifica la fecha de finalización del contrato
	 * 
	 * @param endDate, fecha de finalización del contrato
	 */
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	/**
	 * Método getAnnualBaseWage
	 * Devuelve el salario anual del contrato
	 * 
	 * @return annualBaseWage, salario anual del contrato
	 */
	public double getAnnualBaseWage() {
		return annualBaseWage;
	}

	/**
	 * Método setAnnualBaseWage
	 * Modifica el salario anual del contrato
	 * 
	 * @param annualBaseWage, salario anual del contrato
	 */
	public void setAnnualBaseWage(double annualBaseWage) {
		this.annualBaseWage = annualBaseWage;
	}

	/**
	 * Método getSettlement
	 * Devuelve la indemnizacion del contrato
	 * 
	 * @return settlement, indemnizacion del contrato
	 */
	public double getSettlement() {
		return settlement;
	}

	/**
	 * Método setSettlement
	 * Modifica la indemnizacion del contrato
	 * 
	 * @param settlement, indemnizacion del contrato
	 */
	public void setSettlement(double settlement) {
		this.settlement = settlement;
	}

	/**
	 * Método getState
	 * Modifica el estado del contrato
	 * 
	 * @return state, estado del contrato
	 */
	public ContractState getState() {
		return state;
	}
	
	/**
	 * Método setState
	 * Modifica el estado del contrato
	 * 
	 * @param state, estado del contrato
	 */
	public void setState(ContractState state) {
		this.state = state;
	}

	/**
	 * Método getMechanic
	 * Devuelve el mecánico contratado
	 * 
	 * @return mechanic, mecanico contratado
	 */
	public Optional<Mechanic> getMechanic() {
		return mechanic == null ? Optional.empty() : Optional.of(mechanic);
	}

	/**
	 * Método setMechanic
	 * Moifica el mecánico contratado
	 * 
	 * @param mechanic, mecanico contratado
	 */
	public void setMechanic(Mechanic mechanic) {
		this.mechanic = mechanic;
	}

	/**
	 * Método getFiredMechanic
	 * Devuelve el mecánico despedido
	 * 
	 * @return firedMechanic, mecánico despedido
	 */
	public Optional<Mechanic> getFiredMechanic() {
		return firedMechanic == null ? Optional.empty() : Optional.of(firedMechanic);
	}
	
	/**
	 * Método setFiredMechanic
	 * Modifica el mecánico despedido
	 * 
	 * @param firedMechanic, mecánico despedido
	 */
	public void setFiredMechanic(Mechanic firedMechanic) {
		this.firedMechanic = firedMechanic;
	}

	/**
	 * Método getContractType
	 * Devuelve el tipo del contrato
	 * 
	 * @return contractType, tipo del contrato
	 */
	public ContractType getContractType() {
		return contractType;
	}

	/**
	 * Método setContractType
	 * Modifica el tipo del contrato
	 * 
	 * @param contractType, tipo del contrato
	 */
	public void setContractType(ContractType contractType) {
		this.contractType = contractType;
	}

	/**
	 * Método getProfessionalGroup
	 * Devuelve el grupo prfesional
	 * 
	 * @return professionalGroup, grupo prfesional
	 */
	public ProfessionalGroup getProfessionalGroup() {
		return professionalGroup;
	}

	/**
	 * Método setProfessionalGroup
	 * Modifica el grupo prfesional
	 * 
	 * @param professionalGroup, grupo prfesional
	 */
	public void setProfessionalGroup(ProfessionalGroup professionalGroup) {
		this.professionalGroup = professionalGroup;
	}

	/**
	 * Método getPayrolls
	 * Devuelve las nóminas asociadas al contrato
	 * 
	 * @return payrolls, nóminas asociadas al contrato
	 */
	public Set<Payroll> getPayrolls() {
		return new HashSet<Payroll>(payrolls);
	}

	/**
	 * Método _getPayrolls
	 * Devuelve las nóminas asociadas al contrato para modificarlas
	 * (añadir o eliminar)
	 * 
	 * @return payrolls, nóminas asociadas al contrato
	 */
	Set<Payroll> _getPayrolls() {
		return payrolls;
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
		result = prime * result + Objects.hash(firedMechanic, mechanic, startDate);
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
		Contract other = (Contract) obj;
		return Objects.equals(firedMechanic, other.firedMechanic) && Objects.equals(mechanic, other.mechanic)
				&& Objects.equals(startDate, other.startDate);
	}

	/**
	 * Método toString 
	 * Devuelve una cadena con los datos de la clase
	 * 
	 * @return string, cadena con los datos de la clase
	 */	
	@Override
	public String toString() {
		return "Contract [startDate=" + startDate + ", endDate=" + endDate + ", annualWage=" + annualBaseWage
				+ ", settlement=" + settlement + ", state=" + state + ", mechanic=" + mechanic + ", firedMechanic="
				+ firedMechanic + ", type=" + contractType + ", professionalGroup=" + professionalGroup + "]";
	}	
}
