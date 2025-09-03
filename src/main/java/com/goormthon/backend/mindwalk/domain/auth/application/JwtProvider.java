package com.goormthon.backend.mindwalk.domain.auth.application;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.goormthon.backend.mindwalk.domain.auth.dto.AuthResponse;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtProvider {

	private final RedisTokenService redisTokenService;
	private final SecretKey secretKey;

	@Value("${jwt.accessTokenExpiry}")
	private long accessTokenExpiry;

	@Value("${jwt.refreshTokenExpiry}")
	private long refreshTokenExpiry;

	public AuthResponse issueToken(Long userId) {
		String accessToken = generateAccessToken(userId);
		String refreshToken = generateRefreshToken(userId);

		redisTokenService.saveRefreshToken(
			userId,
			refreshToken,
			refreshTokenExpiry,
			TimeUnit.MILLISECONDS
		);
		return new AuthResponse(userId, accessToken, refreshToken);
	}

	public String generateAccessToken(Long userId) {
		return Jwts.builder()
			.subject(String.valueOf(userId))
			.expiration(new Date(System.currentTimeMillis() + accessTokenExpiry))
			.signWith(secretKey)
			.compact();
	}

	public String generateRefreshToken(Long userId) {
		return Jwts.builder()
			.subject(String.valueOf(userId))
			.expiration(new Date(System.currentTimeMillis() + refreshTokenExpiry))
			.signWith(secretKey)
			.compact();
	}

	public Long extractUserIdFromToken(String token) {
		String subject = parseToken(token).getSubject();
		return Long.parseLong(subject);
	}

	private Claims parseToken(String token) {
		return Jwts.parser()
			.verifyWith(secretKey)
			.build()
			.parseSignedClaims(token)
			.getPayload();
	}
}
