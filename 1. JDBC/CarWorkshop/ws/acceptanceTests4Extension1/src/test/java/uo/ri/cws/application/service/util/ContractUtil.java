package uo.ri.cws.application.service.util;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.contract.ContractService.ContractBLDto;
import uo.ri.cws.application.business.contract.ContractService.ContractState;
import uo.ri.cws.application.business.contract.ContractService.ContractSummaryBLDto;
import uo.ri.cws.application.business.contracttype.ContractTypeService.ContractTypeBLDto;
import uo.ri.cws.application.business.mechanic.MechanicService.MechanicBLDto;
import uo.ri.cws.application.business.professionalgroup.ProfessionalGroupService.ProfessionalGroupBLDto;
import uo.ri.cws.application.service.util.sql.AddContractSqlUnitOfWork;
import uo.ri.cws.application.service.util.sql.FindContractByIdSqlUnitOfWork;
import uo.ri.cws.application.service.util.sql.FindContractInForceByMechanicIdSqlUnitOfWork;
import uo.ri.cws.application.service.util.sql.FindContractsInForceSqlUnitOfWork;

public class ContractUtil {

	private ContractBLDto dto = null;
	private MechanicBLDto mDto;
	private ContractTypeBLDto ctDto;
	private ProfessionalGroupBLDto pgDto;

	private final double minAnnualWage = 965.0;
	private final double maxAnnualWage = 5000.0;
	private List<ContractBLDto> list = new ArrayList<>();
	
	public ContractUtil unique() {
		dto = new ContractBLDto();
		dto.id = UUID.randomUUID().toString();
		dto.version = 1L;
		dto.startDate = LocalDate.now()
				.with(TemporalAdjusters.firstDayOfNextMonth());
		double value = new Random().nextDouble() * (maxAnnualWage - minAnnualWage) + minAnnualWage;		
		dto.annualBaseWage = Math.round(value * 100.0) / 100.0; 
		dto.contractTypeName = "PERMANENT";
		dto.professionalGroupName = "I";
		dto.settlement = 0.0;
		dto.state = ContractState.IN_FORCE;
		return this;
	}


	
	public ContractUtil register() throws BusinessException {
		String contractTypeId = this.ctDto.id;
		String professionalGroupId = this.pgDto.id;
		String mechanicId = this.mDto.id;
		new AddContractSqlUnitOfWork(dto, mechanicId, contractTypeId, professionalGroupId).execute();
		return this;
	}

	public ContractUtil registerIfNew() throws BusinessException {
		FindContractByIdSqlUnitOfWork unit = new FindContractByIdSqlUnitOfWork(dto.id);
		unit.execute();
		Optional<ContractBLDto> op = unit.get();
		if ( op.isEmpty() ) {
			register();
		}
		else {
			dto.id = op.get().id;
		}
		return this;
	}

	public ContractBLDto get() {
		return this.dto;
	}
	
	public List<ContractBLDto> getList() {
		return this.list ;
	}
	public ContractUtil withId(String id) {
		this.dto.id = id;
		return this;
	}
	
	public ContractUtil forMechanic(MechanicBLDto m) {
		this.mDto = m;
		this.dto.dni = m.dni;
		return this;
	}
	
	public ContractUtil withType(ContractTypeBLDto ct) {
		this.ctDto = ct;
		this.dto.contractTypeName = ct.name;
		return this;
	}
	
	public ContractUtil withGroup(ProfessionalGroupBLDto pg) {
		this.pgDto = pg;
		this.dto.professionalGroupName = pg.name;
		return this;
	}
	
	public ContractUtil withState(String string) {
		this.dto.state = ContractState.valueOf(string);
		return this;
	}
	
	public static boolean match(ContractBLDto c1, ContractBLDto c2) {
		 if (c1 == c2)
		        return true;
		    // null check
		    if (c1 == null)
		        return (c2 == null);

		    if ( c1.state.equals( c2.state )  
					&& (match(c1.id, c2.id))
					&& (match(c1.dni, c2.dni))
					&& (match(c1.professionalGroupName, c2.professionalGroupName))
					&& (match(c1.contractTypeName, c2.contractTypeName))
					&& Math.abs(c1.annualBaseWage - c2.annualBaseWage) < Double.MIN_NORMAL
					&& Math.abs(c1.settlement - c2.settlement) < Double.MIN_NORMAL
					&& matchDates(c1.startDate, c2.startDate)
					&& matchDates(c1.endDate, c2.endDate)
					)
		    	return true;
		    else
		    	return false;
	}

	public static boolean matchDates(LocalDate d1, LocalDate d2) {
		if ((d1 == null && d2 == null) || (d1 != null && d2 != null && d1.compareTo(d2) == 0))
			return true;
		return false;
	}
	
	private static boolean match(String id1, String id2) {
		return (id1.compareTo(id2) == 0);
	}

	public ContractUtil withStartDate(LocalDate arg) {
		this.dto.startDate = arg;
		return this;
	}

	public ContractUtil withEndDate(LocalDate arg) {
		this.dto.endDate = arg;
		return this;
	}

	public ContractUtil withAnnualWage(Double arg) {
		this.dto.annualBaseWage = arg;
		return this;
	}

	public static boolean match(ContractSummaryBLDto cs, ContractBLDto c) {
	    // null check
	    if (cs == null)
	        return (c == null);

	    if ( cs.state.equals( c.state )  
				&& (match(cs.id, c.id))
				&& (match(cs.dni, c.dni))
				&& Math.abs(cs.settlement - c.settlement) < Double.MIN_NORMAL
				)
	    	return true;
	    else
	    	return false;		
	}

	public ContractUtil findContractById(String id) {
		FindContractByIdSqlUnitOfWork unit = new FindContractByIdSqlUnitOfWork(id);
		unit.execute();
		this.dto = unit.get().get();
		return this;
	}

	public ContractUtil findContractInForceForMechanic(String mechId) {
		FindContractInForceByMechanicIdSqlUnitOfWork unit = new FindContractInForceByMechanicIdSqlUnitOfWork(mechId);
		unit.execute();
		Optional<ContractBLDto> c = unit.get();
		if(c.isPresent())
			this.dto = c.get();
		else
			this.dto = null;
		return this;
		
	}
	
	
	public ContractUtil findAllContractsInForce() {
		FindContractsInForceSqlUnitOfWork unit = new FindContractsInForceSqlUnitOfWork();
		unit.execute();
		list = unit.get();
		return this;
		
	}
}
