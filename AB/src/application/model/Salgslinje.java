package application.model;

public class Salgslinje {
    private int antal;
    private double aftaltPris;
    private Pris pris;

    public Salgslinje(int antal, double aftaltPris, Pris pris){
        this.antal = antal;
        this.aftaltPris=aftaltPris;
        this.pris=pris;
    }

    public int getAntal() {
        return antal;
    }

    public double getAftaltPris() {
        return aftaltPris;
    }

    public int getPris() {
        return pris.getEnhedspris();
    }

    public void setAntal(int antal) {
        this.antal = antal;
    }

    public void setAftaltPris(double aftaltPris) {
        this.aftaltPris = aftaltPris;
    }

    public void setPris(Pris pris) {
        this.pris = pris;
    }


    @Override
    public String toString() {
        return pris.getProdukt().toString() + ", Antal: 1, "+ pris.getEnhedspris()+",-";
    }
}
