package com.pristonit.domain.exception;


public class NoSuchProductException extends DomainIllegalStateException {
	
	String productId;

	public NoSuchProductException(String productId) {
		this.productId = productId;
	}

	public String getProductId() {
		return productId;
	}
}
