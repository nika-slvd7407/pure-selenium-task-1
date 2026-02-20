package com.solvd.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class Config {

    private static final Properties PROPERTIES = new Properties();

    static {
        try (InputStream input = Config.class.getClassLoader().getResourceAsStream("config.properties")) {

            if (input == null) {
                throw new RuntimeException("properties not found");
            }
            PROPERTIES.load(input);
        } catch (IOException e) {
            throw new RuntimeException("failed", e);
        }
    }

    public Config() {
    }

    public static String get(String key) {
        return PROPERTIES.getProperty(key);
    }
}
