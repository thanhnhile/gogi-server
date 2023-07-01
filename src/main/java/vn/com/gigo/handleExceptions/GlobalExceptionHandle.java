package vn.com.gigo.handleExceptions;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import vn.com.gigo.exception.DuplicateValueInResourceException;
import vn.com.gigo.exception.ResourceNotFoundException;
import vn.com.gigo.exception.TokenRefreshException;
import vn.com.gigo.dtos.DataResponse;

@ControllerAdvice
public class GlobalExceptionHandle {

	@ExceptionHandler(RuntimeException.class)
	@ResponseBody
	public ResponseEntity<DataResponse> handleRuntimeException(RuntimeException e) {
		DataResponse dataResponse = new DataResponse("500", e.getMessage(), 500);
		return new ResponseEntity<DataResponse>(dataResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(Exception.class)
	@ResponseBody
	public ResponseEntity<DataResponse> handle(Exception e, HttpServletRequest request, HttpServletResponse response) {
		if (e instanceof NullPointerException) {
			DataResponse dataResponse = new DataResponse("400", e.getMessage(), 400);
			return new ResponseEntity<DataResponse>(dataResponse, HttpStatus.BAD_REQUEST);
		}
		DataResponse dataResponse = new DataResponse("500", e.getMessage(), 500);
		return new ResponseEntity<DataResponse>(dataResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	@ResponseBody
	public ResponseEntity<DataResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
		DataResponse dataResponse = new DataResponse("404", e.getMessage(), 404);
		return new ResponseEntity<DataResponse>(dataResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(SQLException.class)
	@ResponseBody
	public ResponseEntity<DataResponse> handleSqlException(SQLException e) {
		DataResponse dataResponse = new DataResponse("500", e.getMessage(), 500);
		return new ResponseEntity<DataResponse>(dataResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler({ DuplicateValueInResourceException.class })
	@ResponseBody
	public ResponseEntity<DataResponse> handleDuplicateValueInResourceException(DuplicateValueInResourceException e) {
		DataResponse dataResponse = new  DataResponse("422", e.getMessage(), 422);
		return new ResponseEntity<DataResponse>(dataResponse, HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseBody
	public ResponseEntity<DataResponse> handleResourceNotFoundException(ResourceNotFoundException e) {
		DataResponse dataResponse = new DataResponse("404", e.getMessage(), 404);
		return new ResponseEntity<DataResponse>(dataResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(TokenRefreshException.class)
	@ResponseBody
	public ResponseEntity<DataResponse> handleRefreshTokenException(TokenRefreshException e) {
		DataResponse dataResponse = new DataResponse("403", e.getMessage(), 403);
		return new ResponseEntity<DataResponse>(dataResponse, HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<DataResponse> handleAuthenticationException(AuthenticationException ex) {
        String errorMessage;
        if (ex instanceof BadCredentialsException) {
            errorMessage = "Sai tài khoản hoặc mật khẩu";
        } else if (ex instanceof UsernameNotFoundException) {
            errorMessage = "Tài khoản không tồn tại";
        } else {
            errorMessage = "Đăng nhập không thành công. Vui lòng thử lại!";
        }

        DataResponse dataResponse = new DataResponse("401", errorMessage, 401);
		return new ResponseEntity<DataResponse>(dataResponse, HttpStatus.UNAUTHORIZED);
    }
}
