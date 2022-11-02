package application.Controller;

import Storage.Storage;
import application.model.*;

import java.util.ArrayList;

public interface StorageInterface {


    ArrayList<Arrangement> getArrangementer();

    void addArrangement(Arrangement arrangement);

    void removeArrangement(Arrangement arrangement);

    ArrayList<Betalingsmetode> getBetalingsmetoder();

    void addBetalingsmetode(Betalingsmetode betalingsmetode);

    void removeBetalingsmetode(Betalingsmetode betalingsmetode);

    ArrayList<Produktgruppe> getProduktgrupper();

    void addProduktgruppe(Produktgruppe produktgruppe);

    void removeProduktgruppe(Produktgruppe produktgruppe);

    ArrayList<Produkt> getProdukter();

    void addProdukt(Produkt produkt);

    void removeProdukt(Produkt produkt);

    ArrayList<Pris> getPriser();

    void addPris(Pris pris);

    void removePris(Pris pris);

    ArrayList<Salgslinje> getSalgslinjer();

    void addSalgslinje(Salgslinje salgslinje);

    void removeSalgslinje(Salgslinje salgslinje);

    ArrayList<Salg> getSalg();

    void addSalg(Salg salg);

    void removeSalg(Salg salg);

    ArrayList<Rundvisning> getRundvisninger();

    void addRundvisning(Rundvisning rundvisning);

    void removeRundvisning(Rundvisning rundvisning);

    ArrayList<Udlejning> getUdlejninger();

    void addUdlejning(Udlejning udlejning);

    void removeUdlejning(Udlejning udlejning);

    ArrayList<Kunde> getKunder();

    void addKunde(Kunde kunde);

    void removeKunde(Kunde kunde);
}
