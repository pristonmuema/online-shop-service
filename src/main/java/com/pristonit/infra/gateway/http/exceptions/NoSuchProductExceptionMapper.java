package com.pristonit.infra.gateway.http.exceptions;

import com.pristonit.domain.exception.NoSuchProductException;
import com.pristonit.infra.gateway.models.BaseResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

@ApplicationScoped
public class NoSuchProductExceptionMapper {

	@ServerExceptionMapper
	public <T> RestResponse<BaseResponse<T>> mapException(NoSuchProductException x) {
		String message = String.format("Product not found, %s", x.getProductId());
		BaseResponse<T> baseResponse = new BaseResponse<>(message, null, false);
		return RestResponse.status(Response.Status.NOT_FOUND, baseResponse);
	}
}
