package application.Controller;

import Storage.Storage;
import application.model.*;


import java.util.ArrayList;

public class Controller {

    public static ArrayList<Arrangement> getArrangementer(){
        return Storage.getArrangementer();
    }

    public static ArrayList<Betalingsmetode> getBetalingsmetoder(){
        return Storage.getBetalingsmetoder();
    }

    public static ArrayList<Produktgruppe> getProduktgrupper(){
        return Storage.getProduktgrupper();
    }

    public static ArrayList<Produktgruppe> getProduktgrupper(EnumArrangementVisning enumArrangementVisning){
        ArrayList<Produktgruppe> alleproduktgrupper = Storage.getProduktgrupper();
        ArrayList<Produktgruppe> produktgrupperDerSkalVises = new ArrayList<>();
        EnumArrangementVisning visning = enumArrangementVisning;

        for (Produktgruppe pg : alleproduktgrupper){
            if(pg.getVisning() == enumArrangementVisning){
                produktgrupperDerSkalVises.add(pg);
            }
        }
        return  produktgrupperDerSkalVises;
    }


    public static ArrayList<Produkt> getProdukter() {return Storage.getProdukter();}

    public static ArrayList<Produkt> getProdukter(Produktgruppe produktgruppe)
    {
        return produktgruppe.getProdukter();
    }


    public static ArrayList<Pris> getPriser() {return Storage.getPriser();}

    public static ArrayList<Salg> getSalg() {return Storage.getSalg();}

    public static ArrayList<Salgslinje> getSalgslinjer() {return Storage.getSalgslinjer();}

    public static ArrayList<Rundvisning> getRundvisning() {return Storage.getRundvisninger();}

    public static ArrayList<Udlejning> getUdlejning() {return Storage.getUdlejninger();}

    public static ArrayList<Kunde> getKunder() {return Storage.getKunder();}

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

    public static ArrayList<Pris> getPriserFromArrangementWithinProduktgruppe(Arrangement arrangement, Produktgruppe produktgruppe){
        ArrayList<Pris> priserFraArrangementMedDenneProduktgruppe = new ArrayList<>();
        ArrayList<Produkt> produkterIDenneProduktgruppe = new ArrayList<>();
        if (produktgruppe != null){
            produkterIDenneProduktgruppe = produktgruppe.getProdukter();
        }

        ArrayList<Pris> liste = getPriserFromArrangement(arrangement);

        for (Pris pris : liste){
            if(produkterIDenneProduktgruppe.contains(pris.getProdukt())){
                priserFraArrangementMedDenneProduktgruppe.add(pris);
            }
        }
        return priserFraArrangementMedDenneProduktgruppe;
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

    public static Salg createSalg(){
        Salg salg = new Salg();
        Storage.addSalg(salg);
        return salg;
    }

    public static Rundvisning createRundvisning(){
        Rundvisning rundvisning = new Rundvisning();
        Storage.addRundvisning(rundvisning);
        return rundvisning;
    }

    public static Udlejning createUdlejning(){
        Udlejning udlejning = new Udlejning();
        Storage.addUdlejning(udlejning);
        return udlejning;
    }

    public static Kunde createKunde(String navn, String telefon, String email, String adresse){
        Kunde kunde = new Kunde(navn, telefon, email, adresse);
        Storage.addKunde(kunde);
        return kunde;
    }

    public static Salgslinje createSalgsLinje(Salg salg, int antal, double aftaltpris, Pris pris){
        Salgslinje salgslinje = salg.createSalgslinje(antal, aftaltpris, pris);
        Storage.addSalgslinje(salgslinje);
        return salgslinje;
    }

    public static boolean incrementSalgslinje(Salg salg, Pris pris){
        boolean existsAlready = salg.incrementSalgslinje(pris);

        return  existsAlready;
    }

    public static void removeSalgsLinje(Salg salg,Salgslinje salgslinje) {
        salg.removeSalgslinje(salgslinje);
    }
//               if (priser.contains(pris)) {
//        priser.remove(pris);
    public static Betalingsmetode createBetalingsmetode(String metode){
        Betalingsmetode betalingsmetode = new Betalingsmetode(metode);
        Storage.addBetalingsmetode(betalingsmetode);
        return betalingsmetode;
    }

    public static void setBetalingsmetode(Salg salg, Betalingsmetode betalingsmetode){
        salg.setBetalingsmetode(betalingsmetode);
    }


    public static void setRabatprocent(Salg salg, double rabatprocent){
        salg.setRabatprocent(rabatprocent);
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

    public static void setPant(Produkt produkt, int pant) {
        produkt.setPant(pant);
    }

    public static void setVisning(Produktgruppe produktgruppe, EnumArrangementVisning salg) {
        produktgruppe.setVisning(salg);
    }

    public static void init() {
        initStorage();
    }


    public static void initStorage() {



    //    Arrangementer
    Arrangement a1 = createArrangement("Fredagsbar");
    Arrangement a2 = createArrangement("Daglig butikssalg");
    Arrangement a3 = createArrangement("Rundvisning");


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

    // Betalingsmetoder
    Betalingsmetode b1 = createBetalingsmetode("Dankort");
    Betalingsmetode b2 = createBetalingsmetode("Kontant");
    Betalingsmetode b3 = createBetalingsmetode("Klippekort");
    Betalingsmetode b4 = createBetalingsmetode("Mobilpay");
    Betalingsmetode b5 = createBetalingsmetode("Regning");

//    sætter lige Fustager og Kulsyre og Anlæg til at kunne udlejes
    pg4.setProduktgruppeSomUdlejning();
    pg5.setProduktgruppeSomUdlejning();
    pg8.setProduktgruppeSomUdlejning();

    pg4.setVisning(EnumArrangementVisning.UDLEJNING);
    pg5.setVisning(EnumArrangementVisning.UDLEJNING);
    pg8.setVisning(EnumArrangementVisning.UDLEJNING);

    pg1.setVisning(EnumArrangementVisning.SALG);
    pg2.setVisning(EnumArrangementVisning.SALG);
    pg3.setVisning(EnumArrangementVisning.SALG);
    pg6.setVisning(EnumArrangementVisning.SALG);
    pg7.setVisning(EnumArrangementVisning.SALG);
    pg9.setVisning(EnumArrangementVisning.SALG);
    pg10.setVisning(EnumArrangementVisning.SALG);







//

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

    Produkt p15 = createProdukt("Klosterbryg 40 cl",pg2);
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

    Produkt p74 = createProdukt("Rundvisning", pg11);

        //        priser

        Pris pris1 = createPris(70,p1,a1);
        Pris pris2 = createPris(65,p2,a1);
        Pris pris3 = createPris(70,p15,a1);
        Pris pris4 = createPris(65,p16,a1);

        Pris pr1 = createPris(70,p1,a2);
        Pris pr2 = createPris(65,p2,a2);
        Pris pr3 = createPris(70,p3,a2);
        Pris pr4 = createPris(65,p4,a2);
        Pris pr5 = createPris(70,p5,a2);
        Pris pr6 = createPris(65,p6,a2);
        Pris pr7 = createPris(70,p7,a2);
        Pris pr8 = createPris(65,p8,a2);
        Pris pr9 = createPris(70,p9,a2);
        Pris pr10 = createPris(65,p10,a2);
        Pris pr11 = createPris(70,p11,a2);
        Pris pr12 = createPris(65,p12,a2);
        Pris pr13 = createPris(70,p13,a2);
        Pris pr14 = createPris(65,p14,a2);
        Pris pr15= createPris(75,p15,a2);
        Pris pr16= createPris(75,p16,a2);
        Pris pr17 = createPris(70,p17,a2);
        Pris pr18 = createPris(65,p18,a2);
        Pris pr19 = createPris(75,p19,a2);
        Pris pr20 = createPris(75,p20,a2);
        Pris pr21 = createPris(75,p21,a2);
        Pris pr22 = createPris(75,p22,a2);
        Pris pr23 = createPris(75,p23,a2);
        Pris pr24 = createPris(75,p24,a2);
        Pris pr25 = createPris(75,p25,a2);
        Pris pr26 = createPris(75,p26,a2);
        Pris pr27 = createPris(75,p27,a2);
        Pris pr28 = createPris(75,p28,a2);
        Pris pr29 = createPris(75,p29,a2);
        Pris pr30 = createPris(75,p30,a2);
        Pris pr31 = createPris(75,p31,a2);
        Pris pr32 = createPris(75,p32,a2);
        Pris pr33 = createPris(75,p33,a2);
        Pris pr34 = createPris(75,p34,a2);
        Pris pr35 = createPris(75,p35,a2);
        Pris pr36 = createPris(75,p36,a2);
        Pris pr37 = createPris(75,p37,a2);
        Pris pr38 = createPris(75,p38,a2);
        Pris pr39 = createPris(75,p39,a2);
        Pris pr40 = createPris(75,p40,a2);
        Pris pr41 = createPris(75,p41,a2);
        Pris pr42 = createPris(75,p42,a2);
        Pris pr43 = createPris(75,p43,a2);
        Pris pr44 = createPris(75,p44,a2);
        Pris pr45 = createPris(75,p45,a2);
        Pris pr46 = createPris(75,p46,a2);
        Pris pr47 = createPris(75,p47,a2);
        Pris pr48 = createPris(75,p48,a2);
        Pris pr49 = createPris(75,p49,a2);
        Pris pr50 = createPris(75,p50,a2);
        Pris pr51 = createPris(75,p51,a2);
        Pris pr52 = createPris(75,p52,a2);
        Pris pr53 = createPris(75,p53,a2);
        Pris pr54 = createPris(75,p54,a2);
        Pris pr55 = createPris(75,p55,a2);
        Pris pr56 = createPris(75,p56,a2);
        Pris pr57 = createPris(75,p57,a2);
        Pris pr58= createPris(75,p58,a2);
        Pris pr59 = createPris(75,p59,a2);
        Pris pr60 = createPris(75,p60,a2);
        Pris pr61 = createPris(75,p61,a2);
        Pris pr62 = createPris(75,p62,a2);
        Pris pr63 = createPris(75,p63,a2);
        Pris pr64 = createPris(75,p64,a2);
        Pris pr65 = createPris(75,p65,a2);
        Pris pr66 = createPris(75,p66,a2);
        Pris pr67 = createPris(75,p67,a2);
        Pris pr68 = createPris(75,p68,a2);
        Pris pr69 = createPris(75,p69,a2);
        Pris pr70 = createPris(75,p70,a2);
        Pris pr71 = createPris(75,p71,a2);
        Pris pr72 = createPris(75,p72,a2);
        Pris pr73 = createPris(75,p73,a2);
        Pris pr74 = createPris(75,p74,a2);





        Pris pris10 = createPris(200,p74,a3);


        //Kunder
        Kunde k1 = createKunde("Hans","12345678", "hans112@gmail.com","Vejen 1");







    }



}
