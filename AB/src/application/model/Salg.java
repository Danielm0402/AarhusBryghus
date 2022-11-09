package application.model;


import java.time.LocalDate;
import java.util.ArrayList;


public class Salg {
    private final ArrayList<Salgslinje> salgslinjer = new ArrayList<>();
    private Betalingsmetode betalingsmetode;
    private double totalPris;
    private int totalKlip;
    private double rabatprocent;
    private LocalDate dato = LocalDate.now();

    public Salg(){
    }

    public ArrayList<Salgslinje> getSalgsLinjer(){
        return new ArrayList<>(salgslinjer);
    }

    public Salgslinje createSalgslinje(int antal, Pris pris){
        Salgslinje salgslinje = new Salgslinje(antal, pris);
        salgslinjer.add(salgslinje);
        return salgslinje;
    }

    public Salgslinje createSalgslinje(int antal, Pris pris, int klip){
        Salgslinje salgslinje = new Salgslinje(antal, pris, klip);
        salgslinjer.add(salgslinje);
        return salgslinje;
    }

    public LocalDate getDato() {
        return dato;
    }

    public double getTotalPris() {
        totalPris = 0;
        for (Salgslinje s : salgslinjer){
            if (!betalingsmetode.getBetalingstype().equals(EnumBetalingsmetode.KLIPPEKORT)){
                totalPris += s.getAftaltPris() * s.getAntal();
            }
        }
        return totalPris;
    }

    public double udregnTotalPris(){
        double totalPris = 0;

        for (Salgslinje salgslinje : getSalgsLinjer()){
            totalPris += salgslinje.getAftaltPris() * salgslinje.getAntal();
        }

        return totalPris;
    }

    public void setTotalPris(double totalPris) {
        this.totalPris = totalPris;
    }

    public int getTotalKlip() {
        totalKlip = 0;
        for (Salgslinje s : salgslinjer){
            if (betalingsmetode.getBetalingstype().equals(EnumBetalingsmetode.KLIPPEKORT)){
                totalKlip += s.getKlip() * s.getAntal();
            }
        }
        return totalKlip;
    }

    public int udregnTotalKlip(){
        int totalKlip = 0;

        for (Salgslinje salgslinje : getSalgsLinjer()){
            totalKlip += salgslinje.getKlip() * salgslinje.getAntal();
        }

        return totalKlip;
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

    public Betalingsmetode getBetalingsmetode(){
        return betalingsmetode;
    }

    public void setRabatprocent(double rabatprocent) {
        this.rabatprocent = rabatprocent;
    }

    public void setDato(LocalDate dato) {
        this.dato = dato;
    }

    public double fraTrækRabatFraTotalPris(double totalPris, double rabat){
        this.rabatprocent = rabat;
        return totalPris * (1-(rabat/100));
    }

        public boolean incrementSalgslinje(Pris pris){
            boolean existsAlready = false;
            for (Salgslinje s : getSalgsLinjer()) {
                if (s.getPris() == pris) {
                    s.incrementSalgslinje();
                    existsAlready = true;
                }
            }
            return existsAlready;
        }

    @Override
    public String toString() {
        if (betalingsmetode.getBetalingstype().equals(EnumBetalingsmetode.KLIPPEKORT)){
            return getTotalKlip() + " klip, betalt med " + betalingsmetode;
        }else {
            return getTotalPris() + " kr, betalt med " + betalingsmetode;
        }
    }
}
