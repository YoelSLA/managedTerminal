package orderValidation;

import java.time.LocalDateTime;

import driver.Driver;
import order.ExportOrder;
import terminal.ManagedTerminal;
import truck.Truck;

/**
 * Validador de �rdenes de exportaci�n. Implementa el algoritmo del m�todo
 * validation de su superclase.
 * 
 * @author Gabriela Fascetta | Yoel Ventoso
 */
public final class ExportValidation extends OrderValidation {

//	public static void validateOrderFor(ExportOrder exportOrder, ManagedTerminal managedTerminal) {
//		validateDriverOf(exportOrder, managedTerminal);
//		validateTruckOf(exportOrder, managedTerminal);
//
//	}

//	private static void validateDriverOf(ExportOrder exportOrder, ManagedTerminal managedTerminal) {
//		if (!managedTerminal.isItRegistered(exportOrder.getDriver())) {
//			throw new RuntimeException("The driver is not registered at the managed terminal.");
//		}
//
//	}

//	private static void validateTruckOf(ExportOrder exportOrder, ManagedTerminal managedTerminal) {
//		if (!managedTerminal.isItRegistered(exportOrder.getTruck())) {
//			throw new RuntimeException("El cami�n no esta registrado en la terminal gestionada.");
//		}
//	}

	public static void validateTruckEntry(Driver driver, ExportOrder exportOrder, Truck truck,
			LocalDateTime arrivalDate) {
		validateDriverReportedByShipper(exportOrder, driver);
		validateTruckArrivalTime(exportOrder, arrivalDate);
		validateTruckReportedByShipper(exportOrder, truck);

	}

	private static void validateTruckArrivalTime(ExportOrder exportOrder, LocalDateTime arrivalDate) {
		if (!(Math.abs(arrivalDate.getHour() - exportOrder.getDateTruck().getHour()) > 3)) {
			throw new RuntimeException("El horario difiere de m�s de 3 horas.");
		}

	}

	private static void validateDriverReportedByShipper(ExportOrder exportOrder, Driver driver) {
		if (!driver.equals(exportOrder.getDriver())) {
			throw new RuntimeException("El chofer no es el informado por el shipper.");
		}
	}

	private static void validateTruckReportedByShipper(ExportOrder exportOrder, Truck truck) {
		if (!truck.equals(exportOrder.getTruck())) {
			throw new RuntimeException("El cami�n no es el informado por el shipper.");
		}
	}

}
