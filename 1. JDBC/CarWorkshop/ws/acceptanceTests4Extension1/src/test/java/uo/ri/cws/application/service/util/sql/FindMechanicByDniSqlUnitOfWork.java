package uo.ri.cws.application.service.util.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import uo.ri.cws.application.business.mechanic.MechanicService.MechanicBLDto;
import uo.ri.cws.application.service.util.db.ConnectionData;
import uo.ri.cws.application.service.util.db.JdbcTransaction;
import uo.ri.cws.application.service.util.db.PersistenceXmlScanner;


public class FindMechanicByDniSqlUnitOfWork {

	private String id;
	private ConnectionData connectionData;
	private PreparedStatement find;
	private static final String FIND_MECHANIC_BY_DNI =
			"SELECT * FROM TMECHANICS"
			+ " WHERE DNI = ?"
			;

	private Optional<MechanicBLDto> result = Optional.empty();

	
	public FindMechanicByDniSqlUnitOfWork(String id) {
		this.connectionData = PersistenceXmlScanner.scan();
		this.id = id;
	}

	public void execute() {
		JdbcTransaction trx = new JdbcTransaction( connectionData );
		trx.execute((con) -> {
			prepareStatements( con );
			findMechanic();
		});
	}
	
	private void findMechanic() throws SQLException {
		PreparedStatement st = find;
		MechanicBLDto m = null;
		int i = 1;
		st.setString(i++, id);

		ResultSet rs = st.executeQuery();
		
		if ( rs.next() ) {
			m = new MechanicBLDto();
			m.id = rs.getString("id");
			m.version = rs.getLong("version");
			m.dni = rs.getString("dni");
			m.name = rs.getString("name");
			m.surname = rs.getString("surname");
			result = Optional.ofNullable(m);
		}
	}

	private void prepareStatements(Connection con) throws SQLException {
		find = con.prepareStatement(FIND_MECHANIC_BY_DNI);
	}

	public Optional<MechanicBLDto> get() {

		return this.result;
	}

}
