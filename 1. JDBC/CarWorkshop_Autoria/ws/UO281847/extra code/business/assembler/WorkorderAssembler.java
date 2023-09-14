package uo.ri.cws.application.business.workorder.assembler;

import uo.ri.cws.application.business.workorder.WorkOrderService;
import uo.ri.cws.application.persistence.workorder.WorkOrderGateway;

import java.util.ArrayList;
import java.util.List;

public class WorkorderAssembler {
    public static List<WorkOrderService.WorkOrderBLDto> toDtoList(List<WorkOrderGateway.WorkOrderDALDto> list) {
        List<WorkOrderService.WorkOrderBLDto> res = new ArrayList<>();

        for (WorkOrderGateway.WorkOrderDALDto w : list)
            res.add(toDto(w));

        return res;
    }


    private static WorkOrderService.WorkOrderBLDto toDto(WorkOrderGateway.WorkOrderDALDto record) {
        WorkOrderService.WorkOrderBLDto dto = new WorkOrderService.WorkOrderBLDto();

        dto.id = record.id;
        dto.version = record.version;

        dto.vehicleId = record.vehicle_id;
        dto.description = record.description;
        dto.date = record.date;
        dto.total = record.amount;
        dto.state = record.state;
        dto.mechanicId = record.mechanic_id;
        dto.invoiceId = record.invoice_id;

        return dto;
    }
}
