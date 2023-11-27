package turn;

import java.time.LocalDateTime;

import order.Order;

public class Turn {

	private Order order;
	private LocalDateTime dateTurn;

	public Turn(Order order, LocalDateTime dateTurn) {
		this.order = order;
		this.dateTurn = dateTurn;
	}

	public LocalDateTime getDateTurn() {
		return dateTurn;
	}

	public Order getOrder() {
		return order;
	}

}
