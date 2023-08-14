package com.pristonit.infra.gateway.http.exceptions;

import com.pristonit.domain.exception.NoSuchCategoryException;
import com.pristonit.infra.gateway.models.BaseResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

@ApplicationScoped
public class NoSuchItemExceptionMapper {

	@ServerExceptionMapper
	public <T> RestResponse<BaseResponse<T>> mapException(NoSuchCategoryException x) {
		String message = String.format("Category not found, %d", x.getCategoryId());
		BaseResponse<T> baseResponse = new BaseResponse<>(message, null, false);
		return RestResponse.status(Response.Status.NOT_FOUND, baseResponse);
	}
}
