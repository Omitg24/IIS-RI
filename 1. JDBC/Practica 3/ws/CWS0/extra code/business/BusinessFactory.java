package uo.ri.cws.application.business;

import uo.ri.cws.application.business.client.ClientService;
import uo.ri.cws.application.business.invoice.InvoicingService;
import uo.ri.cws.application.business.invoice.create.InvoicingServiceImpl;
import uo.ri.cws.application.business.mechanic.MechanicService;
import uo.ri.cws.application.business.mechanic.crud.MechanicServiceImpl;

public class BusinessFactory {


	public static MechanicService forMechanicService() {
		return new MechanicServiceImpl();
	}

	public static InvoicingService forInvoicingService() {
		return new InvoicingServiceImpl();
	}
//
//	public static WorkOrderService forWorkOrderService() {
//		throw new RuntimeException("Not yet implemented");
//	}
//
//	public static VehicleService forVehicleService() {
//		throw new RuntimeException("Not yet implemented");
//	}
//
//	public static CourseService forCourseService() {
//		throw new RuntimeException("Not yet implemented");
//	}
//
//	public static CourseAttendanceService forCourseAttendanceService() {
//		throw new RuntimeException("Not yet implemented");
//	}
//
//	public static CourseReportService forCourseReportService() {
//		throw new RuntimeException("Not yet implemented");
//	}
//
//	public static CertificateService forCertificateService() {
//		throw new RuntimeException("Not yet implemented");
//	}
//
//	public static VehicleTypeService forVehicleTypeService() {
//		throw new RuntimeException("Not yet implemented");
//	}
//
//	public static AssignWorkOrderService forAssignWorkOrderService() {
//		return new AssignWorkOrderServiceImpl();
//	}
//
	public static ClientService forClientService() {
		throw new RuntimeException("Not yet implemented");
	}
//
//	public static SparePartService forSparePartService() {
//		throw new RuntimeException("Not yet implemented");
//	}
//
//	public static SettleInvoiceService forSettleInvoiceService() {
//		throw new RuntimeException("Not yet implemented");
//	}
//
//	public static ClientHistoryService forClientHistoryService() {
//		throw new RuntimeException("Not yet implemented");
//	}
//
//	public static ViewAssignedWorkOrdersService forViewAssignedWorkOrdersService() {
//		throw new RuntimeException("Not yet implemented");
//	}

}

