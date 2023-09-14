package uo.ri.cws.domain;

import uo.ri.cws.domain.base.BaseEntity;
import uo.ri.util.assertion.ArgumentChecks;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "TClients")
public class Client extends BaseEntity {
    @Column(unique = true)
    private String dni;
    @Basic(optional = false)
    private String name;
    @Basic(optional = false)
    private String surname;
    private String email;
    private String phone;
    private Address address;
    @OneToMany(mappedBy = "client")
    private Set<Vehicle> vehicles = new HashSet<Vehicle>();
    @Transient
    private Set<PaymentMean> payments = new HashSet<PaymentMean>();

    Client() {
    }

    public Client(String dni) {
        this(dni, "no-name", "no-surname", "no-email", "no-phone", null);
    }


    public Client(String dni, String name, String surname) {
        this.dni = dni;
        this.name = name;
        this.surname = surname;
    }


    public Client(String dni, String name, String surname, String email, String phone, Address address) {
        ArgumentChecks.isNotBlank(dni);
        ArgumentChecks.isNotBlank(name);
        ArgumentChecks.isNotBlank(surname);
        ArgumentChecks.isNotBlank(email);
        ArgumentChecks.isNotBlank(phone);

        this.dni = dni;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }


    public String getDni() {
        return dni;
    }


    public void setDni(String dni) {
        this.dni = dni;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getSurname() {
        return surname;
    }


    public void setSurname(String surname) {
        this.surname = surname;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getPhone() {
        return phone;
    }


    public void setPhone(String phone) {
        this.phone = phone;
    }


    public Address getAddress() {
        return address;
    }


    public void setAddress(Address address) {
        this.address = address;
    }


    public Set<Vehicle> getVehicles() {
        return new HashSet<Vehicle>(vehicles);
    }


    Set<Vehicle> _getVehicles() {
        return vehicles;
    }


    public Set<PaymentMean> getPayments() {
        return new HashSet<>(payments);
    }


    Set<PaymentMean> _getPayments() {
        return payments;
    }


    @Override
    public int hashCode() {
        return Objects.hash(address, dni, email, name, phone, surname);
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Client other = (Client) obj;
        return Objects.equals(address, other.address) && Objects.equals(dni, other.dni)
                && Objects.equals(email, other.email) && Objects.equals(name, other.name)
                && Objects.equals(phone, other.phone) && Objects.equals(surname, other.surname);
    }


    @Override
    public String toString() {
        return "Client [dni=" + dni + ", name=" + name + ", surname=" + surname + ", email=" + email + ", phone="
                + phone + ", address=" + address + "]";
    }
}

