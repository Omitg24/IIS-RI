package uo.ri.cws.domain.base;

import java.util.UUID;

/**
 * 
 * Titulo: Clase BaseEntity 
 * Descripción: Contiene la información base de las distintas entidades que 
 * se encuentran en el modelo
 *
 * @author Omar Teixeira González, UO281847
 * @version 12 nov 2022
 */
public abstract class BaseEntity {
	/**
	 * Atributo id
	 */
	private String id = UUID.randomUUID().toString();

	/**
	 * Atributo version
	 */
	private long version = 1L;

	/**
	 * Constructor sin parámetros de la clase BaseEntity
	 */
	public BaseEntity() {
		super();
	}

	/**
	 * Método getId 
	 * Devuelve el id
	 * 
	 * @return id, id de la entidad
	 */
	public String getId() {
		return id;
	}

	/**
	 * Método getVersion 
	 * Devuelve la version
	 * 
	 * @return version, version de la entidad
	 */
	public long getVersion() {
		return version;
	}

	/**
	 * Método hashCode 
	 * Devuelve el hashCode del objeto
	 * 
	 * @return hashCode, hashCode de la entidad
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/**
	 * Método equals 
	 * Devuelve true si dos objetos son iguales
	 * 
	 * @return true o false, en función de si los objetos son iguales
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseEntity other = (BaseEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}