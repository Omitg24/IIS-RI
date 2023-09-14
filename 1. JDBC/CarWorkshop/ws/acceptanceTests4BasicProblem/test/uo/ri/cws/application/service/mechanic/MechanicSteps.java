package uo.ri.cws.application.service.mechanic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.RandomStringUtils;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import uo.ri.conf.Factory;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.mechanic.MechanicService;
import uo.ri.cws.application.business.mechanic.MechanicService.MechanicBLDto;
import uo.ri.cws.application.service.common.TestContext;
import uo.ri.cws.application.service.util.MechanicUtil;
import uo.ri.cws.application.service.util.WorkOrderUtil;

public class MechanicSteps {

	private TestContext ctx;

	private MechanicService service = Factory.service.forMechanicService();
	private MechanicBLDto mechanic, found;
	private List<MechanicBLDto> mechanics;

	public MechanicSteps(TestContext ctx) {
		this.ctx = ctx;
	}

	@Given("a mechanic")
	public void aMechanic() throws BusinessException {
		mechanic = new MechanicUtil().withDni(randomDni()).withName(randomName()).withSurname(randomSurname())
				.register().get();
	}

	@Given("a mechanic with work orders registered")
	public void aMechanicWithWorkOrdersRegistered() throws BusinessException {
		String dni = randomDni();
		mechanic = new MechanicUtil().withDni(dni).register().get();
		new WorkOrderUtil().forMechanic(mechanic.id).register();
	}

	@Given("the following relation of mechanics")
	public void theFollowingRelationOfMechanics(DataTable dataTable) throws BusinessException {
		List<Map<String, String>> table = dataTable.asMaps();
		for (Map<String, String> row : table) {
			new MechanicUtil().withDni(row.get("dni")).withName(row.get("name")).withSurname(row.get("surname"))
					.register();
		}
	}

	@When("I try to remove a non existent mechanic")
	public void iTryToRemoveAnonExistentMechanic() {
		tryDeleteAndKeepException("does-not-exist-mechanic");
	}

	@When("I try to remove the mechanic")
	public void iTryToRemoveTheMechanic() {
		tryDeleteAndKeepException(mechanic.id);
	}

	@When("I try to remove a mechanic with null argument")
	public void iTryToRemoveAMechanicWithNullArgument() {
		tryDeleteAndKeepException(null);
	}

	@When("I try to delete a mechanic with {string}")
	public void iTryToRemoveAMechanicWith(String id) {
		tryDeleteAndKeepException(id);
	}

	@When("we read all mechanics")
	public void weReadAllMechanics() throws BusinessException {
		mechanics = service.findAllMechanics();
	}

	private String randomDni() {
		return RandomStringUtils.randomAlphabetic(9);
	}

	private String randomSurname() {
		return RandomStringUtils.randomAlphabetic(4) + "-surname";
	}

	private String randomName() {
		return RandomStringUtils.randomAlphabetic(4) + "-name";

	}

	@When("I add a new non existing mechanic")
	public void iAddANewMechanic() throws BusinessException {
		mechanic = new MechanicUtil().withDni(randomDni()).get();
		mechanic.id = service.addMechanic(mechanic).id;
	}

	@When("I remove the mechanic")
	public void iRemoveTheMechanic() throws BusinessException {
		service.deleteMechanic(mechanic.id);
	}

	@When("I try to add a new mechanic with null argument")
	public void iTryToAddANewMechanicWithNullArgument() {

		MechanicBLDto dto = null;

		tryAddAndKeepException(dto);
	}

	@When("I try to add a new mechanic with null dni")
	public void iTryToAddANewMechanicWithNullDni() {

		MechanicBLDto dto = new MechanicUtil().withDni(null).withName(randomName()).withSurname(randomSurname()).get();

		tryAddAndKeepException(dto);
	}

	@When("I try to add a new mechanic with {string}, {string}, {string}")
	public void iTryToAddANewMechanicWith(String dni, String name, String surname) {
		MechanicBLDto dto = new MechanicUtil().withDni(dni).withSurname(surname).withName(name).get();

		tryAddAndKeepException(dto);
	}

	@When("I try to add a new mechanic with same dni")
	public void iTryToAddANewMechanicWithSameDni() throws BusinessException {

		MechanicBLDto dto = new MechanicUtil().withDni(mechanic.dni).get();

		tryAddAndKeepException(dto);
	}

	@When("I try to update a non existing mechanic")
	public void iTryToUpdateNonExistingMechanic() throws BusinessException {
		MechanicBLDto dto = new MechanicUtil().withId("mechanic-does-not-exist").get();

		tryUpdateAndKeepException(dto);
	}

	@When("I try to update a mechanic with null name")
	public void iTryToUpdateAMechanicWithNullName() {
		MechanicBLDto dto = new MechanicUtil().withName(null).get();

		tryUpdateAndKeepException(dto);

	}

	@When("I try to update a mechanic with null surname")
	public void iTryToUpdateAMechanicWithNullSurname() {
		MechanicBLDto dto = new MechanicUtil().withSurname(null).get();

		tryUpdateAndKeepException(dto);

	}

	@When("I try to update a mechanic with null dni")
	public void iTryToUpdateAMechanicWithNullDni() {
		MechanicBLDto dto = new MechanicUtil().withDni(null).get();
		tryUpdateAndKeepException(dto);

	}

	@When("I try to update the mechanic to {string}, {string} and {string}")
	public void iTryToUpdateTheMechanicToDniNameAndSurname(String name, String surname, String dni)
			throws BusinessException {
		MechanicBLDto dto = new MechanicUtil().loadById(mechanic.id).get();

		dto.name = name;
		dto.surname = surname;
		dto.dni = dni;

		tryUpdateAndKeepException(dto);
	}

	@When("I update the mechanic")
	public void iUpdateTheMechanic() throws BusinessException {
		MechanicBLDto dto = new MechanicUtil().loadById(mechanic.id).get();

		dto.name = dto.name += "-updated";
		dto.surname = dto.surname += "-updated";
		dto.dni = dto.dni += "-updated";

		service.updateMechanic(dto);
	}

	@When("I try to find a mechanic with null argument")
	public void iTryToFindAMechanicWithNullArgument() {
		tryFindAndKeepException(null);
	}

	@When("I try to find a mechanic with null dni")
	public void iTryToFindAMechanicWithNullDni() {
		tryFindByDniAndKeepException(null);

	}

	@When("I try to find a non existent mechanic")
	public void iTryToFindAMechanicByTheId() throws BusinessException {
		ctx.setUniqueResult(service.findMechanicById("does-not-exist-id"));
	}

	@When("I try to find a mechanic by a non existent dni")
	public void iTryToFindAMechanicByANonExistentDni() throws BusinessException {
		ctx.setUniqueResult(service.findMechanicByDni("does-not-exist-dni"));

	}

	@When("I look for a mechanic by wrong id {string}")
	public void iLookForAMechanicById(String id) throws BusinessException {
		tryFindAndKeepException(id);
	}

	@When("I look for a mechanic by wrong dni {string}")
	public void iLookForAMechanicByWrongDni(String string) {
		tryFindByDniAndKeepException(string);

	}

	@When("I look for mechanic")
	public void iLookForMechanic() throws BusinessException {
		found = service.findMechanicById(mechanic.id).get();
	}

	@When("I look for mechanic by dni")
	public void iLookForMechanicByDni() throws BusinessException {
		found = service.findMechanicByDni(mechanic.dni).get();

	}

	@Then("the mechanic results updated for fields name and surname")
	public void theMechanicResultsUpdatedForNameAndSurnameOnly() throws BusinessException {
		MechanicBLDto loaded = new MechanicUtil().loadById(mechanic.id).get();

		assertEquals(mechanic.dni, loaded.dni);
		assertEquals(mechanic.name + "-updated", loaded.name);
		assertEquals(mechanic.surname + "-updated", loaded.surname);
	}

	@Then("the mechanic results added to the system")
	public void theMechanicResultsAddedToTheSystem() throws BusinessException {
		MechanicService service = Factory.service.forMechanicService();
		Optional<MechanicBLDto> op = service.findMechanicById(mechanic.id);

		MechanicBLDto added = op.get();

		assertEquals(mechanic.dni, added.dni);
		assertEquals(mechanic.name, added.name);
		assertEquals(mechanic.surname, added.surname);
		assertFalse(added.id.isBlank());
	}

	@Then("mechanic not found")
	public void weGetNoMechanic() throws BusinessException {
		assertTrue(ctx.getUniqueResult().isEmpty());
	}

	@Then("the mechanic no longer exists")
	public void theMechanicNoLongerExists() throws BusinessException {
		MechanicBLDto dto = new MechanicUtil().loadById(mechanic.id).get();
		assertNull(dto);
	}

	@Then("we get the following list of mechanics")
	public void iGetTheFollowingRelationOfMechanics(DataTable data) {
		List<String> expectedMechanics = extractMechanics(data);

		assertEquals(expectedMechanics.size(), mechanics.size());
		for (MechanicBLDto mechanic : mechanics) {
			String mString = new StringBuilder().append(mechanic.dni).append(",").append(mechanic.name).append(",")
					.append(mechanic.surname).toString();
			assertTrue(expectedMechanics.contains(mString));
		}
	}

	@Then("I get mechanic")
	public void iGetTheMechanic() {

		assertEquals(mechanic.dni, found.dni);
		assertEquals(mechanic.name, found.name);
		assertEquals(mechanic.surname, found.surname);
	}

	private List<String> extractMechanics(DataTable data) {
		return data.asMaps().stream().map(r -> r.get("mechanic")).collect(Collectors.toList());
	}

	private void tryAddAndKeepException(MechanicBLDto dto) {
		try {
			service.addMechanic(dto);
			fail();
		} catch (BusinessException ex) {
			ctx.setException(ex);
		} catch (IllegalArgumentException ex) {
			ctx.setException(ex);
		}

	}

	private void tryUpdateAndKeepException(MechanicBLDto dto) {
		try {
			service.updateMechanic(dto);
			fail();
		} catch (BusinessException ex) {
			ctx.setException(ex);
		} catch (IllegalArgumentException ex) {
			ctx.setException(ex);
		}

	}

	private void tryDeleteAndKeepException(String id) {
		try {
			service.deleteMechanic(id);
			fail();
		} catch (BusinessException ex) {
			ctx.setException(ex);
		} catch (IllegalArgumentException ex) {
			ctx.setException(ex);
		}
	}

	private void tryFindAndKeepException(String id) {
		try {
			service.findMechanicById(id);
			fail();
		} catch (BusinessException ex) {
			ctx.setException(ex);
		} catch (IllegalArgumentException ex) {
			ctx.setException(ex);
		}
	}

	private void tryFindByDniAndKeepException(String dni) {
		try {
			service.findMechanicByDni(dni);
			fail();
		} catch (BusinessException ex) {
			ctx.setException(ex);
		} catch (IllegalArgumentException ex) {
			ctx.setException(ex);
		}
	}

}
