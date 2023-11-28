package maritimeCircuit;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

import stretch.Stretch;
import terminal.Terminal;

public class MaritimeCircuit {

	private List<Stretch> stretchs;

	public MaritimeCircuit(List<Stretch> stretchs) {
		this.stretchs = stretchs;
	}

	public Integer calculateTimeBetween(Terminal origin, Terminal destiny) {
		Long totalNanos = getStretchs().subList(getPositionOf(origin), getPositionOf(destiny)).stream()
				.mapToLong(s -> s.getTime().toNanos()).sum();
		return (int) Math.round(totalNanos / (double) Duration.ofHours(1).toNanos());
	}

	public Integer getPositionOf(Terminal terminal) {
		Optional<Stretch> stretch = stretchs.stream().filter(s -> s.getOrigin().equals(terminal)).findFirst();
		return stretch.map(s -> stretchs.indexOf(s)).orElse(-1);
	}

	public Double getPrice() {
		return getStretchs().stream().mapToDouble(Stretch::getPrice).sum();
	}

	public List<Stretch> getStretchs() {
		return stretchs;
	}

	public boolean hasADestinyTerminal(Terminal destiny) {
		return stretchs.stream().anyMatch(s -> s.getDestiny().equals(destiny));
	}

	public Terminal originTerminal() {
		return getStretchs().get(0).getOrigin();
	}

}
