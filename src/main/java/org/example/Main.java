package org.example;

import org.example.Konfiguracja;
import org.example.dane.ŻądanieZamówienia;
import org.example.service.ZamówieniaService;

import java.io.File;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Konfiguracja config = new Konfiguracja();
        String ordersDirectory = config.getOrdersDirectory();
        System.out.println("Katalog z zamówieniami: " + ordersDirectory);

        WczytywanieJson loader = new WczytywanieJson();
        List<File> jsonFiles = loader.loadJsonFiles(ordersDirectory);

        if (jsonFiles.isEmpty()) {
            System.out.println("Brak plików JSON w katalogu.");
            return;
        }

        ZamówieniaService service = new ZamówieniaService();

        for (File file : jsonFiles) {
            ŻądanieZamówienia order = service.readOrder(file);
            System.out.println("Wczytano plik: " + file.getName());
            System.out.println("Numer zamówienia: " + order.getOrderNumber());
        }
    }
}
