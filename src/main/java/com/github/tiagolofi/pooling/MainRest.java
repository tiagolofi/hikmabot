package com.github.tiagolofi.pooling;

import com.github.tiagolofi.client.TelegramConfigs;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/main")
public class MainRest {
    
    @Inject
    TelegramConfigs telegramConfigs;

    @GET
    public String main() {
        return "Hello from MainRest! " +
               "Bot Name: " + telegramConfigs.botName() + ", " +
               "Bot Username: " + telegramConfigs.botUsername();
    }

}
