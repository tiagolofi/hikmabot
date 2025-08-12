package com.github.tiagolofi.client;

import io.smallrye.config.ConfigMapping;

@ConfigMapping(prefix = "telegram")
public interface TelegramConfigs {
    String botToken();
    String botName();
    String botUsername();
    int hourSessionDuration();
}
