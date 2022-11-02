package application.model;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

public class Rundvisning extends Salg{
    private Kunde kunde;

    private boolean erBetalt;
    private LocalTime modetidspunkt;
    private int antalDeltagere;


    public Rundvisning(){
    }

    public void setKunde(Kunde kunde) {
        this.kunde = kunde;
    }

    public void setErBetalt(boolean erBetalt) {
        this.erBetalt = erBetalt;
    }

    public void setModetidspunkt(LocalTime modetidspunkt) {
        this.modetidspunkt = modetidspunkt;
    }

    public Kunde getKunde() {
        return kunde;
    }

    public boolean isErBetalt() {
        return erBetalt;
    }

    public LocalTime getModetidspunkt() {
        return modetidspunkt;
    }

    public int getAntalDeltagere() {
        return antalDeltagere;
    }

    public void setAntalDeltagere(int antal){
        antalDeltagere = antal;
    }

    @Override
    public String toString() {
        return
                "erBetalt: " + erBetalt + ", Kunde: " + kunde.getNavn() +

                ", Mødetid: " + modetidspunkt +
                ", Deltagere: " + antalDeltagere;
    }
}
