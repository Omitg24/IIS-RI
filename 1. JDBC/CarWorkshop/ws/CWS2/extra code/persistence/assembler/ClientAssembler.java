package uo.ri.cws.application.persistence.client.assembler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import uo.ri.cws.application.persistence.client.ClientGateway.ClientDALDto;

public class ClientAssembler {
	public static Optional<ClientDALDto> toClientDALDto(ResultSet rs) throws SQLException {
		if (rs.next()) {
			return Optional.of(resultSetToClientDALDto( rs ));
		}
		else 	
			return Optional.ofNullable(null);
	}


	private static ClientDALDto resultSetToClientDALDto(ResultSet rs) throws SQLException {
		ClientDALDto record = new ClientDALDto();
		record.id = rs.getString("id");
		record.version = rs.getLong("version");
		
		record.dni = rs.getString("dni");
		record.name = rs.getString("name");
		record.surname = rs.getString("surname");
		record.phone = rs.getString("phone");
		record.email = rs.getString("email");
		record.street = rs.getString("street");
		record.city = rs.getString("City");
		record.zipcode = rs.getString("Zipcode");
		return record;
	}
}
