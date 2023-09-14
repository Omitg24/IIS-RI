package uo.ri.cws.application.business.payroll.crud;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.payroll.PayrollService;
import uo.ri.cws.application.business.payroll.crud.commands.DeletePayrollForMechanicTS;
import uo.ri.cws.application.business.payroll.crud.commands.GeneratePayrollsTS;
import uo.ri.cws.application.business.payroll.crud.commands.DeleteLastPayrollTS;
import uo.ri.cws.application.business.payroll.crud.commands.ListAllPayrollsSummaryTS;
import uo.ri.cws.application.business.payroll.crud.commands.ListPayrollByMechanicTS;
import uo.ri.cws.application.business.payroll.crud.commands.ListPayrollDetailTS;
import uo.ri.cws.application.business.payroll.crud.commands.ListPayrollsByProfessionalGroupTS;
import uo.ri.cws.application.business.util.CommandExecutor;

/**
 * Titulo: Clase PayrollServiceImpl
 *
 * @author Omar Teixeira González, UO281847
 * @version 27 oct 2022
 */
public class PayrollServiceImpl implements PayrollService {
	/**
	 * Método generatePayrolls
	 */
	@Override
	public void generatePayrolls() throws BusinessException {
		generatePayrolls(LocalDate.now());
	}

	/**
	 * Método generatePayrolls
	 * @param present
	 */
	@Override
	public void generatePayrolls(LocalDate present) throws BusinessException {
		new CommandExecutor().execute(new GeneratePayrollsTS(present));
	}

	/**
	 * Método deleteLastPayrollFor
	 * @param id
	 */
	@Override
	public void deleteLastPayrollFor(String id) throws BusinessException {
		new CommandExecutor().execute(new DeletePayrollForMechanicTS(id));
	}

	/**
	 * Método deleteLastPayrolls
	 */
	@Override
	public void deleteLastPayrolls() throws BusinessException {
		new CommandExecutor().execute(new DeleteLastPayrollTS());
	}

	/**
	 * Método getPayrollDetails
	 * @param id
	 * @return PayrollBLDto
	 */
	@Override
	public Optional<PayrollBLDto> getPayrollDetails(String id) throws BusinessException {
		return new CommandExecutor().execute(new ListPayrollDetailTS(id));
	}

	/**
	 * Método getAllPayrolls
	 * @return list
	 */
	@Override
	public List<PayrollSummaryBLDto> getAllPayrolls() throws BusinessException {
		return new CommandExecutor().execute(new ListAllPayrollsSummaryTS());
	}

	/**
	 * Método getAllPayrolls
	 * @param id
	 * @return list
	 */
	@Override
	public List<PayrollSummaryBLDto> getAllPayrollsForMechanic(String id) throws BusinessException {
		return new CommandExecutor().execute(new ListPayrollByMechanicTS(id));
	}

	/**
	 * Método getAllPayrollsForProfessionalGroup
	 * @param name
	 * @return list
	 */
	@Override
	public List<PayrollSummaryBLDto> getAllPayrollsForProfessionalGroup(String name) throws BusinessException {
		return new CommandExecutor().execute(new ListPayrollsByProfessionalGroupTS(name));
	}

}
