package com.pristonit.application.usecase;

import java.util.List;

public record EPage<T>(int page, int requestedSize, int totalPages, List<T> items) {

	public int size() {
		return items.size();
	}
}
