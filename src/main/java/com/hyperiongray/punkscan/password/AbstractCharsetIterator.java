package com.hyperiongray.punkscan.password;

public abstract class AbstractCharsetIterator {

	protected final char[] charset;
	protected final int passwordLength;
	protected char[][] parentCharSet; // array of arrays of valid characters
	protected int[] metaPositions; // the position in the char sequence for each
									// char of
									// protected int[] nextMetaPositions; // the
									// position in the char sequence for each
									// protected Boolean _hasNext = true;
	private boolean endReached = false;
	private boolean endConsumed = false;
	private boolean boolean_isLast;

	public AbstractCharsetIterator(char[] charset, int passwordLength) {
		this.charset = charset;
		this.passwordLength = passwordLength;

		// loads the arrays of the valid charsequences
		parentCharSet = new char[passwordLength][];
		for (int i = 0; i < passwordLength; i++) {
			parentCharSet[i] = charset;
		}
		metaPositions = new int[passwordLength];
	}

	public int getTotalSize() {
		int totalSize = 1;
		for (int i = 0; i < metaPositions.length; i++) {
			totalSize = totalSize * (parentCharSet[i].length);
		}
		return totalSize;
	}

	public void initialize() {
		setMetapositions();
	}

	public boolean hasNext() {
		return !endConsumed;
	}

	public char[] next() {
		return this.next(1);
	}

	public char[] next(int increment) {
		return next(1, false);
	}

	public char[] nextBigStep() {
		return this.next(1, true);
	}

	public char[] next(int increment, boolean bigstep) {
		char[] output = getCurrentCharset();
		if (isLast()) {
			endConsumed = true;
		} else {
			for (int i = 0; i < increment && hasNext(); i++) {
				if (bigstep) {
					moveBigStep();
				} else {
					moveNext();
				}
			}
		}
		return output;
	}

	private void moveBigStep() {
		int dimension;
		dimension = passwordLength - 6;
		moveNext(dimension);
	}

	private void moveNext() {
		moveNext(passwordLength - 1);
	}

	private void moveNext(int dimension) {

		if (isLast()) {
			return;
		}

		for (int i = dimension; i >= 0; i--) {
			if (metaPositions[i] < parentCharSet[i].length - 1) {
				metaPositions[i] = metaPositions[i] + 1; // move one
				return;
			} else {
				metaPositions[i] = 0; // reset and move the previous char
			}
		}
		setMetapositionsToLast();
	}

	private boolean isLast() {
		for (int i = 0; i <= metaPositions.length - 1; i++) {
			if (metaPositions[i] != parentCharSet[i].length - 1) {
				return false;
			}
		}
		return true;
	}

	protected abstract void setMetapositions();

	public void setMetapositionsToFirst() {
		int[] metaPositions = new int[passwordLength];
		for (int i = 0; i < passwordLength; i++) {
			metaPositions[i] = 0;
		}
	}

	public void setMetapositionsToLast() {
		for (int i = 0; i < passwordLength; i++) {
			metaPositions[i] = parentCharSet[i].length - 1;
		}
	}

	private char[] metaPosition2Charset() {
		char[] output = new char[passwordLength];
		for (int i = 0; i <= metaPositions.length - 1; i++) {
			output[i] = parentCharSet[i][metaPositions[i]];
		}
		return output;
	}

	public char[] getCurrentCharset() {
		return metaPosition2Charset();
	}

}
