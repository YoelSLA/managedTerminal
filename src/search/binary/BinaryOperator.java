package search.binary;

import search.Search;

public abstract class BinaryOperator implements Search {

	private BinaryOperator leftClause;
	private BinaryOperator rightClause;

	public BinaryOperator(BinaryOperator leftClause, BinaryOperator rightClause) {
		this.leftClause = leftClause;
		this.rightClause = rightClause;
	}

	public BinaryOperator getLeftClause() {
		return leftClause;
	}

	public BinaryOperator getRightClause() {
		return rightClause;
	}
}
