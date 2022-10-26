package application.Controller;

import Storage.Storage;
import application.model.Arrangement;
import application.model.Pris;
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

    public static ArrayList<Pris> getPriser() {return Storage.getPriser();}

    public static void removePris(Pris pris){Storage.removePris(pris);}

    public static ArrayList<Pris> getPriserFromArrangement(Arrangement arrangement){
        ArrayList<Pris> priserFraArrangement = new ArrayList<>();
        for (int i = 0; i < Storage.getPriser().size(); i++) {
            if (Storage.getPriser().get(i).getArrangement().equals(arrangement)){
                priserFraArrangement.add(Storage.getPriser().get(i));
            }
        }
        return priserFraArrangement;
    }

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

    public static Pris createPris(int enhedsPris, Produkt produkt, Arrangement arrangement){
        Pris pris = arrangement.createPris(enhedsPris, produkt);
        Storage.addPris(pris);
        return pris;
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

    Produkt p15 = createProdukt("Klosterbryg",pg2);
    Produkt p16 = createProdukt("Jazz Classic",pg2);
    Produkt p17 = createProdukt("Extra Pilsner",pg2);
    Produkt p18 = createProdukt("Celebration",pg2);
    Produkt p19 = createProdukt("Blondie",pg2);
    Produkt p20 = createProdukt("Forårsbryg",pg2);
    Produkt p21 = createProdukt("India Pale Ale",pg2);
    Produkt p22 = createProdukt("Julebryg",pg2);
    Produkt p23 = createProdukt("Imperial Stout",pg2);
    Produkt p24 = createProdukt("Special",pg2);
    Produkt p25 = createProdukt("Æblebrus",pg2);
    Produkt p26 = createProdukt("Chips",pg2);
    Produkt p27 = createProdukt("Peanuts",pg2);
    Produkt p28 = createProdukt("Cola",pg2);
    Produkt p29 = createProdukt("Nikoline",pg2);
    Produkt p30 = createProdukt("7-Up",pg2);
    Produkt p31 = createProdukt("Vand",pg2);
    Produkt p32 = createProdukt("Ølpølser",pg2);

    Produkt p33 = createProdukt("Whisky 45% 50 cl rør",pg3);
    Produkt p34 = createProdukt("Whisky 4 cl",pg3);
    Produkt p35 = createProdukt("Whisky 43% 50 cl rør",pg3);
    Produkt p36 = createProdukt("u/ egesplint",pg3);
    Produkt p37 = createProdukt("m/ egesplint",pg3);
    Produkt p38 = createProdukt("2*whisky glas + brikker",pg3);
    Produkt p39 = createProdukt("Liquor of Aarhus",pg3);
    Produkt p40 = createProdukt("Lyng gin 50 cl",pg3);
    Produkt p41 = createProdukt("Lyng gin 4 cl",pg3);

    Produkt p42 = createProdukt("Klosterbryg, 20 liter",pg4);
    Produkt p43 = createProdukt("Jazz Classic, 25 liter",pg4);
    Produkt p44 = createProdukt("Extra Pilsner, 25 liter",pg4);
    Produkt p45 = createProdukt("Celebration, 20 liter",pg4);
    Produkt p46 = createProdukt("Blondie, 25 liter",pg4);
    Produkt p47 = createProdukt("Forårsbryg, 20 liter",pg4);
    Produkt p48 = createProdukt("India Pale Ale, 20 liter",pg4);
    Produkt p49 = createProdukt("Julebryg, 20 liter",pg4);
    Produkt p50 = createProdukt("Imperial Stout, 20 liter",pg4);
    Produkt p51 = createProdukt("Pant",pg4);

    Produkt p52 = createProdukt("6 kg",pg5);
    Produkt p53 = createProdukt("Pant",pg5);
    Produkt p54 = createProdukt("4 kg",pg5);
    Produkt p55 = createProdukt("10 kg",pg5);

    Produkt p56 = createProdukt("25 kg sæk",pg6);

    Produkt p57 = createProdukt("t-shirt",pg7);
    Produkt p58 = createProdukt("polo",pg7);
    Produkt p59 = createProdukt("cap",pg7);

    Produkt p60 = createProdukt("1-hane",pg8);
    Produkt p61 = createProdukt("2-haner",pg8);
    Produkt p62 = createProdukt("Bar med flere haner",pg8);
    Produkt p63 = createProdukt("Levering",pg8);
    Produkt p64 = createProdukt("Krus",pg8);

    Produkt p65 = createProdukt("uanset størrelse",pg9);

    Produkt p66 = createProdukt("gaveæske 2 øl, 2 glas",pg10);
    Produkt p67 = createProdukt("gaveæske 4 øl",pg10);
    Produkt p68 = createProdukt("trækasse 6 øl",pg10);
    Produkt p69 = createProdukt("gavekurv 6 øl, 2 glas",pg10);
    Produkt p70 = createProdukt("trækasse 6 øl, 6 glas",pg10);
    Produkt p71 = createProdukt("trækasse 12 øl",pg10);
    Produkt p72 = createProdukt("papkasse 12 øl",pg10);

    Produkt p73 = createProdukt("pr person dag",pg11);
    }



}
