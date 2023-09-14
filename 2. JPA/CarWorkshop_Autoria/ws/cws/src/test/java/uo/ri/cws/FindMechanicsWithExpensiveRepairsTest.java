package uo.ri.cws;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.BusinessFactory;
import uo.ri.cws.application.service.mechanic.MechanicCrudService;
import uo.ri.cws.application.service.mechanic.MechanicCrudService.MechanicDto;
import uo.ri.cws.infrastructure.persistence.jpa.executor.JpaExecutorFactory;
import uo.ri.cws.infrastructure.persistence.jpa.repository.JpaRepositoryFactory;

public class FindMechanicsWithExpensiveRepairsTest {


	private List<String> ids;


	@Before
	public void setUp() throws Exception {
		Factory.service = new BusinessFactory();
		Factory.repository = new JpaRepositoryFactory();
		Factory.executor = new JpaExecutorFactory();
		ids = loadIds();
	}

	
	@Test
	public void severalMechanics() throws BusinessException {
		MechanicCrudService ms = Factory.service.forMechanicCrudService();
		List<MechanicDto> res = ms.findMechanicsWithExpensiveRepairs();

		assertEquals(ids.size(), res.size());
		for (MechanicDto dto : res) {
			assertTrue(ids.contains(dto.id));
		}
	}
	
	private List<String> loadIds() {
		return List.of(
				"74d15878-bb80-4b21-a1a2-b0f63e6e9618",
				"83511938-b027-4b05-980c-d03533c3998a",
				"c8ea322a-313a-4e42-a7ff-2744d24fd174",
				"e826558b-1269-4d65-bbb5-c3d83566afcc");
	}

}
