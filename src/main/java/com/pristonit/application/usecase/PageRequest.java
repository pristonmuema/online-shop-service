package com.pristonit.application.usecase;

public record PageRequest(int page, int size) {

	public com.pristonit.application.usecase.PageRequest normalise() {
		int pg = Math.max(page, 0);
		int sze = size < 10 ? 20 : size;
		return new com.pristonit.application.usecase.PageRequest(pg, sze);
	}
}
