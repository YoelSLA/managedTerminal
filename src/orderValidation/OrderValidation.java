package orderValidation;

import driver.Driver;
import order.Order;
import terminal.ManagedTerminal;
import truck.Truck;

public abstract class OrderValidation {
	
	public static void validateOrderWithDriverAndTruckInTerminal(ManagedTerminal managedTerminal, Driver driver, Truck truck) {
	    validateTruckInTerminal(managedTerminal, truck);
	    validateDriverInTerminal(managedTerminal, driver);
	}

	private static void validateTruckInTerminal(ManagedTerminal managedTerminal, Truck truck) {
	    if (!managedTerminal.isTruckRegistered(truck)) {
	        throw new RuntimeException("Truck not registered in the Managed Teminal.");
	    }

	}

	private static void validateDriverInTerminal(ManagedTerminal managedTerminal, Driver driver) {
	    if (!managedTerminal.isDriverRegistered(driver)) {
	        throw new RuntimeException("Driver not registered in the Managed Teminal.");
	    }
	}
	
	public static void validateDriverAndTruckWithClientInfo(Order order, Driver driver, Truck truck) {
		// TODO Auto-generated method stub
		
	}


}
