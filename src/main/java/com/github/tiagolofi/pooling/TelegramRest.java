package com.github.tiagolofi.pooling;

import java.util.Set;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import com.github.tiagolofi.client.TelegramClient;
import com.github.tiagolofi.client.TelegramConfigs;
import com.github.tiagolofi.secutiry.TokenJwt;
import com.github.tiagolofi.types.Result;

import io.smallrye.mutiny.tuples.Tuple4;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/telegram")
public class TelegramRest {
    
    @Inject
    TelegramConfigs telegramConfigs;

    @Inject
    Logger log;

    @Inject
    @RestClient
    TelegramClient telegramClient;

    @Inject
    TokenJwt tokenJwt;

    @POST
    @Path("/auth")
    public Response auth() {
        Result result = telegramClient.updates(telegramConfigs.botToken(),"getUpdates", 1, -1);

        Tuple4<Long, String, String, Long> dados = parseResult(result);

        String text = dados.getItem3();
        if (text != null && "/auth".equals(text)) {
            String token = tokenJwt.geraToken(String.valueOf(dados.getItem1()), dados.getItem2(), dados.getItem4(), Set.of("user"));
            log.info("Generated JWT: " + token);

            telegramClient.send(
                telegramConfigs.botToken(),
                "sendMessage",
                dados.getItem1(),
                String.format(
                    "Seu dispositivo está autenticado %s!%n%nObs: Esta sessão tem a duração de %d hora(s).", 
                    dados.getItem2(), telegramConfigs.hourSessionDuration()
                )
            );
        }

        return Response.ok().build();
    }

    private Tuple4<Long, String, String, Long> parseResult(Result result) {
        Long userId = result.getResult().getFirst().getMessage().getChat().getId();
        String name = result.getResult().getFirst().getMessage().getChat().getFirstName();
        Long date = result.getResult().getFirst().getMessage().getDate();
        String text = result.getResult().getFirst().getMessage().getText();
        return Tuple4.of(userId, name, text, date);
    }
}
