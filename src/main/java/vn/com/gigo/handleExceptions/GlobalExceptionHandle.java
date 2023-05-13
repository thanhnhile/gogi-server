package vn.com.gigo.handleExceptions;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import vn.com.gigo.exception.DuplicateValueInResourceException;
import vn.com.gigo.exception.ResourceNotFoundException;
import vn.com.gigo.dtos.DataResponse;

@ControllerAdvice
public class GlobalExceptionHandle {
	
	@ExceptionHandler(RuntimeException.class)
	@ResponseBody
	public DataResponse handleRuntimeException(RuntimeException e) {
		return new DataResponse("500", e.getMessage(), 200);
	}

	@ExceptionHandler(Exception.class)
	@ResponseBody
	public DataResponse handle(Exception ex, HttpServletRequest request, HttpServletResponse response) {
		if (ex instanceof NullPointerException) {
			return new DataResponse("400", ex.getMessage(), 200);
		}
		return new DataResponse("500", ex.getMessage(), 200);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	@ResponseBody
	public DataResponse handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
		return new DataResponse("404", e.getMessage(), 200);
	}

	@ExceptionHandler(SQLException.class)
	@ResponseBody
	public DataResponse handleSqlException(SQLException e) {
		return new DataResponse("500", e.getMessage(), 200);
	}

	@ExceptionHandler({ DuplicateValueInResourceException.class })
	@ResponseBody
	public DataResponse handleDuplicateValueInResourceException(DuplicateValueInResourceException e) {
		return new DataResponse("400", e.getMessage(), 200);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseBody
	public DataResponse handleResourceNotFoundException(ResourceNotFoundException e) {
		return new DataResponse("404", e.getMessage(), 200);
	}

//	@ExceptionHandler({ ExpiredJwtException.class, SignatureException.class, IllegalArgumentException.class,
//			MalformedJwtException.class, UnsupportedJwtException.class })
//	@ResponseBody
//	public DataResponse handleJWTException(JwtException e) {
//		return new DataResponse("403", e.getMessage(), 200);
//	}
//	
}
