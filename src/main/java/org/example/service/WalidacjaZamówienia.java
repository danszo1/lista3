package org.example.service;

import org.example.dane.Klient;
import org.example.dane.Produkt;
import org.example.dane.ŻądanieZamówienia;
import java.util.List;

public class WalidacjaZamówienia {

    public void waliduj(ŻądanieZamówienia zadanie) {
        Klient klient = zadanie.getCustomerInfo();

        if (klient == null) {
            throw new IllegalArgumentException("Brak danych klienta.");
        }

        if (klient.getFirstName() == null || klient.getFirstName().trim().isEmpty() ||
                klient.getLastName() == null || klient.getLastName().trim().isEmpty()) {
            throw new IllegalArgumentException("Imię i nazwisko zamawiającego nie mogą być puste.");
        }

        List<Produkt> produkty = zadanie.getItems();

        if (produkty == null || produkty.isEmpty() || produkty.size() > 9) {
            throw new IllegalArgumentException("Zamówienie musi zawierać od 1 do 9 produktów.");
        }

        double calkowitaWagaWKg = 0.0;

        for (Produkt produkt : produkty) {
            String jednostka = produkt.getUnitOfMeasure();

            if (jednostka == null) {
                throw new IllegalArgumentException("Brak jednostki miary.");
            }

            jednostka = jednostka.toLowerCase();

            if (!jednostka.equals("gram") && !jednostka.equals("kilogram") && !jednostka.equals("tona")) {
                throw new IllegalArgumentException("Nieprawidłowa jednostka miary: " + jednostka);
            }

            double ilosc;
            try {
                ilosc = Double.parseDouble(produkt.getQuantity());
            } catch (NumberFormatException | NullPointerException e) {
                throw new IllegalArgumentException("Ilość produktu musi być liczbą.");
            }

            if (ilosc <= 0) {
                throw new IllegalArgumentException("Ilość produktu musi być dodatnia.");
            }

            switch (jednostka) {
                case "gram":
                    calkowitaWagaWKg += ilosc * 0.001;
                    break;
                case "kilogram":
                    calkowitaWagaWKg += ilosc;
                    break;
                case "tona":
                    calkowitaWagaWKg += ilosc * 1000.0;
                    break;
            }
        }

        if (calkowitaWagaWKg > 2000.0) {
            throw new IllegalArgumentException("Całkowita waga zamówienia przekracza 2 tony (aktualnie: " + calkowitaWagaWKg + " kg).");
        }
    }
}