package terminal;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import client.Consignee;
import client.Shipper;
import driver.Driver;
import order.ExportOrder;
import order.ImportOrder;
import orderValidation.ExportValidation;
import position.Position;
import routing.Routing;
import shippingLine.ShippingLine;
import tracker.Tracker;
import trip.Trip;
import truck.Truck;
import truckTransportCompany.TruckTransportCompany;
import turn.Turn;

public class ManagedTerminal extends Terminal {

	private List<Consignee> consignees;
	private List<ExportOrder> exportOrders;
	private List<ImportOrder> importOrders;
	private Routing routing;
	private List<Shipper> shippers;
	private List<ShippingLine> shippingLines;
	private Tracker tracker;
	private List<TruckTransportCompany> truckTransportCompanies;
	private List<Turn> turns;

	public ManagedTerminal(Position geographicalPosition, Routing routing) {
		super(geographicalPosition, "Puerto de Buenos Aires");
		this.consignees = new ArrayList<Consignee>();
		this.exportOrders = new ArrayList<ExportOrder>();
		this.importOrders = new ArrayList<ImportOrder>();
		this.routing = routing;
		this.shippers = new ArrayList<Shipper>();
		this.shippingLines = new ArrayList<ShippingLine>();
		this.tracker = new Tracker();
		this.truckTransportCompanies = new ArrayList<TruckTransportCompany>();
		this.turns = new ArrayList<Turn>();
	}

	public List<Consignee> getConsignees() {
		return consignees;
	}

	public List<ExportOrder> getExportOrders() {
		return exportOrders;
	}

	public List<ImportOrder> getImportOrders() {
		return importOrders;
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

	public Tracker getTracker() {
		return tracker;
	}

	public List<TruckTransportCompany> getTruckTransportCompanies() {
		return truckTransportCompanies;
	}

	public List<Turn> getTurns() {
		return turns;
	}

	public List<Trip> searchTrips() {
		return tracker.searchTrips(allTripsInAllShippingLines());
	}

	public void hireExportService(ExportOrder exportOrder) {
		// Se agrega al shipper que esta en la orden de exportacíon a la terminal
		// gestionada si no esta registrado, caso contriario no se hace nada.
		if (!getShippers().contains(exportOrder.getShipper())) {
			shippers.add(exportOrder.getShipper());
		}
		// Luego se debe validar que la orden de exportación sea valida, para eso se
		// debe verificar que el chofer y el camión de la orden esten registrados en la
		// terminal gestionada.
		ExportValidation.validateOrderFor(exportOrder, this);
		// Se le asigna un turno a la orden de exportación.
		assignTurnFor(exportOrder);
		// Se añade la orden de exportación a la lista de ordenes.
		exportOrders.add(exportOrder);

	}

	private void assignTurnFor(ExportOrder exportOrder) {
		// Se obtiene la fecha de llegada del barco a la terminal gestionada.
		LocalDateTime arrivedDate = exportOrder.getTrip().dateArrivedToTerminal(this);
		// Se crea una nueva instancia de turno con la orden de exportación y la fecha
		// establecida son 6 horas antes de que llegue el buque.
		Turn turn = new Turn(exportOrder, arrivedDate.minus(6, ChronoUnit.HOURS));
		turns.add(turn);

	}

	public boolean isItRegistered(Driver driver) {
		return registredDrivers().contains(driver);
	}

	public boolean isItRegistered(Truck truck) {
		return registredTrucks().contains(truck);
	}

	public void registerConsignee(Consignee consignee) {
		consignees.add(consignee);
	}

	public void registerShipper(Shipper shipper) {
		shippers.add(shipper);
	}

	public void registerShippingCompany(ShippingLine shippingCompany) {
		// TODO: HACER
		// Al registrar una naviera en la terminal gestionada, solamente interesa de la
		// naviera los circuitos donde incluyan a la terminal gestionada (sea como
		// destino o origen en algun tramo).
		shippingLines.add(shippingCompany);
	}

	public void registerTruckTransportCompany(TruckTransportCompany truckTransportCompany) {
		truckTransportCompanies.add(truckTransportCompany);
	}

	private List<Driver> registredDrivers() {
		return truckTransportCompanies.stream().flatMap(t -> t.getDrivers().stream()).distinct()
				.collect(Collectors.toList());
	}

	private List<Truck> registredTrucks() {
		return truckTransportCompanies.stream().flatMap(t -> t.getTrucks().stream()).distinct()
				.collect(Collectors.toList());
	}

	private List<Trip> allTripsInAllShippingLines() {
		return shippingLines.stream().flatMap(s -> s.getTrips().stream().distinct()).toList();
	}

}
