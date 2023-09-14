package uo.ri.cws.demo;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import jdbc.Jdbc;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.vehicle.VehicleService;

public class TestVehiclesByDni {
    private static List<VehicleService.VehicleBLDto> vehicles = getVehicles();
    private final static String ADD = "INSERT INTO TVEHICLES(ID, MAKE, MODEL, PLATENUMBER, VERSION, CLIENT_ID, VEHICLETYPE_ID) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private final static String REMOVE = "DELETE FROM TVEHICLES WHERE ID=?";

    private static List<VehicleService.VehicleBLDto> getVehicles() {
	List<VehicleService.VehicleBLDto> vehicles = new ArrayList<>();
	VehicleService.VehicleBLDto v;

	for (String plate : new String[] { "1234ZSD", "6543DHP", "7823FGH",
		"4672JKL", "7893LSK" }) {
	    v = new VehicleService.VehicleBLDto();
	    v.id = UUID.randomUUID().toString();
	    v.version = 1L;
	    v.plateNumber = plate;
	    v.make = "Hyundai";
	    v.model = "i30";
	    v.clientId = "03a0d006-4179-4120-8d6c-f51b26061478";
	    v.vehicleTypeId = "9cccd3d1-3395-4d94-b43a-a1152a86b9d3";
	}
	return vehicles;
    }

    @BeforeClass
    public static void setUp() {
	try (Connection c = Jdbc.createThreadConnection();
		PreparedStatement pst = c.prepareStatement(ADD)) {
	    for (VehicleService.VehicleBLDto v : vehicles) {
		pst.setString(1, v.id);
		pst.setString(2, v.make);
		pst.setString(3, v.model);
		pst.setString(4, v.plateNumber);
		pst.setLong(5, v.version);
		pst.setString(6, v.clientId);
		pst.setString(7, v.vehicleTypeId);

		pst.executeUpdate();
	    }
	} catch (SQLException e) {
	    throw new RuntimeException(e);
	}
    }

    @Test
    public void vehiclesByDni() throws BusinessException {
	List<VehicleService.VehicleBLDto> vs = BusinessFactory.forVehicle()
		.findByClientDni("227647456");
	assertTrue(vehicles.size() == vs.size());
	/* ids match */
	Map<String, VehicleService.VehicleBLDto> map = vehicles.stream()
		.collect(Collectors.toMap(dto -> dto.id, dto -> dto));
	List<VehicleService.VehicleBLDto> hasDiffId = vs.stream()
		.filter(s -> !map.get(s.id).id.equals(s.id))
		.collect(Collectors.toList());
	assertTrue(hasDiffId.isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void vehiclesWithEmptyDni() throws BusinessException {
	BusinessFactory.forVehicle().findByClientDni(" ");

    }

    @Test(expected = IllegalArgumentException.class)
    public void vehiclesWithNullDni() throws BusinessException {
	BusinessFactory.forVehicle().findByClientDni(null);

    }

    @AfterClass
    public static void clean() {
	try (Connection c = Jdbc.createThreadConnection();
		PreparedStatement pst = c.prepareStatement(REMOVE)) {
	    for (VehicleService.VehicleBLDto v : vehicles) {
		pst.setString(1, v.id);
		pst.executeUpdate();
	    }
	} catch (SQLException e) {
	    throw new RuntimeException(e);
	}
    }
}
