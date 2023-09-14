package uo.ri.cws.application.service.mechanic;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.mechanic.MechanicService;
import uo.ri.cws.application.business.mechanic.MechanicService.MechanicBLDto;
import uo.ri.cws.application.service.common.TestContext;
import uo.ri.cws.application.service.common.TestContext.Key;

public class FindMechanicsInProfessionalGroup {

	private MechanicService service = BusinessFactory.forMechanicService();
	private TestContext ctx;
	private List<MechanicBLDto> mechanics = new ArrayList<>();

	public FindMechanicsInProfessionalGroup(TestContext ctx) {
		this.ctx = ctx;
	}


	@When("I try to find mechanics for a null professional group id")
	public void i_try_to_find_mechanics_with_null_argument() {
		tryFindMechanicsByIdAndKeepException(null);

	}

	@When("I try to find mechanics for a wrong professional group id {string}")
	public void i_try_to_find_mechanics_with_wrong_argument(String id) {
		tryFindMechanicsByIdAndKeepException(id);

	}
	
	@When("I search mechanics in a non existing professional group")
	public void i_search_mechanis_in_a_non_existent_id() throws BusinessException {
		mechanics  = service.findMechanicsInProfessionalGroups(UUID.randomUUID().toString());
	}

	
	@When("I search mechanics in professional group I")
	public void i_search_mechanis_professional_group_i() throws BusinessException {
		mechanics  = service.findMechanicsInProfessionalGroups("I");
		ctx.put(Key.MECHANICS, mechanics);
	}
	
	
	@Then("zero mechanics are found")
	public void no_mechanic_found() {
		assertTrue(mechanics.isEmpty());
	}
	
	private void tryFindMechanicsByIdAndKeepException(String id) {
		try {
			service.findMechanicsInProfessionalGroups(id);
			fail();
		} catch (BusinessException ex) {
			ctx.setException(ex);
		} catch (IllegalArgumentException ex) {
			ctx.setException(ex);
		}
	}

}
