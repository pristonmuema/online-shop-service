package com.pristonit.infra.gateway.http.exceptions;

import com.pristonit.domain.exception.NoSuchItemException;
import com.pristonit.infra.gateway.models.BaseResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

@ApplicationScoped
public class NoSuchCategoryExceptionMapper {

	@ServerExceptionMapper
	public <T> RestResponse<BaseResponse<T>> mapException(NoSuchItemException x) {
		String message = String.format("Item not found, %d", x.getItemId());
		BaseResponse<T> baseResponse = new BaseResponse<>(message, null, false);
		return RestResponse.status(Response.Status.NOT_FOUND, baseResponse);
	}
}
