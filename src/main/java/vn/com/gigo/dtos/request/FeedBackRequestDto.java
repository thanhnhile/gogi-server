package vn.com.gigo.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FeedBackRequestDto {
	@JsonProperty("fullname")
	String fullName;
	
	@JsonProperty("email")
	String email;
	
	@JsonProperty("content")
	String content;

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
}
