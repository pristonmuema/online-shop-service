package com.pristonit.domain.exception;


public class NoSuchCategoryException extends DomainIllegalStateException {

	int categoryId;

	public NoSuchCategoryException(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getCategoryId() {
		return categoryId;
	}
}
