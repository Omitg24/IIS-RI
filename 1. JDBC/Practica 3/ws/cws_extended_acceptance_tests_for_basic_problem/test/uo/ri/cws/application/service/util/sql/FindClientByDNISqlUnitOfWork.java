package uo.ri.cws.application.service.util.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import uo.ri.cws.application.business.client.ClientService.ClientBLDto;

public class FindClientByDNISqlUnitOfWork {

	private String dni;

	private ConnectionData connectionData;
	private PreparedStatement findVehicle;

	private Optional<ClientBLDto> result;

	public FindClientByDNISqlUnitOfWork(String dni) {
		this.connectionData = PersistenceXmlScanner.scan();
		this.dni = dni;
		this.result = Optional.ofNullable(null);
	}

	public void execute() {
		JdbcTransaction trx = new JdbcTransaction( connectionData );
		trx.execute((con) -> {
			prepareStatements( con );
			find();
		});
	}

	public Optional<ClientBLDto> get() {
		return result;
	}

	private static final String FIND_BY_DNI =
			"SELECT * FROM TCLIENTS "
				+ " WHERE DNI = ?";

	private void find() throws SQLException {
		PreparedStatement st = findVehicle;

		int i = 1;
		st.setString(i++, dni);

		ResultSet rs = st.executeQuery();
		
		if ( rs.next() ) {
			ClientBLDto res = new ClientBLDto();
			res.id = rs.getString("id");
			res.dni = rs.getString("dni");
			this.result = Optional.ofNullable(res);
		}
	}

	private void prepareStatements(Connection con) throws SQLException {
		findVehicle = con.prepareStatement(FIND_BY_DNI);
	}

	
	
}
