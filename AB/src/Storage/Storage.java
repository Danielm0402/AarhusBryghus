package Storage;

import application.model.*;

import java.util.ArrayList;

public class Storage {

    private static final ArrayList<Arrangement> arrangementer = new ArrayList<>();
    private static final ArrayList<Produktgruppe> produktgrupper = new ArrayList<>();
    private static final ArrayList<Produkt> produkter = new ArrayList<>();
    private static final ArrayList<Pris> priser = new ArrayList<>();
    private static final ArrayList<Salgslinje> salgslinjer = new ArrayList<>();
    private static final ArrayList<Salg> salgs = new ArrayList<>();
    private static final ArrayList<Rundvisning> rundvisninger = new ArrayList<>();
    private static final ArrayList<Udlejning> udlejninger = new ArrayList<>();
    private static final ArrayList<Kunde> kunder = new ArrayList<>();
    private static final ArrayList<Betalingsmetode> betalingsmetoder = new ArrayList<>();

    //Rundvisnings pris til oprettelse af rundvisnings salgslinje i GUI
    private static Arrangement rundvisningsArrangement = new Arrangement("Rundvisning");
    private static Produktgruppe rundvisningsProduktgruppe = new Produktgruppe("Rundvisning");
    private static Produkt rundvisningProdukt=new Produkt("Rundvisning");
    private static Pris rundvisningPris=new Pris(100, rundvisningProdukt, rundvisningsArrangement);


    public static ArrayList<Arrangement> getArrangementer() {
        return new ArrayList<Arrangement>(arrangementer);
    }

    public static void addArrangement(Arrangement arrangement) {
        arrangementer.add(arrangement);
    }

    public static void removeArrangement(Arrangement arrangement) {arrangementer.remove(arrangement);
    }

    public static ArrayList<Betalingsmetode> getBetalingsmetoder(){
        return new ArrayList<>(betalingsmetoder);
    }

    public static void addBetalingsmetode(Betalingsmetode betalingsmetode){
        betalingsmetoder.add(betalingsmetode);
    }

    public static void removeBetalingsmetode(Betalingsmetode betalingsmetode){
        betalingsmetoder.remove(betalingsmetode);
    }


    public static ArrayList<Produktgruppe> getProduktgrupper() {
        return new ArrayList<Produktgruppe>(produktgrupper);
    }

    public static void addProduktgruppe(Produktgruppe produktgruppe) {
        produktgrupper.add(produktgruppe);
    }

    public static void removeProduktgruppe(Produktgruppe produktgruppe) {produktgrupper.remove(produktgruppe);
    }


    public static ArrayList<Produkt> getProdukter() {
        return new ArrayList<Produkt>(produkter);
    }

    public static void addProdukt(Produkt produkt) {
        produkter.add(produkt);
    }

    public static void removeProdukt(Produkt produkt) {produkter.remove(produkt);
    }


    public static ArrayList<Pris> getPriser(){
        return new ArrayList<Pris>(priser);
    }

    public static void addPris(Pris pris) {
        priser.add(pris);
    }

    public static void removePris(Pris pris) {priser.remove(pris);
    }


    public static ArrayList<Salgslinje> getSalgslinjer() {
        return new ArrayList<Salgslinje>(salgslinjer);
    }

    public static void addSalgslinje(Salgslinje salgslinje) {
        salgslinjer.add(salgslinje);
    }

    public static void removeSalgslinje(Salgslinje salgslinje) {salgslinjer.remove(salgslinje);
    }


    public static ArrayList<Salg> getSalg() {
        return new ArrayList<Salg>(salgs);
    }

    public static void addSalg(Salg salg) {
        salgs.add(salg);
    }
    public static void removeSalg(Salg salg) {salgs.remove(salgs);
    }


    public static ArrayList<Rundvisning> getRundvisninger() {
        return new ArrayList<Rundvisning>(rundvisninger);
    }

    public static void addRundvisning(Rundvisning rundvisning) {rundvisninger.add(rundvisning);}

    public static void removeRundvisning(Rundvisning rundvisning) {rundvisninger.remove(rundvisning);
    }


    public static ArrayList<Udlejning> getUdlejninger() {
        return new ArrayList<Udlejning>(udlejninger);
    }

    public static void addUdlejning(Udlejning udlejning) {
        udlejninger.add(udlejning);
    }

    public static Pris getRundvisningPris(){
        return rundvisningPris;
    }

    public static void removeUdlejning(Udlejning udlejning) {udlejninger.remove(udlejning);
    }

    public static ArrayList<Kunde> getKunder() {
        return new ArrayList<Kunde>(kunder);
    }

    public static void addKunde(Kunde kunde) {
        kunder.add(kunde);
    }

    public static void removeKunde(Kunde kunde) {kunder.remove(kunde);
    }
}
