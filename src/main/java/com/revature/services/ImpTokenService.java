package com.revature.services;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.revature.beans.User;
import com.revature.services.interfaces.TokenService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class ImpTokenService implements TokenService {

	private static final Logger log = Logger.getLogger(ImpTokenService.class);

	private static final long TOKEN_EXPIRY = 1000 * 60 * 60 * 24; // 1 day
	private static final String SECRET = "ThisISAsUPerLoNgPasSWord0417anDNumbRs!BWYUBUUYB*@!^&#GUYWQGD!^@GDUYWQGD&!^FDUQWFUD!&^FUWQYDF^!F";

	@Override
	public String generateToken(User details) {
		// The JWT signature algorithm we will be using to sign the token
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

		long nowMillis = System.currentTimeMillis();
		Date nows = new Date(nowMillis);

		// We will sign our JWT with our ApiKey secret
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET);
		Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

		// Let's set the JWT Claims
		JwtBuilder builder = Jwts.builder().setId("" + details.getUserId()).setIssuedAt(nows)
				.setSubject(details.getUsername()).signWith(signingKey, signatureAlgorithm);

		// if it has been specified, let's add the expiration
		if (TOKEN_EXPIRY > 0) {
			long expMillis = nowMillis + TOKEN_EXPIRY;
			Date exp = new Date(expMillis);
			builder.setExpiration(exp);
		}

		// Builds the JWT and serializes it to a compact, URL-safe string
		return builder.compact();
	}

	@Override
	public boolean validateToken(String token) {
		try {
			log.info(token);

			Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(SECRET)).parseClaimsJws(token.split(" ")[1])
					.getBody();
			return true;
		} catch (Exception e) {
			log.error("JWT validation failed at {}. Exception was {}"
					+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy")));
			return false;
		}

	}

	@Override
	public String getTokenId(String token) {
		if (token != null && token.startsWith("Bearer ")) {
			Claims claims = Jwts.parser()
					.setSigningKey(DatatypeConverter.parseBase64Binary("ThisISAsUPerLoNgPasSWord0417anDNumbRs!"))
					.parseClaimsJws(token.replace("Bearer ", "")).getBody();
			return claims.getId();
		}
		return null;
	}

//	@Override
//	public User getUserDetailsFromToken(String token) {
//		if (token == null || !token.startsWith("Bearer ")) {
//			return null;
//		}
//		Claims claims = Jwts.parser().setSigningKey(keyPair.getPublic()).parseClaimsJws(token.replace("Bearer ", ""))
//				.getBody();
//		return new AuthenticatedUser(claims.getSubject(), (String) claims.get("email"),
//				Arrays.stream(claims.get("roles").toString().split(",")).collect(Collectors.toList()));
//	}

}