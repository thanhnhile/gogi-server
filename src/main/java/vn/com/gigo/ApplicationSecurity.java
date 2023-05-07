package vn.com.gigo;

import java.util.Arrays;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import vn.com.gigo.repositories.AccountRepository;
import vn.com.gigo.security.JwtTokenFilter;
import vn.com.gigo.utils.RoleType;

@Configuration
@EnableWebSecurity(debug = true)
@RestController
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
@CrossOrigin(value = "http://localhost:3000")
public class ApplicationSecurity {

	@Autowired
	private AccountRepository accountRepo;

	@Autowired
	private JwtTokenFilter jwtTokenFilter;

	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsService() {

			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				return accountRepo.findByUsername(username)
						.orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found"));
			}
		};
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}

	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http.cors().and();
		http.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.authorizeRequests().antMatchers("/auth", "/register", "/employees/account/**","/subscribe/**").permitAll()
		.antMatchers(HttpMethod.GET, "/categories/**", "/products/**", "/stores/**").permitAll()
		.anyRequest().authenticated();

//		http.authorizeRequests().antMatchers(HttpMethod.POST, "/categories/**", "/products/**", "/stores/**")
//				.hasAuthority("ADMIN").antMatchers(HttpMethod.DELETE, "/categories/**", "/products/**", "/stores/**")
//				.hasAuthority("ADMIN").antMatchers(HttpMethod.PUT, "/categories/**", "/products/**", "/stores/**")
//				.hasAuthority("ADMIN");
//
//		// role employee
//		http.authorizeRequests().antMatchers("/orders/update/delivering/{id}", "/orders/update/delivering/{id}",
//				"/orders/update/success/{id}").hasAuthority("EMPLOYEE");
//
//		http.authorizeRequests().antMatchers(HttpMethod.GET, "/categories/**", "/products/**", "/stores/**").permitAll()
//				.anyRequest().authenticated();

		http.exceptionHandling().authenticationEntryPoint((request, response, ex) -> {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage());
		});

		http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
		configuration.addAllowedHeader("*");
		configuration.addAllowedOrigin("*");
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

}
