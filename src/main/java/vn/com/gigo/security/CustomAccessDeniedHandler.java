package vn.com.gigo.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		// Set the response status code
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

		// Set the response content type
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);

		// Create a JSON response body with additional information
		String responseBody = "{\"errMsg\": \"Access denied\",\n\"errCode\":\"401\",\n\"status\": 401,\n"
				+ "    \"data\": null}";

		// Write the response body to the output stream
		response.getWriter().write(responseBody);

	}

}
