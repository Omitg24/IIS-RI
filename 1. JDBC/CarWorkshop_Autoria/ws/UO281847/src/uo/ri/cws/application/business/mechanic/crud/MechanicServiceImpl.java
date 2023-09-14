package uo.ri.cws.application.business.mechanic.crud;

import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.mechanic.MechanicService;
import uo.ri.cws.application.business.mechanic.crud.commands.AddMechanicTS;
import uo.ri.cws.application.business.mechanic.crud.commands.DeleteMechanicTS;
import uo.ri.cws.application.business.mechanic.crud.commands.FindAllMechanicsTS;
import uo.ri.cws.application.business.mechanic.crud.commands.FindMechanicByDniTS;
import uo.ri.cws.application.business.mechanic.crud.commands.FindMechanicByIdTS;
import uo.ri.cws.application.business.mechanic.crud.commands.FindMechanicsWithContractInForceInContractTypeTS;
import uo.ri.cws.application.business.mechanic.crud.commands.FindMechanicsWithContractInForceTS;
import uo.ri.cws.application.business.mechanic.crud.commands.UpdateMechanicTS;
import uo.ri.cws.application.business.util.CommandExecutor;

/**
 * Titulo: Clase MechanicServiceImpl
 *
 * @author Omar Teixeira Gonz�lez, UO281847
 * @version 13 oct 2022
 */
public class MechanicServiceImpl implements MechanicService {
	/**
	 * M�todo addMechanic 
	 * A�ade un mec�nico
	 */
	@Override
	public MechanicBLDto addMechanic(MechanicBLDto mechanic) throws BusinessException {
		return new CommandExecutor().execute(new AddMechanicTS(mechanic));
	}

	/**
	 * M�todo deleteMechanic 
	 * Borra un mec�nico
	 */
	@Override
	public void deleteMechanic(String idMechanic) throws BusinessException {
		new CommandExecutor().execute(new DeleteMechanicTS(idMechanic));
	}

	/**
	 * M�todo updateMechanic 
	 * Actualiza un mec�nico
	 */
	@Override
	public void updateMechanic(MechanicBLDto mechanic) throws BusinessException {
		new CommandExecutor().execute(new UpdateMechanicTS(mechanic));
	}

	/**
	 * M�todo findMechanicById 
	 * Busca un mec�nico por Id
	 */
	@Override
	public Optional<MechanicBLDto> findMechanicById(String idMechanic) throws BusinessException {
		return new CommandExecutor().execute(new FindMechanicByIdTS(idMechanic));
	}

	/**
	 * M�todo findMechanicByDni 
	 * Busca un mec�nico por Dni
	 */
	@Override
	public Optional<MechanicBLDto> findMechanicByDni(String dniMechanic) throws BusinessException {
		return new CommandExecutor().execute(new FindMechanicByDniTS(dniMechanic));
	}

	/**
	 * M�todo findAllMechanics 
	 * Busca todos los mec�nicos
	 */
	@Override
	public List<MechanicBLDto> findAllMechanics() throws BusinessException {
		return new CommandExecutor().execute(new FindAllMechanicsTS());
	}

	/**
	 * M�todo findMechanicsInForce 
	 * Busca mec�nicos con contrato en vigor
	 */
	@Override
	public List<MechanicBLDto> findMechanicsInForce() throws BusinessException {
		return new CommandExecutor().execute(new FindMechanicsWithContractInForceTS());
	}

	/**
	 * M�todo findMechanicsWithContractInForceInContractType 
	 * Busca mec�nicos con contrato en vigor y por nombre del tipo de contrato
	 */
	@Override
	public List<MechanicBLDto> findMechanicsWithContractInForceInContractType(String name) throws BusinessException {
		return new CommandExecutor().execute(new FindMechanicsWithContractInForceInContractTypeTS(name));
	}

	/**
	 * M�todo findMechanicsInProfessionalGroups 
	 * Busca mec�nicos en grupos de profesionales
	 */
	@Override
	public List<MechanicBLDto> findMechanicsInProfessionalGroups(String name) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}
}
