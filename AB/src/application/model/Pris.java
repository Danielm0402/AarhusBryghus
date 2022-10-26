package application.model;

public class Pris {

    private Produkt produkt;
    private int enhedspris;
    private Arrangement arrangement;

    public Pris(int enhedspris,Produkt produkt, Arrangement arrangement) {
        this.enhedspris = enhedspris;
        this.produkt = produkt;
        this.arrangement = arrangement;
    }

    public Arrangement getArrangement(){
        return arrangement;
    }

    public Produkt getProdukt(){
        return produkt;
    }

    @Override
    public String toString() {
        return  produkt +
                ", pris: " + enhedspris + ",-";
    }
}
