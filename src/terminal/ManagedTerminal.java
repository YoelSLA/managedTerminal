package terminal;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import client.Client;
import client.Consignee;
import client.Shipper;
import driver.Driver;
import load.Load;
import order.ExportOrder;
import order.ImportOrder;
import order.Order;
import orderValidation.ExportValidation;
import orderValidation.ImportValidation;
import position.Position;
import routing.Routing;
import search.Search;
import service.Electricity;
import service.ExcessStorage;
import service.Washed;
import service.Weigh;
import ship.Ship;
import shippingLine.ShippingLine;
import trip.Trip;
import truck.Truck;
import truckTransportCompany.TruckTransportCompany;

public class ManagedTerminal implements Terminal {

	private List<Consignee> consignees;
	private List<ExportOrder> exportOrders;
	private List<ImportOrder> importOrders;
	private Routing routing;
	private List<Shipper> shippers;
	private List<ShippingLine> shippingLines;
	private List<TruckTransportCompany> truckTransportCompanies;
	private Double weighingCost;
	private Double costPerKw;
	private Double excessStorageCost;
	private Double costPerSmallLoad;
	private Double costPerBigLoad;

	public ManagedTerminal(Routing routing) {
		this.consignees = new ArrayList<Consignee>();
		this.exportOrders = new ArrayList<ExportOrder>();
		this.importOrders = new ArrayList<ImportOrder>();
		this.routing = routing;
		this.shippers = new ArrayList<Shipper>();
		this.shippingLines = new ArrayList<ShippingLine>();
		this.truckTransportCompanies = new ArrayList<TruckTransportCompany>();
		this.weighingCost = 0.0;
		this.setCostPerKw(0.0);
		this.excessStorageCost = 0.0;
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

	public Integer timeItTakesToGetTo(ShippingLine shippingLine, Terminal destiny) {
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
		registerShipperIfNew((Shipper) exportOrder.getClient());
		// Se debe validar que el chofer y el camión esten registrados en la terminal
		// gestionada.
		ExportValidation.validateOrderInTerminal(this, exportOrder);
		// Se le asigna al turno del shipper que contiene la fecha una estimación de 6
		// horas antes de que llegue el buque a la
		// terminal gestionada.
		exportOrder.getTurn()
				.setDate(exportOrder.getTrip().calculateArrivalDateToTerminal(this).minus(6, ChronoUnit.HOURS));
		// Se registra la orden de exportación en la terminal gestionada.
		exportOrders.add(exportOrder);
	}

	public void truckArrivedWithLoad(ExportOrder exportOrder, Driver driver, Truck truck, LocalDateTime dateToArrival) {
		// Se debe validar que el chofer y el camión esten registrados en la terminal
		// gestionada.
		ExportValidation.validateOrderInTerminal(this, exportOrder);
		// Se debe validar que el chofer y el camión sean los informados por el
		// consginee.
		ExportValidation.validateDriverAndTruckWithClientInfo(exportOrder, driver, truck);
		// Se debe validar que la hora del turno no difiera de más de 3 horas con la
		// hora de llegada del camión.
		ExportValidation.validateShiftTiming(exportOrder, dateToArrival);
		// Se debe agregar el servicio de pesado para cualquier carga.
		registerWeighService(exportOrder);
		// Se debe agregar el servicio electrico solamente para el contenedor reefer.
		registerElectricityService(exportOrder, dateToArrival);
	}

	private void registerShipperIfNew(Shipper shipper) {
		if (!shippers.contains(shipper)) {
			shippers.add((Shipper) shipper);
		}
	}

	// ------------------------------------------------------------
	// PROCESS OF IMPORT ORDER
	// ------------------------------------------------------------
	public void hireImportService(ImportOrder importOrder) {
		// Se verifica si el consignee esta registrado en la terminal gestionada, caso
		// contrario se lo registra.
		registerConsigneeIfNew((Consignee) importOrder.getClient());
		// Se debe validar que el chofer y el camiÃ³n esten registrados en la terminal
		// gestionada.
		ExportValidation.validateOrderInTerminal(this, importOrder);
		// Se registra la orden de importación en la terminal gestionada.
		importOrders.add(importOrder);
	}

	public void truckLeaveWithLoad(ImportOrder importOrder, Driver driver, Truck truck, LocalDateTime dateToArrival) {
		// Se debe validar que el chofer y el camión esten registrados en la terminal
		// gestionada.
		ImportValidation.validateOrderInTerminal(this, importOrder);
		// Se debe validar que el chofer y el camión sean los informados por el
		// consginee.
		ImportValidation.validateDriverAndTruckWithClientInfo(importOrder, driver, truck);
		// Se le registra el servicio de exceso de almacenamiento si corresponde.
		registerExcessStorageService(importOrder, dateToArrival);
		// Se le enviara la facturación con el desglose de los servicios aplicados con
		// la fecha y el monto de cada uno.
		// Ademas, de la facturación del viaje en si mismo, que consta de la sumatoria
		// de todos los tramos realizados por el buque para la entrega correspondiente.
		importOrder.getClient().sendBill(this, importOrder.getClient(), importOrder.getBill());
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

//		ship.startWorking();

		// Se debe obtener la fecha en la que llego el buque a la terminal gestionada.
		LocalDateTime arrivalDateShip = ship.getTrip().calculateArrivalDateToTerminal(this);

		// Cuando el buque arriba en la terminal gestionada, se le debe agregar a las
		// ordenes de importación el servicio electrico, solamente si la orden tiene una
		// carga Reefer.
		importOrders.stream().filter(i -> i.getLoad().equals(2))
				.forEach(i -> i.registerService(new Electricity(costPerKw, arrivalDateShip)));

		// Se le debe agregar a todas las ordenes de importación la hora para el turno,
		// que sera 6 horas despues de que haya llegado el buque a la terminal
		// gestionada.
		importOrders.stream().forEach(i -> i.getTurn().setDate(arrivalDateShip.plus(6, ChronoUnit.HOURS)));

		// Cuando el buque arriba en la terminal gestionada, se le debe agregar a las
		// ordenes de exportación al servicio electrico, la fecha en que finaliza el
		// servicio.
		List<Electricity> electricitys = exportOrders.stream().flatMap(i -> i.getServices().stream())
				.filter(s -> s.getName().equals("Electricity")).map(s -> (Electricity) s).collect(Collectors.toList());
		electricitys.forEach(e -> e.setEndConnection(arrivalDateShip));

		// Se le informa al barco su partida.
//		ship.depart();
	}

	@Override
	public void notifyShipDeparture(Ship ship) {
		// Se le envia la factura con los gastos a cada cliente de cada orden de
		// exportación.
		exportOrders.stream().filter(e -> e.getTrip().equals(ship.getTrip()))
				.forEach(e -> e.getClient().sendBill(this, e.getClient(), e.getBill()));
		// Se le avisa a todos los consignees que su carga llego en la determinada
		// fecha.
		importOrders.stream().filter(e -> e.getTrip().equals(ship.getTrip())).forEach(i -> i.getClient()
				.sendNotifyDepartureShip(this, i.getClient(), i.getTrip().calculateArrivalDateToTerminal(this)));
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

	// ------------------------------------------------------------
	// SERVICES
	// ------------------------------------------------------------
	public void hireWashedServiceFor(Load load, Client client) {
		// Se crea una lista con todas las ordenes de la terminalg gestonada.
		List<Order> orders = new ArrayList<>();
		orders.addAll(exportOrders);
		orders.addAll(importOrders);

		// Se debe buscar la orden que corresponde para la carga y el cliente.
		Order order = orders.stream().filter(o -> o.getClient().equals(client)).findFirst().get();
		// Se registra el servicio de lavado en la orden.
		order.registerService(new Washed(costPerBigLoad, costPerSmallLoad));
	}

	private void registerExcessStorageService(Order order, LocalDateTime dateToArrival) {
		// Se obtiene la fecha en la cual el buque llego a la terminal destino (en este
		// caso la gestionada).
		LocalDateTime dateArrivedShip = order.getTrip().calculateArrivalDateToTerminal(this);
		// Se calcula las horas que estuvo la carga en la terminal gestionada.
		Integer hours = (int) ChronoUnit.HOURS.between(dateArrivedShip, dateToArrival);
		// Si supero las 24 horas, se le crea y añade el servicio a la orden.
		if (hours > 24) {
			// Se le agrega las horas que estuvo en la terminal siendo 24 - (las horas que
			// estuvo en la terminal).
			// A partir de las 24 horas se le empieza a cobrar cada hora por la estadia.
			order.registerService(new ExcessStorage(excessStorageCost, 24 - hours));
		}
	}

	private void registerElectricityService(Order order, LocalDateTime dateToStartService) {
		// Se pregunta si la carga es un Reefer, si lo es se le agrega el servicio
		// electrico.
		if (order.getLoad().getName().equals("Reefer")) {
			order.registerService(new Electricity(costPerKw, dateToStartService));
		}
	}

	private void registerWeighService(Order order) {
		order.registerService(new Weigh(weighingCost));
	}

	public void setWeighingCost(Double weighingCost) {
		this.weighingCost = weighingCost;
	}

	public void setCostPerKw(Double costPerKw) {
		this.costPerKw = costPerKw;
	}

}
