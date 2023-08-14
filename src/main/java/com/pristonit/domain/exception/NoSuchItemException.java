package com.pristonit.domain.exception;


public class NoSuchItemException extends DomainIllegalStateException {

	final String itemId;

	public NoSuchItemException(String itemId) {
		this.itemId = itemId;
	}

	public String getItemId() {
		return itemId;
	}
}
