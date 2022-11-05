package application.model;

public class Salgslinje {
    private int antal;
    private Pris pris;
    private int aftaltPris;

    public Salgslinje(int antal, Pris pris){
        this.antal = antal;
        this.pris=pris;
        this.aftaltPris = pris.getEnhedspris();
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

    public void incrementSalgslinje(){
        this.antal++;
    }

    @Override
    public String toString() {
        String s = "";
        if (pris.getProdukt().getPant() >0){
            s = " pant: "+pris.getProdukt().getPant()+",-";
        }
        return pris.getProdukt().toString() + " " + antal + " stk af " + getAftaltPris()+",-" +s;
    }

    public Pris getPris() {
        return pris;
    }
}
