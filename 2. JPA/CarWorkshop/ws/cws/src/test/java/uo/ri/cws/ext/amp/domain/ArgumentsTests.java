package uo.ri.cws.ext.amp.domain;

import java.time.LocalDate;

import org.junit.Test;

import uo.ri.cws.domain.Contract;
import uo.ri.cws.domain.ContractType;
import uo.ri.cws.domain.Mechanic;
import uo.ri.cws.domain.Payroll;
import uo.ri.cws.domain.ProfessionalGroup;

public class ArgumentsTests {
	
	@Test(expected = IllegalArgumentException.class)
	public void testWrongContract() {
		new Payroll(new Contract(null, null, null, -1));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testWrongMechanic() {
		new Payroll(new Contract(new Mechanic(null), null, null, 0), LocalDate.now());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testPayrollWrongValues() {
		new Payroll(new Contract(new Mechanic("no-dni"), new ContractType("no-name", 1), 
				new ProfessionalGroup("no-name"), 4), LocalDate.now(), -2, -2, -2, 
				-2, -2, -2);
	}
	
	@Test
	public void testPassArguments() {
		new Payroll(new Contract(new Mechanic("no-dni"), new ContractType("no-name", 1), 
				new ProfessionalGroup("no-name"), 4), LocalDate.now());
	}
	
	@Test
	public void testAllPassValues() {
		new Payroll(new Contract(new Mechanic("no-dni", "no-name", "no-surname"), 
				new ContractType("no-name", 1), 
				new ProfessionalGroup("no-name", 5, 5), 4), LocalDate.now(), 18, 24, 14, 
				39, 5, 20);
	}
}
