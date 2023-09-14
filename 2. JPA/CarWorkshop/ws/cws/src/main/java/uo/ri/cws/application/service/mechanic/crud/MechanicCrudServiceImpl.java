package uo.ri.cws.application.service.mechanic.crud;

import java.util.List;
import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.mechanic.MechanicCrudService;
import uo.ri.cws.application.service.mechanic.crud.command.AddMechanic;
import uo.ri.cws.application.service.mechanic.crud.command.DeleteMechanic;
import uo.ri.cws.application.service.mechanic.crud.command.FindAllMechanics;
import uo.ri.cws.application.service.mechanic.crud.command.FindMechanicById;
import uo.ri.cws.application.service.mechanic.crud.command.FindMechanicsInForce;
import uo.ri.cws.application.service.mechanic.crud.command.FindMechanicsWithContractInForceInContractType;
import uo.ri.cws.application.service.mechanic.crud.command.UpdateMechanic;
import uo.ri.cws.application.util.command.CommandExecutor;

/**
 * Título: Clase MechanicCrudServiceImpl
 * Descripción: Implementación de la interfaz de servicio de los mecánicos 
 *
 * @author Omar Teixeira González, UO281847
 * @version 16 nov 2022
 */
public class MechanicCrudServiceImpl implements MechanicCrudService {
	/**
	 * Atributo executor
	 */
	private CommandExecutor executor = Factory.executor.forExecutor();
	
	/**
     * Add a new mechanic to the system with the data specified in the dto.
     * 		The id value will be ignored
     * @param mecanico dto
     * @return the dto with the id filed updated to the UUID generated
     * @throws BusinessException if there already exist another
     * 		mechanic with the same dni
     */
	@Override
	public MechanicDto addMechanic(MechanicDto dto) throws BusinessException {
		return executor.execute(new AddMechanic(dto));
	}

    /**
     * Updates values for the mechanic specified by the id field,
     * 		just name and surname will be updated
     * @param mechanic dto, the id field, name and surname cannot be null
     * @throws BusinessException if the mechanic does not exist
     */
	@Override
	public void updateMechanic(MechanicDto dto) throws BusinessException {
		executor.execute(new UpdateMechanic(dto));
	}

    /**
     * @param idMecanico
     * @throws BusinessException if the mechanic does not exist
     */
	@Override
	public void deleteMechanic(String iddto) throws BusinessException {
		executor.execute(new DeleteMechanic(iddto));
	}

    /**
     * @return the list of all mechanics registered in the system
     * 	without regarding their contract status or if they have
     * 	contract or not. It might be an empty list if there is no mechanic
     *
     * DOES NOT @throws BusinessException
     */
	@Override
	public List<MechanicDto> findAllMechanics() throws BusinessException {
		return executor.execute(new FindAllMechanics());
	}

	/**
     * @param id of the mechanic
     * @return the dto for the mechanic or null if there is none with the id
     * DOES NOT @throws BusinessException
     */
	@Override
	public Optional<MechanicDto> findMechanicById(String id) throws BusinessException {
		return executor.execute(new FindMechanicById(id));
	}

    /**
     *
     * 						ONLY FOR EXTENSION
     *
     * @return the list of all mechanics registered in the system
     * with a contract in force. It might be an empty list if there is no mechanic
     *
     * DOES NOT @throws BusinessException
     */
	@Override
	public List<MechanicDto> findMechanicsInForce() throws BusinessException {
		return executor.execute(new FindMechanicsInForce());
	}

	/**
     * @return the list of mechanics with contracts in force in a contract type, or
     * 		an empty list if there is none
     * @throws IllegalArgumentException if
     * 		- id is null or empty
     * @throws BusinessException DOES NOT
     */
	@Override
	public List<MechanicDto> findMechanicsWithContractInForceInContractType(String name) throws BusinessException {
		return executor.execute(new FindMechanicsWithContractInForceInContractType(name));
	}

    /**
     * @return the list of mechanis in a professional group, or
     * 		an empty list if there is none
     * @throws BusinessException DOES NOT
     */
	@Override
	public List<MechanicDto> findMechanicsInProfessionalGroups(String name) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}