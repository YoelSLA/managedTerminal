package search.selection.selectionDate;

import java.time.LocalDateTime;

import search.criteria.Criteria;
import search.selection.Selection;
import terminal.Terminal;

public abstract class SelectionDate extends Selection {

	private Criteria criteria;
	private LocalDateTime dateForSearch;

	public SelectionDate(Criteria criteria, LocalDateTime dateForSearch, Terminal terminal) {
		super(terminal);
		this.criteria = criteria;
		this.dateForSearch = dateForSearch;
	}

	public Criteria getCriteria() {
		return criteria;
	}

	public LocalDateTime getDateForSearch() {
		return dateForSearch;
	}

	protected boolean searchByCriteriaTo(LocalDateTime date) {
		switch (criteria) {
		case GREATHER_THAN:
			return this.dateForSearch.isBefore(date);
		case EQUALS:
			return this.dateForSearch.equals(date);
		default:
			return this.dateForSearch.isAfter(date);
		}
	}

	public void setCriteria(Criteria criteria) {
		this.criteria = criteria;
	}

}
