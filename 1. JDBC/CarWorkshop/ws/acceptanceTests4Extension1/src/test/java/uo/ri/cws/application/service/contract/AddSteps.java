package uo.ri.cws.application.service.contract;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.contract.ContractService;
import uo.ri.cws.application.business.contract.ContractService.ContractBLDto;
import uo.ri.cws.application.business.contract.ContractService.ContractState;
import uo.ri.cws.application.business.contracttype.ContractTypeService.ContractTypeBLDto;
import uo.ri.cws.application.business.mechanic.MechanicService.MechanicBLDto;
import uo.ri.cws.application.business.payroll.PayrollService.PayrollBLDto;
import uo.ri.cws.application.business.professionalgroup.ProfessionalGroupService.ProfessionalGroupBLDto;
import uo.ri.cws.application.service.common.LocalDateConverter;
import uo.ri.cws.application.service.common.NIFUtil;
import uo.ri.cws.application.service.common.TestContext;
import uo.ri.cws.application.service.common.TestContext.Key;
import uo.ri.cws.application.service.util.ContractTypeUtil;
import uo.ri.cws.application.service.util.ContractUtil;
import uo.ri.cws.application.service.util.MechanicUtil;
import uo.ri.cws.application.service.util.PayrollUtil;
import uo.ri.cws.application.service.util.ProfessionalGroupUtil;
import uo.ri.cws.application.service.util.sql.FindContractByIdSqlUnitOfWork;
import uo.ri.cws.application.service.util.sql.FindContractTypeByIdSqlUnitOfWork;
import uo.ri.cws.application.service.util.sql.FindPayrollsByContractLastYearSqlUnitOfWork;

public class AddSteps {

	private static final long DAYSINAYEAR = 365;
	private ContractService service = BusinessFactory.forContractService();
	private TestContext ctx;
	private ContractUtil cUtil = new ContractUtil();
	private PayrollUtil pUtil = new PayrollUtil();

	private ContractBLDto inForce = null, terminated = null;

	public AddSteps(TestContext ctx) {
		this.ctx = ctx;
		inForce = (ContractBLDto) ctx.get(Key.INFORCE);
		terminated = (ContractBLDto) ctx.get(Key.TERMINATED);

	}

	@When("I hire the mechanic with this data")
	// start date = first day next month
	// end date = null
	public void i_register_this_contract_for_this_mechanic(DataTable dataTable) throws BusinessException {
		Map<String, String> row = dataTable.asMaps().get(0);

		MechanicBLDto m = (MechanicBLDto) ctx.get(Key.MECHANIC);

		ContractBLDto previous = new ContractUtil().findContractInForceForMechanic(m.id).get();
		if (previous != null) { // there were a prvious contract in force
			FindContractByIdSqlUnitOfWork x = new FindContractByIdSqlUnitOfWork(previous.id);
			x.execute();
			terminated = x.get().get();
			ctx.put(Key.TERMINATED, terminated);
		}

		String type = row.get("type");
		String group = row.get("group");
		String p = row.get("pay");

		ContractTypeBLDto ct = new ContractTypeUtil().findContractType(type).get();
		ProfessionalGroupBLDto pg = new ProfessionalGroupUtil().findProfessionalGroup(group).get();
		double pay = (p == null || p.isBlank()) ? 1000.0 : Double.parseDouble(p);

		ContractBLDto contractDto = new ContractUtil().unique().forMechanic(m).withType(ct).withGroup(pg)
				.withAnnualWage(pay).get();

		contractDto = service.add(contractDto);
		ctx.put(Key.INFORCE, contractDto);

	}

	@Given("a contract terminated")
	public void a_contract_terminated_for_the_mechanic() throws BusinessException {
		MechanicBLDto m = (MechanicBLDto) ctx.get(Key.MECHANIC);
		ContractTypeBLDto ct = (ContractTypeBLDto) ctx.get(Key.CONTRACTTYPE);
		ProfessionalGroupBLDto pg = (ProfessionalGroupBLDto) ctx.get(Key.PROFESSIONALGROUP);

		add_a_contract_terminated_for_mechanic(m, ct, pg);
	}

	private void add_a_contract_terminated_for_mechanic(MechanicBLDto m, ContractTypeBLDto ct, ProfessionalGroupBLDto pg)
			throws BusinessException {
		LocalDate startDate = LocalDate.now().minusYears(1);
		LocalDate endDate = startDate.plusMonths(6);
		terminated = new ContractUtil().unique().forMechanic(m).withType(ct).withGroup(pg).withState("TERMINATED")
				.withStartDate(startDate).withEndDate(endDate).register().get();
		ctx.put(Key.TERMINATED, terminated);
	}

	@Then("contract version is {int}")
	public void contract_version_is(Integer n) {
		assertTrue(inForce.version == n);

	}


	@Then("previous contract is terminated with this settlement")
	public void previous_contract_is_terminated(DataTable t) throws BusinessException {
		terminated = (ContractBLDto) ctx.get(Key.TERMINATED);
		terminated = cUtil.findContractById(terminated.id).get();
		double sett = Double.parseDouble(t.asMaps().get(0).get("settlement"));

		assertTrue(terminated.state.equals(ContractState.TERMINATED));
		assertTrue(terminated.endDate.equals(LocalDate.now().with(TemporalAdjusters.lastDayOfMonth())));
		assertTrue(terminated.settlement == sett);
	}

	@Then("there is a contract in force for the mechanic with the following data")
	public void there_is_a_contract_in_force_for_the_mechanic(DataTable dataTable) throws BusinessException {
		MechanicBLDto m = (MechanicBLDto) ctx.get(Key.MECHANIC);
		inForce = cUtil.findContractInForceForMechanic(m.id).get();
		LocalDate initDate, endDate = null;

		Map<String, String> row = dataTable.asMaps().get(0);
		String d = row.get("startDate");
		if (d != null)
			initDate = LocalDateConverter.convert(d);
		else
			initDate = LocalDate.now().with(TemporalAdjusters.firstDayOfNextMonth());

		if (inForce.contractTypeName.equals("FIXED_TERM")) {
			d = row.get("endDate");
			endDate = LocalDateConverter.convert(d);
		}

		assertNotNull(inForce);
		assertTrue(inForce.dni.equals(row.get("dni")));
		assertTrue(inForce.professionalGroupName.equals(row.get("group")));
		assertTrue(inForce.contractTypeName.equals(row.get("type")));
		assertTrue(inForce.state.equals(ContractState.IN_FORCE));
		assertTrue(inForce.startDate.equals(initDate));
		if (inForce.contractTypeName.equals("FIXED_TERM"))
			assertTrue(inForce.endDate.equals(endDate));
		assertTrue(Math.abs(inForce.settlement - Double.parseDouble(row.get("settlement"))) < Double.MIN_NORMAL);
		assertTrue(Math.abs(inForce.annualBaseWage - Double.parseDouble(row.get("pay"))) < Double.MIN_NORMAL);

	}

	@Then("there is no settlement")
	public void there_is_no_settlement() {
		assertTrue(inForce.settlement == 0.0);
	}

	@Then("start date is first day next month")
	public void start_date_is_first_day_next_month() {
		LocalDate firstDayofNextMonth = LocalDate.now().with(TemporalAdjusters.firstDayOfNextMonth());
		assertTrue(inForce.startDate.equals(firstDayofNextMonth));
	}


	/*
	 * All payrolls are generated, this month and before
	 */
	@Given("several payrolls")
	public void several_payrolls() {
		List<PayrollBLDto> payrolls = new ArrayList<>();
		LocalDate fromDate = LocalDate.now();
		LocalDate toDate = inForce.startDate;
		while (toDate.isBefore(fromDate) || toDate.isEqual(fromDate)) {
			payrolls.add(generateAMonthPayroll(inForce, toDate));
			toDate = toDate.plusMonths(1);
		}
		ctx.put(Key.PAYROLLS, payrolls);
	}

	private PayrollBLDto generateAMonthPayroll(ContractBLDto c, LocalDate endDate) {
		double bonus = 0.0;
		if (endDate.getMonthValue() == 6 || endDate.getMonthValue() == 12) {
			bonus = 1000.0;
		}
		pUtil.unique().forContract(c.id).forDate(endDate).forMonthlyWage(1000.0).forBonus(bonus)
				.forProductivityBonus(10.0).forTrienniumPayment(15.0).forIncomeTax(100.0).forNIC(50.0);
		pUtil.register();
		return pUtil.get();
	}

	@Given("several payrolls this month only")
	public void several_payrolls_this_month_only() {
		ContractBLDto c = inForce;
		LocalDate beginDate = LocalDate.now();
		LocalDate endDate = beginDate;
		generateAMonthPayroll(c, endDate);
	}

	@Given("several payrolls before this month")
	public void several_old_payrolls() {
		ContractBLDto c = inForce;
		LocalDate beginDate = c.startDate;
		LocalDate endDate = c.startDate.plusMonths(1);
		while (!endDate.isBefore(beginDate)) {
			generateAMonthPayroll(c, endDate);
			endDate = endDate.minusMonths(1);
		}

	}

	@Then("previous contract settlement is calculated")
	public void settlement_is_calculated() {
		/*
		 * settlement is 158.67
		 */
		double settlement = calculateSettlement(terminated) * 100;
		settlement = Math.round(settlement);
		settlement = (double) settlement / 100;
		assertTrue(terminated.settlement == settlement);

	}

	@When("I try to add a contract for a non existing mechanic")
	public void i_try_to_add_a_contract_for_a_non_existing_mechanic() {
		MechanicBLDto m = new MechanicUtil().unique().get();
		ContractTypeBLDto ct = new ContractTypeUtil().findContractType("PERMANENT").get();
		ProfessionalGroupBLDto pg = new ProfessionalGroupUtil().findProfessionalGroup("I").get();
		
		ContractBLDto c = cUtil.unique().forMechanic(m).withType(ct).withGroup(pg).get();
		tryAddAndKeepException(c);
	}

	@When("I try to add a contract for a non existing contract type")
	public void i_try_to_add_a_contract_for_a_non_existing_contract_type() {
		MechanicBLDto m = (MechanicBLDto) ctx.get(Key.MECHANIC);
		ContractTypeBLDto ct = new ContractTypeUtil().unique().get();
		ProfessionalGroupBLDto pg = new ProfessionalGroupUtil().findProfessionalGroup("I").get();

		ContractBLDto c = cUtil.unique().forMechanic(m).withType(ct).withGroup(pg).get();
		tryAddAndKeepException(c);
	}

	@When("I try to add a contract for a non existing professional group")
	public void i_try_to_add_a_contract_for_a_non_existing_professional_group() {
		MechanicBLDto m = (MechanicBLDto) ctx.get(Key.MECHANIC);
		ContractTypeBLDto ct = new ContractTypeUtil().findContractType("PERMANENT").get();
		ProfessionalGroupBLDto pg = new ProfessionalGroupUtil().unique();

		ContractBLDto c = cUtil.unique().forMechanic(m).withType(ct).withGroup(pg).get();
		tryAddAndKeepException(c);
	}

	@When("I try to add a contract with end date not later than start date")
	public void i_try_to_add_a_contract_with_start_date_not_later_than_today() {
		MechanicBLDto m = (MechanicBLDto) ctx.get(Key.MECHANIC);
		ContractTypeBLDto ct = new ContractTypeUtil().findContractType("FIXED_TERM").get();
		ProfessionalGroupBLDto pg = new ProfessionalGroupUtil().findProfessionalGroup("I").get();		
		ContractBLDto c = cUtil.unique().forMechanic(m).withGroup(pg).withType(ct).get();
		c.endDate = c.startDate;
		
		tryAddAndKeepException(c);
	}

	@When("I try to add a null argument")
	public void i_try_to_add_a_null_argument() {
		ContractBLDto c = null;
		tryAddAndKeepException(c);
	}

	@When("I try to add a contract with null dni")
	public void i_try_to_add_a_contract_with_null_mechanic_id() {

		ContractBLDto c = cUtil.unique().get();
		c.dni = null;

		tryAddAndKeepException(c);
	}

	@When("I try to add a contract with null contract type id")
	public void i_try_to_add_a_contract_with_null_contract_type_id() {

		ContractBLDto c = cUtil.unique().get();
		c.dni = NIFUtil.generateRandomNIF();
		c.contractTypeName = null;
		tryAddAndKeepException(c);
	}

	@When("I try to add a contract with null professional group id")
	public void i_try_to_add_a_contract_with_null_professional_group_id() {

		ContractBLDto c = cUtil.unique().get();
		c.dni = NIFUtil.generateRandomNIF();
		c.contractTypeName = null;
		tryAddAndKeepException(c);
	}

	@When("I try to add a contract with wrong dni {string}")
	public void i_try_to_add_a_contract_with_dni(String mechdni) {

		ContractBLDto c = cUtil.unique().get();
		c.dni = mechdni;
		tryAddAndKeepException(c);
	}

	/*
	 * 
   When I try to add a contract with wrong fields <type> <profGroup> <annualWage> 
	 */

	@When("I try to add a contract with wrong fields {string} {string} {double}")
	public void i_try_to_add_a_contract_wrong(String type, String group, Double wage) {
		ContractBLDto c = cUtil.unique().get();

		c.contractTypeName = type;
		c.professionalGroupName = group;
		c.annualBaseWage = wage;
		tryAddAndKeepException(c);
	}
	
	
	
	@When("I try to add a contract with null end date for FIXED_TERM contract type") 
	public void i_try_to_add_a_contract_with_null_end_date() {
		ContractBLDto c = cUtil.unique().get();
		c.contractTypeName = "FIXED_TERM";
		c.endDate = null;
		tryAddAndKeepException(c);
	}

	private void tryAddAndKeepException(ContractBLDto c) {
		try {
			service.add(c);
			fail();
		} catch (BusinessException ex) {
			ctx.setException(ex);
		} catch (IllegalArgumentException ex) {
			ctx.setException(ex);
		}
	}

	private double calculateSettlement(ContractBLDto arg) {
		long days = arg.startDate.until(arg.endDate, ChronoUnit.DAYS);
		int numberOfEntireYearsWorked = (int) (days / DAYSINAYEAR);
		Double settlement = 0.0;
		if (numberOfEntireYearsWorked > 0) {
			double dailyGrossWage = calculateDailyGrossWageLastYear(arg);
			double compensationDays = findCompensationDays(arg);
			settlement = numberOfEntireYearsWorked * dailyGrossWage * compensationDays;
		}
		return settlement;
	}

	private double calculateDailyGrossWageLastYear(ContractBLDto existing) {
		FindPayrollsByContractLastYearSqlUnitOfWork unit = new FindPayrollsByContractLastYearSqlUnitOfWork(existing.id);
		unit.execute();
		List<PayrollBLDto> lastYear = unit.get();

		double monthlyEarnings = lastYear.stream()
				.map(pr -> pr.bonus + pr.monthlyWage + pr.productivityBonus + pr.trienniumPayment)
				.reduce(0.0, Double::sum);
		return monthlyEarnings / DAYSINAYEAR;
	}

	private double findCompensationDays(ContractBLDto arg) {
		FindContractTypeByIdSqlUnitOfWork unit = new FindContractTypeByIdSqlUnitOfWork(arg.contractTypeName);
		unit.execute();
		return unit.get().get().compensationDays;

	}
}
