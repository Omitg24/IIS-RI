package uo.ri.cws.domain;

import uo.ri.cws.domain.base.BaseEntity;
import uo.ri.util.assertion.ArgumentChecks;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "TVehicles")
public class Vehicle extends BaseEntity {
    @Column(unique = true)
    private String plateNumber;
    @Basic(optional = false)
    @Column(name = "BRAND")
    private String make;
    @Basic(optional = false)
    private String model;

    @ManyToOne(optional = false)
    private Client client;
    @ManyToOne(optional = false)
    private VehicleType type;
    @OneToMany(mappedBy = "vehicle")
    private Set<WorkOrder> workOrders = new HashSet<WorkOrder>();

    Vehicle() {
    }

    public Vehicle(String plateNumber) {
        this(plateNumber, "no-make", "no-model");
    }

    public Vehicle(String plateNumber, String make, String model) {
        ArgumentChecks.isNotBlank(plateNumber);
        ArgumentChecks.isNotBlank(make);
        ArgumentChecks.isNotBlank(model);

        this.plateNumber = plateNumber;
        this.make = make;
        this.model = model;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }


    public Client getClient() {
        return client;
    }

    void setClient(Client client) {
        this.client = client;
    }

    public VehicleType getType() {
        return type;
    }

    void setType(VehicleType type) {
        this.type = type;
    }


    public Set<WorkOrder> getWorkOrders() {
        return new HashSet<>(workOrders);
    }

    Set<WorkOrder> _getWorkOrders() {
        return workOrders;
    }

    @Override
    public int hashCode() {
        return Objects.hash(make, model, plateNumber);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Vehicle other = (Vehicle) obj;
        return Objects.equals(make, other.make) && Objects.equals(model, other.model)
                && Objects.equals(plateNumber, other.plateNumber);
    }

    @Override
    public String toString() {
        return "Vehicle [plateNumber=" + plateNumber + ", make=" + make + ", model=" + model + "]";
    }

}
