package application.model;

import java.util.ArrayList;

public class Arrangement {

    private String arrangementsNavn;
    private final ArrayList<Pris> priser = new ArrayList<>();

    public Arrangement(String arrangementsNavn){
        this.arrangementsNavn = arrangementsNavn;
    }

    public ArrayList<Pris> getPriser(){
        return new ArrayList<>(priser);
    }

    public Pris createPris(int enhedspris, Produkt produkt){
        Pris pris = new Pris(enhedspris, produkt,this);
        priser.add(pris);
        return pris;
    }

    public void removePris(Pris pris){
        if (priser.contains(pris)){
            priser.remove(pris);
        }
    }

    @Override
    public String toString() {
        return arrangementsNavn;
    }


}
