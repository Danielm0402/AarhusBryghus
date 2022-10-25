package application.model;

public class Pris {

    private int enhedspris;
    private Arrangement arrangement;

    public Pris(int enhedspris,Arrangement arrangement) {
        this.enhedspris = enhedspris;
        this.arrangement = arrangement;
    }

    public Arrangement getArrangement(){
        return arrangement;
    }




}
