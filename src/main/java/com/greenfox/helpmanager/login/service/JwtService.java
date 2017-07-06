package com.greenfox.helpmanager.login.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;

@Service
public class JwtService {
  public String createJwt(String issuer, String subject, long ttlMillis, String username) throws Exception {

    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    long nowMillis = System.currentTimeMillis();
    Date now = new Date(nowMillis);

    JwtBuilder builder = Jwts.builder()
        .setId(username)
        .setSubject(subject)
        .setIssuedAt(now)
        .setIssuer(issuer)
        .signWith(
            signatureAlgorithm,
            "1111power".getBytes("UTF-8")
        );

    if (ttlMillis >= 0) {
      long expMillis = nowMillis + ttlMillis;
      Date exp = new Date(expMillis);
      builder.setExpiration(exp);
    }

    return builder.compact();
  }

  public boolean isValid(String username, String token) throws Exception {
    Claims claims =
        Jwts.parser()
            .setSigningKey("1111power".getBytes("UTF-8"))
            .parseClaimsJws(token)
            .getBody();
    long nowMillis = System.currentTimeMillis();
    Date now = new Date(nowMillis);
    return claims.getId().equals(username) && claims.getExpiration().after(now);
  }
}
