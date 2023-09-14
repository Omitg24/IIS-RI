package uo.ri.cws.domain;

import uo.ri.cws.domain.base.BaseEntity;
import uo.ri.util.assertion.ArgumentChecks;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "TInvoices")
public class Invoice extends BaseEntity {
    public enum InvoiceStatus {NOT_YET_PAID, PAID}

    // natural attributes
    @Column(unique = true)
    private Long number;
    @Basic(optional = false)
    private LocalDate date;
    private double amount;
    private double vat;
    @Enumerated(EnumType.STRING)
    private InvoiceStatus status = InvoiceStatus.NOT_YET_PAID;

    // accidental attributes
    @OneToMany(mappedBy = "invoice")
    private Set<WorkOrder> workOrders = new HashSet<>();
    @Transient
    private Set<Charge> charges = new HashSet<>();

    Invoice() {
    }

    public Invoice(Long number) {
        this(number, LocalDate.now(), new ArrayList<WorkOrder>());
    }

    public Invoice(Long number, LocalDate date) {
        this(number, date, new ArrayList<WorkOrder>());
    }

    public Invoice(Long number, List<WorkOrder> workOrders) {
        this(number, LocalDate.now(), workOrders);
    }

    // full constructor
    public Invoice(Long number, LocalDate date, List<WorkOrder> workOrders) {
        ArgumentChecks.isTrue(number >= 0);
        ArgumentChecks.isNotNull(date);
        ArgumentChecks.isNotNull(workOrders);

        this.number = number;
        this.date = date;
        this.workOrders.addAll(workOrders);
    }


    /**
     * Computes amount and vat (vat depends on the date)
     */
    private void computeAmount() {

    }

    /**
     * Adds (double links) the workOrder to the invoice and updates the amount and vat
     *
     * @param workOrder
     * @throws IllegalStateException if the invoice status is not NOT_YET_PAID
     * @see UML_State diagrams on the problem statement document
     */
    public void addWorkOrder(WorkOrder workOrder) {

    }

    /**
     * Removes a work order from the invoice and recomputes amount and vat
     *
     * @param workOrder
     * @throws IllegalStateException if the invoice status is not NOT_YET_PAID
     * @see UML_State diagrams on the problem statement document
     */
    public void removeWorkOrder(WorkOrder workOrder) {

    }

    /**
     * Marks the invoice as PAID, but
     *
     * @throws IllegalStateException if
     *                               - Is already settled
     *                               - Or the amounts paid with charges to payment means do not cover
     *                               the total of the invoice
     */
    public void settle() {

    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getVat() {
        return vat;
    }

    public void setVat(double vat) {
        this.vat = vat;
    }

    public InvoiceStatus getStatus() {
        return status;
    }

    public void setStatus(InvoiceStatus status) {
        this.status = status;
    }

    public Set<WorkOrder> getWorkOrders() {
        return new HashSet<>(workOrders);
    }

    Set<WorkOrder> _getWorkOrders() {
        return workOrders;
    }

    public Set<Charge> getCharges() {
        return new HashSet<>(charges);
    }

    Set<Charge> _getCharges() {
        return charges;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, date, number, status, vat);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Invoice other = (Invoice) obj;
        return Double.doubleToLongBits(amount) == Double.doubleToLongBits(other.amount)
                && Objects.equals(date, other.date) && Objects.equals(number, other.number) && status == other.status
                && Double.doubleToLongBits(vat) == Double.doubleToLongBits(other.vat);
    }

    @Override
    public String toString() {
        return "Invoice [number=" + number + ", date=" + date + ", amount=" + amount + ", vat=" + vat + ", status="
                + status + "]";
    }


}
