package application.model;

import java.util.ArrayList;

public class Produkt {

    private String navn;
    private int pant;

    Produkt(String navn) {
        this.navn = navn;
    }

    private final ArrayList<Pris> priser = new ArrayList<>();

    public ArrayList<Pris> getPriser(){
        return new ArrayList<>(priser);
    }

    public void addPris(Pris pris){
        if (!priser.contains(pris)) {
            priser.add(pris);
        }
    }

    public void removePris(Pris pris){
        if (priser.contains(pris)) {
            priser.remove(pris);
        }
    }

    @Override
    public String toString() {
        return navn;
    }


    public void setPant(int pant) {
        this.pant=pant;
    }
}
