package uo.ri.cws.application.persistence;

import java.util.List;
import java.util.Optional;

/**
 * Titulo: Interfaz Gateway
 * Common interface for a gateway that provides common operations for any gateway.
 * That is, CRUD operations: add, remove, update, find by id and find all.
 *
 * @author Omar Teixeira González, UO281847
 * @version 14 oct 2022
 * @param <T>
 */
public interface Gateway<T> {
	/**
	 * Método add
	 * Adds a new item to the table 
	 * @param t, new item 
	 */
	void add(T t) ;
	/**
	 * Método remove
	 * Removes an object from the table
	 * @param id, key to delete
	 */
	void remove(String id) ;
	/**
	 * Método update
	 * Updates a row
	 * @param t, new data to overwrite old one
	 */
	void update(T t) ;
	/**
	 * Método findById
	 * Finds a row in the table
	 * @param id, record's primary key to retrieve
	 * @return dto from that record, probably null
	 */
	Optional<T> findById(String id) ;
	/**
	 * Método findAll
	 * Retrieves all data in a table
	 * @return list
	 */
	List<T> findAll( );
}
