package uo.ri.cws.application.service.util.sql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import uo.ri.cws.application.business.contract.ContractService.ContractBLDto;
import uo.ri.cws.application.business.contract.ContractService.ContractState;
import uo.ri.cws.application.service.util.db.ConnectionData;
import uo.ri.cws.application.service.util.db.JdbcTransaction;
import uo.ri.cws.application.service.util.db.PersistenceXmlScanner;


public class FindContractByIdSqlUnitOfWork {

	private String id;
	private ConnectionData connectionData;
	private PreparedStatement find;
	private static final String FIND_CONTRACT_BY_ID =
			"SELECT * FROM TCONTRACTS"
			+ " WHERE ID = ?"
			;
	private String mId, ctId, pgId;
	private Optional<ContractBLDto> result = Optional.empty();

	
	public FindContractByIdSqlUnitOfWork(String id) {
		this.connectionData = PersistenceXmlScanner.scan();
		this.id = id;
	}

	public void execute() {
		JdbcTransaction trx = new JdbcTransaction( connectionData );
		trx.execute((con) -> {
			prepareStatements( con );
			findContract();
			fillContract();
		});
	}
	
	private void fillContract() {
		ContractBLDto c = this.result.get();

		FindMechanicByIdSqlUnitOfWork munit = new FindMechanicByIdSqlUnitOfWork(mId);
		munit.execute();
		
		c.dni = munit.get().dni;
		
		FindContractTypeByIdSqlUnitOfWork ctunit = new FindContractTypeByIdSqlUnitOfWork(ctId);
		ctunit.execute();
		c.contractTypeName = ctunit.get().get().name;
		
		FindProfessionalGroupByIdSqlUnitOfWork pgunit = new FindProfessionalGroupByIdSqlUnitOfWork(pgId);
		pgunit.execute();
		c.professionalGroupName = pgunit.get().name;
		this.result = Optional.of(c);
	}

	public Optional<ContractBLDto> get() {
		return result;
	}

	private void findContract() throws SQLException {
		ContractBLDto result = new ContractBLDto();
		PreparedStatement st = find;

		int i = 1;
		st.setString(i++, id);

		ResultSet rs = st.executeQuery();
		
		if ( rs.next() ) {
			result.id = rs.getString("id");
			result.version = rs.getLong("version");
			result.annualBaseWage = rs.getDouble("annualbasewage");
			
			ctId = rs.getString("contracttype_id");
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
			this.result = Optional.ofNullable(result);
		}
	}

	private void prepareStatements(Connection con) throws SQLException {
		find = con.prepareStatement(FIND_CONTRACT_BY_ID);
	}

}
