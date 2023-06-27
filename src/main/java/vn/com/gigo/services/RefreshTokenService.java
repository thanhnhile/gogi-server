package vn.com.gigo.services;

import vn.com.gigo.entities.RefreshToken;

public interface RefreshTokenService {
	RefreshToken findByToken(String refreshToken);
	
	RefreshToken createRefreshToken(Long userId);
	
	RefreshToken verifyExpiration(RefreshToken token);
	
	int deleteByUserId(Long userId);
}
