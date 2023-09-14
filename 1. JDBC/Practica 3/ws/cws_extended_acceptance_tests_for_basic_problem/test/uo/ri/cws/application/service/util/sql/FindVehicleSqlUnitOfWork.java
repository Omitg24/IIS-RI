package uo.ri.cws.application.service.util.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import uo.ri.cws.application.persistence.vehicle.VehicleGateway.VehicleDALDto;

public class FindVehicleSqlUnitOfWork {

	private String id;
	private VehicleDALDto result = new VehicleDALDto();
	
	private ConnectionData connectionData;
	private PreparedStatement findVehicle;

	public FindVehicleSqlUnitOfWork(String plate) {
		this.connectionData = PersistenceXmlScanner.scan();
		this.id = plate;
	}

	public void execute() {
		JdbcTransaction trx = new JdbcTransaction( connectionData );
		trx.execute((con) -> {
			prepareStatements( con );
			findVehicle();
		});
	}

	public VehicleDALDto get() {
		return result;
	}

	private static final String FIND_BY_PLATE =
			"SELECT * FROM TVEHICLES "
				+ " WHERE PLATENUMBER = ?";

	private void findVehicle() throws SQLException {
		PreparedStatement st = findVehicle;

		int i = 1;
		st.setString(i++, id);

		ResultSet rs = st.executeQuery();
		
		if ( rs.next() ) {
			result.id = rs.getString("id");
			result.platenumber = rs.getString("plateNumber");
			result.client_id = rs.getString("client_id");
		}
	}

	private void prepareStatements(Connection con) throws SQLException {
		findVehicle = con.prepareStatement(FIND_BY_PLATE);
	}

	
	
}
