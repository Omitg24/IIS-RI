package uo.ri.cws.application.service.contracttype.crud.command;

import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.util.BusinessChecks;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.ContractType;
import uo.ri.util.assertion.ArgumentChecks;

//GESTI�N DE TIPOS DE CONTRATO - Borrar tipos de contrato
/**
 * Titulo: Clase DeleteContractType
 * Descripción: Realiza la acción de borrar el tipo de contrato de la base de 
 * datos
 *
 * @author Omar Teixeira González, UO281847
 * @version 16 nov 2022
 */
public class DeleteContractType implements Command<Void>{
	/**
	 * Atributo name
	 */
	private String name;
	
	/**
	 * Constructor con el nombre del tipo de contrato como parámetro
	 * 
	 * @param name, nombre del tipo de contrato
	 */
	public DeleteContractType(String name) {
		ArgumentChecks.isNotBlank(name, "El nombre del tipo de contrato a"
				+ " borrar no puede ser null ni estar vac�o");	
		this.name=name;
	}

	/**
	 * Método execute
	 * Ejecuta la acción de la clase
	 */
	@Override
	public Void execute() throws BusinessException {		
		Optional<ContractType> oct = Factory.repository.forContractType().findByName(name);
		BusinessChecks.isTrue(oct.isPresent(), "El tipo de contrato a eliminar no existe");
		
		ContractType ct = oct.get();		
		BusinessChecks.isTrue(ct.getContracts().isEmpty(), "No se pueden borrar "
				+ "tipos de contrato que tengan contratos asociados");
//		BusinessChecks.isTrue(Factory.repository.forContract().findByContractTypeId(
//				ct.getId()).isEmpty(), "No se pueden borrar tipos de contrato que "
//						+ "tengan contratos asociados");
		
		Factory.repository.forContractType().remove(ct);	
		return null;
	}
}
