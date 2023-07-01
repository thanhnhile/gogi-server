package vn.com.gigo.services.impl;

import java.time.Instant;
import java.util.UUID;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.com.gigo.entities.RefreshToken;
import vn.com.gigo.exception.ResourceNotFoundException;
import vn.com.gigo.exception.TokenRefreshException;
import vn.com.gigo.repositories.AccountRepository;
import vn.com.gigo.repositories.RefreshTokenRepository;
import vn.com.gigo.services.RefreshTokenService;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {
	@Autowired
	private RefreshTokenRepository refreshTokenRepo;

	@Autowired
	private AccountRepository accountRepo;

	private static final long EXPIRE_DURATION = 24 * 60 * 60 * 1000 * 1; // 1 week

	@Override
	public RefreshToken findByToken(String refreshToken) {
		return refreshTokenRepo.findByToken(refreshToken)
				.orElseThrow(() -> new ResourceNotFoundException("Refresh token does not exist"));
	}

	@Override
	public RefreshToken createRefreshToken(Long userId) {
		//delete old token
		deleteByUserId(userId);
		RefreshToken refreshToken = new RefreshToken();
		refreshToken.setAccount(accountRepo.findOneById(userId));
		refreshToken.setExpiryDate(Instant.now().plusMillis(EXPIRE_DURATION));
		refreshToken.setToken(UUID.randomUUID().toString());
		//save new token
		refreshToken = refreshTokenRepo.save(refreshToken);
		return refreshToken;
	}

	@Override
	public RefreshToken verifyExpiration(RefreshToken token) {
		if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
			refreshTokenRepo.delete(token);
			throw new TokenRefreshException(token.getToken(),
					"Refresh token was expired. Please make a new signin request");
		}

		return token;
	}

	@Override
	@Transactional
	public int deleteByUserId(Long userId) {
		return refreshTokenRepo.deleteByAccount(accountRepo.findOneById(userId));
	}

}
