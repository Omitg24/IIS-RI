package uo.ri.cws.application.persistence.client;

import java.util.Optional;

import uo.ri.cws.application.persistence.Gateway;
import uo.ri.cws.application.persistence.client.ClientGateway.ClientDALDto;

/**
 * Titulo: Interfaz ClientGateway
 *
 * @author Omar Teixeira González, UO281847
 * @version 18 oct 2022
 */
public interface ClientGateway extends Gateway<ClientDALDto> {	
	/**
	 * Método findByDni
	 * @param clientdni
	 * @return
	 */
	Optional<ClientDALDto> findByDni(String clientdni);

	/**
	 * Titulo: Clase ClientDALDto
	 *
	 * @author Omar Teixeira González, UO281847
	 * @version 18 oct 2022
	 */
	public class ClientDALDto {
		/**
		 * Atributo id
		 */
		public String id;
		/**
		 * Atributo version
		 */
		public Long version;
		/**
		 * Atributo dni
		 */
		public String dni;
		/**
		 * Atributo email
		 */
		public String email;
		/**
		 * Atributoname
		 */
		public String name;
		/**
		 * Atributo surname
		 */
		public String surname;
		/**
		 * Atributo phone
		 */
		public String phone;
		/**
		 * Atributo city
		 */
		public String city;
		/**
		 * Atributo street
		 */
		public String street;
		/**
		 * Atributo zipcode
		 */
		public String zipcode;		
	}
}
