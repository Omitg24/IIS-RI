package uo.ri.cws.application.service.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;

import uo.ri.cws.application.business.contracttype.ContractTypeService.ContractTypeBLDto;
import uo.ri.cws.application.service.util.sql.AddContractTypeSqlUnitOfWork;
import uo.ri.cws.application.service.util.sql.FindContractTypeByNameSqlUnitOfWork;

public class ContractTypeUtil {
	private ContractTypeBLDto dto = createContractTypeDto();

	public ContractTypeUtil register() {
		new AddContractTypeSqlUnitOfWork(dto).execute();
		return this;
	}


	public static void sortBLDtoByName(List<ContractTypeBLDto> arg) {
		Collections.sort(arg, new Comparator<ContractTypeBLDto>() {
			  @Override
			  public int compare(ContractTypeBLDto p1, ContractTypeBLDto p2) {
			    return p1.name.compareTo(p2.name);
			  }
			});
	}

	public ContractTypeBLDto get() {
		return dto;
	}
	

	private ContractTypeBLDto createContractTypeDto() {
		ContractTypeBLDto res = new ContractTypeBLDto();

		res.id = UUID.randomUUID().toString();
		res.version = 1L;
		res.name = "dummy-contract-type-name";
		res.compensationDays = new Random().nextDouble() * 5.0;
		return res;
	}
	public ContractTypeUtil withName(String arg) {
		dto.name = arg;
		return this;
	}
	
	public ContractTypeUtil withDays(double arg) {
		dto.compensationDays= arg;
		return this;
	}

	public void findContractType() {
		
	}
	
	public static boolean match(ContractTypeBLDto g1, ContractTypeBLDto g2) {
		 if (g1 == g2)
		        return true;
		    // null check
		    if (g1 == null)
		        return (g2 == null);

		    if ( (match(g1.id, g2.id))
		    		&& (g1.version == g2.version)
					&& (match(g1.name, g2.name))
					&& Math.abs(g1.compensationDays - g2.compensationDays) < Double.MIN_NORMAL
					)
		    	return true;
		    else
		    	return false;
	}
	
	private static boolean match(String id1, String id2) {
		return (id1.compareTo(id2) == 0);
	}


	public ContractTypeUtil withId(String arg) {
		this.dto.id = arg;
		return this;
	}


	public static boolean matchPERMANENT(ContractTypeBLDto arg) {
	    if ( (match(arg.name, "PERMANENT"))
				&& Math.abs(arg.compensationDays - 1.35) < Double.MIN_NORMAL
				)
	    	return true;
	    else
	    	return false;
	}


	public static boolean matchTEMPORARY(ContractTypeBLDto arg) {
	    if ( (match(arg.name, "TEMPORARY"))
				&& Math.abs(arg.compensationDays - 3.25) < Double.MIN_NORMAL
				)
	    	return true;
	    else
	    	return false;
	}


	public static boolean matchFIXEDTERM(ContractTypeBLDto arg) {
	    if ( (match(arg.name, "FIXED_TERM"))
				&& Math.abs(arg.compensationDays - 4.2) < Double.MIN_NORMAL
				)
	    	return true;
	    else
	    	return false;
	}


	public static boolean isDefaultContractType(ContractTypeBLDto c) {
		return (matchFIXEDTERM(c) || matchPERMANENT(c) || matchTEMPORARY(c) );
	}


	public static boolean newContractTypeIsFound(ContractTypeBLDto arg, List<ContractTypeBLDto> allFound) {
		for (ContractTypeBLDto c : allFound)
			if (match(c, arg))
				return true;
		return false;
	}


	public ContractTypeUtil findContractType(String string) {
		FindContractTypeByNameSqlUnitOfWork unit = new FindContractTypeByNameSqlUnitOfWork(string);
		unit.execute();
		this.dto = unit.get().get();
		return this;
	}


	public ContractTypeUtil unique() {
		this.dto = new ContractTypeBLDto();

		this.dto.id = UUID.randomUUID().toString();
		this.dto.version = 1L;
		this.dto.name = RandomStringUtils.randomAlphabetic(4) + "-name";
		this.dto.compensationDays = new Random().nextInt();
		return this;
	}
}
