package uo.ri.cws.ext.amp.persistence;

import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.BusinessFactory;
import uo.ri.cws.application.service.payroll.PayrollService.PayrollBLDto;
import uo.ri.cws.application.service.payroll.PayrollService.PayrollSummaryBLDto;
import uo.ri.cws.infrastructure.persistence.jpa.executor.JpaExecutorFactory;
import uo.ri.cws.infrastructure.persistence.jpa.repository.JpaRepositoryFactory;

public class PayrollsTests {
	
	@Before
	public void setUp() throws Exception {
		Factory.service = new BusinessFactory();
		Factory.repository = new JpaRepositoryFactory();
		Factory.executor = new JpaExecutorFactory();
	}	
	
	@Test
	public void checkAllPayrollsHaveContract() throws BusinessException {
		List<PayrollSummaryBLDto> payrolls = Factory.service.forPayrollService().getAllPayrolls();
		for (PayrollSummaryBLDto ps : payrolls) {
			Optional<PayrollBLDto> op = Factory.service.forPayrollService().getPayrollDetails(ps.id);
			if (op.isPresent()) {
				PayrollBLDto p = op.get();
				assertNotNull(p.contractId);
			}
		}
	}
	
	
	@Test(expected = BusinessException.class)
	public void testFindById() throws BusinessException  {
		Factory.service.forPayrollService().getAllPayrollsForMechanic("no-mechanic");
	}
	
	
	@Test (expected = BusinessException.class)
	public void testBorrarNominaMecanicoNoExiste() throws BusinessException {
		Factory.service.forPayrollService().deleteLastPayrollFor("no-mechanic");
	}
}
