package uo.ri.cws.domain;

import uo.ri.cws.domain.base.BaseEntity;
import uo.ri.util.assertion.ArgumentChecks;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "TWorkOrders",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {
                        "DATE", "VEHICLE_ID"
                })
        }

)
public class WorkOrder extends BaseEntity {
    public enum WorkOrderStatus {
        OPEN,
        ASSIGNED,
        FINISHED,
        INVOICED
    }

    // natural attributes
    @Basic(optional = false)
    private LocalDateTime date;
    @Basic(optional = false)
    private String description;
    private double amount = 0.0;
    @Enumerated(EnumType.STRING)
    private WorkOrderStatus status = WorkOrderStatus.OPEN;

    // accidental attributes
    @ManyToOne(optional = false)
    private Vehicle vehicle;
    @ManyToOne
    private Mechanic mechanic;
    @ManyToOne
    private Invoice invoice;
    @OneToMany(mappedBy = "workOrder")
    private Set<Intervention> interventions = new HashSet<>();

    WorkOrder() {
    }

    public WorkOrder(Vehicle vehicle, LocalDateTime date) {
        this(vehicle, date, "no-description");
    }

    public WorkOrder(Vehicle vehicle, LocalDateTime date, String description) {
        ArgumentChecks.isNotNull(vehicle);
        ArgumentChecks.isNotNull(date);
        ArgumentChecks.isNotBlank(description);

        this.date = date;
        this.description = description;

        Associations.Fix.link(vehicle, this);
    }

    /**
     * Changes it to INVOICED state given the right conditions
     * This method is called from Invoice.addWorkOrder(...)
     *
     * @throws IllegalStateException if
     *                               - The work order is not FINISHED, or
     *                               - The work order is not linked with the invoice
     * @see UML_State diagrams on the problem statement document
     */
    public void markAsInvoiced() {

    }

    /**
     * Changes it to FINISHED state given the right conditions and
     * computes the amount
     *
     * @throws IllegalStateException if
     *                               - The work order is not in ASSIGNED state, or
     *                               - The work order is not linked with a mechanic
     * @see UML_State diagrams on the problem statement document
     */
    public void markAsFinished() {

    }

    /**
     * Changes it back to FINISHED state given the right conditions
     * This method is called from Invoice.removeWorkOrder(...)
     *
     * @throws IllegalStateException if
     *                               - The work order is not INVOICED, or
     *                               - The work order is still linked with the invoice
     * @see UML_State diagrams on the problem statement document
     */
    public void markBackToFinished() {

    }

    /**
     * Links (assigns) the work order to a mechanic and then changes its status
     * to ASSIGNED
     *
     * @throws IllegalStateException if
     *                               - The work order is not in OPEN status, or
     *                               - The work order is already linked with another mechanic
     * @see UML_State diagrams on the problem statement document
     */
    public void assignTo(Mechanic mechanic) {

    }

    /**
     * Unlinks (deassigns) the work order and the mechanic and then changes
     * its status back to OPEN
     *
     * @throws IllegalStateException if
     *                               - The work order is not in ASSIGNED status
     * @see UML_State diagrams on the problem statement document
     */
    public void desassign() {

    }

    /**
     * In order to assign a work order to another mechanic is first have to
     * be moved back to OPEN state and unlinked from the previous mechanic.
     *
     * @throws IllegalStateException if
     *                               - The work order is not in FINISHED status
     * @see UML_State diagrams on the problem statement document
     */
    public void reopen() {

    }

    public Set<Intervention> getInterventions() {
        return new HashSet<>(interventions);
    }

    Set<Intervention> _getInterventions() {
        return interventions;
    }

    void _setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    void _setMechanic(Mechanic mechanic) {
        this.mechanic = mechanic;
    }

    void _setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

}
