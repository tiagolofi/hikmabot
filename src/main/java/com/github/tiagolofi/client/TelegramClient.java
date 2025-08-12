package com.github.tiagolofi.client;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.reactive.RestPath;
import org.jboss.resteasy.reactive.RestQuery;

import com.github.tiagolofi.types.Result;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@ApplicationScoped
@RegisterRestClient(configKey = "telegram")
public interface TelegramClient {
    
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{token}/{methodName}")
    public Result updates(
        @RestPath String token, 
        @RestPath String methodName,
        @RestQuery("limit") int limit,
        @RestQuery("offset") int offset
    );

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{token}/{methodName}")
    public void send(
        @RestPath String token, 
        @RestPath String methodName,
        @RestQuery("chat_id") Long chatId,
        @RestQuery String text
    );

}
