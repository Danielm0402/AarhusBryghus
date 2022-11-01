package application.model;

import application.Controller.Controller;

import java.util.ArrayList;
import java.util.Date;

public class Salg {
    private final ArrayList<Salgslinje> salgslinjer = new ArrayList<>();
    private Betalingsmetode betalingsmetode;

    private double rabatprocent;
    private Date dato;

    public Salg(){
    }


    public ArrayList<Salgslinje> getSalgsLinjer(){
        return new ArrayList<>(salgslinjer);
    }

    public Salgslinje createSalgslinje(int antal, double aftaltPris, Pris pris){
        Salgslinje salgslinje = new Salgslinje(antal,aftaltPris,pris);
        salgslinjer.add(salgslinje);
        return salgslinje;
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

    public void setBetalingsmetode(Betalingsmetode betalingsmetode) {
        this.betalingsmetode = betalingsmetode;
    }

    public void setRabatprocent(double rabatprocent) {
        this.rabatprocent = rabatprocent;
    }

    public void setDato(Date dato) {
        this.dato = dato;
    }

    public double SamletPris() {
        double samletPris = 0;
        if (rabatprocent > 0) {
            double procent = 1 - (rabatprocent / 100);
            for (Salgslinje s : salgslinjer) {
                samletPris += s.getEnhedspris();
            }
            return samletPris * procent;
        }else {
                for (Salgslinje s : salgslinjer) {
                    samletPris += s.getEnhedspris();
                }
                return samletPris;
            }
        }

        public boolean incrementSalgslinje(Pris pris){
            boolean existsAlready = false;
//            for (int i = 0; i < salgslinjer.size(); i++) {
//                if (salgslinjer.get(i).getPris() == pris) {
//                    salgslinjer.get(i).incrementSalgslinje();
//                    existsAlready = true;
//                }
//            }
            for (Salgslinje s : getSalgsLinjer()) {
                if (s.getPris() == pris) {
                    s.incrementSalgslinje();
                    existsAlready = true;
                }
            }
            return existsAlready;
        }





}
