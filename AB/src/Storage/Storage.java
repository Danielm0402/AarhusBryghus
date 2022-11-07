package Storage;

import application.model.*;

import java.util.ArrayList;

public class Storage implements application.Controller.StorageInterface {

    private static Storage uniqueInstance;

    //Rundvisnings pris-objekt til oprettelse af rundvisningssalgslinje i GUI
    private static Arrangement rundvisningsArrangement = new Arrangement("Rundvisning");
    private static Produktgruppe rundvisningsProduktgruppe = new Produktgruppe("Rundvisning");
    private static Produkt rundvisningProdukt=new Produkt("Rundvisning");
    private static Pris rundvisningPris=new Pris(100, rundvisningProdukt, rundvisningsArrangement);

    private final ArrayList<Arrangement> arrangementer = new ArrayList<>();
    private final ArrayList<Produktgruppe> produktgrupper = new ArrayList<>();
    private final ArrayList<Produkt> produkter = new ArrayList<>();
    private final ArrayList<Pris> priser = new ArrayList<>();
    private final ArrayList<Salgslinje> salgslinjer = new ArrayList<>();
    private final ArrayList<Salg> salgs = new ArrayList<>();
    private final ArrayList<Rundvisning> rundvisninger = new ArrayList<>();
    private final ArrayList<Udlejning> udlejninger = new ArrayList<>();
    private final ArrayList<Kunde> kunder = new ArrayList<>();
    private final ArrayList<Betalingsmetode> betalingsmetoder = new ArrayList<>();


    @Override
    public ArrayList<Arrangement> getArrangementer() {
        return new ArrayList<Arrangement>(arrangementer);
    }

    @Override
    public void addArrangement(Arrangement arrangement) {
        arrangementer.add(arrangement);
    }

    @Override
    public void removeArrangement(Arrangement arrangement) {arrangementer.remove(arrangement);
    }

    @Override
    public ArrayList<Betalingsmetode> getBetalingsmetoder(){
        return new ArrayList<>(betalingsmetoder);
    }

    @Override
    public void addBetalingsmetode(Betalingsmetode betalingsmetode){
        betalingsmetoder.add(betalingsmetode);
    }

    @Override
    public void removeBetalingsmetode(Betalingsmetode betalingsmetode){
        betalingsmetoder.remove(betalingsmetode);
    }


    @Override
    public ArrayList<Produktgruppe> getProduktgrupper() {
        return new ArrayList<Produktgruppe>(produktgrupper);
    }

    @Override
    public void addProduktgruppe(Produktgruppe produktgruppe) {
        produktgrupper.add(produktgruppe);
    }

    @Override
    public void removeProduktgruppe(Produktgruppe produktgruppe) {produktgrupper.remove(produktgruppe);
    }


    @Override
    public ArrayList<Produkt> getProdukter() {
        return new ArrayList<Produkt>(produkter);
    }

    @Override
    public void addProdukt(Produkt produkt) {
        produkter.add(produkt);
    }

    @Override
    public void removeProdukt(Produkt produkt) {produkter.remove(produkt);
    }


    @Override
    public ArrayList<Pris> getPriser(){
        return new ArrayList<Pris>(priser);
    }

    @Override
    public void addPris(Pris pris) {
        priser.add(pris);
    }

    @Override
    public void removePris(Pris pris) {priser.remove(pris);
    }


    @Override
    public ArrayList<Salgslinje> getSalgslinjer() {
        return new ArrayList<Salgslinje>(salgslinjer);
    }

    @Override
    public void addSalgslinje(Salgslinje salgslinje) {
        salgslinjer.add(salgslinje);
    }

    @Override
    public void removeSalgslinje(Salgslinje salgslinje) {salgslinjer.remove(salgslinje);
    }


    @Override
    public ArrayList<Salg> getSalg() {
        return new ArrayList<Salg>(salgs);
    }

    @Override
    public void addSalg(Salg salg) {
        salgs.add(salg);
    }
    @Override
    public void removeSalg(Salg salg) {salgs.remove(salgs);
    }


    @Override
    public ArrayList<Rundvisning> getRundvisninger() {
        return new ArrayList<Rundvisning>(rundvisninger);
    }

    @Override
    public void addRundvisning(Rundvisning rundvisning) {rundvisninger.add(rundvisning);
    }

    @Override
    public void removeRundvisning(Rundvisning rundvisning) {rundvisninger.remove(rundvisning);
    }

    public static Pris getRundvisningPris(){
        return rundvisningPris;
    }


    @Override
    public ArrayList<Udlejning> getUdlejninger() {
        return new ArrayList<Udlejning>(udlejninger);
    }

    @Override
    public void addUdlejning(Udlejning udlejning) {
        udlejninger.add(udlejning);
    }

    @Override
    public void removeUdlejning(Udlejning udlejning) {udlejninger.remove(udlejning);
    }

    public ArrayList<Kunde> getKunder() {
        return new ArrayList<Kunde>(kunder);
    }

    @Override
    public void addKunde(Kunde kunde) {
        kunder.add(kunde);
    }

    @Override
    public void removeKunde(Kunde kunde) {kunder.remove(kunde);
    }

    public static Storage getInstance() {
        if (Storage.uniqueInstance == null) {
            Storage.uniqueInstance = new Storage();
        }
        return Storage.uniqueInstance;
    }
























}
