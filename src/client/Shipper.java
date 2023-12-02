package client;

import java.time.LocalDateTime;

import bill.Bill;
import terminal.ManagedTerminal;

public class Shipper extends Client {

	public Shipper(String dni, String name) {
		super(dni, name);
	}

	@Override
	public void sendMail(ManagedTerminal managedTerminal, Client client, Bill bill) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendMail(ManagedTerminal managedTerminal, Client client, String string) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendMail(ManagedTerminal managedTerminal, Client client, LocalDateTime date) {
		// TODO Auto-generated method stub

	}

}
