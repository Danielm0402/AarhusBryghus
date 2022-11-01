package application.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class Rundvisning extends Salg{
    private Kunde kunde;

    private boolean erBetalt;
    private LocalDateTime modetidspunkt;
    private int antalDeltagere;


    public Rundvisning(){
    }

    public void setKunde(Kunde kunde) {
        this.kunde = kunde;
    }

    public void setErBetalt(boolean erBetalt) {
        this.erBetalt = erBetalt;
    }

    public void setModetidspunkt(LocalDateTime modetidspunkt) {
        this.modetidspunkt = modetidspunkt;
    }

    public Kunde getKunde() {
        return kunde;
    }

    public boolean isErBetalt() {
        return erBetalt;
    }

    public LocalDateTime getModetidspunkt() {
        return modetidspunkt;
    }
}
