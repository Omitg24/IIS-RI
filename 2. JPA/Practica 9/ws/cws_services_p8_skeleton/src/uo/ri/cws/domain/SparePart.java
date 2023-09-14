package uo.ri.cws.domain;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import uo.ri.cws.domain.base.BaseEntity;
import uo.ri.util.assertion.ArgumentChecks;

/**
 * Titulo: Clase SparePart
 * Descripción: Contiene la entidad que corresponde a las piezas de repuesto 
 *
 * @author Omar Teixeira González, UO281847
 * @version 12 nov 2022
 */
@Entity
@Table(name = "TSpareParts")
public class SparePart extends BaseEntity {
	// natural attributes
	/**
	 * Atributo code
	 */
	@Column(unique = true)
	@Basic(optional = false)
	private String code;
	/**
	 * Atributo description
	 */
	@Basic(optional = false)
	private String description;
	/**
	 * Atributo price
	 */
	@Basic(optional = false)
	private double price;

	// accidental attributes
	/**
	 * Atributo substitutions
	 */
	@OneToMany(mappedBy = "sparePart")
	private Set<Substitution> substitutions = new HashSet<>();

	/**
	 * Cosntructor sin parámetros de la clase SparePart
	 */
	SparePart() {}

	/**
	 * Constructor con el codígo de la pieza de repuesto como parámetro de 
	 * la clase SparePart
	 * 
	 * @param code, codigo de la pieza de repuesto
	 */
	public SparePart(String code) {
		this(code, "no-description", 0);
	}

	/**
	 * Constructor con el codígo, la descripción y el precio de la pieza de 
	 * repuesto como parámetro de la clase SparePart
	 * 
	 * @param code
	 * @param description
	 * @param price
	 */
	public SparePart(String code, String description, double price) {
		ArgumentChecks.isNotBlank(code);
		ArgumentChecks.isNotBlank(description);
		ArgumentChecks.isNotNull(price);
		ArgumentChecks.isTrue(price >= 0);

		this.code = code;
		this.description = description;
		this.price = price;
	}

	/**
	 * Método getSustitutions
	 * Devuelve las sustituciones de las piezas de repuesto a realizar
	 * 
	 * @return substitutions, sustituciones de las piezas de repuesto
	 */
	public Set<Substitution> getSustitutions() {
		return new HashSet<>(substitutions);
	}

	/**
	 * Método _getSubstitutions
	 * Devuelve las sustituciones de las piezas de repuesto para modificarlas
	 * 
	 * @return substitutions, sustituciones de las piezas de repuesto
	 */
	public Set<Substitution> _getSubstitutions() {
		return substitutions;
	}

	/**
	 * Método getCode
	 * Devuelve el código de la pieza de repuesto
	 * 
	 * @return code, código de la pieza de repuesto
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Método setCode
	 * Modifica el código de la pieza de repuesto
	 * 
	 * @param code, código de la pieza de repuesto
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Método getDescription
	 * Devuelve la descripción de la pieza de repuesto
	 * 
	 * @return description, descipción de la pieza de repuesto
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Método setDescription 
	 * Modifica la descripción de la pieza de repuesto
	 * 
	 * @param description, descipción de la pieza de repuesto
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Método getPrice
	 * Devuelve el precio de la pieza de repuesto
	 * 
	 * @return price, precio de la pieza de repuesto
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Método setPrice
	 * Modifica el precio de la pieza de repuesto
	 * 
	 * @param price, precio de la pieza de repuesto
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * Método hashCode 
	 * Devuelve el hashCode del objeto
	 * 
	 * @return hashCode, hashCode de la entidad
	 */
	@Override
	public int hashCode() {
		return Objects.hash(code);
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
		SparePart other = (SparePart) obj;
		return Objects.equals(code, other.code);
	}

	/**
	 * Método toString 
	 * Devuelve una cadena con los datos de la clase
	 * 
	 * @return string, cadena con los datos de la clase
	 */
	@Override
	public String toString() {
		return "SparePart [code=" + code + ", description=" + description + ", price=" + price + "]";
	}
}
