package maritimeCircuit;

import java.util.List;
import java.util.Optional;

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

	private Optional<Integer> getPositionOfOriginInCircuit(Terminal origin) {
		int index = getStretchs()
				.indexOf(getStretchs().stream().filter(s -> s.getOrigin().equals(origin)).findFirst().orElse(null));
		return (index != -1) ? Optional.of(index) : Optional.empty();
	}

	private Optional<Integer> getPositionOfDestinyInCircuit(Terminal destiny) {
		int index = getStretchs()
				.indexOf(getStretchs().stream().filter(s -> s.getDestiny().equals(destiny)).findFirst().orElse(null));
		return (index != -1) ? Optional.of(index) : Optional.empty();
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
		System.out.println("Checking for destiny terminal: " + destiny);
		return getStretchs().stream().anyMatch(s -> {
			boolean result = s.getDestiny().equals(destiny);
			System.out.println("Stretch: " + s + ", Result: " + result);
			return result;
		});
	}

	public Integer calculateTimeBetween(Terminal origin, Terminal destiny) {
		System.out.println("origin: " + origin);
		System.out.println("destiny: " + destiny);

		final Optional<Integer> originPosition = getPositionOfOriginInCircuit(origin);
		final Optional<Integer> destinyPosition = getPositionOfDestinyInCircuit(destiny);

		System.out.println("PositionOrigin: " + originPosition);
		System.out.println("PositionDestiny: " + destinyPosition);

		int originPositionValue = originPosition.orElse(-1);
		int destinyPositionValue = destinyPosition.orElse(-1);

		if (originPositionValue != -1 && destinyPositionValue != -1) {
			return getStretchs().subList(originPositionValue, destinyPositionValue).stream()
					.mapToInt(s -> (int) s.getTime().toSeconds()).sum();
		} else {
			// Manejar el caso en el que uno o ambos índices no son válidos.
			return -1; // O cualquier valor que desees usar para indicar que la operación no fue
						// exitosa
		}
	}

//	private Double calculateTimeBetween(ManagedTerminal origin, Terminal destiny) {
//		final Optional<Integer> originPosition = getPositionOfOriginInCircuit(origin);
//		final Optional<Integer> destinyPosition = getPositionOfDestinyInCircuit(destiny);
//
//		return getStretchs().subList(originPosition, destinyPosition).stream().mapToDouble(s -> s.getTime().toSeconds())
//				.sum();
//
//	}

}
