package com.pristonit.infra.gateway.http.exceptions;

import com.pristonit.domain.exception.DomainIllegalStateException;
import com.pristonit.infra.gateway.models.BaseResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import jakarta.ws.rs.core.Response;
import java.sql.SQLIntegrityConstraintViolationException;
import org.jboss.logging.Logger;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

@ApplicationScoped
public class ExceptionMappers {

	private static final Logger logger = Logger.getLogger(ExceptionMappers.class);

	@ServerExceptionMapper
	public <T> RestResponse<BaseResponse<T>> mapException(DomainIllegalStateException x) {
		String message = x.getMessage();
		BaseResponse<T> baseResponse = new BaseResponse<>(message, null, false);
		return RestResponse.status(Response.Status.BAD_REQUEST, baseResponse);
	}


	@ServerExceptionMapper
	public <T> RestResponse<BaseResponse<T>> mapException(RuntimeException x) {
		logger.info(x.toString());
		String message = x.getMessage();
		BaseResponse<T> baseResponse = new BaseResponse<>(message, null, false);
		return RestResponse.status(Response.Status.INTERNAL_SERVER_ERROR, baseResponse);
	}

	@ServerExceptionMapper
	public <T> RestResponse<BaseResponse<T>> mapException(
			SQLIntegrityConstraintViolationException x) {
		logger.info(x.toString());
		String message = x.getMessage();
		BaseResponse<T> baseResponse = new BaseResponse<>(message, null, false);
		return RestResponse.status(Response.Status.INTERNAL_SERVER_ERROR, baseResponse);
	}

	@ServerExceptionMapper
	public <T> RestResponse<BaseResponse<T>> mapException(ValidationException x) {
		String message = x.getMessage();
		BaseResponse<T> baseResponse = new BaseResponse<>(message, null, false);
		return RestResponse.status(Response.Status.BAD_REQUEST, baseResponse);
	}

	@ServerExceptionMapper
	public <T> RestResponse<BaseResponse<T>> mapException(ConstraintViolationException x) {
		String message = x.getMessage();
		BaseResponse<T> baseResponse = new BaseResponse<>(message, null, false);
		return RestResponse.status(Response.Status.BAD_REQUEST, baseResponse);
	}

}
