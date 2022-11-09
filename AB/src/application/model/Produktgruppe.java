package application.model;

import java.util.ArrayList;

public class Produktgruppe {
    private String navn;
    private final ArrayList<Produkt> produkter = new ArrayList<>();
    private boolean isBrugtTilUdlejning = false;
    private EnumArrangementVisning visning;

    public Produktgruppe(String navn) {
        this.navn = navn;
    }

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

    public EnumArrangementVisning getVisning() {
        return visning;
    }

    public void setVisning(EnumArrangementVisning visning) {
        this.visning = visning;
    }

    @Override
    public String toString() {
        return navn;
    }

    public void setProduktgruppeSomUdlejning() {
        isBrugtTilUdlejning = true;
    }

    public boolean isBrugtTilUdlejning(){
        return isBrugtTilUdlejning;
    }

}
