package com.github.tiagolofi.types;

public interface Command {
    String command();
    String description();
    void run();
}
