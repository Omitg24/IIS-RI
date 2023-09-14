package uo.ri.cws.application.service.contract;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.UUID;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.contract.ContractService;
import uo.ri.cws.application.business.contract.ContractService.ContractBLDto;
import uo.ri.cws.application.service.common.TestContext;
import uo.ri.cws.application.service.common.TestContext.Key;

public class DeleteSteps {
	private String idToDel;
	private ContractService service = BusinessFactory.forContractService();
	
	private TestContext ctx;

	public DeleteSteps(TestContext ctx) {
		this.ctx = ctx;
		ContractBLDto theContract = ((ContractBLDto) ctx.get(Key.INFORCE) != null) ?
				( (ContractBLDto) ctx.get(Key.INFORCE) ):
				( (ContractBLDto) ctx.get(Key.TERMINATED) ); 
		idToDel = (theContract != null)? theContract.id : null;
	}

	@When("I try to delete a null contract id")
	public void i_try_to_delete_a_null_argument() {
		tryDeleteAndKeepException(null);
	}
	
	@When("I try to delete a non existing contract")
	public void i_try_to_delete_a_non_existing_contract() {
		tryDeleteAndKeepException(UUID.randomUUID().toString());

	}

	@When("I try to delete the contract")
	public void i_try_to_delete_the_contract() {
		tryDeleteAndKeepException(this.idToDel);
	}
		
	@When("I delete the contract")
	public void i_delete_the_contract() throws BusinessException {
		service.deleteContract(this.idToDel);
	}
	
	@Then("This contract does not exist any more")
	public void this_contract_does_not_exist_any_more() throws BusinessException {
		assertTrue(service.findContractById(idToDel).isEmpty());
	}
	

	private void tryDeleteAndKeepException(String id) {
		try {
			service.deleteContract(id);
			fail();
		} catch (BusinessException ex) {
			ctx.setException(ex);
		} catch (IllegalArgumentException ex) {
			ctx.setException(ex);
		}
	}

}
