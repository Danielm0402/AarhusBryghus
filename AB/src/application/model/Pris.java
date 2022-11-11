package application.model;

public class Pris {

    private Produkt produkt;
    private int enhedspris;
    private Arrangement arrangement;
    private int Klip;

    Pris(int enhedspris,Produkt produkt, Arrangement arrangement) {
        this.enhedspris = enhedspris;
        this.produkt = produkt;
        this.arrangement = arrangement;
    }

    Pris(int enhedspris,Produkt produkt, Arrangement arrangement, int klip){
        this.enhedspris = enhedspris;
        this.produkt = produkt;
        this.arrangement = arrangement;
        this.Klip = klip;
    }

    public int getEnhedspris() {
        return enhedspris;
    }

    public Arrangement getArrangement(){
        return arrangement;
    }

    public Produkt getProdukt(){
        return produkt;
    }

    public int getKlip() {
        return Klip;
    }

    @Override
    public String toString() {
        if (Klip != 0){
            return  produkt +
                    ", pris: " + enhedspris + ",-" +
                    "/ " + Klip + " klip";
        }else {
            return  produkt +
                    ", pris: " + enhedspris + ",-";
        }

    }
}
