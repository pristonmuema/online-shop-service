package com.pristonit.utils;

@jakarta.enterprise.context.ApplicationScoped
public class ModelUtil {

  @jakarta.inject.Inject
  com.fasterxml.jackson.databind.ObjectMapper mapper;

  /**
   * Default constructor.
   */
  public ModelUtil() {
  }

  public String modelToString(Object object) {
    try {
      return mapper.writeValueAsString(object);
    } catch (com.fasterxml.jackson.core.JsonProcessingException e) {
      return object.toString();
    }
  }

}
