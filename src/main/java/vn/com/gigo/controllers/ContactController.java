package vn.com.gigo.controllers;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.com.gigo.dtos.DataResponse;
import vn.com.gigo.dtos.request.FeedBackRequestDto;

@RestController
@RequestMapping("/sendFeedback")
public class ContactController {
	@Autowired
	JavaMailSender mailSender;

	@PostMapping
	public DataResponse sendFeedback(@RequestBody FeedBackRequestDto feedBackInputDto)
			throws MessagingException, UnsupportedEncodingException {
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message);

			helper.setFrom("thanhhai6398@gmail.com", feedBackInputDto.getFullName());
			
			helper.setTo("thanhhai6398@gmail.com");

			String subject = "Feedback from " + feedBackInputDto.getFullName();

			helper.setSubject(subject);

			String content = "<h2> From " + feedBackInputDto.getEmail() + "</h2></br><p>"
					+ feedBackInputDto.getContent() + "</p>";

			helper.setText(content, true);

			mailSender.send(message);
			return new DataResponse("Sent email");
		} catch (Exception e) {
			return new DataResponse("500", e.getMessage(), 200);
		}

	}
}
