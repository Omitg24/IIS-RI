package uo.ri.cws.application.service.payroll.crud;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.payroll.PayrollService;
import uo.ri.cws.application.service.payroll.crud.commands.DeleteLastPayrollForMechanic;
import uo.ri.cws.application.service.payroll.crud.commands.DeleteLastPayrolls;
import uo.ri.cws.application.service.payroll.crud.commands.FindAllPayrolls;
import uo.ri.cws.application.service.payroll.crud.commands.FindAllPayrollsByMechanic;
import uo.ri.cws.application.service.payroll.crud.commands.FindAllPayrollsByProfessionalGroup;
import uo.ri.cws.application.service.payroll.crud.commands.FindPayrollDetails;
import uo.ri.cws.application.service.payroll.crud.commands.GeneratePayrolls;
import uo.ri.cws.application.util.command.CommandExecutor;

/**
 * Título: Clase MechanicCrudServiceImpl
 * Descripción: Implementación de la interfaz de servicio de las nóminas 
 *
 * @author Omar Teixeira González, UO281847
 * @version 16 nov 2022
 */
public class PayrollServiceImpl implements PayrollService {
	/**
	 * Atributo executor
	 */
	private CommandExecutor executor = Factory.executor.forExecutor();
	
    /**
     * Método generatePayrolls 
     * It generates all payrolls in current month only.
     * Notice next method and be careful don't repeat code, when possible.
     * 
     * @throws {@link BusinessException}
     */
	@Override
	public void generatePayrolls() throws BusinessException {
		generatePayrolls(LocalDate.now());
	}

    /**
     * Método generatePayrolls 
     * It generates all payrolls in the date passed as argument
     * @param arg Payroll will be generated considering this as present day
     *
     * @throws {@link BusinessException}
     */
	@Override
	public void generatePayrolls(LocalDate present) throws BusinessException {
		executor.execute(new GeneratePayrolls(present));
	}

    /**
     * Método deleteLastPayrollFor 
     * It deletes the payroll generated last month for the mechanic passed as argument
     *
     * @param mechanic identifier
     * @throws {@link BusinessException}
     */
	@Override
	public void deleteLastPayrollFor(String mechanicId) throws BusinessException {
		executor.execute(new DeleteLastPayrollForMechanic(mechanicId));
	}

    /**
     * Método deleteLastPayrolls
     * It deletes all payrolls generated this month.
     * 
     * @throws {@link BusinessException}
     */
	@Override
	public void deleteLastPayrolls() throws BusinessException {
		executor.execute(new DeleteLastPayrolls());
	}

    /**
     * Método getPayrollDetails
     * It returns one payroll details.
     *
     * @param id payroll id
     * @return payroll dto 
     * @throws IllegalArgumentException when argument is null or empty
     */
	@Override
	public Optional<PayrollBLDto> getPayrollDetails(String id) throws BusinessException {
		return executor.execute(new FindPayrollDetails(id));
	}

    /**
     * Método getAllPayrolls
     * Returns all payrolls (a summary).
     *
     * @return List of all payrolls summary, or empty.
     * @throws {@link BusinessException}
     */
	@Override
	public List<PayrollSummaryBLDto> getAllPayrolls() throws BusinessException {
		return executor.execute(new FindAllPayrolls());
	}

    /**
     * Método getAllPayrollsForMechanic
     * Returns all payrolls (a summary) for a certain mechanic.
     *
     * @param id mechanic id
     * @return List of payrolls summary, or empty.
     * @throws IllegalArgument Exception when argument is null or empty
     * @throws BusinessException when mechanic does not exist
     */
	@Override
	public List<PayrollSummaryBLDto> getAllPayrollsForMechanic(String id) throws BusinessException {
		return executor.execute(new FindAllPayrollsByMechanic(id));
	}

    /**
     * Método getAllPayrollsForProfessionalGroup
     * It returns all payrolls (a summary) for a give professional group
     * 
     * @param name the name of the professional group
     * @throws BussinessException DOES NOT
     * @throws IllegalArgumentException when argument is null or empty.
     */
	@Override
	public List<PayrollSummaryBLDto> getAllPayrollsForProfessionalGroup(String name) throws BusinessException {
		return executor.execute(new FindAllPayrollsByProfessionalGroup(name));
	}

}
