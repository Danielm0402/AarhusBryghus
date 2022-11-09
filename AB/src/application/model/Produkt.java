package application.model;

import java.util.ArrayList;

public class Produkt {


    private String navn;
    private int pant;

    public Produkt(String navn) {
        this.navn = navn;
    }

    @Override
    public String toString() {
        return navn;
    }


    public void setPant(int pant) {
        this.pant=pant;
    }

    public int getPant() {
        return pant;
    }

    public String getNavn() {
        return navn;
    }
}
