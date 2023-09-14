package uo.ri.cws.application.service.contract;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.contract.ContractService;
import uo.ri.cws.application.business.contract.ContractService.ContractBLDto;
import uo.ri.cws.application.business.contract.ContractService.ContractState;
import uo.ri.cws.application.business.contract.ContractService.ContractSummaryBLDto;
import uo.ri.cws.application.business.contracttype.ContractTypeService.ContractTypeBLDto;
import uo.ri.cws.application.business.mechanic.MechanicService.MechanicBLDto;
import uo.ri.cws.application.business.professionalgroup.ProfessionalGroupService.ProfessionalGroupBLDto;
import uo.ri.cws.application.service.common.TestContext;
import uo.ri.cws.application.service.common.TestContext.Key;
import uo.ri.cws.application.service.util.ContractUtil;

public class FindsSteps {

	private ContractService service = BusinessFactory.forContractService();
	private TestContext ctx;
	private ContractBLDto theContract = null;
	private Optional<ContractBLDto> optionalContractFound = Optional.empty();
	private List<ContractSummaryBLDto> contractsSummaryFound = null;
	private ContractBLDto inForce = null;


	public FindsSteps(TestContext ctx) {
		this.ctx = ctx;
	}

	@When("I try to find a contract with null id")
	public void i_try_to_find_a_contract_with_null_arg() {
		tryFindByIdAndKeepException(null);
	}

	

	@When("I try to find a contract for a mechanic with null id")
	public void i_try_to_find_a_contract_for_a_mechanic_with_null_arg() {
		tryFindByMechanicIdAndKeepException(null);

	}

	@When("I search a non existing contract id")
	public void i_search_a_non_existing_contract() throws BusinessException {
		this.optionalContractFound = service
				.findContractById(UUID.randomUUID().toString());

	}

	@Then("Contract is not found")
	public void contract_is_not_found() {
		assertTrue(this.optionalContractFound.isEmpty());
	}

	@When("I search the contract in force")
	public void i_search_the_contract_inforce() throws BusinessException {
		this.theContract = (ContractBLDto) ctx.get(Key.INFORCE);
		this.optionalContractFound = service
				.findContractById(theContract.id);
	}

	@When("I search the contract terminated")
	public void i_search_the_contract_terminated() throws BusinessException {
		this.theContract = (ContractBLDto) ctx.get(Key.TERMINATED);
		this.optionalContractFound = service
				.findContractById(theContract.id);
	}
	
	@Then("Contract is found")
	public void contract_is_found() {
		assertTrue(this.optionalContractFound.isPresent());
		ContractUtil.match(this.optionalContractFound.get(), theContract);
	}

	@When("I search contracts for a non existent mechanic")
	public void i_search_contracts_for_a_non_existent_mechanic() throws BusinessException {
		contractsSummaryFound = service.findContractsByMechanic(UUID.randomUUID().toString());
	}

	@Then("List of contracts summary is empty")
	public void list_of_contracts_is_empty() {
		assertTrue(contractsSummaryFound.isEmpty());
	}

	@Given("Several contracts for the mechanic")
	public void several_contracts_for_the_mechanic() throws BusinessException {
		
		MechanicBLDto m = (MechanicBLDto) ctx.get(Key.MECHANIC);
		ContractTypeBLDto ct = (ContractTypeBLDto) ctx.get(Key.CONTRACTTYPE);
		ProfessionalGroupBLDto pg = (ProfessionalGroupBLDto) ctx.get(Key.PROFESSIONALGROUP);
		
		List<ContractBLDto> l = new ArrayList<ContractBLDto> ();
		l.add(add_a_contract_inForce_for_mechanic(m, ct, pg));
		l.add(add_a_contract_terminated_for_mechanic(m, ct, pg, LocalDate.now().minusYears(1L), LocalDate.now().minusMonths(6)));
		l.add(add_a_contract_terminated_for_mechanic(m, ct, pg, LocalDate.now().minusYears(2L), LocalDate.now().minusMonths(18)));
		ctx.put(Key.CONTRACTS, l);

	}
	
	@Given("Several contracts terminated for the mechanic")
	public void several_contracts_terminated_for_the_mechanic() throws BusinessException {
		
		MechanicBLDto m = (MechanicBLDto) ctx.get(Key.MECHANIC);
		ContractTypeBLDto ct = (ContractTypeBLDto) ctx.get(Key.CONTRACTTYPE);
		ProfessionalGroupBLDto pg = (ProfessionalGroupBLDto) ctx.get(Key.PROFESSIONALGROUP);
		
		List<ContractBLDto> l = new ArrayList<ContractBLDto> ();
		l.add(add_a_contract_terminated_for_mechanic(m, ct, pg, LocalDate.now().minusYears(1L), LocalDate.now().minusMonths(6)));
		l.add(add_a_contract_terminated_for_mechanic(m, ct, pg, LocalDate.now().minusYears(2L), LocalDate.now().minusMonths(18)));
		ctx.put(Key.CONTRACTS, l);
	}
	
	private ContractBLDto add_a_contract_terminated_for_mechanic(MechanicBLDto m,
			ContractTypeBLDto ct,
			ProfessionalGroupBLDto pg, 
			LocalDate startDate, LocalDate endDate) throws BusinessException {
		ContractBLDto c = new ContractUtil().unique().forMechanic(m).withType(ct).withGroup(pg).withState("TERMINATED")
				.withStartDate(startDate).withEndDate(endDate).register().get();
		return c;
	}
	
	private ContractBLDto add_a_contract_inForce_for_mechanic(MechanicBLDto m,
			ContractTypeBLDto ct,
			ProfessionalGroupBLDto pg) throws BusinessException {
		LocalDate startDate = LocalDate.now().minusYears(1);
		LocalDate endDate = startDate.plusMonths(6);
		ContractBLDto c = new ContractUtil().unique().forMechanic(m).withType(ct).withGroup(pg).withState("IN_FORCE")
				.withStartDate(startDate).withEndDate(endDate).register().get();
		return c;
	}

	@Then("All contracts summary are found")
	public void all_contracts_summary_are_found() throws BusinessException {
		
		@SuppressWarnings("unchecked")
		List<ContractBLDto> theContracts = (List<ContractBLDto>) ctx.get(Key.CONTRACTS);
		List<ContractSummaryBLDto> l1 = (List<ContractSummaryBLDto>) contractsSummaryFound.stream()
				.sorted((c1, c2) -> { return c1.id.compareTo(c2.id); })
				.collect(Collectors.toList());
		List<ContractBLDto> l2 = (List<ContractBLDto>) theContracts.stream()
				.sorted((c1, c2) -> { return c1.id.compareTo(c2.id); })
				.collect(Collectors.toList());
		
		assertTrue(this.contractsSummaryFound.size() == theContracts.size());
		for (int index = 0; index < l1.size(); index++) {
			assertTrue(ContractUtil.match(l1.get(index), l2.get(index)));
		}
	}
	
	@Then("There is no contract in force")
	public void there_is_no_contract_in_force() {
		List<ContractSummaryBLDto> l1 = (List<ContractSummaryBLDto>) contractsSummaryFound.stream()
				.sorted((c1, c2) -> { return c1.id.compareTo(c2.id); })
				.collect(Collectors.toList());
		assertTrue(l1.stream().filter(c -> c.state.equals(ContractState.IN_FORCE)).collect(Collectors.toList()).isEmpty());
	}

	@Then("Contract in force summary is found with no payrolls")
	public void contract_in_force_summary_is_found_with_no_payrolls() {
		List<ContractSummaryBLDto> l1 = (List<ContractSummaryBLDto>) contractsSummaryFound.stream()
				.filter( contract -> contract.state.equals(ContractState.IN_FORCE))
				.collect(Collectors.toList());
		assertTrue(l1.size() == 1);
		ContractSummaryBLDto cSummary = l1.get(0);
		assertTrue(ContractUtil.match(cSummary, inForce));
		assertTrue(cSummary.numPayrolls == 0);

	}
	
	
	@When("I search contracts for the mechanic")
	public void i_search_contracts_for_the_mechanic() throws BusinessException {
		String mdni = ((MechanicBLDto)ctx.get(Key.MECHANIC)).dni;
		this.contractsSummaryFound = service.findContractsByMechanic(mdni);
	}

	
	private void tryFindByIdAndKeepException(String arg) {
		try {
			service.findContractById(arg);
			fail();
		} catch (BusinessException ex) {
			ctx.setException(ex);
		} catch (IllegalArgumentException ex) {
			ctx.setException(ex);
		}
	}

	private void tryFindByMechanicIdAndKeepException(String arg) {
		try {
			service.findContractsByMechanic(arg);
			fail();
		} catch (BusinessException ex) {
			ctx.setException(ex);
		} catch (IllegalArgumentException ex) {
			ctx.setException(ex);
		}
	}

}
