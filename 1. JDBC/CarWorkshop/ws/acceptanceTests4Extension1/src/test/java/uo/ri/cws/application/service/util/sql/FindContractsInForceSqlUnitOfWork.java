package uo.ri.cws.application.service.util.sql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import uo.ri.cws.application.business.contract.ContractService.ContractBLDto;
import uo.ri.cws.application.business.contract.ContractService.ContractState;
import uo.ri.cws.application.service.util.db.ConnectionData;
import uo.ri.cws.application.service.util.db.JdbcTransaction;
import uo.ri.cws.application.service.util.db.PersistenceXmlScanner;


public class FindContractsInForceSqlUnitOfWork {

	private ConnectionData connectionData;
	private PreparedStatement find;
	private static final String FIND_CONTRACTS_INFORCE =
			"SELECT * FROM TCONTRACTS"
			+ " WHERE STATE = 'IN_FORCE'" 
			;
	private String mId, ctId, pgId;
	private List<ContractBLDto> resultList = new ArrayList<>();

	
	public FindContractsInForceSqlUnitOfWork() {
		this.connectionData = PersistenceXmlScanner.scan();
		
	}

	public void execute() {
		JdbcTransaction trx = new JdbcTransaction( connectionData );
		trx.execute((con) -> {
			prepareStatements( con );
			findContracts();
			fillContracts();
		});
	}
	
	private void fillContract(ContractBLDto c) {
		
		FindMechanicByIdSqlUnitOfWork munit = new FindMechanicByIdSqlUnitOfWork(mId);
		munit.execute();
		
		c.dni = munit.get().dni;
		
		FindContractTypeByIdSqlUnitOfWork ctunit = new FindContractTypeByIdSqlUnitOfWork(ctId);
		ctunit.execute();
		c.contractTypeName = ctunit.get().get().name;
		
		FindProfessionalGroupByIdSqlUnitOfWork pgunit = new FindProfessionalGroupByIdSqlUnitOfWork(pgId);
		pgunit.execute();
		c.professionalGroupName = pgunit.get().name;
	}
	
	private void fillContracts() {
		ContractBLDto c ;
		for (int index = 0; index<this.resultList.size(); index++) {
			c = this.resultList.get(index);
			fillContract(c);
		}
	}

	public List<ContractBLDto> get() {
		return resultList;
	}

	private void findContracts() throws SQLException {
		ContractBLDto result;
		PreparedStatement st = find;

		ResultSet rs = st.executeQuery();
		
		while ( rs.next() ) {
			result = new ContractBLDto();
			result.id = rs.getString("id");
			result.version = rs.getLong("version");
			result.annualBaseWage = rs.getDouble("annualbasewage");
			
			ctId = rs.getString("contractType_id");
			result.startDate = rs.getDate("startdate").toLocalDate();
			pgId = rs.getString("professionalgroup_id");
			result.state = ContractState.valueOf( rs.getString("state") );
			mId = rs.getString("mechanic_id");
		
			// Optional fields
			result.settlement = rs.getDouble("settlement");
			Date d = rs.getDate("enddate");
			if (rs.wasNull())
				result.endDate = null;
			else
				result.endDate = d.toLocalDate();
			this.resultList.add(result);
		}
	}

	private void prepareStatements(Connection con) throws SQLException {
		find = con.prepareStatement(FIND_CONTRACTS_INFORCE);
	}

}
