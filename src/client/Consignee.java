package client;

import java.time.LocalDateTime;

import bill.Bill;
import terminal.ManagedTerminal;

public class Consignee extends Client {

	public Consignee(String dni, String name) {
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
