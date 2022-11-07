package GUI;

import application.model.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public interface ControllerInterface {
    ArrayList<Arrangement> getArrangementer();

    ArrayList<Betalingsmetode> getBetalingsmetoder();

    ArrayList<Produktgruppe> getProduktgrupper();

    ArrayList<Produktgruppe> getProduktgrupper(EnumArrangementVisning enumArrangementVisning);

    ArrayList<Produkt> getProdukter();

    ArrayList<Produkt> getProdukter(Produktgruppe produktgruppe);

    ArrayList<Pris> getPriser();

    ArrayList<Salg> getSalg();

    ArrayList<Salgslinje> getSalgslinjer();

    ArrayList<Rundvisning> getRundvisning();

    ArrayList<Rundvisning> getRundvisning(boolean erBetalt);

    ArrayList<Udlejning> getUdlejning();

    ArrayList<Kunde> getKunder();

    void removePris(Pris pris);

    ArrayList<Pris> getPriserFromArrangement(Arrangement arrangement);

    ArrayList<Pris> getPriserFromArrangementWithinProduktgruppe(Arrangement arrangement, Produktgruppe produktgruppe);

    Arrangement createArrangement(String navn);

    Produktgruppe createProduktgruppe(String navn);

    Salg createSalg();

    Rundvisning createRundvisning();

    Udlejning createUdlejning(Kunde selectedItem);

    Kunde createKunde(String navn, String telefon, String email, String adresse);

    void setKunde(Rundvisning rundvisning, Kunde kunde);

    void setAntalDeltagere(Rundvisning rundvisning, int antal);

    Salgslinje createSalgsLinje(Salg salg, int antal, Pris pris);

    boolean incrementSalgslinje(Salg salg, Pris pris);

    void removeSalgsLinje(Salg salg, Salgslinje salgslinje);

    //               if (priser.contains(pris)) {
    //        priser.remove(pris);
    Betalingsmetode createBetalingsmetode(String metode);

    void setBetalingsmetode(Salg salg, Betalingsmetode betalingsmetode);

    void setRabatprocent(Salg salg, double rabatprocent);

    Produkt createProdukt(String produktnavn, Produktgruppe produktgruppe);

    Pris createPris(int enhedsPris, Produkt produkt, Arrangement arrangement);

    Pris createPris(int klip, int enhedsPris, Produkt produkt, Arrangement arrangement);

    void setProduktgruppeSomUdlejning(Produktgruppe produktgruppe);

    ArrayList<Produktgruppe> getProduktgrupperWithUdlejningsattribut();

    void setPant(Produkt produkt, int pant);

    void setVisning(Produktgruppe produktgruppe, EnumArrangementVisning salg);

    void setErBetalt(Rundvisning rundvisning, boolean erBetalt);

    void init();

    void initStorage();

    Pris getRundvisningsPris();

    void setUdlejningAfleveret(Udlejning valgteUdlejning);

    void setDato(Salg salg, LocalDate dato);

    void setModetidspunkt(Rundvisning rundvisning, LocalTime modetidspunkt);

    public ArrayList<Udlejning> getUdlejningerIkkeAfleveret();

    ArrayList<Salgslinje> getAlleProdukterIkkeAfleveret();

    public void setTotalPris(Salg salg, double totalPris);

    double udregnTotalPris(Salg salg);

    int getTotalPant(Udlejning udlejning);
}
