package application.model;

public class Salgslinje {
    private int antal;
    private Pris pris;
    private int aftaltPris;
    private int klip;

    public Salgslinje(int antal, Pris pris){
        this.antal = antal;
        this.pris=pris;
        this.aftaltPris = pris.getEnhedspris();
    }

    public Salgslinje(int antal, Pris pris,int klip){
        this.antal = antal;
        this.pris=pris;
        this.aftaltPris = pris.getEnhedspris();
        this.klip = klip;
    }

    public int getAntal() {
        return antal;
    }

    public int getAftaltPris() {
        return aftaltPris;
    }

    public int getEnhedspris() {
        return pris.getEnhedspris();
    }

    public Produkt getProdukt(){
        return pris.getProdukt();
    }

    public void setAntal(int antal) {
        this.antal = antal;
    }

    public void setAftaltPris(int aftaltPris) {
        this.aftaltPris = aftaltPris;
    }

    public void setPris(Pris pris) {
        this.pris = pris;
    }

    public int getKlip() {
        return klip;
    }

    public void incrementSalgslinje(){
        this.antal++;
    }

    @Override
    public String toString() {
        String s = "";
        if (pris.getProdukt().getPant() > 0) {
            s = " pant: " + pris.getProdukt().getPant() + ",-";
        } else if (pris.getKlip() != 0) {
            return pris.getProdukt().toString() + " " + antal + " stk af " + getAftaltPris() + ",- / " + pris.getKlip() + " klip";
        } else {
            return pris.getProdukt().toString() + " " + antal + " stk af " + getAftaltPris() + ",-" + s;
        }
        return null;
    }

    public Pris getPris() {
        return pris;
    }
}
