package com.cagdasyilmaz.couriertrackerservice.exception.handler;

import com.cagdasyilmaz.couriertrackerservice.courier.exception.CourierAlreadyEmployedException;
import com.cagdasyilmaz.couriertrackerservice.courier.exception.CourierNotFoundException;
import com.cagdasyilmaz.couriertrackerservice.store.exception.StoreAlreadyFunctionalException;
import com.cagdasyilmaz.couriertrackerservice.store.exception.StoreNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CourierTrackerExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler({CourierAlreadyEmployedException.class, StoreAlreadyFunctionalException.class})
	public ResponseEntity<Object> handleExistingCurrencyException(RuntimeException exception, WebRequest webRequest) {
		return handleExceptionInternal(exception, exception.getMessage(), new HttpHeaders(),
			HttpStatus.CONFLICT, webRequest);
	}

	@ExceptionHandler({CourierNotFoundException.class, StoreNotFoundException.class})
	public ResponseEntity<Object> handleNoSuchObjectExceptions(RuntimeException exception, WebRequest webRequest) {
		return handleExceptionInternal(exception, exception.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, webRequest);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
		MethodArgumentNotValidException exception,
		HttpHeaders headers,
		HttpStatus status,
		WebRequest request) {

		String bodyOfResponse = exception.getMessage();
		return new ResponseEntity(bodyOfResponse, headers, status);
	}
}
