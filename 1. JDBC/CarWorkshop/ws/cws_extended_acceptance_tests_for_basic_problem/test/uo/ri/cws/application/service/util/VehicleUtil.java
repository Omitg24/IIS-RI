package uo.ri.cws.application.service.util;

import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;

import uo.ri.cws.application.persistence.vehicle.VehicleGateway.VehicleDALDto;
import uo.ri.cws.application.service.util.sql.AddVehicleSqlUnitOfWork;
import uo.ri.cws.application.service.util.sql.FindVehicleSqlUnitOfWork;

public class VehicleUtil {

	private VehicleDto dto = createDefaultVehicle();

	public VehicleUtil randomPlate() {
		dto.plateNumber = RandomStringUtils.randomAlphanumeric(7);
		return this;
	}

	public VehicleUtil register() {
		new AddVehicleSqlUnitOfWork(dto).execute();
		return this;
	}

	public VehicleUtil findByPlate(String plate ) {
		FindVehicleSqlUnitOfWork finder = new FindVehicleSqlUnitOfWork(plate);
		finder.execute();
		this.dto = new VehicleDto();
		VehicleDALDto record = finder.get();
		dto.id = record.id;
		dto.client_id = record.client_id;
		dto.make = record.make;
		dto.model = record.model;
		dto.plateNumber = record.platenumber;
		dto.vehicleType_id = record.vehicletype_id;
		dto.version = record.version;
		return this;
	}
	
	public VehicleDto get() {
		return dto;
	}

	private VehicleDto createDefaultVehicle() {
		VehicleDto res = new VehicleDto();
		res.id = UUID.randomUUID().toString();
		res.version = 1L;
		res.plateNumber = RandomStringUtils.randomAlphanumeric(9);
		res.make = RandomStringUtils.randomAlphabetic(4);
		res.model = RandomStringUtils.randomAlphabetic(4);
		res.client_id = RandomStringUtils.randomAlphabetic(4);

		return res;
	}

	public class VehicleDto {
		public String id;
		public Long version;
		
		public String plateNumber;
		public String make;
		public String model;
		public String client_id;
		public String vehicleType_id;
	}

	public VehicleUtil withOwner(String id) {
		dto.client_id = id;
		return this;
	}

	public VehicleUtil withPlate(String string) {
		dto.plateNumber = string;
		return this;
	}

}
