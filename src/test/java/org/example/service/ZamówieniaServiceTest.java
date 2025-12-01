package org.example.service;

import com.github.nylle.javafixture.JavaFixture;
import org.example.dane.Klient; // Upewnij się, że importujesz dobrą klasę
import org.example.dane.Produkt;
import org.example.dane.ŻądanieZamówienia;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ZamówieniaServiceTest {

    private final ZamówieniaService service = new ZamówieniaService();
    private final JavaFixture fixture = new JavaFixture();

    @Test
    void powinienWygenerowacPoprawnyNumerZamowienia() {
        ŻądanieZamówienia zamowienie = new ŻądanieZamówienia();

        Klient klient = fixture.create(Klient.class);
        klient.setFirstName("Jan");
        klient.setLastName("Kowalski");
        zamowienie.setCustomerInfo(klient);

        List<Produkt> produkty = new ArrayList<>();
        produkty.add(fixture.create(Produkt.class));

        zamowienie.setProducts(produkty);

        String numerZamowienia = service.generateOrderNumber(zamowienie);

        assertNotNull(numerZamowienia);

        assertTrue(numerZamowienia.startsWith("ORD-"));

        String[] czesci = numerZamowienia.split("-");
        assertEquals(3, czesci.length, "Numer powinien składać się z 3 części (np. ORD-1234-abcd)");

        assertEquals(4, czesci[2].length());

        System.out.println("Wygenerowany numer: " + numerZamowienia);
    }
}
