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
 * @author Omar Teixeira González, UO281847
 * @version 13 oct 2022
 */
public class MechanicServiceImpl implements MechanicService {
	/**
	 * Método addMechanic 
	 * Añade un mecánico
	 */
	@Override
	public MechanicBLDto addMechanic(MechanicBLDto mechanic) throws BusinessException {
		return new CommandExecutor().execute(new AddMechanicTS(mechanic));
	}

	/**
	 * Método deleteMechanic 
	 * Borra un mecánico
	 */
	@Override
	public void deleteMechanic(String idMechanic) throws BusinessException {
		new CommandExecutor().execute(new DeleteMechanicTS(idMechanic));
	}

	/**
	 * Método updateMechanic 
	 * Actualiza un mecánico
	 */
	@Override
	public void updateMechanic(MechanicBLDto mechanic) throws BusinessException {
		new CommandExecutor().execute(new UpdateMechanicTS(mechanic));
	}

	/**
	 * Método findMechanicById 
	 * Busca un mecánico por Id
	 */
	@Override
	public Optional<MechanicBLDto> findMechanicById(String idMechanic) throws BusinessException {
		return new CommandExecutor().execute(new FindMechanicByIdTS(idMechanic));
	}

	/**
	 * Método findMechanicByDni 
	 * Busca un mecánico por Dni
	 */
	@Override
	public Optional<MechanicBLDto> findMechanicByDni(String dniMechanic) throws BusinessException {
		return new CommandExecutor().execute(new FindMechanicByDniTS(dniMechanic));
	}

	/**
	 * Método findAllMechanics 
	 * Busca todos los mecánicos
	 */
	@Override
	public List<MechanicBLDto> findAllMechanics() throws BusinessException {
		return new CommandExecutor().execute(new FindAllMechanicsTS());
	}

	/**
	 * Método findMechanicsInForce 
	 * Busca mecánicos con contrato en vigor
	 */
	@Override
	public List<MechanicBLDto> findMechanicsInForce() throws BusinessException {
		return new CommandExecutor().execute(new FindMechanicsWithContractInForceTS());
	}

	/**
	 * Método findMechanicsWithContractInForceInContractType 
	 * Busca mecánicos con contrato en vigor y por nombre del tipo de contrato
	 */
	@Override
	public List<MechanicBLDto> findMechanicsWithContractInForceInContractType(String name) throws BusinessException {
		return new CommandExecutor().execute(new FindMechanicsWithContractInForceInContractTypeTS(name));
	}

	/**
	 * Método findMechanicsInProfessionalGroups 
	 * Busca mecánicos en grupos de profesionales
	 */
	@Override
	public List<MechanicBLDto> findMechanicsInProfessionalGroups(String name) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}
}
