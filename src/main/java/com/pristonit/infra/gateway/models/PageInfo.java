package com.pristonit.infra.gateway.models;

public class PageInfo {

  int pageIdx;
  int totalPages;
  long totalItems;
  long pageSize;
  int requestedPageSize;

  public PageInfo(int pageIdx, int totalPages, long totalItems, long pageSize,
                  int requestedPageSize) {
    this.pageIdx = pageIdx;
    this.totalPages = totalPages;
    this.totalItems = totalItems;
    this.pageSize = pageSize;
    this.requestedPageSize = requestedPageSize;
  }

  public int getPageIdx() {
    return pageIdx;
  }

  public int getTotalPages() {
    return totalPages;
  }

  public long getTotalItems() {
    return totalItems;
  }

  public long getPageSize() {
    return pageSize;
  }

  public int getRequestedPageSize() {
    return requestedPageSize;
  }
}
