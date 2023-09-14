package uo.ri.conf;

import uo.ri.cws.application.ServiceFactory;
import uo.ri.cws.application.repository.RepositoryFactory;
import uo.ri.cws.application.util.command.ComandExecutorFactory;

/**
 * Título: Clase Factory
 * Descripción: Contiene las factorias correspondientes 
 *
 * @author Omar Teixeira González, UO281847
 * @version 15 nov 2022
 */
public class Factory {
	/**
	 * Atributo repository
	 */
	public static RepositoryFactory repository;
	/**
	 * Atributo service
	 */
	public static ServiceFactory service;
	/**
	 * Atributo executor
	 */
	public static ComandExecutorFactory executor;
}
