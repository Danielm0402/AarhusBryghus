package Storage;

import application.model.Arrangement;
import application.model.Pris;
import application.model.Produkt;
import application.model.Produktgruppe;

import java.util.ArrayList;

public class Storage {

    private static final ArrayList<Arrangement> arrangementer = new ArrayList<>();
    private static final ArrayList<Produktgruppe> produktgrupper = new ArrayList<>();
    private static final ArrayList<Produkt> produkter = new ArrayList<>();
    private static final ArrayList<Pris> priser = new ArrayList<>();


    public static ArrayList<Arrangement> getArrangementer() {
        return new ArrayList<Arrangement>(arrangementer);
    }

    public static void addArrangement(Arrangement arrangement) {
        arrangementer.add(arrangement);
    }



    public static ArrayList<Produktgruppe> getProduktgrupper() {
        return new ArrayList<Produktgruppe>(produktgrupper);
    }

    public static void addProduktgruppe(Produktgruppe produktgruppe) {
        produktgrupper.add(produktgruppe);
    }


    public static ArrayList<Produkt> getProdukter() {
        return new ArrayList<Produkt>(produkter);
    }


    public static void addProdukt(Produkt produkt) {
        produkter.add(produkt);
    }

    public static ArrayList<Pris> getPriser(){
        return new ArrayList<Pris>(priser);
    }
    public static void addPris(Pris pris) {
        priser.add(pris);
    }
    public static void removePris(Pris pris) {priser.remove(pris);
    }
}
