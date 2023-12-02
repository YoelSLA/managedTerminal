package orderValidation;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import order.ExportOrder;

public final class ExportValidation extends OrderValidation {

	public static void validateShiftTiming(ExportOrder exportOrder, LocalDateTime arrivalDate) {
		Integer hours = (int) ChronoUnit.HOURS.between(arrivalDate, exportOrder.getTurn().getDate());
		if (hours > 3) {
			throw new RuntimeException("Shift differs by more than 3 hours.");
		}
	}
}