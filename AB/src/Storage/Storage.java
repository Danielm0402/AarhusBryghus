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


    public static ArrayList<Salgslinje> getSalgslinjer() {
        return new ArrayList<Salgslinje>(salgslinjer);
    }

    public static void addSalgslinje(Salgslinje salgslinje) {
        salgslinjer.add(salgslinje);
    }


    public static ArrayList<Salg> getSalg() {
        return new ArrayList<Salg>(salgs);
    }

    public static void addSalg(Salg salg) {
        salgs.add(salg);
    }
}
