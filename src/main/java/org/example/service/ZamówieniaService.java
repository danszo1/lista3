package org.example.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dane.ŻądanieZamówienia;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class ZamówieniaService {

    private final ObjectMapper mapper = new ObjectMapper();

    private final WalidacjaZamówienia walidator = new WalidacjaZamówienia();

    public ŻądanieZamówienia readOrder(File file) {
        try {
            ŻądanieZamówienia order = mapper.readValue(file, ŻądanieZamówienia.class);

            walidator.waliduj(order);

            order.setOrderNumber(generateOrderNumber(order));
            return order;
        } catch (IOException e) {
            throw new RuntimeException("Błąd wczytywania pliku JSON: " + file.getName(), e);
        }
    }

    private String generateOrderNumber(ŻądanieZamówienia order) {
        String base = order.getCustomerInfo().getFirstName() +
                order.getCustomerInfo().getLastName() +
                order.getItems().size();
        return "ORD-" + Math.abs(base.hashCode()) + "-" + UUID.randomUUID().toString().substring(0, 4);
    }
}