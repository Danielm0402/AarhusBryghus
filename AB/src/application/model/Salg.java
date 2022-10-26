package application.model;

import java.util.ArrayList;

public class Salg {
    private final ArrayList<Salgslinje> salgslinjer = new ArrayList<>();
    private Betalingsmetode betalingsmetode;

    private double rabatprocent;


    public ArrayList<Salgslinje> getSalgsLinjer(){
        return new ArrayList<>(salgslinjer);
    }

    public Salgslinje createSalgslinje(int antal, double aftaltPris, Pris pris){
        Salgslinje salgslinje = new Salgslinje(antal,aftaltPris,pris);
        salgslinjer.add(salgslinje);
        return salgslinje;
    }

    public void setBetalingsmetode(Betalingsmetode betalingsmetode) {
        this.betalingsmetode = betalingsmetode;
    }

    public double givRabatSamletPris(double rabatprocent){
        double samletPris = 0;
        double procent=1-(rabatprocent/100);
        for(Salgslinje s : salgslinjer){
            samletPris += s.getPris();
        }
        return samletPris*procent;
    }

    public double samletPris(){
        double samletPris = 0;
        for(Salgslinje s : salgslinjer){
            samletPris += s.getPris();
        }
        return samletPris;
    }

    public void addSalgslinje(Salgslinje salgslinje){
        if(!salgslinjer.contains(salgslinje)){
            salgslinjer.add(salgslinje);
        }
    }

    public void removeSalgslinje(Salgslinje salgslinje){
        if(salgslinjer.contains(salgslinje)){
            salgslinjer.remove(salgslinje);
        }
    }

}
