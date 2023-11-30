package terminal;

import java.util.ArrayList;
import java.util.List;

import client.Consignee;
import client.Shipper;
import order.ExportOrder;
import order.ImportOrder;
import position.Position;
import routing.Routing;
import shippingLine.ShippingLine;
import tracker.Tracker;
import truckTransportCompany.TruckTransportCompany;
import turn.Turn;

public class ManagedTerminal implements Terminal {

	private List<Consignee> consignees;
	private List<ExportOrder> exportOrders;
	private List<ImportOrder> importOrders;
	private Routing routing;
	private List<Shipper> shippers;
	private List<ShippingLine> shippingLines;
	private Tracker tracker;
	private List<TruckTransportCompany> truckTransportCompanies;
	private List<Turn> turns;

	public ManagedTerminal(Routing routing) {
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

	public Tracker getTracker() {
		return tracker;
	}

	public List<TruckTransportCompany> getTruckTransportCompanies() {
		return truckTransportCompanies;
	}

	public List<Turn> getTurns() {
		return turns;
	}

	// ----------------------------------
	// REGISTRATION METHODS
	// ----------------------------------

	public void registerConsignee(Consignee consignee) {
		consignees.add(consignee);
	}

	public void registerShipper(Shipper shipper) {
		shippers.add(shipper);
	}

	public void registerShippingCompany2(ShippingLine shippingCompany) {

		System.out.println("1 " + shippingCompany.getMaritimeCircuits());
		System.out.println("2 " + shippingCompany.maritimeCircuitsForTerminal(this));
		shippingCompany.setMaritimeCircuits(shippingCompany.maritimeCircuitsForTerminal(this));
		System.out.println("3 " + shippingCompany.getMaritimeCircuits());
		// TODO: HACER
		// Al registrar una naviera en la terminal gestionada, solamente interesa de la
		// naviera los circuitos donde incluyan a la terminal gestionada (sea como
		// destino o origen en algun tramo).
		shippingLines.add(shippingCompany);
	}

	public void registerShippingCompany(ShippingLine shippingLine) {

		// shippingCompany.registerToTerminal(this);
		// shippingLines.add(shippingCompany);
	}

	public void registerTruckTransportCompany(TruckTransportCompany truckTransportCompany) {
		truckTransportCompanies.add(truckTransportCompany);
	}

}
