package uo.ri.cws.application.service.util.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import uo.ri.cws.application.business.workorder.WorkorderService.WorkOrderBLDto;

public class FindWorkOrderSqlUnitOfWork {
	private static final String FIND_BY_ID =
			"SELECT * FROM TWORKORDERS "
				+ " WHERE iD = ?";


	private String id;
	private WorkOrderBLDto result = new WorkOrderBLDto();

	private ConnectionData connectionData;
	private PreparedStatement find;

	public FindWorkOrderSqlUnitOfWork(String id) {
		this.connectionData = PersistenceXmlScanner.scan();
		this.id = id;
	}

	public void execute() {
		JdbcTransaction trx = new JdbcTransaction( connectionData );
		trx.execute((con) -> {
			prepareStatements( con );
			findInvoice();
		});
	}
	
	private void findInvoice() throws SQLException {
		PreparedStatement st = find;

		int i = 1;
		st.setString(i++, id);

		ResultSet rs = st.executeQuery();
		
		if ( rs.next() ) {
			result.id = rs.getString("id");
			result.version = rs.getLong("version");
			result.total = rs.getDouble("amount");
			result.invoiceId = rs.getString("invoice_id");
			result.state = rs.getString("state");
			
		}
	}


	private void prepareStatements(Connection con) throws SQLException {
		find = con.prepareStatement(FIND_BY_ID);
	}

	public WorkOrderBLDto get() {
		return result;
	}
}
