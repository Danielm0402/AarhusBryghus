package Storage;

import application.model.Arrangement;
import application.model.Produktgruppe;

import java.util.ArrayList;

public class Storage {

    private static final ArrayList<Arrangement> arrangementer = new ArrayList<>();
    private static final ArrayList<Produktgruppe> produktgrupper = new ArrayList<>();



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




}
