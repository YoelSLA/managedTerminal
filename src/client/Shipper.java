package client;

import java.time.LocalDateTime;

import bill.Bill;
import terminal.ManagedTerminal;

public class Shipper extends Client {
	
	private int dni;
	private String name;
	
	public Shipper(int dni, String name) {
		super(dni, name);
	}

	public void sendEmail(Bill bill) {
		// TODO Auto-generated method stub
	}

	@Override
	public void sendBill(ManagedTerminal managedTerminal, Client client, Bill bill) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendNotifyDepartureShip(ManagedTerminal managedTerminal, Client client, LocalDateTime departureDate) {
		// TODO Auto-generated method stub
		
	}
	
}
