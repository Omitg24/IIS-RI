package uo.ri.cws.domain;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import uo.ri.cws.domain.base.BaseEntity;
import uo.ri.util.assertion.ArgumentChecks;

/**
 * Titulo: Clase Intervention
 * Descripción: Contiene la entidad que corresponde a la intervención de un 
 * mecánico en una avería 
 *
 * @author Omar Teixeira González, UO281847
 * @version 12 nov 2022
 */
@Entity
@Table(name = "TInterventions", uniqueConstraints = {
		@UniqueConstraint(columnNames = { 
				"WORLORDER_ID", "MECHANIC_ID", "DATE" 
			}
		) 
	}
)
public class Intervention extends BaseEntity {
	// natural attributes
	/**
	 * Atributo date
	 */
	@Column(unique = true)
	@Basic(optional = false)
	private LocalDateTime date;
	/**
	 * Atributo minutes
	 */
	@Basic(optional = false)
	private int minutes;

	// accidental attributes
	/**
	 * Atributo workOrder
	 */
	@ManyToOne
	private WorkOrder workOrder;
	/**
	 * Atributo mechanic
	 */
	@ManyToOne
	private Mechanic mechanic;
	/**
	 * Atributo substitutions
	 */
	@OneToMany(mappedBy = "intervention")
	private Set<Substitution> substitutions = new HashSet<>();

	/**
	 * Constructor sin parámetros de la clase Intervention
	 */
	Intervention() {}

	/**
	 * Constructor con el mecánico, la avería y los minutos de la intervención 
	 * como parámetros de la clase Intervention
	 * 
	 * @param mechanic, mecánico
	 * @param workOrder, avería
	 * @param minutes, minutos de la intervención
	 */
	public Intervention(Mechanic mechanic, WorkOrder workOrder, int minutes) {
		this(mechanic, workOrder, LocalDateTime.now(), minutes);
	}

	/**
	 * Constructor con el mecánico, la avería, la fecha y los minutos de la 
	 * intervención como parámetros de la clase Intervention
	 * 
	 * @param mechanic, mecánico
	 * @param workOrder, avería
	 * @param date, fecha de la intervención
	 * @param minutes, minutos de la intervención
	 */
	public Intervention(Mechanic mechanic, WorkOrder workOrder, LocalDateTime date, int minutes) {
		ArgumentChecks.isNotNull(mechanic, "El mecánico no puede ser null");
		ArgumentChecks.isNotNull(workOrder, "La avería no puede ser null");
		ArgumentChecks.isNotNull(date, "La fecha de la factura no puede ser null");
		ArgumentChecks.isTrue(minutes >= 0, "Los minutos de la factura deben ser mayor o igual a cero");

		this.date = date;
		this.minutes = minutes;

		Associations.Intervene.link(workOrder, this, mechanic);
	}

	/**
	 * Método getAmount
	 * Devuelve la cantidad de la intervención
	 * 
	 * @return amount, cantidad de la intervención
	 */
	public double getAmount() {
		return getSubstitutionsAmount() * getWorkOrderAmount();
	}
	
	/**
	 * Método getSubstitutionsAmount
	 * Devuelve la cantidad de las sustituciones
	 * 
	 * @return amount, cantidad de las sustituciones
	 */
	public double getSubstitutionsAmount() {
		double amount = 0.0;
		for (Substitution substitution : substitutions) {
			amount += substitution.getAmount();
		}
		return amount;
	}
	
	/**
	 * Método getWorkOrderAmount
	 * Devuelve la cantidad de las averías
	 * 
	 * @return amount, cantidad de las averías
	 */
	public double getWorkOrderAmount() {
		return workOrder.getVehicle().getType().getPricePerHour()/60 * minutes;
	}
	
	/**
	 * Método getDate
	 * Devuelve la fecha de la intervención
	 * 
	 * @return date, fecha de la intervención
	 */
	public LocalDateTime getDate() {
		return date;
	}

	/**
	 * Método setDate
	 * Modifica la fecha de la intervención
	 * 
	 * @param date, fecha de la intervención
	 */
	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	/**
	 * Método getMinutes
	 * Devuelve los minutos de la intervención
	 * 
	 * @return minutes, minutos de la intervención
	 */
	public int getMinutes() {
		return minutes;
	}

	/**
	 * Método setMinutes
	 * Modifica los minutos de la intervención
	 * 
	 * @param minutes, minutos de la intervención
	 */
	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	/**
	 * Método getWorkOrder
	 * Devuelve la avería de la intervención
	 *  
	 * @return workOrder, avería de la intervención
	 */
	public WorkOrder getWorkOrder() {
		return workOrder;
	}
	
	/**
	 * Método _setWorkOrder
	 * Modifica la avería de la intervención
	 *  
	 * @param workOrder, avería de la intervención
	 */
	public void _setWorkOrder(WorkOrder workOrder) {
		this.workOrder = workOrder;
	}
	
	/**
	 * Método getMechanic
	 * Devuelve el mecánico de la intervención
	 * 
	 * @return mechanic, mecánico de la intervención
	 */
	public Mechanic getMechanic() {
		return mechanic;
	}

	/**
	 * Método _setMechanic
	 * Modifica el mecánico de la intervención
	 * 
	 * @param mechanic, mecánico de la intervención
	 */
	public void _setMechanic(Mechanic mechanic) {
		this.mechanic = mechanic;
	}	

	/**
	 * Método getSubstitutions
	 * Devuelve las sustituciones de la intervención
	 * 
	 * @return substitutions, sustituciones de la intervención
	 */
	public Set<Substitution> getSubstitutions() {
		return new HashSet<>(substitutions);
	}

	/**
	 * Método _getSubstitutions
	 * Devuelve las sustituciones de la intervención para modificarlas 
	 * (añadir o eliminar)
	 * 
	 * @return  sustitutions, sustituciones de la intervención
	 */
	Set<Substitution> _getSubstitutions() {
		return substitutions;
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
		result = prime * result + Objects.hash(date, mechanic, workOrder);
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
		Intervention other = (Intervention) obj;
		return Objects.equals(date, other.date) && Objects.equals(mechanic, other.mechanic)
				&& Objects.equals(workOrder, other.workOrder);
	}

	/**
	 * Método toString 
	 * Devuelve una cadena con los datos de la clase
	 * 
	 * @return string, cadena con los datos de la clase
	 */
	@Override
	public String toString() {
		return "Intervention [date=" + date + ", minutes=" + minutes + ", workOrder=" + workOrder + ", mechanic="
				+ mechanic + "]";
	}	
}
