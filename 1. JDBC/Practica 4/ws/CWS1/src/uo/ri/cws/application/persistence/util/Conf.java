package uo.ri.cws.application.persistence.util;

import java.io.IOException;
import java.util.Properties;

/**
 * Titulo: Clase Conf
 *
 * @author Omar Teixeira González, UO281847
 * @version 14 oct 2022
 */
public class Conf {
	/**
	 * Constante FILE_CONF
	 */
	private static final String FILE_CONF = "configuration.properties";
	
	/**
	 * Atributo props
	 */
	private Properties props;
	/**
	 * Atributo instance
	 */
	private static Conf instance = null;	

	/**
	 * Constructor Conf
	 */
	private Conf() {
		this.props = new Properties();
		try {
			props.load(Conf.class.getClassLoader().getResourceAsStream(FILE_CONF));
		} catch (IOException e) {
			throw new RuntimeException("File properties cannot be loaded",e);
		}		
	}
	
	/**
	 * Método getInstance
	 * @return instance
	 */
	public static Conf getInstance() {
		if (instance == null) {
			instance = new Conf();
		}
		return instance;
	}
	
	/**
	 * Método getProperty
	 * @param key
	 * @return value
	 */
	public String getProperty(String key) {
		String value = props.getProperty(key);
		if (value == null) {
			throw new RuntimeException("Property not found in config file");
		}
		return value;
	}
}
