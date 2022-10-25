package application.model;

import java.util.ArrayList;

public class Produktgruppe {



    private final ArrayList<Produkt> produkter = new ArrayList<>();

    public ArrayList<Produkt> getProdukter(){
        return new ArrayList<>(produkter);
    }

    public Produkt createProdukt(String produktNavn){
        Produkt produkt = new Produkt(produktNavn);
        produkter.add(produkt);
        return produkt;
    }

    public void addProdukt(Produkt produkt){
        if (!produkter.contains(produkt)){
            produkter.add(produkt);
        }
    }

    public void removeProdukt(Produkt produkt){
        if (produkter.contains(produkt)){
            produkter.remove(produkt);
        }
    }



}
