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
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import vn.com.gigo.repositories.AccountRepository;
import vn.com.gigo.security.CustomAccessDeniedHandler;
import vn.com.gigo.security.DelegatedAuthenticationEntryPoint;
import vn.com.gigo.security.JwtTokenFilter;

@Configuration
@EnableWebSecurity(debug = true)
@RestController
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
@CrossOrigin(value = "http://localhost:3000", allowCredentials = "true")
public class ApplicationSecurity {

	@Autowired
	private AccountRepository accountRepo;

	@Autowired
	private JwtTokenFilter jwtTokenFilter;
	
	@Autowired
	private DelegatedAuthenticationEntryPoint delegatedAuthenticationEntryPoint;

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

        
		// role admin
		http.authorizeRequests()
				.antMatchers(HttpMethod.POST, "/categories/**", "/products/**", "/stores/**", "/vouchers/**",
						"/toppings/**")
				.hasAnyAuthority("ADMIN")
				.antMatchers(HttpMethod.PUT, "/categories/**", "/products/**", "/stores/**", "/vouchers/**",
						"/toppings/**")
				.hasAnyAuthority("ADMIN")
				.antMatchers(HttpMethod.DELETE, "/categories/**", "/products/**", "/stores/**", "/vouchers/**",
						"/toppings/**", "/accounts")
				.hasAnyAuthority("ADMIN").antMatchers("/roles", "/statistics/admin", "/employees")
				.hasAnyAuthority("ADMIN");

		// role employee
		http.authorizeRequests()
				.antMatchers("/orders/update/delivering/{id}", "/orders/update/success/{id}", "/orders/pay/**")
				.hasAuthority("EMPLOYEE");

		http.authorizeRequests()
				.antMatchers("/auth", "/register", "/refresh", "/employees/account/**", "/subscribe/**",
						"/accounts/forgot_password/**", "/accounts/reset_password/**", "/sendFeedback")
				.permitAll()
				.antMatchers(HttpMethod.GET, "/categories/**", "/products/**", "/stores/**", "/vouchers/**",
						"/rates/**", "/toppings/**")
				.permitAll().antMatchers(HttpMethod.POST, "/orders").permitAll().anyRequest().authenticated();

		// configure access denied handler
		http.exceptionHandling().accessDeniedHandler(accessDeniedHandler());
		
		// configure access token exception handler
        http.exceptionHandling().authenticationEntryPoint(delegatedAuthenticationEntryPoint);

		http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}
	
	public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
		configuration.setAllowCredentials(true);
		configuration.addAllowedHeader("*");
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

}
