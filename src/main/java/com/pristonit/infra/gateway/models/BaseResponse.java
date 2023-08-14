package com.pristonit.infra.gateway.models;

@com.fasterxml.jackson.annotation.JsonInclude(com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL)
public class BaseResponse<T> {

  @com.fasterxml.jackson.annotation.JsonProperty("data")
  public T data;
  @com.fasterxml.jackson.annotation.JsonProperty("message")
  public String message;
  @com.fasterxml.jackson.annotation.JsonProperty("errors")
  public java.util.List<Error> errors;
  @com.fasterxml.jackson.annotation.JsonProperty("status")
  public boolean status;

  public BaseResponse(String message, T data, boolean status) {
    this.message = message;
    this.data = data;
    this.status = status;
  }

  public BaseResponse(T data, String message, java.util.List<Error> errors, boolean status) {
    this.data = data;
    this.message = message;
    this.errors = errors;
    this.status = status;
  }

  public static <T> com.pristonit.infra.gateway.models.BaseResponse<T> success(String message, T data) {
    return new com.pristonit.infra.gateway.models.BaseResponse<>(message, data, true);
  }

  public static <T> com.pristonit.infra.gateway.models.BaseResponse<T> success(String message) {
    return new com.pristonit.infra.gateway.models.BaseResponse<>(message, null, true);
  }

  public static <T> com.pristonit.infra.gateway.models.BaseResponse<T> fail(String reason) {
    return new com.pristonit.infra.gateway.models.BaseResponse<>(reason, null, false);
  }
}
