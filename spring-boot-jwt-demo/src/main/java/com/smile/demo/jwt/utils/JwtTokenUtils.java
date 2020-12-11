package com.smile.demo.jwt.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.smile.demo.jwt.entity.Token;
import com.smile.demo.jwt.exception.TokenException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;

@Component
public class JwtTokenUtils {

	@Value("${demo.user.username}")
	private String defaultUsername;
	@Value("${demo.user.password}")
	private String defaultPassword;

	/**
	 * 过期时间，单位毫秒
	 * - 10分钟
	 */
	private static final long EXPIRE_TIME = 1000 * 60 * 10;

	public Token createToken(String username, String password) throws TokenException {
		if (!username.equals(defaultUsername) || !password.equals(defaultPassword)) {
			throw new TokenException("用户名或密码不正确");
		}
		//设置头信息
		HashMap<String, Object> header = new HashMap<>(2);
		header.put("typ", "JWT");
		header.put("alg", "HMAC256");
		long expireAt = System.currentTimeMillis() + EXPIRE_TIME;
		String token = JWT.create()
				.withHeader(header)
				// 存入需要保存在token的信息
				.withAudience(username)
				// 过期时间
				.withExpiresAt(new Date(expireAt))
				.sign(Algorithm.HMAC256(password));
		return new Token(token, expireAt);
	}

	public void verify(String token) throws TokenException {
		Algorithm algorithm = Algorithm.HMAC256(defaultPassword);
		JWTVerifier verifier = JWT.require(algorithm).build();
		try {
			DecodedJWT jwt = verifier.verify(token);
			if(!defaultUsername.equals(jwt.getAudience().get(0))) {
				throw new TokenException("用户不存在");
			}
		} catch(TokenExpiredException e) {
			throw new TokenException("token已过期");
		} catch(Exception e) {
			e.printStackTrace();
			throw new TokenException("token无效");
		}
	}
}
