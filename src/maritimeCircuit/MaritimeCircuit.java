package maritimeCircuit;

import java.time.Duration;
import java.util.List;
import java.util.stream.Stream;

import stretch.Stretch;
import terminal.Terminal;

public class MaritimeCircuit {

	private List<Stretch> stretchs;

	public MaritimeCircuit(List<Stretch> stretchs) {
		this.stretchs = stretchs;
	}

	public Double getPrice() {
		return getStretchs().stream().mapToDouble(Stretch::getPrice).sum();
	}

	public List<Stretch> getStretchs() {
		return stretchs;
	}

//	public boolean areTheTerminalsThere(Terminal origin, Terminal destiny) {
//		return isTheOriginTerminal(origin) && isTheDestinyTerminal(destiny)
//				&& isTheOriginTerminalBeforeDestinationTerminal(origin, destiny);
//	}

	public boolean isTheDestinyTerminal(Terminal destiny) {
		return getStretchs().stream().anyMatch(s -> s.getDestiny().equals(destiny));
	}

	public boolean isTheOriginTerminal(Terminal origin) {
		return getStretchs().stream().anyMatch(s -> s.getOrigin().equals(origin));
	}

//	public boolean isTheOriginTerminalBeforeDestinationTerminal(Terminal origin, Terminal destiny) {
//		return getPositionOfDestinyInCircuit(origin) < getPositionOfDestinyInCircuit(destiny);
//	}

	public boolean hasADestinyTerminal(Terminal destiny) {
		return getStretchs().stream().anyMatch(s -> {
			boolean result = s.getDestiny().equals(destiny);
			return result;
		});
	}

	public Integer calculateTimeBetween(Terminal origin, Terminal destiny) {

		Integer originPosition = getPositionOfOrigin(origin);
		Integer destinyPosition = getPositionOfDestiny(destiny);

		// Primero se particiona la lista de tramos entre los índices, y luego se suma
		// el tiempo entre todos.
		long totalNanos = getStretchs().subList(originPosition, destinyPosition).stream()
				.mapToLong(s -> s.getTime().toNanos()).sum();

		// Convierte los nanosegundos a horas y redondea al entero más cercano.
		int totalHours = (int) Math.round(totalNanos / (double) Duration.ofHours(1).toNanos());

		return totalHours;
	}

	private Integer getPositionOfDestiny(Terminal destiny) {

		// Se recorre todas los tramos, y se filtran solamente aquellos que tienen la
		// terminal origen.
		Stream<Stretch> stretchsWithOrigin = getStretchs().stream()
				.filter(s -> s.getOrigin().hashCode() == destiny.hashCode());
		// Luego se busca la primera aparacion y se obtiene la posición, despues se
		// busca la posicion en donde encuentra el tramo.
		return getStretchs().indexOf(stretchsWithOrigin.findFirst().get());

	}

	private Integer getPositionOfOrigin(Terminal origin) {

		// Se recorre todos los tramos, y se filtran solamente aquellos que tienen la
		// terminal origen.
		List<Stretch> stretchsWithOrigin = getStretchs().stream()
				.filter(s -> s.getOrigin().hashCode() == origin.hashCode()).toList();
		// Luego se busca la primera aparacion y se obtiene la posición, despues se
		// busca la posicion en donde encuentra el tramo.
		return getStretchs().indexOf(stretchsWithOrigin.stream().findFirst().get());

	}

	public Terminal originTerminal() {
		return getStretchs().get(0).getOrigin();
	}

}
