package uo.ri.cws.application.service.invoice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import uo.ri.cws.application.service.invoice.InvoicingService.InvoicingWorkOrderDto;
import uo.ri.cws.domain.WorkOrder;

/**
 * Título: Clase DtoAssembler
 * Descripción: Contiene al conversor a dto de averías 
 *
 * @author Omar Teixeira González, UO281847
 * @version 16 nov 2022
 */
public class DtoAssembler {
	/**
	 * Método toDto
	 * Devuelve un dto de averías para facturas
	 * 
	 * @param a, avería
	 * @return dto, dto de averías para facturas
	 */
    public static InvoicingWorkOrderDto toDto(WorkOrder a) {
		InvoicingWorkOrderDto dto = new InvoicingWorkOrderDto();
		dto.id = a.getId();
	
		dto.description = a.getDescription();
		dto.date = a.getDate();
		dto.total = a.getAmount();
		dto.state = a.getState().toString();
	
		return dto;
    }
    
    /**
     * Método toWorkOrderDtoList
     * Devuelve una lista de averías para facturas
     * 
     * @param list, lista de averías
     * @return list, lista de averías para facturas
     */
    public static List<InvoicingWorkOrderDto> toWorkOrderDtoList(List<WorkOrder> list) {
    	return list.stream().map(a -> toDto(a)).collect(Collectors.toList());
    }

    /**
     * Método toWorkOrderForInvoicingDtoList
     * Devuelve una lista de averías para facturas
     * 
     * @param list, lista de averías
     * @return list, lista de averías para facturas
     */
    public static List<InvoicingWorkOrderDto> toWorkOrderForInvoicingDtoList(List<WorkOrder> list) {
		List<InvoicingWorkOrderDto> result = new ArrayList<InvoicingWorkOrderDto>();
		for (WorkOrder w : list)
		    result.add(toDto(w));
		return result;
    }
}

