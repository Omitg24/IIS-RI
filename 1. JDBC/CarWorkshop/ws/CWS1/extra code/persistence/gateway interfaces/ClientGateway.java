package uo.ri.cws.application.persistence.client;

import java.util.Optional;

import uo.ri.cws.application.persistence.Gateway;
import uo.ri.cws.application.persistence.client.ClientGateway.ClientDALDto;

public interface ClientGateway extends Gateway<ClientDALDto> {

	Optional<ClientDALDto> findByDni(String clientdni);

	public class ClientDALDto {

		public String id;
		public Long version;
		
		public String dni;
		public String email;
		public String name;
		public String surname;
		public String phone;
		public String city;
		public String street;
		public String zipcode;
		
	}

}
