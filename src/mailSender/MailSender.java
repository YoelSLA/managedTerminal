package mailSender;

import java.time.LocalDateTime;

import bill.Bill;
import client.Client;
import terminal.ManagedTerminal;

public interface MailSender {
	
	public void sendBill(ManagedTerminal managedTerminal, Client client, Bill bill);
	
	public void sendNotifyDepartureShip(ManagedTerminal managedTerminal, Client client, LocalDateTime departureDate);
}
