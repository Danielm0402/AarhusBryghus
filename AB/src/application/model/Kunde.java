package application.model;

import java.util.ArrayList;

public class Kunde {

    private final ArrayList<Rundvisning> rundvisninger = new ArrayList<>();
    private final ArrayList<Udlejning> udlejninger = new ArrayList<>();
    private String navn;
    private String telefon;
    private String email;
    private String adresse;

    public Kunde(String navn, String telefon, String email, String adresse) {
        this.navn = navn;
        this.telefon = telefon;
        this.email = email;
        this.adresse = adresse;
    }

    public ArrayList<Rundvisning> getRundvisninger() {
        return rundvisninger;
    }

    public ArrayList<Udlejning> getUdlejninger() {
        return udlejninger;
    }

    public String getNavn() {
        return navn;
    }

    public String getTelefon() {
        return telefon;
    }

    public String getEmail() {
        return email;
    }

    public String getAdresse() {
        return adresse;
    }


    public void setNavn(String navn) {
        this.navn = navn;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Override
    public String toString() {
        return navn +
                ", Tlf: " + telefon +
                ", Email: " + email;
    }
}
