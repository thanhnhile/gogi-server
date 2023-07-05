package vn.com.gigo.security;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import vn.com.gigo.exception.ResourceNotFoundException;

public class SecurityUtils {
	public static String getLoggedUsername() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String currentUserName = authentication.getName();
			return currentUserName;
		}
		throw new ResourceNotFoundException("User does not exist");
	}
}
