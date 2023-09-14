package uo.ri.cws.application.service.util.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import uo.ri.cws.application.service.util.db.ConnectionData;
import uo.ri.cws.application.service.util.db.JdbcTransaction;
import uo.ri.cws.application.service.util.db.PersistenceXmlScanner;

public class HsqlDbClearAllTablesUnitOfWork {
	private ConnectionData connectionData = PersistenceXmlScanner.scan();
	private PreparedStatement clearAllTables;

	public void execute() {
		JdbcTransaction trx = new JdbcTransaction( connectionData );
		trx.execute((con) -> {
			prepareStatements( con );
			clearAllTables();
		});
	}

	private static final String CLEAR_ALL_TABLES =
			"TRUNCATE SCHEMA PUBLIC RESTART IDENTITY AND COMMIT NO CHECK";

	private void clearAllTables() throws SQLException {
		PreparedStatement st = clearAllTables;
		st.executeUpdate();
	}

	private void prepareStatements(Connection con) throws SQLException {
		clearAllTables = con.prepareStatement( CLEAR_ALL_TABLES );
	}

}
