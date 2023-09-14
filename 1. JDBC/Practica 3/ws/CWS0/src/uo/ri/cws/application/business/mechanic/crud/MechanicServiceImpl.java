package uo.ri.cws.application.business.mechanic.crud;

import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.mechanic.MechanicService;
import uo.ri.cws.application.business.mechanic.crud.commands.AddMechanic;
import uo.ri.cws.application.business.mechanic.crud.commands.DeleteMechanic;
import uo.ri.cws.application.business.mechanic.crud.commands.FindAllMechanics;
import uo.ri.cws.application.business.mechanic.crud.commands.UpdateMechanic;

/**
 * Titulo: Clase MechanicServiceImpl
 *
 * @author Omar Teixeira González, UO281847
 * @version 13 oct 2022
 */
public class MechanicServiceImpl implements MechanicService {
	/**
	 * Método addMechanic
	 */
	@Override
	public MechanicBLDto addMechanic(MechanicBLDto mechanic) throws BusinessException {		
		return new AddMechanic(mechanic).execute();
	}

	/**
	 * Método deleteMechanic
	 */
	@Override
	public void deleteMechanic(String idMechanic) throws BusinessException {
		new DeleteMechanic(idMechanic).execute();
	}

	/**
	 * Método updateMechanic
	 */
	@Override
	public void updateMechanic(MechanicBLDto mechanic) throws BusinessException {
		new UpdateMechanic(mechanic).execute();
	}

	/**
	 * Método findMechanicById
	 */
	@Override
	public Optional<MechanicBLDto> findMechanicById(String idMechanic) throws BusinessException {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	/**
	 * Método findMechanicByDni
	 */
	@Override
	public Optional<MechanicBLDto> findMechanicByDni(String dniMechanic) throws BusinessException {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	/**
	 * Método findAllMechanics
	 */
	@Override
	public List<MechanicBLDto> findAllMechanics() throws BusinessException {
		return new FindAllMechanics().execute();
	}

}
