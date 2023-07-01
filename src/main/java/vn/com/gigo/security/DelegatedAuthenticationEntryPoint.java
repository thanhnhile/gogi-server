package vn.com.gigo.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class DelegatedAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		// Set the response status code
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);

		// Set the response content type
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);

		// Create a JSON response body with additional information
		String responseBody = "{\"errMsg\": \"Expired or Invalid JWT token\",\n\"errCode\":\"403\",\n\"status\": 403,\n"
				+ "    \"data\": null}";

		// Write the response body to the output stream
		response.getWriter().write(responseBody);
	}

}
