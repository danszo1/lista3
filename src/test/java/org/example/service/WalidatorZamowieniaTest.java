package org.example.service;

import com.github.nylle.javafixture.JavaFixture;
import org.example.dane.Klient;
import org.example.dane.Produkt;
import org.example.dane.ŻądanieZamówienia;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WalidatorZamowieniaTest {

    private final WalidacjaZamówienia walidator = new WalidacjaZamówienia();
    private final JavaFixture fixture = new JavaFixture();

    @Test
    void powinienZaakceptowacPoprawneZamowienie() {
        ŻądanieZamówienia zamowienie = fixture.create(ŻądanieZamówienia.class);

        poprawDaneZamowienia(zamowienie);

        assertDoesNotThrow(() -> walidator.waliduj(zamowienie));
    }

    @Test
    void powinienRzucicBladGdyBrakKlienta() {
        ŻądanieZamówienia zamowienie = new ŻądanieZamówienia();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            walidator.waliduj(zamowienie);
        });

        assertTrue(exception.getMessage().contains("Brak danych") || exception.getMessage().contains("puste"));
    }

    @Test
    void powinienRzucicBladGdyZlaJednostka() {
        ŻądanieZamówienia zamowienie = fixture.create(ŻądanieZamówienia.class);
        poprawDaneZamowienia(zamowienie);

        zamowienie.getItems().get(0).setUnitOfMeasure("litr");

        assertThrows(IllegalArgumentException.class, () -> walidator.waliduj(zamowienie));
    }

    @Test
    void powinienRzucicBladGdyZaDuzoProduktow() {
        ŻądanieZamówienia zamowienie = fixture.create(ŻądanieZamówienia.class);
        poprawDaneZamowienia(zamowienie);

        List<Produkt> duzoProduktow = new ArrayList<>();
        for(int i=0; i<10; i++) {
            Produkt p = fixture.create(Produkt.class);
            p.setUnitOfMeasure("kg");
            p.setQuantity("1");
            duzoProduktow.add(p);
        }

        zamowienie.setProducts(duzoProduktow);

        assertThrows(IllegalArgumentException.class, () -> walidator.waliduj(zamowienie));
    }

    @Test
    void powinienRzucicBladGdyPrzekroczonoWage() {
        ŻądanieZamówienia zamowienie = fixture.create(ŻądanieZamówienia.class);
        poprawDaneZamowienia(zamowienie);

        Produkt p = zamowienie.getItems().get(0);
        p.setUnitOfMeasure("tona");
        p.setQuantity("3");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> walidator.waliduj(zamowienie));
        assertTrue(exception.getMessage().contains("przekracza 2 tony"));
    }

    private void poprawDaneZamowienia(ŻądanieZamówienia z) {

        z.getCustomerInfo().setFirstName("Jan");
        z.getCustomerInfo().setLastName("Kowalski");

        Produkt p = fixture.create(Produkt.class);
        p.setUnitOfMeasure("kilogram");
        p.setQuantity("10");

        List<Produkt> lista = new ArrayList<>();
        lista.add(p);

        z.setProducts(lista);
    }
}