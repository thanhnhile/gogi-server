package vn.com.gigo.controllers;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.com.gigo.dtos.DataResponse;
import vn.com.gigo.dtos.request.AccountDto;
import vn.com.gigo.security.JwtTokenUtil;
import vn.com.gigo.services.impl.AccountServiceImpl;

@RestController
@RequestMapping("/accounts")
public class AccountController {
	@Autowired
	private AccountServiceImpl accountImpl;

	@Autowired
	AuthenticationManager authManager;

	@Autowired
	JwtTokenUtil jwtUtil;

	@Autowired
	JavaMailSender mailSender;

	@GetMapping()
	public DataResponse getAccount() {
		return new DataResponse(accountImpl.getAllAccount());
	}

	@GetMapping("/available")
	public DataResponse getAvailableAccount() {
		return new DataResponse(accountImpl.getAvailableAccount());
	}
	
	@GetMapping("/{id}")
	public DataResponse getAccount(@PathVariable(value = "id") Long id) {
		return new DataResponse(accountImpl.getAccount(id));
	}

	@GetMapping("/customers")
	public DataResponse getCustomerInfoByUsername () {
		return new DataResponse(accountImpl.getCustomerInfoByUserName());
	}
	@GetMapping("/customers/default")
	public DataResponse getDefaultCustomerInfo() {
		return new DataResponse(accountImpl.getCustomerInfoDefault());
	}
	
	@PutMapping("/customers/default/{id}")
	private DataResponse updateDefaultCustomerInfo(@PathVariable(value="id") Long id) {
		return new DataResponse(accountImpl.updateDefaultCustomerInfo(id));
	}

	@PostMapping()
	public DataResponse addAccount(@RequestBody AccountDto accountDto) {
		return new DataResponse(accountImpl.addAccount(accountDto));
	}

	@DeleteMapping("/{id}")
	public void deleteAccount(@PathVariable(value = "id") Long id) {
		accountImpl.deleteAccount(id);
	}

//	@PutMapping("/{id}")
//	public DataResponse updateAccount(@PathVariable(value = "id") Long id, @RequestBody AccountDto accountDto) {
//		return new DataResponse(accountImpl.updateAccount(id, accountDto));
//	}

	@PostMapping("/forgot_password/{email}")
	public DataResponse processForgotPassword(@PathVariable(value = "email") String email) throws UnsupportedEncodingException, MessagingException {
		String token = accountImpl.updateToken(email);
		String resetPasswordLink = "http://localhost:3000/resetPassword/" + token;
		sendEmail(email, resetPasswordLink);
		return new DataResponse(token);
	}

	public void sendEmail(String recipientEmail, String link) throws MessagingException, UnsupportedEncodingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		helper.setFrom("thanhhai6398@gmail.com", "Shopme Support");
		helper.setTo(recipientEmail);

		String subject = "Here's the link to reset your password";

		String content = "<p>Hello,</p>" + "<p>You have requested to reset your password.</p>"
				+ "<p>Click the link below to change your password:</p>" + "<p><a href=\"" + link
				+ "\">Change my password</a></p>" + "<br>" + "<p>Ignore this email if you do remember your password, "
				+ "or you have not made the request.</p>";

		helper.setSubject(subject);

		helper.setText(content, true);

		mailSender.send(message);
	}
	
	@PutMapping("/reset_password/{token}")
	public DataResponse processResetPassword(@PathVariable(value = "token") String token, @RequestBody AccountDto accountDto) {
	     
	    return new DataResponse(accountImpl.resetPassword(token, accountDto));
	    
	}
	
	@GetMapping("/productsLiked")
	public DataResponse getProductsLikedByUsername () {
		return new DataResponse(accountImpl.getAllProductsLiked());
	}
}
