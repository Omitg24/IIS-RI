package uo.ri.cws.application.business.mechanic.crud;

import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.mechanic.MechanicService;
import uo.ri.cws.application.business.mechanic.crud.commands.AddMechanicTS;
import uo.ri.cws.application.business.mechanic.crud.commands.DeleteMechanicTS;
import uo.ri.cws.application.business.mechanic.crud.commands.FindAllMechanics;
import uo.ri.cws.application.business.mechanic.crud.commands.UpdateMechanic;

/**
 * Titulo: Clase MechanicServiceImpl
 *
 * @author Omar Teixeira Gonz�lez, UO281847
 * @version 13 oct 2022
 */
public class MechanicServiceImpl implements MechanicService {
	/**
	 * M�todo addMechanic
	 */
	@Override
	public MechanicBLDto addMechanic(MechanicBLDto mechanic) throws BusinessException {		
		return new AddMechanicTS(mechanic).execute();
	}

	/**
	 * M�todo deleteMechanic
	 */
	@Override
	public void deleteMechanic(String idMechanic) throws BusinessException {
		new DeleteMechanicTS(idMechanic).execute();
	}

	/**
	 * M�todo updateMechanic
	 */
	@Override
	public void updateMechanic(MechanicBLDto mechanic) throws BusinessException {
		new UpdateMechanic(mechanic).execute();
	}

	/**
	 * M�todo findMechanicById
	 */
	@Override
	public Optional<MechanicBLDto> findMechanicById(String idMechanic) throws BusinessException {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	/**
	 * M�todo findMechanicByDni
	 */
	@Override
	public Optional<MechanicBLDto> findMechanicByDni(String dniMechanic) throws BusinessException {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	/**
	 * M�todo findAllMechanics
	 */
	@Override
	public List<MechanicBLDto> findAllMechanics() throws BusinessException {
		return new FindAllMechanics().execute();
	}

}
