package application.Controller;

import Storage.Storage;
import application.model.Arrangement;
import application.model.Produkt;
import application.model.Produktgruppe;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Controller {

    public static ArrayList<Arrangement> getArrangementer(){
        return Storage.getArrangementer();
    }

    public static ArrayList<Produktgruppe> getProduktgrupper(){
        return Storage.getProduktgrupper();
    }

    public static ArrayList<Produkt> getProdukter() {return Storage.getProdukter();}


    public static Arrangement createArrangement(String navn){
        Arrangement arrangement = new Arrangement(navn);
        Storage.addArrangement(arrangement);
        return arrangement;}

    public static Produktgruppe createProduktgruppe(String navn) {
        Produktgruppe produktgruppe = new Produktgruppe(navn);
        Storage.addProduktgruppe(produktgruppe);
        return produktgruppe;
    }

    public static Produkt createProdukt(String produktnavn, Produktgruppe produktgruppe) {
        Produkt produkt = produktgruppe.createProdukt(produktnavn);
        Storage.addProdukt(produkt);
        return produkt;
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

    Produkt p1 = createProdukt("Klosterbryg",pg1);
    Produkt p2 = createProdukt("Sweet Georgia Brown",pg1);
    Produkt p3 = createProdukt("Extra Pilsner",pg1);
    Produkt p4 = createProdukt("Celebration",pg1);
    Produkt p5 = createProdukt("Blondie",pg1);
    Produkt p6 = createProdukt("Forårsbryg",pg1);
    Produkt p7 = createProdukt("India Pale Ale",pg1);
    Produkt p8 = createProdukt("Julebryg",pg1);
    Produkt p9 = createProdukt("Juletønden",pg1);
    Produkt p10 = createProdukt("Old Strong Ale",pg1);
    Produkt p11 = createProdukt("Fregatten Jylland",pg1);
    Produkt p12 = createProdukt("Imperial Stout",pg1);
    Produkt p13 = createProdukt("Tribute",pg1);
    Produkt p14 = createProdukt("Black Monster",pg1);

// vi burde nok fortsætte med alle produkter her...


    }



}
