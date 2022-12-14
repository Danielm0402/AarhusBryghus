package application.Controller;

import Storage.Storage;
import application.model.*;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Controller implements GUI.ControllerInterface {
    private StorageInterface storage;
    public Controller(StorageInterface storage) {
        this.storage = storage;
    }

    public static Controller getTestController() {
        return new Controller(Storage.getInstance());
    }

    @Override
    public ArrayList<Arrangement> getArrangementer(){
        return storage.getArrangementer();
    }

    @Override
    public ArrayList<Betalingsmetode> getBetalingsmetoder(){
        return storage.getBetalingsmetoder();
    }

    @Override
    public ArrayList<Produktgruppe> getProduktgrupper(){
        return storage.getProduktgrupper();
    }

    @Override
    public ArrayList<Produktgruppe> getProduktgrupper(EnumArrangementVisning enumArrangementVisning){
        ArrayList<Produktgruppe> alleproduktgrupper = storage.getProduktgrupper();
        ArrayList<Produktgruppe> produktgrupperDerSkalVises = new ArrayList<>();
        EnumArrangementVisning visning = enumArrangementVisning;

        for (Produktgruppe pg : alleproduktgrupper){
            if(pg.getVisning() == enumArrangementVisning){
                produktgrupperDerSkalVises.add(pg);
            }
        }
        return  produktgrupperDerSkalVises;
    }


    @Override
    public ArrayList<Produkt> getProdukter() {return storage.getProdukter();}

    @Override
    public ArrayList<Produkt> getProdukter(Produktgruppe produktgruppe)
    {
        return produktgruppe.getProdukter();
    }

    @Override
    public void setTotalPris(Salg salg, double totalPris){
        salg.setTotalPris(totalPris);
    }

    @Override
    public ArrayList<Pris> getPriser() {return storage.getPriser();}

    @Override
    public ArrayList<Salg> getSalg() {return storage.getSalg();}

    @Override
    public ArrayList<Salgslinje> getSalgslinjer() {return storage.getSalgslinjer();}
    @Override
    public ArrayList<Salgslinje> getSalgslinjer(Salg salg){
        return new ArrayList<>(salg.getSalgsLinjer());
    }

    @Override
    public ArrayList<Rundvisning> getRundvisning() {return storage.getRundvisninger();}

    @Override
    public ArrayList<Rundvisning> getRundvisning(boolean erBetalt) {
        ArrayList<Rundvisning> rundvisninger = storage.getRundvisninger();
        ArrayList<Rundvisning> rundvisningerIkkeBetalt = new ArrayList<>();

        for (Rundvisning r : rundvisninger) {
            if (r.isErBetalt() == false) {
                rundvisningerIkkeBetalt.add(r);
                }else if(r.isErBetalt()==true){
                rundvisningerIkkeBetalt.remove(r);
            }
        }
        return rundvisningerIkkeBetalt;
    }

    @Override
    public ArrayList<Udlejning> getUdlejninger() {return storage.getUdlejninger();}

    @Override
    public ArrayList<Udlejning> getUdlejningerIkkeAfleveret() {
        ArrayList<Udlejning> alleUdlejninger = storage.getUdlejninger();
        ArrayList<Udlejning> ikkeAfleveredeUdlejninger = new ArrayList<>();

        for (Udlejning udlejning : alleUdlejninger){
            if (!udlejning.erAfleveret()){
                ikkeAfleveredeUdlejninger.add(udlejning);
            }
        }
        return ikkeAfleveredeUdlejninger;}

    @Override
    public ArrayList<Salgslinje> getAlleProdukterIkkeAfleveret(){
        ArrayList<Udlejning> UdlejningerIkkeAfleveret = getUdlejningerIkkeAfleveret();
        ArrayList<Salgslinje> ProdukterIkkeAfleveret = new ArrayList<>();

        for (Udlejning u : UdlejningerIkkeAfleveret){
            ProdukterIkkeAfleveret.addAll(u.getSalgsLinjer());
        }
        return ProdukterIkkeAfleveret;
    }

    @Override
    public ArrayList<Kunde> getKunder() {return storage.getKunder();}

    @Override
    public void removePris(Pris pris){storage.removePris(pris);}

    @Override
    public ArrayList<Pris> getPriserFromArrangement(Arrangement arrangement){
        ArrayList<Pris> priserFraArrangement = new ArrayList<>();
        for (int i = 0; i < storage.getPriser().size(); i++) {
            if (storage.getPriser().get(i).getArrangement().equals(arrangement)){
                priserFraArrangement.add(storage.getPriser().get(i));
            }
        }
        return priserFraArrangement;
    }

    @Override
    public ArrayList<Pris> getPriserFromArrangementWithinProduktgruppe(Arrangement arrangement, Produktgruppe produktgruppe){
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

    @Override
    public Arrangement createArrangement(String navn){
        Arrangement arrangement = new Arrangement(navn);
        storage.addArrangement(arrangement);
        return arrangement;}

    @Override
    public Produktgruppe createProduktgruppe(String navn) {
        Produktgruppe produktgruppe = new Produktgruppe(navn);
        storage.addProduktgruppe(produktgruppe);
        return produktgruppe;
    }

    @Override
    public Salg createSalg(){
        Salg salg = new Salg();
        storage.addSalg(salg);
        return salg;
    }

    @Override
    public Rundvisning createRundvisning(){
        Rundvisning rundvisning = new Rundvisning();
        storage.addRundvisning(rundvisning);
        return rundvisning;
    }

    public Udlejning createUdlejning(Kunde selectedItem){
        Udlejning udlejning = new Udlejning(selectedItem);
        storage.addUdlejning(udlejning);
        return udlejning;
    }

    @Override
    public Kunde createKunde(String navn, String telefon, String email, String adresse){
        Kunde kunde = new Kunde(navn, telefon, email, adresse);
        storage.addKunde(kunde);
        return kunde;
    }

    @Override
    public void setKunde(Rundvisning rundvisning, Kunde kunde){
        rundvisning.setKunde(kunde);
    }

    public void setUdlejningAfleveret(Udlejning udlejning){
        udlejning.setErAfleveret(true);
    }

    public void setAntalDeltagere(Rundvisning rundvisning, int antal){
        rundvisning.setAntalDeltagere(antal);
    }

    @Override
    public Salgslinje createSalgsLinje(Salg salg, int antal, Pris pris){
        Salgslinje salgslinje = salg.createSalgslinje(antal, pris);
        storage.addSalgslinje(salgslinje);
        return salgslinje;
    }

    @Override
    public Salgslinje createSalgsLinje(Salg salg, int antal, Pris pris, int klip){
        Salgslinje salgslinje = salg.createSalgslinje(antal, pris, klip);
        storage.addSalgslinje(salgslinje);
        return salgslinje;
    }

    @Override
    public boolean incrementSalgslinje(Salg salg, Pris pris){
        boolean existsAlready = salg.incrementSalgslinje(pris);

        return  existsAlready;
    }

    @Override
    public void removeSalgsLinje(Salg salg, Salgslinje salgslinje) {
        salg.removeSalgslinje(salgslinje);
    }

    @Override
    public Betalingsmetode createBetalingsmetode(EnumBetalingsmetode metode){
        Betalingsmetode betalingsmetode = new Betalingsmetode(metode);
        storage.addBetalingsmetode(betalingsmetode);
        return betalingsmetode;
    }

    @Override
    public void setBetalingsmetode(Salg salg, Betalingsmetode betalingsmetode){
        salg.setBetalingsmetode(betalingsmetode);
    }

    @Override
    public int getTotalPant(Udlejning udlejning){
        return udlejning.getTotalPant();
    }

    @Override
    public void setRabatprocent(Salg salg, double rabatprocent){
        salg.setRabatprocent(rabatprocent);
    }

    @Override
    public Produkt createProdukt(String produktnavn, Produktgruppe produktgruppe) {
        Produkt produkt = produktgruppe.createProdukt(produktnavn);
        storage.addProdukt(produkt);
        return produkt;
    }

    @Override
    public Pris createPris(int enhedsPris, Produkt produkt, Arrangement arrangement){
        Pris pris = arrangement.createPris(enhedsPris, produkt);
        storage.addPris(pris);
        return pris;
    }

    @Override
    public Pris createPris(int klip,int enhedsPris, Produkt produkt, Arrangement arrangement){
        Pris pris = arrangement.createPris(klip,enhedsPris, produkt);
        storage.addPris(pris);
        return pris;
    }

    @Override
    public void setProduktgruppeSomUdlejning(Produktgruppe produktgruppe) {
        produktgruppe.setProduktgruppeSomUdlejning();
    }

    @Override
    public ArrayList<Produktgruppe> getProduktgrupperWithUdlejningsattribut() {
        ArrayList<Produktgruppe> produktgrupperWithUdlejningsattribut = new ArrayList<>();
        ArrayList<Produktgruppe> alleproduktgrupper = getProduktgrupper();

//        l??ber gennem alle produktgrupper og ser om de bruges til udlejning
        for (Produktgruppe pg : alleproduktgrupper){
            if (pg.isBrugtTilUdlejning()){
                produktgrupperWithUdlejningsattribut.add((pg));
            }
        }
        return produktgrupperWithUdlejningsattribut;
    }

    @Override
    public double udregnTotalPris(Salg salg){
        return salg.udregnTotalPris();
    }
    @Override
    public int udregnTotalKlip(Salg salg){
        return salg.udregnTotalKlip();
    }

    @Override
    public void setPant(Produkt produkt, int pant) {
        produkt.setPant(pant);
    }

    @Override
    public void setVisning(Produktgruppe produktgruppe, EnumArrangementVisning salg) {
        produktgruppe.setVisning(salg);
    }

    @Override
    public void setErBetalt(Rundvisning rundvisning, boolean erBetalt){
        rundvisning.setErBetalt(erBetalt);
    }

    @Override
    public  void setDato(Salg salg, LocalDate dato) {
        salg.setDato(dato);
    }

    @Override
    public void setModetidspunkt(Rundvisning rundvisning, LocalTime modetidspunkt){rundvisning.setModetidspunkt(modetidspunkt);}

    @Override
    public Pris getRundvisningsPris(){
        return Storage.getRundvisningPris();
    }

    @Override
    public double fraTr??kRabatFraTotalPris(Salg salg, double totalPris, double rabat){
        return salg.fratr??kRabatFraTotalPris(totalPris, rabat);
    }

    @Override
    public void setAftaltPris(Salgslinje valgteSalgsLinje, int indtastetPris){
        valgteSalgsLinje.setAftaltPris(indtastetPris);
    }

    @Override
    public void init() {
        initStorage();
    }

    @Override
    public void initStorage() {



    //    Arrangementer
    Arrangement a1 = createArrangement("Fredagsbar");
    Arrangement a2 = createArrangement("Daglig butikssalg");


    Produktgruppe pg1 = createProduktgruppe("Flaske");
    Produktgruppe pg2 = createProduktgruppe("Fad??l, 40 cl");
    Produktgruppe pg3 = createProduktgruppe("Spiritus");
    Produktgruppe pg4 = createProduktgruppe("Fustage");
    Produktgruppe pg5 = createProduktgruppe("Kulsyre");
    Produktgruppe pg6 = createProduktgruppe("Malt");
    Produktgruppe pg7 = createProduktgruppe("Bekl??dning");
    Produktgruppe pg8 = createProduktgruppe("Anl??g");
    Produktgruppe pg9 = createProduktgruppe("Glas");
    Produktgruppe pg10 = createProduktgruppe("Sampakninger");
    Produktgruppe pg11 = createProduktgruppe("Klippekort");

    // Betalingsmetoder
    Betalingsmetode dankort = createBetalingsmetode(EnumBetalingsmetode.DANKORT);
    Betalingsmetode kontant = createBetalingsmetode(EnumBetalingsmetode.KONTANT);
    Betalingsmetode klippekort = createBetalingsmetode(EnumBetalingsmetode.KLIPPEKORT);
    Betalingsmetode mobilepay = createBetalingsmetode(EnumBetalingsmetode.MOBILEPAY);
    Betalingsmetode regning = createBetalingsmetode(EnumBetalingsmetode.REGNING);

//    s??tter lige Fustager og Kulsyre og Anl??g til at kunne udlejes
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
    pg11.setVisning(EnumArrangementVisning.SALG);
    //

    Produkt p1 = createProdukt("Klosterbryg",pg1);
    Produkt p2 = createProdukt("Sweet Georgia Brown",pg1);
    Produkt p3 = createProdukt("Extra Pilsner",pg1);
    Produkt p4 = createProdukt("Celebration",pg1);
    Produkt p5 = createProdukt("Blondie",pg1);
    Produkt p6 = createProdukt("For??rsbryg",pg1);
    Produkt p7 = createProdukt("India Pale Ale",pg1);
    Produkt p8 = createProdukt("Julebryg",pg1);
    Produkt p9 = createProdukt("Julet??nden",pg1);
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
    Produkt p20 = createProdukt("For??rsbryg",pg2);
    Produkt p21 = createProdukt("India Pale Ale",pg2);
    Produkt p22 = createProdukt("Julebryg",pg2);
    Produkt p23 = createProdukt("Imperial Stout",pg2);
    Produkt p24 = createProdukt("Special",pg2);
    Produkt p25 = createProdukt("??blebrus",pg2);
    Produkt p26 = createProdukt("Chips",pg2);
    Produkt p27 = createProdukt("Peanuts",pg2);
    Produkt p28 = createProdukt("Cola",pg2);
    Produkt p29 = createProdukt("Nikoline",pg2);
    Produkt p30 = createProdukt("7-Up",pg2);
    Produkt p31 = createProdukt("Vand",pg2);
    Produkt p32 = createProdukt("??lp??lser",pg2);

    Produkt p33 = createProdukt("Whisky 45% 50 cl r??r",pg3);
    Produkt p34 = createProdukt("Whisky 4 cl",pg3);
    Produkt p35 = createProdukt("Whisky 43% 50 cl r??r",pg3);
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
    Produkt p47 = createProdukt("For??rsbryg, 20 liter",pg4);
    Produkt p48 = createProdukt("India Pale Ale, 20 liter",pg4);
    Produkt p49 = createProdukt("Julebryg, 20 liter",pg4);
    Produkt p50 = createProdukt("Imperial Stout, 20 liter",pg4);
//    Produkt p51 = createProdukt("Pant",pg4);

    Produkt p52 = createProdukt("6 kg",pg5);
    Produkt p54 = createProdukt("4 kg",pg5);
    Produkt p55 = createProdukt("10 kg",pg5);

    Produkt p56 = createProdukt("25 kg s??k",pg6);

    Produkt p57 = createProdukt("t-shirt",pg7);
    Produkt p58 = createProdukt("polo",pg7);
    Produkt p59 = createProdukt("cap",pg7);

    Produkt p60 = createProdukt("1-hane",pg8);
    Produkt p61 = createProdukt("2-haner",pg8);
    Produkt p62 = createProdukt("Bar med flere haner",pg8);
    Produkt p63 = createProdukt("Levering",pg8);
    Produkt p64 = createProdukt("Krus",pg8);

    Produkt p65 = createProdukt("uanset st??rrelse",pg9);

    Produkt p66 = createProdukt("gave??ske 2 ??l, 2 glas",pg10);
    Produkt p67 = createProdukt("gave??ske 4 ??l",pg10);
    Produkt p68 = createProdukt("tr??kasse 6 ??l",pg10);
    Produkt p69 = createProdukt("gavekurv 6 ??l, 2 glas",pg10);
    Produkt p70 = createProdukt("tr??kasse 6 ??l, 6 glas",pg10);
    Produkt p71 = createProdukt("tr??kasse 12 ??l",pg10);
    Produkt p72 = createProdukt("papkasse 12 ??l",pg10);

    Produkt p73 = createProdukt("Klippekort", pg11);

        //        priser
        Pris pris1 = createPris(2,70,p1,a1);
        Pris pris2 = createPris(2,65,p2,a1);
        Pris pris3 = createPris(2,70,p15,a1);
        Pris pris4 = createPris(2,65,p16,a1);
        Pris pris5 = createPris(2,70,p1,a1);
        Pris pris6 = createPris(2,65,p2,a1);
        Pris pris7 = createPris(2,70,p3,a1);
        Pris pris8 = createPris(2,65,p4,a1);
        Pris pris9 = createPris(2,70,p5,a1);
        Pris pris10 = createPris(2,65,p6,a1);
        Pris pris11 = createPris(2,70,p7,a1);
        Pris pris12 = createPris(2,65,p8,a1);
        Pris pris13 = createPris(2,70,p9,a1);
        Pris pris14 = createPris(3,65,p10,a1);
        Pris pris15 = createPris(1,70,p11,a1);
        Pris pris16 = createPris(1,65,p12,a1);
        Pris pris17 = createPris(1,70,p13,a1);
        Pris pris18 = createPris(1,65,p14,a1);
        Pris pris19= createPris(1,38,p15,a1);
        Pris pris20= createPris(1,38,p16,a1);
        Pris pris21 = createPris(1,38,p17,a1);
        Pris pris22 = createPris(1,38,p18,a1);
        Pris pris23 = createPris(1,38,p19,a1);
        Pris pris24 = createPris(1,38,p20,a1);
        Pris pris25= createPris(38,p21,a1);
        Pris pris26 = createPris(38,p22,a1);
        Pris pris27 = createPris(38,p23,a1);
        Pris pris28 = createPris(38,p24,a1);
        Pris pris29 = createPris(15,p25,a1);
        Pris pris30 = createPris(10,p26,a1);
        Pris pris31 = createPris(15,p27,a1);
        Pris pris32 = createPris(15,p28,a1);
        Pris pris33 = createPris(15,p29,a1);
        Pris pris34 = createPris(15,p30,a1);
        Pris pris35 = createPris(10,p31,a1);
        Pris pros36 = createPris(1,75,p32,a1);

        Pris pr1 = createPris(36,p1,a2);
        Pris pr2 = createPris(36,p2,a2);
        Pris pr3 = createPris(36,p3,a2);
        Pris pr4 = createPris(36,p4,a2);
        Pris pr5 = createPris(36,p5,a2);
        Pris pr6 = createPris(36,p6,a2);
        Pris pr7 = createPris(36,p7,a2);
        Pris pr8 = createPris(36,p8,a2);
        Pris pr9 = createPris(36,p9,a2);
        Pris pr10 = createPris(36,p10,a2);
        Pris pr11 = createPris(36,p11,a2);
        Pris pr12 = createPris(36,p12,a2);
        Pris pr13 = createPris(36,p13,a2);
        Pris pr14 = createPris(60,p14,a2);
        Pris pr15= createPris(75,p15,a2);
        Pris pr16= createPris(75,p16,a2);
        Pris pr17 = createPris(70,p17,a2);
        Pris pr18 = createPris(65,p18,a2);
        Pris pr19 = createPris(75,p19,a2);
        Pris pr20 = createPris(75,p20,a2);
        Pris pr21 = createPris(38,p21,a2);
        Pris pr22 = createPris(38,p22,a2);
        Pris pr23 = createPris(38,p23,a2);
        Pris pr24 = createPris(38,p24,a2);
        Pris pr25 = createPris(15,p25,a2);
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
        Pris pr42 = createPris(775,p42,a2);
        Pris pr43 = createPris(625,p43,a2);
        Pris pr44 = createPris(575,p44,a2);
        Pris pr45 = createPris(775,p45,a2);
        Pris pr46 = createPris(700,p46,a2);
        Pris pr47 = createPris(775,p47,a2);
        Pris pr48 = createPris(775,p48,a2);
        Pris pr49 = createPris(775,p49,a2);
        Pris pr50 = createPris(775,p50,a2);
        Pris pr52 = createPris(400,p52,a2);
        Pris pr54 = createPris(350,p54,a2);
        Pris pr55 = createPris(600,p55,a2);
        Pris pr56 = createPris(75,p56,a2);
        Pris pr57 = createPris(75,p57,a2);
        Pris pr58 = createPris(75,p58,a2);
        Pris pr59 = createPris(75,p59,a2);
        Pris pr60 = createPris(250,p60,a2);
        Pris pr61 = createPris(400,p61,a2);
        Pris pr62 = createPris(500,p62,a2);
        Pris pr63 = createPris(500,p63,a2);
        Pris pr64 = createPris(75,p64,a2);
        Pris pr65 = createPris(75,p65,a2);
        Pris pr66 = createPris(75,p66,a2);
        Pris pr67 = createPris(75,p67,a2);
        Pris pr68 = createPris(75,p68,a2);
        Pris pr69 = createPris(75,p69,a2);
        Pris pr70 = createPris(75,p70,a2);
        Pris pr71 = createPris(75,p71,a2);
        Pris pr72 = createPris(75,p72,a2);

        Pris pris75 = createPris(2,p72, a1);
        Pris pris76 = createPris(130,p73,a1);

        //Kunder
        Kunde k1 = createKunde("Hans","12345678", "hans112@gmail.com","Vejen 1");
        Kunde k2 = createKunde("Michael","32783292","mich43fw@gmail.com","Solen 43");
        Kunde k3 = createKunde("Torben","87398409","torbenfradk@gmail.com","Hejgade 12");

        p42.setPant(200);
        p43.setPant(200);
        p44.setPant(200);
        p45.setPant(200);
        p46.setPant(200);
        p47.setPant(200);
        p48.setPant(200);
        p49.setPant(200);
        p50.setPant(200);
        p52.setPant(1000);
        p54.setPant(1000);
        p55.setPant(1000);

//        Lav lidt salg at kunne vise i oversigten
        Salg salg1 = createSalg();
        Salg salg2 = createSalg();
        Salg salg3 = createSalg();
        Salg salg4 = createSalg();
        Salg salg5 = createSalg();

        salg1.createSalgslinje(2,pris1,2);
        salg1.createSalgslinje(2,pris5,2);
        salg1.createSalgslinje(2,pris9,2);

        salg2.createSalgslinje(2,pris76);

        salg3.createSalgslinje(3,pris76);

        salg4.createSalgslinje(1,pris12);
        salg4.createSalgslinje(3,pris3);
        salg4.createSalgslinje(1,pris21);
        salg4.createSalgslinje(2,pris16);

        salg5.createSalgslinje(1,pris3);
        salg5.createSalgslinje(1,pris4);
        salg5.createSalgslinje(2,pris8);
        salg5.createSalgslinje(1,pris10);

        salg1.setBetalingsmetode(klippekort);
        salg2.setBetalingsmetode(dankort);
        salg3.setBetalingsmetode(kontant);
        salg4.setBetalingsmetode(mobilepay);
        salg5.setBetalingsmetode(dankort);

        LocalDate localDate1 = LocalDate.now();
        LocalDate localDate2 = LocalDate.of(2022,11,5);
        LocalDate localDate3 = LocalDate.of(2022,11,6);
        LocalDate localDate4 = LocalDate.of(2022,11,3);

        salg1.setDato(localDate1);
        salg2.setDato(localDate2);
        salg3.setDato(localDate3);
        salg4.setDato(localDate4);
    }
}
