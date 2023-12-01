package terminal;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import client.Consignee;
import client.Shipper;
import driver.Driver;
import order.ExportOrder;
import order.ImportOrder;
import orderValidation.ExportValidation;
import orderValidation.ImportValidation;
import position.Position;
import routing.Routing;
import search.Search;
import ship.Ship;
import shippingLine.ShippingLine;
import trip.Trip;
import truck.Truck;
import truckTransportCompany.TruckTransportCompany;
import turn.Turn;

public class ManagedTerminal implements Terminal {

	private List<Consignee> consignees;
	private List<ExportOrder> exportOrders;
	private List<ImportOrder> importOrders;
	private Routing routing;
	private List<Shipper> shippers;
	private List<ShippingLine> shippingLines;
	private List<TruckTransportCompany> truckTransportCompanies;
	private List<Turn> turns;

	public ManagedTerminal(Routing routing) {
		this.consignees = new ArrayList<Consignee>();
		this.exportOrders = new ArrayList<ExportOrder>();
		this.importOrders = new ArrayList<ImportOrder>();
		this.routing = routing;
		this.shippers = new ArrayList<Shipper>();
		this.shippingLines = new ArrayList<ShippingLine>();
		this.truckTransportCompanies = new ArrayList<TruckTransportCompany>();
		this.turns = new ArrayList<Turn>();
	}

	// ----------------------------------
	// GETTERS
	// ----------------------------------

	public List<Consignee> getConsignees() {
		return consignees;
	}

	public List<ExportOrder> getExportOrders() {
		return exportOrders;
	}

	public List<ImportOrder> getImportOrders() {
		return importOrders;
	}

	@Override
	public String getName() {
		return "Puerto de Buenos Aires";
	}

	@Override
	public Position getPosition() {
		return new Position(-34.5795823299825, -58.373877081937);
	}

	public Routing getRouting() {
		return routing;
	}

	public List<Shipper> getShippers() {
		return shippers;
	}

	public List<ShippingLine> getShippingLines() {
		return shippingLines;
	}

	public List<TruckTransportCompany> getTruckTransportCompanies() {
		return truckTransportCompanies;
	}

	public List<Turn> getTurns() {
		return turns;
	}

	// ------------------------------------------------------------
	// SERVICE POR CLIENTS
	// ------------------------------------------------------------

	public List<Trip> searchTrips(Search search) {
		// Obtengo todos los viajes de cada una de los lineas navieras.
		List<Trip> allTrips = shippingLines.stream().flatMap(s -> s.getTrips().stream()).toList();
		return search.filterTrips(allTrips);
	}

	public LocalDateTime nextDepartureDateTo(Terminal terminal) {
		return null;

	}

	public LocalDateTime timeItTakesToGetTo(ShippingLine shippingLine, Terminal destiny) {
		return null;

	}

	// ------------------------------------------------------------
	// REGISTRATION METHODS
	// ------------------------------------------------------------

	public void registerConsignee(Consignee consignee) {
		consignees.add(consignee);
	}

	public void registerShipper(Shipper shipper) {
		shippers.add(shipper);
	}

	public void registerShippingCompany(ShippingLine shippingLine) {
		shippingLines.add(shippingLine);

	}

	public void registerTruckTransportCompany(TruckTransportCompany truckTransportCompany) {
		truckTransportCompanies.add(truckTransportCompany);
	}

	// ------------------------------------------------------------
	// PROCESS OF EXPORT ORDER
	// ------------------------------------------------------------

	public void hireExportService(ExportOrder exportOrder) {
		// Se verifica si el shipper esta registrado en la terminal gestionada, caso
		// contrario se lo registra.
		registerShipperIfNew(exportOrder.getShipper());
		// Se debe validar que el chofer y el camión esten registrados en la terminal
		// gestionada.
		ExportValidation.validateOrderWithDriverAndTruckInTerminal(this, exportOrder.getDriver(),
				exportOrder.getTruck());
		// Se le asgina un turno al shipper de 6 horas antes de que llegue el buque a la
		// terminal gestionada.
//		System.out.println(exportOrder);
//		System.out.println(exportOrder.getTrip());
//		System.out.println(exportOrder.getTrip().calculateArrivalDateToTerminal(this));
		Turn turnToAssgin = new Turn(exportOrder.getDriver(), exportOrder.getTruck(),
				exportOrder.getTrip().calculateArrivalDateToTerminal(this).minus(6, ChronoUnit.HOURS));
		// Se le asigna el turno a la orden de exportación.
		exportOrder.setTurn(turnToAssgin);
		// Se registra el turno en la terminal gestionada.
		registerTurn(turnToAssgin);
		// Se registra la orden de exportación en la terminal gestionada.
		exportOrders.add(exportOrder);
	}
	
	public void truckArrivedWithLoad(ExportOrder exportOrder, Driver driver, Truck truck, LocalDateTime dateToArrival) {
		// Se debe validar que el chofer y el camión esten registrados en la terminal
		// gestionada.
		ImportValidation.validateOrderWithDriverAndTruckInTerminal(this, driver, truck);
		// Se debe validar que el chofer y el camión sean los informados por el
		// consginee.
		ImportValidation.validateDriverAndTruckWithClientInfo(exportOrder, driver, truck);
	}
	
	private void registerShipperIfNew(Shipper shipper) {
		if (!shippers.contains(shipper)) {
			shippers.add(shipper);
		}
	}

	public void registerTurn(Turn turn) {
		turns.add(turn);
	}
	
	// ------------------------------------------------------------
	// PROCESS OF IMPORT ORDER
	// ------------------------------------------------------------

	public void hireImportService(ImportOrder importOrder) {
		// Se verifica si el consignee esta registrado en la terminal gestionada, caso
		// contrario se lo registra.
		registerConsigneeIfNew(importOrder.getConsignee());
		// Se debe validar que el chofer y el camión esten registrados en la terminal
		// gestionada.
		ExportValidation.validateOrderWithDriverAndTruckInTerminal(this, importOrder.getDriver(),
				importOrder.getTruck());
		
	}
	
	public void truckLeaveWithLoad(ImportOrder importOrder, Driver driver, Truck truck, LocalDateTime dateToArrival) {
		// Se debe validar que el chofer y el camión esten registrados en la terminal
		// gestionada.
		ImportValidation.validateOrderWithDriverAndTruckInTerminal(this, driver, truck);
		// Se debe validar que el chofer y el camión sean los informados por el
		// consginee.
		ImportValidation.validateDriverAndTruckWithClientInfo(importOrder, driver, truck);
		// Se le enviara la facturación con el desglose de los servicios aplicados con
		// la fecha y el monto de cada uno.
		// Ademas, de la facturación del viaje en si mismo, que consta de la sumatoria
		// de todos los tramos realizados por el buque para la entrega correspondiente.
		importOrder.getConsignee().sendBill(this, importOrder.getConsignee(), importOrder.getBill());
	}
	
	private void registerConsigneeIfNew(Consignee consignee) {
		if (!consignees.contains(consignee)) {
			consignees.add(consignee);
		}
	}

	// ------------------------------------------------------------
	// SHIP COMMMUNICATION
	// ------------------------------------------------------------

	@Override
	public void notifyShipArrival(Ship ship) {

	}

	@Override
	public void notifyShipDeparture(Ship ship) {
		// Cuando el buque pasa a estado “outbound” por haberse retirado de la terminal,
		// se
		// envía la factura vía mail con el desglose de servicios aplicados, fecha y
		// monto de cada uno
		// (a partir de la que se desprende el monto total facturado) a quien deba
		// afrontar los costos
		// de los mismos. Este responsable del pago será el shipper para la fase de
		// exportación y el
		// consignee para la fase de importación.
		// Además de la facturación de servicios, al consignee se le debe agregar la
		// facturación del viaje en sí mismo, que consta de la sumatoria del precio de
		// todos los tramos
		// realizados por el buque para la entrega de la carga correspondiente.

		// Se le debe agregar la facturación con el desglose de los servicios aplicados
		// con la fecha y monto de cada uno.
		// PREGUNTA: Con la fecha se refiere a la fecha en el que el servicio fue
		// contratado o a la fecha en la que llego el buque?.

		exportOrders.stream().filter(e -> e.getTrip().equals(ship.getTrip()))
				.forEach(i -> i.getShipper().sendBill(this, i.getShipper(), i.getBill()));

		exportOrders.stream().filter(e -> e.getTrip().equals(ship.getTrip()))
				.forEach(i -> i.getShipper().sendBill(this, i.getShipper(), i.getBill()));

		importOrders.stream().filter(e -> e.getTrip().equals(ship.getTrip())).forEach(i -> i.getConsignee()
				.sendNotifyDepartureShip(this, i.getConsignee(), i.getTrip().calculateArrivalDateToTerminal(this)));
		// PREGUNTA: Esto se debe hacer cuando el camión retire la carga de la terminal?
		// Porque no tiene sentido que cuando llegue el buque se le envie la factura, ya
		// que si se demora en retirar la carga, se le cobra el servicio por
		// almacenamiento excendente. (La terminal )
		// -----------------------

	}

	@Override
	public void notifyShipInminentArrival(Ship ship) {

	}

	// ------------------------------------------------------------
	// OTHERS METHODS
	// ------------------------------------------------------------

	public Boolean isDriverRegistered(Driver driver) {
		return getAllDistinctDriversFromTransportCompanies().contains(driver);
	}

	public Boolean isTruckRegistered(Truck truck) {
		return getAllDistinctTrucksFromTransportCompanies().contains(truck);
	}

	private List<Driver> getAllDistinctDriversFromTransportCompanies() {
		return truckTransportCompanies.stream().flatMap(t -> t.getDrivers().stream()).distinct().toList();
	}

	private List<Truck> getAllDistinctTrucksFromTransportCompanies() {
		return truckTransportCompanies.stream().flatMap(t -> t.getTrucks().stream()).distinct().toList();
	}



}
