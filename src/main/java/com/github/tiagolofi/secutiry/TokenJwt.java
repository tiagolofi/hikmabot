package com.github.tiagolofi.secutiry;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Set;

import com.github.tiagolofi.client.TelegramConfigs;

import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class TokenJwt {

    @Inject
    TelegramConfigs telegramConfigs;

    public String geraToken(String id, String name, Long date, Set<String> roles) { 
        return Jwt
            .issuer("https://github.com.br/tiagolofi")
            .upn(id)
            .groups(roles)
            .claim("tokenCreatedAt", LocalDateTime.now(ZoneId.of("America/Sao_Paulo")))
            .claim("authMessageDate", LocalDateTime.ofEpochSecond(date, 0, ZoneOffset.of("-03:00")))
            .claim("name", name)
            .expiresIn(Duration.ofHours(telegramConfigs.hourSessionDuration()))
            .innerSign()
            .encrypt();
    }

}
