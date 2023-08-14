package com.pristonit.domain.exception;


public class NoSuchItemException extends DomainIllegalStateException {

	final int itemId;

	public NoSuchItemException(int itemId) {
		this.itemId = itemId;
	}

	public int getItemId() {
		return itemId;
	}
}
