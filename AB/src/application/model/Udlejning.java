package application.model;

public class Udlejning extends Salg{
    private Kunde kunde;
    private boolean erAfleveret;


    public Udlejning(Kunde kunde){
        this.kunde = kunde;
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

    public int getTotalPant() {
        int totalPant = 0;
        for (Salgslinje s : super.getSalgsLinjer()){
            totalPant += s.getProdukt().getPant();
        }
        return totalPant;
    }

    @Override
    public String toString() {
        return kunde.getNavn() +", tlf. " + kunde.getTelefon() + ", udlejningsdato: "+super.getDato();
    }


}
