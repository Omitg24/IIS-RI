package uo.ri.cws.persistence.entity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import uo.ri.cws.domain.*;
import uo.ri.cws.persistence.util.UnitOfWork;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class SubstitutionMappingTests {

    private EntityManagerFactory factory;
    private UnitOfWork unitOfWork;

    private VehicleType vehicleType;
    private Vehicle vehicle;
    private WorkOrder workOrder;
    private Mechanic mechanic;
    private Intervention intervention;
    private SparePart sparePart;
    private Substitution substitution;

    @Before
    public void setUp() {
        factory = Persistence.createEntityManagerFactory("carworkshop");
        unitOfWork = UnitOfWork.over(factory);

        vehicleType = new VehicleType("vehicle-type", 50);
        vehicle = new Vehicle("plateNumber", "make", "model");
        Associations.Classify.link(vehicleType, vehicle);

        LocalDateTime now = LocalDateTime.now();
        workOrder = new WorkOrder(vehicle, now, "description");

        mechanic = new Mechanic("dni", "name", "surname");
        workOrder.assignTo(mechanic);
        intervention = new Intervention(mechanic, workOrder, 60);
        sparePart = new SparePart("spare-code", "spare description", 100.0);

        substitution = new Substitution(sparePart, intervention, 1);

        workOrder.markAsFinished();

        unitOfWork.persist(
                vehicleType, mechanic,
                vehicle, intervention, workOrder,
                sparePart, substitution
        );
    }

    @After
    public void tearDown() {
        unitOfWork.remove(
                vehicleType, mechanic,
                vehicle, intervention, workOrder,
                sparePart, substitution
        );
        factory.close();
    }

    /**
     * All fields of substitution are persisted properly
     */
    @Test
    public void testAllFieldsPersisted() {

        Substitution restored = unitOfWork.findById(Substitution.class,
                substitution.getId());

        assertEquals(substitution.getId(), restored.getId());
        assertEquals(substitution.getQuantity(), restored.getQuantity());
        assertEquals(substitution.getIntervention(), restored.getIntervention());
        assertEquals(substitution.getSparePart(), restored.getSparePart());
    }

    /**
     * When two substitution are for the same intervention and spare part,
     * the second cannot be persisted
     */
    @Test(expected = PersistenceException.class)
    public void testRepeated() {
        Substitution repeated = new Substitution(
                substitution.getSparePart(),
                substitution.getIntervention(),
                100
        );

        unitOfWork.persist(repeated);
    }

}
