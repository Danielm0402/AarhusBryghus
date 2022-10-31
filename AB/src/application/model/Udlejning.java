package application.model;

public class Udlejning extends Salg{
    private Kunde kunde;

    private boolean erAfleveret;

    public Udlejning(){
    }

    public void setKunde(Kunde kunde) {
        this.kunde = kunde;
    }

    public void setErAfleveret(boolean erAfleveret) {
        this.erAfleveret = erAfleveret;
    }

    public Kunde getKunde() {
        return kunde;
    }

    public boolean erAfleveret() {
        return erAfleveret;
    }


}
