package application.model;

public class Pris {

    private int enhedspris;
    private Arrangement arrangement;

    public Pris(int enhedspris,Produkt produkt, Arrangement arrangement) {
        this.enhedspris = enhedspris;
        this.arrangement = arrangement;
    }

    public Arrangement getArrangement(){
        return arrangement;
    }




}
