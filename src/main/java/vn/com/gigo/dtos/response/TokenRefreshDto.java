package vn.com.gigo.dtos.response;

public class TokenRefreshDto {
	private String accessToken;

	public TokenRefreshDto(String accessToken) {
		super();
		this.accessToken = accessToken;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

}
