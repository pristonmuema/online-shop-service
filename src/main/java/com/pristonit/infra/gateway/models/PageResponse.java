package com.pristonit.infra.gateway.models;

public class PageResponse<T> extends BaseResponse<java.util.List<T>> {

  @com.fasterxml.jackson.annotation.JsonProperty("pagination")
  PageInfo pagination;

  public PageResponse(String message) {
    super(message, null, false);
  }

  public PageResponse(String message, java.util.List<T> data, PageInfo pagination) {
    super(message, data, true);
    this.pagination = pagination;
  }

  public PageResponse(java.util.List<T> data, String message, java.util.List<Error> errors,
                      PageInfo pagination) {
    super(data, message, errors, false);
    this.pagination = pagination;
  }

  public static <R> com.pristonit.infra.gateway.models.PageResponse<R> success(java.util.List<R> data, PageInfo pageInfo) {
    return new com.pristonit.infra.gateway.models.PageResponse<>("success", data, pageInfo);
  }
}
