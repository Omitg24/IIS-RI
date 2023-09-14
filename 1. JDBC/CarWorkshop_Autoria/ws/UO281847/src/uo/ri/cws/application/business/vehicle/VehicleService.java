package uo.ri.cws.application.business.vehicle;

import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.business.BusinessException;

/**
 * This service is intended to be used by the Foreman It follows the ISP
 * principle (@see SOLID principles from RC Martin)
 */
public interface VehicleService {
    /**
     * AUTORSHIP 28/10/2022
     * 
     * List all the vehicles of a client specified by the dni.
     * 
     * @param dni
     * @return A list of VehicleBLDto that belongs to a client given a DNI.
     * @throws IllegalArgumentException if the dni is null or empty.
     */
    public List<VehicleBLDto> findByClientDni(String dni)
	    throws BusinessException;

    /**
     * @param plate number
     * @return an Optional with the vehicle dto specified be the plate number
     * @throws BusinessException, DOES NOT
     */
    Optional<VehicleBLDto> findVehicleByPlate(String plate)
	    throws BusinessException;

    public static class VehicleBLDto {
	public String id;
	public Long version;

	public String plateNumber;
	public String make;
	public String model;
	public String clientId;
	public String vehicleTypeId;

    }

}
