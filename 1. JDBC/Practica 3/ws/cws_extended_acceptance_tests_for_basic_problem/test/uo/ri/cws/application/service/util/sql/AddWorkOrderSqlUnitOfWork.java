package uo.ri.cws.application.service.util.sql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import uo.ri.cws.application.business.workorder.WorkorderService.WorkOrderBLDto;

public class AddWorkOrderSqlUnitOfWork {

	private WorkOrderBLDto dto;
	private ConnectionData connectionData;
	private PreparedStatement insertIntoWorkOrders;

	public AddWorkOrderSqlUnitOfWork(WorkOrderBLDto dto) {
		this.connectionData = PersistenceXmlScanner.scan();
		this.dto = dto;
	}

	public void execute() {
		JdbcTransaction trx = new JdbcTransaction( connectionData );
		trx.execute((con) -> {
			prepareStatements( con );
			insertWorkOrder();
		});
	}

	private static final String INSERT_INTO_TWORKORDERS =
			"INSERT INTO TWORKORDERS"
			+ " ( ID, AMOUNT, DATE, DESCRIPTION, STATE, VERSION, VEHICLE_ID, MECHANIC_ID, INVOICE_ID )"
			+ " VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	private void insertWorkOrder() throws SQLException {
		PreparedStatement st = insertIntoWorkOrders;
		int i = 1;
		st.setString(i++, dto.id);
		st.setDouble(i++, dto.total);
		
		st.setDate(i++, Date.valueOf(dto.date.toLocalDate()) );
		st.setString(i++, dto.description);
		st.setString(i++, dto.state);
		st.setLong(i++, dto.version);
		st.setString(i++, dto.vehicleId);
		st.setString(i++, dto.mechanicId);
		st.setString(i++, dto.invoiceId);

		st.executeUpdate();
	}

	private void prepareStatements(Connection con) throws SQLException {
		insertIntoWorkOrders = con.prepareStatement(INSERT_INTO_TWORKORDERS);
	}

}
