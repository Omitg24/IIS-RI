package uo.ri.cws.application.service.util.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import uo.ri.cws.application.persistence.invoice.InvoiceGateway.InvoiceDALDto;

public class FindInvoiceByNumberSqlUnitOfWork {


	private Long number;
	private InvoiceDALDto result = null;

	private ConnectionData connectionData;
	private PreparedStatement findInvoice;

	public FindInvoiceByNumberSqlUnitOfWork(String id) {
		this.connectionData = PersistenceXmlScanner.scan();
		this.number = Long.parseLong(id);
	}

	public void execute() {
		JdbcTransaction trx = new JdbcTransaction( connectionData );
		trx.execute((con) -> {
			prepareStatements( con );
			findInvoice();
		});
	}

	public InvoiceDALDto get() {
		return result;
	}

	private static final String FIND_BY_NUMBER =
			"SELECT * FROM TINVOICES "
				+ " WHERE number = ?";

	private void findInvoice() throws SQLException {
		PreparedStatement st = findInvoice;

		int i = 1;
		st.setLong(i++, number);

		ResultSet rs = st.executeQuery();
		
		if ( rs.next() ) {
			result = new InvoiceDALDto();
			result.id = rs.getString("id");
			result.number = rs.getLong("number");
			result.state = rs.getString("state");
			result.amount = rs.getDouble("amount");
		}
	}

	private void prepareStatements(Connection con) throws SQLException {
		findInvoice = con.prepareStatement(FIND_BY_NUMBER);
	}

	
}
