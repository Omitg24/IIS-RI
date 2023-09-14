package uo.ri.cws.ext.amp.associations;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import uo.ri.cws.domain.Associations;
import uo.ri.cws.domain.Contract;
import uo.ri.cws.domain.ContractType;
import uo.ri.cws.domain.Mechanic;
import uo.ri.cws.domain.ProfessionalGroup;

public class AssociationsTests {
		
	private Contract contract;
	private Mechanic mechanic;
	private ContractType contractType;
	private ProfessionalGroup professionalGroup;
	
	@Before
	public void setUp() {
		mechanic = new Mechanic("no-dni");
		contractType = new ContractType("no-name", 23);
		professionalGroup = new ProfessionalGroup("no-name");
		contract = new Contract(mechanic, contractType, professionalGroup, 20);
	}
	
	@Test
	public void testHireLink() {
		assertTrue(contract.getMechanic().isPresent());
		assertTrue(contract.getMechanic().get().equals(mechanic));
		assertTrue(mechanic.getContractInForce().isPresent());
		assertTrue(mechanic.getContractInForce().get().equals(contract));
		assertTrue(!mechanic.getTerminatedContracts().contains(contract));
	}
	
	@Test
	public void testHireUnlink() {
		Associations.Hire.unlink(contract, mechanic);
		assertTrue(mechanic.getContractInForce().isEmpty());
		assertFalse(contract.getMechanic().isEmpty());
	}
	
	@Test
	public void testHireAndFire() {
		Associations.Hire.unlink(contract, mechanic);
		Associations.Fire.link(contract);
		assertFalse(contract.getMechanic().isEmpty());
		assertTrue(contract.getFiredMechanic().isPresent());
		assertTrue(contract.getFiredMechanic().get().equals(mechanic));
		assertTrue(mechanic.getTerminatedContracts().contains(contract));		
	}
	
	@Test
	public void testGroupAndType() {
		assertNotNull(contract.getProfessionalGroup());
		assertTrue(contract.getProfessionalGroup().equals(professionalGroup));
		assertFalse(professionalGroup.getContracts().isEmpty());
		assertTrue(professionalGroup.getContracts().contains(contract));
		assertNotNull(contract.getContractType());
		assertTrue(contract.getContractType().equals(contractType));
		assertFalse(contractType.getContracts().isEmpty());
		assertTrue(contractType.getContracts().contains(contract));
		Associations.Group.unlink(contract, professionalGroup);
		Associations.Type.unlink(contract, contractType);
		assertNull(contract.getProfessionalGroup());
		assertTrue(professionalGroup.getContracts().isEmpty());
		assertFalse(professionalGroup.getContracts().contains(contract));
		assertNull(contract.getContractType());
		assertTrue(contractType.getContracts().isEmpty());
		assertFalse(contractType.getContracts().contains(contract));
	}
}
