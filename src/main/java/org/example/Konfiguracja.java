package org.example;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

public class Konfiguracja
{
    private final Configuration config;

    private static final String CONFIG_FILENAME = "appsettings.properties";

    public Konfiguracja()
    {
        Configurations configs = new Configurations();

        try
        {
            config = configs.properties(CONFIG_FILENAME);
        } catch (ConfigurationException e)

        {
            throw new RuntimeException("Nie udało się wczytać pliku konfiguracyjnego: " + CONFIG_FILENAME, e);
        }
    }

    public String getOrdersDirectory() {

        String env = System.getenv("ORDER_DIR");

        if (env != null && !env.isBlank()) {
            System.out.println("Używam katalogu z ORDER_DIR: " + env);
            return env;
        }

        System.out.println("ORDER_DIR nie ustawione → używam wartości z appsettings.properties");

        return config.getString("orders.directory");
    }
}
