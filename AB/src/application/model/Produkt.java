package application.model;

import java.util.ArrayList;

public class Produkt {
    public Produkt(String produktNavn) {
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



}
