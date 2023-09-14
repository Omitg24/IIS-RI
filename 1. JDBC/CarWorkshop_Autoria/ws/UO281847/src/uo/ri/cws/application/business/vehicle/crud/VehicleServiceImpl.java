package uo.ri.cws.application.business.vehicle.crud;

import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.util.CommandExecutor;
import uo.ri.cws.application.business.vehicle.VehicleService;
import uo.ri.cws.application.business.vehicle.crud.commands.FindByClientTS;

/**
 * Titulo: Clase VehicleServiceImpl
 *
 * @author Omar Teixeira González, UO281847
 * @version 28 oct 2022
 */
public class VehicleServiceImpl implements VehicleService {

	/**
	 * Método findByClientDni
	 * @param dni
	 */
	@Override
	public List<VehicleBLDto> findByClientDni(String dni) throws BusinessException {
		return new CommandExecutor().execute(new FindByClientTS(dni));
	}

	@Override
	public Optional<VehicleBLDto> findVehicleByPlate(String plate) throws BusinessException {
		// TODO Auto-generated method stub
		return Optional.empty();
	}
}
