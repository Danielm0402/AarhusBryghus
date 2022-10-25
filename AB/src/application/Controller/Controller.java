package application.Controller;

import Storage.Storage;
import application.model.Arrangement;
import application.model.Produktgruppe;

import java.util.ArrayList;

public class Controller {

    public static ArrayList<Arrangement> getArrangementer(){
        return Storage.getArrangementer();
    }

    public static ArrayList<Produktgruppe> getProduktgrupper(){
        return Storage.getProduktgrupper();
    }


    public static Arrangement createArrangement(String navn){
        Arrangement arrangement = new Arrangement(navn);
        Storage.addArrangement(arrangement);
        return arrangement;}

    private static Produktgruppe createProduktgruppe(String navn) {
        Produktgruppe produktgruppe = new Produktgruppe(navn);
        Storage.addProduktgruppe(produktgruppe);
        return produktgruppe;
    }


    public static void init() {
        initStorage();
    }


    public static void initStorage() {

    //    Arrangementer
    Arrangement a1 = createArrangement("Fredagsbar");
    Arrangement a2 = createArrangement("Daglig butikssalg");


    Produktgruppe pg1 = createProduktgruppe("Flaske");
    Produktgruppe pg2 = createProduktgruppe("Fadøl, 40 cl");
    Produktgruppe pg3 = createProduktgruppe("Spiritus");
    Produktgruppe pg4 = createProduktgruppe("Fustage");
    Produktgruppe pg5 = createProduktgruppe("Kulsyre");
    Produktgruppe pg6 = createProduktgruppe("Malt");
    Produktgruppe pg7 = createProduktgruppe("Beklædning");
    Produktgruppe pg8 = createProduktgruppe("Anlæg");
    Produktgruppe pg9 = createProduktgruppe("Glas");
    Produktgruppe pg10 = createProduktgruppe("Sampakninger");
    Produktgruppe pg11 = createProduktgruppe("Rundvisning");


    }
}
